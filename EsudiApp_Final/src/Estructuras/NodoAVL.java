/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;


public class NodoAVL<T extends Comparable<T>> implements Comparable<NodoAVL<T>> {
    private T key;
    private NodoAVL<T> leftChild;
    private NodoAVL<T> rightChild;
    private NodoAVL<T> parent;
    private int height;
    public int level;

    public NodoAVL<T> getParent() {
        return parent;
    }

    public NodoAVL(T key) {
        this.key = key;
        this.height=1;
    }

    public void setParent(NodoAVL<T> parent) {
        this.parent = parent;
    }

    public NodoAVL(T key, NodoAVL<T> leftChild, NodoAVL<T> rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        if(leftChild == null && rightChild == null)
          setHeight(1);
        else if(leftChild == null)
            setHeight(rightChild.getHeight()+1);
        else if(rightChild == null)  
            setHeight(leftChild.getHeight()+1);
        else 
            setHeight(Math.max(leftChild.getHeight(), rightChild.getHeight())+1);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setKey(T key) {
        this.key = key;
    }

    public void setLeft(NodoAVL<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRight(NodoAVL<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getKey() {
        return key;
    }

    public NodoAVL<T> getLeft() {
        return leftChild;
    }

    public NodoAVL<T> getRight() {
        return rightChild;
    }

    @Override
    public int compareTo(NodoAVL<T> n){
      return this.key.compareTo(n.key);
    }

    @Override
  	public String toString() {
	  	return "Level " + level + ": " + key;
	}
}