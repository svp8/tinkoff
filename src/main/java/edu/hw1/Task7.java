package edu.hw1;

public final class Task7 {
    public int rotateRight(int num, int target) {
        String binary = Integer.toBinaryString(num);
        int target1 = target;
        while (target1 != 0) {
            String temp = binary.substring(0, binary.length() - 1);
            temp = binary.substring(binary.length() - 1) + temp;
            binary = temp;
            target1--;
        }
        return Integer.parseInt(binary, 2);
    }

    public int rotateLeft(int num, int target) {
        String binary = Integer.toBinaryString(num);
        int target1 = target;
        while (target1 != 0) {
            String temp = binary.substring(1);
            temp = temp + binary.charAt(0);
            binary = temp;
            target1--;
        }
        return Integer.parseInt(binary, 2);
    }
}
