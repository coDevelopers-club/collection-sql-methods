package hu.codevelopersclub.collection;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CollectionSqlHelper {
    public static <A, B> List<Tuple2<A, B>> innerJoin(
            Collection<A> aCollection,
            Collection<B> bCollection,
            BiFunction<A, B, Boolean> joinOn) {
        return aCollection.stream()
                .flatMap(a -> bCollection.stream()
                        .filter(b -> joinOn.apply(a, b))
                        .map(b -> new Tuple2<>(a, b)))
                .collect(Collectors.toList());
    }

    public static <A, B> List<Tuple2<A, B>> leftOuterJoin(
            Collection<A> aCollection,
            Collection<B> bCollection,
            BiFunction<A, B, Boolean> joinOn) {
        throw new UnsupportedOperationException();
    }

    public static <A, B> List<Tuple2<A, B>> rightOuterJoin(
            Collection<A> aCollection,
            Collection<B> bCollection,
            BiFunction<A, B, Boolean> joinOn) {
        throw new UnsupportedOperationException();
    }


}
