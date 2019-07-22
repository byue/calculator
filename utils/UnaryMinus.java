package utils;
import utils.interfaces.UnaryOperator;

public class UnaryMinus extends Operator implements UnaryOperator {
	public UnaryMinus() {
		this.precedence = 2;
	}
	public Operand evaluate(Operand operand) {
		return new Operand(-operand.getValue());
	}
}
