package view;
import model.*;
import Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class Buttons extends JPanel implements Observer{

    private island mod;


    public Buttons(island mod, Grid gamePanel){
        this.mod = mod;



        JButton finDeTour = new JButton(">");
        this.add(finDeTour);

        control ctrl = new control(this.mod);
        finDeTour.addActionListener(ctrl);

        finDeTour.setFocusable(false);



    }


    @Override
    public void update() {
        repaint();
    }
}
