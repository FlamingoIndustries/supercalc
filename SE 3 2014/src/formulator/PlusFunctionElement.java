/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

class PlusFunctionElement extends FunctionElement
{
	/**
	 * The function can be constructed with no arguments to be added later
	 */
	public PlusFunctionElement()
	{
		this.precedence=2;
	}
	
	/**
	 * Plus being a binary operator, can also be constructed with the standard 2 arguments
	 * @param x is an argument which the Divide function is initialized with.
	 * @param y is an argument which the Divide function is initialized with.
	 */
	public PlusFunctionElement(FormulaElement x, FormulaElement y)
	{
		this();
		this.addNewArgument(x);
		this.addNewArgument(y);
	}
	
	/**
	 * addNewArgument is overidden here from the superclass to add the elements of x to the 
	 * plus function if the argument is a multiple function
	 * @param x is an argument which is added to the plus function
	 */
	@Override
	public void addNewArgument(FormulaElement x)
	{
		if(x instanceof PlusFunctionElement)
			for(FormulaElement i: ((PlusFunctionElement) x).getArguments())
				this.addNewArgument(i);
		else
			super.addNewArgument(x);
	}
	
	@Override
	public double evaluate() throws Exception
	{
		Vector<FormulaElement> temp=this.getArguments();
		double total=0;
		for(FormulaElement x: temp)
		{
			total+=x.evaluate();
		}
		return total;
	}
	
	/**
	 * @return The string representation of the plus function in the form: argument+argument, 
	 * where:
	 * 1. An argument with lower precedence than plus is surrounded with brackets.
	 * 2. Equal arguments are counted and represented as countargument
	 * 3. Constants are added up and placed last
	 * 4. Variables are placed before constants.
	 */
	@Override
	public String toString()
	{
		String output="";
		double constant_total=0;
		Boolean first=true;
		HashMap<FormulaElement, Integer> vars=new HashMap<FormulaElement, Integer>();	//This hashmap will hold the counts of individual variables 
		Vector<FormulaElement> temp=this.getArguments();
		for(int i=0;i<temp.size();i++)
		{
			if(temp.elementAt(i) instanceof ConstantElement)	//Totaling constant elements
				constant_total+=((ConstantElement)temp.elementAt(i)).evaluate();
			else if(temp.elementAt(i) instanceof VariableElement)
			{
				Boolean exists=false;
				for(FormulaElement x:vars.keySet())
				{
					if(temp.elementAt(i).toString().equals(x.toString()))		//if the hashmap already contains the variable, the count is incremented 
					{
						vars.put(x, vars.get(x)+1);
						exists=true;
					}
					
				}
				if(!exists)
					vars.put(temp.elementAt(i), 1);
			}
			else
			{	
				if(first)
					first=false;
				else if(temp.elementAt(i).toString().charAt(0)!='-')
					output+="+";
				if(temp.elementAt(i).precedence<this.precedence&&temp.elementAt(i).precedence!=0)
					output+=("("+temp.elementAt(i).toString()+")");
				else
					output+=temp.elementAt(i).toString();
			}
		}
		for(Entry<FormulaElement, Integer> x: vars.entrySet())	//Add variables to output 
		{
			if(first)
				first=false;
			else
				output+="+";
			if(x.getValue()!=1)		//If there is more than one of the same variable, output the variable with the count before it
				output+=x.getValue();
			output+=x.getKey();
		}
		if(constant_total!=0)		//If there is a constant total, add it to the output last
		{
			if(first)
				first=false;
			else
				output+="+";
			if(constant_total!=0)
				if(constant_total%1!=0)
					output+=constant_total;
				else
					output+=((int)constant_total);
		}
		return output;
	}
	
	@Override
	public FormulaElement getSimplifiedCopy()
	{
		PlusFunctionElement out=new PlusFunctionElement();
		double consttotal=0;
		Vector<FormulaElement> pluselem=this.getArguments();
		HashMap<String, Vector<FormulaElement>> vars=new HashMap<String, Vector<FormulaElement>>();
		for(int x=0;x<pluselem.size();x++)
		{
			FormulaElement p=pluselem.remove(x);
			pluselem.add(x,p.getSimplifiedCopy());
		}
		for(int x=0;x<pluselem.size();)
		{
			if(pluselem.elementAt(x) instanceof ConstantElement)
				consttotal+=((ConstantElement) pluselem.remove(x)).getValue();
			else if(pluselem.elementAt(x) instanceof VariableElement)
				vars=this.addToHashMap(pluselem.remove(x), new ConstantElement(1), vars);
			else if(pluselem.elementAt(x) instanceof PlusFunctionElement)
				pluselem.addAll(((PlusFunctionElement)pluselem.remove(x)).getArguments());
			else if(pluselem.elementAt(x) instanceof MinusFunctionElement)
			{
				MinusFunctionElement minus=(MinusFunctionElement)pluselem.remove(x);
				Vector<FormulaElement> minelem=minus.getArguments();
				pluselem.add(minelem.elementAt(0));
				FormulaElement minuspart=minelem.lastElement();
				MultipleFunctionElement r=new MultipleFunctionElement(new ConstantElement(-1), minuspart);
				pluselem.add(r);
			}
			else if(pluselem.elementAt(x) instanceof MultipleFunctionElement)
				vars=this.simplifyMultipleFunctionElement(vars, (MultipleFunctionElement)pluselem.remove(x));
			else if(pluselem.elementAt(x) instanceof PowerFunctionElement)
				vars=this.addToHashMap((PowerFunctionElement) pluselem.remove(x), new ConstantElement(1), vars);
			else
				x++;
		}
		for(Entry<String,Vector<FormulaElement>> ent:vars.entrySet())
		{
			Vector<FormulaElement> var=ent.getValue();
			MultipleFunctionElement mult=new MultipleFunctionElement();
			FormulaElement varcount=var.lastElement();
			if (varcount instanceof ConstantElement&&((ConstantElement)varcount).getValue()==1)
				pluselem.add((FormulaElement)var.firstElement());
			else
			{
				mult.addNewArgument(varcount);
				mult.addNewArgument((FormulaElement)var.firstElement());
				pluselem.add(mult);
			}
		}
		if(consttotal!=0)
		{
			ConstantElement constelem=new ConstantElement(consttotal);
			if(pluselem.isEmpty())
				return constelem;
			pluselem.add(constelem);
		}
		if(pluselem.size()>1)
			for(FormulaElement e:pluselem)
				out.addNewArgument(e);
		else
			return pluselem.firstElement();
		return out;
	}
		
	private HashMap<String,Vector<FormulaElement>> simplifyMultipleFunctionElement(HashMap<String, Vector<FormulaElement>> vars, MultipleFunctionElement mult)
	{
		Vector<FormulaElement> v=mult.getArguments();
		double constprod=1;
		Vector<String> variables=new Vector<String>(); 
		FormulaElement check=mult;
		for(int i=0;i<v.size();i++)
		{
			if(v.elementAt(i) instanceof ConstantElement)
			{
				constprod*=((ConstantElement) v.remove(i)).getValue();
				i--;
			}
			else if(v.elementAt(i) instanceof VariableElement||v.elementAt(i) instanceof FunctionElement)
				variables.add(v.elementAt(i).toString());
		}
		if(v.size()>1)
		{
			check=new MultipleFunctionElement();
			for(FormulaElement elem:v)
				((MultipleFunctionElement) check).addNewArgument(elem);
		}
		else
			check=v.firstElement();
		vars=this.addToHashMap(check, new ConstantElement(constprod), vars);
		return vars;
	}
}
