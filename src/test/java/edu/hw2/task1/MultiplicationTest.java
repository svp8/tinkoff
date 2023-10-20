package edu.hw2.task1;

import org.junit.jupiter.api.Assertions;

public class MultiplicationTest {
    void multiply(){
        Expr.Constant constant1=new Expr.Constant(2);
        Expr.Constant constant2=new Expr.Constant(4);
        Expr.Multiplication mul=new Expr.Multiplication(constant2,constant1);
        Assertions.assertEquals(8,mul.evaluate());
    }
}
