package expression;

import java.util.Objects;

public class Const implements MyExpression {
    private final int constanta;

    public Const(int constanta) {
        this.constanta = constanta;
    }

    @Override
    public int evaluate(int x) {
        return constanta;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.evaluate(constanta);
    }

    @Override
    public String toString() {
        return String.valueOf(constanta);
    }
    @Override
    public boolean equals(Object inp) {
        if (inp == null) {
            return false;
        }
        if (inp == null || this.getClass() != inp.getClass()) {
            return false;
        }
        return constanta == ((Const) inp).constanta;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(constanta);
    }
}
