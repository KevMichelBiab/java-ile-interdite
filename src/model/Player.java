package model;


import view.Grid;

import java.awt.event.KeyEvent;

public class Player extends Observable{
    private String name;

    private int x;
    private int y;
    private int playerKey;
    private int actionsRemaining;
    private int CountArteFacts;
    private boolean onHelicopter = false;
    public boolean isPlayerDead = false;


    public Player(String name){
        this.name = name;
        this.x =0;
        this.y=0;
        this.playerKey = 0;
        this.actionsRemaining = 3;
        this.CountArteFacts = 0;



    };

    public void setActionsRemaining(int actionsRemaining) {
        this.actionsRemaining = actionsRemaining;
    }

    public String getName() {
        return name;
    }

    public int getCountArteFacts() {
        return CountArteFacts;
    }

    public void setCountArteFacts(int countArteFacts) {
        CountArteFacts = countArteFacts;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getActionsRemaining() {
        return actionsRemaining;
    }

    public boolean isOnHelicopter() {
        return onHelicopter;
    }

    public void setOnHelicopter(boolean onHelicopter) {
        this.onHelicopter = onHelicopter;
    }

    public boolean isPlayerDead() {
        return isPlayerDead;
    }

    public void setPlayerDead(boolean playerDead) {
        isPlayerDead = playerDead;
    }

    public void decrementActions() {
        if (actionsRemaining > 0) {
            actionsRemaining--;
        }
    }
    public void resetAction(){
        this.actionsRemaining = 3;
    }

    public void decrementKeys(){
        if(this.playerKey > 0){
            this.playerKey--;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getPlayerKey() {
        return playerKey;
    }

    public void setPlayerKey(int keynumber) {
        this.playerKey = keynumber;
    }

    public void deplacement(Direction.direction whereToGo) {
        switch (whereToGo) {
            case FRONT:
                if (this.x > 0) this.x--;
                break;
            case BACK:
                if (this.x < island.HAUTEUR - 1) this.x++;
                break;
            case RIGHT:
                if (this.y < island.LARGEUR - 1) this.y++;
                break;
            case LEFT:
                if (this.y > 0) this.y--;
                break;
        }
        notifyObservers();
    }

    /*
    -Considere qu'on a la position actuelle du joeur
    -We can use enum FRONT, BACK, RIGHT,LEFT
    -Else il asseche ou il se trouve
    */

    public boolean isInBounds(){
        return (this.x >= 0 && this.x < island.HAUTEUR) && (this.y >= 0 && this.y < island.LARGEUR);
    }







}
