package formulator;

/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

import java.util.Vector;

abstract class FunctionElement extends FormulaElement
{
	private Vector<FormulaElement> arguments=new Vector<FormulaElement>();		//A private vector is used to house an arbitrary number of arguments
	
	/**
	 * The user can only manipulate the vector by adding arguments to the vector
	 * @param x is a FormulaElement which is added to the list of arguments for this function
	 */
	public void addNewArgument(FormulaElement x)
	{
			arguments.add(x);								
	}
	
	/**
	 * 
	 * @return a vector of arguments for this function
	 */
	public Vector<FormulaElement> getArguments()
	{
		return arguments;								 
	}
	
}
