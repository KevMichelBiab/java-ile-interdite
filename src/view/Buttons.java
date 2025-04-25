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


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Align left with spacing
        buttonPanel.add(finDeTour);
        buttonPanel.add(keyExchange);
        buttonPanel.add(partyWon);


        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(100, 0));

        this.setLayout(new BorderLayout());
        this.add(spacerPanel, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.CENTER);






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
