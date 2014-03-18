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
	 * The user can only read the value through the getValue public method
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
}
