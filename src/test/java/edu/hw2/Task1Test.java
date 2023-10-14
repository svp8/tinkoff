package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task1Test {

    @Test
    @DisplayName("Simple sequence of computations")
    void calculator(){
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));
        Assertions.assertEquals(37,res.evaluate());
    }

    @Test
    @DisplayName("Test of Exponent")
    void exponent(){
        var exp = new Expr.Exponent(new Expr.Constant(3), 2);
        Assertions.assertEquals(9,exp.evaluate());
    }
    @Test
    @DisplayName("Test of negative values")
    void negate(){
        var exp = new Expr.Negate(new Expr.Negate(new Expr.Constant(4)));
        Assertions.assertEquals(4,exp.evaluate());
    }
}
