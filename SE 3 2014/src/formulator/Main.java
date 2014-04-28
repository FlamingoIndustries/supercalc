package formulator;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		FormulaElement result;
<<<<<<< HEAD
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
=======
		result=FormulaElement.parseFormula("x+y");
		System.out.println(result);
		System.out.println(result.isFullyGrounded());
		result.setVariableValue("x", 2);
		System.out.println(result.isFullyGrounded());
		result.setVariableValue("y", 3);
		System.out.println(result.isFullyGrounded());
		System.out.println("x=2, y=3, result: "+result.evaluate());
		result.setVariableValue("x", 5);
		result.setVariableValue("y", 25);
		System.out.println("x=5, y=25, result: "+result.evaluate()+"\n");
		
		result=FormulaElement.parseFormula("x^2+x+3");
		System.out.println(result);
		System.out.println(result.isFullyGrounded());
		result.setVariableValue("x", 2);
		System.out.println(result.isFullyGrounded());
		System.out.println("x=2, result: "+result.evaluate());
		result.setVariableValue("x", 10);
		System.out.println("x=10, result: "+result.evaluate()+"\n");
		
		result=FormulaElement.parseFormula("x^(3x+4)+5x+1");
		System.out.println(result);
<<<<<<< HEAD
		System.out.println(result.isFullyGrounded());
		result.setVariableValue("x", 3);
		System.out.println(result.isFullyGrounded());
		System.out.println("x=3, result: "+result.evaluate());
		result.setVariableValue("x", 2);
		System.out.println("x=2, result: "+result.evaluate()+"\n");
		
		result=FormulaElement.parseFormula("cos(x)+(z-y)/y");
=======
>>>>>>> 6608bc01beeeadc20e43f7e2fbf8f3351947e686
	//	System.out.println(result2);
		//System.out.println(ReadWriteFormulae.ReadFormulae());
		//ReadWriteFormulae.WriteFormulae();
<<<<<<< HEAD
		//System.out.println(result.getSimplifiedCopy()+"\n\n\n");
//		result=FormulaElement.parseFormula("(x+2)+(x-3)(x+4)/(x+4)");
//		System.out.println(result);
//		System.out.println(result.getSimplifiedCopy());
=======
		System.out.println(result.getSimplifiedCopy()+"\n\n\n");
		result=FormulaElement.parseFormula("(x+2)+(x-3)(x+4)/(x+4)");
>>>>>>> FETCH_HEAD
		System.out.println(result);
		System.out.println(result.isFullyGrounded());
		result.setVariableValue("x", Math.toRadians(60));
		System.out.println(result.isFullyGrounded());
		result.setVariableValue("y", 2);
		System.out.println(result.isFullyGrounded());
		result.setVariableValue("z", 6);
		System.out.println(result.isFullyGrounded());
		System.out.println("x=60 degrees in radians, y=2, z=6, result: "+result.evaluate());
		result.setVariableValue("x", Math.toRadians(90));
		result.setVariableValue("y", 3);
		result.setVariableValue("z", 18);
		System.out.println("x=90 degrees in radians, y=3, z=18, result: "+result.evaluate());		
>>>>>>> 6608bc01beeeadc20e43f7e2fbf8f3351947e686
	}
}
