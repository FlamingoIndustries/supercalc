package formulator;

public class MinusFunctionElement extends FunctionElement{
	
	//constructor that allows two arguments to be added immediately
	public MinusFunctionElement(FormulaElement arg1, FormulaElement arg2){
		addArgument(arg1);
		addArgument(arg2);
	}
	
	//empty constructor; arguments can be added manually
	public MinusFunctionElement(){
	}
	
	//override addArgument method so that you can't add more than 2 arguments
	public void addArguments(FormulaElement arg){
		if(arguments.size()==2)
			System.out.println("The minus function can't have more than 2 arguments");
		else
			arguments.add(arg);
	}
	
	public String toString(){
		FormulaElement arg1 = getArguments().elementAt(0);
		FormulaElement arg2 = getArguments().elementAt(1);
		//generate result if both arguments are constants
		if(arg1 instanceof ConstantElement && arg2 instanceof ConstantElement){
			double retNum = ((ConstantElement) arg1).getValue() - ((ConstantElement) arg2).getValue();
			if(retNum%1==0)
				return ""+(int) retNum;
			return ""+retNum;
		}
		//create string with minus symbol if 1+ arguments are variables
		else
			return arg1.toString() + " - " + arg2.toString();
	}
	
	public double evaluate(){
		FormulaElement arg1 = getArguments().elementAt(0);
		FormulaElement arg2 = getArguments().elementAt(1);
		return arg1.evaluate()-arg2.evaluate();
	}

	
	public FormulaElement getSimplifiedCopy() {
		MinusFunctionElement simple = new MinusFunctionElement();
		
		//simplify all the current arguments
		for(FormulaElement elem: getArguments()){
			simple.addArgument(elem.getSimplifiedCopy());
		}
		
		//subtract all the arguments that are constant values
		double totalConstants=0;
		for(int i=0; i<simple.getArguments().size(); i++){
			FormulaElement arg = simple.getArguments().get(i);
			if(arg instanceof ConstantElement){
				totalConstants-=((ConstantElement) arg).getValue();
				simple.getArguments().remove(i);
				i--;
			}
		}
		simple.addArgument(new ConstantElement(totalConstants));
		
		//take all the arguments of the plus function arguments
		for(int i=0; i<simple.getArguments().size(); i++){
			FormulaElement arg = simple.getArguments().get(i);
			if(arg instanceof PlusFunctionElement){
				simple.addArgument(((FunctionElement)arg).arguments.get(0).getSimplifiedCopy());
				FormulaElement sub_arg = ((FunctionElement)arg).getArguments().get(1);
				simple.addArgument(sub_arg);
				simple.arguments.remove(arg);
			}
		}
		//add up all the arguments that are constant values
		totalConstants=0;
		for(int i=0; i<simple.getArguments().size(); i++){
			FormulaElement arg = simple.getArguments().get(i);
			if(arg instanceof ConstantElement){
				totalConstants-=((ConstantElement) arg).getValue();
				simple.arguments.remove(i);
				i--;
			}
		}
		simple.arguments.add(new ConstantElement(totalConstants));
		
		return simple;
	}

}
