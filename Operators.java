import java.util.Map;
import java.util.HashMap;

public class Operators {
   public static final Map<String, Integer> PRECEDENCE = new HashMap<String, Integer>() {{
      put("+", 0);
      put("-", 0);
      put("*", 1);
      put("/", 1);
		put("U+", 2);
		put("U-", 2);
   }};
}
