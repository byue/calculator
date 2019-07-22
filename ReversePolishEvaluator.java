import java.util.List;
import java.util.Stack;

public class ReversePolishEvaluator {
	public static double evaluate(List<String> postfix) {
		Stack<Double> results = new Stack<Double>();
		for (String token : postfix) {
			if (Operators.PRECEDENCE.containsKey(token)) {
				double operand1 = results.pop();
				if (token.equals("U+")) {
					results.push(operand1);
				} else if (token.equals("U-")) {
					results.push(-operand1);
				} else {
					double operand2 = results.pop();
					if (token.equals("+")) {
						results.push(operand2 + operand1);
					} else if (token.equals("-")) {					
						results.push(operand2 - operand1);
					} else if (token.equals("*")) {
						results.push(operand2 * operand1);
					} else if (token.equals("/")) {
						results.push(operand2 / operand1);
					}
				}
			} else {
				results.push(Double.parseDouble(token));
			}
		}
		return results.pop();
	}
}
