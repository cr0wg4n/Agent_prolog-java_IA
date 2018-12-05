/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.planificador;

/**
 *
 * @author IA team
 */
import static java.lang.Thread.sleep;
import java.util.*;
import java.util.stream.IntStream;
import javax.swing.*;
import org.jpl7.Query;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pantalla extends javax.swing.JFrame {

    
    int money=0;
    DefaultListModel modelo=new DefaultListModel();
    int contadorLista=0;
    Agente pl;
    public Pantalla() {
        initComponents();
        cupcake.setVisible(false);
    }
    public void inicializarAgente(){
        money = Integer.parseInt(dinero.getText());
        String nom = nombre.getText();
        pl=new Agente(nom,money);
        modelo.addElement("Agente: "+nom+"        Presupuesto: "+money+" Bs.");
        estados.setModel(modelo);
        pl.moverRobotinCasa();
        modelo=pl.comprar(modelo);
    }
    
    public class Agente {
     
     String name;
     int presupuesto;
     String compras[]=new String[]{"harina","azucar","huevo","mantequilla","leche","levadura","vainilla"};
     String lugares[]=new String[]{"casa","tienda","cocina"};
     String elementos[]=new String[]{"capucillo","bandeja","horno"};
     ArrayList<String> compra_inicial = new ArrayList<String>();
     
     ArrayList<String> tener = new ArrayList<String>();
     boolean pasar=true;
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
      public String formatoArray(ArrayList<String> lista){
        String res="[";
        for(int i=0;i<lista.size();i++){
            res=res+lista.get(i)+",";
        }
        if(lista.size()==0){
            res=res+"nada]";
        }else{
            res=res.substring(0,res.length()-1)+"]";
        }
        return res;
     }
     /////////////////////////////////////////////////////////////
     public DefaultListModel comprar(DefaultListModel entrada){
         DefaultListModel res=entrada;
         //entrada.addElement("");
        String aux = consultarText("en(casa,X).");
        res.addElement(aux);
        boolean aux2;
        for(int i=0;i<compras.length;i++){
            aux2=consultarB("tener("+compras[i]+","+formatoArray(compra_inicial)+").");
            aux = (aux2==true)?"":"no tengo "+compras[i];
            res.addElement(aux);
        }
        aux=consultarText("ir("+lugares[1]+",X).");
        res.addElement(aux);
        moverRobotinTienda();
        res.addElement(consultarText("en(tienda,X)."));
        int[]azar=desorden(7);
        
        for(int i=0;i<azar.length;i++){
            aux2=consultarB("consultar_presupuesto("+compras[azar[i]]+","+presupuesto+").");
            if(aux2==true){
                res.addElement("Comprar "+compras[azar[i]]);
                aux = consultarNumbers("precio("+compras[azar[i]]+",X).");
                int gasto=Integer.parseInt(aux);
                res.addElement("gaste --> "+aux+" Bs.");
                presupuesto=presupuesto-gasto;
                res.addElement("me queda "+presupuesto+" Bs. de cambio");
                compra_inicial.add(compras[azar[i]]);
            }else{
                res.addElement(consultarText("sin_(presupuesto,X)."));
                res.addElement("FIN");
                pasar=false;
                break;
            }
        }
        
        if(pasar){
           for(int i=0;i<compras.length;i++){
                aux2=consultarB("tener("+compras[i]+","+formatoArray(compra_inicial)+").");
                aux = (aux2==true)? "tengo "+compras[i]:"no tengo "+compras[i];
                res.addElement(aux);
           }
           
        res.addElement(consultarText("en(tienda,X)."));
        res.addElement("/ / / / / / / / / / / / / / / / / / / / / / / / / /");
        
        }
        return res;
     }
     ////////////////////////////////////////////////////////////
     public DefaultListModel hornear(DefaultListModel entrada){
         DefaultListModel res=entrada;
         //res.addElement("");
         res.addElement(consultarText("ir(casa,X)."));
         moverRobotinCasa();
         res.addElement(consultarText("en(casa,X)."));
         res.addElement(consultarText("ir(cocina,X)."));
         res.addElement(consultarText("en(cocina,X)."));
         
         int azar[]=desorden(4);
         boolean horno=false;
         boolean agregar=false;
         boolean bol1=false;
         boolean bol2=false;
         
         for(int i=0;i<4;i++){
            if(azar[i]==0){
                if(consultarB("en(cocina).")){
                   res.addElement(consultarText("calentar(horno,X)."));
                }
                horno=true;
            }else if(azar[i]==1){
                if(consultarB("en(cocina).")){
                   res.addElement(consultarText("batir(mantequilla,azucar,bol_1,X)."));
                   res.addElement(consultarText("agregar(bol_1,huevo,mantequilla,X)."));
                   bol1=true;
                   if(bol2==true){
                       res.addElement(consultarText("juntar(bol_1,bol_2,X)."));
                       res.addElement(consultarText("obtener(masa,X)."));
                       
                   }
                }
            }else if(azar[i]==2){
                if(consultarB("en(cocina).")){
                    res.addElement(consultarText("mezclar(harina,levadura,bol_2,X)."));
                    res.addElement(consultarText("agregar(bol_2,leche,X)."));
                    bol2=true;
                    if(bol1==true){
                        res.addElement(consultarText("juntar(bol_1,bol_2,X)."));
                        res.addElement(consultarText("obtener(masa,X)."));
                        
                    } 
                }
            }else if(azar[i]==3){
                if(consultarB("en(cocina).")){
                    res.addElement(consultarText("agregar(bandeja,capucillos,X)."));
                }
                 agregar=true;
            }    
         }
         if(agregar){
            res.addElement(consultarText("dividir_en(masa,bandeja,X)."));
         }
         if(consultarText("caliente(horno,X).").equals("El horno esta caliente") && 
                 consultarText("en(masa,bandeja,X).").equals("masa distribuida en la bandeja")&&horno){
             res.addElement(consultarText("hornear(masa,15,X)."));
             res.addElement(consultarText("sacar(bandeja,horno,X)."));
         }
         res.addElement(" ");
         res.addElement(consultarText("obtener(cupcakes ,X)."));
         
         
         cupcake.setVisible(true);
         return res;
     }
     
     /////////////////////////////////////////////////////////////
     
     /*TESTINGs
        int[] nuevo= desorden();
         for (int i = 0; i < 7; i++) {
            System.out.println(nuevo[i]+"-->");
         }
     */
     public int[] desorden(int tam){
        int[] numerosAleatorios = IntStream.rangeClosed(0,tam-1).toArray();
        Random r = new Random();
        for (int i = numerosAleatorios.length; i > 0; i--) {
            int posicion = r.nextInt(i);
            int tmp = numerosAleatorios[i-1];
            numerosAleatorios[i - 1] = numerosAleatorios[posicion];
            numerosAleatorios[posicion] = tmp;
        }
        return numerosAleatorios;
     }
     public void moverRobotinTienda(){
         robotin.setBounds(550,123,96,96);
     }
     public void moverRobotinCentro(){
         robotin.setBounds(330,123,96,96);
     }
     public void moverRobotinCasa(){
         robotin.setBounds(123,123,96,96);
     }
     
     
     public String boolToAction(String accion){
     String res;
     res=accion;
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
     public String consultarNumbers(String pregunta){
     String res;
     Query consulta=new Query(pregunta);
     res=consulta.oneSolution().toString();
     res=res.substring(3,res.length()-1);
     return res;
     }
}
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        robotin = new javax.swing.JLabel();
        ScrolEstados = new javax.swing.JScrollPane();
        estados = new javax.swing.JList<>();
        BtnCompras = new javax.swing.JButton();
        casa = new javax.swing.JLabel();
        tienda = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        dinero = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BtnCocina = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cupcake = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(760, 470));

        robotin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agente/planificador/Recursos/rbot96.gif"))); // NOI18N

        ScrolEstados.setViewportView(estados);

        BtnCompras.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        BtnCompras.setText("Realizar Compras");
        BtnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnComprasActionPerformed(evt);
            }
        });

        casa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agente/planificador/Recursos/casa.png"))); // NOI18N

        tienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agente/planificador/Recursos/tienda.png"))); // NOI18N

        titulo.setFont(new java.awt.Font("04b03", 0, 24)); // NOI18N
        titulo.setText("Agente Repostero <<Preparacion de CUPCAKES>>");

        jLabel1.setText("Bs");

        jLabel2.setText("Dinero:");

        BtnCocina.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        BtnCocina.setText("Preparar Cupcake");
        BtnCocina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCocinaActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre Agente: ");

        jLabel4.setText("Log de Actividades:");

        cupcake.setText("Ver Cupcakes");
        cupcake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cupcakeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(casa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(robotin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tienda, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(titulo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(207, 207, 207)
                                        .addComponent(cupcake))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(BtnCocina)
                                            .addGap(38, 38, 38)
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dinero, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel1)
                                            .addGap(28, 28, 28)
                                            .addComponent(BtnCompras))
                                        .addComponent(ScrolEstados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tienda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(casa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(robotin, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCocina)
                            .addComponent(BtnCompras)
                            .addComponent(dinero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(cupcake))
                .addGap(4, 4, 4)
                .addComponent(ScrolEstados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCocinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCocinaActionPerformed
     if(pl.pasar){
        modelo=pl.hornear(modelo);
        
     }
    }//GEN-LAST:event_BtnCocinaActionPerformed

    private void BtnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnComprasActionPerformed
        inicializarAgente();
    }//GEN-LAST:event_BtnComprasActionPerformed

    private void cupcakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cupcakeActionPerformed
       Cupcake pantalla=new Cupcake();
       pantalla.show();
    }//GEN-LAST:event_cupcakeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               Pantalla p = new Pantalla();
               p.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCocina;
    private javax.swing.JButton BtnCompras;
    private javax.swing.JScrollPane ScrolEstados;
    private javax.swing.JLabel casa;
    private javax.swing.JButton cupcake;
    private javax.swing.JTextField dinero;
    private javax.swing.JList<String> estados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel robotin;
    private javax.swing.JLabel tienda;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
