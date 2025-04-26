package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;

public class backgroundImage extends JPanel {
    private Image backgroundImage;

    public backgroundImage(){
        try {
            // Load image from the 'resources' folder (package path)
            backgroundImage = ImageIO.read(new File("src/view/JUNGLE IMAGE.jpg"));
        } catch (IOException e) {
            e.printStackTrace();  // Handle error if image is not found
        }
        this.setLayout(new BorderLayout());
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this); // Draw it at coordinates (0, 0)
        }
    }

}
