package model;


import view.Grid;

import java.awt.event.KeyEvent;

public class Player extends Observable{
    private String name;

    private int x;
    private int y;
    private int playerKey;
    private int actionsRemaining;


    public Player(String name){
        this.name = name;
        this.x =0;
        this.y=0;
        this.playerKey = 0;
        this.actionsRemaining = 3;



    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getActionsRemaining() {
        return actionsRemaining;
    }

    public void decrementActions() {
        if (actionsRemaining > 0) {
            actionsRemaining--;
        }
    }
    public void resetAction(){
        this.actionsRemaining = 3;
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
                if (this.y > 0) this.y--;
                break;
            case BACK:
                if (this.y < island.HAUTEUR - 1) this.y++;
                break;
            case RIGHT:
                if (this.x < island.LARGEUR - 1) this.x++;
                break;
            case LEFT:
                if (this.x > 0) this.x--;
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
        return (this.x >= 0 && this.x < island.LARGEUR) && (this.y >= 0 && this.y < island.HAUTEUR);
    }







}
