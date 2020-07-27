package strings_comp_and_filters;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created on 26 Jul, 2020 - 17:43
 *
 * @author Victor
 */
public class StringsCompAndFilters {

    static final String MESSAGE = "Hello, this is Victor!";

    //Comparator functional interface

     static Comparator<Person> ascendingOrder = Person::ageDifference;

     static Comparator<Person> descendingOrder = ascendingOrder.reversed();

    //List sorted using comparators
    static final List<Person> personList = Arrays.asList(new Person("Joe", 20), new Person("James", 12),
                                            new Person("Victoria", 22), new Person("Ana", 32));

    static final List<Person> ascendingAge = personList.stream()
                                          .sorted(ascendingOrder)
                                          .collect(Collectors.toList());

    static final List<Person> descendingAge = personList.stream()
                                                        .sorted(descendingOrder)
                                                        .collect(Collectors.toList());

    static final List<Person> alphabeticOrder = personList.stream()
            .sorted(Comparator.comparing(Person::getName))
            .collect(Collectors.toList());

    private static void printPeople(final String message, final List<Person> list){
        System.out.println();
        System.out.println(message);
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {

        //Obtains every ascii digit representation and maps it to a Char obj
        MESSAGE.chars()
                .mapToObj(ch -> (char) ch)
                .forEach(System.out::println);


        printPeople("People:", personList);
        printPeople("People by ascending order given age:", ascendingAge);
        printPeople("Reversed order by age:", descendingAge);
        printPeople("Ordered alphabetically", alphabeticOrder);


        //Using a min function of comparator fo find the youngest. Also max could be used to find the eldest.
        personList.stream()
                .min(Person::ageDifference)
                .ifPresent(person -> System.out.println("Youngest " + person));


    }
}
