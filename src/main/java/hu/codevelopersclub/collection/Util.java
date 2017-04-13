package hu.codevelopersclub.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Util {
    public static boolean allNull(Object... args){
        return Arrays.stream(args).filter(Objects::isNull).count() == args.length;
    }

    public static boolean allNonNull(Object... args){
        return Arrays.stream(args).filter(Objects::nonNull).count() == args.length;
    }
}
