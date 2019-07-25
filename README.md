# Calculator
Calculator supports +, -, *, /, %, and parenthesis. Order of operations are applied. 

I utilized the Djikstra's Shunting-Yard Algorithm and parsing reverse Polish notation to evaluate the mathematical expression.
To add a new operation, add new class extending Operator and implementing either UnaryOperator or BinaryOperator. Add class to factory. Update gui.

Enter "make run" to run calculator program on command line. Pressing enter after inputting mathematical expression displays result. Exit with Cntrl-C. "make clean" cleans up .class files. "make" builds the program.

Enter "make gui" to run gui calculator program. MS stores result in memory. MR retrieves result from memory. MC clears memory. = sign displays result of expression.

On Windows run javac Driver.java followed by java Driver to run command line. Run javac CalculatorGui.java and java CalculatorGui to run gui.
