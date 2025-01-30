package expression;

import java.util.Objects;

public class Variable implements MyExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (name) {
            case "x" ->  x;
            case "y" ->  y;
            case "z" ->  z;
            default -> 0;
        };
    }
    @Override
    public boolean equals(Object inp) {
        if (inp == null) {
            return false;
        }
        if (inp == null || this.getClass() != inp.getClass()) {
            return false;
        }
        return Objects.equals(name, ((Variable) inp).name);
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
