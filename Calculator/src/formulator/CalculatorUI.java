import org.eclipse.swt.*;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.custom.*;
 
public class CalculatorUI {
 
  public void createComponents() {
    FillLayout2 = new FillLayout();
    Display2 = new Display();
    Calculator = new Shell(Display2);
    Calculator.setText("Calculator");
    Calculator.setLayout(FillLayout2);
    menuBar1 = new Menu(Calculator , SWT.BAR);
    Calculator.setMenuBar(menuBar1);
    Menu4 = new Menu(Calculator , SWT.DROP_DOWN);
    MenuItem4 = new MenuItem(menuBar1 , SWT.CASCADE);
    MenuItem4.setText("Edit");
    MenuItem4.setMenu(Menu4);
    MenuItem5 = new MenuItem(Menu4 , SWT.PUSH);
    MenuItem5.setText("Copy");
    MenuItem6 = new MenuItem(Menu4 , SWT.PUSH);
    MenuItem6.setText("Paste");
    Menu7 = new Menu(Calculator , SWT.DROP_DOWN);
    MenuItem7 = new MenuItem(menuBar1 , SWT.CASCADE);
    MenuItem7.setText("View");
    MenuItem7.setMenu(Menu7);
    MenuItem8 = new MenuItem(Menu7 , SWT.PUSH);
    MenuItem8.setText("Standard");
    MenuItem9 = new MenuItem(Menu7 , SWT.PUSH);
    MenuItem9.setText("Scientific");
    Menu11 = new Menu(Calculator , SWT.DROP_DOWN);
    MenuItem11 = new MenuItem(menuBar1 , SWT.CASCADE);
    MenuItem11.setText("Help");
    MenuItem11.setMenu(Menu11);
    MenuItem12 = new MenuItem(Menu11 , SWT.PUSH);
    MenuItem12.setText("Help Topics");
    MenuItem13 = new MenuItem(Menu11 , SWT.PUSH);
    MenuItem13.setText("About Calculator");
    Composite14 = new Composite(Calculator , SWT.FLAT);
    RowLayout15 = new RowLayout(SWT.VERTICAL);
    Composite14.setLayout(RowLayout15);
    Composite16 = new Composite(Composite14 , SWT.FLAT);
    Text17 = new Text(Composite16 , SWT.BORDER);
    Text17.setBounds(1 , 2 , 170 , 20);
    Composite18 = new Composite(Composite14 , SWT.FLAT);
    GridLayout19 = new GridLayout();
    Composite18.setLayout(GridLayout19);
    GridLayout19.numColumns = 4;
    GridLayout19.horizontalSpacing = 16;
    Button20 = new Button(Composite18 , SWT.PUSH);
    Button20.setText("");
    Button21 = new Button(Composite18 , SWT.PUSH);
    Button21.setText("Backspace");
    Button22 = new Button(Composite18 , SWT.PUSH);
    Button22.setText("Graph");
    Button23 = new Button(Composite18 , SWT.PUSH);
    Button23.setText("C");
    JCalcPanel = new Composite(Composite14 , SWT.FLAT);
    GridLayout25 = new GridLayout();
    JCalcPanel.setLayout(GridLayout25);
    GridLayout25.numColumns = 6;
    GridLayout25.horizontalSpacing = 4;
    Button26 = new Button(JCalcPanel , SWT.PUSH);
    Button26.setText("cos");
    Button27 = new Button(JCalcPanel , SWT.PUSH);
    Button27.setText("7");
    Button28 = new Button(JCalcPanel , SWT.PUSH);
    Button28.setText("8");
    Button29 = new Button(JCalcPanel , SWT.PUSH);
    Button29.setText("9");
    Button30 = new Button(JCalcPanel , SWT.PUSH);
    Button30.setText("/");
    Button31 = new Button(JCalcPanel , SWT.PUSH);
    Button31.setText("sqrt");
    Button32 = new Button(JCalcPanel , SWT.PUSH);
    Button32.setText("sin");
    Button33 = new Button(JCalcPanel , SWT.PUSH);
    Button33.setText("4");
    Button34 = new Button(JCalcPanel , SWT.PUSH);
    Button34.setText("5");
    Button35 = new Button(JCalcPanel , SWT.PUSH);
    Button35.setText("6");
    Button36 = new Button(JCalcPanel , SWT.PUSH);
    Button36.setText("*");
    Button37 = new Button(JCalcPanel , SWT.PUSH);
    Button37.setText("%");
    Button38 = new Button(JCalcPanel , SWT.PUSH);
    Button38.setText("tan");
    Button39 = new Button(JCalcPanel , SWT.PUSH);
    Button39.setText("1");
    Button40 = new Button(JCalcPanel , SWT.PUSH);
    Button40.setText("2");
    Button41 = new Button(JCalcPanel , SWT.PUSH);
    Button41.setText("3");
    Button42 = new Button(JCalcPanel , SWT.PUSH);
    Button42.setText("-");
    Button43 = new Button(JCalcPanel , SWT.PUSH);
    Button43.setText("1/x");
    Button44 = new Button(JCalcPanel , SWT.PUSH);
    Button44.setText("^");
    Button45 = new Button(JCalcPanel , SWT.PUSH);
    Button45.setText("0");
    Button46 = new Button(JCalcPanel , SWT.PUSH);
    Button46.setText("+/-");
    Button47 = new Button(JCalcPanel , SWT.PUSH);
    Button47.setText(".");
    Button48 = new Button(JCalcPanel , SWT.PUSH);
    Button48.setText("+");
    Button49 = new Button(JCalcPanel , SWT.PUSH);
    Button49.setText("=");
    Calculator.open();
    while (!Calculator.isDisposed ()) {
                                if (!Display2.readAndDispatch ())
                                                Display2.sleep ();
                }
                Display2.dispose ();
  }
 
