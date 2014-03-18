/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.Vector;


class DivideFunctionElement extends FunctionElement
{	
	/**
	 * The function can be constructed with no arguments to be added later
	 */
	public DivideFunctionElement()
	{
		this.precedence=3;
	}
	
	/**
	 * Divide being a binary operator, can also be constructed with the standard 2 arguments
	 * @param x is an argument which the Divide function is initialized with.
	 * @param y is an argument which the Divide function is initialized with.
	 */
	public DivideFunctionElement(FormulaElement x, FormulaElement y)
	{
		this();
		this.addNewArgument(x);
		this.addNewArgument(y);
	}
	
	/**
	 * addNewArgument is overidden here to refuse to add an argument if there are already 2 
	 * arguments because division cannot take any more than 2 arguments
	 * @param x is a FormulaElement which is added to the list of arguments for this function
	 */
	public void addNewArgument(FormulaElement x)
	{
		if(this.getArguments().size()<2)
			super.addNewArgument(x);
		else
			System.out.println("Unable to add argument, argument limit reached");
	}
	
	public double getValue()
	{
		Vector<FormulaElement> temp=this.getArguments();
		return ((FormulaElement)temp.elementAt(0)).getValue()/((FormulaElement)temp.elementAt(1)).getValue();
	}
	
	/**
	 * @return The string representation of the divide function in the form: argument/argument, 
	 * where an argument with equal or lower precedence than division is surrounded with brackets
	 */
	public String toString()
	{
		String output="";
		Vector<FormulaElement> temp=this.getArguments();
		if(temp.elementAt(0).equals(temp.elementAt(1)))
			output="1";
		else
			for(int i=0;i<2;i++)
			{
				if(temp.elementAt(i).precedence<=this.precedence&&temp.elementAt(i).precedence!=0)
					output+=("("+temp.elementAt(i).toString()+")");
				else
					output+=temp.elementAt(i).toString();
				if(i==0)
					output+="/";
			}
		return output;
	}
}
