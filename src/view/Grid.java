package view;
import model.*;
import Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Grid extends JPanel implements Observer{
    private island plateau;


    public final static int TAILLE = 30;

    private Player currentPlayer;

    public Grid(island plateau){
        this.plateau = plateau;
        this.plateau.addObserver(this);

        this.currentPlayer = new Player("ALEXANDER");
        this.currentPlayer.addObserver(this);


        Dimension dim = new Dimension(island.LARGEUR * TAILLE, island.HAUTEUR * TAILLE);
        this.setPreferredSize(dim);

        control ctrl = new control(this.plateau, this.currentPlayer);
        this.addKeyListener(ctrl);

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
            for(int j=0; j<island.HAUTEUR; j++) {

                paint(g, this.plateau.getGrid()[i][j],i ,j );
            }
        }
        this.drawPlayerNAme(g,this.currentPlayer.getX(),this.currentPlayer.getY());





    }

    public void paint(Graphics g, zone c, int x, int y){
        if(c.getState() == ZoneState.NORMAL){
            g.setColor(Color.WHITE);
        }
        if(c.getState() == ZoneState.FLOODED){
            g.setColor(Color.GREEN);
        }
        if(c.getState() == ZoneState.SUNK){
            g.setColor(Color.BLACK);
        }
        g.fillRect(x*TAILLE,y*TAILLE,TAILLE, TAILLE);
        g.setColor(Color.BLUE); //Draw the borders of the grid
        g.drawRect(x * TAILLE, y * TAILLE, TAILLE, TAILLE);




    }

    public void drawPlayerNAme(Graphics g, int x, int y){
         x = currentPlayer.getX();
         y = currentPlayer.getY();

        String playerName = currentPlayer.getName();
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);

        /*Font metrics a pour objectif de nous donner la taille et la hauteur du nom pour qu'on
        puisse faire calculs afin de centrer le nom dans la case*/

        FontMetrics metrics = g.getFontMetrics();
        int nameWidth = metrics.stringWidth(playerName);
        int nameHeight = metrics.getHeight();


        int maxNameWidth = TAILLE - 5; // Leave some padding for the name
        if (nameWidth > maxNameWidth) {
            playerName = playerName.substring(0, 3); // On fait le choix de reduire tous les noms a 3 caracteres
        }

        // Calculate the position to center the name in the cell
        int centerX = x + (TAILLE - nameWidth) / 2;  // Center horizontally
        int centerY = y + (TAILLE + nameHeight) / 2 - metrics.getDescent();  // Center vertically

        // Draw the name
        g.setColor(Color.RED);
        g.drawString(playerName, centerX, centerY);
    }

}