  public Object getComponent(String name) {
    Object result = null;
    if (name.equalsIgnoreCase("FillLayout2")) {
                                result = FillLayout2;
    }
    else if (name.equalsIgnoreCase("Display2")) {
                                result = Display2;
    }
    else if (name.equalsIgnoreCase("Calculator")) {
                                result = Calculator;
    }
    else if (name.equalsIgnoreCase("menuBar1")) {
                                result = menuBar1;
    }
    else if (name.equalsIgnoreCase("Menu4")) {
                                result = Menu4;
    }
    else if (name.equalsIgnoreCase("MenuItem4")) {
                                result = MenuItem4;
    }
    else if (name.equalsIgnoreCase("MenuItem5")) {
                                result = MenuItem5;
    }
    else if (name.equalsIgnoreCase("MenuItem6")) {
                                result = MenuItem6;
    }
    else if (name.equalsIgnoreCase("Menu7")) {
                                result = Menu7;
    }
    else if (name.equalsIgnoreCase("MenuItem7")) {
                                result = MenuItem7;
    }
    else if (name.equalsIgnoreCase("MenuItem8")) {
                                result = MenuItem8;
    }
    else if (name.equalsIgnoreCase("MenuItem9")) {
                                result = MenuItem9;
    }
    else if (name.equalsIgnoreCase("MenuItem10")) {
                                result = MenuItem10;
    }
    else if (name.equalsIgnoreCase("Menu11")) {
                                result = Menu11;
    }
    else if (name.equalsIgnoreCase("MenuItem11")) {
                                result = MenuItem11;
    }
    else if (name.equalsIgnoreCase("MenuItem12")) {
                                result = MenuItem12;
    }
    else if (name.equalsIgnoreCase("MenuItem13")) {
                                result = MenuItem13;
    }
    else if (name.equalsIgnoreCase("Composite14")) {
                                result = Composite14;
    }
    else if (name.equalsIgnoreCase("RowLayout15")) {
                                result = RowLayout15;
    }
    else if (name.equalsIgnoreCase("Composite16")) {
                                result = Composite16;
    }
    else if (name.equalsIgnoreCase("Text17")) {
                                result = Text17;
    }
    else if (name.equalsIgnoreCase("Composite18")) {
                                result = Composite18;
    }
    else if (name.equalsIgnoreCase("GridLayout19")) {
                                result = GridLayout19;
    }
    else if (name.equalsIgnoreCase("Button20")) {
                                result = Button20;
    }
    else if (name.equalsIgnoreCase("Button21")) {
                                result = Button21;
    }
    else if (name.equalsIgnoreCase("Button22")) {
                                result = Button22;
    }
    else if (name.equalsIgnoreCase("Button23")) {
                                result = Button23;
    }
    else if (name.equalsIgnoreCase("JCalcPanel")) {
                                result = JCalcPanel;
    }
    else if (name.equalsIgnoreCase("GridLayout25")) {
                                result = GridLayout25;
    }
    else if (name.equalsIgnoreCase("Button26")) {
                                result = Button26;
    }
    else if (name.equalsIgnoreCase("Button27")) {
                                result = Button27;
    }
    else if (name.equalsIgnoreCase("Button28")) {
                                result = Button28;
    }
    else if (name.equalsIgnoreCase("Button29")) {
                                result = Button29;
    }
    else if (name.equalsIgnoreCase("Button30")) {
                                result = Button30;
    }
    else if (name.equalsIgnoreCase("Button31")) {
                                result = Button31;
    }
    else if (name.equalsIgnoreCase("Button32")) {
                                result = Button32;
    }
    else if (name.equalsIgnoreCase("Button33")) {
                                result = Button33;
    }
    else if (name.equalsIgnoreCase("Button34")) {
                                result = Button34;
    }
    else if (name.equalsIgnoreCase("Button35")) {
                                result = Button35;
    }
    else if (name.equalsIgnoreCase("Button36")) {
                                result = Button36;
    }
    else if (name.equalsIgnoreCase("Button37")) {
                                result = Button37;
    }
    else if (name.equalsIgnoreCase("Button38")) {
                                result = Button38;
    }
    else if (name.equalsIgnoreCase("Button39")) {
                                result = Button39;
    }
    else if (name.equalsIgnoreCase("Button40")) {
                                result = Button40;
    }
    else if (name.equalsIgnoreCase("Button41")) {
                                result = Button41;
    }
    else if (name.equalsIgnoreCase("Button42")) {
                                result = Button42;
    }
    else if (name.equalsIgnoreCase("Button43")) {
                                result = Button43;
    }
    else if (name.equalsIgnoreCase("Button44")) {
                                result = Button44;
    }
    else if (name.equalsIgnoreCase("Button45")) {
                                result = Button45;
    }
    else if (name.equalsIgnoreCase("Button46")) {
                                result = Button46;
    }
    else if (name.equalsIgnoreCase("Button47")) {
                                result = Button47;
    }
    else if (name.equalsIgnoreCase("Button48")) {
                                result = Button48;
    }
    else if (name.equalsIgnoreCase("Button49")) {
                                result = Button49;
    }
    return(result);
  }
 
