package view;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel implements Observer{
    private island plateau;

    private final static int TAILLE = 12;

    public Grid(island plateau){
        this.plateau = plateau;
        this.plateau.addObserver(this);

        Dimension dim = new Dimension(100,400);
        this.setPreferredSize(dim);
    }


    public void update() { repaint(); }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0; i<island.LARGEUR; i++) {
            for(int j=0; j<island.HAUTEUR; j++) {

                paint(g, this.plateau.getGrid()[i][j],i ,j );
            }
        }

    }

    public void paint(Graphics g, zone c, int x, int y){
        if(c.getState() == ZoneState.NORMAL){
            g.setColor(Color.WHITE);
        }
        if(c.getState() == ZoneState.FLOODED){
            g.setColor(Color.GRAY);
        }
        if(c.getState() == ZoneState.SUNK){
            g.setColor(Color.BLACK);
        }
        g.fillRect(x*TAILLE,y*TAILLE,c.getWidth(), c.getHeight());

    }

}
