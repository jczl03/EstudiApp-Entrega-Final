package Estructuras;




public class HashMap {

    private static void rehash(int size1) {
        int contPrimo = 0;
        int auxSize = 2*size1;
        while(contPrimo>0){
            for(int i = 2 ; i < auxSize; i++){
                if(auxSize%i==0){
                 contPrimo++;
                }
             }
            if(contPrimo==0){
                size1=auxSize;
                
            }else{
                auxSize++;
                }
            }
        }
	//Constructor
	String[] array;
	static int size = 25000000;
	int cont;
        
        //static int values = 1000;
	
	public HashMap(int si) {
		size = si;
		array = new String[si];
		fill(array, "-1");
				
	}
        public void fill(Object[] a,Object v){
            for (int i = 0 ; i < a.length; i++){
            a[i] = v;
            }
        }
	
	public void HashFunction(int[] str, String[] arr) {
		for (int i = 0 ; i < str.length; i++) {
			int element = str[i];
			int arrKey = element%(size-1);
			//System.out.println("El indice es "+ arrKey + " para el elemento o valor" + element);
			while(!"-1".equals(arr[arrKey])) {
				arrKey++;
				//System.out.println("Ocurrió una colisión en el indice" + (arrKey-1) + "Cambiar al indice " + arrKey);
				arrKey%= size;
			}
			arr[arrKey]= String.valueOf(element);
		}
	}
	
	//Método de busqueda   
	public String searchKey(int element) {
		int arrKey = element%7;
		int cont=0;
		while(array[arrKey]!="-1") {
			if(array[arrKey] == String.valueOf(element)) {
				//System.out.println("el elemento " + element + "fué encontrado en el índice" + arrKey);
				return array[arrKey];
			}else{
                            //System.out.println("no encontrado");
                        }
			arrKey++;
			arrKey%= size;
			cont++;
			if(cont>size-1) {
				break;
			}
		}
		return null;
	}
	public static void main(String[] args) {
                
		HashMap hashPrueba = new HashMap(size);
                
		int[] elements = new int[size];
                
                //Prueba de complejidad 1 (ingreso)
                Long t1 = System.currentTimeMillis();
		for(int i = 0 ; i < size-1 ;i++) {
			elements[i] = i;
		}
		hashPrueba.HashFunction(elements, hashPrueba.array);
		Long t2 = System.currentTimeMillis();
                System.out.println("ingresados");
                System.out.println("tiempo de ejecución de ingreso de datos:  "+(t2-t1));
                
                //Prueba de complejidad 2 (busqueda)
                Long t3 = System.currentTimeMillis();
                
                    
                    hashPrueba.searchKey(size-1);
                    
                
                Long t4 = System.currentTimeMillis();
		System.out.println("tiempo de ejecución de busqueda de datos:  "+(t4-t3));
	}
}
