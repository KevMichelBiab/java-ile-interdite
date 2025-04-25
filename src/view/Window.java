package view;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window  {
    private JFrame frame;
    private Grid grid;
    private Buttons buttons;


    private JLabel labelName;
    private JLabel labelKey;
    private JLabel labelRemainActions;
    private JLabel labelArtefacts;
    private ArrayList<JTextField> textFieldActions = new ArrayList<>();
    private ArrayList<JTextField> textfieldNames = new ArrayList<>();
    private ArrayList<JTextField> textfieldKeys = new ArrayList<>();
    private ArrayList<JTextField> textfieldArtefacts = new ArrayList<>();
    private ArrayList<Player> players;





    protected Window(island plateau, ArrayList<Player> players){

        this.frame = new JFrame("ILE INTERDITE");
        this.frame.setSize(2000,650);
        this.frame.setLayout(new BorderLayout());


        this.grid = new Grid(plateau,players);
        System.out.println(this.grid.getListOfPlayers().size());

        JPanel gameSettings = new JPanel();
        gameSettings.setLayout(new BoxLayout(gameSettings, BoxLayout.Y_AXIS));
        //gameSettings.setBackground(Color.CYAN);

        /*JPanel buttonSection = new JPanel();
        buttonSection.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonSection.setBackground(Color.PINK);*/


        this.buttons = new Buttons(plateau,grid);

        this.buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //this.buttons.setBackground(Color.PINK);


        gameSettings.add(this.buttons);



        JPanel playerInputPanel = new JPanel();
        playerInputPanel.setPreferredSize(new Dimension(1000, 600));

        playerInputPanel.setLayout(new BoxLayout(playerInputPanel, BoxLayout.Y_AXIS));

        for(int i = 0; i< players.size(); i++) {
            JPanel playerRow = new JPanel();
            playerRow.setLayout(new BoxLayout(playerRow, BoxLayout.X_AXIS));
            playerRow.setAlignmentX(Component.LEFT_ALIGNMENT);

            Dimension shortFieldSize = new Dimension(40, 25);
            Dimension nameFieldSize = new Dimension(100, 25);

            // Create new fields for each player row
            JLabel labelName = new JLabel("Current Player");
            JTextField textfieldName = new JTextField(50);
            this.textfieldNames.add(textfieldName);
            textfieldName.setMaximumSize(nameFieldSize);
            textfieldName.setText(players.get(i).getName());

            JLabel labelKey = new JLabel("Key count");
            JTextField textfieldKey = new JTextField(50);
            this.textfieldKeys.add(textfieldKey);
            textfieldKey.setMaximumSize(shortFieldSize);
            textfieldKey.setText(String.valueOf(players.get(i).getPlayerKey()));

            JLabel labelRemainActions = new JLabel("Remaining actions");
            JTextField textFieldAction = new JTextField(50);
            this.textFieldActions.add(textFieldAction);
            textFieldAction.setMaximumSize(shortFieldSize);
            textFieldAction.setText(String.valueOf(players.get(i).getActionsRemaining()));

            JLabel labelArtefacts = new JLabel("Artefacts numbers: ");
            JTextField textfieldArtefact = new JTextField(50);
            this.textfieldArtefacts.add(textfieldArtefact);
            textfieldArtefact.setMaximumSize(shortFieldSize);
            textfieldArtefact.setText(String.valueOf(players.get(i).getCountArteFacts()));

            playerRow.add(labelName);
            playerRow.add(textfieldName);
            playerInputPanel.add(Box.createVerticalStrut(10));

            playerRow.add(labelKey);
            playerRow.add(textfieldKey);

            playerRow.add(labelRemainActions);
            playerRow.add(textFieldAction);

            playerRow.add(labelArtefacts);
            playerRow.add(textfieldArtefact);
            playerInputPanel.add(playerRow);

        }

       //playerInputPanel.setBackground(Color.GREEN);
        gameSettings.add(playerInputPanel);
       //gameSettings.setBackground(Color.YELLOW);

        this.frame.add(grid, BorderLayout.WEST);  // Add the grid panel to the left of the frame
        this.frame.add(gameSettings, BorderLayout.EAST);  // Add the buttons panel to the right of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        grid.requestFocusInWindow();


    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Buttons getButtons() {
        return buttons;
    }

    public void setButtons(Buttons buttons) {
        this.buttons = buttons;
    }

    public JLabel getLabelName() {
        return labelName;
    }

    public void setLabelName(JLabel labelName) {
        this.labelName = labelName;
    }

    public JLabel getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(JLabel labelKey) {
        this.labelKey = labelKey;
    }

    public JLabel getLabelRemainActions() {
        return labelRemainActions;
    }

    public void setLabelRemainActions(JLabel labelRemainActions) {
        this.labelRemainActions = labelRemainActions;
    }

    public JLabel getLabelArtefacts() {
        return labelArtefacts;
    }

    public void setLabelArtefacts(JLabel labelArtefacts) {
        this.labelArtefacts = labelArtefacts;
    }

    public ArrayList<JTextField> getTextFieldActions() {
        return textFieldActions;
    }

    public ArrayList<JTextField> getTextfieldNames() {
        return textfieldNames;
    }

    public ArrayList<JTextField> getTextfieldKeys() {
        return textfieldKeys;
    }

    public ArrayList<JTextField> getTextfieldArtefacts() {
        return textfieldArtefacts;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
