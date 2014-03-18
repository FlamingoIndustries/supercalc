/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

class VariableElement extends FormulaElement
{
	private String name;						
	private double value;
	
	/**
	 * 
	 * @param nme is the initialization value for the name of the variable
	 */
	public VariableElement(String nme)
	{
		name=nme;								
	}
	
	/**
	 * 
	 * @return The string name of the variable
	 */
	public String getName()
	{
		return name;							
	}											
	
	/**
	 * 
	 * @return The double value of the variable
	 */
	public double getValue()
	{
		return value;
	}									
	
	/**
	 * 
	 * @param val is the desired value to set the variable to
	 */
	public void setValue(double val)
	{
		value=val;
	}
	
	/**
	 * @return the name of the variable in string form
	 */
	public String toString()
	{
		return name;
	}
}
