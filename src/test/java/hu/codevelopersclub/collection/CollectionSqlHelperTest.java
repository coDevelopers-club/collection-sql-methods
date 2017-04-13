package hu.codevelopersclub.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CollectionSqlHelperTest {

    @Test
    public void innerJoinTest(){
        List<Integer> aList = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> bList = new ArrayList<>(Arrays.asList(1,4,6));
        BiFunction<Integer,Integer,Boolean> joinOn = (a,b) -> (2 * a == b);

        List<Tuple2<Integer,Integer>> expected =
                Arrays.asList(
                        new Tuple2<Integer, Integer>(2,4),
                        new Tuple2<Integer, Integer>(3,6)
                        );
        Assert.assertEquals(expected,CollectionSqlHelper.innerJoin(aList,bList,joinOn));
    }
}
