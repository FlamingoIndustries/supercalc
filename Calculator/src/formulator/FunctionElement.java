package formulator;

import java.util.Vector;

abstract class FunctionElement extends FormulaElement {
	protected Vector<FormulaElement> arguments = new Vector<FormulaElement>();
	
	//empty default constructor
	public FunctionElement(){
	}
	
	public void addArgument(FormulaElement arg){
		arguments.add(arg);
	}
	
	public Vector<FormulaElement> getArguments(){
		return arguments;
	}

}
