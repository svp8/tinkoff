package edu.hw10.task2;

import edu.hw10.task1.MyClass;
import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import edu.hw7.task3.PersonDatabaseImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CacheProxyTest {
    @TempDir
    Path tempDir;

    @Test
    void fileCache() throws IOException {
        Path path = tempDir.resolve("cache.txt");
        FibCalculator fibCalculator = new FibCalculatorImpl();
        CacheProxy cacheProxy = new CacheProxy(path);
        FibCalculator proxy = cacheProxy.create(fibCalculator, fibCalculator.getClass());

        proxy.fib(5);
        long actual = proxy.fib(5);

        try {
            List<String> lines = Files.readAllLines(path);
            String line = lines.get(0);
            Assertions.assertEquals("fib:[5]=3", line);
            Assertions.assertEquals(1, lines.size());
            Assertions.assertEquals(3, actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void memoryCache() throws IOException {
        Path path = tempDir.resolve("cache.txt");
        PersonDatabaseImpl database = new PersonDatabaseImpl();
        Person person = new Person(10, "abc", "ttt", "12345678");
        CacheProxy cacheProxy = new CacheProxy(path);
        PersonDatabase proxy = cacheProxy.create(database, database.getClass());
        database.add(person);

        proxy.findByAddress("ttt");
        proxy.delete(10);
        Person actual = proxy.findByAddress("ttt");

        Assertions.assertEquals(person, actual);
        Assertions.assertEquals(1, cacheProxy.getMemoryCache().size());
    }

    @Test
    @DisplayName("Should throw exception if method persisted and return type is invalid")
    void invalidReturnType() throws IOException {
        Path path = tempDir.resolve("cache.txt");
        PersonDatabaseImpl database = new PersonDatabaseImpl();
        Person person = new Person(10, "abc", "ttt", "12345678");
        CacheProxy cacheProxy = new CacheProxy(path);
        PersonDatabase proxy = cacheProxy.create(database, database.getClass());
        database.add(person);
        database.add(person);

        Assertions.assertThrows(IllegalArgumentException.class, () -> proxy.findByName("abc2"));

    }
}
