package utils;

public class Operand extends ExpressionUnit {
	private double operand;
	public Operand(double operand) {
		this.operand = operand;
	}
	public double getValue() {
		return this.operand;
	}
}
