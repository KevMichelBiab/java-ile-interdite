package model;

import view.Grid;

import java.util.ArrayList;
import java.util.HashSet;

public class island extends Observable{
    private zone[][] grid;



    public static final int HAUTEUR=20, LARGEUR=20;

    public island() {
        this.grid = new zone[LARGEUR][HAUTEUR];

        /* Generate the helicopter position
        int helicoX = 1 + (int) (Math.random() * (LARGEUR-1));
        int helicoY = 1 + (int) (Math.random() * (HAUTEUR-1));

        /Artefacts
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
                    System.out.println("Helicop at : " + helicoX +", " + helicoY);
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
        }*/
        HashSet<String> occupied = new HashSet<>(); // On utilise ce hashset pour ne pas avoir de doublons

        int helicoX, helicoY;  //Ce sont les coordonnes de notre helicopter
        do {
            helicoX = 1 + (int) (Math.random() * (LARGEUR - 1));
            helicoY = 1 + (int) (Math.random() * (HAUTEUR - 1));
        } while (!occupied.add(helicoX + "," + helicoY));
        /*
        Etant donne que occupied store les position deja occupes, on veut s'assurer que les coordonnees que nous calculons
        ne soient pas ajoutes dans occupied donc, on met une negation ! dans la condition while pourvoir generer des coordonees tant que
        les coordonnes que nous avons calculees sont deja presentes  dans occupied
        */
        System.out.println("Helicopter at: " + helicoX + ", " + helicoY);

        //Maintenant on s'occupe des artefactes
        int[] artefactsX = new int[4]; // On va storer les coordonees X des artefactes
        int[] artefactsY = new int[4]; // On va storer les coordonees Y des artefactes

        for (int i = 0; i < 4; i++) {
            int x, y;
            do {
                x = (int) (Math.random() * LARGEUR);
                y = (int) (Math.random() * HAUTEUR);
            } while (!occupied.add(x + "," + y)); // La meme raison exposee en haut
            artefactsX[i] = x;
            artefactsY[i] = y;
            System.out.println("Artefacts " + (i + 1) + " placed at: " + x + "," + y);
        }

            for (int i = 0; i < LARGEUR; i++) {
                for (int j = 0; j < HAUTEUR; j++) {
                    if (i == helicoX && j == helicoY) {
                        this.grid[i][j] = new helicopterZone();
                    } else {
                        boolean placedArtefacts = false;
                        for (int k = 0; k < 4; k++) {
                            if (i == artefactsX[k] && j == artefactsY[k]) {
                                this.grid[i][j] = new ArtefactsZones();
                                placedArtefacts = true;
                                break; // On fait pour ne pas avoir a checker les autres zones. Du temps qu'on trouve un artefact, cela suffit
                            }
                        }
                        if (!placedArtefacts) {
                            this.grid[i][j] = new zone();
                        }
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
            if(this.grid[randomI][randomJ].getState() == ZoneState.NORMAL) {
                this.grid[randomI][randomJ].setState(ZoneState.FLOODED);
                System.out.println(" " + randomI + ":" + randomJ + " are " + this.grid[randomI][randomJ].getState());
                floodedCount++;
            } else if (this.grid[randomI][randomJ].getState() == ZoneState.FLOODED){
                this.grid[randomI][randomJ].setState(ZoneState.SUNK);
                System.out.println(" " + randomI + ":" + randomJ + " are " + this.grid[randomI][randomJ].getState());
            }
        }

        notifyObservers();
    }

    public Player exchangeKeys(ArrayList<Player> listPlayers, Player p){
        Player otherPlayer = null;
        for(int i=0; i<listPlayers.size(); i++){
            if(listPlayers.get(i) == p){
                continue;
            }
            otherPlayer = listPlayers.get(i);
            if(p.getPlayerKey() > 0) {
                if (this.grid[p.getX()][p.getY()] == this.grid[otherPlayer.getX()][otherPlayer.getY()]) {
                    otherPlayer.setPlayerKey(otherPlayer.getPlayerKey() + p.getPlayerKey());
                    System.out.println(otherPlayer.getName() + " Keys right after the update: " + otherPlayer.getPlayerKey());
                    p.decrementKeys();
                    System.out.println("Keys exchanged!");
                    System.out.println(otherPlayer.getName() + " keys: " + otherPlayer.getPlayerKey());
                    return otherPlayer;

                }


            }
        }


        System.out.println("Current player Keys : " + p.getPlayerKey());
        return p;
    }
    public void floodPlayer(Player p){
        if(this.grid[p.getX()][p.getY()].getState() == ZoneState.NORMAL) {

            System.out.println("Player flooded!");
            this.grid[p.getX()][p.getY()].setState(ZoneState.FLOODED);
            notifyObservers();
        }else if(this.grid[p.getX()][p.getY()].getState() == ZoneState.FLOODED){
            System.out.println("Player sunk!");
            this.grid[p.getX()][p.getY()].setState(ZoneState.SUNK);
        }

        /*A test for to see if helicop actually triggers the end of the game
        for(int i=0; i<LARGEUR; i++){
            for(int j=0; j<HAUTEUR; j++){
                if(this.grid[i][j].getState() == ZoneState.HELICOPTER){
                    this.grid[i][j].setState(ZoneState.SUNK);
                }
            }
        }*/
        notifyObservers();
    }



    public void actionButtonFinDeTour(Player p){
        if(Math.random() < 0.2){
            this.floodPlayer(p);
        } else {
            this.generateKey(p);
        }
    }

    public void assechement(Direction.direction whereToGo, Player p){
        switch (whereToGo) {
            case FRONT:
                if(p.isInBounds()) {
                    if (this.grid[p.getX()-1][p.getY()].getState() == ZoneState.FLOODED) {
                        System.out.println("Coordinates after updating: " + p.getX() + ", " + p.getY());
                        this.grid[p.getX()-1][p.getY()].setState(ZoneState.NORMAL);
                    }
                }
                break;
            case BACK:
                if(p.isInBounds()) {
                    if (this.grid[p.getX()+1][p.getY()].getState() == ZoneState.FLOODED) {
                        System.out.println("Coordinates after updating: " + p.getX() + ", " +p.getY());
                        this.grid[p.getX()+1][p.getY()].setState(ZoneState.NORMAL);
                    }
                }
                break;

            case RIGHT:
                if(p.isInBounds()) {
                    if (this.grid[p.getX()][p.getY()+1].getState() == ZoneState.FLOODED) {
                        System.out.println("Coordinates after updating: " + p.getX() +", " + p.getY());
                        this.grid[p.getX()][p.getY()+1].setState(ZoneState.NORMAL);
                    }
                }
                break;

            case LEFT:
                if(p.isInBounds()) {
                    if (this.grid[p.getX()][p.getY()-1].getState() == ZoneState.FLOODED) {
                        System.out.println("Coordinates after updating: " + p.getX() +", " + p.getY());
                        this.grid[p.getX()][p.getY()-1].setState(ZoneState.NORMAL);
                    }
                }
                break;
            case CENTER:
                if(p.isInBounds()) {
                    if (this.grid[p.getX()][p.getY()].getState() == ZoneState.FLOODED) {
                        this.grid[p.getX()][p.getY()].setState(ZoneState.NORMAL);
                    }
                }
        }
        notifyObservers();
    }

    public void recupArtefacts(Player p){
        zone zoneChecked = this.grid[p.getX()][p.getY()];
        if(zoneChecked.getState() == ZoneState.ARTEFACTS){
            if(p.getPlayerKey() > 0) {
                zoneChecked.setState(ZoneState.NORMAL);
                p.setCountArteFacts(p.getCountArteFacts() + 1);
                p.decrementKeys();
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
    public void generateKey(Player p){
        p.incrementKeys();
    }

    public boolean ifPlayerOnHelicop(Player p){
        return this.grid[p.getX()][p.getY()].getState() == ZoneState.HELICOPTER;
    }

    public boolean isDead(Player p){
        return this.grid[p.getX()][p.getY()].getState() == ZoneState.SUNK;
    }

    public boolean lostHelicop(){
        for(int i =0; i<LARGEUR; i++){
            for(int j=0; j<HAUTEUR; j++) {
                if(this.grid[i][j] instanceof helicopterZone){
                    return this.grid[i][j].getState() == ZoneState.SUNK;

                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Create a new island
        island myIsland = new island();



        // Print out the state of each zone in the island
        myIsland.printIsland();
    }


}
