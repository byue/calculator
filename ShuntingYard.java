import java.util.*;
import java.util.regex.*;

public class ShuntingYard {
	private static final Map<String, Integer> PRECEDENCE = new HashMap<String, Integer>() {{
		put("+", 0);
      put("-", 0);
		put("*", 1);
		put("/", 1);
   }};

	private static String formatExpression(String expression) {
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
		return formattedExpression.toString();
	}

	public static List<String> getPostfix(String infix) {
		String formattedExpression = ShuntingYard.formatExpression(infix);
		Stack<String> operators = new Stack<String>();
		List<String> output = new ArrayList<String>();
		for (String token : formattedExpression.split(" ")) {
			if (PRECEDENCE.containsKey(token)) {
				while (!operators.isEmpty() && !operators.peek().equals(")")
													 && !operators.peek().equals("(")
													 && PRECEDENCE.get(token) >= PRECEDENCE.get(operators.peek())) {
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
}
