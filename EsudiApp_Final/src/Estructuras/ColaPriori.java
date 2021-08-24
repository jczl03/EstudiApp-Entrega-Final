package Estructuras;
import Estructuras.*;
public class ColaPriori<T> {

	ArregloDinamico priori;
	ArregloDinamico guardar ;
	int size;
	int maxSize;
	public ColaPriori(){
	this.priori= new ArregloDinamico();
	//Sirve para cualquier objeto pero tiene dos arreglos
	//Otra opcion seria hacerlo con solo un arreglo espesificando el tipo de objeto
	this.guardar=new ArregloDinamico();
	size=-1;
	//Agregar metodo para capacity por ser privado
	maxSize=this.priori.capacity();
}
	int Parent(int i) {
		return (int) (Math.floor((i-1)/2));
	}
	int LeftChild(int i) {
		return (2*i+1);
	}
	int RightChild(int i) {
		return (2*i+2);
	}
	
	void SiftUp(int i) {
		if (this.priori.Get(Parent(i))==null) {
			this.priori.Set(0,this.priori.Get(i));
			this.priori.Set(1,null);
		}
		else {
			
		while (i>0 && (int)(this.priori.Get(Parent(i)))<(int)(this.priori.Get(i))) {
			int c = (int) (this.priori.Get(Parent(i)));
			this.priori.Set(Parent(i),this.priori.Get(i));
			this.priori.Set(i, c);
			T g = (T)(this.guardar.Get(Parent(i)));
			this.guardar.Set(Parent(i),this.guardar.Get(i));
			this.guardar.Set(i, g);
			i= Parent(i);			
		}
		}
	}
	public boolean Empty(){
		return this.size==0;
	}
	void SiftDown(int i) {
		int maxIndex = i;
		int l=LeftChild(i);
		if (l <= this.size &&(int) this.priori.Get(l)> (int)this.priori.Get(maxIndex)) {
			maxIndex = l;
		}
		int r=RightChild(i);
		if (r<= this.size && (int)this.priori.Get(r)>(int)this.priori.Get(maxIndex)) {
			maxIndex = r;
		}
		if(i!= maxIndex) {
			int c = (int) (this.priori.Get(i));
			this.priori.Set((i),this.priori.Get(maxIndex));
			this.priori.Set(maxIndex, c);
			T g = (T)(this.guardar.Get(i));
			this.guardar.Set((i),this.guardar.Get(maxIndex));
			this.guardar.Set(maxIndex, g);
			SiftDown(maxIndex);
		}
	}
	
	private int size() {
		// TODO Auto-generated method stub
		this.size=this.priori.size();
		return this.size;
	}
	public void Insert(int p, T key) {
		this.size=this.size+1;
		this.priori.insert(this.size,p);
		this.guardar.insert(this.size,key);
		SiftUp(this.size);
	}
	
	
	public T ExtractMax() {
		T result = (T) this.guardar.Get(0);
		this.priori.Set(0,this.priori.Get(this.size));
		this.guardar.Set(0, this.guardar.Get(this.size));
		this.size--;
		SiftDown(0);
		return result;
		
	}
        public T getMax(){
            return (T) this.guardar.Get(0);
        }
	
	public T Remove(int i) {
		//Remover de acuerdo a su prioridad obejeto o posicion.???
		this.priori.Set(i, (int) Double.POSITIVE_INFINITY);
		SiftUp(i);
		return ExtractMax();
	}
	
	public void ChangePriority(int i,int p, T key) {
		int oldp =(int) this.priori.Get(i);
		this.priori.Set(i,p);
		T oldkey =(T) this.guardar.Get(i);
		this.guardar.Set(i, key);
		if(p>oldp) {
			SiftUp(i);
		}
		else {
			SiftDown(i);
		}
		
	}
}