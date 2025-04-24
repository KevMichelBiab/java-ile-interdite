package view;
import model.*;
import Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class Buttons extends JPanel implements Observer{

    private island mod;
    private JButton finDeTour;


    public JButton getFinDeTour() {
        return finDeTour;
    }

    public void setFinDeTour(JButton finDeTour) {
        this.finDeTour = finDeTour;
    }

    public Buttons(island mod, Grid gamePanel){
        this.mod = mod;



        this.finDeTour = new JButton(">");
        this.add(finDeTour);

        control ctrl = new control(this.mod);


        finDeTour.setFocusable(false);



    }


    @Override
    public void update() {
        repaint();
    }
}
