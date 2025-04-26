package view;
import model.*;
import Controller.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        IntroWindow intro = new IntroWindow(null);
        intro.setVisible(true);

        // Get names from intro window

        ArrayList<String> names = intro.getPlayerNames();
        if(names.isEmpty()){
            System.exit(0);
        }
        ArrayList<Player> players = new ArrayList<>();
        for(String name : names){
            players.add(new Player(name));
        }
        // Create players with entered names

        island plat = new island();
        Window window = new Window(plat,players);
        control ctrl = new control(plat, players);
        ctrl.setWindow(window);
        window.getGrid().addKeyListener(ctrl);
        window.getButtons().getFinDeTour().addActionListener(ctrl);
        window.getButtons().getPartyWon().addActionListener(ctrl);
        window.getButtons().getKeyExchange().addActionListener(ctrl);

        /*plat.printIsland();*/


    }
}
