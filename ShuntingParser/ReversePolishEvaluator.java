package ShuntingParser;

import java.util.List;
import java.util.Stack;
import utils.*;
import utils.interfaces.BinaryOperator;
import utils.interfaces.UnaryOperator;

public class ReversePolishEvaluator {
	public static double evaluate(List<ExpressionUnit> postfix) {
		Stack<Operand> results = new Stack<Operand>();
		for (ExpressionUnit unit : postfix) {
			if (unit instanceof Operator) {
				Operand operand1 = results.pop();
				if (unit instanceof UnaryOperator) {
					results.push(((UnaryOperator) unit).evaluate(operand1));
				} else {
					Operand operand2 = results.pop();
					results.push(((BinaryOperator) unit).evaluate(operand2, operand1));					
				}
			} else {
				results.push((Operand) unit);
			}
		}
		return results.pop().getValue();
	}
}
