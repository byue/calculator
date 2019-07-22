import java.util.List;
import java.util.stream.Collectors;

public class Driver {
	public static void main(String[] args) {
      String input =  "-(-5) + 6.5 * 2 - +10 / -2 * (((1 + 2.7) / 0.4) + -.7)";
      String input2 = "4+18/(9-3)";
      List<String> res = ShuntingYard.getPostfix(input2);
      System.out.println(input2);
      System.out.println(res.stream().map(Object::toString).collect(Collectors.joining(" ")));
   }
}

