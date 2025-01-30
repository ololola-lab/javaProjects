package expression;

public class Add extends BinaryOperations{
    public Add(MyExpression firstVariable, MyExpression secondVariable) {
        super(firstVariable, secondVariable, "+");
    }

    @Override
    public int operate(int firstVar, int secondVar) {
        return firstVar + secondVar;
    }
}

