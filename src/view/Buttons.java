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
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Create buttons
        this.finDeTour = createdStyledButton("<<Fin de Tour>>");
        this.partyWon = createdStyledButton("<<End>>");
        this.keyExchange = createdStyledButton("Key Exchange");

        this.add(finDeTour);
        this.add(Box.createHorizontalStrut(20));
        this.add(keyExchange);
        this.add(Box.createHorizontalStrut(20));
        this.add(partyWon);


        finDeTour.setFocusable(false);
        partyWon.setFocusable(false);
        keyExchange.setFocusable(false);
    }

    public JButton createdStyledButton(String text){
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(180,40));
        btn.setFocusable(false);
        return btn;
    }

    @Override
    public void update() {
        repaint();
    }
}
