package view;

import model.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
    private JScrollPane scrollPane;
    private JTextArea playerLog;


    private backgroundImage background;


    protected Window(island plateau, ArrayList<Player> players) {

        this.frame = new JFrame("ILE INTERDITE");
        this.frame.setSize(1920, 1080);
        this.frame.setLayout(new BorderLayout());
        this.background = new backgroundImage();



        // 1. Main background panel
        //JPanel mainPanel = new JPanel(new BorderLayout());
       // mainPanel.setBackground(new Color(135, 206, 235)); // Solid background
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(135,206,235));

        // 2. Add grid first
        //this.grid = new Grid(plateau, players);
        //grid.setOpaque(false);
        //mainPanel.add(grid, BorderLayout.CENTER);

        this.grid = new Grid(plateau, players);
        this.grid.setOpaque(false);
        mainPanel.add(this.grid, BorderLayout.CENTER);
        /*Now grid occupies the whole main panel*/

        // 3. Create overlay panel for UI elements
        //JPanel uiOverlay = new JPanel(new BorderLayout());
        //uiOverlay.setOpaque(false);
        JPanel uiOverlay = new JPanel(new BorderLayout());
        uiOverlay.setOpaque(false);

        // 4. Add your gameSettings panel to overlay
        //JPanel gameSettings = createGameSettingsPanel(players);
        //uiOverlay.add(gameSettings, BorderLayout.EAST);
        JPanel gameSettings = createGameSettingsPanel(players);
        uiOverlay.add(gameSettings, BorderLayout.EAST);
        /*Here we make another box containing our gameSetting on the right*/

        // 5. We layer the the box containing gamesetting and the box that represents grid in mainPanel
        //What this does is that grid and overlay are on top of each other
        //But since the gamesetting on the right in overlay it gives an illusion that it is on the right
        //of grid

        //mainPanel.add(uiOverlay, BorderLayout.CENTER);
        //frame.add(mainPanel);
        mainPanel.add(uiOverlay, BorderLayout.CENTER);
        frame.add(mainPanel);

        //this.buttons = new Buttons(plateau,this.grid);
        this.buttons = new Buttons(plateau, this.grid);
        this.buttons.setOpaque(false);
        // Add buttons at the top
        //gameSettings.add(buttons, BorderLayout.NORTH);
        gameSettings.add(buttons, BorderLayout.NORTH);

        // Player info panel
        //JPanel playerInfoContainer = new JPanel();
        //playerInfoContainer.setLayout(new BoxLayout(playerInfoContainer, BoxLayout.Y_AXIS));
        //playerInfoContainer.setOpaque(false);
        JPanel playerInfoContainer = new JPanel();
        playerInfoContainer.setLayout(new BoxLayout(playerInfoContainer, BoxLayout.Y_AXIS));
        playerInfoContainer.setOpaque(false);

        //for (Player player : players) {
           // playerInfoContainer.add(createPlayerPanel(player));
           // playerInfoContainer.add(Box.createVerticalStrut(10));
        //}
        for(Player player : players){
            playerInfoContainer.add(createPlayerPanel(player));
            playerInfoContainer.add(Box.createVerticalStrut(10));
        }

        // Add player info with scroll
        //JScrollPane playerScroll = new JScrollPane(playerInfoContainer);
        //playerScroll.setBorder(BorderFactory.createTitledBorder("Player Status"));
        //playerScroll.setOpaque(false);
        //playerScroll.getViewport().setOpaque(false);
        //gameSettings.add(playerScroll, BorderLayout.CENTER);

        JScrollPane playerScroll = new JScrollPane(playerInfoContainer);
        playerScroll.setBorder(BorderFactory.createTitledBorder("Player status"));
        playerScroll.setOpaque(false);
        playerScroll.getViewport().setOpaque(false);
        gameSettings.add(playerScroll, BorderLayout.CENTER);

        // Add game log at bottom
        //playerLog = new JTextArea();
        //playerLog.setEditable(false);
        //JScrollPane logScroll = new JScrollPane(playerLog);
        //logScroll.setBorder(BorderFactory.createTitledBorder("Game Log"));
        //logScroll.setPreferredSize(new Dimension(900, 300));
        //gameSettings.add(logScroll, BorderLayout.SOUTH);

        this.playerLog = new JTextArea();
        this.playerLog.setEditable(false);
        this.playerLog.setBackground(new Color(242,210,188));
        this.playerLog.setText("Welcome to the forbidden island. Your goal is to pick all the artefacts and reach the helicopter together before the whole island disappears");
        JScrollPane logScroll = new JScrollPane(this.playerLog);
        logScroll.setBorder(BorderFactory.createTitledBorder("Game Log"));
        logScroll.setPreferredSize(new Dimension(900,300));
        gameSettings.add(logScroll, BorderLayout.SOUTH);

        // Add components to background
        background.add(grid, BorderLayout.WEST);
        background.add(gameSettings, BorderLayout.EAST);
        this.frame.add(background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        //Creating a background music
        playBackgroundMusic();


        //Creating  a gameLog where information can be updated about the player


        grid.requestFocusInWindow();



    }


    private JPanel createGameSettingsPanel(ArrayList<Player> players){
        JPanel gameSettings = new JPanel(new BorderLayout());
        gameSettings.setPreferredSize(new Dimension(900,1080));
        gameSettings.setOpaque(false);
        return gameSettings;
    }
    /*private JPanel createPlayerPanel(Player player) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setMaximumSize(new Dimension(880, 80));
        panel.setOpaque(false);

        // Create text fields for each player attribute
        JTextField nameField = createTextField(player.getName());
        textfieldNames.add(nameField);

        JTextField keyField = createTextField(String.valueOf(player.getPlayerKey()));
        textfieldKeys.add(keyField);

        JTextField actionField = createTextField(String.valueOf(player.getActionsRemaining()));
        textFieldActions.add(actionField);

        JTextField artefactField = createTextField(String.valueOf(player.getCountArteFacts()));
        textfieldArtefacts.add(artefactField);

        // Add fields to the panel with labels
        addLabelField(panel, "Player:", nameField);
        addLabelField(panel, "Keys:", keyField);
        addLabelField(panel, "Actions:", actionField);
        addLabelField(panel, "Artefacts:", artefactField);

        return panel;
    }*/
    private JPanel createPlayerPanel(Player player){
        JPanel panel = new JPanel(new GridLayout(0,2,10,5));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setMaximumSize(new Dimension(800,80));
        panel.setOpaque(false);

        JTextField nameField = createTextField(player.getName());
        this.textfieldNames.add(nameField);

        JTextField keyField = createTextField(String.valueOf(player.getPlayerKey()));
        this.textfieldKeys.add(keyField);

        JTextField actionField = createTextField(String.valueOf(player.getActionsRemaining()));
        this.textFieldActions.add(actionField);

        JTextField artefactField = createTextField(String.valueOf(player.getActionsRemaining()));
        this.textfieldArtefacts.add(artefactField);

        addLabelField(panel, "Player:", nameField);
        addLabelField(panel, "Keys:", keyField);
        addLabelField(panel, "Actions:", actionField);
        addLabelField(panel, "Artefacts:", artefactField);

        return panel;

    }

    /*private JTextField createTextField(String value) {
        JTextField textField = new JTextField(value);
        textField.setEditable(false);
        textField.setBorder(null);
        textField.setOpaque(false);
        textField.setForeground(Color.YELLOW);
        return textField;
    }

    private void addLabelField(JPanel panel, String label, JTextField textField) {
        JLabel jLabel = new JLabel(label);
        jLabel.setForeground(Color.WHITE);
        panel.add(jLabel);
        panel.add(textField);
    }*/

    private JTextField createTextField(String value){
        JTextField textField = new JTextField(value);
        textField.setEditable(false);
        textField.setBorder(null);
        textField.setOpaque(false);
        textField.setForeground(Color.YELLOW);
        return textField;
    }

    private void addLabelField(JPanel panel, String label, JTextField textField){
        JLabel jLabel = new JLabel(label);
        jLabel.setForeground(Color.WHITE);
        panel.add(jLabel);
        panel.add(textField);
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

    public JTextArea getPlayerLog() {
        return playerLog;
    }

    public void setPlayerLog(JTextArea playerLog) {
        this.playerLog = playerLog;
    }

    public void playBackgroundMusic() {
        new Thread(() -> {
            try {
                File musicFile = new File("src/view/Mystery-Bazaar.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                clip.loop(Clip.LOOP_CONTINUOUSLY);

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
