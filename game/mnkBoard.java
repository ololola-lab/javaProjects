package game;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */


public class mnkBoard implements Board, Position{
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    private Cell[][] cells;
    private Cell turn;
    private final int m, n, k;
    private int movCounter;

    int lastHash = 0;
    int currentHash = 0;

    int counter = 0;
    public mnkBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }

        this.turn = Cell.X;
        movCounter = 0;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        int x = move.getRow();
        int y = move.getColumn();
        Cell c = move.getValue();
        cells[x][y] = c;

        movCounter++;
        int d1 = 1;
        int d2 = 1;

        boolean bonus = false;
        for (int i = 1; (x - i >= 0) && (y - i >= 0) && (cells[x - i][y - i] == c); i++) {
            d1++;
        }
        for (int i = 1; (x + i < m) && (y + i < n) && (cells[x + i][y + i] == c); i++) {
            d1++;
        }
        if (d1 >= k) {
            if( counter % 2 == 0){
                return Result.WIN;
            }
            else {
                return Result.LOSE;
            }
        }

        if (d1 >= 4){
            bonus = true;
            currentHash = d1 * 3;
        }

        for (int i = 1; (x - i >= 0) && (y + i < n) && (cells[x - i][y + i] == c); i++) {
            d2++;
        }
        for (int i = 1; (x + i < m) &&( y - i >= 0) && (cells[x + i][y - i] == c); i++) {
            d2++;
        }
        if (d2 >= k) {
            if( counter % 2 == 0){
                return Result.WIN;
            }
            else {
                return Result.LOSE;
            }
        }

        if (d2 >= 4){
            bonus = true;
            currentHash = d2 * 5;
        }

        for (int u = 0; u < m; u++) {
            int inRow = 0;
            int inColumn = 0;

            for (int v = 0; v < n; v++) {
                if (isGoodCell(u, v) && cells[u][v] == turn) {

                    for (int r = v; r <= v + k; r++) {
                        if (isGoodCell(u, r) && cells[u][r] == turn) {
                            inRow++;
                            if (inRow >= k) {
                                if( counter % 2 == 0){

                                    return Result.WIN;
                                }
                                else {
                                    return Result.LOSE;
                                }
                            }
                            if (inRow >= 4) {
                                bonus = true;
                                currentHash = inRow * 7;
                            }
                        } else {
                            inRow = 0;
                        }

                    }
                }
                if (isGoodCell(v, u) && cells[v][u] == turn){
                    for (int r = u; r <= u + k; r++) {
                        if (isGoodCell(r, u) && cells[r][u] == turn) {
                            inColumn++;
                            if (inColumn >= k ) {

                                if( counter % 2 == 0){
                                    return Result.WIN;
                                }
                                else {
                                    return Result.LOSE;
                                }
                            }
                            if (inColumn >= 4){
                                bonus = true;
                                currentHash = inColumn * 13;
                            }
                        } else {
                            inColumn = 0;
                        }

                    }

                }
            }
        }
        if (m * n == movCounter) {
            return Result.DRAW;
        }

        if (! ( (currentHash != lastHash) && bonus) ) {
            turn = turn == Cell.X ? Cell.O : Cell.X;
            lastHash = currentHash;

            return Result.UNKNOWN;
        }
        else{
            lastHash = currentHash;
            counter++;
            return Result.UNKNOWN;
        }

    }

    private boolean isGoodCell(int x, int y) {
        return (0 <= x && x < m && 0 <= y && y < n && cells[x][y] == turn);
    }
    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < n; i++) {
            sb.append(i);
        }

        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }

}

