package edu.project4.utils;

import java.util.List;
import java.util.Random;

public final class RenderUtils {
    private RenderUtils() {
    }

    public static <T> T getRandomElement(List<T> list, Random random) {
        return list.get(random.nextInt(list.size()));
    }

}
