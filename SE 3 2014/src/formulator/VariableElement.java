/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

class VariableElement extends FormulaElement
{
	private String name;						
	private double value;
	private Boolean assigned;
	
	/**
	 * 
	 * @param nme is the initialization value for the name of the variable
	 */
	public VariableElement(String nme)
	{
		name=nme;	
		assigned=false;
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
	 * @throws Exception 
	 */
	public double getValue() throws Exception
	{
		if(!assigned)
			throw new Exception("Variable "+getName()+" unassigned!");
		return value;
	}	
	
	/**
	 * 
	 * @return The double value of the variable
	 * @throws Exception 
	 */
	public double evaluate() throws Exception
	{
		return getValue();
	}									
	
	/**
	 * 
	 * @param val is the desired value to set the variable to
	 */
	public void setValue(double val)
	{
		value=val;
		assigned=true;
	}
	
	/**
	 * 
	 * @param varName The name of the variable to assign
	 * @param value value to assign to the variable
	 */
	public void setVariableValue(String varName, double value)
	{
		if(name.equals(varName))
			setValue(value);
	}
	
	/**
	 * @return a boolean value if the variable has been assigned
	 */
	public Boolean isFullyGrounded()
	{
		return assigned;
	}
	
	/**
	 * @return the name of the variable in string form
	 */
	public String toString()
	{
		return name;
	}
	
	public FormulaElement getSimplifiedCopy() throws CloneNotSupportedException
	{
		return (VariableElement)this.clone();
	}
}
