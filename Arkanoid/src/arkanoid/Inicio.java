/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Urrea
 */
public class Inicio extends JFrame{
    Arkanoid arkanoid;
    Manejador manejador;
    JLabel label;
    JButton iniciar,registrar;
    ManejadorDB BD;
  
    
    public Inicio(){
        setSize(800,600);
        setLocation(0,0);
        setBackground(Color.white);
        setVisible(true);
        
        BD = new ManejadorDB();
        manejador = new Manejador();
        iniciar = new JButton("INGRESAR");
        registrar = new JButton("REGISTRAR");
        iniciar.setBounds(350, 200, 100, 50);
        registrar.setBounds(350, 250, 100, 50);
        iniciar.addActionListener(manejador);
        registrar.addActionListener(manejador);
        iniciar.setBackground(Color.LIGHT_GRAY);
        registrar.setBackground(Color.LIGHT_GRAY);
        this.add(iniciar);
        this.add(registrar);
        arkanoid = new Arkanoid();
        
    }
    
    public void cerrar(){
        this.dispose();
    }
    
    public static void main(String[] args) {      
        Inicio inicio = new Inicio();
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class Manejador implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //int id = 
            
            if(e.getSource() == iniciar){
               JOptionPane.showMessageDialog(null,"Sus puntajes no quedarán guardados");
               arkanoid.setVisible(true);
               cerrar();
            }
            
            if(e.getSource() == registrar){
               //arkanoid.setVisible(true);
               
                arkanoid.nombre = JOptionPane.showInputDialog("Ingrese Su Nombre o Apodo");
                arkanoid.aux= true;
                arkanoid.setVisible(true);
                cerrar();
            }
            
        }
    }
    
}
