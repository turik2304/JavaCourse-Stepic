import java.util.NoSuchElementException;
import java.util.Objects;

class Pair<F, S> {
    private final F firstValue;
    private final S secondValue;


    private Pair(F firstValue, S secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <F, S> Pair<F, S> of(F firstValue, S secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    public F getFirst() {
        if (this.firstValue == null) {
            throw new NoSuchElementException();
        } else {
            return this.firstValue;
        }
    }

    public S getSecond() {
        if (this.secondValue == null) {
            throw new NoSuchElementException();
        } else {
            return this.secondValue;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Pair)) {
            return false;
        } else {
            Pair<?, ?> other = (Pair) obj;
            return (Objects.equals(this.firstValue, other.firstValue) && Objects.equals(this.secondValue, other.secondValue));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode((this.firstValue)) + Objects.hashCode((this.secondValue));
    }
}
