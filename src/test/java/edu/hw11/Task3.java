package edu.hw11;

import edu.hw11.task3.Appender;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class Task3 {
    @Test
    void create()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", long.class, ACC_PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(new Appender()))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        long[] expected = new long[] {0L, 1L, 1L, 2L, 3L, 5L};
        Method method = dynamicType.getMethod("fib", int.class);
        Object instance = dynamicType.getDeclaredConstructor().newInstance();

        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], method.invoke(instance, i + 1));
        }
    }

    long fib(int n) {
        if (n <= 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

}

//
//
//  // access flags 0x0
//  fib(I)J
//    // parameter  n
//   L0
//    LINENUMBER 21 L0
//    ILOAD 1
//    ICONST_1
//    IF_ICMPGT L1
//   L2
//    LINENUMBER 22 L2
//    LCONST_0
//    LRETURN
//   L1
//    LINENUMBER 23 L1
//   FRAME SAME
//    ILOAD 1
//    ICONST_2
//    IF_ICMPNE L3
//   L4
//    LINENUMBER 24 L4
//    LCONST_1
//    LRETURN
//   L3
//    LINENUMBER 26 L3
//   FRAME SAME
//    ALOAD 0
//    ILOAD 1
//    ICONST_1
//    ISUB
//    INVOKEVIRTUAL edu/hw11/Task3.fib (I)J
//    ALOAD 0
//    ILOAD 1
//    ICONST_2
//    ISUB
//    INVOKEVIRTUAL edu/hw11/Task3.fib (I)J
//    LADD
//    LRETURN
//   L5
//    LOCALVARIABLE this Ledu/hw11/Task3; L0 L5 0
//    LOCALVARIABLE n I L0 L5 1
//    MAXSTACK = 5
//    MAXLOCALS = 2
//}
