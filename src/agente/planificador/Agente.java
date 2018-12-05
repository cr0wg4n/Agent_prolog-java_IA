/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.planificador;
import org.jpl7.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author IA team
 */
public class Agente {
     
     String name;
     int presupuesto;
     String compras[]=new String[]{"harina","azucar","huevo","mantequilla","leche","levadura","vainilla"};
     String lugares[]=new String[]{"casa","tienda","cocina"};
     String elementos[]=new String[]{"capucillo","bandeja","horno"};
    
     ArrayList<String> tener = new ArrayList<String>();
     
     public Agente(String nombre,int dinero){
         name=nombre;
         presupuesto=dinero;
         System.out.println("Agente: "+name+"\nPresupuesto: "+presupuesto+" Bs.");
         connect();
         
     }
     public String formatoLista(String[] lista){
        String res="[";
        for(int i=0;i<lista.length;i++){
            res=res+lista[i]+",";
        }
        res=res.substring(0,res.length()-1)+"]";
        return res;
     }
     public DefaultListModel comprar(DefaultListModel entrada){
         DefaultListModel res=entrada;
         //entrada.addElement("");
         
         
         return res;
     }
     public DefaultListModel hornear(DefaultListModel entrada){
         DefaultListModel res=entrada;
         //entrada.addElement("");
         
         
         return res;
     }
     
     public void connect(){
      String t1 = "consult('agente.pl')";
      System.out.println((consultarB(t1) ? "Base de conocimientos: agente.pl" : "Sin conexión"));
      
     }
     public boolean consultarB(String pregunta){
     boolean res;
     Query consulta=new Query(pregunta);
     res=consulta.hasSolution();
     return res;
     }
     public String consultarText(String pregunta){
     String res;
     Query consulta=new Query(pregunta);
     res=consulta.oneSolution().toString();
     res=res.substring(4,res.length()-2);
     return res;
     }
}
