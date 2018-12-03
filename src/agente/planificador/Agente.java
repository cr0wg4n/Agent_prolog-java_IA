/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.planificador;
import org.jpl7.*;
/**
 *
 * @author IA team
 */
public class Agente {
     
     String name;
     int presupuesto;
     public Agente(String nombre,int dinero){
         name=nombre;
         presupuesto=dinero;
         System.out.println("Agente: "+name+"\nPresupuesto: "+presupuesto+" Bs.");
         connect();
     }
     public void connect(){
      String t1 = "consult('agente.pl')";
      Query q1 = new Query(t1);
      System.out.println((consultar(t1) ? "Base de conocimientos: agente.pl" : "Sin conexión"));
     }
     public boolean consultar(String pregunta){
     boolean res;
     Query consulta=new Query(pregunta);
     res=consulta.hasSolution();
     return res;
     }
}
