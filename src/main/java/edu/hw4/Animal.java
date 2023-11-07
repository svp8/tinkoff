package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {

    public static final int FOUR_PAWS = 4;
    public static final int TWO_PAWS = 2;
    public static final int EIGHT_PAWS = 8;


    public int paws() {
        return switch (type) {
            case CAT, DOG -> FOUR_PAWS;
            case BIRD -> TWO_PAWS;
            case FISH -> 0;
            case SPIDER -> EIGHT_PAWS;
        };
    }
}
