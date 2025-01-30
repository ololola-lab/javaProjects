package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Tournament {
    private  int m;
    private  int n;
    private  int k;


    private int counter;
    ArrayList<MyPlayer> tournament = new ArrayList<>();
    public Tournament(ArrayList<MyPlayer> players, int count){
        tournament = (ArrayList<MyPlayer>) players.clone();
        this.counter = count;

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Print m, n, k");
            if(sc.hasNextLine()) {
                int m = -1, n = -1, k = -1;
                if (sc.hasNextInt()) {
                    m = sc.nextInt();
                }
                if (sc.hasNextInt()) {
                    n = sc.nextInt();
                }
                if (sc.hasNextInt()) {
                    k = sc.nextInt();
                }
                if (m > 0 && n > 0 && k > 0) {
                    this.m = m;
                    this.n = n;
                    this.k = k;
                    break;
                } else{
                    System.out.println("incorrect value entered");
                }
            }
            else {
                System.out.println("incorrect value entered");
                sc.close();
                System.exit(0);
            }
        }

    }


    public void start() {
        int roundCounter = 1;
        while (tournament.size() > 1){
            System.out.println("We Start round number " + roundCounter);
            ArrayList<MyPlayer> nextTournament = new ArrayList<>();

            for (int i = 0; i < tournament.size() - 1; i += 2) {
                MyPlayer player1 = tournament.get(i);
                MyPlayer player2 = tournament.get(i + 1);

                Game game = new Game(false, player1, player2);
                int result;
                result = game.play(new mnkBoard(m, n, k));

                if (result == 1) {
                    System.out.println(player1.getName() + " win");
                    System.out.println(player2.getName() + " lose, he takes " + counter);
                    nextTournament.add(player1);
                }
                else if (result == 2) {
                    System.out.println(player2.getName() + " win");
                    System.out.println(player1.getName() + " lose, he takes " + counter);
                    nextTournament.add(player2);
                }
                else{
                    System.out.println("draw");
                    nextTournament.add(replay(player1, player2));
                }
            }
            tournament = (ArrayList<MyPlayer>) nextTournament.clone();
            counter = counter/2 + 1;
            roundCounter++;
        }
        MyPlayer winner = tournament.get(0);
        System.out.println(winner.getName() + " is winner");

    }
    private MyPlayer replay(MyPlayer player1, MyPlayer player2) {
        int result = 0;
        while (result == 0){
            Game game = new Game(false, player1, player2);
            result = game.play(new mnkBoard(m, n, k));
        }
        if (result == 1) return player1;
        else  return player2;
    }
}
