/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Delgado
 */
public class Poderes {
    Bola bola;
    int x;
    int y;
    boolean estado;
    JLabel label = new JLabel();
    int poder;
    public Poderes(int x) {       
        poder = (int) (Math.random()*2);
        label.setBounds(x,-100, 75, 40);
        System.out.println(poder);
        if(poder == 0){
        ImageIcon imagen= new ImageIcon(getClass().getResource("/img/Azul.png"));
        ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(75, 40,Image.SCALE_DEFAULT)) ;
        label.setIcon(icono);
        }
        
        if(poder == 1){
        ImageIcon imagen= new ImageIcon(getClass().getResource("/img/rojo.png"));
        ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(75, 40,Image.SCALE_DEFAULT)) ;
        label.setIcon(icono);
        }
        
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
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
    
    public void moverPoder(){
        label.setLocation(getX(),getY());   
    }
}
