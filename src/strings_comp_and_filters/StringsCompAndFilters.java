package strings_comp_and_filters;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
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

    //This is a thread safe collection operation on the array list instance created by the collect.
    static final List<Person> peopleOlderThan20 = personList
            .stream()
            .filter(person -> person.getAge()>20)
            .collect(Collectors.toList()); //This expression replaces manually adding supply, etc. E.g ArrayList::new, ArrayList::add

    //First groups the person list by age, once bucketed it will map given the name into a list on the bucket.
    //This is to say that grouping can take several criteria. in this example we first ordered by age. Then we wanted
    //to list them by name inside the value of the pair.
    static final Map<Integer, List<String>> byNameAndAge = personList
            .stream()
            .collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())));


    //This will create buckets given each letter and will select the element given the age of the person
    static Comparator<Person> byAge = Comparator.comparing(Person::getAge);

    static final Map<Character, Optional<Person>> byAgeAndLetter = personList
            .stream()
            .collect(Collectors.groupingBy(person -> person.getName().charAt(0), Collectors.reducing(BinaryOperator.maxBy(byAge))));



    static final Function<Person, Integer> funcByAge = Person::getAge;
    static final Function<Person, String> byName = Person::getName;


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



        //Only function is to print the different list created by different operations.
        printPeople("People:", personList);
        printPeople("People by ascending order given age:", ascendingAge);
        printPeople("Reversed order by age:", descendingAge);
        printPeople("Ordered alphabetically", alphabeticOrder);
        printPeople("Only people older than twenty", peopleOlderThan20);

        //Using a min function of comparator fo find the youngest. Also max could be used to find the eldest.
        personList.stream()
                .min(Person::ageDifference)
                .ifPresent(person -> System.out.println("Youngest " + person));


        System.out.println();

        //Comparing based on more than one condition.
        personList.stream()
                .sorted(Comparator.comparing(funcByAge).thenComparing(byName))
                .forEach(System.out::println);


    }
}
