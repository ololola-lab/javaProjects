package expression;

import java.util.Objects;

abstract class BinaryOperations implements MyExpression {
    protected MyExpression firstVariable;
    protected MyExpression secondVariable;
    protected String sign;

    public BinaryOperations(MyExpression firstVariable, MyExpression secondVariable, String sign) {
        this.firstVariable = firstVariable;
        this.secondVariable = secondVariable;
        this.sign = sign;
    }

    public abstract int operate(int firstVar, int secondVar);

    public int evaluate(int x) {
        return operate(firstVariable.evaluate(x), secondVariable.evaluate(x));
    }
    public int evaluate(int x, int y, int z) {
        return operate(firstVariable.evaluate(x, y, z), secondVariable.evaluate(x, y, z));
    }

    public String toString() {
        return "(" + firstVariable.toString() + " " +  sign + " " + secondVariable.toString() + ")";
    }

//    public boolean compare(expression.BinaryOperations operator) {
//
//    }

    @Override
    public boolean equals(Object input) {
        if (input == null) {
            return false;
        }
        if (this == input) {
            return true;
        }
        if (this.getClass() != input.getClass()) {
            return false;
        }
//        if (!(input instanceof expression.BinaryOperations)) {
//            return false;
//        }
        final BinaryOperations operator = (BinaryOperations) input;
        return firstVariable.equals(operator.firstVariable) &&
                secondVariable.equals(operator.secondVariable) && sign.equals(operator.sign);
    }
    @Override
    public int hashCode() {
        return Objects.hash(Objects.hashCode(firstVariable), Objects.hashCode(secondVariable), Objects.hashCode(sign));
    }

}
