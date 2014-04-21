package formulator;

public class VariableElement extends FormulaElement {
	private String name;
	private double value;
	boolean valueAssigned;

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

	@Override
	public FormulaElement getSimplifiedCopy() {
		return this;
	}
	
}
