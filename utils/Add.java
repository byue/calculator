package utils;
import utils.interfaces.BinaryOperator;

public class Add extends Operator implements BinaryOperator {
	public Add() {
		this.precedence = 0;
	}

}
