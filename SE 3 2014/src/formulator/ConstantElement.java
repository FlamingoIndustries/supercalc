/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

class ConstantElement extends FormulaElement
{
	private double value;					//value is kept private so that it cannot be changed from outside
	
	/**
	 * Value of constant is only set when the constant is created
	 * @param val is the double value which the constant is initialized with
	 */
	public ConstantElement(double val)
	{
		value=val;						
	}
	
	/**
	 * 
	 * @return the value assigned to the constant
	 */
	public double evaluate()
	{
		return getValue();					
	}
	
	/**
	 * 
	 * @return the value assigned to the constant
	 */
	public double getValue()
	{
		return value;					
	}
		
	/**
	 * @return the constant value in string form
	 */
	public String toString()
	{
		if(value%1==0)					//If the value is an integer, return its string form
			return ""+((int)value);
		return ""+value;
	}
	
	/**
	 * 
	 * @param varName The name of the variable to assign
	 * @param value value to assign to the variable
	 */
	public void setVariableValue(String varName, double value)
	{
	}
	
	/**
	 * 
	 * @return true always because constants are always assigned
	 */
	public Boolean isFullyGrounded()
	{
		return true;
	}
	
	public FormulaElement getSimplifiedCopy() throws CloneNotSupportedException
	{
		return (ConstantElement)this.clone();
	}
}
