package model;

import view.Grid;

public class island extends Observable{
    private zone[][] grid;

    public static final int HAUTEUR=40, LARGEUR=60;

    public island(){
        this.grid = new zone[LARGEUR+2][HAUTEUR+2];
        for(int i=0; i<LARGEUR+2; i++){
            for(int j=0; j<HAUTEUR+2; j++){
                this.grid[i][j] = new zone();

            }
        }



    }
    public void printIsland() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].toString() + " ");  // Print each zone's state
            }
            System.out.println();  // New line after each row
        }


    }

    public void init() {
        int floodedCount = 0;
        while(floodedCount < 3) {
            int randomI = (int)(Math.random() * LARGEUR);
            int randomJ = (int)(Math.random() * HAUTEUR);

            // Only flood the zone if it's not already flooded
            if(this.grid[randomI][randomJ].getState() != ZoneState.FLOODED) {
                this.grid[randomI][randomJ].setState(ZoneState.FLOODED);
                System.out.println(" " + randomI + ":" + randomJ + " are " + this.grid[randomI][randomJ].getState());
                floodedCount++;
            }
        }
        notifyObservers();


    }

    public void assechement(Direction.direction whereToGo, Player p){
        switch (whereToGo) {
            case Direction.direction.FRONT:
                if(p.isInBounds()) {
                    if (this.grid[p.getX()][p.getY() - 1].getState() == ZoneState.FLOODED) {
                        System.out.println("Coordinates after updating: " + p.getX() + p.getY());
                        this.grid[p.getX()][p.getY() - 1].setState(ZoneState.NORMAL);
                    }
                }
                break;
            case Direction.direction.BACK:
                if(p.isInBounds()) {
                    if (this.grid[p.getX()][p.getY() + 1].getState() == ZoneState.FLOODED) {
                        System.out.println("Coordinates after updating: " + p.getX() + p.getY());
                        this.grid[p.getX()][p.getY() + 1].setState(ZoneState.NORMAL);
                    }
                }
                break;

            case Direction.direction.RIGHT:
                if(p.isInBounds()) {
                    if (this.grid[p.getX() + 1][p.getY()].getState() == ZoneState.FLOODED) {
                        System.out.println("Coordinates after updating: " + p.getX() + p.getY());
                        this.grid[p.getX() + 1][p.getY()].setState(ZoneState.NORMAL);
                    }
                }
                break;

            case Direction.direction.LEFT:
                if(p.isInBounds()) {
                    if (this.grid[p.getX() - 1][p.getY()].getState() == ZoneState.FLOODED) {
                        System.out.println("Coordinates after updating: " + p.getX() + p.getY());
                        this.grid[p.getX() - 1][p.getY()].setState(ZoneState.NORMAL);
                    }
                }
                break;
            case Direction.direction.CENTER:
                if(p.isInBounds()) {
                    if (this.grid[p.getX()][p.getY()].getState() == ZoneState.FLOODED) {
                        this.grid[p.getX()][p.getY()].setState(ZoneState.NORMAL);
                    }
                }
        }

        notifyObservers();
    }


    public zone[][] getGrid() {
        return grid;
    }

    public void setGrid(zone[][] grid) {
        this.grid = grid;
    }



}
