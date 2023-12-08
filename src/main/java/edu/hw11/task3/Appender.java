package edu.hw11.task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.F_SAME;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.IF_ICMPGT;
import static org.objectweb.asm.Opcodes.IF_ICMPNE;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.ISUB;
import static org.objectweb.asm.Opcodes.LADD;
import static org.objectweb.asm.Opcodes.LRETURN;

public class Appender implements ByteCodeAppender {
    @Override
    public Size apply(
        MethodVisitor methodVisitor,
        Implementation.Context context,
        MethodDescription methodDescription
    ) {
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
        methodVisitor.visitFrame(F_SAME, 0, null, 0, null);
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitInsn(ICONST_2);
        Label l3 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPNE, l3);
        Label l4 = new Label();

        methodVisitor.visitLabel(l4);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitInsn(LRETURN);

        methodVisitor.visitLabel(l3);
        methodVisitor.visitFrame(F_SAME, 0, null, 0, null);
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
        methodVisitor.visitMaxs(5, 2);
        methodVisitor.visitEnd();
        StackManipulation.Size operandStackSize = new StackManipulation.Compound().apply(methodVisitor, context);
        return new Size(operandStackSize.getMaximalSize(), methodDescription.getStackSize());
    }
}
