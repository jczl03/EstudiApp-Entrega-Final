package Estructuras;
public class NodeListaEnlazada<T>{
    private T key;
    private NodeListaEnlazada<T> next;
    public NodeListaEnlazada(T key, NodeListaEnlazada<T> nodo){
        this.key=key;
        this.next=nodo;
    }
    public NodeListaEnlazada(T key){
        this(key,null);
        
    }
    public NodeListaEnlazada(){
        this(null);
    }
    public T getKey(){
        return key;
    }
    public void setNext(NodeListaEnlazada<T> nodo){
        this.next=nodo;
    }
    public NodeListaEnlazada<T> getNext(){
        return this.next;
    }
    public boolean hasNext(){
      if(next==null){
        return false;
      }
      return true;
    }
}