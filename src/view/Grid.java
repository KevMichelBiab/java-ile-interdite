package view;
import model.*;
import Controller.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grid extends JPanel implements Observer {
    private island plateau;




    public final static int TAILLE = 30;

    private ArrayList<Player> listOfPlayers;
    private Image backgroundImage;


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

    public Grid(island plateau, ArrayList<Player> players) {
        this.plateau = plateau;
        this.plateau.addObserver(this);
        this.listOfPlayers = new ArrayList<>(players);
        for (Player play : this.listOfPlayers) {
            play.addObserver(this);
        }


        Dimension dim = new Dimension((island.LARGEUR) * TAILLE + 1, (island.HAUTEUR) * TAILLE + 1);
        this.setPreferredSize(dim);
        setFocusable(true);
        requestFocusInWindow();
        setDoubleBuffered(true);


    }


    @Override
    public void update() {
        System.out.println("Grid updated!");
        repaint();
        System.out.println();
    }


    public void paintComponent(Graphics g) {

        for (int i = 0; i < island.LARGEUR; i++) {
            for (int j = 0; j < island.HAUTEUR; j++) {
                /*System.out.println("State at : "+ i + ", " + j + " : " + this.plateau.getGrid()[i][j].getState());*/
                paint(g, this.plateau.getGrid()[i][j], i, j);
            }
        }
        for (Player play : this.listOfPlayers) {
            drawPlayerNAme(g, play);
        }
    }

    public void paint(Graphics g, zone c, int x, int y) {
        /*System.out.println("Current cell state: " + c.getState());*/
        Color cellColor = new Color(255, 255, 255, 0);
        if (c.getState() == ZoneState.NORMAL) {
            cellColor = new Color(255, 255, 255, 0);
        }
        if (c.getState() == ZoneState.FLOODED) {
            cellColor = Color.GREEN;
        }
        if (c.getState() == ZoneState.SUNK) {
            cellColor = Color.BLACK;
        }
        if (c.getState() == ZoneState.ARTEFACTS) {
            cellColor = Color.ORANGE;
        }
        if (c.getState() == ZoneState.HELICOPTER) {
            cellColor = Color.MAGENTA;
        }

        g.setColor(cellColor);
        /*ystem.out.println("Drawn cell as " + g.getColor());
        System.out.println("X coordinate: " + x*TAILLE);
        System.out.println("Y coordinate: " + y*TAILLE );*/
        g.fillRect(y * TAILLE, x * TAILLE, TAILLE, TAILLE);
        g.setColor(Color.BLUE);
        g.drawRect(y * TAILLE, x * TAILLE, TAILLE, TAILLE);


    }

    public void drawPlayerNAme(Graphics g, Player play) {
        // Clear previous position first
        //paintCellBackground(play.getPreviousX(), play.getPreviousY());
        paintCellBackground(play.getPreviousX(), play.getPreviousY() );

        // Then draw new position
       // String playerName = play.getName().substring(0, Math.min(3, play.getName().length()));
        //Font font = new Font("Arial",Font.BOLD, 12);
       // g.setFont(font);

        String playerName = play.getName().substring(0, Math.min(3,play.getName().length()));
        Font font = new Font("Arial", Font.BOLD, 12);
        g.setFont(font);



        FontMetrics metrics = g.getFontMetrics();
        int nameWidth = metrics.stringWidth(playerName);

        // Calculate position

        int pixelX = play.getY()*TAILLE;// Find the left edge of the cell
        int pixelY = play.getX() * TAILLE;


        int centerX = pixelX +(TAILLE - nameWidth) / 2; //Center it horizontally
        int centerY = pixelY + (TAILLE / 2) + (metrics.getAscent() - metrics.getDescent()) / 2;
        g.setColor(Color.BLACK);
        g.drawString(playerName, centerX, centerY);

    }


    public  Color getCellColor(ZoneState state){
        //It is better to do an enhanced switch here
        return switch (state) {
            case NORMAL -> new Color(255, 255, 255, 0);
            case FLOODED -> Color.GREEN;
            case SUNK -> Color.BLACK;
            case ARTEFACTS -> Color.ORANGE;
            case HELICOPTER -> Color.MAGENTA;
        };
    }



    public void paintCellBackground(int x, int y){
        Graphics g = getGraphics();
        if(g != null){
            zone cell = plateau.getGrid()[x][y];
            Color cellColor = getCellColor(cell.getState());
            g.setColor(cellColor);
            g.fillRect(y*TAILLE, x*TAILLE, TAILLE, TAILLE);
            g.setColor(Color.BLUE);
            g.drawRect(y*TAILLE, x*TAILLE, TAILLE, TAILLE);
        }
    }
}
