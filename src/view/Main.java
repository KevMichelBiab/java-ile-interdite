package view;
import model.*;
import Controller.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        island plat = new island();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("James"));
        players.add(new Player("Eli"));
        players.add(new Player("Lex"));

        Window window = new Window(plat,players);
        control ctrl = new control(plat, players);
        ctrl.setWindow(window);
        window.getGrid().addKeyListener(ctrl);
        window.getButtons().getFinDeTour().addActionListener(ctrl);

        /*plat.printIsland();*/







    }
}
