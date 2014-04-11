package formulator;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		FormulaElement result;
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
		System.out.println(result.isFullyGrounded());
		result.setVariableValue("x", 3);
		System.out.println(result.isFullyGrounded());
		System.out.println("x=3, result: "+result.evaluate());
		result.setVariableValue("x", 2);
		System.out.println("x=2, result: "+result.evaluate()+"\n");
		
		result=FormulaElement.parseFormula("cos(x)+(z-y)/y");
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
	}
}
