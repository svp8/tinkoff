package edu.hw2.task1;

public sealed interface Expr
    permits Expr.Addition, Expr.Constant, Expr.Exponent, Expr.Multiplication, Expr.Negate {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(evaluate());
        }
    }

    record Negate(Expr value) implements Expr {

        @Override
        public double evaluate() {
            return value.evaluate() * -1;
        }

        @Override
        public String toString() {
            return "Negative of " + value.evaluate();
        }
    }

    record Exponent(Expr value, Expr exponent) implements Expr {
        public Exponent(Expr value, double exponent) {
            this(value, new Constant(exponent));
        }

        @Override
        public double evaluate() {
            return Math.pow(value.evaluate(), exponent.evaluate());
        }

        @Override
        public String toString() {
            return String.format("%.2f to the power %.2f", value.evaluate(), exponent.evaluate());
        }
    }

    record Addition(Expr a, Expr b) implements Expr {
        @Override
        public double evaluate() {
            return a.evaluate() + b.evaluate();
        }

        @Override
        public String toString() {
            return String.format("%s of %.2f to %.2f", this.getClass().getSimpleName(), a.evaluate(), b.evaluate());
        }
    }

    record Multiplication(Expr a, Expr b) implements Expr {
        @Override
        public double evaluate() {
            return a.evaluate() * b.evaluate();
        }

        @Override
        public String toString() {
            return String.format("%s of %.2f and %.2f", this.getClass().getSimpleName(), a.evaluate(), b.evaluate());
        }
    }
}
