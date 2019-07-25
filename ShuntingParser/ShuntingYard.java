package ShuntingParser;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.IllegalArgumentException;
import utils.*;
import utils.factory.ExpressionUnitFactory;

public class ShuntingYard {
	public static List<ExpressionUnit> getPostfix(String infix) {
		Stack<Operator> operators = new Stack<Operator>();
		List<ExpressionUnit> output = new ArrayList<ExpressionUnit>();
		String[] formattedExpression = ShuntingYard.formatExpression(infix);
		for (int i = 0; i < formattedExpression.length; i++) {
			String token = formattedExpression[i];
			// unary operators are at start of expression, after (, or after another operator
			// differentiate unary + and - with U prefix
         if ((token.equals("-") || token.equals("+"))) {
				if (i == 0 || hasOperatorBefore(ExpressionUnitFactory.create(formattedExpression[i - 1]))) {
					token = token.equals("+") ? "U+" : "U-";
				}
			}
			ExpressionUnit unit = ExpressionUnitFactory.create(token);
			if (unit instanceof Operand) {
				output.add((Operand) unit);
			} else if (unit instanceof LeftParenthesis) {
				operators.push((LeftParenthesis) unit);
			} else if (unit instanceof RightParenthesis) {
				while (!operators.isEmpty() && !(operators.peek() instanceof LeftParenthesis)) {
					output.add(operators.pop());
				}
				if (!operators.isEmpty() && (operators.peek() instanceof LeftParenthesis)) {
					operators.pop();
				} else {
					throw new IllegalArgumentException("Right Parenthesis found but no corresponding Left Parenthesis was found.");
				}
			} else {
				Operator op = (Operator) unit;
				while (!operators.isEmpty() && !(operators.peek() instanceof RightParenthesis)
													 && !(operators.peek() instanceof LeftParenthesis)
													 && op.compareTo((Operator) operators.peek()) <= 0) {
					output.add(operators.pop());
				}
				operators.push(op);
			}
		}
		while (!operators.isEmpty()) {
			Operator op = operators.pop();
			if (op instanceof RightParenthesis || op instanceof LeftParenthesis) {
				throw new IllegalArgumentException("Found left or right parenthesis in operator stack.");
			}
			output.add(op);
		}
		return output;
	}

	private static String[] formatExpression(String expression) {
		Pattern pattern =
			 Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\%\\+\\-\\*/\\(\\)]))");
		Matcher m = pattern.matcher(expression);
		StringBuilder formattedExpression = new StringBuilder();
		boolean first = true;
		while (m.find()) {
			if (first) {
				first = false;
			} else {
				formattedExpression.append(" ");
			}
			String token = m.group();
         int periodCount = 0;
         for (int i = 0; i < token.length(); i++) {
				if (token.charAt(i) == '.') {
				    periodCount++;
				}
            if (periodCount == 2) {
                throw new IllegalArgumentException("Bad formatting, extra decimal points");
            }
			}
			formattedExpression.append(m.group());
		}
		return formattedExpression.toString().split(" ");
	}

	private static boolean hasOperatorBefore(ExpressionUnit previousUnit) {
		return (previousUnit instanceof LeftParenthesis || ((previousUnit instanceof Operator)
																			&& !(previousUnit instanceof RightParenthesis)));
	}
}
