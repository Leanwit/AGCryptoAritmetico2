/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcryptoaritmetico2.interfaz;



import agcryptoaritmetico2.AGCryptoAritmetico2;
import java.text.DecimalFormat;
import javax.swing.plaf.SliderUI;

/**
 *
 * @author Leanwit
 */
public class ventanita extends javax.swing.JFrame {
   
    /**
     * Creates new form ventanita
     */
    public ventanita() {        
        initComponents();
        jSliderLamda.setValue(25);
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextOperacion = new javax.swing.JTextField();
        jButtonCalcular = new javax.swing.JButton();
        jComboBoxCantidadIndividuos = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldSeleccion = new javax.swing.JTextField();
        jTextFieldCruza = new javax.swing.JTextField();
        jTextFieldMutacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelLamda = new javax.swing.JLabel();
        jSliderLamda = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextOperacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextOperacion.setText("send+more=money");

        jButtonCalcular.setText("Calcular");
        jButtonCalcular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCalcularMouseClicked(evt);
            }
        });
        jButtonCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcularActionPerformed(evt);
            }
        });

        jComboBoxCantidadIndividuos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "100", "500", "1000", "5000" }));
        jComboBoxCantidadIndividuos.setSelectedIndex(1);
        jComboBoxCantidadIndividuos.setToolTipText("");

        jLabel1.setText("Cantidad de Individuos");

        jLabel2.setText("% Selección");

        jLabel3.setText("%Cruza");

        jLabel4.setText("%Mutación");

        jTextFieldSeleccion.setText("20");

        jTextFieldCruza.setText("70");
        jTextFieldCruza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCruzaActionPerformed(evt);
            }
        });

        jTextFieldMutacion.setText("10");
        jTextFieldMutacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMutacionActionPerformed(evt);
            }
        });

        jLabel5.setText("Lamda");

        jLabelLamda.setText("50");

        jSliderLamda.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderLamdaStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jButtonCalcular))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxCantidadIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldMutacion, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldCruza, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldSeleccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jSliderLamda, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelLamda, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jTextOperacion, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxCantidadIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCruza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSliderLamda, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLamda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCalcular)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldMutacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMutacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMutacionActionPerformed

    private void jTextFieldCruzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCruzaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCruzaActionPerformed

    private void jButtonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcularActionPerformed
        double startTime = System.currentTimeMillis()*0.001;
        AGCryptoAritmetico2.comenzarAlgoritmo(jTextOperacion.getText(), Integer.parseInt(jComboBoxCantidadIndividuos.getSelectedItem().toString()),Integer.parseInt(jTextFieldSeleccion.getText()),
            (Integer.parseInt(jTextFieldCruza.getText())),Integer.parseInt(jTextFieldMutacion.getText()), ((double) jSliderLamda.getValue())/100000);
        double stopTime = System.currentTimeMillis()*0.001;// TODO add your handling code here:
        System.out.println("Tiempo que tardó en encontrar la solucion: " + Math.rint((stopTime - startTime)*100)/100 + " segundos");
    }//GEN-LAST:event_jButtonCalcularActionPerformed

    private void jButtonCalcularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCalcularMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCalcularMouseClicked

    private void jSliderLamdaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderLamdaStateChanged
        DecimalFormat formato = new DecimalFormat("0.000000");
        jLabelLamda.setText(formato.format(((double)jSliderLamda.getValue()/100000)));
    }//GEN-LAST:event_jSliderLamdaStateChanged

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
            java.util.logging.Logger.getLogger(ventanita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanita().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalcular;
    private javax.swing.JComboBox jComboBoxCantidadIndividuos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelLamda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider jSliderLamda;
    private javax.swing.JTextField jTextFieldCruza;
    private javax.swing.JTextField jTextFieldMutacion;
    private javax.swing.JTextField jTextFieldSeleccion;
    private javax.swing.JTextField jTextOperacion;
    // End of variables declaration//GEN-END:variables
}
