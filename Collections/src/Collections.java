import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Collections {
    public static void main(String[] args) {

    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> set1Buff = new LinkedHashSet<>(set1);
        Set<T> set2Buff = new LinkedHashSet<>(set2);
        set1Buff.removeAll(set2);
        set2Buff.removeAll(set1);
        set1Buff.addAll(set2Buff);
        return set1Buff;
    }
}
