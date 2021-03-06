package formulator;

public class DivideFunctionElement extends FunctionElement{
	
	//constructor that allows two arguments to be added immediately
	public DivideFunctionElement(FormulaElement arg1, FormulaElement arg2){
		addArgument(arg1);
		addArgument(arg2);
	}
	
	//empty constructor; arguments can be added manually
	public DivideFunctionElement(){
	}
	
	//override addArgument method so that you can't add more than 2 arguments
	public void addArgument(FormulaElement arg){
		if(arguments.size()==2){
			System.out.println("The divide function can't have more than 2 arguments");
		}
		else
			arguments.add(arg);
	}

	public String toString(){
		FormulaElement arg1 = getArguments().elementAt(0);
		FormulaElement arg2 = getArguments().elementAt(1);
		//check if both arguments are constants; in which case division is performed to yield 1 value
		if(arg1 instanceof ConstantElement && arg2 instanceof ConstantElement){
			double retNum=((ConstantElement) arg1).getValue() / ((ConstantElement) arg2).getValue();
			return ""+retNum;
		}
		else{
			String argStr1="", argStr2="";
		
			//checking if parentheses are needed around first argument
			if(arg1 instanceof PlusFunctionElement || arg1 instanceof MinusFunctionElement)
				argStr1 = "("+arg1.toString()+")";
			else
				argStr1 = arg1.toString();
			
			//check if parentheses are needed around second argument
			if(arg2 instanceof PlusFunctionElement || arg2 instanceof MinusFunctionElement)
				argStr2 = "("+arg2.toString()+")";
			else
				argStr2 = arg2.toString();
			
			return argStr1+"/"+argStr2;
		}
	}
	
	public double evaluate(){
		FormulaElement arg1 = getArguments().elementAt(0);
		FormulaElement arg2 = getArguments().elementAt(1);
		return arg1.evaluate()/arg2.evaluate();
	}

	@Override
	public FormulaElement dEval() {
		FormulaElement arg1 = getArguments().elementAt(0);
		FormulaElement arg2 = getArguments().elementAt(1);
		return new DivideFunctionElement(arg1.dEval(), arg2.dEval());
	}
	

}
