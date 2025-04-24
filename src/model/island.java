package model;

import view.Grid;

import java.util.ArrayList;

public class island extends Observable{
    private zone[][] grid;

    public static final int HAUTEUR=5, LARGEUR=5;

    public island() {
        this.grid = new zone[LARGEUR][HAUTEUR];

        // Generate the helicopter position
        int helicoX = (int) (Math.random() * LARGEUR);
        int helicoY = (int) (Math.random() * HAUTEUR);

        //Artefacts
        int artefact1X = (int) (Math.random() * LARGEUR);
        int artefact1Y = (int) (Math.random() * HAUTEUR);

        int artefact2X = (int) (Math.random() * LARGEUR);
        int artefact2Y = (int) (Math.random() * HAUTEUR);

        int artefact3X = (int) (Math.random() * LARGEUR);
        int artefact3Y = (int) (Math.random() * HAUTEUR);

        int artefact4X = (int) (Math.random() * LARGEUR);
        int artefact4Y = (int) (Math.random() * HAUTEUR);



        for (int i = 0; i < LARGEUR; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                if (i == helicoX && j == helicoY) {
                    this.grid[i][j] = new helicopterZone();
                }

                if (i == artefact1X && j == artefact1Y) {
                    System.out.println("Artefact 1 at: " + artefact1X + ", " + artefact1Y);
                    this.grid[i][j] = new ArtefactsZones();
                }

                if (i == artefact2X && j == artefact2Y) {
                    System.out.println("Artefact 2 at: " + artefact2X + ", " + artefact2Y);
                    this.grid[i][j] = new ArtefactsZones();
                }

                if (i == artefact3X && j == artefact3Y) {
                    System.out.println("Artefact 3 at: " + artefact3X + ", " + artefact3Y);
                    this.grid[i][j] = new ArtefactsZones();
                }

                if (i == artefact4X && j == artefact4Y) {
                    System.out.println("Artefact 4 at: " + artefact4X + ", " + artefact4Y);
                    this.grid[i][j] = new ArtefactsZones();
                }
                if (this.grid[i][j] == null) {
                    this.grid[i][j] = new zone();
                }



            }
        }
        notifyObservers();


    }





    public void printIsland() {
        for (int i = 0; i < LARGEUR; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                System.out.print(this.grid[i][j].toString() + "(" + i + "," + j + ")" +" ");  // Print each zone's state
            }
            System.out.println();  // New line after each row
        }



    }

    public void init() {
        int floodedCount = 0;
        while(floodedCount < 3) {
            int randomI = (int)(Math.random() * (LARGEUR));
            int randomJ = (int)(Math.random() * (HAUTEUR));

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

    public static void main(String[] args) {
        // Create a new island
        island myIsland = new island();



        // Print out the state of each zone in the island
        myIsland.printIsland();
    }


}
