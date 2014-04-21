package formulator;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

public class EvalFormula {
	private FormulaElement formula;
	private HashMap<String, Double> variables = new HashMap<String, Double>();
	private String name;
	
	public EvalFormula(String n){
		this.name = n;
		this.formula = Main.formulas.get(name);
		
		//identify the variables; either the formula is just a single variable or it is a function element
		if(formula instanceof VariableElement){
			VariableElement var = ((VariableElement) formula);
			variables.put(var.getName(), var.getValue());
		}
		else
			identifyVars((FunctionElement) formula);
	}

	public double evaluateFor(String input){
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

		//evaluating a function with a SINGLE VARIABLE - only one value to assign
		if(variables.size()==1){
			//identify the key of the only variable
			String key = variables.keySet().iterator().next();
			//with a nested formula in the brackets
			if(tokens.size()>2){
				String recInput=toParse.substring(0, 4);
				EvalFormula E = new EvalFormula(recInput.substring(0, 1));
				double varValue = E.evaluateFor(recInput);
				variables.put(key, varValue);
			}
			else
			//with just a single value in the brackets
				variables.put(key, Double.parseDouble(tokens.elementAt(0)));
		}
		
		//evaluating a function with MULTIPLE VARIABLES
		else{
			//used to indicate that the next token will be a variable value
			boolean next=false;
			//to indicate what variable the next value belongs to; necessary as variables may be out of order
			String varKey="";
			for(int i=0; i<tokens.size(); i++){
				String token=tokens.get(i);
				if(token.equals("="))
					next=true;
				//assign a value to the current variable
				else if(next){
					//recursion
					if(isVariable(token)){
						String recInput = token;
						recInput+=tokens.remove(i+1);
						recInput+=tokens.remove(i+1);
						recInput+=tokens.remove(i+1);
						EvalFormula E = new EvalFormula(recInput.substring(0, 1));
						double varValue = E.evaluateFor(recInput);
						variables.put(varKey, varValue);
					}
					else
						variables.put(varKey, Double.parseDouble(tokens.elementAt(i)));
					next=false;
				}
				//if token is a variable, identify which one so the correct value can be assigned
				else if(isVariable(token))
					varKey = token;
				else if(tokens.equals(")"))
					break;
			}
		}
		
		//testing that variables were assigned the correct values
		for(String key: variables.keySet()){
			System.out.print(key+": ");
			System.out.println(variables.get(key));
		}
		
		//assign respective values to all variables in the formula using the created variables vector
		for(String key: variables.keySet()){
			formula.setVariableValue(key, variables.get(key));
		}
		return formula.evaluate();
	}
	
	public void identifyVars(FunctionElement element){
		for(FormulaElement elem: element.getArguments()){
			if(elem instanceof VariableElement){
				VariableElement var = (VariableElement) elem;
				if(!variables.containsKey(var.getName()))
					variables.put(var.getName(), var.getValue());
			}
			else if(elem instanceof FunctionElement)
				identifyVars((FunctionElement)elem);
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
