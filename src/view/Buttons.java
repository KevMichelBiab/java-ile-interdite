package view;
import model.*;
import Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Buttons extends JPanel implements Observer{

    private island mod;

    public Buttons(island mod){
        this.mod = mod;

        JButton finDeTour = new JButton(">");
        this.add(finDeTour);

        control ctrl = new control(this.mod);
        finDeTour.addActionListener(ctrl);


    }


    @Override
    public void update() {
        repaint();
    }
}
