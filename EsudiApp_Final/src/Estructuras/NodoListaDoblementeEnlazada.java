
package Estructuras;

public class NodoListaDoblementeEnlazada<T extends Comparable<? super T>> {
    private NodoListaDoblementeEnlazada<T> next;
    private NodoListaDoblementeEnlazada<T> previous;
    private T key;
    NodoListaDoblementeEnlazada(T key){
        this.key=key; 
    }

    public NodoListaDoblementeEnlazada(T key, NodoListaDoblementeEnlazada<T> previous,NodoListaDoblementeEnlazada<T> next) {
        this.next = next;
        this.previous = previous;
        this.key = key;
    }
    

    public NodoListaDoblementeEnlazada<T> getNext() {
        return next;
    }

    public NodoListaDoblementeEnlazada<T> getPrevious() {
        return previous;
    }

    public T getKey() {
        return key;
    }

    public void setNext(NodoListaDoblementeEnlazada<T> next) {
        this.next = next;
    }

    public void setPrevious(NodoListaDoblementeEnlazada<T> previous) {
        this.previous = previous;
    }

    public void setKey(T key) {
        this.key = key;
    }
    
    
    
}
