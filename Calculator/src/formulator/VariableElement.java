package formulator;

public class VariableElement extends FormulaElement {
	private String name;
	private double value;
	boolean valueAssigned;
	private FormulaElement dVal;

	public VariableElement(String input){
		name = input;
		valueAssigned=false;
	}
	
	public String getName(){
		return name;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double x){
		value = x;
		valueAssigned=true;
	}
	
	public String toString(){
		return name;
	}

	@Override
	public double evaluate() {
		return value;
	}

	public void setdVal(FormulaElement value){
		dVal = value;
	}
	
	@Override
	public FormulaElement dEval() {
		return dVal;
	}
	
}
