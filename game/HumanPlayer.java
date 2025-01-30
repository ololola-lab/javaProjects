package game;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer extends MyPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            Scanner in = new Scanner(System.in);
            if (in.hasNextLine()) {

                int x = -1, y = -1;
                if (in.hasNextInt()) {
                    x = in.nextInt();
                }
                if (in.hasNextInt()) {
                    y = in.nextInt();
                }
                if (x > -1 && y > -1) {

                    final Move move = new Move(x, y, cell);
                    if (position.isValid(move)) {
                        return move;
                    }
                    final int row = move.getRow();
                    final int column = move.getColumn();
                    out.println("Move " + move + " is invalid");
                } else {
                    System.out.println("incorrect value entered");
                }
            }

            else {
                System.out.println("incorrect value entered");
                in.close();
                System.exit(0);

            }
        }
    }
}