  public static CalculatorUI getInstance() {
    if ( gui_instance == null ) {
                                gui_instance = new CalculatorUI();
                                gui_instance.createComponents();
                }
                return gui_instance;
  }
 
  public static void main(String[] args) {
    getInstance();
  }
 
  protected Button Button40;
 
  protected Button Button41;
 
  protected Shell Calculator;
 
  protected FillLayout FillLayout2;
 
  protected GridLayout GridLayout19;
 
  protected MenuItem MenuItem8;
 
  protected MenuItem MenuItem11;
 
  protected MenuItem MenuItem4;
 
  protected GridLayout GridLayout25;
 
  protected MenuItem MenuItem7;
 
  protected RowLayout RowLayout15;
 
  protected MenuItem MenuItem10;
 
  protected Menu Menu11;
 
  protected Composite JCalcPanel;
 
  protected Display Display2;
 
  protected Menu Menu4;
 
  protected MenuItem MenuItem13;
 
  protected MenuItem MenuItem6;
 
  protected Composite Composite18;
 
  protected Composite Composite16;
 
  protected Text Text17;
 
  protected Menu menuBar1;
 
  protected Button Button31;
 
  protected Button Button32;
 
  protected Button Button33;
 
  protected Button Button34;
 
  protected Button Button35;
 
  protected Button Button36;
 
  protected Button Button37;
 
  protected Button Button38;
 
  protected Composite Composite14;
 
  protected Menu Menu7;
 
  protected Button Button39;
 
  protected MenuItem MenuItem12;
 
  protected MenuItem MenuItem5;
 
  protected Button Button49;
 
  protected Button Button48;
 
  protected Button Button47;
 
  protected Button Button46;
 
  protected Button Button45;
 
  protected Button Button20;
 
  protected Button Button21;
 
  protected Button Button22;
 
  protected Button Button23;
 
  protected Button Button26;
 
  protected Button Button27;
 
  protected Button Button42;
 
  protected Button Button43;
 
  protected Button Button44;
 
  protected Button Button30;
 
  private static CalculatorUI gui_instance;
 
  protected Button Button28;
 
  protected Button Button29;
 
  protected MenuItem MenuItem9;
 
  ////>>>>  CalculatorUI: fields and methods
  //// Add fields and methods below.
 
  ////<<<<  CalculatorUI: fields and methods
}