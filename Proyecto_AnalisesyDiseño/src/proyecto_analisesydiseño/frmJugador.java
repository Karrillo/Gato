package proyecto_analisesydiseño;

/**
 * Importacion para importar imagenes
 */
import javax.swing.ImageIcon;
/**
 * Importacion para avisos de errores
 */
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */
public class frmJugador extends javax.swing.JFrame {

    BaseDatos coneccion = Proyecto_AnalisesyDiseño.coneccion; //Instancia para conectar a base de datos
    public String jugador1, jugador2; //Variable el cual guarda los nombres de los jugadores
    public int jugadores; //Varible que establece cuantos jugadores van a jugar

    /**
     * Costructor del formulario
     */
    public frmJugador() {
        initComponents();
        //localiza el form en el centro del escritorio
        setLocationRelativeTo(null);
    }

    /**
     * Validacion de los textbox de nick o nombres de usuarios Si no todos los
     * datos estan ingresados al sistema segun el requisito envia un true de
     * comprobacion
     *
     * @return
     */
    private boolean comprobar() {
        //Comprobamos que los campos estén llenos para un jugador
        if (this.jugadores == 1) {
            if (!this.txt_Ujugador.getText().equals("")) {
                jugador1 = this.txt_Ujugador.getText();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Llene el nombre del jugador uno por favor", "Error:", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Comprobamos que los campos estén llenos para dos jugadores
        if (this.jugadores == 2) {
            if (!this.txt_Ujugador.getText().equals("") && !this.txt_Djugador.getText().equals("")) {
                if (!this.txt_Ujugador.getText().equals(this.txt_Djugador.getText())) {
                    this.jugador1 = this.txt_Ujugador.getText();
                    this.jugador2 = this.txt_Djugador.getText();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Los jugadores tiene el mismo nombre", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Llene todos los nombres del los jugador por favor", "Error:", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
    }

    /**
     * Validar y limpiar componentes para un solo jugador
     */
    public void unJugador() {
        this.txt_Djugador.setEnabled(false);
        this.txt_Ujugador.setText(null);
        this.txt_Djugador.setText(null);
        this.lb_Djugador.setEnabled(false);
        this.lb_figura2.setVisible(false);
        this.lb_figura1.setVisible(true);
        this.jugadores = 1;
    }

    /**
     * Validar y limpiar componentes para dos jugadores
     */
    public void jugadores() {
        this.txt_Djugador.setEnabled(true);
        this.txt_Ujugador.setText(null);
        this.txt_Djugador.setText(null);
        this.lb_Djugador.setEnabled(true);
        this.lb_figura2.setVisible(true);
        this.lb_figura1.setVisible(false);
        this.jugadores = 2;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        btn_acepta = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        lb_nombre = new javax.swing.JLabel();
        lb_Ujugador = new javax.swing.JLabel();
        lb_Djugador = new javax.swing.JLabel();
        lb_estilo = new javax.swing.JLabel();
        txt_Ujugador = new javax.swing.JTextField();
        txt_Djugador = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lb_figura1 = new javax.swing.JLabel();
        lb_figura2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("JUGADOR(ES)");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 213, 355, 10));

        btn_acepta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aceptar1.png"))); // NOI18N
        btn_acepta.setText("Aceptar");
        btn_acepta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_acepta, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 305, 110, -1));

        btn_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar1.png"))); // NOI18N
        btn_cancel.setText("Cancelar");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 305, -1, -1));

        lb_nombre.setText("Nombre(s) de Jugadore(s):");
        getContentPane().add(lb_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 229, -1, -1));

        lb_Ujugador.setText("Jugador 1:");
        getContentPane().add(lb_Ujugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 263, -1, -1));

        lb_Djugador.setText("Jugador 2:");
        getContentPane().add(lb_Djugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 263, -1, -1));

        lb_estilo.setText("Estilo de Juego:");
        getContentPane().add(lb_estilo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));
        getContentPane().add(txt_Ujugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 257, 103, -1));
        getContentPane().add(txt_Djugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 257, 106, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 297, 355, -1));

        lb_figura1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VSCP.png"))); // NOI18N
        getContentPane().add(lb_figura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 174, -1));

        lb_figura2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UVU.png"))); // NOI18N
        getContentPane().add(lb_figura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 175, 179));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento del boton de aceptar el cual inicializa el juego y abre el
     * frmTablero segun sus datos ingresados para inicializar sobre 1 o 2
     * jugadores
     *
     * @param evt
     */
    private void btn_aceptaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptaActionPerformed
        //si comprobacion es true abre form de tablero
        if (comprobar()) {
            //random
            int uno = (int) (Math.random() * 7);
            int dos = (int) (Math.random() * 7);
            //instancia al juego frmTablero
            frmTablero tablero = new frmTablero();
            //Dos Jugadores
            if (jugadores == 2) {
                //verifica quien saco el random o numero mayor para establecerlo como primero X y segundo O
                if (uno > dos) {
                    tablero.iniciarJuego(jugador1, jugador2, jugadores);
                    JOptionPane.showMessageDialog(null, "A jugar !!!", "Juego", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Imagenes/primero.png")));
                } else {
                    tablero.iniciarJuego(jugador2, jugador1, jugadores);
                    JOptionPane.showMessageDialog(null, "A jugar !!!", "Juego", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Imagenes/segundo.png")));
                }
                //Un Jugador
            } else {
                tablero.iniciarJuego(jugador1, jugador2, jugadores);
                JOptionPane.showMessageDialog(null, "A jugar !!!", "Informativo", JOptionPane.INFORMATION_MESSAGE);
            }
            coneccion.EliminarPartida();//Elimina Partidas Guardadas
            coneccion.EliminarJugador("Computadora");//Elimina la Computadora de la base de datos   
            tablero.show();
            this.dispose();
        }
    }//GEN-LAST:event_btn_aceptaActionPerformed

    /**
     * Cierra el formulario de jugadores y vuelve abrir el formulario de menu
     *
     * @param evt
     */
    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        frmMenu menu = new frmMenu();
        menu.show();
        this.dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

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
            java.util.logging.Logger.getLogger(frmJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmJugador().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_acepta;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lb_Djugador;
    private javax.swing.JLabel lb_Ujugador;
    private javax.swing.JLabel lb_estilo;
    private javax.swing.JLabel lb_figura1;
    private javax.swing.JLabel lb_figura2;
    private javax.swing.JLabel lb_nombre;
    private javax.swing.JTextField txt_Djugador;
    private javax.swing.JTextField txt_Ujugador;
    // End of variables declaration//GEN-END:variables
}
