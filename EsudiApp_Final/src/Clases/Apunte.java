
package Clases;
public class Apunte extends MiniTarea{
  String nombre;
  String apunte;
  public Apunte(String nombre,String apunte,int id){
    this.nombre=nombre;
    this.apunte=apunte;
    super.id=id;
    super.tipo=1;
  }

    public String getNombre() {
        return nombre;
    }

    public String getApunte() {
        return apunte;
    }
  public void print(){
    System.out.println("----");
    System.out.println(" "+nombre+" :");
    System.out.println(apunte);
    System.out.println("----");
  }

    @Override
    public String toString() {
       return String.format("   %s:   \n%s\n",nombre,apunte);
    }
}