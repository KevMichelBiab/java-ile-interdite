package Controller;
import model.*;
import view.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class control implements ActionListener, KeyListener, DocumentListener {
    public island mod;
    private ArrayList<Player> listPlayers;
    private  int currentPlayerIndex = 0;

    private view.Window window;







    public control(island mod){
        this.mod = mod;


    }
    public control(island mod, ArrayList<Player>player){
        this(mod);
        this.listPlayers = player;
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
        System.out.println("Current coordinates of player " + currentPlayerIndex + " (" + currentPlayer.getX() + currentPlayer.getY() + ")");

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
                case KeyEvent.VK_W:
                    this.mod.assechement(Direction.direction.FRONT,currentPlayer);
                    break;
                case KeyEvent.VK_S:
                    this.mod.assechement(Direction.direction.CENTER,currentPlayer);
                    break;
                case KeyEvent.VK_A:
                    this.mod.assechement(Direction.direction.LEFT,currentPlayer);
                    break;
                case KeyEvent.VK_D:
                    this.mod.assechement(Direction.direction.RIGHT,currentPlayer);
                    break;
                case KeyEvent.VK_Z:
                    this.mod.assechement(Direction.direction.BACK,currentPlayer);
                    break;
            }
            currentPlayer.decrementActions();
            System.out.println("Remaining actions: " + currentPlayer.getActionsRemaining());
            System.out.println("Player " + currentPlayerIndex + ": (" + currentPlayer.getX() + ", " + currentPlayer.getY() + ")");

            if (currentPlayer.getActionsRemaining() == 0) {
                this.mod.init();
                currentPlayerIndex = (currentPlayerIndex + 1) % listPlayers.size();
                System.out.println("Switched to player: " + currentPlayerIndex);
                currentPlayer.resetAction();


            }
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
