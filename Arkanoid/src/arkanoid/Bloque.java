/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Avyla
 */
public class Bloque {
    
    int x;
    int y;
    boolean estado;
    private JLabel label = new JLabel();
    public Bloque(int x, int y, boolean estado) {
        this.x = x;
        this.y = y;
        this.estado = estado;       
        label.setBounds(x, y, 125, 30);
        ImageIcon imagen= new ImageIcon(getClass().getResource("/img/Bloque.jpg"));
        ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(125, 30,Image.SCALE_DEFAULT)) ;
        label.setIcon(icono);
    }

    public JLabel getLabel() {
        return label;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
