package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class ValidationError extends IllegalArgumentException {
    private final String field;

    public ValidationError(String s, String field) {
        super(s);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        validateName(errors,animal);
        validateHeight(errors,animal);
        validateWeight(errors,animal);
        validateAge(errors,animal);
        return errors;

    }

    private static void validateName(Set<ValidationError> errors, Animal animal) {
        if (animal.name() == null) {
            errors.add(new ValidationError("Name is null", "name"));
        } else if ("".equals(animal.name())) {
            errors.add(new ValidationError("Name is empty", "name"));
        }
    }

    private static void validateWeight(Set<ValidationError> errors, Animal animal) {
        if (animal.weight() <= 0) {
            errors.add(new ValidationError("Invalid weight", "weight"));
        }
    }

    private static void validateHeight(Set<ValidationError> errors, Animal animal) {
        if (animal.height() <= 0) {
            errors.add(new ValidationError("Invalid height", "height"));
        }
    }

    private static void validateAge(Set<ValidationError> errors, Animal animal) {
        if (animal.age() < 0) {
            errors.add(new ValidationError("Invalid age", "age"));
        }
    }
}
