package Clases;
import java.time.*;
import java.util.Date;
import java.sql.Time;
public class Recordatorio extends MiniTarea{
  
  Date fecha;
  Time hora;
  String nombre;
  String descripcion;

    public Recordatorio(Date fecha, Time hora, String nombre, String descripcion,int id) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombre = nombre;
        this.descripcion = descripcion;
        super.id=id;
        super.tipo=4;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }
  
  public void print(){
    System.out.println("__Recordatorio: "+nombre+"__");
    System.out.print(fecha+"  ");
    if(hora==null){
        System.out.print(hora);
    }
    System.out.println("");
    System.out.println(descripcion);
  }

    @Override
    public String toString() {
        String r1="";
        if(hora!=null){
            r1="  "+hora.toString();
        }
        String r="__Recordatorio: "+nombre+"__\n"+fecha+r1+"\n"+descripcion;
        return r;
    }
}