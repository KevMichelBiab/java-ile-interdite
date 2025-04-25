package view;
import model.*;
import Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class Buttons extends JPanel implements Observer{

    private island mod;
    private JButton finDeTour;
    private JButton partyWon;
    private JButton keyExchange;


    public JButton getFinDeTour() {
        return finDeTour;
    }
    public JButton getPartyWon() {
        return partyWon;
    }

    public JButton getKeyExchange() {
        return keyExchange;
    }

    public void setNameButton(String nameButton) {
        this.finDeTour.setName(nameButton);
    }

    public Buttons(island mod, Grid gamePanel) {
        this.mod = mod;

        // Create buttons
        this.finDeTour = new JButton("<<Fin de Tour>>");
        this.partyWon = new JButton("<<End>>");
        this.keyExchange = new JButton("Key Exchange");
        this.finDeTour.setPreferredSize(new Dimension(200,40));
        this.partyWon.setPreferredSize(new Dimension(200,40));
        this.keyExchange.setPreferredSize(new Dimension(200,40));



        this.setPreferredSize(new Dimension(650, 20));
        // Align left with spacing
        this.add(finDeTour);
        this.add(keyExchange);
        this.add(partyWon);









        // Initialize control and set action listeners for buttons (if needed)


        finDeTour.setFocusable(false);
        partyWon.setFocusable(false);
        keyExchange.setFocusable(false);
    }


    @Override
    public void update() {
        repaint();
    }
}
