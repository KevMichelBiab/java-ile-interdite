package view;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window  {
    private JFrame frame;
    private Grid grid;
    private Buttons buttons;

    protected Window(island plateau){
        this.frame = new JFrame("ILE INTERDITE");
        this.frame.setSize(2000,1500);
        this.frame.setLayout(new BorderLayout());


        Player play1 = new Player("James");
        Player play2 = new Player("Eli");
        ArrayList<Player> players = new ArrayList<>();
        players.add(play1);
        players.add(play2);
        this.grid = new Grid(plateau,players);
        this.grid.getListOfPlayers().add(play1);
        this.grid.getListOfPlayers().add(play2);
        System.out.println(this.grid.getListOfPlayers().size());
        this.frame.add(this.grid);

        this.buttons = new Buttons(plateau,grid);

        this.frame.add(this.buttons);


        this.frame.add(grid, BorderLayout.WEST);  // Add the grid panel to the left of the frame
        this.frame.add(buttons, BorderLayout.EAST);  // Add the buttons panel to the right of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        grid.requestFocusInWindow();


    }


}
