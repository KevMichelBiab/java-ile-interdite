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

    private JLabel labelName;
    private JLabel labelKey;
    private JLabel labelRemainActions;
    private JTextField textFieldActions;
    private JTextField textfieldName;
    private JTextField textfieldKey;
    private ArrayList<Player> players;




    protected Window(island plateau, ArrayList<Player> players){
        this.frame = new JFrame("ILE INTERDITE");
        this.frame.setSize(500,500);
        this.frame.setLayout(new BorderLayout());


        this.grid = new Grid(plateau,players);
        System.out.println(this.grid.getListOfPlayers().size());

        JPanel gameSettings = new JPanel();
        gameSettings.setLayout(new BoxLayout(gameSettings, BoxLayout.Y_AXIS));
        this.buttons = new Buttons(plateau,grid);
        this.buttons.setMaximumSize(new Dimension(1000, 500));
        gameSettings.add(this.buttons);

        JPanel playerInputPanel = new JPanel();
        this.labelName = new JLabel("Current Player");
        this.textfieldName = new JTextField(15);

        this.textfieldName.setText(players.get(0).getName());
        this.labelKey = new JLabel("Key count");

        this.textfieldKey = new JTextField(15);
        this.textfieldKey.setText(String.valueOf(players.get(0).getPlayerKey()));

        this.labelRemainActions = new JLabel("Remaining actions");
        this.textFieldActions = new JTextField(15);
        this.textFieldActions.setText(String.valueOf(players.get(0).getActionsRemaining()));

        playerInputPanel.add(this.labelName);
        playerInputPanel.add(this.textfieldName);

        playerInputPanel.add(this.labelKey);
        playerInputPanel.add(this.textfieldKey);

        playerInputPanel.add(this.labelRemainActions);
        playerInputPanel.add(this.textFieldActions);

        /*Create a pannel to keep track of the keys of all players*/

       /*JPanel keyBox = new JPanel();
        for(Player p : players){
            JLabel keyScoreText = new JLabel(p.getName() + " Score Keys: ");
            JTextField KeyScore = new JTextField(15);
            KeyScore.setText(String.valueOf(p.getPlayerKey()));
            keyBox.add(keyScoreText);
            keyBox.add(KeyScore);
        } An idea to always have the players key score on the screen*/

        gameSettings.add(playerInputPanel);


        this.frame.add(grid, BorderLayout.WEST);  // Add the grid panel to the left of the frame
        this.frame.add(gameSettings, BorderLayout.EAST);  // Add the buttons panel to the right of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        grid.requestFocusInWindow();


    }


    public JLabel getLabelName() {
        return labelName;
    }

    public Buttons getButtons() {
        return buttons;
    }

    public JTextField getTextFieldActions() {
        return textFieldActions;
    }

    public void setTextFieldActions(String actionCount) {
        this.textFieldActions.setText(actionCount);
    }

    public void setButtons(Buttons buttons) {
        this.buttons = buttons;
    }

    public Grid getGrid() {
        return grid;
    }

    public JTextField getTextfieldName() {
        return textfieldName;
    }

    public void setTextfieldName(String name) {
        this.textfieldName.setText(name);
    }

    public JTextField getTextfieldKey() {
        return textfieldKey;
    }

    public void setTextfieldKey(String KeyCount) {
        this.textfieldKey.setText(KeyCount);
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setLabelName(String name) {
        this.labelName.setText(name);
    }
}
