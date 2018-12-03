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
import javax.swing.*;

public class Pantalla extends javax.swing.JFrame {

    /**
     * Creates new form Pantalla
     */
    int money=0;
    DefaultListModel modelo=new DefaultListModel();
    int contadorLista=0;
    public Pantalla() {
        initComponents();
    }
    public void inicializarAgente(){
        money = Integer.parseInt(dinero.getText());
        String nom = nombre.getText();
        Agente pl=new Agente(nom,money);
        modelo.addElement("Agente: "+nom+"        Presupuesto: "+money+" Bs.");
        estados.setModel(modelo);
        //  modelo=pl.comprar(modelo);
        
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(casa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
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
                                    .addComponent(jLabel4)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCocina)
                    .addComponent(BtnCompras)
                    .addComponent(dinero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(ScrolEstados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCocinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCocinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCocinaActionPerformed

    private void BtnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnComprasActionPerformed
        inicializarAgente();
    }//GEN-LAST:event_BtnComprasActionPerformed

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
