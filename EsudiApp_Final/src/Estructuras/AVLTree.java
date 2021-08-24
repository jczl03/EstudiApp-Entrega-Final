

package Estructuras;
import java.math.*;


public class AVLTree<T extends Comparable<T>> {
    private NodoAVL<T> root; 

    public AVLTree() {
        
    }

    public NodoAVL<T> getRoot() {
        return root;
    }

    public void setRoot(NodoAVL<T> root) {
        this.root = root;
    }

    public AVLTree(T key) {
        root=new NodoAVL<T>(key);
    }
    public NodoAVL<T> Find(NodoAVL<T> R,T k){
            if(R!=null){
                if(R.getKey().compareTo(k)==0){
                    return R;
                }else if(R.getKey().compareTo(k)>0){
                    return Find(R.getLeft(),k);
                }else if(R.getKey().compareTo(k)<0){
                    return Find(R.getRight(),k);
                }
            }
            return null;
    }
    public T Maximum() {
		NodoAVL<T> local = root;
		if (local == null)
			return null;
		while (local.getRight() != null)
			local = local.getRight();
		return local.getKey();
	}

	public T Minimum() {
		NodoAVL<T> local = root;
		if (local == null)
			return null;
		while (local.getLeft() != null) {
			local = local.getLeft();
		}
		return local.getKey();
	}
  /*private NodoAVL<T> rotateRight(NodoAVL<T> nodo){
    NodoAVL<T> n = nodo;
    NodoAVL<T> r = n.getRight();
    NodoAVL<T> l = n.getLeft();
    NodoAVL<T> right = l.getRight();
    NodoAVL<T> left = l.getLeft();
    n = new NodoAVL<T>(n.getKey(), right, r);
    l = new NodoAVL<T>(l.getKey(), left, n);
    return l; 
  }  */
  public NodoAVL<T> insert(NodoAVL<T> nodo, T key){
    if(nodo == null){
        return new NodoAVL<T>(key);
    }
        
    if(nodo.getKey().compareTo(key) > 0){
      nodo.setLeft(insert(nodo.getLeft(),key));
    }else if(nodo.getKey().compareTo(key) < 0){
      nodo.setRight(insert(nodo.getRight(),key));
    }
   return Rebalance(nodo);
  }
  public NodoAVL<T> insert(T key){
    root = insert(root, key);
    return root;
  }

  public boolean search(T key){
    NodoAVL<T> local = root;
    while(local != null){
      if(local.getKey().compareTo(key) == 0)
        return true;
      else if(local.getKey().compareTo(key) > 0)
        local = local.getLeft();
      else
        local = local.getRight();
    }
    return false;
  }

  public String toString(){
    return root.toString();
  }
  public boolean empty(){
      return root==null;
  }
 
  public void preOrderPrint(NodoAVL<T> nodo){
      if(nodo!=null){
         // System.out.println(nodo.getKey()+" Heigth: "+nodo.getHeight());
         System.out.println(nodo.getKey());
          preOrderPrint(nodo.getLeft());
          preOrderPrint(nodo.getRight());
        }
  }
  public void preOrderPrint(){
      preOrderPrint(this.root);
  }
  public void inOrderPrint(NodoAVL<T> nodo){
      if(nodo!=null){
          inOrderPrint(nodo.getLeft());
       //   System.out.println(nodo.getKey()+" Heigth: "+nodo.getHeight());
       System.out.println(nodo.getKey());
          inOrderPrint(nodo.getRight());
        }
  }
  public void postOrderPrint(NodoAVL<T> nodo){
      if(nodo!=null){
          postOrderPrint(nodo.getLeft());
          postOrderPrint(nodo.getRight());
       //   System.out.println(nodo.getKey()+" Heigth: "+nodo.getHeight());
       System.out.println(nodo.getKey());
        }
  }
  //MineSS
  public NodoAVL<T> rotateRight(NodoAVL<T> y){
      NodoAVL<T> x=y.getLeft();
      y.setLeft(x.getRight());
      if(y.getLeft()!=null){
          y.getLeft().setParent(y);
      }
      
      x.setRight(y);
      y.setParent(x);
      updateHeight(y);
      updateHeight(x);
      return x;
  }
  public NodoAVL<T> rotateLeft(NodoAVL<T> y){
      NodoAVL<T> x=y.getRight();
      y.setRight(x.getLeft());
      if(y.getRight()!=null){
        y.getRight().setParent(y);
      }
      
      x.setLeft(y);
      y.setParent(x);
      updateHeight(y);
      updateHeight(x);
      return x;
  }
  public void updateHeight(NodoAVL<T> nodo){
      if(nodo.getLeft()!=null){
          if(nodo.getRight()!=null){
              nodo.setHeight(1+Math.max(nodo.getLeft().getHeight(), nodo.getRight().getHeight()));
        }else{
              nodo.setHeight(1+nodo.getLeft().getHeight());
          }
      }else{
          if(nodo.getRight()!=null){
            nodo.setHeight(1+nodo.getRight().getHeight());
          }else{
              nodo.setHeight(1);
          }
      }
      
      
  }
  
