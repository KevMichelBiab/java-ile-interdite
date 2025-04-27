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

        //Create a box where this.grid and gameSettings will be layered.
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(135,206,235));

        this.grid = new Grid(plateau, players);
        this.grid.setOpaque(false);
        mainPanel.add(this.grid, BorderLayout.CENTER);

        //The box containing gameSettings and all its components
        JPanel uiOverlay = new JPanel(new BorderLayout());
        uiOverlay.setOpaque(false);

        JPanel gameSettings = createGameSettingsPanel(players);
        uiOverlay.add(gameSettings, BorderLayout.EAST);

        mainPanel.add(uiOverlay, BorderLayout.CENTER);
        frame.add(mainPanel);

        //The buttons on top
        this.buttons = new Buttons(plateau, this.grid);
        this.buttons.setOpaque(false);

        gameSettings.add(buttons, BorderLayout.NORTH);


        JPanel playerInfoContainer = new JPanel();
        playerInfoContainer.setLayout(new BoxLayout(playerInfoContainer, BoxLayout.Y_AXIS));
        playerInfoContainer.setOpaque(false);

        for(Player player : players){
            playerInfoContainer.add(createPlayerPanel(player));
            playerInfoContainer.add(Box.createVerticalStrut(10));
        }

        //this is where we see player info. They are all contained in their own box represented by playerInfoContainer above
        JScrollPane playerScroll = new JScrollPane(playerInfoContainer);
        playerScroll.setBorder(BorderFactory.createTitledBorder("Player status"));
        playerScroll.setOpaque(false);
        playerScroll.getViewport().setOpaque(false);
        gameSettings.add(playerScroll, BorderLayout.CENTER);

        //This is where the game log will be appaeaar
        this.playerLog = new JTextArea();
        this.playerLog.setBackground(new Color(151,255,193));
        this.playerLog.setText("Welcome to the forbidden island. Your goal is to pick all the artefacts and reach the helicopter together before the whole island disappears");

        //this makes the game log scrollable
        JScrollPane logScroll = new JScrollPane(this.playerLog);
        logScroll.setBorder(BorderFactory.createTitledBorder("Game Log"));
        logScroll.setPreferredSize(new Dimension(900,300));
        gameSettings.add(logScroll, BorderLayout.SOUTH);

        //We add everything in the background and the background will be added in the window later
        background.add(grid, BorderLayout.WEST);
        background.add(gameSettings, BorderLayout.EAST);
        this.frame.add(background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        //Creating a background music
        playBackgroundMusic();

        //We use that to make sure that the grid has the focus all the time
        grid.requestFocusInWindow();



    }


    private JPanel createGameSettingsPanel(ArrayList<Player> players){
        JPanel gameSettings = new JPanel(new BorderLayout());
        gameSettings.setPreferredSize(new Dimension(900,1080));
        gameSettings.setOpaque(false);
        return gameSettings;
    }

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







    public Grid getGrid() {
        return grid;
    }


    public Buttons getButtons() {
        return buttons;
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
    public JTextArea getPlayerLog() {
        return playerLog;
    }

    public void playBackgroundMusic() {
        //The new thread is used to make sure it runs at the same as the game
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
