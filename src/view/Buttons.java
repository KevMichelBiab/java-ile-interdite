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


    public JButton getFinDeTour() {
        return finDeTour;
    }
    public JButton getPartyWon() {
        return partyWon;
    }

    public void setNameButton(String nameButton) {
        this.finDeTour.setName(nameButton);
    }

    public Buttons(island mod, Grid gamePanel) {
        this.mod = mod;

        // Create buttons
        this.finDeTour = new JButton("<<Fin de Tour>>");
        this.partyWon = new JButton("<<End>>");
        this.finDeTour.setPreferredSize(new Dimension(200,40));
        this.partyWon.setPreferredSize(new Dimension(200,40));

        this.add(finDeTour);

        this.add(partyWon);

        // Initialize control and set action listeners for buttons (if needed)
        control ctrl = new control(this.mod);

        finDeTour.setFocusable(false);
        partyWon.setFocusable(false);
    }

    @Override
    public void update() {
        repaint();
    }
}
