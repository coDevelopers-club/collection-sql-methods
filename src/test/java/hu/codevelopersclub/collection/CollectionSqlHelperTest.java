package hu.codevelopersclub.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class CollectionSqlHelperTest {
    private List<Integer> aList;
    private List<Integer> bList;
    BiFunction<Integer,Integer,Boolean> joinOn;

    @Before
    public void buildUp(){
        aList = new ArrayList<>(Arrays.asList(1, 2, 3));
        bList = new ArrayList<>(Arrays.asList(1, 4, 6));
        joinOn = (a, b) -> (2 * a == b);
    }


    @Test
    public void innerJoinTest() {


        List<Tuple2<Integer, Integer>> expected =
                Arrays.asList(
                        new Tuple2<Integer, Integer>(2, 4),
                        new Tuple2<Integer, Integer>(3, 6)
                );
        Assert.assertEquals(expected, CollectionSqlHelper.innerJoin(aList, bList, joinOn));
    }

    @Test
    public void leftOuterJoinTest() {
        List<Tuple2<Integer, Integer>> expected =
                Arrays.asList(
                        new Tuple2<Integer,Integer>(1,null),
                        new Tuple2<Integer, Integer>(2, 4),
                        new Tuple2<Integer, Integer>(3, 6)
                );
        Assert.assertEquals(expected, CollectionSqlHelper.leftOuterJoin(aList, bList, joinOn));
    }

    @Test
    public void rightOuterJoinTest(){
        List<Tuple2<Integer, Integer>> expected =
                Arrays.asList(
                        new Tuple2<Integer,Integer>(null,1),
                        new Tuple2<Integer, Integer>(2, 4),
                        new Tuple2<Integer, Integer>(3, 6)
                );
        Assert.assertEquals(expected, CollectionSqlHelper.rightOuterJoin(aList, bList, joinOn));
    }
}
