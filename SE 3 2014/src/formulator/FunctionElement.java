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
	
	/**
	 * Iterates through the arguments of the function element and passes the 
	 * assignment of the variable value to these arguments.
	 * @param varName The name of the variable to assign
	 * @param value value to assign to the variable
	 */
	public void setVariableValue(String varName, double value)
	{
		for(FormulaElement f:arguments)
			f.setVariableValue(varName, value);
	}
	
	/**
	 * @return a boolean value if the variables in the arguments are assigned
	 */
	public Boolean isFullyGrounded()
	{
		for(FormulaElement f:arguments)
			if(f instanceof VariableElement || f instanceof FunctionElement)
				if(!f.isFullyGrounded())
					return false;
		return true;
	}
	
}
