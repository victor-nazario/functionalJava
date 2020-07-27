package strings_comp_and_filters;

/**
 * Created on 26 Jul, 2020 - 22:51
 *
 * @author Victor Nazario Morales <victor.nazario@innovatiopr.com>
 */
public class Person {

    private final String name;
    private final int age;

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int ageDifference(final Person other){
        return this.getAge() - other.getAge();
    }

    @Override
    public String toString() {
        return String.format("%s - %d", this.getName(), this.getAge());
    }
}
