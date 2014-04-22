package formulator;
import java.util.Vector;

//shouldn't catch the minus

public class Main {
	public static Vector<StoreFormula> formulas = new Vector<StoreFormula>();
	
	public static void main(String[] args){
		//Sample input Strings to try:
		//"x^y^z+4"
		//"2X + Y/(X+5.5)^(2+n)"
		//"Y^3-6X(Z+5(Y+2^2))"
		//"3 + 4.6 + cos(1)"
		
//		String shaneSampleInput = ("--1");
//		FormulaElement testShane = FormulaElement.parseFormula(shaneSampleInput);
//		if(testShane!=null)
//			System.out.println("Parsed formula: "+testShane.toString());
//		else
//			System.out.println("String wasn't parsed correctly");
		
		String sampleInput = "(2.3 + X + 4.5 + 3X)(2X - (Y^3 + 7) + cos(2^X))";
		FormulaElement test = FormulaElement.parseFormula(sampleInput);
		if(test!=null)
			System.out.println("Parsed formula: "+test.toString());
		else
			System.out.println("String wasn't parsed correctly");
	
		FormulaElement evalEx = FormulaElement.parseFormula("x+2+x^2-y");
		int x_val=2;
		int y_val=3;
		evalEx.setVariableValue("x", x_val);
		evalEx.setVariableValue("y", y_val);
		System.out.println("Evaluating: "+evalEx.toString()+" with "+"x="+x_val+" and y="+y_val);
		System.out.println("Fully grounded: "+evalEx.isFullyGrounded());
		System.out.println("Evaluation: "+evalEx.evaluate());
		
		StoreFormula S1 = new StoreFormula("f(x, y)=x+3+y");
		StoreFormula S2 = new StoreFormula("g(x)=x/2");
		formulas.add(S1);
		formulas.add(S2);
		EvalFunction E1 = new EvalFunction();
		System.out.println("result: "+E1.evaluateFor("f(x=g(4), y=2)"));
		
		
		
		
	}

}
