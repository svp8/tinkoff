package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tasks {

    public static void main(String[] args) {
        List<Animal> animals = Arrays.asList(
            new Animal("name123fe", Animal.Type.BIRD,
                Animal.Sex.M, 2, 2, 4, true
            ),
            new Animal("name2ew", Animal.Type.BIRD,
                Animal.Sex.F, 56, 1, 2, true
            ),
            new Animal("name1", Animal.Type.CAT,
                Animal.Sex.F, 32, 4, 1, true
            ),
            new Animal("namer", Animal.Type.CAT,
                Animal.Sex.M, 23, 3, 5, true
            ),
            new Animal("na", Animal.Type.BIRD,
                Animal.Sex.F, 8, 8, 7, true
            )
        );
        System.out.println(ageNotEqualsPaws(animals));
    }

    public static List<Animal> sortHeightASC(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> sortWeightDESC(List<Animal> animals, long k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Integer> countAnimalTypes(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(
            Animal::type,
            Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
        ));
    }

    public static Animal longestName(List<Animal> animals) {
        return animals.stream().max((o1, o2) -> Integer.compare(o1.name().length(), o2.name().length())).orElse(null);
    }

    public static Animal.Sex findSexMajority(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting())).entrySet().stream()
            .max(Map.Entry.comparingByValue()).orElse(null).getKey();
    }

    public static Map<Animal.Type, Animal> heaviestByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    public static Animal oldestAnimal(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::age).reversed()).limit(k).toList().getLast();
    }
    public static Optional<Animal> heaviestAndLowerK(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height()<k).max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer countPaws(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> ageNotEqualsPaws(List<Animal> animals) {
        return animals.stream().filter(animal->animal.paws()!=animal.age()).toList();
    }
}
