/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.StringTokenizer;
import java.util.Vector;


public abstract class FormulaElement
{
	public abstract double evaluate() throws Exception;
	public abstract void setVariableValue(String varName, double value);
	public abstract Boolean isFullyGrounded();
	public abstract FormulaElement getSimplifiedCopy() throws CloneNotSupportedException;
	protected int precedence=0;
	
	/**
	 * 
	 * @param formula The recursive string formula given by the user
	 * @return	An OOP hierarchy form of the entered formula
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static FormulaElement parseFormula(String formula) throws Exception
	{
		Vector tokens=new Vector();
		StringTokenizer tokenizer = new StringTokenizer(formula, "+-/^() \t", true);	//Breaking up formula into its components
		String token="";
		while(tokenizer.hasMoreTokens()==true)
		{
			token=tokenizer.nextToken();
			String num="";
			if(!token.contentEquals(" "))	//All non whitespace tokens are added to token vector
			{
				char start=token.charAt(0);
				char end=token.charAt(token.length()-1);
				while((Character.isDigit(start)||start=='.')&&Character.isLetter(end))
				{
					num+=token.charAt(0);
					token=token.substring(1);
					start=token.charAt(0);
					end=token.charAt(token.length()-1);
				}
				
				if(!num.isEmpty())				//Multiplied numbers and variables are added separately to token vector
					tokens.add(num);
				if(!token.isEmpty())
					tokens.add(token);
			}
		}
		for(int j=0;j<tokens.size();j++)
		{
			if(tokens.elementAt(j) instanceof String)
			{
				String arg=(String)tokens.elementAt(j);
				if(arg.charAt(0)=='(')			//Bracket indicates the beginning of a subformula 
				{
					String subformula="";
					tokens.remove(j);
					int bracketcount=1;
					while(bracketcount>0)
					{
						if(tokens.elementAt(j).equals("("))
							bracketcount++;
						subformula+=tokens.remove(j);
						if(j>=tokens.size())
							throw new Exception("Badly formed formula!");
						if(tokens.elementAt(j).equals(")"))
							bracketcount--;
					}
					tokens.remove(j);
					if(!subformula.isEmpty())
						tokens.add(j, parseFormula(subformula));	//Add the parsed subformula to the main formula
						
				}
				else if(arg.charAt(0)==')')
				{
					throw new Exception("Badly formed formula!");
				}
			}
		}
		return parsesubFormula(tokens);
	}
	
	/**
	 * 
	 * @param formula The non-recursive string formula given by the user
	 * @return	An OOP hierarchy form of the entered formula
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static FormulaElement parsesubFormula(Vector tokens) throws Exception
	{		
		for(int i=1;i<=6;i++)					//Making 6 passes through token vector
			for(int j=0;j<tokens.size();j++)
			{
				switch (i)
				{
					case 1:		//Pass 1
					if(tokens.elementAt(j) instanceof String)
					{
						String arg=(String) tokens.elementAt(j);
						if(Character.isDigit(arg.charAt(0)))
						{
							tokens.remove(j);	
							double val;
							try
							{
								val=Double.parseDouble(arg);
							}
							catch(Exception e)
							{
								throw new Exception("Badly Formed Formula!");
							}
							//All number tokens are replaced with Constant elements of the same value
							ConstantElement cons=new ConstantElement(val);
							tokens.add(j, cons);
						}
						else if(Character.isLetter(arg.charAt(0))&&!arg.equals("cos")&&!arg.equals("sin"))
						{
							tokens.remove(j);
							VariableElement var=new VariableElement(arg);		//All words found, that aren't sin or cos, are changed to VariableElements 
							tokens.add(j, var);
						}
					}
					break;
					case 2:	//Pass 2
					if(j<tokens.size()-1&&tokens.elementAt(j)instanceof FormulaElement&&tokens.elementAt(j+1)instanceof FormulaElement)
					{
						FormulaElement var1=(FormulaElement)tokens.remove(j);
						FormulaElement var2=(FormulaElement)tokens.remove(j);					//Any 2 side by side Variable/constant elements are replaced by a multiple function
						MultipleFunctionElement func=new MultipleFunctionElement(var1,var2);
						tokens.add(j, func);
					}
					break;
					case 3:	//Pass 3
					if(tokens.elementAt(j).equals("cos")||tokens.elementAt(j).equals("sin"))			//Bracket signifies cos or sin since non-recursive case
					{
						FunctionElement func;
						if(tokens.elementAt(j).equals("cos"))
							func=new CosineFunctionElement();
						else
							func=new SineFunctionElement();
						tokens=parseUnary(tokens,j,func);
					}
					break;
					case 4:	//Pass 4
					if(tokens.elementAt(j).equals("^"))		//^ means there is a power
					{
						tokens=parseBinary(tokens,j, new PowerFunctionElement());
						j--;	//Allowing for 2 removed tokens from vector	
					}
					break;
					case 5:	//Pass5
					if(j<tokens.size()-1&&tokens.elementAt(j)instanceof FormulaElement&&tokens.elementAt(j+1)instanceof FormulaElement)
					{
						FormulaElement var1=(FormulaElement)tokens.remove(j);					//Adjacent formula elements are replaced with multiple function
						FormulaElement var2=(FormulaElement)tokens.remove(j);
						MultipleFunctionElement mult=new MultipleFunctionElement(var1,var2);
						tokens.add(j, mult);		
					}
					else if(tokens.elementAt(j).equals("/"))
					{
						tokens=parseBinary(tokens,j, new DivideFunctionElement());
						j--;
					}
					break;
					case 6:	//Pass 6 
					if(tokens.elementAt(j).equals("+")||tokens.elementAt(j).equals("-"))
					{				
						FunctionElement func;
						if(tokens.elementAt(j).equals("+"))
							func=new PlusFunctionElement();
						else
							func=new MinusFunctionElement();
						tokens=parseBinary(tokens,j,func);
						j--;
					}
					break;
				}
			}
		return (FormulaElement)tokens.elementAt(0);			//The final head of the OOP hierarchy is returned 
	}
	
	/**
	 * 
	 * @param tokens vector of formula tokens
	 * @param index index of the function operator
	 * @param type function type
	 * @return The new token vector with the three elements involved in the function replaced by the function element 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Vector parseBinary(Vector tokens, int index, FunctionElement type) throws Exception
	{
		if(index<tokens.size()-1&&index>0&&tokens.elementAt(index-1)instanceof FormulaElement&&tokens.elementAt(index+1)instanceof FormulaElement)
		{
			tokens.remove(index);
			FormulaElement var1=(FormulaElement)tokens.remove(index-1);			
			FormulaElement var2=(FormulaElement)tokens.remove(index-1);
			type.addNewArgument(var1);
			type.addNewArgument(var2);
			tokens.add(index-1,type);
		}
		else
			throw new Exception("Badly formed formula!");
		return tokens;
	}
	
	/**
	 * 
	 * @param tokens vector of formula tokens
	 * @param index index of the function operator
	 * @param type function type
	 * @return The new token vector with the two elements involved in the function replaced by the function element 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Vector parseUnary(Vector tokens, int index, FunctionElement type) throws Exception
	{
		if(index<tokens.size()-1&&tokens.elementAt(index+1)instanceof FormulaElement)
		{
			tokens.remove(index);			
			FormulaElement var=(FormulaElement)tokens.remove(index);
			type.addNewArgument(var);
			tokens.add(index,type);
		}
		else
			throw new Exception("Badly formed formula!");
		return tokens;
	}
}
