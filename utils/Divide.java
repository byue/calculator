package utils;
import utils.interfaces.BinaryOperator;

public class Divide extends Operator implements BinaryOperator {
	public Divide() {
		this.precedence = 1;
	}
	public Operand evaluate(Operand o1, Operand o2) {
		return new Operand(o1.getValue() / o2.getValue());
	}
}
