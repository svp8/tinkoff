package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Task5 {
    private Task5() {
    }

    public static List<String> parseContacts(List<String> people, String method) {
        if (people == null) {
            return new ArrayList<String>();
        }

        List<String> temp = new ArrayList<>(people);
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String temp1, String temp2) {
                String o1 = temp1;
                String o2 = temp2;
                if (o1 == null || o2 == null) {
                    throw new NullPointerException("Element is null");
                }
                String[] name = o1.split(" ");
                if (name.length >= 2) {
                    o1 = name[1];
                } else if (name.length == 1) {
                    o1 = name[0];
                }
                name = o2.split(" ");
                if (name.length >= 2) {
                    o2 = name[1];
                } else if (name.length == 1) {
                    o2 = name[0];
                }
                return o1.compareTo(o2);
            }
        };
        if (method.equals("ASC")) {
            temp.sort(comparator);
        } else if (method.equals("DESC")) {
            temp.sort(comparator.reversed());
        } else {
            throw new IllegalArgumentException("Invalid method");
        }
        return temp;
    }
}
