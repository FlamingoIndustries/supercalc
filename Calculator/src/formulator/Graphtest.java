package formulator;

import javax.swing.SwingUtilities;

public class Graphtest {
	/*Current bug list:
	 Extra Origin point being drawn.
	 Conversion from double to integer is leaving point offsets a little weird.
	 NO MINUS POINTS YET
	 Need check to stop drawing if a point exceeds the limit of the graph.
	 Allow multiple graphs.
	*/
	public static void main(String[] args) {
		  SwingUtilities.invokeLater(new Runnable() {
		   
		   @Override
		   public void run() {
			   String str = "x^2/3";
				//String str = "(z^2) ";
				//String str = "(x+2)(x-(y^7)+cos(2^x))";
				FormulaElement result = (FormulaElement.parseFormula(str));
				//Pass in the root node of the formula to the CartesianFrame constructor
				CartesianFrame frame = new CartesianFrame(result);
				frame.showUI();
		   }
		  });
		 }
	
	
}
