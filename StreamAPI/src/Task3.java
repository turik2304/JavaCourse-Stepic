import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Task3 {
    public static void main(String[] args) {
        Iterable<String> it = () -> new Scanner(System.in, "UTF8").useDelimiter("[^\\p{L}\\p{Digit}]+");
        Map<String, Long> wordsFreq = StreamSupport.stream(it.spliterator(), false)
                .flatMap(Pattern.compile(" ")::splitAsStream)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        wordsFreq.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }


}
