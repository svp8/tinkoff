package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class Tasks1to10Test {
    static List<Animal> animals;

    @BeforeAll static void before() {
        animals = Arrays.asList(
            new Animal("name123fe", Type.BIRD,
                Sex.M, 2, 2, 4, true
            ),
            new Animal("abc", Type.BIRD,
                Sex.F, 56, 1, 2, true
            ),
            new Animal("xyz yy", Type.CAT,
                Sex.F, 32, 4, 1, true
            ),
            new Animal("Aiuy dse", Type.CAT,
                Sex.M, 23, 3, 5, true
            ),
            new Animal("na", Type.BIRD,
                Sex.F, 8, 8, 7, true
            )
        );
    }

    @Test
    void sortHeightASC() {
        Assertions.assertEquals(List.of(
            new Animal("abc", Type.BIRD,
                Sex.F, 56, 1, 2, true
            ), new Animal("name123fe", Type.BIRD,
                Sex.M, 2, 2, 4, true
            ),
            new Animal("Aiuy dse", Type.CAT,
                Sex.M, 23, 3, 5, true
            ),
            new Animal("xyz yy", Type.CAT,
                Sex.F, 32, 4, 1, true
            ),
            new Animal("na", Type.BIRD,
                Sex.F, 8, 8, 7, true
            )
        ), Tasks1to10.sortHeightASC(animals));
    }

    @Test
    void sortWeightDESC() {
        Assertions.assertEquals(3, Tasks1to10.sortWeightDESC(animals, 3).size());
        Assertions.assertEquals(
            List.of("na", "Aiuy dse", "name123fe"),
            Tasks1to10.sortWeightDESC(animals, 3).stream().map(Animal::name).collect(Collectors.toList())
        );
    }

    @Test
    void countAnimalTypes() {
        Assertions.assertEquals(2, Tasks1to10.countAnimalTypes(animals).size());
        Assertions.assertEquals(3, Tasks1to10.countAnimalTypes(animals).get(Type.BIRD));
    }

    @Test
    void longestName() {
        ArrayList<Animal> animals2 = new ArrayList<>();
        animals2.add(new Animal("samesize", Type.BIRD,
            Sex.F, 8, 8, 7, true
        ));
        animals2.add(new Animal("samesiz2", Type.BIRD,
            Sex.F, 8, 8, 7, true
        ));
        animals2.add(new Animal("same", Type.BIRD,
            Sex.F, 8, 8, 7, true
        ));
        Assertions.assertEquals("samesize", Tasks1to10.longestName(animals2).name());
    }

    @Test
    void findSexMajority() {
        Assertions.assertEquals(Sex.F, Tasks1to10.findSexMajority(animals));
        assertNull(Tasks1to10.findSexMajority(new ArrayList<>()));
    }

    @Test
    void heaviestByType() {
        Assertions.assertEquals(2, Tasks1to10.heaviestByType(animals).size());
        Assertions.assertEquals("na", Tasks1to10.heaviestByType(animals).get(Type.BIRD).name());
    }

    @Test
    void oldestAnimal() {
        Assertions.assertEquals("abc", Tasks1to10.oldestAnimal(animals, 1).name());
        Assertions.assertEquals("name123fe", Tasks1to10.oldestAnimal(animals, 100).name());
    }

    @Test
    void heaviestAndLowerK() {
        Assertions.assertEquals("Aiuy dse", Tasks1to10.heaviestAndLowerK(animals, 5).get().name());
        assertTrue(Tasks1to10.heaviestAndLowerK(animals, -5).isEmpty());
    }

    @Test
    void countPaws() {
        Assertions.assertEquals(14, Tasks1to10.countPaws(animals));
        Assertions.assertEquals(0, Tasks1to10.countPaws(new ArrayList<>()));
    }

    @Test
    void ageNotEqualsPaws() {
        Assertions.assertEquals(4, Tasks1to10.ageNotEqualsPaws(animals).size());
        Assertions.assertEquals(0, Tasks1to10.ageNotEqualsPaws(
            List.of(new Animal("same", Type.SPIDER,
                Sex.F, 8, 8, 7, true
            ))).size());
    }
}
