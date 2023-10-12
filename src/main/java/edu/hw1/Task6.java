package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public final class Task6 {
    private Task6() {

    }

    private static final int KAPREKAR_NUMBER = 6174;
    private static final int DECIMAL_FORM = 10;

    public static int countK(int num, int count) {
        if (num == KAPREKAR_NUMBER) {
            return count;
        }
        int temp = num;
        ArrayList<Integer> array = new ArrayList<>();
        do {
            array.add(temp % DECIMAL_FORM);
            temp /= DECIMAL_FORM;
        } while (temp > 0);
        Collections.sort(array);
        ArrayList<Integer> array2 = new ArrayList<>(array);
        array2.sort(Collections.reverseOrder());
        int num1 = 0;
        int num2 = 0;
        for (int i = array.size() - 1; i >= 0; i--) {
            num1 += array.get(i) * Math.pow(DECIMAL_FORM, i);
            num2 += array2.get(i) * Math.pow(DECIMAL_FORM, i);
        }
        int result = num1 - num2;
        return countK(result, count + 1);
    }
}
