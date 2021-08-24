package Estructuras;
public class Fila <T>{
	  public ListaEnlazada<T> lista;
	  public Fila(){
	    lista=new ListaEnlazada<T>();
	  }
	  public void enqueue(T a){
	    lista.PushBack(a);
	  }
	  public T dequeue(){
            T temp=lista.TopFront();
	    lista.PopFront();
      return temp;
	  }
    public boolean Empty(){
      return lista.Empty();
    }
	  public void Print(){
	    lista.Print();
	  }
          public void clear(){
              this.lista.clear();
          }
	}