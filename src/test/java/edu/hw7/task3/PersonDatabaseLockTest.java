package edu.hw7.task3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class PersonDatabaseLockTest {

    @Test
    void add() throws InterruptedException {
        PersonDatabase database = new PersonDatabaseLock();
        Person person = new Person(10, "abc", "ttt", "12345678");
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> database.add(person));
            threads.add(thread);
            thread.start();
        }
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                boolean flag = findByAll(person, database);
                assertTrue(flag);
            });
            threads.add(thread);
            thread.start();
        }

    }

    boolean findByAll(Person person, PersonDatabase database) {
        Person address = database.findByAddress(person.address());
        Person name = database.findByName(person.name());
        Person phone = database.findByPhone(person.phoneNumber());
        if (address == null && name == null && phone == null) {
            return true;
        } else {
            return address != null && name != null && phone != null;
        }
    }
}
