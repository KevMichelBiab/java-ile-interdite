package view;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Window  {
    private JFrame frame;
    private Grid grid;
    private Buttons buttons;

    protected Window(island plateau){
        this.frame = new JFrame("ILE INTERDITE");
        this.frame.setSize(600,400);
        this.frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.grid = new Grid(plateau);

        this.frame.add(this.grid);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }


}
