package hu.codevelopersclub.collection;

import org.junit.Assert;
import org.junit.Test;

public class Tuple2Test {
    @Test
    public void equalAllNonNullTest() {
        Tuple2<String, String> t1 = new Tuple2<>("a", "b");
        Tuple2<String, String> t2 = new Tuple2<>("a", "b");
        Assert.assertTrue(t1.equals(t2));
    }

    @Test
    public void equalAllNullTest() {
        Tuple2<String, String> t1 = new Tuple2<>(null, null);
        Tuple2<String, String> t2 = new Tuple2<>(null, null);
        Assert.assertTrue(t1.equals(t2));
    }

    @Test
    public void equalLeftNullTest() {
        Tuple2<String, String> t1 = new Tuple2<>(null, "b");
        Tuple2<String, String> t2 = new Tuple2<>(null, "b");
        Assert.assertTrue(t1.equals(t2));
    }

    @Test
    public void equalRightNullTest() {
        Tuple2<String, String> t1 = new Tuple2<>("a", null);
        Tuple2<String, String> t2 = new Tuple2<>("a", null);
        Assert.assertTrue(t1.equals(t2));
    }

    @Test
    public void notEqualWithoutNullTest() {
        Tuple2<String, String> t1 = new Tuple2<>("a", "b");
        Tuple2<String, String> t2 = new Tuple2<>("b", "b");
        Assert.assertFalse(t1.equals(t2));
    }

    @Test
    public void notEqualWithNullTest() {
        Tuple2<String, String> t1 = new Tuple2<>("a", null);
        Tuple2<String, String> t2 = new Tuple2<>(null, "b");
        Assert.assertFalse(t1.equals(t2));
    }
}
