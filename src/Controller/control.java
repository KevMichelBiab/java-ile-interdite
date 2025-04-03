package Controller;
import model.*;
import view.*;
import Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class control implements ActionListener {
    public island mod;

    public control(island mod){
        this.mod = mod;
    }


    public void actionPerformed(ActionEvent e) {

        System.out.println("Button presseed!");
        mod.init();
    }

}
