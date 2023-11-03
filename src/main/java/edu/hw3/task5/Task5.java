package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Task5 {
    private Task5() {
    }

    public static List<Contact> parseContacts(List<String> people, String method) {
        if (people == null) {
            return new ArrayList<>();
        }
        List<Contact> temp = new ArrayList<>();
        people.forEach((name) -> {
            if (name == null || name.trim().equals("")) {
                throw new NullPointerException("Name is empty");
            }
            String[] input = name.split(" ");
            if (input.length >= 2) {
                temp.add(new Contact(input[0], input[1]));
            } else if (input.length == 1) {
                temp.add(new Contact(input[0], null));
            }
        });
        Comparator<Contact> comparator = (temp1, temp2) -> {
            String o1 = temp1.surname();
            String o2 = temp2.surname();
            if (o1 == null) {
                o1 = temp1.name();
            }
            if (o2 == null) {
                o2 = temp2.name();
            }
            return o1.compareTo(o2);
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
