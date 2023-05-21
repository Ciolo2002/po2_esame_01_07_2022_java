import java.util.function.Function;

public interface Predicate<T> extends Function<T,Boolean> {
    Boolean apply(T t);
}
