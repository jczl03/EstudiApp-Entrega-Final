package Estructuras;
public class ArregloDinamico<T>{
  private T arr[];
  private int size;
  private int capacity;
  private T new_arr[];
  public ArregloDinamico(){
    this.capacity=2;
    this.size=0;
    arr=(T[])new Object[capacity];
  }
  public T Get(int i){
    if(inRange(i)){
      return arr[i];
    }
    throw new RuntimeException("ERROR: index out of range");
  }
  public void Set(int i, T val){
    if(inRange(i)){
      
      arr[i] = val;
    }else{
      throw new RuntimeException("ERROR: index out of range");
    }
    
  }
  public int capacity() {
	// TODO Auto-generated method stub
	return this.capacity;
    }
  public void insert(int idx, T val){
      if(inRange(idx)){
          if(size==capacity){
              new_arr = (T[]) new Object[capacity*2];
              for(int i=0;i<idx;i++){
                  new_arr[i] = arr[i];
                  System.out.println();
                  arr[i]=null;
              }
              new_arr[idx]=val;
              for(int i=idx;i<size;i++){
                  new_arr[i+1]=arr[i];
                  arr[i]=null;
              }
              arr = new_arr;
              capacity = capacity * 2;
              size++;
          }else{
              T aux=arr[idx];
              arr[idx]=val;
              Pila<T> pilaaux=new Pila<T>();
              pilaaux.Push(aux);
              for(int i=idx+1;i<size;i++){
                  aux=arr[i];
                  arr[i]=pilaaux.Pop();
                  pilaaux.Push(aux);
              }
              size++;
          }
      }else{
        throw new RuntimeException("ERROR: index out of range");
      }
  }
  public void Push(T val){
    if(size == capacity){
      new_arr = (T[]) new Object[capacity*2];
      for(int i = 0 ; i < size ; i++){
        new_arr[i] = arr[i];
        arr[i] = null;
      }
      arr = new_arr;
      capacity = capacity * 2;
    }
    arr[size] = val;
    size++;
      
  }
  public void Remove(int i){
    if(inRange(i)){
      
      for(int j = i ; j < size - 1 ; j++){
        arr[j] = arr[j+1];
      }
      size--;
    }else{
    throw new RuntimeException("ERROR: index out of range");
    }
  }
  public boolean inRange(int i){
      return i >= 0 && i <= capacity;
  }
  public int size(){
    return size;
  }

}