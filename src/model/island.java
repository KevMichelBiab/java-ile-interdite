package model;

public class island extends Observable{
    private zone[][] grid;

    public static final int HAUTEUR=40, LARGEUR=60;

    public island(){
        this.grid = new zone[LARGEUR][HAUTEUR];
        for(int i=0; i<LARGEUR; i++){
            for(int j=0; j<HAUTEUR; j++){
                this.grid[i][j] = new zone();

            }
        }
        this.init();
    }
    public void printIsland() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].toString() + " ");  // Print each zone's state
            }
            System.out.println();  // New line after each row
        }

        notifyObservers();
    }

    public void init() {
        for(int i=0; i<LARGEUR; i++) {
            for(int j=0; j<HAUTEUR; j++) {
                if (Math.random() < .2) {
                    this.grid[i][j].setState(ZoneState.FLOODED);
                }
                if (Math.random() < .3){
                    this.grid[i][j].setState(ZoneState.SUNK);
                }
            }
        }
    }

    public zone[][] getGrid() {
        return grid;
    }

    public void setGrid(zone[][] grid) {
        this.grid = grid;
    }

    public static void main(String[] args) {
        // Create a new zone (e.g., 5x5)
        zone z = new zone();
        System.out.println(z);  // Should print: Zone (5x5) - State: NORMAL

        // Change the state to FLOODED
        z.setState(ZoneState.FLOODED);
        System.out.println(z);  // Now it should print: Zone (5x5) - State: FLOODED


        island island = new island();

        // Print the initial state of the island (all zones should be NORMAL)
        island.printIsland();
    }
}
