/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_analisesydiseño;

/**
 * Importacion para anuncion o avisos de errores
 */
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */
public class frmMenu extends javax.swing.JFrame {

    BaseDatos coneccion = Proyecto_AnalisesyDiseño.coneccion; //instancia de base de datos

    /**
     * Constructor del formulario
     */
    public frmMenu() {
        initComponents();
        //localiza el form en el centro del escritorio
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Ujugados = new javax.swing.JButton();
        btn_Djugados = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        btn_estadistica = new javax.swing.JButton();
        btn_continuar = new javax.swing.JButton();
        lb_logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MENU");
        setResizable(false);

        btn_Ujugados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1jugador.png"))); // NOI18N
        btn_Ujugados.setText("            Un Jugador");
        btn_Ujugados.setToolTipText("");
        btn_Ujugados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UjugadosActionPerformed(evt);
            }
        });

        btn_Djugados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dosjugadores.png"))); // NOI18N
        btn_Djugados.setText("     Dos Jugadores");
        btn_Djugados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DjugadosActionPerformed(evt);
            }
        });

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        btn_salir.setText("                         Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        btn_estadistica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ranking.png"))); // NOI18N
        btn_estadistica.setText("          Estadisticas");
        btn_estadistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_estadisticaActionPerformed(evt);
            }
        });

        btn_continuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/regresar (2).png"))); // NOI18N
        btn_continuar.setText("               Continuar");
        btn_continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_continuarActionPerformed(evt);
            }
        });

        lb_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ASDASD.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_logo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Ujugados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Djugados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_continuar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_estadistica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Ujugados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btn_Djugados)
                .addGap(18, 18, 18)
                .addComponent(btn_continuar)
                .addGap(18, 18, 18)
                .addComponent(btn_estadistica)
                .addGap(18, 18, 18)
                .addComponent(btn_salir)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Instancia el formulario frmJugador y carga los componentes para un solo
     * jugador y cierra el menu
     *
     * @param evt
     */
    private void btn_UjugadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UjugadosActionPerformed
        frmJugador jugar = new frmJugador();
        jugar.unJugador();
        jugar.show();
        this.dispose();
    }//GEN-LAST:event_btn_UjugadosActionPerformed

    /**
     * Instancia el formulario frmJugador y carga los componentes para dos
     * jugadores y cierra el menu
     *
     * @param evt
     */
    private void btn_DjugadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DjugadosActionPerformed
        frmJugador jugar = new frmJugador();
        jugar.jugadores();
        jugar.show();
        this.dispose();
    }//GEN-LAST:event_btn_DjugadosActionPerformed

    /**
     * Cierra el formulario y conexion a la base de datos
     *
     * @param evt
     */
    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        coneccion.CerrarConexion();
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    /**
     * Instancia el formulario frmEstadisticas para mostrar formulario de
     * estadisticas
     *
     * @param evt
     */
    private void btn_estadisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_estadisticaActionPerformed
        frmEstadisticas ranking = new frmEstadisticas();
        ranking.show();
    }//GEN-LAST:event_btn_estadisticaActionPerformed

    /**
     * Instancia el formulario frmTablero para mostrar el juego para continuar
     *
     * @param evt
     */
    private void btn_continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_continuarActionPerformed
        //Conexion a la base de datos para verificar si existe una partida guardada
        if (coneccion.ExistePartidas() == true) {
            frmTablero tablero = new frmTablero();
            tablero.cargar();
            tablero.show();
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna partida guardada en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_continuarActionPerformed

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
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Djugados;
    private javax.swing.JButton btn_Ujugados;
    public javax.swing.JButton btn_continuar;
    private javax.swing.JButton btn_estadistica;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel lb_logo;
    // End of variables declaration//GEN-END:variables
}
