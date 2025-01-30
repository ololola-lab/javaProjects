package expression;

public class Subtract extends BinaryOperations{
    public Subtract(MyExpression firstVariable, MyExpression secondVariable) {

        super(firstVariable, secondVariable, "-");
    }

    @Override
    public int operate(int firstVar, int secondVar) {
        return firstVar - secondVar;
    }

}
