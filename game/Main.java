package game;
/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {

        Scanner sc;
        int val = -1;
        while(true){
            System.out.println("Choice game mode.");
            System.out.println("Print 1, if we want play a game");
            System.out.println("Print 2, if we want play a tournament");
            sc = new Scanner(System.in);
            if(sc.hasNextLine()){
                if (sc.hasNextInt()){
                    val = sc.nextInt();
                    break;
                }
                else{
                    System.out.println("incorrect value entered");
                }
            }
            else{
                System.out.println("input completed");
                sc.close();
                System.exit(0);
            }
        }
        if (val == 1){
            System.out.println("Print m, n, k");
            if(sc.hasNextLine()){
                int m = -1, n = -1, k = -1;
                if (sc.hasNextInt()){
                    m  = sc.nextInt();
                }
                if (sc.hasNextInt()){
                    n  = sc.nextInt();
                }
                if (sc.hasNextInt()){
                    k  = sc.nextInt();
                }
                if (m > 0 && n > 0 && k > 0){
                    MyGame myGame = new MyGame(false, new HumanPlayer(), new HumanPlayer(), m, n, k);
                    myGame.start();

                    sc.close();
                }
                else{
                    System.out.println("incorrect value entered");
                    sc.close();
                    System.exit(0);
                }
            }
            else{
                System.out.println("input completed");
                sc.close();
                System.exit(0);
            }
        }
        if (val == 2){
            System.out.println("Print number of players ");

            if(sc.hasNextLine()){

                if(sc.hasNextInt()){
                    int playersCount = sc.nextInt();
                    if (playersCount > 0){
                        SetUpPlayersTournament setting = new SetUpPlayersTournament(playersCount);
                        ArrayList<MyPlayer> players = setting.generatePlayers();

                        Tournament tournament = new Tournament(players, setting.getCount());
                        tournament.start();
                    }
                }
                else{
                    System.out.println("input completed");
                    sc.close();
                    System.exit(0);
                }
            }
            else{
                System.out.println("input completed");
                sc.close();
                System.exit(0);
            }
        }


    }


}
