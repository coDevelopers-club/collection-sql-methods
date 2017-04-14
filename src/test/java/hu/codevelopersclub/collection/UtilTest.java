package hu.codevelopersclub.collection;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest {
    @Test
    public void isAllNull() {
        Assert.assertTrue(Util.allNull(null, null));
    }

    @Test
    public void isNotAllNull() {
        Assert.assertFalse(Util.allNull(null, new Object()));
    }


    @Test
    public void isAllNonNull() {
        Assert.assertTrue(Util.allNonNull(new Object(), new Object()));
    }

    @Test
    public void isNotAllNonNull() {
        Assert.assertFalse(Util.allNull(null, new Object()));
    }
}
