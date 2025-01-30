package game;

public abstract class MyPlayer implements Player{
    String name;

    public void setName(String name){
        this.name = name;

    }
    public String getName(){
        return name;
    }
}
