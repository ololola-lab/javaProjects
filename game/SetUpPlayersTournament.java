package game;

import java.util.ArrayList;

public class SetUpPlayersTournament {
    private int myCounter;
    private final int playersCount;
    public SetUpPlayersTournament(int playersCount){
        this.playersCount = playersCount;
    }
    public  ArrayList<MyPlayer> generatePlayers(){
        ArrayList<MyPlayer> tournament = new ArrayList<>();

        int count = 1;
        int botCount;
        while (playersCount > count){
            count *= 2;
        }
        botCount = count - playersCount;

        for(int i = 0; i < playersCount; i++){
            MyPlayer humanPlayer = new HumanPlayer();
            humanPlayer.setName("Human Player " + i);
            tournament.add(humanPlayer);
        }
        for(int i = 0; i < botCount; i++){
            MyPlayer botPlayer = new RandomPlayer();
            botPlayer.setName("Bot Player" + i);
            tournament.add(botPlayer);
        }

        myCounter = count/2 + 1;
        return tournament;
    }


    public int getCount(){
        return myCounter;
    }
}
