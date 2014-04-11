/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.HashMap;
import java.util.Vector;
import java.util.Map.Entry;


class MultipleFunctionElement extends FunctionElement
{
	/**
	 * The function can be constructed with no arguments to be added later
	 */
	public MultipleFunctionElement()
	{
		this.precedence=4;
	}
	
	/**
	 * Multiple being a binary operator, can also be constructed with the standard 2 arguments
	 * @param x is an argument which the multiple function is initialized with.
	 * @param y is an argument which the multiple function is initialized with.
	 */
	public MultipleFunctionElement(FormulaElement x, FormulaElement y)
	{
		this();
		this.addNewArgument(x);
		this.addNewArgument(y);
	}
	
	/**
	 * addNewArgument is overidden here from the superclass to add the elements of x to the 
	 * multiple function if the argument is a multiple function
	 * @param x is an argument which is added to the multiple function
	 */
	public void addNewArgument(FormulaElement x)
	{
		if(x instanceof MultipleFunctionElement)
			for(FormulaElement i: ((MultipleFunctionElement) x).getArguments())
				this.addNewArgument(i);
		else
			super.addNewArgument(x);
	}
	
	public double evaluate() throws Exception
	{
		Vector<FormulaElement> temp=this.getArguments();
		double total=1;
		for(FormulaElement x: temp)
		{
			total*=x.evaluate();
		}
		return total;
	}
	
	/**
	 * @return The string representation of the multiple function in the form: argumentargument, 
	 * where:
	 * 1. An argument with lower precedence than multiplication is surrounded with brackets.
	 * 2. Equal arguments are counted and represented as argument^count
	 * 3. Constants are multiplied out and placed before variables
	 * 4. Variables are placed after constants.
	 */
	public String toString()
	{
		String output="";
		double constant_total=1;
		HashMap<FormulaElement, Integer> vars=new HashMap<FormulaElement, Integer>();	//This hashmap will hold the counts of individual variables 
		Vector<FormulaElement> temp=this.getArguments();
		for(int i=0;i<temp.size();i++)
		{			
			if(temp.elementAt(i) instanceof ConstantElement)			//Totaling constant elements
				constant_total*=((ConstantElement)temp.elementAt(i)).evaluate();
			else if(temp.elementAt(i) instanceof VariableElement)
			{
				Boolean exists=false;
				for(FormulaElement x:vars.keySet())
				{
					if(temp.elementAt(i).toString().equals(x.toString()))			//if the hashmap already contains the variable, the count is incremented 
					{
						vars.put(x, vars.get(x)+1);
						exists=true;
					}
					
				}
				if(!exists)
					vars.put(temp.elementAt(i), 1);
			}
			else
				if(temp.elementAt(i).precedence<this.precedence&&temp.elementAt(i).precedence!=0)
					output+=("("+temp.elementAt(i).toString()+")");
				else
					output+=temp.elementAt(i).toString();
		}
		for(Entry<FormulaElement, Integer> x: vars.entrySet())	//Add variables to output 
		{
			if(x.getValue()!=1)			//If there is more than one of the same variable output the variable to the power of the count
				output=("^"+x.getValue()+output);
			output=x.getKey()+output;
		}
		if(constant_total!=1)		//If there is a constant total, add it to the output first
			if(constant_total%1!=0)
				output=constant_total+output;
			else
				output=((int)constant_total)+output;
		
		return output;
	}
}
