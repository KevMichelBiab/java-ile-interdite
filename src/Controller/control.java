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

    public void handleButton(ActionEvent e, Player currentPlayer){
        if(e.getSource() == window.getButtons().getFinDeTour()){ // If the button selected is <<fin de tour>>
            handleFindeDeTour(currentPlayer);
        } else if (e.getSource() == window.getButtons().getPartyWon()){ //If the button selected is <<party won>>
            handlePartyWon(currentPlayer);

        } else if( e.getSource() == window.getButtons().getKeyExchange()){ //If the button selected is <<end>>
            handleKeyExcahnge(currentPlayer);
        }
    }
    public void handleFindeDeTour(Player p){
            Player currentPlayer = this.listPlayers.get(currentPlayerIndex);
            window.getPlayerLog().setText(currentPlayer.getName() + " decided to try his luck to obtain a key...");
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
            if (currentPlayer.getActionsRemaining() > 0) {
                this.mod.actionButtonFinDeTour(currentPlayer);

                if(currentPlayer.isKeyUpdated()){
                    window.getPlayerLog().setText(currentPlayer.getName() + " got a key!");
                }
                currentPlayer.decrementActions();
                updatePlayerInfo(currentPlayer);

            }
            if (currentPlayer.getActionsRemaining() == 0) {
                currentPlayer.resetAction();
                switchToNextPlayerNotOnHelicopter();
            }

    }
    public void handlePartyWon(Player p){
        Player currentPlayer = this.listPlayers.get(currentPlayerIndex);
        updatePartyButton();
        return;
    }
    public void handleKeyExcahnge( Player p){
        Player currentPlayer = this.listPlayers.get(currentPlayerIndex);
        Player updated = this.mod.exchangeKeys(this.listPlayers, currentPlayer);
        if(updated != null) {
            window.getPlayerLog().setText(p.getName() + " gave a key to " + updated.getName());
            updatePlayerInfo(currentPlayer);
            window.getTextfieldNames().get(this.listPlayers.indexOf(updated)).setText(updated.getName());
            window.getTextfieldKeys().get(this.listPlayers.indexOf(updated)).setText(String.valueOf(updated.getPlayerKey()));
            updated.setActionsRemaining(0);
            window.getTextFieldActions().get(this.listPlayers.indexOf(updated)).setText("0");
            updated.resetAction();
        } else {
            window.getPlayerLog().setText(p.getName() + " tried to give a key to  a player but no key is available.");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(gameOver) return;
        if (this.listPlayers.size() == 0) return; // Ensure there are players
        Player currentPlayer = this.listPlayers.get(currentPlayerIndex);
        if (mod.lostHelicop()) {
            updatePartyButton();
            return;
        }
        handleButton(e,currentPlayer);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(gameOver) return;
        if (this.listPlayers.isEmpty()) return; // Ensure there are players
        Player currentPlayer = this.listPlayers.get(currentPlayerIndex);
        window.getPlayerLog().setText(currentPlayer.getName() + " is moving...");

        if (mod.lostHelicop()) { //If the helicopter is sunk
            updatePartyButton();
            return;
        }
        if(this.mod.isDead(currentPlayer)){ //If a player is dead
            currentPlayer.setPlayerDead(true);
            currentPlayer.setActionsRemaining(0);
            currentPlayer.setPlayerKey(0);
            currentPlayer.setCountArteFacts(0);
            updatePlayerInfo(currentPlayer);
            updatePartyButton();
            return;
        }

        if (this.mod.ifPlayerOnHelicop(currentPlayer)) { //if a player is on the helicopter
            if(this.artefactTotalCount() == 4) {
                currentPlayer.setOnHelicopter(true);
                currentPlayer.setActionsRemaining(0);
                currentPlayer.setPlayerKey(0);
                currentPlayer.setCountArteFacts(0);
                updatePlayerInfo(currentPlayer);
                window.getPlayerLog().setText(currentPlayer.getName() + " is on the helicopter zone!");
                if(allHelicop()){gameOver=true;}
                switchToNextPlayerNotOnHelicopter();
                return;
            }
        }



        if (currentPlayer.getActionsRemaining() > 0) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP: // If the player got up to another cell
                    currentPlayer.deplacement(Direction.direction.FRONT);
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    break;
                case KeyEvent.VK_DOWN: //I the player goes down to another cell
                    currentPlayer.deplacement(Direction.direction.BACK);
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    break;
                case KeyEvent.VK_LEFT: //If the player goes left to another cell
                    currentPlayer.deplacement(Direction.direction.LEFT);
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    break;
                case KeyEvent.VK_RIGHT: //If the player goes right to another cell
                    currentPlayer.deplacement(Direction.direction.RIGHT);
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    break;
                case KeyEvent.VK_W:
                    this.mod.assechement(Direction.direction.FRONT,currentPlayer);
                    window.getPlayerLog().setText(currentPlayer.getName() + " just changed the state of the cell above");
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    break;
                case KeyEvent.VK_S:
                    this.mod.assechement(Direction.direction.CENTER,currentPlayer);
                    window.getPlayerLog().setText(currentPlayer.getName() + " just changed the state of the cell he is currently at");
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    break;
                case KeyEvent.VK_A:
                    this.mod.assechement(Direction.direction.LEFT,currentPlayer);
                    window.getPlayerLog().setText(currentPlayer.getName() + " just changed the state of the cell behind");
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    break;
                case KeyEvent.VK_D:
                    this.mod.assechement(Direction.direction.RIGHT,currentPlayer);
                    window.getPlayerLog().setText(currentPlayer.getName() + " just changed the state of the cell in front");
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    break;
                case KeyEvent.VK_Z:
                    this.mod.assechement(Direction.direction.BACK,currentPlayer);
                    window.getPlayerLog().setText(currentPlayer.getName() + " just changed the state of the cell below");
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    break;
                case KeyEvent.VK_ENTER:
                    this.mod.recupArtefacts(currentPlayer);
                    currentPlayer.decrementActions();
                    updatePlayerInfo(currentPlayer);
                    window.getPlayerLog().setText(currentPlayer.getName() + " picked an artefact.");

            }
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
            window.getPlayerLog().setText("Congrats! You won the party!");
        } else if(lostParty()){
            gameOver = true;
            window.getButtons().getPartyWon().setBackground(Color.BLACK);
            window.getButtons().getPartyWon().setForeground(Color.WHITE); // optional: make text visible
            window.getButtons().getPartyWon().setText("Party Lost");
            window.getPlayerLog().setText("Unfortunately, You lost party!");
        } else {
            window.getButtons().getPartyWon().setBackground(Color.RED);
            window.getPlayerLog().setText("Not yet, you still have things before winning the party!");
        }
    }
    public boolean allHelicop(){
        boolean check = true;
        for(Player p: this.listPlayers){
            if(!p.isOnHelicopter()){
                 check = false;
            }
        }
        return check;
    }


    public boolean winningParty(){
        int count = this.artefactTotalCount();
        boolean onHelicop = true;
        for(Player p : this.listPlayers){
            if(!this.mod.ifPlayerOnHelicop(p)){
                onHelicop = false;
                break; //We exit if the player is not on the helicopter
            }
        }
        boolean hasEnoughArtefacts = (count == 4);
        return onHelicop && hasEnoughArtefacts;
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
        /*Here we are updating the player info which are the name, keys, actions and artefact number*/
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




    private void switchToNextPlayerNotOnHelicopter() {

        int attempts = 0; // Just to prevent infinite loop
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % listPlayers.size();
            attempts++;

        } while ((listPlayers.get(currentPlayerIndex).isOnHelicopter() || listPlayers.get(currentPlayerIndex).isPlayerDead())
                && attempts < listPlayers.size());

        Player nextPlayer = listPlayers.get(currentPlayerIndex);
        updatePlayerInfo(nextPlayer);
        window.getPlayerLog().setText(nextPlayer.getName() + ", it is your turn");
        //System.out.println("Switched to player: " + currentPlayerIndex);
    }
    public int artefactTotalCount(){
        int countArte = 0;
        for(Player p: this.listPlayers){
            countArte += p.getCountArteFacts();
        }
        return countArte;
    }







}
