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
        this.backgroundImage = new backgroundImage();
        this.backgroundImage.setPreferredSize(new Dimension(800,600));
        //this.setLayout(new BorderLayout());
        //this.setSize(1920,1080);
        this.setContentPane(backgroundImage);
        this.backgroundImage.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,10,10,10);

        JLabel titleLabel = new JLabel("ILE INTERDITE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        this.backgroundImage.add(titleLabel,gbc);

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
        gbc.insets = new Insets(20,10,20,10);
        this.backgroundImage.add(inputPanel,gbc);

        JButton startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(200,40));
        startButton.addActionListener(e->handleStartButton());
        gbc.insets = new Insets(10,10,50,10);
        this.backgroundImage.add(startButton,gbc);

        //inputPanel.setFocusable(false);
        //startButton.setFocusable(false);
        this.setSize(800,600);
        //this.backgroundImage.add(inputPanel, BorderLayout.CENTER);
        //this.backgroundImage.add(startButton, BorderLayout.SOUTH);
        //this.add(backgroundImage);
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