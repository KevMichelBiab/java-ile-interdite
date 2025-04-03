package model;

public class player {
    private String name;
    private zone currentArea;
    private int playerKey;

    public player(zone c){
        this.name = "James";
        this.currentArea = c;
        this.playerKey = 0;
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public zone getCurrentArea() {
        return currentArea;
    }

    public int getPlayerKey() {
        return playerKey;
    }

    public void setPlayerKey(int keynumber) {
        this.playerKey = keynumber;
    }

    public void setCurrentArea(zone currentArea) {
        this.currentArea = currentArea;
    }

    public void deplacement(zone whereToGo){
        this.currentArea = whereToGo;

    }
}
