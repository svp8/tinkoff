package edu.hw10.task2;

import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import edu.hw7.task3.PersonDatabaseImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CacheProxyTest {
    public static final Path path = Path.of("src/main/resources/cache");
    @BeforeAll
    static void delete(){
        try{
            Files.delete(path);
        }catch (Exception ignored){

        }
    }
    @AfterEach
    void deleteCache(){
        try{
            Files.delete(path);
        }catch (Exception ignored){

        }
    }
    @Test
    void create() {
        FibCalculator fibCalculator=new FibCalculatorImpl();
        FibCalculator proxy=CacheProxy.create(fibCalculator,fibCalculator.getClass());

        proxy.fib(5);

        try(BufferedReader reader=new BufferedReader(new FileReader(path.toFile()))){
            String line=reader.readLine();
            Assertions.assertEquals("fib - [5] = 3",line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void databaseProxy() {
        PersonDatabaseImpl database = new PersonDatabaseImpl();
        Person person = new Person(10, "abc", "ttt", "12345678");
        PersonDatabase proxy=CacheProxy.create(database,database.getClass());
        database.add(person);
        database.add(person);

        proxy.findByName("abc");
        proxy.findByName("abc2");

        try(BufferedReader reader=new BufferedReader(new FileReader(path.toFile()))){
            String line=reader.readLine();
            Assertions.assertEquals("findByName - [abc] = Person[id=10, name=abc, address=ttt, phoneNumber=12345678]",line);
            line=reader.readLine();
            Assertions.assertEquals("findByName - [abc2] = null",line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
