package view;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Window {
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

    private backgroundImage background;


    protected Window(island plateau, ArrayList<Player> players) {

        this.frame = new JFrame("ILE INTERDITE");
        this.frame.setSize(1920, 1080);
        this.frame.setLayout(new BorderLayout());

        this.background = new backgroundImage();
        this.grid = new Grid(plateau, players);


        JPanel gameSettings = new JPanel();
        gameSettings.setPreferredSize(new Dimension(900, 900));
        gameSettings.setLayout(new BoxLayout(gameSettings, BoxLayout.Y_AXIS));
        /*
         * Here I basically ask the layou manager to set the size of exactly 600x900
         * It is preferred to setmax because here the layout will actually try his best to respect
         * the measurement and make the panel appear. But with setMax it might not be the case
         * the panel might disappear*/

        //gameSettings.setLayout(new BoxLayout(gameSettings, BoxLayout.Y_AXIS));
        gameSettings.setBackground(Color.CYAN);

        /*JPanel buttonSection = new JPanel();
        buttonSection.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonSection.setBackground(Color.PINK);*/


        this.buttons = new Buttons(plateau, grid);

        this.buttons.setBackground(Color.PINK);
        gameSettings.add(this.buttons);
        //gameSettings.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 10));
        //JPanel spacer = new JPanel();
        //spacer.setPreferredSize(new Dimension(900, 900)); // This takes up the remaining vertical space
        //spacer.setBackground(Color.CYAN); // Set the background color to cyan
        //gameSettings.add(spacer);


        JPanel playerInputPanel = new JPanel();
        playerInputPanel.setPreferredSize(new Dimension(900, 160));
        playerInputPanel.setBackground(Color.GREEN);
        playerInputPanel.setLayout(new BoxLayout(playerInputPanel, BoxLayout.Y_AXIS));


        for (int i = 0; i < players.size(); i++) {
            JPanel playerRow = new JPanel();
            playerRow.setPreferredSize(new Dimension(900, 50));
            playerRow.setBackground(Color.YELLOW);
            playerRow.setLayout(new BoxLayout(playerRow, BoxLayout.X_AXIS));
           // Fais en sorte que les textfields et labels commencent a etre genere a gauch de gamesettings



            Dimension shortFieldSize = new Dimension(200, 30);
            Dimension nameFieldSize = new Dimension(200, 30);

            // Create new fields for each player row
            JLabel labelName = new JLabel("Current Player");
            JTextField textfieldName = new JTextField(50);
            this.textfieldNames.add(textfieldName);
            //textfieldName.setPreferredSize(nameFieldSize);
            textfieldName.setMaximumSize(nameFieldSize);
            textfieldName.setText(players.get(i).getName());

            JLabel labelKey = new JLabel("Key count");
            JTextField textfieldKey = new JTextField(50);
            this.textfieldKeys.add(textfieldKey);
            //textfieldKey.setPreferredSize(shortFieldSize);
            textfieldKey.setMaximumSize(shortFieldSize);
            textfieldKey.setText(String.valueOf(players.get(i).getPlayerKey()));

            JLabel labelRemainActions = new JLabel("Remaining actions");
            JTextField textFieldAction = new JTextField(50);
            this.textFieldActions.add(textFieldAction);
            //textFieldAction.setPreferredSize(shortFieldSize);
            textFieldAction.setMaximumSize(shortFieldSize);
            textFieldAction.setText(String.valueOf(players.get(i).getActionsRemaining()));

            JLabel labelArtefacts = new JLabel("Artefacts numbers: ");
            JTextField textfieldArtefact = new JTextField(50);
            this.textfieldArtefacts.add(textfieldArtefact);
            //textfieldArtefact.setPreferredSize(shortFieldSize);
            textfieldArtefact.setMaximumSize(shortFieldSize);
            textfieldArtefact.setText(String.valueOf(players.get(i).getCountArteFacts()));

            playerRow.add(labelName);
            playerRow.add(textfieldName);
            playerRow.add(Box.createRigidArea(new Dimension(0, 10)));

            playerRow.add(labelKey);
            playerRow.add(textfieldKey);
            playerRow.add(Box.createRigidArea(new Dimension(0, 10)));

            playerRow.add(labelRemainActions);
            playerRow.add(textFieldAction);
            playerRow.add(Box.createRigidArea(new Dimension(0, 10)));

            playerRow.add(labelArtefacts);
            playerRow.add(textfieldArtefact);

            playerInputPanel.add(playerRow);

        }


        gameSettings.add(playerInputPanel);
        playerInputPanel.setOpaque(false);
        buttons.setOpaque(false);
        grid.setOpaque(false);
        gameSettings.setOpaque(false);
            gameSettings.setBackground(Color.YELLOW);

            JPanel spacer = new JPanel();
            spacer.setPreferredSize(new Dimension(900, 720)); // This takes up the remaining vertical space
            spacer.setBackground(Color.CYAN); // Set the background color to cyan
            gameSettings.add(spacer);

            this.background.add(grid, BorderLayout.WEST);
            this.background.add(gameSettings, BorderLayout.EAST);
            //this.frame.add(grid, BorderLayout.WEST);  // Add the grid panel to the left of the frame
            // this.frame.add(gameSettings, BorderLayout.EAST);  // Add the buttons panel to the right of the frame
            frame.add(this.background);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            grid.requestFocusInWindow();


        }


        public JFrame getFrame () {
            return frame;
        }

        public void setFrame (JFrame frame){
            this.frame = frame;
        }

        public Grid getGrid () {
            return grid;
        }

        public void setGrid (Grid grid){
            this.grid = grid;
        }

        public Buttons getButtons () {
            return buttons;
        }

        public void setButtons (Buttons buttons){
            this.buttons = buttons;
        }

        public JLabel getLabelName () {
            return labelName;
        }

        public void setLabelName (JLabel labelName){
            this.labelName = labelName;
        }

        public JLabel getLabelKey () {
            return labelKey;
        }

        public void setLabelKey (JLabel labelKey){
            this.labelKey = labelKey;
        }

        public JLabel getLabelRemainActions () {
            return labelRemainActions;
        }

        public void setLabelRemainActions (JLabel labelRemainActions){
            this.labelRemainActions = labelRemainActions;
        }

        public JLabel getLabelArtefacts () {
            return labelArtefacts;
        }

        public void setLabelArtefacts (JLabel labelArtefacts){
            this.labelArtefacts = labelArtefacts;
        }

        public ArrayList<JTextField> getTextFieldActions () {
            return textFieldActions;
        }

        public ArrayList<JTextField> getTextfieldNames () {
            return textfieldNames;
        }

        public ArrayList<JTextField> getTextfieldKeys () {
            return textfieldKeys;
        }

        public ArrayList<JTextField> getTextfieldArtefacts () {
            return textfieldArtefacts;
        }

        public ArrayList<Player> getPlayers () {
            return players;
        }


}
