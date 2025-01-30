package expression;

public interface MyExpression extends Expression, TripleExpression{

    int evaluate(int x);

    int evaluate(int x, int y, int z);
    //boolean equals(Object expression);
}
