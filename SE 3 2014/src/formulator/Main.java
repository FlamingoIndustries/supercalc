package formulator;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		FormulaElement result;
		FormulaElement result2;
		Branch b=new Branch();
		b.branch("f'''(x)");
		Differentiation diff=new Differentiation();
		
		result=FormulaElement.parseFormula("y(3)");
		result2=FormulaElement.parseFormula("3(y)");
//		result2=result.copyFormula();
		
	//	result.replaceVariable("x", new ConstantElement(3));
		//result=diff.numericDiff(result,"x",1);
		//System.out.println(result);
	//	System.out.println(result2);
		//System.out.println(ReadWriteFormulae.ReadFormulae());
		//ReadWriteFormulae.WriteFormulae();
		//System.out.println(result.getSimplifiedCopy()+"\n\n\n");
//		result=FormulaElement.parseFormula("(x+2)+(x-3)(x+4)/(x+4)");
//		System.out.println(result);
//		System.out.println(result.getSimplifiedCopy());
	}
}
