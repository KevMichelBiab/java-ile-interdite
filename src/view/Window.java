package view;
import Controller.control;
import model.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.DigestException;
import java.util.ArrayList;

public class Window  {
    private JFrame frame;
    private Grid grid;
    private Buttons buttons;


    private JLabel labelName;
    private JLabel labelKey;
    private JLabel labelRemainActions;
    private JLabel labelArtefacts;
    private JTextField textFieldActions;
    private JTextField textfieldName;
    private JTextField textfieldKey;
    private JTextField textfieldArtefacts;
    private ArrayList<Player> players;




    protected Window(island plateau, ArrayList<Player> players){
        this.frame = new JFrame("ILE INTERDITE");
        this.frame.setSize(2000,700);
        this.frame.setLayout(new BorderLayout());


        this.grid = new Grid(plateau,players);
        System.out.println(this.grid.getListOfPlayers().size());

        JPanel gameSettings = new JPanel();
        gameSettings.setLayout(new BoxLayout(gameSettings, BoxLayout.Y_AXIS));
        //gameSettings.setBackground(Color.CYAN);

        JPanel buttonSection = new JPanel();
        buttonSection.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        //buttonSection.setBackground(Color.PINK);
        this.buttons = new Buttons(plateau,grid);
        buttonSection.add(this.buttons);
        gameSettings.add(buttonSection);



        JPanel playerInputPanel = new JPanel();
        playerInputPanel.setPreferredSize(new Dimension(1000, 1000));

        playerInputPanel.setLayout(new BoxLayout(playerInputPanel, BoxLayout.Y_AXIS));
       // playerInputPanel.setBackground(Color.LIGHT_GRAY);
        for(int i = 0; i< players.size(); i++) {

            JPanel playerRow = new JPanel();
            playerRow.setLayout(new BoxLayout(playerRow, BoxLayout.X_AXIS));
            playerRow.setAlignmentX(Component.LEFT_ALIGNMENT);

            Dimension shortFieldSize = new Dimension(40, 25);
            Dimension nameFieldSize = new Dimension(100, 25);

            this.labelName = new JLabel("Current Player");
            this.textfieldName = new JTextField(15);
            this.textfieldName.setMaximumSize(nameFieldSize);
            this.textfieldName.setText(players.get(i).getName());

            this.labelKey = new JLabel("Key count");
            this.textfieldKey = new JTextField(15);
            this.textfieldKey.setMaximumSize(shortFieldSize);
            this.textfieldKey.setText(String.valueOf(players.get(i).getPlayerKey()));

            this.labelRemainActions = new JLabel("Remaining actions");
            this.textFieldActions = new JTextField(15);
            this.textFieldActions.setMaximumSize(shortFieldSize);
            this.textFieldActions.setText(String.valueOf(players.get(i).getActionsRemaining()));

            this.labelArtefacts = new JLabel("Artefacts numbers: ");
            this.textfieldArtefacts = new JTextField(15);
            this.textfieldArtefacts.setMaximumSize(shortFieldSize);
            this.textfieldArtefacts.setText(String.valueOf(players.get(i).getCountArteFacts()));

            playerRow.add(this.labelName);
            playerRow.add(this.textfieldName);
            playerInputPanel.add(Box.createVerticalStrut(10));



            playerRow.add(this.labelKey);
            playerRow.add(this.textfieldKey);





            playerRow.add(this.labelRemainActions);
            playerRow.add(this.textFieldActions);





            playerRow.add(this.labelArtefacts);
            playerRow.add(this.textfieldArtefacts);
            playerInputPanel.add(playerRow);

        }

       // playerInputPanel.setBackground(Color.GREEN);
        gameSettings.add(playerInputPanel);
       //gameSettings.setBackground(Color.YELLOW);

        this.frame.add(grid, BorderLayout.WEST);  // Add the grid panel to the left of the frame
        this.frame.add(gameSettings, BorderLayout.EAST);  // Add the buttons panel to the right of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        grid.requestFocusInWindow();


    }




    public Buttons getButtons() {
        return buttons;
    }
    public void setButtons(Buttons buttons) {
        this.buttons = buttons;
    }

    public JTextField getTextFieldActions() {
        return textFieldActions;
    }
    public void setTextFieldActions(String actionCount) {
        this.textFieldActions.setText(actionCount);
    }


    public Grid getGrid() {
        return grid;
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
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


    public void setLabelName(String name) {
        this.labelName.setText(name);
    }
    public JLabel getLabelName() {
        return labelName;
    }

    public JLabel getLabelArtefacts() {
        return labelArtefacts;
    }

    public void setLabelArtefacts(JLabel labelArtefacts) {
        this.labelArtefacts = labelArtefacts;
    }

    public JTextField getTextfieldArtefacts() {
        return textfieldArtefacts;
    }

    public void setTextfieldArtefacts(String ArteCounts) {
        this.textfieldArtefacts.setText(ArteCounts);
    }
}
