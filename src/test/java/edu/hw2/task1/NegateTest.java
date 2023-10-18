package edu.hw2.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NegateTest {
    @Test
    @DisplayName("Test of negative values")
    void negate(){
        var exp = new Expr.Negate(new Expr.Negate(new Expr.Constant(4)));
        Assertions.assertEquals(4,exp.evaluate());
    }
}
