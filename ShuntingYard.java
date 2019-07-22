import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ShuntingYard {
	public static List<String> getPostfix(String infix) {
		Stack<String> operators = new Stack<String>();
		List<String> output = new ArrayList<String>();
		String[] formattedExpression = ShuntingYard.formatExpression(infix);
		for (int i = 0; i < formattedExpression.length; i++) {
			String token = formattedExpression[i];
			// unary operators are at start of expression, after (, or after another operator
			// differentiate unary + and - with U prefix
         if ((token.equals("-") || token.equals("+")) &&
				 (i == 0 || formattedExpression[i - 1].equals("(")
                     || Operators.PRECEDENCE.containsKey(formattedExpression[i - 1]))) {
				token = token.equals("+") ? "U+" : "U-";
			}	
			if (Operators.PRECEDENCE.containsKey(token)) {
				while (!operators.isEmpty() && !operators.peek().equals(")")
													 && !operators.peek().equals("(")
													 && Operators.PRECEDENCE.get(token) 
															<= Operators.PRECEDENCE.get(operators.peek())) {
					output.add(operators.pop());
				}
				operators.push(token);
			} else if (token.equals("(")) {
				operators.push(token);
			} else if (token.equals(")")) {
				while (!operators.isEmpty() && !operators.peek().equals("(")) {
					output.add(operators.pop());
				}
				if (!operators.isEmpty() && operators.peek().equals("(")) {
					operators.pop();
				}
			} else {
				if (token.contains(".")) {
					output.add(Double.parseDouble(token) + "");
				} else {
					output.add(Integer.parseInt(token) + "");
				}
			}
		}
		while (!operators.isEmpty()) {
			output.add(operators.pop());
		}
		return output;
	}

	private static String[] formatExpression(String expression) {
		Pattern pattern =
			 Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\+\\-\\*/\\(\\)]))");
		Matcher m = pattern.matcher(expression);
		StringBuilder formattedExpression = new StringBuilder();
		boolean first = true;
		while (m.find()) {
			if (first) {
				first = false;
			} else {
				formattedExpression.append(" ");
			}
			formattedExpression.append(m.group());
		}
		return formattedExpression.toString().split(" ");
	}
}
