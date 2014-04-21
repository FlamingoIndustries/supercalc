package formulator;

/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map.Entry;

abstract class FunctionElement extends FormulaElement
{
	private Vector<FormulaElement> arguments=new Vector<FormulaElement>();		//A private vector is used to house an arbitrary number of arguments
	
	/**
	 * The user can only manipulate the vector by adding arguments to the vector
	 * @param x is a FormulaElement which is added to the list of arguments for this function
	 */
	public void addNewArgument(FormulaElement x)
	{
			arguments.add(x);								
	}
	
	/**
	 * 
	 * @return a vector of arguments for this function
	 */
	public Vector<FormulaElement> getArguments()
	{
		return arguments;								 
	}
	
	/**
	 * Iterates through the arguments of the function element and passes the 
	 * assignment of the variable value to these arguments.
	 * @param varName The name of the variable to assign
	 * @param value value to assign to the variable
	 */
	@Override
	public void setVariableValue(String varName, double value)
	{
		for(FormulaElement f:arguments)
			f.setVariableValue(varName, value);
	}
	
	@Override
	public void replaceVariable(String varName, FormulaElement replace)
	{
		for(int i=0;i<arguments.size();i++)
		{
			FormulaElement e=arguments.elementAt(i);
			if(e instanceof VariableElement&& ((VariableElement)e).getName().equals(varName))
			{
				arguments.remove(i);
				arguments.add(i, replace);
				System.out.println("replaced");
			}
			else if(e instanceof FunctionElement)
			{
				e.replaceVariable("x", replace);
			}
		}
	}
	
	@Override
	public VariableElement findVariable(String varName)
	{
		for(int i=0;i<arguments.size();i++)
		{
			FormulaElement e=arguments.elementAt(i);
			if(e instanceof VariableElement&& ((VariableElement)e).getName().equals(varName))
				return (VariableElement)e;
			else if(e instanceof FunctionElement)
			{
				VariableElement f=e.findVariable(varName);
				if(f!=null)
					return f;
			}
		}
		return null;
	}
	
	/**
	 * @return a boolean value if the variables in the arguments are assigned
	 */
	@Override
	public Boolean isFullyGrounded()
	{
		for(FormulaElement f:arguments)
			if(f instanceof VariableElement || f instanceof FunctionElement)
				if(!f.isFullyGrounded())
					return false;
		return true;
	}
	
	@Override
	public String getXMLformat(String tabbing)
	{
		String newline=System.lineSeparator()+tabbing;
		String funcName=this.getClass().getSimpleName();
		String out="<"+funcName+">";
		for(FormulaElement e: arguments)
		{
			out+=newline+"\t"+e.getXMLformat(tabbing+"\t");
		}
		out+=newline+"</"+funcName+">";
		return out;
	}
	
	public Boolean equals(FormulaElement comp)
	{
		if(this.getClass().getSimpleName().equals(comp.getClass().getSimpleName()))
		{
			FunctionElement comp1=(FunctionElement) comp;
			Vector<FormulaElement> thiselements=new Vector<FormulaElement>();
			Vector<FormulaElement> compelements=new Vector<FormulaElement>();
			for(FormulaElement e:arguments)
				thiselements.add(e);
			for(FormulaElement e:comp1.getArguments())
				compelements.add(e);
			for(int i=0;i<thiselements.size();i++)
			{
				for(int j=0;j<compelements.size();j++)
				{
					if(thiselements.elementAt(i).equals(compelements.elementAt(j)))
					{
						thiselements.remove(i);
						compelements.remove(j);
						i--;
						break;
					}
				}
			}
			if(thiselements.isEmpty()&&compelements.isEmpty())
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	protected HashMap<String,Vector<FormulaElement>> addToHashMap(FormulaElement elem, FormulaElement count, HashMap<String,Vector<FormulaElement>> vars)
	{
		Boolean match=false;
		for(Entry<String, Vector<FormulaElement>> ent:vars.entrySet())
		{
			FormulaElement form=(FormulaElement) ent.getValue().firstElement();
			match= elem.equals(form);
			if(match)
			{
				Vector<FormulaElement> n=vars.get(ent.getKey());
				FormulaElement plus=new PlusFunctionElement(count,n.remove(1));
				try
				{
					plus=new ConstantElement(plus.evaluate());
				}
				catch(Exception e)
				{}
				if(plus instanceof ConstantElement&& ((ConstantElement)plus).getValue()==0)
					vars.remove(ent.getKey());
				else
				{
					n.add(plus);
					vars.put(ent.getKey(), n);
				}
				break;
			}
		}
		if(!match)
		{
			Vector<FormulaElement> q=new Vector<FormulaElement>();
			q.add(elem);
			q.add(count);
			vars.put(elem.toString(), q);
		}
		return vars;
	}
}
