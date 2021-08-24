package Estructuras;
public class ListaEnlazada<T>{
    private NodeListaEnlazada<T> head;
    private NodeListaEnlazada<T> tail;
    public ListaEnlazada(){
        
    }
    public void PushFront(T key){
        NodeListaEnlazada<T> node=new NodeListaEnlazada<T>(key);
        node.setNext(head);
        head=node;
        if (tail==null){
            tail=node;
        }
    }
    public void PopFront(){
        if(head==null){
            throw new RuntimeException("Lista Vacía");
        }
        this.head=head.getNext();
        if(head==null){
            this.tail=null;
        }
    }
    public T TopFront(){
        return head.getKey();
    }
    public T TopBack(){
        return tail.getKey();
    }
    public void PushBack(T key){
        NodeListaEnlazada<T> node=new NodeListaEnlazada<T>(key);
        if (tail==null){
            head=node;
            tail=node;
        }else{
            tail.setNext(node);
            tail=node;
        }
    }
    public void PopBack(){
        if(head==null){
            throw new RuntimeException("Lista Vacía");
        }
        if(head==tail){
            head=null;
            tail=null;
        }else{
            NodeListaEnlazada<T> node=head;
            while(node.getNext().getNext()!=null){
                node=node.getNext();
            }
            node.setNext(null);
            tail=node;
        }
    }
    public void addAfter(NodeListaEnlazada<T> nodo,T key) {
      NodeListaEnlazada<T> node2=new NodeListaEnlazada<T>(key,nodo.getNext());
    	nodo.setNext(node2);
    	if(tail==nodo) {
    		tail=node2;
    	}
    }
    public void Erase(T key){
      NodeListaEnlazada<T> node=head;
      NodeListaEnlazada<T> last=null;
      if(head.getKey()==key){
        PopFront();
      }else if(tail.getKey()==key){
        PopBack();
      }else{
        while(node.getNext()!=null ){
          if(node.getNext().getKey()==key){
            node.setNext(node.getNext().getNext());
          }
          node=node.getNext();
        }
      }
      
    }
    public void addBefore(NodeListaEnlazada<T> nodo,T key) {
    	
    	NodeListaEnlazada<T> nodo3=head;
      NodeListaEnlazada<T> node2=new NodeListaEnlazada<T>(key);
      node2.setNext(nodo);
      //boolean a=true;
      int a=0;
    	while(nodo3.getNext()!=null && a<9) {
        System.out.println("Nodo3 Key");
        System.out.println(nodo3.getKey());
    		if(nodo3.getNext().getKey()==nodo.getKey()) {
    			nodo3.setNext(node2);
          //a=false;
          
    		}
        a++;
    		nodo3=nodo3.getNext();
    	}

    }
    public boolean Empty() {
    	return head==null;
    }
    public void Print() {
    	NodeListaEnlazada<T> nodo=head;
    	do {
    		System.out.print(nodo.getKey()+" ");
    		nodo=nodo.getNext();
    	}while(nodo!=null);
    	System.out.println();
    }
    public NodeListaEnlazada<T> getHead(){
      return head;
    }
    public NodeListaEnlazada<T> Find(T key){
      NodeListaEnlazada<T> node=head;
      while(node!=null){
        if(node.getKey()==key){
          return node;
        }
        node=node.getNext();
      }
      return null;
    }
    public void clear(){
        this.head=null;
        this.tail=null;
    }
    
    
}

