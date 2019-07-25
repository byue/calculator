package utils.factory;
import utils.*;

public class ExpressionUnitFactory {
	public static ExpressionUnit create(String rep) {
		if (rep.equals("+")) {
			return new Plus();
		} else if (rep.equals("-")) {
			return new Minus();
		} else if (rep.equals("*")) {
			return new Multiply();
		} else if (rep.equals("/")) {
			return new Divide();
		} else if (rep.equals("%")) {
         return new Modulo();
      } else if (rep.equals("U+")) {
			return new UnaryPlus();
		} else if (rep.equals("U-")) {
			return new UnaryMinus();
		} else if (rep.equals("(")) {
			return new LeftParenthesis();
		} else if (rep.equals(")")) {
			return new RightParenthesis();
		} else {
			try {
				return new Operand(Double.parseDouble(rep));
			} catch (Exception e) {
				throw new IllegalArgumentException("Input contains illegal symbol");
			}
		}
	}
}
