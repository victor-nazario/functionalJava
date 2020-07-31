package oop_and_functional.chaining_behaviour;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created on 31 Jul, 2020 - 03:43
 *
 */
public class Camera {
    private Function<Color, Color> filter;

    public Camera() {
        setFilters();
    }

    public Color capture(final Color inputColor) {
        final Color processedColor = filter.apply(inputColor);
        return processedColor;
    }

    @SafeVarargs
    public final void setFilters(final Function<Color, Color>... filters) {
        filter = Stream.of(filters)
                        .reduce(Function::compose)
                        .orElseGet(Function::identity);
    }


    public static void main(String[] args) {
        final Camera camera = new Camera();

        final Consumer<String> printCaptured = (filterInfo) -> System.out.printf("with %s: %S%n",
                filterInfo, camera.capture(new Color(200, 100, 200)));

        camera.setFilters(Color::brighter, Color::darker);
        printCaptured.accept("Brighter");
    }

}
