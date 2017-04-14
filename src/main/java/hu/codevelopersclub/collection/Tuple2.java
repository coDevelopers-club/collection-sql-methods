package hu.codevelopersclub.collection;

public class Tuple2<A, B> {
    private final A left;
    private final B right;

    public Tuple2(A left, B right) {
        this.left = left;
        this.right = right;
    }

    public A getLeft() {
        return left;
    }

    public B getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple2<?, ?> other = (Tuple2<?, ?>) o;

        if (Util.allNonNull(left, right, other.left, other.right)) {
            return left.equals(other.left) && right.equals(other.right);
        } else if (Util.allNonNull(left, other.left) &&
                Util.allNull(right, other.right)) {
            return left.equals(other.left);
        } else if (Util.allNull(left, other.left) &&
                Util.allNonNull(right, other.right)) {
            return right.equals(other.right);
        } else return Util.allNull(left, other.left, right, other.right);

    }

    @Override
    public int hashCode() {
        int result = left == null ? 127 : left.hashCode();
        result = 31 * result + (right == null ? 131 : right.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Tuple2("
                + left + " , "
                + right +
                ')';
    }
}
