/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.Vector;

class MinusFunctionElement extends FunctionElement
{
	/**
	 * The function can be constructed with no arguments to be added later
	 */
	public MinusFunctionElement()
	{
		this.precedence=1;
	}
	
	/**
	 * Minus being a binary operator, can also be constructed with the standard 2 arguments
	 * @param x is an argument which the Divide function is initialized with.
	 * @param y is an argument which the Divide function is initialized with.
	 */
	public MinusFunctionElement(FormulaElement x, FormulaElement y)
	{
		this();
		this.addNewArgument(x);
		this.addNewArgument(y);
	}
	
	/**
	 * addNewArgument is overidden here to refuse to add an argument if there are already 2 
	 * arguments because minus cannot take any more than 2 arguments 
	 * @param x is a FormulaElement which is added to the list of arguments for this function
	 */
	public void addNewArgument(FormulaElement x)
	{
		if(this.getArguments().size()<2)
			super.addNewArgument(x);
		else
			System.out.println("Unable to add argument, argument limit reached");
	}
	
	public double evaluate() throws Exception
	{
		Vector<FormulaElement> temp=this.getArguments();
		return ((FormulaElement)temp.elementAt(0)).evaluate()-((FormulaElement)temp.elementAt(1)).evaluate();
	}
	
	/**
	 * @return The string representation of the minus function in the form: argument-argument, 
	 * where an argument with equal precedence to division is surrounded with brackets.
	 */
	public String toString()
	{
		String output="";
		Vector<FormulaElement> temp=this.getArguments();
		if(temp.elementAt(0) instanceof ConstantElement&&temp.elementAt(1) instanceof ConstantElement)					//If both arguments are constants, the arithmetic result is returned
			output+=(((ConstantElement)temp.elementAt(0)).evaluate()-((ConstantElement)temp.elementAt(1)).evaluate());
		else if(temp.elementAt(0).equals(temp.elementAt(1)))		//If both arguments are the same, the output is 0
			output="0";
		else
			for(int i=0;i<2;i++)
			{
				if(temp.elementAt(i).precedence!=0)	//If an argument is a minus function surround it with brackets
					output+=("("+temp.elementAt(i).toString()+")");
				else
					output+=temp.elementAt(i).toString();
				if(i==0)
					output+="-";
			}
		return output;
	}
}
