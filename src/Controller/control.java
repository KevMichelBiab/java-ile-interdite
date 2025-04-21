package Controller;
import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class control implements ActionListener, KeyListener {
    public island mod;
    private ArrayList<Player> listPlayers;
    private  int currentPlayerIndex = 0;





    public control(island mod){
        this.mod = mod;


    }

    public control(island mod, ArrayList<Player> listPlayers){
        this(mod);
        this.listPlayers = listPlayers;




    }



    public void actionPerformed(ActionEvent e) {

        System.out.println("Button presseed!");
        mod.init();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
/*Pour le moment, je vais utiliser maxActionMoves pour savoir le nombre total d'actions que je peux realiser*/

/* How it should be for different players
* -Get into the list of players
* -Target the first player
* -Update his move up to 3
* -Go to the next
* -Do the same */
@Override
public void keyPressed(KeyEvent e) {
    System.out.println("Before List size: " + this.listPlayers.size());
    if (this.listPlayers.size() == 0) return; // Ensure there are players

    System.out.println("Value index: " + currentPlayerIndex);
    Player currentPlayer = this.listPlayers.get(currentPlayerIndex);

    // Control actions for each player up to a limit (3 actions per player)
    if (currentPlayer.getActionsRemaining() > 0) {
        int keyCode = e.getKeyCode();

        // Handle key presses based on direction
        switch (keyCode) {
            case KeyEvent.VK_UP:
                currentPlayer.deplacement(Direction.direction.FRONT);
                break;
            case KeyEvent.VK_DOWN:
                currentPlayer.deplacement(Direction.direction.BACK);
                break;
            case KeyEvent.VK_LEFT:
                currentPlayer.deplacement(Direction.direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                currentPlayer.deplacement(Direction.direction.RIGHT);
                break;
        }
        currentPlayer.decrementActions(); // Decrease actions remaining for this player
        System.out.println("Remaining actions: " + currentPlayer.getActionsRemaining());
        System.out.println("Player " + currentPlayerIndex + ": (" + currentPlayer.getX() + ", " + currentPlayer.getY() + ")");
    }

    // Move to the next player after their turn is finished

    System.out.println("Now List size: " + this.listPlayers.size());
    currentPlayerIndex = (currentPlayerIndex + 1) % this.listPlayers.size();
    System.out.println("Value index: " + currentPlayerIndex);
    System.out.println();
    // Possibly reset maxActionsMoves here if needed (for each player, not global)
}


    @Override
    public void keyReleased(KeyEvent e) {

    }


}
