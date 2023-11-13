package edu.project2Labyrinth;

import edu.project2Labyrinth.generators.HuntAndKillGenerator;
import edu.project2Labyrinth.generators.RandomizedDFSGenerator;
import edu.project2Labyrinth.solvers.BFSSolver;
import edu.project2Labyrinth.solvers.DFSSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Test
    void chooseGenerator() {
        assertNull(Console.chooseGenerator("0"));
        assertNull(Console.chooseGenerator("sdvfd"));
        assertEquals(RandomizedDFSGenerator.class, Console.chooseGenerator("1").getClass());
        assertEquals(HuntAndKillGenerator.class, Console.chooseGenerator("2").getClass());
    }

    @Test
    void chooseSolver() {
        assertNull(Console.chooseSolver("0"));
        assertNull(Console.chooseSolver("sdvfd"));
        assertEquals(DFSSolver.class, Console.chooseSolver("1").getClass());
        assertEquals(BFSSolver.class, Console.chooseSolver("2").getClass());
    }

    @Test
    void validateCoordinateInput() {
        int height = 5;
        int width = 6;
        String[] valid = {"1", "1"};
        assertTrue(Console.validateCoordinateInput(valid, height, width));
    }

    private static Stream<Arguments> validateCoordinateInputWithWrongValues() {
        return Stream.of(
            Arguments.of((Object) new String[] {"a", "1"}),
            Arguments.of((Object) new String[] {"a", "1"}),
            Arguments.of((Object) new String[] {"a", "1", "s"}),
            Arguments.of((Object) new String[] {"3", "10"}),
            Arguments.of((Object) new String[] {"3"})
        );
    }

    @ParameterizedTest(name = "#{index} - Проверка неправильных значений координаты : {0}")
    @MethodSource
    void validateCoordinateInputWithWrongValues(String[] input) {
        int height = 5;
        int width = 6;
        assertFalse(Console.validateCoordinateInput(input, height, width));
    }

    private static Stream<Arguments> validateDimensionsInputWrong() {
        return Stream.of(
            Arguments.of((Object) new String[] {"a", "1"}),
            Arguments.of((Object) new String[] {"a", "1"}),
            Arguments.of((Object) new String[] {"a", "1", "s"}),
            Arguments.of((Object) new String[] {"10"}),
            Arguments.of((Object) new String[] {"0", "4"})
        );
    }

    @ParameterizedTest
    @MethodSource
    void validateDimensionsInputWrong(String[] input) {
        assertFalse(Console.validateDimensionsInput(input));
    }

    @Test
    void validateDimensionsInput() {
        String[] valid = new String[] {"1", "1"};
        assertTrue(Console.validateDimensionsInput(valid));
    }
}
