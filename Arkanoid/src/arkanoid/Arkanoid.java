/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;


import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author
 * Eliam Avila
 * Carlos Delgado
 * Camilo Urrea
 */
public final class Arkanoid extends JFrame{
    
    
    Bloque bloques[] = new Bloque[28]; // array de la clases "bloques", almacena JLabels.
    Poderes poderes; //variable de la clase poderes, que es un Jlabel
    Base base;//variable de la clase base, que es un Jlabel
    Bola bola;//variable de la clase Bola, que es un Jlabel
    Timer timer;
    int contadorMilesimas,contadorSegundos,contadorMinutos, puntuacion,contbloques;
    JLabel tiempo,puntaje,aviso;
    ArrayList<JLabel> vidas; // en este array se alamcen los JLabels para mostrar los coranzones.
    ManejadorDB BD;
    String nombre;
    boolean aux;
    
    
    public Arkanoid(){
        super("Arkanoid"); 
        BD = new ManejadorDB();
        vidas = new ArrayList<JLabel>();
        contadorMilesimas = 0;
        contadorSegundos = 0;
        contadorMinutos = 0;
        puntuacion = 0;
        puntaje = new JLabel("Puntaje: "+ puntuacion);
        tiempo = new JLabel("00:00:00");
        tiempo.setBounds(1100, 200, 50, 50);
        this.add(tiempo);
        Container contenedor = getContentPane();
        Manejador manejador =  new Manejador();
        aux = false;
        contbloques=0;
        nombre = "";
        timer = new Timer(10,null);
        timer.addActionListener(manejador);
        puntaje.setBounds(1000,80, 100, 50);
        this.addKeyListener(manejador);
        contenedor.setLayout(null);
        pintarBola(); 
        configuracionVentana();
        pintarBloques();    
        pintarBase();
        crearVidas(3);
        pintarVidas(); 
    }
    
     public void configuracionVentana(){
        this.setSize(1200,600);
        this.setLayout(null);
        this.add(puntaje);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);    
    }
    boolean poderY = false;
     
