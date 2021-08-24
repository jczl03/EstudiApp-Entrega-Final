package Clases;
import Estructuras.ArregloDinamico;
//import java.time.*;
import java.sql.Date;
import java.sql.Time;
public class Tarea implements Comparable<Tarea>{
  private ArregloDinamico<MiniTarea> lista;
  private String nombre;
  private int id;
  private int asignatura;
  private Date fecha;
  private Time hora;
  private int importancia;

    public ArregloDinamico<MiniTarea> getLista() {
        return lista;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }

    public java.sql.Time getHora() {
        return hora;
    }

    public int getImportancia() {
        return importancia;
    }

    public Tarea(String nombre, int id, int asignatura, Date fecha, Time hora, int importancia) {
        lista=new ArregloDinamico<MiniTarea>();
        this.nombre = nombre;
        this.id = id;
        this.asignatura = asignatura;
        this.fecha = fecha;
        this.hora = hora;
        this.importancia = importancia;
    }
    public Tarea(String nombre, int id, int asignatura, Date fecha, int importancia) {
        this(nombre,id,asignatura,fecha,null,importancia);
    }
  
  public Tarea(){
    lista=new ArregloDinamico<MiniTarea>();
  }
  public void addMiniTarea(MiniTarea miniTarea){
    lista.Push(miniTarea);
  }
  public int getAsignatura(){
      return asignatura;
  }

    public String getNombre() {
        return nombre;
    }
  public void removerTarea(int idx){
    lista.Remove(idx);
  }
  public MiniTarea getTarea(int idx){
    return lista.Get(idx);
  }
  public int tamano(){
    return lista.size();
  }
  public void print(){
      System.out.println("______________________");
      System.out.println("Nombre de la Tarea: "+nombre );
      if(id!=-1){
          System.out.println("Asignatura: "+id);
      }
      
      System.out.println("Fecha de entrega: "+fecha);
      System.out.print("Hora de entrega: ");
      if(hora==null){
        System.out.println("No especifica");
      }else{
        System.out.println(hora);
      }
      System.out.println("______MiniTareas______");
      System.out.println("");
      for(int i=0;i<tamano();i++){
          System.out.printf("______MiniTarea #%d______\n",i);
          getTarea(i).print();
      }
      System.out.println("Fin de Tarea "+nombre);
      System.out.println("______________________");
  }
 /* public String toString(){
      String r1="";
      String r2="";
      
      if(id!=-1){
          r1=String.format("Asignatura: %d\n",id);
      }
      if(hora!=null){
          r2=hora.toString();
      }else{
          r2="No especifica";
      }
      String minitareas="";
      if(tamano()==0){
          minitareas="No hay minitareas\n";
      }else{
          for(int i=0;i<tamano();i++){
              minitareas=minitareas+String.format("______MiniTarea #%d______\n%s",i+1,getTarea(i));
          }
      }
      
       String r=String.format("______________________\nNombre de la Tarea: %s\n%sFecha de entrega: "+fecha+"\nHora de entrega: %s\n%s",nombre,r1,r2,minitareas);
       
      return r;
  }*/
  public String toString(){
      return nombre;
  }
  public int compareTo(Tarea tarea){
      if(this.fecha.equals(tarea.getFecha())){
          if(this.importancia==tarea.getImportancia()){
              if(this.hora.equals(tarea.getHora())){
                return 0;
             }else{
                   if(this.hora.compareTo(tarea.getHora())>0){
                       return 1;
                   }else{
                       return -1;
                   }
             }
          }else{
              if(this.importancia>tarea.getImportancia()){
                  return 1;
              }else{
                  return -1;
              }
          }
      }else{
          if(this.fecha.compareTo(tarea.fecha)>0){
              return 1;
          }else{
              return -1;
          }
      
      }
  }

}