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
public class Bola {
    
    int x;
    int y;
    boolean estado;
    JLabel label = new JLabel();
    public Bola(int x, int y, boolean estado) {
        this.x = x;
        this.y = y;
        this.estado = estado;
        if(estado){
        label.setBounds(x, y, 50 ,50);
        ImageIcon imagen= new ImageIcon(getClass().getResource("/img/Bola.png"));
        ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT)) ;
        label.setIcon(icono);
        }
    }
    
    public JLabel getLabel(){
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public void moverBola(){
        label.setLocation(getX(),getY());
    }   
    
}
