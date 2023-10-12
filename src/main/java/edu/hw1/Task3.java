package edu.hw1;

public final class Task3 {
    private Task3() {

    }

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1.length == 0 || a2.length == 0) {
            throw new NullPointerException();
        }
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i : a1) {
            min1 = Math.min(min1, i);
            max1 = Math.max(max1, i);
        }
        for (int i : a2) {
            min2 = Math.min(min2, i);
            max2 = Math.max(max2, i);
        }
        return min1 > min2 && max1 < max2;
    }
}
