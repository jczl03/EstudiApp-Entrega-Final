
package Estructuras;



public class Lista<T extends Comparable<? super T>> {
    private NodoListaDoblementeEnlazada<T> head;
    private NodoListaDoblementeEnlazada<T> tail;
    public Lista(){
        this.head=null;
        this.tail=null;
    }
    public void sortPush(T key){
        if(empty()){
            pushFront(key);
        }else{
            NodoListaDoblementeEnlazada<T> current=head;

            while(current!=null && current.getKey().compareTo(key)<0  ){
                current=current.getNext();
            }
            if(current==null){
                pushBack(key);
            }else{
                addBefore(current,key);
            }
        }
        
    }
    public boolean empty(){
        return head==null;
    }
    public void pushFront(T key){
        NodoListaDoblementeEnlazada<T> node=new NodoListaDoblementeEnlazada<T>(key);
        node.setNext(head);
        if(head!=null){
            head.setPrevious(node);
        }
        
        head=node;
        if (tail==null){
            tail=node;
        }
    }
    public T popFront(){
        if(head==null){
            throw new RuntimeException("Lista Vacía");
        }
        T r= this.head.getKey();
        this.head=head.getNext();
        if(head==null){
            this.tail=null;
        }else{
            head.setPrevious(null);
        }
        return r;
    }
    public T topFront(){
        return head.getKey();
    }
    public void pushBack(T key){
        NodoListaDoblementeEnlazada<T> node=new NodoListaDoblementeEnlazada<T>(key);
        if (tail==null){
            head=node;
            tail=node;
        }else{
            tail.setNext(node);
            node.setPrevious(tail);
            tail=node;
        }
    }
    public T popBack(){
        if(head==null){
            throw new RuntimeException("Lista Vacía");
        }
        T r=this.tail.getKey();
        if(head==tail){
            head=null;
            tail=null;
        }else{
            tail=tail.getPrevious();
            tail.setNext(null);
        }
        return r;
    }
    public void addAfter(NodoListaDoblementeEnlazada<T> nodo,T key) {
        if(tail==nodo) {
            pushBack(key);
    	}else{
            NodoListaDoblementeEnlazada<T> node2=new NodoListaDoblementeEnlazada<T>(key,nodo,nodo.getNext());
            nodo.setNext(node2);
            node2.getNext().setPrevious(node2);
        }
    	
    }
    public void delete(NodoListaDoblementeEnlazada<T> nodo){
        if(head==nodo){
            popFront();
        }else if(tail==nodo){
            popBack();
        }
        else{
            nodo.getNext().setPrevious(nodo.getPrevious());
            nodo.getPrevious().setNext(nodo.getNext());
        }
        
    }
    public void addBefore(NodoListaDoblementeEnlazada<T> nodo,T key){
        if(head==nodo){
            pushFront(key);
        }else{
            NodoListaDoblementeEnlazada<T> node2=new NodoListaDoblementeEnlazada<T>(key,nodo.getPrevious(),nodo);
            nodo.setPrevious(node2);
            node2.getPrevious().setNext(node2);
        }
        
        
    }
    public NodoListaDoblementeEnlazada<T> getHead(){
      return head;
    }
    public NodoListaDoblementeEnlazada<T> getTail(){
      return tail;
    }
    public void clear(){
        this.head=null;
        this.tail=null;
    }
    public T getKey(int idx){
        NodoListaDoblementeEnlazada<T> nodoaux=this.getHead();
        for(int i=0;i<idx && nodoaux!=null;i++){
            nodoaux=nodoaux.getNext();
        }
        if(nodoaux==null){
            throw new RuntimeException("Lista Vacía");
        }
        return nodoaux.getKey();
    }
    
}
