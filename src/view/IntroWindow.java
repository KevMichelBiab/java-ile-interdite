package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class IntroWindow extends JDialog {
    private ArrayList<String> playerNames = new ArrayList<>();
    private JTextField[] nameFields;
    public static final int NUMPLAYERMAX = 3;
    private backgroundImage backgroundImage;

    public IntroWindow(JFrame parent){
        super(parent, "Player Setup", true);
        setupUI();
    }

    public void setupUI(){
        //Set the background and image size
        this.backgroundImage = new backgroundImage();
        this.backgroundImage.setPreferredSize(new Dimension(800,600));

        //The background is now the main content panel
        this.setContentPane(backgroundImage);
        //GridBagLayout is used to set exactly how wide the component will be , where to place and the margins around the component
        this.backgroundImage.setLayout(new GridBagLayout());

        //gbc controls the how we place elements
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; //The component takes the full row
        gbc.anchor = GridBagConstraints.CENTER;// The elements are at the center
        gbc.insets = new Insets(10,10,10,10); //Add some space around the components

        //The title of the intro screen
        JLabel titleLabel = new JLabel("ILE INTERDITE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        this.backgroundImage.add(titleLabel,gbc);

        //the fields where we can input player names
        JPanel inputPanel = new JPanel(new GridLayout(3,2,10,10));
        inputPanel.setOpaque(false);
        this.nameFields = new JTextField[3];
        for(int i = 0; i<NUMPLAYERMAX; i++){
            JLabel label = new JLabel("Player " + (i+1) + " name: ");
            label.setForeground(Color.WHITE);
            nameFields[i] = new JTextField(15);
            inputPanel.add(label);
            inputPanel.add(nameFields[i]);
        }

        //The fields are below the title
        gbc.insets = new Insets(20,10,20,10);
        this.backgroundImage.add(inputPanel,gbc);

        //Create start button
        JButton startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(200,40));
        startButton.addActionListener(e->handleStartButton());
        gbc.insets = new Insets(10,10,50,10);
        this.backgroundImage.add(startButton,gbc);

        this.setSize(800,600);
        setLocationRelativeTo(null);
    }

    public void handleStartButton(){
        this.playerNames.clear();
        for(JTextField field : this.nameFields){
            String name = field.getText().trim();
            if(!name.isEmpty()){
                this.playerNames.add(name);
            }
        }
        if(this.playerNames.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter at least one player name!","Input Required", JOptionPane.WARNING_MESSAGE);
        } else {
            dispose();

        }
    }

    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }
}