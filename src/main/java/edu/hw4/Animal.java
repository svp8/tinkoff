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

    public static final int FOURPAWS = 4;
    public static final int TWOPAWS = 2;
    public static final int EIGHTPAWS = 8;

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        F, M
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> FOURPAWS;
            case BIRD -> TWOPAWS;
            case FISH -> 0;
            case SPIDER -> EIGHTPAWS;
        };
    }
}
