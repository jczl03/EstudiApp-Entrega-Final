package Main;

import java.time.*;
import Clases.*;
import Controlador.*;
import Vista.*;
import Estructuras.*;
import java.util.Scanner;
import Modelo.BD.Conexion;
import Modelo.BD.Consult;
import Modelo.BD.ConsultTareas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Main {
   private static final  ArregloDinamico<Asignatura> asignaturas=new ArregloDinamico<Asignatura>();
   private static final AVLTree<Tarea> tareas=new AVLTree<Tarea>();
   private static final ArregloDinamico<Mazo> mazos=new ArregloDinamico<Mazo>();
   private static final ColaPriori<FlashCard> colaFlashCards=new ColaPriori<FlashCard>();
   private static final ColaPriori<Tarea> colaTareas=new ColaPriori<Tarea>();
   private static Conexion conexion;
    public static ArregloDinamico<Asignatura> asignaturas() {
        return asignaturas;
    }
///imagenes
    public static AVLTree<Tarea> tareas() {
        return tareas;
    }
    

    public static ArregloDinamico<Mazo> mazos() {
        return mazos;
    }
   
  public static void main(String[] args) {
   // ModeloPrototipo1.menu();
   // PruebasVista()
    
    conexion= new Conexion(getUser(),getPassword());
      try{
          FramePrincipal newframe = new FramePrincipal();
            ControladorPrincipal controlador=new ControladorPrincipal(newframe,conexion.getConexion());
            ConsultTareas consultxd=new ConsultTareas(conexion.getConexion());

            newframe.setVisible(true);
            JOptionPane.showMessageDialog(null, "Conexion Establecida");
      }catch(NullPointerException e){
          JOptionPane.showMessageDialog(null, "No se pudo establecer una conexion :(");
      }
      
   
  }

    public static ColaPriori<FlashCard> colaFlashCards() {
        return colaFlashCards;
    }
    public static ArregloDinamico<Asignatura> getAsignaturas(){
        return asignaturas;
    }
   /*public static String getUser()  {
       FileReader f = null;
       try {
           String cadena;
           f = new FileReader("c://user.txt");
           BufferedReader b = new BufferedReader(f);
           cadena = b.readLine();
           b.close();
           return cadena;
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
       } finally {
           try {
               f.close();
           } catch (IOException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       return null;
        
    }*/
    public static String getUser(){
       try {
           BufferedReader reader = new BufferedReader(new FileReader("user.txt"));
           String linea = reader.readLine();
           return linea;
       } catch (IOException ex) {
           Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
 
    }
    public static String getPassword(){
       try {
           BufferedReader reader = new BufferedReader(new FileReader("password.txt"));
           String linea = reader.readLine();
           return linea;
       } catch (IOException ex) {
           Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
 
    }
 
}