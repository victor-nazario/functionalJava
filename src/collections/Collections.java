package collections;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created on 26 Jul, 2020 - 15:15
 *
 * @author Victor Nazario 
 */
public class Collections {
    static final List<String> names = Arrays.asList("James", "Juan", "Pedro", "Kev", "Lazu", "Hermenegildo");

   public static Predicate<String> startsWithLetter(final String letter) {
       return name -> name.startsWith(letter);
   }

    public static void main(String[] args) {
        final Optional<String> found = names.stream()
                .filter(startsWithLetter("Z"))
                .findFirst();

        System.out.println(found.orElse("No name starting with Z found."));

        final String longestName = names.stream()
                                                  .reduce("Juan", (name1, name2) -> name1.length() > name2.length() ? name1 : name2);

        System.out.println(longestName);

        //Join the list in a single line
        final String joined = names.stream()
                                   .map(String::toUpperCase)
                                   .collect(Collectors.joining(", "));

        System.out.println(joined);

    }
}
