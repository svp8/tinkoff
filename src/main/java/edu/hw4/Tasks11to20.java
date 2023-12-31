package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Tasks11to20 {

    public static final int HUNDRED = 100;

    private Tasks11to20() {

    }

    public static List<Animal> biteAndHigherHundred(List<Animal> animals) {
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > HUNDRED)
            .toList();
    }

    public static Integer countWeightMoreThanHeight(List<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.height() < animal.weight())
            .count());
    }

    public static List<Animal> nameHasMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name()
                .split(" ").length > 1)
            .toList();
    }

    public static Boolean dogHigherThanK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch((animal) -> animal.type() == Type.DOG && animal.height() > k);
    }

    public static Map<Type, Integer> sumWeightWithAgeFromKtoL(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter((animal -> animal.age() >= k && animal.age() <= l))
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(
                        Animal::type,
                        Comparator.comparing(Enum::toString)
                    )
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            )
            .toList();
    }

    public static Boolean spidersBiteMoreOftenThanDogs(List<Animal> animals) {
        long dogs = animals.stream()
            .filter(animal -> animal.type()
                .equals(Type.DOG) && animal.bites())
            .count();
        long spiders = animals.stream()
            .filter(animal -> animal.type()
                .equals(Type.SPIDER) && animal.bites())
            .count();
        if (dogs == 0 || spiders == 0) {
            return false;
        }
        return spiders > dogs;
    }

    public static Animal heaviestFish(List<Animal>... animals) {
        return Stream.of(animals)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type()
                .equals(Type.FISH))
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors
                .toMap(Animal::name, ValidationError::validateAnimal,
                    //Можно заменить
                    (set1, set2) -> {
                        set1.add(new ValidationError("Duplicate name", "name"));
                        return set1;
                    }
                ))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue()
                .size() != 0)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> validateAnimalsPrettier(List<Animal> animals) {
        return validateAnimals(animals).entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    (entry) -> entry.getValue()
                        .stream()
                        .map(ValidationError::getField)
                        .collect(Collectors.joining(","))
                )
            );
    }
}
