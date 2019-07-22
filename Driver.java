import ShuntingParser.ReversePolishEvaluator;
import ShuntingParser.ShuntingYard;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      while (true) {
      	System.out.print("Enter mathematical expression: ");
			String input = in.nextLine();
 			try {
				double result = ReversePolishEvaluator.evaluate(ShuntingYard.getPostfix(input));
				System.out.println("Result: " + result);
			} catch (Exception e) {
				e.printStackTrace(); 
				System.out.println("Invalid input, re-enter expression.");
			}
      }
   }
}

