package formulator;

import javax.swing.SwingUtilities;

public class graphControl {
	/*Current bug list:
	 !Extra Origin point being drawn.
	 Conversion from double to integer is leaving point offsets a little weird.
	 !NO MINUS POINTS YET 
	 !Need check to stop drawing if a point exceeds the limit of the graph.
	 Allow multiple graphs.
	 !Powers are broken
	 !Labels are intrusive in large numbers
	 !Keyboard shortcuts for labels, dots etc.
	 
	 SHORTCUTS:
	 1 - Toggle labels
	 2 - Toggle dots
	 3 - Toggle lines
	*/
	public static void main
	(String[] args) {
		  SwingUtilities.invokeLater(new Runnable() {
		   
		   @Override
		   public void run() {
			   	String str = "x(4/3)";
				//String str = "(x^(3/2)) ";
				//String str = "(x+2)(x-(y^7)+cos(2^x))";
				FormulaElement result = (FormulaElement.parseFormula(str));
				//Pass in the root node of the formula to the CartesianFrame constructor
				int min = -10;
				int max = 10;
				CartesianFrame frame = new CartesianFrame(result, min, max, true, true, true);
				frame.showUI();
		   }
		  });
		 }
}
