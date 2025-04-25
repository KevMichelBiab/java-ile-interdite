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
    private boolean gameOver = false;







    public control(island mod){
        this.mod = mod;


    }
    public control(island mod, ArrayList<Player>player){
        this(mod);
        this.listPlayers = player;
    }

    public void setWindow(view.Window window){
        this.window = window;
    }




    public void actionPerformed(ActionEvent e) {
        if(gameOver) return;

        /*System.out.println("Before List size: " + this.listPlayers.size());*/
        if (this.listPlayers.size() == 0) return; // Ensure there are players
        Player currentPlayer = this.listPlayers.get(currentPlayerIndex);

        /*System.out.println("Value index: " + currentPlayerIndex);*/
        if (mod.lostHelicop()) {
            System.out.println("Button Pressed");
            updatePartyButton();
            return;
        }
        if(e.getSource() == window.getButtons().getFinDeTour()) {

            if (this.mod.ifPlayerOnHelicop(currentPlayer)) {
                currentPlayer.setOnHelicopter(true);
                currentPlayer.setActionsRemaining(0);
                currentPlayer.setPlayerKey(0);
                currentPlayer.setCountArteFacts(0);
                updatePlayerInfo(currentPlayer);
                switchToNextPlayerNotOnHelicopter();
                return;

            }
            if(this.mod.isDead(currentPlayer)){
                currentPlayer.setPlayerDead(true);
                currentPlayer.setActionsRemaining(0);
                currentPlayer.setPlayerKey(0);
                currentPlayer.setCountArteFacts(0);
                updatePlayerInfo(currentPlayer);
                updatePartyButton();
                return;
            }
        /*System.out.println("Current coordinates of player " + currentPlayerIndex + " (" + currentPlayer.getX() + currentPlayer.getY() + ")");
        System.out.println("Button presseed!")*/
            if (currentPlayer.getActionsRemaining() > 0) {
                this.mod.actionButtonFinDeTour(currentPlayer);
                currentPlayer.decrementActions();
                updatePlayerInfo(currentPlayer);

            }
            if (currentPlayer.getActionsRemaining() == 0) {
                currentPlayer.resetAction();
                switchToNextPlayerNotOnHelicopter();
            }
        }
        if(e.getSource() == window.getButtons().getPartyWon()){
            updatePartyButton();
            return;

        }
        if(e.getSource() == window.getButtons().getKeyExchange()){
            Player updated = this.mod.exchangeKeys(this.listPlayers, currentPlayer);
            updatePlayerInfo(currentPlayer);
            window.getTextfieldNames().get(this.listPlayers.indexOf(updated)).setText(updated.getName());
            window.getTextfieldKeys().get(this.listPlayers.indexOf(updated)).setText(String.valueOf(updated.getPlayerKey()));
            updated.setActionsRemaining(0);
            window.getTextFieldActions().get(this.listPlayers.indexOf(updated)).setText("0");
            updated.resetAction();



        }

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
        if(gameOver) return;
        System.out.println("Before List size: " + this.listPlayers.size());
        if (this.listPlayers.size() == 0) return; // Ensure there are players
        Player currentPlayer = this.listPlayers.get(currentPlayerIndex);

        System.out.println("Value index: " + currentPlayerIndex);

        if (mod.lostHelicop()) {
            System.out.println("Button Pressed");
            updatePartyButton();
            return;
        }
        if(this.mod.isDead(currentPlayer)){
            currentPlayer.setPlayerDead(true);
            currentPlayer.setActionsRemaining(0);
            currentPlayer.setPlayerKey(0);
            currentPlayer.setCountArteFacts(0);
            updatePlayerInfo(currentPlayer);
            updatePartyButton();
            return;
        }


        if (currentPlayer.isPlayerDead()) {
            System.out.println("Dead player skipped");
            switchToNextPlayerNotOnHelicopter();
            return;
        }
        if (this.mod.ifPlayerOnHelicop(currentPlayer)) {
            currentPlayer.setOnHelicopter(true);
            currentPlayer.setActionsRemaining(0);
            currentPlayer.setPlayerKey(0);
            currentPlayer.setCountArteFacts(0);
            updatePlayerInfo(currentPlayer);
            switchToNextPlayerNotOnHelicopter();
            return;
        }



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
                case KeyEvent.VK_ENTER:
                    this.mod.recupArtefacts(currentPlayer);

            }
            currentPlayer.decrementActions();
            System.out.println("Current coordinate: " + currentPlayer.getX() + ", " + currentPlayer.getY());
            updatePlayerInfo(currentPlayer);


            if (currentPlayer.getActionsRemaining() == 0) {
                this.mod.init();

                currentPlayer.resetAction();
                switchToNextPlayerNotOnHelicopter();

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
    public void updatePartyButton(){
        if(winningParty()){
            gameOver = true;
            window.getButtons().getPartyWon().setBackground(Color.BLUE);
            window.getButtons().getPartyWon().setForeground(Color.WHITE); // optional: make text visible
            window.getButtons().getPartyWon().setText("Party Won");
        }
        if(lostParty()){
            gameOver = true;
            window.getButtons().getPartyWon().setBackground(Color.BLACK);
            window.getButtons().getPartyWon().setForeground(Color.WHITE); // optional: make text visible
            window.getButtons().getPartyWon().setText("Party Lost");
        }
        window.getButtons().getPartyWon().setBackground(Color.RED);
    }
    public void updateKeyExchangeButton(){

    }

    public boolean winningParty(){
        int count = 0;
        boolean onHelicop = true;
        for(int i = 0; i<this.listPlayers.size(); i++){
            count += this.listPlayers.get(i).getCountArteFacts();
            if(!(this.mod.ifPlayerOnHelicop(this.listPlayers.get(i)))){
                onHelicop = !onHelicop;
            }
        }
        return (count == 4) && onHelicop;
    }
    public boolean ifPlayerDead(){
        int howManyDead = 0;
        for(Player p : this.listPlayers){
            if(p.isPlayerDead()){
                System.out.println("Player status: " + p.isPlayerDead());
                howManyDead++;
            }
        }
        return howManyDead >= 1;
    }
    public boolean lostParty(){
        return ifPlayerDead() || this.mod.lostHelicop();
    }

    private void updatePlayerInfo(Player player) {
        window.getTextfieldNames().get(this.listPlayers.indexOf(player)).setText(player.getName());
        window.getTextfieldKeys().get(this.listPlayers.indexOf(player)).setText(String.valueOf(player.getPlayerKey()));

        if (player.isOnHelicopter()) {
            window.getTextFieldActions().get(this.listPlayers.indexOf(player)).setText("🚁");
        } else {
            window.getTextFieldActions().get(this.listPlayers.indexOf(player)).setText(String.valueOf(player.getActionsRemaining()));
        }
        if(player.isPlayerDead()){
            window.getTextFieldActions().get(this.listPlayers.indexOf(player)).setText("Dead!");
        } else {
            window.getTextFieldActions().get(this.listPlayers.indexOf(player)).setText(String.valueOf(player.getActionsRemaining()));
        }

        window.getTextfieldArtefacts().get(this.listPlayers.indexOf(player)).setText(String.valueOf(player.getCountArteFacts()));

    }
    private void switchPlayer() {
        if(this.listPlayers.size() == 1){
            return;
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % listPlayers.size();
            System.out.println("Switched to player: " + currentPlayerIndex);
            Player currentPlayer = listPlayers.get(currentPlayerIndex);
            updatePlayerInfo(currentPlayer);
        }
    }
    private void switchToNextPlayerNotOnHelicopter() {
        int attempts = 0; // Just to prevent infinite loop
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % listPlayers.size();
            attempts++;

        } while ((listPlayers.get(currentPlayerIndex).isOnHelicopter() || listPlayers.get(currentPlayerIndex).isPlayerDead())
                && attempts < listPlayers.size());

        Player nextPlayer = listPlayers.get(currentPlayerIndex);
        updatePlayerInfo(nextPlayer);
        System.out.println("Switched to player: " + currentPlayerIndex);
    }






}
