package Clases;
public class EnlaceWeb extends MiniTarea{
  String nombre;
  String enlace;
  public EnlaceWeb(String nombre, String enlace,int id){
    this.nombre=nombre;
    this.enlace=enlace;
    super.id=id;
    super.tipo=2;
  }
  public void print(){
    System.out.println("Nombre: "+ nombre);
    System.out.println("Enlace: "+ enlace);
  }

    public String getNombre() {
        return nombre;
    }

    public String getEnlace() {
        return enlace;
    }

    public String toString() {
       return String.format("Nombre: %s\nEnlace: %s\n",nombre,enlace);
    }
}