package edu.hw2.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExponentTest {
    @Test
    @DisplayName("Test of Exponent")
    void exponent(){
        var exp = new Expr.Exponent(new Expr.Constant(3), 2);
        Assertions.assertEquals(9,exp.evaluate());
    }
}
