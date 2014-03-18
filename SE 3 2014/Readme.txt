Name: Thomas Higgins
Student no.:11322981

Desciption:
1. FormulaElement: This class is the main parent of all other classes in this program, and at the moment is used
to house the main in which we test all other classes by implementing the test formulas.
	Methods:
	ParseFormula(): Scans recursive formula string argument and returns OOP formula hierarchy. Brackets indicate a
subformula which parseformula is recursively called on. The resultant OOP of the subformula is added to the argument
list of the overall formula. 

2. ConstantElement: This class is a subclass of FormulaElement which has a double value that cannot be changed 
after being initialized.
	Methods:
	getValue(): Returns the double value of the constant element
	toString():	Returns the constant in the form of a string 

3. VariableElement: This class is a subclass of FormulaElement which has a string name which can only be read 
through the public method "getName" after being initialized. It also has a double value which can be gotten and set
using "getValue" and "setValue" public methods.
	Methods:
	getName(): Returns the name assigned to the variable element
	getValue():	Returns the value assigned to the variable element
	setValue():	Sets the value of the variable element to that of the input
	toString():	Returns the variable name in a string

4. FunctionElement: This is a subclass of FormulaElement, and is the parent class for all function classes. This
class has a vector which holds FormualElements. FormulaElements can only be added using the "addNewArgument" public 
method. The vector then can only be read using the "getArguments" public method which returns the full vector.
	Methods:
	addNewArgument(): Adds the input argument to the the list of arguments of function arguments
	getArguments():	Returns the vector of function arguments	

5. MinusFunctionElement: This is a subclass of FunctionElement which can be constructed with 0 or 2 arguments.
	Methods:
	addNewArgument(): This overrides the addNewArgument method to not allow there to be more than two arguments 
added to the function arguments.	
	toString(): Returns the string representation of the minusFunctionElement in the form argument-argument 
where all arguments of equal precedence are surrounded with brackets,		

6. MultipleFunctionElement: This is a subclass of FunctionElement which can be constructed with 0 or 2 arguments.
	Methods:
	addNewArgument(): This overrides the addNewArgument method to add the arguments of another mult function to it 
if one of it's arguments is a mult. 
	toString():	Returns the string representation of the multipleFunctionElement in the form argumentargument, 
where where all arguments of lower precedence are surrounded with brackets, constants and variables appear first 
and second respectively. All constants are multiplied together and multiples of the same variable are represented 
as powers. 

7. PlusFunctionElement: This is a subclass of FunctionElement which can be constructed with 0 or 2 arguments.
	Methods:
	addNewArgument(): This overrides the addNewArgument method to add the arguments of another plus function to it 
if one of it's arguments is a plus. 
	toString():	Returns the string representation of the plusFunctionElement in the form argument+argument, where 
all arguments of lower precedence are surrounded with brackets, constants and variables appear first and second 
respectively. All constants are added together and multiples of the same variable are represented as products.
	
8. DivideFunctionElement: This is a subclass of FunctionElement which can be constructed with 0 or 2 arguments.
	Methods:
	addNewArgument(): This overrides the addNewArgument method to not allow there to be more than two arguments 
added to the function arguments.	
	toString():	Returns the string representation of the divideFunctionElement in the form argument/argument
where all arguments of equal or lower precedence are surrounded with brackets.

9. CosineFunctionElement: This is a subclass of FunctionElement which can be constructed with 0 or 1 argument.
	Methods:
	addNewArgument(): This overrides the addNewArgument method to not allow there to be more than one argument 
to be added to the function arguments.	
	toString():	Returns the string representation of the SineFunctionElement in the form sin(argument).

10. SineFunctionElement: This is a subclass of FunctionElement which can be constructed with 0 or 1 argument.
	Methods:
	addNewArgument(): This overrides the addNewArgument method to not allow there to be more than one argument 
to be added to the function arguments.	
	toString(): Returns the string representation of the CosineFunctionElement in the form cos(argument).
	
11. PowerFuctionElement: This is a subclass of FunctionElement which can be constructed with 0 or 2 arguments.
	Methods:
	addNewArgument(): This overrides the addNewArgument method to not allow there to be more than two arguments 
added to the function arguments.	
	toString():	Returns the string representation of the PowerFunctionElement in the form argument^argument
where all arguments of equal or lower precedence are surrounded with brackets.