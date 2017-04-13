package hu.codevelopersclub.collection;

import java.util.Collection;
import java.util.Collections;
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


    private static <B> List<B> selfOrFallBackInCaseOfEmpty(List<B> collection, List<B> fallback){
        return collection.isEmpty() ? fallback : collection;
    }

    private static <A,B> List<B> getAssociated(A base, Collection<B> collection, BiFunction<A, B, Boolean> association){
        return selfOrFallBackInCaseOfEmpty(collection.stream().filter(e -> association.apply(base,e)).collect(Collectors.toList()),Collections.singletonList(null));
    }

    public static <A, B> List<Tuple2<A, B>> leftOuterJoin(
            Collection<A> aCollection,
            Collection<B> bCollection,
            BiFunction<A, B, Boolean> joinOn) {
        List<Tuple2<A,List<B>>> middle =
                aCollection.stream().map(x -> new Tuple2<>(x, getAssociated(x,bCollection,joinOn))).collect(Collectors.toList());

        return middle.stream()
                .flatMap(x-> x.getRight().stream().map(b -> new Tuple2<>(x.getLeft(),b))).collect(Collectors.toList());
    }

    public static <A, B> List<Tuple2<A, B>> rightOuterJoin(
            Collection<A> aCollection,
            Collection<B> bCollection,
            BiFunction<A, B, Boolean> joinOn) {
        List<Tuple2<B,List<A>>> middle =
                bCollection.stream().map(x -> new Tuple2<>(x, getAssociated(x,aCollection,(a,b) ->joinOn.apply(b,a)))).collect(Collectors.toList());
        return middle.stream()
                .flatMap(x-> x.getRight().stream().map(a -> new Tuple2<>(a,x.getLeft()))).collect(Collectors.toList());
    }


}
