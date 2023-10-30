package edu.hw3.task2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public final class Task2 {
    private Task2() {
    }

    public static ArrayList<String> clusterize(String string) {
        Deque<Character> deque = new ArrayDeque<>();
        StringBuilder stringBuilder = new StringBuilder(string.length());
        ArrayList<String> clusters = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            char temp = string.charAt(i);
            if (temp == '(') {
                deque.offerFirst(temp);
                stringBuilder.append(temp);
            } else if (temp == ')') {
                deque.pollFirst();
                stringBuilder.append(temp);
            }
            if (deque.isEmpty()) {
                clusters.add(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.length());
            }
        }

        return clusters;
    }
}
