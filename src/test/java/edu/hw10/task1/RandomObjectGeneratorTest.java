package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomObjectGeneratorTest {
    Map<Class<?>, Constructor<?>> construtorMap = Map.of(
        RecordImpl.class,
        RecordImpl.class.getConstructor(int.class),
        MyClass.class,
        MyClass.class.getConstructor(String.class, int.class));

    RandomObjectGeneratorTest() throws NoSuchMethodException {
    }

    @Test
    void nextObjectRecord() throws NoSuchMethodException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(construtorMap);
        assertEquals(randomObjectGenerator.nextObject(RecordImpl.class).getClass(), RecordImpl.class);
    }

    @Test
    void nextObjectMyClass() throws NoSuchMethodException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(construtorMap);
        assertEquals(randomObjectGenerator.nextObject(MyClass.class).getClass(), MyClass.class);
    }

    @Test
    void nextObjectMethod() throws NoSuchMethodException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(construtorMap);
        assertEquals(randomObjectGenerator.nextObject(RecordImpl.class, "create").getClass(), RecordImpl.class);
        assertEquals(randomObjectGenerator.nextObject(MyClass.class, "create").getClass(), MyClass.class);
    }

    @Test
    void minMaxMethodRecord() throws NoSuchMethodException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(construtorMap);
        RecordImpl recordImpl = randomObjectGenerator.nextObject(RecordImpl.class, "createMinMax");
        Assertions.assertTrue(recordImpl.amount() >= 1 && recordImpl.amount() < 5);
    }

    @Test
    void minMaxMethodMyClass() throws NoSuchMethodException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(construtorMap);
        MyClass actual = randomObjectGenerator.nextObject(MyClass.class, "createMinMax");
        Assertions.assertTrue(actual.getNumber()>=1&&actual.getNumber()<5);
    }
    @Test
    void notNullMethod() throws NoSuchMethodException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(construtorMap);
        MyClass actual = randomObjectGenerator.nextObject(MyClass.class, "createWithName");
        assertEquals("temp", actual.getName());
    }
    @Test
    void notNullConstructor() throws NoSuchMethodException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(construtorMap);
        MyClass actual = randomObjectGenerator.nextObject(MyClass.class);
        assertEquals("temp", actual.getName());
    }
}
