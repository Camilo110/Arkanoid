/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Urrea
 */
public class ManejadorDB {
    
    Connection conexion;
    Object arreglo [];
  
    JTable tabla;
    JScrollPane scroll;
    DefaultTableModel modelo;
    public ManejadorDB(){ 
    }
    
    public void crearConexion(){
        
        conexion=null;  
        System.out.println("conectando...");
        try {
            conexion=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "camilo2002");
            if(conexion != null) System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Algo salio mal con la conexión");
        }        
    }
    
    public void cerrarConextion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("No cerró correctamente");
        }
    }
    
    public void ejecutarConsulta(String consulta){
        crearConexion();        
        try {
            Statement s = conexion.createStatement();
            s.executeUpdate(consulta);
        } catch (SQLException ex) {
            System.out.println("Error en la consulta");
        }
        cerrarConextion();
    }
 
    
   public void crearModelo(){
        modelo = new DefaultTableModel();
        modelo.addColumn("nombre");
        modelo.addColumn("puntaje");
        tabla = new JTable(modelo);
        scroll = new JScrollPane(tabla);        
    }
    
    public void consultarFilas(){
        crearConexion();
        crearModelo();
        
        try {
            Statement s = conexion.createStatement();
            String consulta = " SELECT nombre, puntajes\n" +
"	FROM public.\"Puntajes\";";
            ResultSet resultado = s.executeQuery(consulta);
            
            while(resultado.next()){
                Object arreglo [] = new Object[2];
                System.out.println("Sale de resultado");
                for(int i = 0; i < 2; i++){
                    arreglo[i] = resultado.getObject(i+1);   
                    System.out.println(""+ resultado.getObject(i+1));
                }
                modelo.addRow(arreglo);
            }
            
        } catch (SQLException ex) {
            System.out.println("Problemas al consultar");
        }
        
        cerrarConextion();
        JOptionPane.showMessageDialog(null, scroll);
    }

    private String toString(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
