import java.util.*;

public class Test {


    final static Map<String, List<String>> map1 = new LinkedHashMap<>();
    final static List<String> list = new ArrayList<>();
    public static void main(String[] args) {
        accept("lena", "popa");
        accept("lena", "popa1");
        accept("lena", "popa2");
        accept("lena", "popa3");
        System.out.println(map1.get("lena"));
        System.out.println(map1);

        Map<String, String> map = new HashMap<>();
        map.put("name", "Joan");


//        map.computeIfPresent("name", (key, value) -> value);

        System.out.println(map);




    }
    public static void accept(String from, String message) {
        list.add(message);
        map1.put(from, list);
    }
}
