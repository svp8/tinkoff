package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public class MyClass {
    private final String name;
    private final int number;

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override public String toString() {
        return "MyClass{"
            + "name='" + name + '\''
            + ", number=" + number
            + '}';
    }

    public MyClass(@NotNull String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static MyClass create(String name, int number) {
        return new MyClass(name, number);
    }

    public static MyClass createWithName(@NotNull String name, int number) {
        return new MyClass(name, number);
    }

    public static MyClass createMinMax(@Max(number = 5) @Min(number = 1) int number) {
        return new MyClass(null, number);
    }
}
