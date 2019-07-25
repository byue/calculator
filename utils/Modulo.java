package utils;
import utils.interfaces.BinaryOperator;

public class Modulo extends Operator implements BinaryOperator {
	public Modulo() {
		this.precedence = 1;
	}
	public Operand evaluate(Operand o1, Operand o2) {
		return new Operand(o1.getValue() % o2.getValue());
	}
}
