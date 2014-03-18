/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

class PlusFunctionElement extends FunctionElement
{
	/**
	 * The function can be constructed with no arguments to be added later
	 */
	public PlusFunctionElement()
	{
		this.precedence=2;
	}
	
	/**
	 * Plus being a binary operator, can also be constructed with the standard 2 arguments
	 * @param x is an argument which the Divide function is initialized with.
	 * @param y is an argument which the Divide function is initialized with.
	 */
	public PlusFunctionElement(FormulaElement x, FormulaElement y)
	{
		this();
		this.addNewArgument(x);
		this.addNewArgument(y);
	}
	
	/**
	 * addNewArgument is overidden here from the superclass to add the elements of x to the 
	 * plus function if the argument is a multiple function
	 * @param x is an argument which is added to the plus function
	 */
	public void addNewArgument(FormulaElement x)
	{
		if(x instanceof PlusFunctionElement)
			for(FormulaElement i: ((PlusFunctionElement) x).getArguments())
				this.addNewArgument(i);
		else
			super.addNewArgument(x);
	}
	
	public double getValue()
	{
		Vector<FormulaElement> temp=this.getArguments();
		double total=0;
		for(FormulaElement x: temp)
		{
			total+=x.getValue();
		}
		return total;
	}
	
	/**
	 * @return The string representation of the plus function in the form: argument+argument, 
	 * where:
	 * 1. An argument with lower precedence than plus is surrounded with brackets.
	 * 2. Equal arguments are counted and represented as countargument
	 * 3. Constants are added up and placed last
	 * 4. Variables are placed before constants.
	 */
	public String toString()
	{
		String output="";
		double constant_total=0;
		Boolean first=true;
		HashMap<FormulaElement, Integer> vars=new HashMap<FormulaElement, Integer>();	//This hashmap will hold the counts of individual variables 
		Vector<FormulaElement> temp=this.getArguments();
		for(int i=0;i<temp.size();i++)
		{
			if(temp.elementAt(i) instanceof ConstantElement)	//Totaling constant elements
				constant_total+=((ConstantElement)temp.elementAt(i)).getValue();
			else if(temp.elementAt(i) instanceof VariableElement)
			{
				Boolean exists=false;
				for(FormulaElement x:vars.keySet())
				{
					if(temp.elementAt(i).toString().equals(x.toString()))		//if the hashmap already contains the variable, the count is incremented 
					{
						vars.put(x, vars.get(x)+1);
						exists=true;
					}
					
				}
				if(!exists)
					vars.put(temp.elementAt(i), 1);
			}
			else
			{	
				if(first)
					first=false;
				else
					output+="+";
				if(temp.elementAt(i).precedence<this.precedence&&temp.elementAt(i).precedence!=0)
					output+=("("+temp.elementAt(i).toString()+")");
				else
					output+=temp.elementAt(i).toString();
			}
		}
		for(Entry<FormulaElement, Integer> x: vars.entrySet())	//Add variables to output 
		{
			if(first)
				first=false;
			else
				output+="+";
			if(x.getValue()!=1)		//If there is more than one of the same variable, output the variable with the count before it
				output+=x.getValue();
			output+=x.getKey();
		}
		if(constant_total!=0)		//If there is a constant total, add it to the output last
		{
			if(first)
				first=false;
			else
				output+="+";
			if(constant_total!=0)
				if(constant_total%1!=0)
					output+=constant_total;
				else
					output+=((int)constant_total);
		}
		return output;
	}
}
