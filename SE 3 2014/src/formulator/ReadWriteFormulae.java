package formulator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class ReadWriteFormulae
{
	public static Boolean WriteFormulae()
	{
		Display display = new Display();
	    final Shell shell = new Shell(display);


	    FileDialog dlg = new FileDialog(shell, SWT.SAVE);
	    String[] extensions={"*.xml", "*.txt"};
	    dlg.setFilterExtensions(extensions);
	    String fileName = dlg.open();
	    if (fileName != null) {
	    	PrintWriter writer;
			try
			{
				writer = new PrintWriter(fileName, "UTF-8");
			} catch (FileNotFoundException e)
			{
				return false;
			} catch (UnsupportedEncodingException e)
			{
				return false;
			}
	    	HashMap<String, FormulaElement> formulae=new HashMap<String, FormulaElement>();
	    	formulae.put("form1", new PlusFunctionElement(new ConstantElement(3), new VariableElement("x")));
	    	for(Entry<String, FormulaElement> form:formulae.entrySet())
	    	{
	    		FormulaElement formula=form.getValue();
	    		String formXML="<"+form.getKey()+">"+System.lineSeparator()+"\t";
	    		formXML+=formula.getXMLformat("\t")+System.lineSeparator();
	    		formXML+="</"+form.getKey()+">";
	    		writer.print(formXML);
	    	}
	    	writer.close();
	    }


	    
	    display.dispose();
	    return true;
	 }
}
