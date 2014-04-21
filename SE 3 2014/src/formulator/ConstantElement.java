/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.Arrays;

class ConstantElement extends FormulaElement
{
	private double value;					
	
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
	@Override
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
	@Override
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
	@Override
	public void setVariableValue(String varName, double value)
	{
	}
	
	@Override
	public void replaceVariable(String varName, FormulaElement replace)
	{
	}
	
	/**
	 * 
	 * @return true always because constants are always assigned
	 */
	@Override
	public Boolean isFullyGrounded()
	{
		return true;
	}
	
	@Override
	public FormulaElement getSimplifiedCopy()
	{
		ConstantElement out=new ConstantElement(this.getValue());
		return out;
	}

	@Override
	public String getXMLformat(String tabbing)
	{
		String newline=System.lineSeparator()+tabbing;
		return "<"+this.getClass().getSimpleName()+">value="+value+"</"+this.getClass().getSimpleName()+">";
	}
	
	public Boolean equals(FormulaElement comp)
	{
		if(comp instanceof ConstantElement&&value==((ConstantElement) comp).getValue())
			return true;
		else
			return false;
		
	}
}
