/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.Vector;

class SineFunctionElement extends FunctionElement
{
	/**
	 * The function can be constructed with no arguments to be added later
	 */
	public SineFunctionElement()
	{
		this.precedence=0;
	}
	
	/**
	 * The function can be constructed with the one argument necessary
	 * @param x is the argument to construct the function with
	 */
	public SineFunctionElement(FormulaElement x)
	{
		this();
		this.addNewArgument(x);
	}
	
	/**
	 * The addNewArgument function from the superclass is overidden to only allow one argument 
	 * to be added to the function. 
	 * @param x is the argument to add to the function
	 */
	public void addNewArgument(FormulaElement x)
	{
		if(this.getArguments().isEmpty())
			super.addNewArgument(x);
		else
			System.out.println("Sine already has one argument");
	}
	
	public double getValue()
	{
		Vector<FormulaElement> temp=this.getArguments();
		return Math.sin(temp.firstElement().getValue());
	}
	
	/**
	 * @return The string form of the argument inside the sine function, in the form sin(argument)
	 */
	public String toString()
	{
		return "sin("+this.getArguments().firstElement().toString()+")";
	}
}