// en este metodo se pintan las vidas recorriendo el array "vidas".    
    public void pintarVidas(){
        int x = 1000;
        for (int i = 0; i < vidas.size(); i++) {
        ImageIcon imagen= new ImageIcon(getClass().getResource("/img/Corazon.png"));
        ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT));
        vidas.get(i).setBounds(x, 1, 90,90);
        vidas.get(i).setIcon(icono);
        this.add(vidas.get(i));
        x += 30; // espacio entre cada corazon.
        }        
    }
    

    
    //alamcena una cantida "n" de Jlabels (corazones) en el array vidas.
    public void crearVidas(int n){
        for (int i = 0; i < n; i++) {
        JLabel vida = new JLabel();
        vidas.add(vida);
        }   
    }
    
    int dirX = 1;
    int dirY = 1;
    int random = 0;
    
    // aqui se gestiona el moviemiento de cada poder.   
    public void movimientoPoder(){
        //se guardan en varuables la coordenasa x,y de cada poder
        int x1 = poderes.getX();
        int y1 = poderes.getY();
        if(x1 >= base.getX()-20 && x1 <= base.getX()+150){ // en este if se compara la posicion del poder con la de base en X.
            if(y1 == base.getY()-40){ //en este if 
                poderes.getLabel().setVisible(false);
                poderY = false;
                if(poderes.getPoder() == 0){
                    timer.setDelay(30);
                }
                
                if(poderes.getPoder() == 1){
                    puntuacion+=10;
                    puntaje.setText("Puntaje: " + puntuacion);
                }
            }
        }
        poderes.setY(y1 + 2);
        poderes.setX(random);
        poderes.moverPoder();
    }
    
    public void movimientoPelota(){
              
        int y = bola.getY();
        int x = bola.getX();  
        
        for (int i = 0; i < bloques.length; i++) { // se hace un for para mira cada bloque
            if(x >= bloques[i].getX() && x<= bloques[i].getX()+125){ // se compara la posicion en X
                if(y >= bloques[i].getY() && y <= bloques[i].getY()+30){ // se compara la posicion en y
                    if(bloques[i].getEstado()){ 
                    bloques[i].getLabel().setVisible(false);
                    bloques[i].setEstado(false);
                    dirY *= -1;
                    puntaje.setText("Puntaje: "+ (puntuacion += 5));
                    contbloques++;
                    
                    if(puntuacion % 25 == 0){
                        poderY = true;
                        random = (int) (Math.random()*900);
                        poderes = new Poderes(random);
                        this.add(poderes.getLabel());
                        base.getLabel().setBounds(base.getX(), base.getY(), 150, 20);
                        timer.setDelay(10);
                    }
                    int variable1=0;
                    do{
                    if (contbloques == 28){
                        if(aux){
                            BD.ejecutarConsulta("INSERT INTO public.\"Puntajes\"(\n" +
                                      "	nombre, puntajes)\n" +
                                      "	VALUES ('" + nombre + "','"+puntaje.getText()+"');");
                        }
                        String [] botones = { "Reintentar", "Ver puntajes"};
                        variable1 = JOptionPane.showOptionDialog
                                       (null, "GANASTEE!! \n ¿Que deseas hacer?", "Fin del juego", 
                                       JOptionPane.YES_NO_CANCEL_OPTION, 
                                       JOptionPane.WARNING_MESSAGE,null/*icono*/, 
                                       botones, botones[0]);
                        if(variable1 == 0){ //si la opcion que se escoge es la cero (Reintentar) se reinicia la aplicacion                               
                            Arkanoid arkanoid = new Arkanoid();
                            arkanoid.setVisible(true);
                            this.dispose();
                        }
                        if(variable1 == 1){
                             BD.consultarFilas();
                        }
                    }
                    }while(variable1 == 1);
                    }
                } 
            }
        }
    
        
        if(y == base.getY()-50){
            if(x >= base.getX()-35 && x<= base.getX()+136){ // se verifica la posicion de la bola con la posicion de la base.
                    dirY *= -1; // si esto es verdadero se cambio la direccion de movimiento en Y.
            }
        }
        
        if(bola.getY() >= 600-80){
            timer.stop();
            if(vidas.size() >= 1){
            vidas.remove(0);
            pintarVidas();
            try{
            poderes.getLabel().setVisible(false);
            }catch(Exception ex){
            }
            }
            if (vidas.size() == 0){//si las vidas son 0 se da la ocpion de escoger si se continua o no.
                if(aux){
                  BD.ejecutarConsulta("INSERT INTO public.\"Puntajes\"(\n" +
                                      "	nombre, puntajes)\n" +
                                      "	VALUES ('" + nombre + "','"+puntaje.getText()+"');");
               }
                String [] botones = { "Reintentar", "Ver puntajes"};
                int variable;
                do{
                variable = JOptionPane.showOptionDialog
                                       (null, "Perdiste \n ¿Que deseas hacer?", "Fin del juego", 
                                       JOptionPane.YES_NO_CANCEL_OPTION, 
                                       JOptionPane.WARNING_MESSAGE,null/*icono*/, 
                                       botones, botones[0]);
                if(variable == 0){ //si la opcion que se escoge es la cero (Reintentar) se reinicia la aplicacion                               
                    Arkanoid arkanoid = new Arkanoid();
                    arkanoid.setVisible(true);
                    this.dispose();
                }
                if(variable == 1){
                    BD.consultarFilas();   
                }
                }while(variable==1);
            }
            y =300;
            x =400;
            dirX = 1;
            dirY = 1;
        }
            
        if(bola.getX() == 0){
            dirX *= -1;
        }
        if(bola.getX() >=900){
            dirX *= -1;
        }
        if(bola.getY() <= 0){
            dirY *= -1;
        }
       
        if(dirX > 0){
            bola.setX(x + 2);
        }else {
            bola.setX(x - 2);
        }
        if(dirY >0){
            bola.setY(y + 2);
        }else{
            bola.setY(y - 2);
        }
        bola.moverBola();  
    }
    
    public void pintarBloques(){
        int x = 45;
        int y = 10;
        int contador = 1;
        
        
        for (int i = 0; i < bloques.length; i++) {
            if(contador == 7){
                bloques[i] = new Bloque(x,y,true);
                x = 45;
                y += 35;
                contador = 1;
            }else{                
                bloques[i] = new Bloque(x,y,true);
                x += 130;
                contador++;
            }
        }
        for (int i = 0; i < bloques.length; i++) {
            this.add(bloques[i].getLabel());
        }
    }
    
    public void pintarBase(){
        base = new Base(400,550,true);
        this.add(base.getLabel());
    }
    public void pintarBola(){
        bola = new Bola(400,300,true);
        this.add(bola.getLabel());
    }

    
    private class Manejador implements  ActionListener, KeyListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            movimientoPelota();   
            if(poderY){
            movimientoPoder();}
            contadorMilesimas++;
            if(contadorMilesimas==60){  
                contadorSegundos++;
                contadorMilesimas=0;                
                if(contadorSegundos==60){
                    contadorMinutos++;
                    contadorSegundos=0;
                }
            }            
            tiempo.setText(contadorMinutos+":"+contadorSegundos+":"+contadorMilesimas); 
        }

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar() == 'p' || e.getKeyChar() == 'P'){
                if(timer.isRunning()){
                    timer.stop();
                }else{
                    timer.start();
                }
            }
            int x = base.getX();
            int y = base.getY();
            
            if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT){
                if(timer.isRunning()){
                    if(x == 0){
                    }else{
                        base.setX(x-20);
                        base.moverBase();
                    }
                }           
            }
            if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT){
                if(timer.isRunning()){
                    if(x >= 1000-160){
                    }else{
                        base.setX(x+20);
                        base.moverBase();
                    }
                } 
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {  
        } 
    } 
}