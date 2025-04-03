package view;
import model.*;
public class Main {
    public static void main(String[]args){
        island plat = new island();
        Window window = new Window(plat);
        zone playerZone = plat.getGrid()[0][0];
        player player1 = new player(playerZone);
    }
}
