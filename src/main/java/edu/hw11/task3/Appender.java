package edu.hw11.task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.jetbrains.annotations.NotNull;

public class Appender implements ByteCodeAppender {
    @Override
    public @NotNull Size apply(
        @NotNull MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        MethodDescription methodDescription
    ) {
        StackManipulation.Size operandStackSize = FibManipulation.INSTANCE.apply(methodVisitor, context);
        return new Size(operandStackSize.getMaximalSize(), methodDescription.getStackSize());
    }
}
