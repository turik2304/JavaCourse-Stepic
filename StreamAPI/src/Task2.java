import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of();
        BiConsumer<Integer, Integer> biConsumer = (integer, integer2) -> {
            System.out.println(integer);
            System.out.println(integer2);
        };
        findMinMax(integerStream, Integer::compareTo, biConsumer);

    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        ArrayDeque<T> arrayDeque = stream.sorted(order)
                .collect(Collectors.toCollection(ArrayDeque::new));
        if (arrayDeque.size() != 0) {
            T min = arrayDeque.peekFirst();
            T max = arrayDeque.peekLast();
            minMaxConsumer.accept(min, max);
        } else minMaxConsumer.accept(null, null);
    }
}
