package edu.hw7.task3;

import java.util.HashMap;
import org.jetbrains.annotations.Nullable;

public class PersonDatabaseImpl implements PersonDatabase {
    private final HashMap<Integer, Person> idCache = new HashMap<>();
    private final HashMap<String, Person> nameCache = new HashMap<>();
    private final HashMap<String, Person> addressCache = new HashMap<>();
    private final HashMap<String, Person> phoneCache = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        delete(person.id());
        idCache.put(person.id(), person);
        nameCache.put(person.name(), person);
        addressCache.put(person.address(), person);
        phoneCache.put(person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        if (idCache.containsKey(id)) {
            Person person = idCache.get(id);
            nameCache.remove(person.name());
            addressCache.remove(person.address());
            phoneCache.remove(person.phoneNumber());
            idCache.remove(id);
        }
    }

    @Override
    public synchronized @Nullable Person findByName(String name) {
        return nameCache.get(name);

    }

    @Override
    public synchronized @Nullable Person findByAddress(String address) {
        return addressCache.get(address);
    }

    @Override
    public synchronized @Nullable Person findByPhone(String phone) {
        return phoneCache.get(phone);
    }
}
