package formulator;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		FormulaElement result;
		FormulaElement result2;
		//Branch b=new Branch();
		//b.branch("graph		f(x3)");
		Differentiation diff=new Differentiation();
		
		result=FormulaElement.parseFormula("y(3)");
		result2=FormulaElement.parseFormula("3(y)");
		System.out.println(result.equals(result2));
//		result2=result.copyFormula();
		
	//	result.replaceVariable("x", new ConstantElement(3));
		//result=diff.numericDiff(result,"x",1);
		System.out.println(result);
	//	System.out.println(result2);
		ReadWriteFormulae.ReadFormulae();
		//ReadWriteFormulae.WriteFormulae();
		System.out.println(result.getSimplifiedCopy()+"\n\n\n");
		result=FormulaElement.parseFormula("(x+2)+(x-3)(x+4)/(x+4)");
		System.out.println(result);
		System.out.println(result.getSimplifiedCopy());
	}
}
