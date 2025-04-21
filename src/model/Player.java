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

    public void deplacement( Direction.direction whereToGo){
            switch (whereToGo) {
                case Direction.direction.FRONT:
                    if( this.y - Grid.TAILLE >= 0) {
                        this.y -= Grid.TAILLE;

                    }
                    break;
                case Direction.direction.BACK:
                    if( this.y + Grid.TAILLE < island.HAUTEUR * Grid.TAILLE){
                        this.y+= Grid.TAILLE;
                    }
                    break;

                case Direction.direction.RIGHT:
                    if (this.x + Grid.TAILLE < island.LARGEUR * Grid.TAILLE) {  // Check if moving right is within bounds
                        this.x+=Grid.TAILLE;
                    }
                    break;

                case Direction.direction.LEFT:
                    if (this.x - Grid.TAILLE>= 0) {  // Check if moving left is within bounds
                        this.x-=Grid.TAILLE;
                    }
                    break;
            }
        notifyObservers();

    }







}
