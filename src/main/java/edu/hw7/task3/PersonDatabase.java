package edu.hw7.task3;

import edu.hw10.task2.Cache;
import org.jetbrains.annotations.Nullable;

public interface PersonDatabase {
    void add(Person person);

    void delete(int id);

    @Nullable @Cache(persist = true) Person findByName(String name);

    @Nullable @Cache(persist = false) Person findByAddress(String address);

    @Nullable Person findByPhone(String phone);
}
