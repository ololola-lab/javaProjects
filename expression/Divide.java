package expression;

public class Divide extends BinaryOperations {
    public Divide(MyExpression firstVariable, MyExpression secondVariable) {
        super(firstVariable, secondVariable, "/");
    }

    @Override
    public int operate(int firstVar, int secondVar) {
        return firstVar / secondVar;
    }
}
