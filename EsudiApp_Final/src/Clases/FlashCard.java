package Clases;
import java.time.*;
import java.util.Scanner;
public class FlashCard extends MiniTarea{
  private static Scanner input=new Scanner(System.in);
  private String pregunta;
  private String respuesta;
  private int coeficienteFrecuencia;
  private int tiempoRespondido;//el tiempo en el que la pregunta se crea o se responde
  private int tiempoActual;//tiempo actual
  private int tiempo;//Tiempo transcurrido desde que se creó o se respondió la pregunta hasta el tiempo actual
  private int intensidadRecuerdo;//La intensidad del recuerdo es la que aumentará una unidad cada vez que se responda bien
  private int retentiva;//La retentiva es igual a Euler elevado a menos el cociente entre el tiempo y la intensidad del recuerdo
  private int prioridad = 1;//Cuando la retentiva sea menor o igual a Euler, la pregunta se vuelve prioridad frente a las otras
  private LocalDate fecha;
  private LocalTime hora;

  public FlashCard(String pregunta,String respuesta,int id){
    super.id=id;
    intensidadRecuerdo=1;//Se inicializa en 1 para no volver indeterminada la función
    coeficienteFrecuencia=0;    
    this.pregunta=pregunta;
    this.respuesta=respuesta;
    
    LocalDateTime respondido = LocalDateTime.now();//Se obtiene el tiempo en el que se crea
    tiempoRespondido = respondido.getDayOfMonth();
  }
  public FlashCard(String pregunta,String respuesta){
    this(pregunta,respuesta,-1);

  }
  //Posiblemente hay que cambiar los valores de prioridad aquí/
  public int getPriority(){
	  if(retentiva > Math.E) {//Si la retentiva disminuye hasta Euler se vuelve muy importante por responder
		  prioridad = 0;
	  	return prioridad;	  
	  }else						//De lo contrario no es prioridad responderla
		  prioridad++;
	  	return prioridad;	
  }
    public void setPriority(int p) {
	 this.prioridad=p;
  }
  
  public int getRetentiva() {
	  LocalDateTime actual = LocalDateTime.now();
	  tiempoActual = actual.getDayOfMonth();
	  tiempo = tiempoRespondido - tiempoActual;
	  retentiva = (int) Math.exp((-tiempo)/intensidadRecuerdo);
	  return retentiva;
  }
  
  public void preguntar(){
	
    System.out.println("-----------");
    System.out.println(pregunta);
    System.out.println("Ingrese la palabra 'next' para saltar la pregunta");
    String respuesta1;
    boolean x=true;
    do{
      respuesta1=input.nextLine();
      if(respuesta1.equals("next")){
        System.out.println("Pregunta saltada");
        x=false;
        coeficienteFrecuencia--;
      }else if(respuesta1.equals(respuesta)){
        System.out.println("Respuesta correcta! :D");
        x=false;
        LocalDateTime respondido = LocalDateTime.now();//Se obtiene el tiempo en el que se responde
        tiempoRespondido = respondido.getDayOfMonth();
        intensidadRecuerdo++;//La intensidad aumenta una unidad para que aumente el tiempo en el que la retentiva sea igual a Euler
        coeficienteFrecuencia++;//Tal vez borrar
      }else{
        System.out.println("Respuesta incorrecta! D:");
        System.out.println("Intentalo nuevamente");
        LocalDateTime respondido = LocalDateTime.now();//Se obtiene el tiempo en el que se responde
        tiempoRespondido = respondido.getDayOfMonth();
        coeficienteFrecuencia--;//Tal vez borrar
        intensidadRecuerdo=1;//La intensidad vuelve a 1 para recomenzar su proceso de crecimiento
      }
    }while(x);
    

    System.out.println("-----------");


  }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setCoeficienteFrecuencia(int coeficiente_frecuencia) {
        this.coeficienteFrecuencia = coeficienteFrecuencia;//Tal vez borrar
    }
    public void respuestaIncorrecta(){
    	intensidadRecuerdo = 1;
    coeficienteFrecuencia--;//Tal vez borrar
    }
    public void respuestaCorrecta(){
    	intensidadRecuerdo++;
    coeficienteFrecuencia++;//Tal vez borrar
    }
  
  public void print(){
    System.out.println("____");
    System.out.println("Pregunta:");
    System.out.println(pregunta);
    System.out.println("Respuesta:");
    
    System.out.println(respuesta);
    System.out.println("_____");
  }

    @Override
    public String toString() {
        
        return "Pregunta:" + pregunta + "\nRespuesta" + respuesta;
    }
}