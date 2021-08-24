package Estructuras;
public class Pila<T>{
	  public ListaEnlazada<T> lista;
	  public Pila(){
	    lista = new ListaEnlazada<T>();
	  } 
	  public void Push(T a){
	    lista.PushFront(a);
	  }
	  public T Pop(){
		  T r=lista.TopFront();
		  lista.PopFront();
		  return r;
	  }
	  public T peek() {
		  return lista.TopFront();
	  }
	  public void Print(){
	    lista.Print();
	  }
	  public boolean isEmpty(){
	    return lista.Empty();
	  }
	}