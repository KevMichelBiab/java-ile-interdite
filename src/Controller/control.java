package Controller;
import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class control implements ActionListener, KeyListener {
    public island mod;
    private Player currentPlayer;
    private int maxActionsMoves;

    public control(island mod){
        this.mod = mod;
        this.maxActionsMoves = 0;
    }

    public control(island mod, Player currentPlayer){
        this(mod);
        this.currentPlayer = currentPlayer;


    }



    public void actionPerformed(ActionEvent e) {

        System.out.println("Button presseed!");
        mod.init();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
/*Pour le moment, je vais utiliser maxActionMoves pour savoir le nombre total d'actions que je peux realiser*/
    @Override
    public void keyPressed(KeyEvent e) {
        if (this.maxActionsMoves < 3) {
            System.out.println("Executed");
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:

                    this.currentPlayer.deplacement(Direction.direction.FRONT);
                    this.maxActionsMoves++;
                    System.out.println("Current coordinates: (" + this.currentPlayer.getX() + ", " + this.currentPlayer.getY() + ")");


                    break;
                case KeyEvent.VK_DOWN:
                    this.currentPlayer.deplacement(Direction.direction.BACK);
                    this.maxActionsMoves++;
                    System.out.println("Current coordinates: (" + this.currentPlayer.getX() + ", " + this.currentPlayer.getY() + ")");

                    break;
                case KeyEvent.VK_LEFT:
                    this.currentPlayer.deplacement(Direction.direction.LEFT);
                    this.maxActionsMoves++;
                    System.out.println("Current coordinates: (" + this.currentPlayer.getX() + ", " + this.currentPlayer.getY() + ")");

                    break;
                case KeyEvent.VK_RIGHT:
                    this.currentPlayer.deplacement(Direction.direction.RIGHT);
                    this.maxActionsMoves++;
                    System.out.println("Current coordinates: (" + this.currentPlayer.getX() + ", " + this.currentPlayer.getY() + ")");

                    break;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
