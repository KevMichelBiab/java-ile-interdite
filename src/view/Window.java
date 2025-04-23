package view;
import Controller.control;
import model.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window  {
    private JFrame frame;
    private Grid grid;
    private Buttons buttons;

    private JTextField textCurrentPlayer;
    private ArrayList<Player> players;




    protected Window(island plateau, ArrayList<Player> players){
        this.frame = new JFrame("ILE INTERDITE");
        this.frame.setSize(2000,1500);
        this.frame.setLayout(new BorderLayout());



        this.grid = new Grid(plateau,players);

        System.out.println(this.grid.getListOfPlayers().size());


        JPanel gameSettings = new JPanel();
        gameSettings.setLayout(new BoxLayout(gameSettings, BoxLayout.Y_AXIS));
        this.buttons = new Buttons(plateau,grid);
        this.buttons.setMaximumSize(new Dimension(200, 100));
        gameSettings.add(this.buttons);


        JPanel playerInputPanel = new JPanel();
        JLabel label = new JLabel("Current Player: ");

        /*Update the current current player*/



        playerInputPanel.add(label);


        gameSettings.add(playerInputPanel);


        this.frame.add(grid, BorderLayout.WEST);  // Add the grid panel to the left of the frame
        this.frame.add(gameSettings, BorderLayout.EAST);  // Add the buttons panel to the right of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        grid.requestFocusInWindow();


    }




}
