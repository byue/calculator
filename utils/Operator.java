package utils;

public class Operator extends ExpressionUnit implements Comparable<Operator> {
	protected int precedence;
	@Override
	public int compareTo(Operator other) {
   	return Integer.compare(this.precedence, other.precedence);
	}
}
