package formulator;

import java.util.Vector;

public class MultipleFunctionElement extends FunctionElement {
	
	//constructor that allows two arguments to be added immediately
	public MultipleFunctionElement(FormulaElement arg1, FormulaElement arg2){
		//multiply arguments together if they are both constants

		if(arg1 instanceof ConstantElement && arg2 instanceof MultipleFunctionElement){
			double temp=((ConstantElement)arg1).getValue();
			VariableElement other=null;
			for(FormulaElement elem: ((MultipleFunctionElement)arg2).getArguments()){
				if(elem instanceof ConstantElement){
					temp*=((ConstantElement)elem).getValue();
				}
				else if(elem instanceof VariableElement){
					other = new VariableElement(((VariableElement) elem).getName());
				}
			}
			addArgument(new ConstantElement(temp));
			addArgument(other);
		}
		else{
			addArgument(arg1);
			addArgument(arg2);
		}
	}
	
	//empty constructor; arguments can be added manually
	public MultipleFunctionElement(){	
	}
	
	public String toString(){
		Vector<FormulaElement> arguments = getArguments();
		double retNum=0;
		String retString="";
		FormulaElement arg;
		for(int i=0; i<arguments.size(); i++){
			arg=arguments.elementAt(i);
			//allows for a result to be calculate if 1+ argument is a constant
			if(arg instanceof ConstantElement){
				retNum+=((ConstantElement) arg).getValue();
			}
			//if an argument is a PlusFuntionElement or MinusFunctionElement, add parentheses around it
			else if(arg instanceof PlusFunctionElement || arg instanceof MinusFunctionElement){
				retString+="("+arg.toString()+")";
			}
			else{
				retString+=arg.toString();
			}
		}
		if(retNum!=0 && retNum%1==0)
			return (int)retNum+retString;
		else if(retNum!=0)
			return retNum+retString;
		else
			return retString;
	}
	
	public double evaluate(){
		Vector<FormulaElement> arguments = getArguments();
		double retNum=1;
		FormulaElement arg;
		for(int i=0; i<arguments.size(); i++){
			arg=arguments.elementAt(i);
			retNum = retNum * arg.evaluate();
		}
		return retNum;
	}

	@Override
	public FormulaElement getSimplifiedCopy() {
		MultipleFunctionElement simple = new MultipleFunctionElement();
		//simplify all the current arguments
		for(FormulaElement elem: getArguments()){
			simple.addArgument(elem.getSimplifiedCopy());
		}
		
		//DISTRIBUTION
		PlusFunctionElement temp = new PlusFunctionElement();
		for(int i=0; i<simple.getArguments().size()-1; i++){
			if(simple.getArguments().get(i) instanceof FunctionElement){
				FunctionElement F1 = (FunctionElement) simple.getArguments().remove(i); 
				
				//multiply function element by function element
				if(simple.getArguments().get(i) instanceof FunctionElement){
					FunctionElement F2 = (FunctionElement) simple.getArguments().remove(i);
					for(FormulaElement arg1: F1.getArguments()){
						for(FormulaElement arg2: F2.getArguments()){
							temp.addArgument(computeElement(arg1, arg2));
						}
					}
				}
				//multiplying function element by variable element
				else if(simple.getArguments().get(i) instanceof VariableElement){
					VariableElement V1 = (VariableElement) simple.getArguments().remove(i);
					for(FormulaElement arg1: F1.getArguments()){
						temp.addArgument(computeElement(arg1, V1));
					}
				}
				//multiplying function element by constant element
				else if(simple.getArguments().get(i) instanceof ConstantElement){
					ConstantElement C1 = (ConstantElement) simple.getArguments().remove(i);
					for(FormulaElement arg1: F1.getArguments()){
						temp.addArgument(computeElement(arg1, C1));
					}
				}
			}
			else if(simple.getArguments().get(i) instanceof VariableElement){
				//multiply variable element by function element
				if(simple.getArguments().get(i+1) instanceof FunctionElement){
					VariableElement V1 = (VariableElement) simple.getArguments().remove(i);
					FunctionElement F1 = (FunctionElement) simple.getArguments().remove(i);
					for(FormulaElement arg1: F1.getArguments()){
						temp.addArgument(computeElement(arg1, V1));
					}
				}
			}
			else if(simple.getArguments().get(i) instanceof ConstantElement){
				//multiply constant element by function element
				if(simple.getArguments().get(i+1) instanceof FunctionElement){
					ConstantElement C1 = (ConstantElement) simple.getArguments().remove(i);
					FunctionElement F1 = (FunctionElement) simple.getArguments().remove(i);
					for(FormulaElement arg1: F1.getArguments()){
						temp.addArgument(computeElement(arg1, C1));
					}
				}
			}
			if(!temp.getArguments().isEmpty())
				simple.addArgument(temp);
		}
		
		if(simple.getArguments().size()>1){
			return simple;
		}
		System.out.println(temp.getArguments());
		return temp.getSimplifiedCopy();
	}
	
	public FormulaElement computeElement(FormulaElement arg1, FormulaElement arg2){
		if(arg1 instanceof ConstantElement && arg2 instanceof ConstantElement){
			ConstantElement element = new ConstantElement(((ConstantElement)arg1).getValue()*((ConstantElement)arg2).getValue());
			return element;
		}
		if(arg1 instanceof VariableElement && arg2 instanceof VariableElement){
			if(((VariableElement)arg1).getName().equals( ((VariableElement)arg2).getName())){
				PowerFunctionElement element = new PowerFunctionElement(arg1, new ConstantElement(2));
				return element;
			}
		}
		return new MultipleFunctionElement(arg1, arg2);
	}
}
