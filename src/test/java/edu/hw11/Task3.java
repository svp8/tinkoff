package edu.hw11;

import edu.hw11.task3.Appender;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.objectweb.asm.Opcodes.*;

public class Task3 {
        @Test
    void create()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            Class<?> dynamicType= new ByteBuddy()
                .subclass(Object.class)
                .defineMethod("fib",long.class).intercept(new Implementation.Simple(new Appender()))
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
            System.out.println(Arrays.toString(dynamicType.getDeclaredMethods()));
//            Method method=dynamicType.getDeclaredMethod("fib",int.class);
//            long result= (long) method.invoke(null,5);
//            System.out.println(result);
    }
    public void checkAndSetF(int f) {
        if (f >= 0) {
            int af = f;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Test
    void create(MethodVisitor methodVisitor) {
//        MethodVisitor methodVisitor;
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitInsn(ICONST_1);
        Label l1 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPGT, l1);
        Label l2 = new Label();

        methodVisitor.visitLabel(l2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitInsn(LRETURN);

        methodVisitor.visitLabel(l1);
        methodVisitor.visitFrame(F_SAME,0,null,0,null);
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitInsn(ICONST_2);
        Label l3 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPNE, l3);
        Label l4 = new Label();

        methodVisitor.visitLabel(l4);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitInsn(LRETURN);

        methodVisitor.visitLabel(l3);
        methodVisitor.visitFrame(F_SAME,0,null,0,null);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitInsn(ISUB);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "edu/hw11/Task3", "fib", "(I)J", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitInsn(ICONST_2);
        methodVisitor.visitInsn(ISUB);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "edu/hw11/Task3", "fib", "(I)J", false);
        methodVisitor.visitInsn(LADD);
        methodVisitor.visitInsn(LRETURN);
        methodVisitor.visitMaxs(5,2);
        methodVisitor.visitEnd();
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
