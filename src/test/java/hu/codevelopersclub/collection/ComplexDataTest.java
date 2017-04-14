package hu.codevelopersclub.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ComplexDataTest {


    private List<Person> people;
    private List<Umbrella> umbrellas;
    private BiFunction<Person, Umbrella, Boolean> joinOn;

    @Before
    public void setUp() {
        people = Arrays.asList(
                new Person("John", "Accountant"),
                new Person("Kevin", "Manager"),
                new Person("Bob", "Janitor")
        );

        umbrellas = Arrays.asList(
                new Umbrella("Red", "John"),
                new Umbrella("Blue", "Bob"),
                new Umbrella("Black", null)
        );

        joinOn = (a, b) -> a.name.equals(b.belongsTo);
    }

    @Test
    public void complexInnerJoin() throws Exception {
        List<Tuple2<Person, Umbrella>> expected = Arrays.asList(
                new Tuple2<Person, Umbrella>(new Person("John", "Accountant"), new Umbrella("Red", "John")),
                new Tuple2<Person, Umbrella>(new Person("Bob", "Janitor"), new Umbrella("Blue", "Bob"))
        );

        Assert.assertEquals(expected, CollectionSqlHelper.innerJoin(people, umbrellas, joinOn));
    }

    @Test
    public void complexLeftOuter() throws Exception {
        List<Tuple2<Person, Umbrella>> expected = Arrays.asList(
                new Tuple2<Person, Umbrella>(new Person("John", "Accountant"), new Umbrella("Red", "John")),
                new Tuple2<Person, Umbrella>(new Person("Kevin", "Manager"), null),
                new Tuple2<Person, Umbrella>(new Person("Bob", "Janitor"), new Umbrella("Blue", "Bob"))
        );

        Assert.assertEquals(expected, CollectionSqlHelper.leftOuterJoin(people, umbrellas, joinOn));
    }

    @Test
    public void complexRightOuter() throws Exception {
        List<Tuple2<Person, Umbrella>> expected = Arrays.asList(
                new Tuple2<Person, Umbrella>(new Person("John", "Accountant"), new Umbrella("Red", "John")),
                new Tuple2<Person, Umbrella>(new Person("Bob", "Janitor"), new Umbrella("Blue", "Bob")),
                new Tuple2<Person, Umbrella>(null, new Umbrella("Black", null))
        );

        Assert.assertEquals(expected, CollectionSqlHelper.rightOuterJoin(people, umbrellas, (a, b) -> joinOn.apply(b, a)));
    }


    private class Person {

        private String name;
        private String association;

        public Person(String name, String association) {
            this.name = name;
            this.association = association;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAssociation() {
            return association;
        }

        public void setAssociation(String association) {
            this.association = association;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (name != null ? !name.equals(person.name) : person.name != null) return false;
            return association != null ? association.equals(person.association) : person.association == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (association != null ? association.hashCode() : 0);
            return result;
        }
    }

    private class Umbrella {
        private String color;
        private String belongsTo;

        public Umbrella(String color, String belongsTo) {
            this.color = color;
            this.belongsTo = belongsTo;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getBelongsTo() {
            return belongsTo;
        }

        public void setBelongsTo(String belongsTo) {
            this.belongsTo = belongsTo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Umbrella umbrella = (Umbrella) o;

            if (color != null ? !color.equals(umbrella.color) : umbrella.color != null) return false;
            return belongsTo != null ? belongsTo.equals(umbrella.belongsTo) : umbrella.belongsTo == null;
        }

        @Override
        public int hashCode() {
            int result = color != null ? color.hashCode() : 0;
            result = 31 * result + (belongsTo != null ? belongsTo.hashCode() : 0);
            return result;
        }
    }

}
