package formulator;

public class ConstantElement extends FormulaElement {
	private double value;

	public ConstantElement(double val){
		value = val;
	}
	
	public double getValue(){
		return value;
	}
	
	public String toString(){
		if(value%1==0)
			return ""+((int)value);
		return ""+value;
	}

	public double evaluate() {
		return value;
	}

	@Override
	public FormulaElement dEval() {
		return this;
	}
}
