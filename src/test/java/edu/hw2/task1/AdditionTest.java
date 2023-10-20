package edu.hw2.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdditionTest {
    @Test
    @DisplayName("Test of addition of negative and positive numbers")
    void add(){
        var add = new Expr.Addition(new Expr.Negate(new Expr.Constant(1)),new Expr.Constant(4));
        Assertions.assertEquals(3,add.evaluate());
    }
}
