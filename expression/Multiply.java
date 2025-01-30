package expression;

public class Multiply extends BinaryOperations {
    public Multiply(MyExpression firstVariable, MyExpression secondVariable) {
        super(firstVariable, secondVariable, "*");
    }

    @Override
    public int operate(int firstVar, int secondVar) {
        return firstVar * secondVar;
    }
}
