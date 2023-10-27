package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks11to20 {

    public static void main(String[] args) {
        List<Animal> animals = Arrays.asList(
            new Animal("namer", Animal.Type.SPIDER,
                Animal.Sex.M, 23, 3434, 523, true
            ),
            new Animal("na", Animal.Type.SPIDER,
                Animal.Sex.F, 8, 4, 7, false
            ),
            new Animal("name123fe d 3 e", Animal.Type.DOG,
                Animal.Sex.M, 2, 20, 422, true
            ),
            new Animal("name2ew fr", Animal.Type.DOG,
                Animal.Sex.F, 56, 2, -222, true
            ),
            new Animal("name2ew fr", Animal.Type.DOG,
                Animal.Sex.F, 80, -1, -22, true
            ),
            new Animal("name2ew fr", Animal.Type.DOG,
                Animal.Sex.F, 80, -1, -22, true
            ),
            new Animal("name1 33 2", Animal.Type.FISH,
                Animal.Sex.F, 32, 400, 1, true
            ),
            new Animal("nasa", Animal.Type.SPIDER,
                Animal.Sex.F, 3, 4, 7, true
            )
        );
        List<Animal> animals2 = Arrays.asList(
            new Animal("name1uy", Animal.Type.FISH,
                Animal.Sex.F, 32, 400, 3, true
            ),
            new Animal("nasa", Animal.Type.SPIDER,
                Animal.Sex.F, 3, 4, 7, true
            )
        );
        System.out.println(validateAnimalsPrettier(animals));
    }

    public static List<Animal> biteAndHigherHundred(List<Animal> animals) {
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > 100)
            .toList();
    }

    public static Integer countWeightMoreThanHeight(List<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.height() < animal.weight()).count());
    }

    public static List<Animal> nameHasMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    public static Boolean dogHigherThanK(List<Animal> animals, int k) {
        return animals.stream().anyMatch((animal) -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Integer sumWeightWithAgeFromKtoL(List<Animal> animals, int k, int l) {
        return animals.stream().filter((animal -> animal.age() >= k && animal.age() <= l)).mapToInt(Animal::weight)
            .sum();
    }

    public static List<Animal> sortByTypeSexName(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::type)
                .thenComparing(Comparator.comparing(Animal::sex))
                .thenComparing(Comparator.comparing(Animal::name)))
            .toList();
    }

    public static Boolean spidersBiteMoreOftenThanDogs(List<Animal> animals) {
        long dogs = animals.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG) && animal.bites()).count();
        long spiders = animals.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG) && animal.bites()).count();
        if (dogs == 0 || spiders == 0) {
            return false;
        }
        return spiders > dogs;

//            .collect(Collectors.toMap(Animal::type, animal -> animal.bites() ? 1 : 0, Integer::sum))
//            .entrySet();
    }

    public static Animal heaviestFish(List<Animal>... animals) {
        return Stream.of(animals).flatMap(Collection::stream).filter(animal -> animal.type().equals(Animal.Type.FISH))
            .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }

    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        return animals.stream().collect(Collectors
                .toMap(Animal::name, ValidationError::validateAnimal,
                    //Можно заменить
                    (set1, set2) -> {
                        set1.add(new ValidationError("Duplicate name", "name"));
                        return set1;
                    }
                )).entrySet().stream().filter(entry -> entry.getValue().size() != 0)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> validateAnimalsPrettier(List<Animal> animals) {
        return validateAnimals(animals).entrySet().stream().collect(
            Collectors.toMap(
                Map.Entry::getKey,
                (entry) -> entry.getValue().stream()
                    .map(ValidationError::getField)
                    .collect(Collectors.joining(","))
            )
        );
//        return animals.stream().collect(Collectors
//            .toMap(Animal::name, (animal -> ValidationError.
//                    validateAnimal(animal).stream()
//                    .map(ValidationError::getField).collect(Collectors.joining(","))
//                ), (str1,str2)->{
//                    String.join("name");
//
//                }
//            )).entrySet().stream().filter(entry -> entry.getValue().length() != 0).collect(Collectors.toMap(
//            Map.Entry::getKey,
//            Map.Entry::getValue
//        ));
    }
}
