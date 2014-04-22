package formulator;

import java.util.StringTokenizer;
import java.util.Vector;

public class EvalFunction {
	private FormulaElement formula;
	private Vector<VariableElement> variables = new Vector<VariableElement>();
	private String name;

	public double evaluateFor(String input){
		for(StoreFormula form: Main.formulas){
			if(form.name.equals(input.substring(0, 1))){
				this.name = form.name;
				this.formula = form.formula;
				this.variables = form.variables;
			}
		}

		//chop off first part of the string input so the parsing bit starts after the open bracket
		String toParse = input.substring(2);
		//create a string tokenizer to identify variables, equal signs, and variable values
		Vector<String> tokens = new Vector<String>();
		StringTokenizer tokenizer = new StringTokenizer(toParse, "(= ),", true);
		while(tokenizer.hasMoreTokens()){
			String currToken = tokenizer.nextToken();
			if(!currToken.equals(" "))
				tokens.add(currToken);
		}
		//evaluating a function with a single variable - only one value to assign - baby
		if(variables.size()==1){
			//with nested formula
			if(tokens.size()>2){
				String recInput=toParse.substring(0, 4);
				EvalFunction E = new EvalFunction();
				double varValue = E.evaluateFor(recInput);
				variables.elementAt(0).setValue(varValue);
			}
			else
				variables.elementAt(0).setValue(Integer.parseInt(tokens.elementAt(0)));
		}
		
		//evaluating a function with multiple variables
		else{
			//to indicate that the next token will be a variable value
			boolean next=false;
			//to indicate what variable the next value belongs to - necessary for variables being out of order
			int currentVarIndex = -1;
			for(int i=0; i<tokens.size(); i++){
				String token=tokens.elementAt(i);
				if(token.equals("="))
					next=true;
				else if(next){
					//assign a value to the current variable
					if(isVariable(token)){
						String recInput = "";
						recInput+=token;
						recInput+=tokens.remove(i+1);
						recInput+=tokens.remove(i+1);
						recInput+=tokens.remove(i+1);
						EvalFunction E = new EvalFunction();
						double varValue = E.evaluateFor(recInput);
						variables.elementAt(currentVarIndex).setValue(varValue);
					}
					else
						variables.elementAt(currentVarIndex).setValue(Integer.parseInt(tokens.elementAt(i)));
					next=false;
					currentVarIndex=-1;
				}
				else if(isVariable(token)){
					//if token is a variable, identify which one so the correct value can be assigned
					for(int j=0; j<variables.size(); j++){
						if(token.equals(variables.elementAt(j).getName()))
							currentVarIndex=j;
					}
				}
				else if(tokens.equals(")"))
					break;
			}
		}
		//System.out.println("vars:"+variables.toString());
		return evaluate();
	}
	
	public double evaluate(){
		//put everything into a tempVars vector for use with the next evaluation step
		Vector<VariableElement> tempVars = new Vector<VariableElement>();
		for(int i=0; i<variables.size(); i++){
			tempVars.add(i, variables.elementAt(i));
		}
		
		//testing that variables were assigned the correct values
		for(VariableElement v:variables){
			System.out.print(v.getName()+": ");
			System.out.println(v.getValue());
		}
		
		//recursively get children of the formulaElement object until variables are found
		VariableElement currentVar;
		while(!tempVars.isEmpty()){
			currentVar = tempVars.remove(0);
			findVar(currentVar, (FunctionElement)formula);
		}
		return formula.evaluate();
	}
	
	//method that is called recursively to find all variables in function elements
	public void findVar(VariableElement x, FunctionElement element){
		for(FormulaElement elem: element.getArguments()){
			if(elem instanceof VariableElement){
				if(((VariableElement) elem).getName().equals(x.getName())){
					((VariableElement) elem).setValue(x.getValue());
				}
			}
			else if(elem instanceof FunctionElement){
				findVar(x, (FunctionElement)elem);
			}
		}
	}
	
	public static boolean isVariable(String s){
		if(s.equals("cos") || s.equals("sin"))
			return false;
		char[] chars = s.toCharArray();
		for(char c: chars){
			if(!Character.isLetter(c))
				return false;
		}
		return true;
	}

}
