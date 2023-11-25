package edu.hw7.task3;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class PersonDatabaseLock implements PersonDatabase {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final HashMap<Integer, Person> idCache = new HashMap<>();
    private final HashMap<String, Person> nameCache = new HashMap<>();
    private final HashMap<String, Person> addressCache = new HashMap<>();
    private final HashMap<String, Person> phoneCache = new HashMap<>();

    @Override
    public void add(Person person) {
        try {
            lock.writeLock().lock();
            idCache.put(person.id(), person);
            nameCache.put(person.name(), person);
            addressCache.put(person.address(), person);
            phoneCache.put(person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        try {
            lock.writeLock().lock();
            if (idCache.containsKey(id)) {
                Person person = idCache.get(id);
                nameCache.remove(person.name());
                addressCache.remove(person.address());
                phoneCache.remove(person.phoneNumber());
                idCache.remove(id);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByName(String name) {
        try {
            lock.readLock().lock();
            Person person = nameCache.get(name);
            return person;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        try {
            lock.readLock().lock();
            Person person = addressCache.get(address);
            return person;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        try {
            lock.readLock().lock();
            Person person = phoneCache.get(phone);
            return person;
        } finally {
            lock.readLock().unlock();
        }
    }
}
