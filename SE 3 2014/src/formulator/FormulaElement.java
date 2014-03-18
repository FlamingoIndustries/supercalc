/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.StringTokenizer;
import java.util.Vector;


public abstract class FormulaElement
{
	public static void main(String[] args)
	{
		ConstantElement form=new ConstantElement(0);
		FormulaElement result;
		result=form.parseFormula("(2X+Y)/(X^2+5+Y)");
		System.out.println(result);
		result=form.parseFormula("cos(3X)/(2^sin(X)(10+3+x))");
		System.out.println(result);
		result=form.parseFormula("2+3a+cos(5b)^(2x+5+sin(x))");
		System.out.println(result);
		result=form.parseFormula("5b+3c^4+sin(10x+y)^cos(7a+3x-5)");
		System.out.println(result);
		result=form.parseFormula("15x^2+cos(5b+(x+x^2))^6y+2x");
		System.out.println(result);
		result=form.parseFormula("(X+2)(X-(Y^3+7)+cos(2^x))");
		System.out.println(result);
	}
	
	abstract double getValue();
	
	/**
	 * 
	 * @param formula The non-recursive string formula given by the user
	 * @return	An OOP hierarchy form of the entered formula
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FormulaElement parseFormula(String formula)
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
				while(Character.isDigit(start)&&Character.isLetter(end))
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
		
		for(int i=1;i<=6;i++)					//Making 6 passes through token vector
			for(int j=0;j<tokens.size();j++)
			{
				switch (i)
				{
					case 1:		//Pass 1
					String arg=(String) tokens.elementAt(j);
					if(arg.charAt(0)=='(')			//Bracket indicates the beginning of a subformula 
					{
						String subformula="";
						tokens.remove(j);
						int bracketcount=1;
						while(bracketcount>0)
						{
							if(tokens.elementAt(j).equals("("))
								bracketcount++;
							subformula+=(String)tokens.remove(j);
							if(tokens.elementAt(j).equals(")"))
								bracketcount--;
						}
						tokens.remove(j);
						if(!subformula.isEmpty())
							tokens.add(j, this.parseFormula(subformula));	//Add the parsed subformula to the main formula
							
					}
					else if(Character.isDigit(arg.charAt(0)))
					{
						tokens.remove(j);
						int val=Integer.parseInt(arg);				//All number tokens are replaced with Constant elements of the same value
						ConstantElement cons=new ConstantElement(val);
						tokens.add(j, cons);
					}
					else if(Character.isLetter(arg.charAt(0))&&!arg.equals("cos")&&!arg.equals("sin"))
					{
						tokens.remove(j);
						VariableElement var=new VariableElement(arg);		//All words found, that aren't sin or cos, are changed to VariableElements 
						tokens.add(j, var);
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
						String type=(String)tokens.remove(j);
						FormulaElement var=(FormulaElement)tokens.remove(j);	//Non-recursive case means only 1 formula element within brackets
						FormulaElement func;
						if(type.equals("cos"))
							func=new CosineFunctionElement(var);
						else
							func=new SineFunctionElement(var);
						tokens.add(j, func);
						//Removing 2 elements from token vector means we need to backtrack to avoid skipping elements
					}
					break;
					case 4:	//Pass 4
					if(tokens.elementAt(j).equals("^"))		//^ means there is a power
					{
						tokens.remove(j);
						FormulaElement var1=(FormulaElement)tokens.remove(j-1);
						//if(var1 instanceof PowerFunctionElement)
							//+
						FormulaElement var2=(FormulaElement)tokens.remove(j-1);
						PowerFunctionElement pow=new PowerFunctionElement(var1,var2);		//Three elements involved are replaced with power function
						tokens.add(j-1, pow);
						j--;	//Allowing for 2 removed tokens from vector
					}
					break;
					case 5:	//Pass5
					if(j<tokens.size()-1)
						if(tokens.elementAt(j)instanceof FormulaElement&&tokens.elementAt(j+1)instanceof FormulaElement)
						{
							FormulaElement var1=(FormulaElement)tokens.remove(j);					//Adjacent formula elements are replaced with multiple function
							FormulaElement var2=(FormulaElement)tokens.remove(j);
							MultipleFunctionElement mult=new MultipleFunctionElement(var1,var2);
							tokens.add(j, mult);		
						}
						else if(tokens.elementAt(j).equals("/"))				//All elements involved in division are replaced with division function
						{
							tokens.remove(j);
							FormulaElement var1=(FormulaElement)tokens.remove(j-1);			
							FormulaElement var2=(FormulaElement)tokens.remove(j-1);
							DivideFunctionElement div=new DivideFunctionElement(var1,var2);
							tokens.add(j-1, div);
							j--;
						}
					break;
					case 6:	//Pass 6 
					if(j<tokens.size()-1&&(tokens.elementAt(j).equals("+")||tokens.elementAt(j).equals("-")))
					{
						String type=(String)tokens.remove(j);
						FormulaElement var1=(FormulaElement)tokens.remove(j-1);
						FormulaElement var2=(FormulaElement)tokens.remove(j-1);
						FormulaElement func;										//Elements involved in addition or subtraction are replaced with their function 
					
						if(type.equals("+"))
							func=new PlusFunctionElement(var1,var2);
						else
							func=new MinusFunctionElement(var1,var2);
						tokens.add(j-1, func);
						j--;
					}
					break;
				}
			}
		return (FormulaElement)tokens.elementAt(0);			//The final head of the OOP hierarchy is returned 
	}
	protected int precedence=0;
}
