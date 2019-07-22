import utils.*;
import ShuntingParser.ReversePolishEvaluator;
import ShuntingParser.ShuntingYard;

public class Driver {
	public static void main(String[] args) {
      String input =  "-(-5) + 6.5 * 2 - +10 / -2 * (((1 + 2.7) / 0.4) + -.7)";
      double result = ReversePolishEvaluator.evaluate(ShuntingYard.getPostfix(input));
		System.out.println(input);
		System.out.println(result);
   }
}

