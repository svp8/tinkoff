package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Tasks1to10 {

    private Tasks1to10() {
    }

    public static List<Animal> sortHeightASC(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> sortWeightDESC(List<Animal> animals, long k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight)
                .reversed())
            .limit(k)
            .toList();
    }

    public static Map<Type, Integer> countAnimalTypes(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
            ));
    }

    public static Animal longestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(o -> o.name()
                .length()))
            .orElse(null);
    }

    public static Sex findSexMajority(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .orElse(new Map.Entry<>() {
                @Override
                public Sex getKey() {
                    return null;
                }

                @Override
                public Long getValue() {
                    return null;
                }

                @Override
                public Long setValue(Long value) {
                    return null;
                }
            })
            .getKey();
    }

    public static Map<Type, Animal> heaviestByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    public static Animal oldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age)
                .reversed())
            .limit(k)
            .toList()
            .getLast();
    }

    public static Optional<Animal> heaviestAndLowerK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer countPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> ageNotEqualsPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }
}
