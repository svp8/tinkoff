package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Tasks11to20Test {

    static List<Animal> animals;

    @BeforeAll static void before() {
        animals = Arrays.asList(
            new Animal("name123fe", Animal.Type.BIRD,
                Animal.Sex.F, 2, 2, 4, true
            ),
            new Animal("abc", Animal.Type.BIRD,
                Animal.Sex.F, 56, 1, 20, true
            ),
            new Animal("xyz yy", Animal.Type.CAT,
                Animal.Sex.F, 32, 100, 1, true
            ),
            new Animal("Aiuy dse", Animal.Type.CAT,
                Animal.Sex.M, 23, 300, 500, false
            ),
            new Animal("na", Animal.Type.DOG,
                Animal.Sex.F, 8, 800, 7, true
            )
        );
    }

    @Test
    void biteAndHigherHundred() {
        Assertions.assertEquals(1, Tasks11to20.biteAndHigherHundred(animals).size());
        Assertions.assertEquals("na", Tasks11to20.biteAndHigherHundred(animals).get(0).name());
    }

    @Test
    void countWeightMoreThanHeight() {
        Assertions.assertEquals(3, Tasks11to20.countWeightMoreThanHeight(animals));
        Assertions.assertEquals(0, Tasks11to20.countWeightMoreThanHeight(new ArrayList<>()));
    }

    @Test
    void nameHasMoreThanTwoWords() {
        Assertions.assertEquals(2, Tasks11to20.nameHasMoreThanTwoWords(animals).size());
        assertTrue(Tasks11to20.nameHasMoreThanTwoWords(animals).contains(new Animal("Aiuy dse", Animal.Type.CAT,
            Animal.Sex.M, 23, 300, 500, false
        )));
    }

    @Test
    void dogHigherThanK() {
        Assertions.assertEquals(true, Tasks11to20.dogHigherThanK(animals, 100));
        Assertions.assertEquals(false, Tasks11to20.dogHigherThanK(animals, 1000));
    }

    @Test
    void sumWeightWithAgeFromKtoL() {
        Assertions.assertEquals(20, Tasks11to20.sumWeightWithAgeFromKtoL(animals, 50, 100));
        Assertions.assertEquals(521, Tasks11to20.sumWeightWithAgeFromKtoL(animals, 23, 500));
    }

    @Test
    void sortByTypeSexName() {
        List<Animal> expected = List.of(
            new Animal("abc", Animal.Type.BIRD,
                Animal.Sex.F, 56, 1, 20, true
            ),
            new Animal("name123fe", Animal.Type.BIRD,
                Animal.Sex.F, 2, 2, 4, true
            ),
            new Animal("xyz yy", Animal.Type.CAT,
                Animal.Sex.F, 32, 100, 1, true
            ),
            new Animal("Aiuy dse", Animal.Type.CAT,
                Animal.Sex.M, 23, 300, 500, false
            ),
            new Animal("na", Animal.Type.DOG,
                Animal.Sex.F, 8, 800, 7, true
            )
        );
        Assertions.assertEquals(expected, Tasks11to20.sortByTypeSexName(animals));
    }

    @Test
    void spidersBiteMoreOftenThanDogs() {
        List<Animal> list = List.of(
            new Animal("abc", Animal.Type.BIRD,
                Animal.Sex.F, 56, 1, 20, true
            ),
            new Animal("name123fe", Animal.Type.BIRD,
                Animal.Sex.F, 2, 2, 4, true
            ),
            new Animal("xyz yy", Animal.Type.DOG,
                Animal.Sex.F, 32, 100, 1, true
            ),
            new Animal("Aiuy dse", Animal.Type.DOG,
                Animal.Sex.M, 23, 300, 500, false
            ),
            new Animal("na", Animal.Type.SPIDER,
                Animal.Sex.F, 8, 800, 7, true
            )
        );
        Assertions.assertEquals(false, Tasks11to20.spidersBiteMoreOftenThanDogs(list));

    }

    @Test
    void spidersBiteMoreOftenThanDogsTrue() {
        List<Animal> list = List.of(
            new Animal("abc", Animal.Type.SPIDER,
                Animal.Sex.F, 56, 1, 20, true
            ),
            new Animal("name123fe", Animal.Type.BIRD,
                Animal.Sex.F, 2, 2, 4, true
            ),
            new Animal("xyz yy", Animal.Type.DOG,
                Animal.Sex.F, 32, 100, 1, true
            ),
            new Animal("Aiuy dse", Animal.Type.DOG,
                Animal.Sex.M, 23, 300, 500, false
            ),
            new Animal("na", Animal.Type.SPIDER,
                Animal.Sex.F, 8, 800, 7, true
            )
        );
        Assertions.assertEquals(true, Tasks11to20.spidersBiteMoreOftenThanDogs(list));
    }

    @Test
    void spidersBiteMoreOftenThanDogsLessData() {
        List<Animal> list = List.of(
            new Animal("abc", Animal.Type.SPIDER,
                Animal.Sex.F, 56, 1, 20, true
            ),
            new Animal("na", Animal.Type.SPIDER,
                Animal.Sex.F, 8, 800, 7, true
            )
        );
        Assertions.assertEquals(false, Tasks11to20.spidersBiteMoreOftenThanDogs(list));
    }

    @Test
    void heaviestFish() {
        List<Animal> list = List.of(
            new Animal("abc2", Animal.Type.FISH,
                Animal.Sex.F, 56, 1, 20, true
            ),
            new Animal("na", Animal.Type.FISH,
                Animal.Sex.F, 8, 800, 7, true
            )
        );
        List<Animal> list1 = List.of(
            new Animal("abc", Animal.Type.FISH,
                Animal.Sex.F, 56, 1, 25, true
            ),
            new Animal("na1", Animal.Type.SPIDER,
                Animal.Sex.F, 8, 800, 7, true
            )
        );
        Assertions.assertEquals("abc", Tasks11to20.heaviestFish(list, list1).name());
    }

    @Test
    void validateAnimalsName() {
        List<Animal> list = List.of(
            new Animal("", Animal.Type.FISH,
                Animal.Sex.F, 56, 1, 20, true
            ),
            new Animal(null, Animal.Type.FISH,
                Animal.Sex.F, 8, 800, 7, true
            ),
            new Animal("sab", Animal.Type.FISH,
                Animal.Sex.F, 8, 800, 7, true
            )
        );
        Assertions.assertEquals(0, Tasks11to20.validateAnimals(animals).size());
        assertTrue(Tasks11to20.validateAnimals(list).get("").contains(new ValidationError("Invalid name", "name")));
        assertTrue(Tasks11to20.validateAnimals(list).get(null).contains(new ValidationError("Invalid name", "name")));
        Assertions.assertEquals(2, Tasks11to20.validateAnimals(list).size());
    }

    @Test
    void validateAnimalsNegatives() {
        List<Animal> list = List.of(
            new Animal("a", Animal.Type.FISH,
                Animal.Sex.F, -56, 1, 20, true
            ),
            new Animal("na", Animal.Type.FISH,
                Animal.Sex.F, -8, -800, -7, true
            )
        );
        Assertions.assertEquals(2, Tasks11to20.validateAnimals(list).size());
        assertTrue(Tasks11to20.validateAnimals(list).get("a").contains(new ValidationError("Invalid age", "age")));
        assertTrue(Tasks11to20.validateAnimals(list).get("na")
            .contains(new ValidationError("Invalid height", "height")));
        assertTrue(Tasks11to20.validateAnimals(list).get("na")
            .contains(new ValidationError("Invalid weight", "weight")));
    }

    @Test
    void validateAnimalsDuplicates() {
        List<Animal> list = List.of(
            new Animal("a", Animal.Type.FISH,
                Animal.Sex.F, -56, 1, 20, true
            ),
            new Animal("a", Animal.Type.FISH,
                Animal.Sex.F, -8, -800, -7, true
            )
        );
        Assertions.assertEquals(1, Tasks11to20.validateAnimals(list).size());
        assertTrue(Tasks11to20.validateAnimals(list).get("a")
            .contains(new ValidationError("", "name")));
    }

    @Test
    void validateAnimalsPrettier() {
        List<Animal> list = List.of(
            new Animal("", Animal.Type.FISH,
                Animal.Sex.F, 56, 1, 20, true
            ),
            new Animal(null, Animal.Type.FISH,
                Animal.Sex.F, 8, 800, 7, true
            ),
            new Animal("sab", Animal.Type.FISH,
                Animal.Sex.F, 8, -800, -7, true
            )
        );
        Assertions.assertEquals(0, Tasks11to20.validateAnimalsPrettier(animals).size());
        assertTrue(Tasks11to20.validateAnimalsPrettier(list).get("").contains("name"));
        assertTrue(Tasks11to20.validateAnimalsPrettier(list).get(null).contains("name"));
        assertTrue(Tasks11to20.validateAnimalsPrettier(list).get("sab").contains("weight"));
        assertTrue(Tasks11to20.validateAnimalsPrettier(list).get("sab").contains("height"));
        Assertions.assertEquals(3, Tasks11to20.validateAnimals(list).size());
    }
}
