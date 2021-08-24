package Clases;
import Estructuras.*;
import java.util.Scanner;
public class Mazo extends MiniTarea{
  private static Scanner input=new Scanner(System.in);
  private String nombre;
  private ListaEnlazada<FlashCard> cartas;
  private NodeListaEnlazada<FlashCard> nodoProvisional;
  private static final Fila<FlashCard> filaFlashCards=new Fila<FlashCard>();
  public Mazo(String nombre){
    this.nombre=nombre;
    cartas=new ListaEnlazada<FlashCard>();
  }
  public void add(FlashCard carta){
    cartas.PushBack(carta);
  }

    public String getNombre() {
        return nombre;
    }
  public void preguntar(){
    nodoProvisional=cartas.getHead();
    while(nodoProvisional!=null){
      nodoProvisional.getKey().preguntar();
      nodoProvisional=nodoProvisional.getNext();
    }
  }
  public ListaEnlazada<FlashCard> getLista(){
    return cartas;
  }
  public void setLista(ListaEnlazada<FlashCard> listanew){
      cartas=listanew;
  }
  public void estudiar(){
      nodoProvisional=cartas.getHead();
      while(nodoProvisional!=null){
          filaFlashCards.enqueue(nodoProvisional.getKey());
          nodoProvisional=nodoProvisional.getNext();
      }
      if(filaFlashCards.Empty()){
        System.out.println("No hay FlashCards en el mazo");
      }else{
        FlashCard carta;
        System.out.println("_________");
        System.out.println("Sesion de estudio "+nombre);
        System.out.println("Ingrese '-1' para terminar la sesión de estudio");
        System.out.println("Si no estas seguro de tu respuesta, ingresa la palabra 'next' para saltar la pregunta y responderla al final");
        System.out.println("___________");
        String respuestaInput="";
        while(!filaFlashCards.Empty() && !respuestaInput.equals("-1")){
            carta=filaFlashCards.dequeue();
            System.out.println(carta.getPregunta());
            respuestaInput=input.nextLine();
            if(!respuestaInput.equals("-1")){
                if(respuestaInput.equals("next")){
                    filaFlashCards.enqueue(carta);
                }else {
                    if(respuestaInput.equals(carta.getRespuesta())){
                        carta.respuestaCorrecta();
                        System.out.println("Respuesta Correcta :D");
                        
                    }else{
                        carta.respuestaIncorrecta();
                        System.out.println("Respuesta Inorrecta :( , la pregunta se realizara nuevamente al final de la sesion ");
                        filaFlashCards.enqueue(carta);
                        
                    }
                }
            }
            
        }
        
        System.out.println("Sesion de Estudio finalizada");
        if(respuestaInput.equals("-1")){
            System.out.println("Has dejado preguntas sin responder correctamente :/, te recomendamos repasarlas en la sección de 'Estudiar con FlashCards' ¡Animo!");
        }else{
            System.out.println("Felicidades! Has respondido correctamente todas las preguntas del mazo :D!");
        }
        System.out.println("_________");
        filaFlashCards.clear();
      }
      
      
      
  }
  public void printPrueba(){
      nodoProvisional=cartas.getHead();
      while(nodoProvisional!=null){
          filaFlashCards.enqueue(nodoProvisional.getKey());
          nodoProvisional=nodoProvisional.getNext();
      }
      while(!filaFlashCards.Empty()){
          filaFlashCards.dequeue().print();
      }
  }
  public void print(){
    nodoProvisional=cartas.getHead();
    while(nodoProvisional!=null){
      nodoProvisional.getKey().print();
      nodoProvisional=nodoProvisional.getNext();
    }


  }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}