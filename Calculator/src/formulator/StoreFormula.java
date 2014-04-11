package formulator;

import java.util.Vector;

public class StoreFormula {
	Vector<VariableElement> variables = new Vector<VariableElement>();
	FormulaElement formula;
	String name;

	public StoreFormula(String input){
		//identify name of function
		name = input.substring(0, 1);
		
		//identify RHS of the function
		int index=0;
		for(int i=0; i<input.length(); i++){
			if(input.charAt(i)=='='){
				index = i+1;
				break;
			}
		}
		String stringFormula = input.substring(index);
		formula = formula.parseFormula(stringFormula);
		
		//identifying variables by looking at what is in the brackets f(...)
		boolean bracket=false;
		for(int i=0; i<input.length(); i++){
			if(input.charAt(i)=='(')
				bracket=true;
			else if(bracket && input.charAt(i)!=' ' && input.charAt(i)!=','){
				if(input.charAt(i)==')'){
					break;
				}
				//add all variables as VariableElements to a variables vector belonging to this class
				variables.add(new VariableElement(input.substring(i, i+1)));
			}
		}
	}
	
	public String getName(){
		return name;
	}
	
	//prints out the complete function
	public String toString(){
		String retString="";
		retString = getName()+"(";
		for(FormulaElement var: variables){
			retString+=var+" ";
		}
		//take out the last space
		retString=retString.substring(0, retString.length()-1);
		retString+=") = "+ formula.toString();
		return retString;
	}
	

}
