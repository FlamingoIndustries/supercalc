package formulator;

import java.util.Vector;

public class PlusFunctionElement extends FunctionElement {
	
	//constructor that allows two arguments to be added immediately
	public PlusFunctionElement(FormulaElement arg1, FormulaElement arg2){
		addArgument(arg1);
		addArgument(arg2);
	}
	
	public void addArgument(FormulaElement arg){
		//when adding another plus function element, add its element instead of the object as a whole
		if(arg instanceof PlusFunctionElement){
			for(FormulaElement sub_arg: ((PlusFunctionElement) arg).getArguments()){
				arguments.add(sub_arg);
			}
		}
		else
			arguments.add(arg);
	}
	
	//empty constructor; arguments can be added manually
	public PlusFunctionElement(){
	}

	public String toString(){
		Vector<FormulaElement> arguments = getArguments();
		//for storing constants
		double retNum=0;
		//for storing variables
		String retString="";
		FormulaElement arg;
		//iterate through all elements in arguments vector
		for(int i=0; i<arguments.size(); i++){
			arg = arguments.get(i);
			//add constants together
			if(arg instanceof ConstantElement){
				retNum+=((ConstantElement) arg).getValue();
			}
			//add variables together with plus symbol
			else{
				retString+=arg.toString()+" + ";
			}
		}
		//assemble return string, putting constants and variables together
		//return an int if the double constant is actually an int
		if(retNum!=0 && retNum%1==0)
			return retString+(int)retNum;
		else if(retNum!=0)
			return retString+retNum;
		else
			return retString.substring(0, retString.length()-3);

	}
	
	public double evaluate(){
		Vector<FormulaElement> arguments = getArguments();
		//for storing constants
		double retNum=0;
		FormulaElement arg;
		//iterate through all elements in arguments vector
		for(int i=0; i<arguments.size(); i++){
			arg = arguments.get(i);
			retNum+=arg.evaluate();
		}
		return retNum;
	}

	@Override
	public FormulaElement getSimplifiedCopy() {
		PlusFunctionElement simple = new PlusFunctionElement();
		
		//simplify all the current arguments
		for(FormulaElement elem: getArguments()){
			simple.addArgument(elem);
		}
		//add up all the arguments that are constant values
		double totalConstants=0;
		for(int i=0; i<simple.getArguments().size(); i++){
			FormulaElement arg = simple.getArguments().get(i);
			if(arg instanceof ConstantElement){
				totalConstants+=((ConstantElement) arg).getValue();
				simple.getArguments().remove(i);
				i--;
			}
		}
		if(totalConstants!=0)
			simple.addArgument(new ConstantElement(totalConstants));
		
		//take all the arguments of the minus function arguments
		for(int i=0; i<simple.getArguments().size(); i++){
			FormulaElement arg = simple.getArguments().get(i);
			if(arg instanceof MinusFunctionElement){
				
				simple.addArgument(((FunctionElement)arg).arguments.get(0).getSimplifiedCopy());
				FormulaElement sub_arg = ((FunctionElement)arg).getArguments().get(1).getSimplifiedCopy();
				ConstantElement con = new ConstantElement(-1);
				simple.addArgument(new MultipleFunctionElement(sub_arg, con).getSimplifiedCopy());
				simple.arguments.remove(arg);
			}
		}
		//add up all the arguments that are constant values
		totalConstants=0;
		for(int i=0; i<simple.getArguments().size(); i++){
			FormulaElement arg = simple.getArguments().get(i);
			if(arg instanceof ConstantElement){
				totalConstants+=((ConstantElement) arg).getValue();
				simple.arguments.remove(i);
				i--;
			}
		}
		if(totalConstants!=0)
			simple.addArgument(new ConstantElement(totalConstants));
		
		System.out.println(simple.getArguments());
		//check for like terms
		for(int i=0; i<simple.getArguments().size()-1; i++){
			FormulaElement arg1 = simple.getArguments().get(i);
			FormulaElement arg2 = simple.getArguments().get(i+1);
			Vector<VariableElement> vars1 = new Vector<VariableElement>();
			Vector<VariableElement> vars2 = new Vector<VariableElement>();
			Double cons1=1.0;
			Double cons2=1.0;
			Vector<PowerFunctionElement> pows1 = new Vector<PowerFunctionElement>();
			Vector<PowerFunctionElement> pows2 = new Vector<PowerFunctionElement>();
			if(arg1 instanceof MultipleFunctionElement && arg2 instanceof MultipleFunctionElement){
				for(FormulaElement elem: ((MultipleFunctionElement)arg1).getArguments()){
					if(elem instanceof VariableElement){
						vars1.add((VariableElement) elem);
					}
					else if(elem instanceof ConstantElement){
						cons1=((ConstantElement)elem).getValue();
					}
					else if(elem instanceof PowerFunctionElement){
						pows1.add((PowerFunctionElement) elem);
					}
				}
				for(FormulaElement elem: ((MultipleFunctionElement)arg2).getArguments()){
					if(elem instanceof VariableElement){
						vars2.add((VariableElement) elem);
					}
					else if(elem instanceof ConstantElement){
						cons2=((ConstantElement)elem).getValue();
					}
					else if(elem instanceof PowerFunctionElement){
						pows2.add((PowerFunctionElement) elem);
					}
				}
				
				//check that variables are the same
				if(vars1.containsAll(vars2)){
					System.out.println(cons2);
					if(pows1.containsAll(pows2) && pows2.containsAll(pows1)){
						MultipleFunctionElement newMult = new MultipleFunctionElement();
						newMult.addArgument(new ConstantElement(cons1*cons2));
						for(VariableElement V: vars1){
							newMult.addArgument(V);
						}
						for(PowerFunctionElement P: pows1){
							newMult.addArgument(P);
						}
						simple.getArguments().remove(i);
						simple.getArguments().remove(i);
						i--;
						simple.addArgument(newMult);
					}
				}
			}
		}
		
		return simple;
	}
	
}
















