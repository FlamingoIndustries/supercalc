package formulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Cartesian {

}

class CartesianFrame extends JFrame {
	CartesianPanel panel;

	public CartesianFrame(FormulaElement input) {
		panel = new CartesianPanel(input);
		add(panel);
	}

	public void showUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cartesian");
		setSize(700, 700);
		setVisible(true);
	}
}

class CartesianPanel extends JPanel {
	FormulaElement node;

	public CartesianPanel(FormulaElement input) {
		node = input;
	}

	// x-axis coord constants
	public static final int X_AXIS_FIRST_X_COORD = 50;
	public static final int X_AXIS_SECOND_X_COORD = 600;
	public static final int X_AXIS_Y_COORD = 600;

	// y-axis coord constants
	public static final int Y_AXIS_FIRST_Y_COORD = 50;
	public static final int Y_AXIS_SECOND_Y_COORD = 600;
	public static final int Y_AXIS_X_COORD = 50;

	// arrows of axis are represented with "hypotenuse" of
	// triangle
	// now we are define length of cathetas of that triangle
	public static final int FIRST_LENGHT = 10;
	public static final int SECOND_LENGHT = 5;

	// size of start coordinate lenght
	public static final int ORIGIN_COORDINATE_LENGHT = 6;

	// distance of coordinate strings from axis
	public static final int AXIS_STRING_DISTANCE = 20;

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// x-axis
		g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD,
				X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
		// y-axis
		g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD, Y_AXIS_X_COORD,
				Y_AXIS_SECOND_Y_COORD);

		// x-axis arrow
		g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT, X_AXIS_Y_COORD
				- SECOND_LENGHT, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
		g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT, X_AXIS_Y_COORD
				+ SECOND_LENGHT, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);

		// y-axis arrow
		g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD
				+ FIRST_LENGHT, Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);
		g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD
				+ FIRST_LENGHT, Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);

		// draw origin Point
		g2.fillOval(X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
				Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
				ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);

		// draw text "X" and draw text "Y"
		g2.drawString("X", X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2,
				X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		g2.drawString("Y", Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
				Y_AXIS_FIRST_Y_COORD + AXIS_STRING_DISTANCE / 2);
		g2.drawString("(0, 0)", X_AXIS_FIRST_X_COORD - AXIS_STRING_DISTANCE,
				Y_AXIS_SECOND_Y_COORD + AXIS_STRING_DISTANCE);

		// numerate axis
		int xCoordNumbers = 20;
		int yCoordNumbers = 20;
		int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)
				/ xCoordNumbers;
		int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)
				/ yCoordNumbers;

		// draw x-axis numbers
		for (int i = 1; i < xCoordNumbers; i++) {
			g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength), X_AXIS_Y_COORD
					- SECOND_LENGHT, X_AXIS_FIRST_X_COORD + (i * xLength),
					X_AXIS_Y_COORD + SECOND_LENGHT);
			g2.drawString(Integer.toString(i), X_AXIS_FIRST_X_COORD
					+ (i * xLength) - 3, X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		}

		// draw y-axis numbers
		for (int i = 1; i < yCoordNumbers; i++) {
			g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, Y_AXIS_SECOND_Y_COORD
					- (i * yLength), Y_AXIS_X_COORD + SECOND_LENGHT,
					Y_AXIS_SECOND_Y_COORD - (i * yLength));
			g2.drawString(Integer.toString(i), Y_AXIS_X_COORD
					- AXIS_STRING_DISTANCE, Y_AXIS_SECOND_Y_COORD
					- (i * yLength));
		}

		Vector<Point> points = new Vector<Point>();
		int x;
		double y;

		// Find points
		// Static value range currently, will sub in user input later.
		for (x = 0; x < 20; x++) {
			((FunctionElement) node).setVariableValue("x", x);
			y = node.evaluate();
			Point current = new Point(x, (int) y);
			points.add(current);
			// Save it to the vector, then draw...
			g2.fillOval(

			X_AXIS_FIRST_X_COORD + (x * xLength) - 3,
					(int) (Y_AXIS_SECOND_Y_COORD - (y * yLength) + 2),
					ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);
		}

		// Iterate over vector of points, draw line between neighbours.
		for (int i = 0; i < points.size() - 1; i++) {

			int x1 = points.get(i).getx();
			int y1 = points.get(i).gety();
			int x2 = points.get(i + 1).getx();
			int y2 = points.get(i + 1).gety();

			g2.drawLine(X_AXIS_FIRST_X_COORD + (x1 * xLength),
					Y_AXIS_SECOND_Y_COORD - (y1 * yLength),
					X_AXIS_FIRST_X_COORD + (x2 * xLength),
					Y_AXIS_SECOND_Y_COORD - (y2 * yLength));
		}
	}
}