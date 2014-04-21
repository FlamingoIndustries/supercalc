/*Name:Thomas Higgins
 *Sudent No.:11322981 
 */

package formulator;

import java.util.Vector;


class DivideFunctionElement extends FunctionElement
{	
	/**
	 * The function can be constructed with no arguments to be added later
	 */
	public DivideFunctionElement()
	{
		this.precedence=3;
	}
	
	/**
	 * Divide being a binary operator, can also be constructed with the standard 2 arguments
	 * @param x is an argument which the Divide function is initialized with.
	 * @param y is an argument which the Divide function is initialized with.
	 */
	public DivideFunctionElement(FormulaElement x, FormulaElement y)
	{
		this();
		this.addNewArgument(x);
		this.addNewArgument(y);
	}
	
	/**
	 * addNewArgument is overidden here to refuse to add an argument if there are already 2 
	 * arguments because division cannot take any more than 2 arguments
	 * @param x is a FormulaElement which is added to the list of arguments for this function
	 */
	@Override
	public void addNewArgument(FormulaElement x)
	{
		if(this.getArguments().size()<2)
			super.addNewArgument(x);
		else
			System.out.println("Unable to add argument, argument limit reached");
	}
	
	@Override
	public double evaluate() throws Exception
	{
		Vector<FormulaElement> temp=this.getArguments();
		return ((FormulaElement)temp.elementAt(0)).evaluate()/((FormulaElement)temp.elementAt(1)).evaluate();
	}
	
	/**
	 * @return The string representation of the divide function in the form: argument/argument, 
	 * where an argument with equal or lower precedence than division is surrounded with brackets
	 */
	@Override
	public String toString()
	{
		String output="";
		Vector<FormulaElement> temp=this.getArguments();
		if(temp.elementAt(0).equals(temp.elementAt(1)))
			output="1";
		else
			for(int i=0;i<2;i++)
			{
				if(temp.elementAt(i).precedence<=this.precedence&&temp.elementAt(i).precedence!=0)
					output+=("("+temp.elementAt(i).toString()+")");
				else
					output+=temp.elementAt(i).toString();
				if(i==0)
					output+="/";
			}
		return output;
	}
	
	@Override
	public FormulaElement getSimplifiedCopy()
	{
		Vector<FormulaElement> v=this.getArguments();
		FormulaElement div1=v.firstElement();
		FormulaElement div2=v.lastElement();
		System.out.println(div1+"   "+div2);
		if(div1.equals(div2))
			return new ConstantElement(1);
		if(div1 instanceof MultipleFunctionElement||div2 instanceof MultipleFunctionElement)
		{
			Vector<FormulaElement> var1=new Vector<FormulaElement>();
			Vector<FormulaElement> var2=new Vector<FormulaElement>();
			if(div1 instanceof MultipleFunctionElement)
				var1=((FunctionElement) div1).getArguments();
			else
				var1.add(div1);
			if(div2 instanceof MultipleFunctionElement)
				var2=((FunctionElement) div2).getArguments();
			else
				var2.add(div2);
			for(int i=0;i<var1.size();i++)
			{
				for(int j=0;j<var2.size();j++)
				{
					if(var1.elementAt(i).equals(var2.elementAt(j)))
					{
						var1.remove(i);
						var2.remove(j);
						i--;
						break;
					}
					else if(var1.elementAt(i) instanceof ConstantElement&&var2.elementAt(j) instanceof ConstantElement)
					{
						double nume=((ConstantElement)var1.remove(i)).getValue();
						double denom=((ConstantElement)var2.remove(j)).getValue();
						nume=nume/denom;
						var1.add(new ConstantElement(nume));
						i--;
						break;
					}
				}
			}
		}
		DivideFunctionElement div=new DivideFunctionElement();
		div.addNewArgument(div1);
		div.addNewArgument(div2);
		return div;
	}
	
	public Boolean equals(FormulaElement comp)
	{
		if(this.getClass().getSimpleName().equals(comp.getClass().getSimpleName()))
		{
			FunctionElement comp1=(FunctionElement) comp;
			Vector<FormulaElement> compelements=comp1.getArguments();
			if(this.getArguments().elementAt(0).equals(compelements.elementAt(0))&&this.getArguments().elementAt(1).equals(compelements.elementAt(1)))
				return true;
		}
		return false;
	}
}
