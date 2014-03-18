package formulator;

import java.util.Vector;

class PowerFunctionElement extends FunctionElement
{
	/**
	 * The function can be constructed with no arguments to be added later
	 */
	public PowerFunctionElement()
	{
		this.precedence=5;
	}
	
	/**
	 * Power can also be constructed with the standard 2 arguments
	 * @param x is an argument which the Divide function is initialized with.
	 * @param y is an argument which the Divide function is initialized with.
	 */
	public PowerFunctionElement(FormulaElement x, FormulaElement y)
	{
		this();
		this.addNewArgument(x);
		this.addNewArgument(y);
	}
	
	/**
	 * addNewArgument is overidden here to refuse to add an argument if there are already 2 
	 * arguments because power cannot take any more than 2 arguments
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
		return Math.pow(temp.firstElement().getValue(), temp.elementAt(1).getValue());
	}
	
	/**
	 * @return THe string representation of power in the form arg1^arg2, however if either
	 * arguments are of lower precedence, they are wrapped in brackets
	 */
	public String toString()
	{
		String output="";
		Vector<FormulaElement> temp=this.getArguments();
		for(int i=0;i<2;i++)
		{
			if(temp.elementAt(i).precedence!=0&&temp.elementAt(i).precedence!=0)
				output+=("("+temp.elementAt(i)+")");
			else
				output+=temp.elementAt(i);
			if(i==0)
				output+="^";
		}
		return output;
	}
}
