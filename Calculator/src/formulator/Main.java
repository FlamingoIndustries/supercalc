package formulator;
import java.util.HashMap;
import java.util.Vector;

public class Main {
	public static HashMap<String, FormulaElement> formulas = new HashMap<String, FormulaElement>();
	
	public static void main(String[] args){
		//Sample input Strings to try:
		//"x^y^z+4"
		//"2X + Y/(X+5.5)^(2+n)"
		//"Y^3-6X(Z+5(Y+2^2))"
		//"3 + 4.6 + cos(1)"
		
		//testing PARSEFORMULA
		String sampleInput1= "-1+3";
		String sampleInput2 = "(2.3 + X + 4.5 + 3X)(2X - (Y^3 + 7) + cos(2^X))";
		FormulaElement test = FormulaElement.parseFormula(sampleInput2);
		if(test!=null)
			System.out.println("Parsed formula: "+test.toString());
		else
			System.out.println("String wasn't parsed correctly");
	
		//testing BASIC EVALUATION
		FormulaElement evalEx = FormulaElement.parseFormula("2x+2+x^2-y");
		int x_val=2;
		int y_val=3;
		evalEx.setVariableValue("x", x_val);
		evalEx.setVariableValue("y", y_val);
		System.out.println("Evaluating: "+evalEx.toString());
		System.out.println("Fully grounded: "+evalEx.isFullyGrounded());
		if(evalEx.isFullyGrounded())
			System.out.println("Evaluation: "+evalEx.evaluate());
		else
			System.out.println("Formula can't be evaluated because it isn't fully grounded.");
		
		//testing SIMPLIFICATION
		//FormulaElement simTest = FormulaElement.parseFormula("(x+3)(x+4)");
		//System.out.println(simTest.getSimplifiedCopy());
		
		//testing ADVANCED EVALUATION
		FormulaElement F = FormulaElement.parseFormula("y(2x)");
		formulas.put("f", F);
		FormulaElement G = FormulaElement.parseFormula("x+3");
		formulas.put("g", G);
		String input = "f(x=g(2), y=2)";
		EvalFormula eval = new EvalFormula(input.substring(0, 1));
		System.out.println("Evaluation: "+eval.evaluateFor(input));
		
	}

}
