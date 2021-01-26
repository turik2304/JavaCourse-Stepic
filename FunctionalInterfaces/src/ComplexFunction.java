import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class ComplexFunction {
    public static void main(String[] args) {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeLength = ternaryOperator(condition, ifTrue, ifFalse);
        String s = null;
        System.out.println(safeLength.apply(s));
    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse
    ) {
        return x -> condition.test(x) ? ifTrue.apply(x) : ifFalse.apply(x);

    }


}
