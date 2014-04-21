package formulator;

public class Differentiation
{
/*
 * (f(x+dx)-f(x))/dx
 */
	final ConstantElement dx=new ConstantElement(0.0000001);
	public FormulaElement numericDiff(FormulaElement form, String respect, int degree) throws Exception
	{
		//Replace variable
		FormulaElement adjustedform= form.copyFormula();
		FormulaElement replace=new PlusFunctionElement(dx, form.findVariable(respect));
		adjustedform.replaceVariable("x", replace);
		MinusFunctionElement minus=new MinusFunctionElement(adjustedform,form);
		DivideFunctionElement div=new DivideFunctionElement(minus,dx);
		if(degree==1)
			return div;
		else
			return numericDiff(div, respect, degree-1);
	}
}
