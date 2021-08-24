package Clases;
public abstract class MiniTarea{
  int id;
  int tipo;
  boolean cumplido;
  abstract public void print();
  abstract public String toString();

    public int getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public boolean isCumplido() {
        return cumplido;
    }
  
}