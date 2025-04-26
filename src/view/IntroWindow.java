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

    public IntroWindow(JFrame parent){
        super(parent, "Player Setup", true);
        setupUI();
    }

    public void setupUI(){
        this.setLayout(new BorderLayout());
        this.setSize(400,300);

        JPanel inputPanel = new JPanel(new GridLayout(3,2,10,10));
        this.nameFields = new JTextField[3];
        for(int i = 0; i<NUMPLAYERMAX; i++){
            JLabel label = new JLabel("Player " + (i+1) + " name: ");
            nameFields[i] = new JTextField();
            inputPanel.add(label);
            inputPanel.add(nameFields[i]);
        }

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e->{
            for(JTextField field : nameFields){
                String name = field.getText().trim();
                if(!name.isEmpty()){
                    playerNames.add(name);
                }

            }
            if(playerNames.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter at least one player name!","Input Required", JOptionPane.WARNING_MESSAGE);
            } else {
            dispose();
            }
        });
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(startButton, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }
}