  public int getBalance(NodoAVL<T> node){
      return Height(node.getRight())-Height(node.getLeft());
      
  }
  
  public NodoAVL<T> Rebalance(NodoAVL<T> z){
      updateHeight(z);
      int balance=getBalance(z);
      if(balance>1){
          if(Height(z.getRight().getRight())>Height(z.getRight().getLeft())){
              z=rotateLeft(z);
          }else{
              z.setRight(rotateRight(z.getRight()));
              z.getRight().setParent(z);
              z=rotateLeft(z);
          }
      }else if(balance<-1){
          if(Height(z.getLeft().getLeft())>Height(z.getLeft().getRight())){
              z=rotateRight(z);
          }else{
              z.setLeft(rotateLeft(z.getLeft()));
              z.getLeft().setParent(z);
              z=rotateRight(z);
          }
      }
        
      return z;
  }
  public NodoAVL<T> leftDescendent(NodoAVL<T> nodo){
      if(nodo.getLeft()==null){
          return nodo;
      }
      return leftDescendent(nodo.getLeft());
      
  }
  public NodoAVL<T> rightAncestor(NodoAVL<T> nodo){
      if(nodo.getParent()==null){
          return nodo;
      }else if(nodo.getKey().compareTo(nodo.getParent().getKey())<0){
          return nodo.getParent();
      }
      return rightAncestor(nodo.getParent());
      
  }
  public NodoAVL<T> next(NodoAVL<T> nodo){
      System.out.println("Buscando siguiente de"+nodo.getKey());
      if(nodo.getRight()!=null){
          return leftDescendent(nodo);
      }else{
          return rightAncestor(nodo);
      }
  }
  
  private int Height(NodoAVL<T> nodo){
      if(nodo==null){
          return 0;
      }
      return nodo.getHeight();
  }
  
  public Fila<NodoAVL<T>> rangeSearch(T in,T fin,NodoAVL<T> R){
      Fila<NodoAVL<T>> r=new Fila<NodoAVL<T>>();
      NodoAVL<T> nodo=Find(R,in);
      if(nodo.getKey().compareTo(in)>=0){
          while(nodo.getKey().compareTo(fin)<=0){
              
          r.enqueue(nodo);
          if(nodo==next(nodo)){
              return r;
          }
          nodo=next(nodo);
           }
      }
      return r;
  }
 
  public NodoAVL<T> Remove(NodoAVL<T> nodo, T key){
	if(nodo == null) {
		return null;
	}
	int c =key.compareTo(nodo.getKey()) ;
	if(c <0) {
		nodo.setLeft(Remove(nodo.getLeft(), key));
                if(nodo.getLeft()!=null){
                    nodo.getLeft().setParent(nodo);
                }
	} else if(c >0) {
		nodo.setRight(Remove(nodo.getRight(), key));
                if(nodo.getRight()!=null){
                    nodo.getRight().setParent(nodo);
                }
                
	} else if(nodo.getLeft() !=null && nodo.getRight() != null) {
		T s = leftDescendent(nodo.getRight()).getKey();
		nodo.setKey(s);
		nodo.setRight(Remove(nodo.getRight(), s));
                if(nodo.getRight()!=null){
                    nodo.getRight().setParent(nodo);
                }
	} else {
            if(nodo.getLeft() != null){
            nodo =   nodo.getLeft();
            }else{
                nodo=nodo.getRight();
            }
	}
	if(nodo != null) {
		nodo = Rebalance(nodo);
	}
	return nodo;
	  
  }
  public void remove(T key){
	  this.root = this.Remove(this.root, key);
  }

  
  
}
