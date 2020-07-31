package strings_comp_and_filters;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Created on 30 Jul, 2020 - 21:11
 *
 */
public class ListFiles {
    public static void main(String[] args) throws IOException {
        Files.list(Paths.get("."))
                .filter(Files::isDirectory)
                .forEach(System.out::println);
    }
}
