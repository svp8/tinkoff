package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.util.Map;
import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RandomObjectGeneratorTest {

    RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

    @Test
    void nextObjectRecord() throws NoSuchMethodException {
        assertEquals(RecordImpl.class, randomObjectGenerator.nextObject(RecordImpl.class).getClass());
    }

    @Test
    void nextObjectMyClass() throws NoSuchMethodException {

        assertEquals(MyClass.class, randomObjectGenerator.nextObject(MyClass.class).getClass());
    }

    @Test
    void nextObjectInvalid() throws NoSuchMethodException {

        assertThrows(IllegalArgumentException.class, () -> randomObjectGenerator.nextObject(DiskMap.class));
    }

    @Test
    void nextObjectMethod() throws NoSuchMethodException {

        assertEquals(RecordImpl.class, randomObjectGenerator.nextObject(RecordImpl.class, "create").getClass());
        assertEquals(MyClass.class, randomObjectGenerator.nextObject(MyClass.class, "create").getClass());
    }

    @Test
    void nextObjectMethodInvalidName() throws NoSuchMethodException {
        assertThrows(NoSuchMethodException.class, () -> randomObjectGenerator.nextObject(RecordImpl.class, "create1"));
    }

    @Test
    void nextObjectMethodInvalidType() throws NoSuchMethodException {
        assertThrows(
            IllegalArgumentException.class,
            () -> randomObjectGenerator.nextObject(RecordImpl.class, "createWithDate")
        );
    }

    @Test
    void minMaxMethodRecord() throws NoSuchMethodException {

        RecordImpl recordImpl = randomObjectGenerator.nextObject(RecordImpl.class, "createMinMax");
        Assertions.assertTrue(recordImpl.amount() >= 1 && recordImpl.amount() < 5);
    }

    @Test
    void minMaxMethodMyClass() throws NoSuchMethodException {

        MyClass actual = randomObjectGenerator.nextObject(MyClass.class, "createMinMax");
        Assertions.assertTrue(actual.getNumber() >= 1 && actual.getNumber() < 5);
    }

    @Test
    void notNullMethod() throws NoSuchMethodException {

        MyClass actual = randomObjectGenerator.nextObject(MyClass.class, "createWithName");
        assertEquals("temp", actual.getName());
    }

    @Test
    void notNullConstructor() throws NoSuchMethodException {

        MyClass actual = randomObjectGenerator.nextObject(MyClass.class);
        assertEquals("temp", actual.getName());
    }
}
