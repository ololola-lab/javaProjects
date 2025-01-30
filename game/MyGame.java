package game;

public class MyGame {
    private final boolean log;
    private final Player player1;
    private final Player player2;
    private final int m;
    private final int n;
    private final int k;


    public MyGame(boolean log, Player player1, Player player2, int m, int n, int k) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
        this.m = m;
        this.n = n;
        this.k = k;
    }
    public void start(){
        Game game = new Game(log, player1, player2);
        int result;
        result = game.play(new mnkBoard(m, n, k));
        if (result == 1) {
            System.out.println("First Player win");
        }
        else if (result == 2) {
            System.out.println("Second Player win");
        }
        else{
            System.out.println("draw");
        }

    }
}

