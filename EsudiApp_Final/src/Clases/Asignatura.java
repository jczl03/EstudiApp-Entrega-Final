package Clases;
public class Asignatura{
  String nombre;
  int id;
  int creditos;
  int importancia;

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getImportancia() {
        return importancia;
    }

    public Asignatura(String nombre, int id, int creditos, int importancia) {
        this.nombre = nombre;
        this.id = id;
        this.creditos = creditos;
        this.importancia = importancia;
    }
    public Asignatura(String nombre, int id, int importancia){
        this(nombre,id,-1,importancia);
    }
    public String toString(){
        return nombre;
    }
}