package model;

public class island {
    private zone[][] grid;

    public island(int width, int height){
        this.grid = new zone[width][height];
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                this.grid[i][j] = new zone(5,5);
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






    public static void main(String[] args) {
        // Create a new zone (e.g., 5x5)
        zone z = new zone(20, 20);
        System.out.println(z);  // Should print: Zone (5x5) - State: NORMAL

        // Change the state to FLOODED
        z.setState(ZoneState.FLOODED);
        System.out.println(z);  // Now it should print: Zone (5x5) - State: FLOODED


        island island = new island(20, 20);

        // Print the initial state of the island (all zones should be NORMAL)
        island.printIsland();
    }
}
