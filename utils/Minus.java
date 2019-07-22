package utils;
import utils.interfaces.BinaryOperator;

public class Minus extends Operator implements BinaryOperator {
	public Minus() {
		this.precedence = 0;
	}
	public Operand evaluate(Operand o1, Operand o2) {
		return new Operand(o1.getValue() - o2.getValue());
	}
}
