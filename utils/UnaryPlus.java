package utils;
import utils.interfaces.UnaryOperator;

public class UnaryPlus extends Operator implements UnaryOperator {
	public UnaryPlus() {
		this.precedence = 2;
	}
	public Operand evaluate(Operand operand) {
		return operand;
	}
}
