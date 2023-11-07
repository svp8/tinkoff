package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Tasks11to20Test {

    static List<Animal> animals;

    @BeforeAll static void before() {
        animals = Arrays.asList(
            new Animal("name123fe", Type.BIRD,
                Sex.F, 2, 2, 4, true
            ),
            new Animal("abc", Type.BIRD,
                Sex.F, 56, 1, 20, true
            ),
            new Animal("xyz yy", Type.CAT,
                Sex.F, 32, 100, 1, true
            ),
            new Animal("Aiuy dse", Type.CAT,
                Sex.M, 23, 300, 500, false
            ),
            new Animal("na", Type.DOG,
                Sex.F, 8, 800, 7, true
            )
        );
    }

    @Test
    void biteAndHigherHundred() {
        List<Animal> actual = Tasks11to20.biteAndHigherHundred(animals);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("na", actual.get(0).name());
    }

    @Test
    void countWeightMoreThanHeight() {
        Assertions.assertEquals(3, Tasks11to20.countWeightMoreThanHeight(animals));
        Assertions.assertEquals(0, Tasks11to20.countWeightMoreThanHeight(new ArrayList<>()));
    }

    @Test
    void nameHasMoreThanTwoWords() {
        Assertions.assertEquals(
            2,
            Tasks11to20.nameHasMoreThanTwoWords(animals)
                .size()
        );
        assertTrue(Tasks11to20.nameHasMoreThanTwoWords(animals)
            .contains(new Animal("Aiuy dse", Type.CAT,
                Sex.M, 23, 300, 500, false
            )));
    }

    @Test
    void dogHigherThanK() {
        Assertions.assertEquals(true, Tasks11to20.dogHigherThanK(animals, 100));
        Assertions.assertEquals(false, Tasks11to20.dogHigherThanK(animals, 1000));
    }

    @Test
    void sumWeightWithAgeFromKtoL() {
        Assertions.assertEquals(2, Tasks11to20.sumWeightWithAgeFromKtoL(animals, 20, 100).size());
        Assertions.assertEquals(1, Tasks11to20.sumWeightWithAgeFromKtoL(animals, 23, 35).size());
        Assertions.assertEquals(501, Tasks11to20.sumWeightWithAgeFromKtoL(animals, 23, 35).get(Type.CAT));
    }

    @Test
    void sortByTypeSexName() {
        List<Animal> expected = List.of(
            new Animal("abc", Type.BIRD,
                Sex.F, 56, 1, 20, true
            ),
            new Animal("name123fe", Type.BIRD,
                Sex.F, 2, 2, 4, true
            ),
            new Animal("xyz yy", Type.CAT,
                Sex.F, 32, 100, 1, true
            ),
            new Animal("Aiuy dse", Type.CAT,
                Sex.M, 23, 300, 500, false
            ),
            new Animal("na", Type.DOG,
                Sex.F, 8, 800, 7, true
            )
        );
        List<Animal> actual = Tasks11to20.sortByTypeSexName(animals);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void spidersBiteMoreOftenThanDogs() {
        List<Animal> list = List.of(
            new Animal("abc", Type.BIRD,
                Sex.F, 56, 1, 20, true
            ),
            new Animal("name123fe", Type.BIRD,
                Sex.F, 2, 2, 4, true
            ),
            new Animal("xyz yy", Type.DOG,
                Sex.F, 32, 100, 1, true
            ),
            new Animal("Aiuy dse", Type.DOG,
                Sex.M, 23, 300, 500, false
            ),
            new Animal("na", Type.SPIDER,
                Sex.F, 8, 800, 7, true
            )
        );

        boolean actual = Tasks11to20.spidersBiteMoreOftenThanDogs(list);
        assertFalse(actual);

    }

    @Test
    void spidersBiteMoreOftenThanDogsTrue() {
        List<Animal> list = List.of(
            new Animal("abc", Type.SPIDER,
                Sex.F, 56, 1, 20, true
            ),
            new Animal("name123fe", Type.BIRD,
                Sex.F, 2, 2, 4, true
            ),
            new Animal("xyz yy", Type.DOG,
                Sex.F, 32, 100, 1, true
            ),
            new Animal("Aiuy dse", Type.DOG,
                Sex.M, 23, 300, 500, false
            ),
            new Animal("na", Type.SPIDER,
                Sex.F, 8, 800, 7, true
            )
        );
        boolean actual = Tasks11to20.spidersBiteMoreOftenThanDogs(list);
        assertTrue(actual);
    }

    @Test
    void spidersBiteMoreOftenThanDogsLessData() {
        List<Animal> list = List.of(
            new Animal("abc", Type.SPIDER,
                Sex.F, 56, 1, 20, true
            ),
            new Animal("na", Type.SPIDER,
                Sex.F, 8, 800, 7, true
            )
        );
        boolean actual = Tasks11to20.spidersBiteMoreOftenThanDogs(list);
        assertFalse(actual);
    }

    @Test
    void heaviestFish() {
        List<Animal> list = List.of(
            new Animal("abc2", Type.FISH,
                Sex.F, 56, 1, 20, true
            ),
            new Animal("na", Type.FISH,
                Sex.F, 8, 800, 7, true
            )
        );
        List<Animal> list1 = List.of(
            new Animal("abc", Type.FISH,
                Sex.F, 56, 1, 25, true
            ),
            new Animal("na1", Type.SPIDER,
                Sex.F, 8, 800, 7, true
            )
        );
        String actualName = Tasks11to20.heaviestFish(list, list1).name();
        Assertions.assertEquals("abc", actualName);
    }

    @Test
    void validateAnimalsName() {
        List<Animal> listWithExceptions = List.of(
            new Animal("", Type.FISH,
                Sex.F, 56, 1, 20, true
            ),
            new Animal(null, Type.FISH,
                Sex.F, 8, 800, 7, true
            ),
            new Animal("sab", Type.FISH,
                Sex.F, 8, 800, 7, true
            )
        );
        Assertions.assertEquals(0, Tasks11to20.validateAnimals(animals).size());

        Map<String, Set<ValidationError>> exceptions = Tasks11to20.validateAnimals(listWithExceptions);
        Assertions.assertEquals(2, exceptions.size());
        assertTrue(exceptions
            .get("")
            .contains(new ValidationError("Invalid name", "name")));
        assertTrue(exceptions
            .get(null)
            .contains(new ValidationError("Invalid name", "name")));

    }

    @Test
    void validateAnimalsNegatives() {
        List<Animal> list = List.of(
            new Animal("a", Type.FISH,
                Sex.F, -56, 1, 20, true
            ),
            new Animal("na", Type.FISH,
                Sex.F, -8, -800, -7, true
            )
        );
        Map<String, Set<ValidationError>> exceptions = Tasks11to20.validateAnimals(list);
        Assertions.assertEquals(2, exceptions.size());
        assertTrue(exceptions.get("a")
            .contains(new ValidationError("Invalid age", "age")));
        assertTrue(exceptions.get("na")
            .contains(new ValidationError("Invalid height", "height")));
        assertTrue(exceptions.get("na")
            .contains(new ValidationError("Invalid weight", "weight")));
    }

    @Test
    void validateAnimalsDuplicates() {
        List<Animal> list = List.of(
            new Animal("a", Type.FISH,
                Sex.F, -56, 1, 20, true
            ),
            new Animal("a", Type.FISH,
                Sex.F, -8, -800, -7, true
            )
        );
        Map<String, Set<ValidationError>> exceptions = Tasks11to20.validateAnimals(list);
        Assertions.assertEquals(1, exceptions.size());
        assertTrue(exceptions.get("a").contains(new ValidationError("", "name")));
    }

    @Test
    void validateAnimalsPrettier() {
        List<Animal> list = List.of(
            new Animal("", Type.FISH,
                Sex.F, 56, 1, 20, true
            ),
            new Animal(null, Type.FISH,
                Sex.F, 8, 800, 7, true
            ),
            new Animal("sab", Type.FISH,
                Sex.F, 8, -800, -7, true
            )
        );

        Assertions.assertEquals(0, Tasks11to20.validateAnimalsPrettier(animals).size());

        Map<String, String> exceptions = Tasks11to20.validateAnimalsPrettier(list);
        Assertions.assertEquals(3, exceptions.size());
        assertTrue(exceptions
            .get("")
            .contains("name"));
        assertTrue(exceptions
            .get(null)
            .contains("name"));
        assertTrue(exceptions
            .get("sab")
            .contains("weight"));
        assertTrue(exceptions
            .get("sab")
            .contains("height"));

    }
}
