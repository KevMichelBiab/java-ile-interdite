package view;
import model.*;
import Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grid extends JPanel implements Observer{
    private island plateau;


    public final static int TAILLE = 30;

    private ArrayList<Player> listOfPlayers;



    public ArrayList<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    public void setListOfPlayers(ArrayList<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }

    public island getPlateau() {
        return plateau;
    }

    public void setPlateau(island plateau) {
        this.plateau = plateau;
    }

    public Grid(island plateau, ArrayList<Player> players){
        this.plateau = plateau;
        this.plateau.addObserver(this);

        this.listOfPlayers = new ArrayList<>(players);

        for(Player play : this.listOfPlayers){

            play.addObserver(this);


        }





        Dimension dim = new Dimension((island.LARGEUR) * TAILLE, (island.HAUTEUR) * TAILLE);
        this.setPreferredSize(dim);



        setFocusable(true);


    }



    @Override
    public void update() {
        System.out.println("Grid updated!");
        repaint();
        System.out.println();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0; i<island.LARGEUR; i++) {
            for(int j=0; j<island.HAUTEUR ; j++) {
                System.out.println("State at : "+ i + ", " + j + " : " + this.plateau.getGrid()[i][j].getState());
                paint(g, this.plateau.getGrid()[i][j],i ,j );

            }

        }
        for(Player play: this.listOfPlayers) {
            drawPlayerNAme(g, play);
        }





    }

    public void paint(Graphics g, zone c, int x, int y){
        System.out.println("Current cell state: " + c.getState());
        if(c.getState() == ZoneState.NORMAL){

            g.setColor(Color.WHITE);
        }
        if(c.getState() == ZoneState.FLOODED){

            g.setColor(Color.GREEN);
        }
        if(c.getState() == ZoneState.SUNK){

            g.setColor(Color.BLACK);
        }
        if(c instanceof ArtefactsZones){
            g.setColor(Color.ORANGE);
        }
        if(c.getState() == ZoneState.HELICOPTER){

            g.setColor(Color.MAGENTA);
        }
        System.out.println("Drawn cell as " + g.getColor());
        System.out.println("X coordinate: " + x*TAILLE);
        System.out.println("Y coordinate: " + y*TAILLE );
        g.fillRect(y*TAILLE,x*TAILLE,TAILLE, TAILLE);
        g.setColor(Color.BLUE);
        g.drawRect(y * TAILLE, x * TAILLE, TAILLE, TAILLE);




    }

    public void drawPlayerNAme(Graphics g, Player play){


        String playerName = play.getName();
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);

        /*Font metrics a pour objectif de nous donner la taille et la hauteur du nom pour qu'on
        puisse faire calculs afin de centrer le nom dans la case*/

        FontMetrics metrics = g.getFontMetrics();
        int nameWidth = metrics.stringWidth(playerName);
        int nameHeight = metrics.getHeight();


        int maxNameWidth = TAILLE - 5;
        if (nameWidth > maxNameWidth) {
            playerName = playerName.substring(0, 3); // On fait le choix de reduire tous les noms a 3 caracteres
        }

        // Calculate the position to center the name in the cell
        int pixelX = (play.getX())* Grid.TAILLE;
        int pixelY = (play.getY())* Grid.TAILLE;
        int centerX = pixelX + (TAILLE - nameWidth) / 2;
        int centerY = pixelY + (TAILLE + nameHeight) / 2 - metrics.getDescent();


        // Draw the name
        g.setColor(Color.RED);
        g.drawString(playerName, centerX, centerY);
    }




}
