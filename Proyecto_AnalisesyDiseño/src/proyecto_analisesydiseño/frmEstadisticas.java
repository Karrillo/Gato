/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_analisesydiseño;

/**
 * Importaciones las cuales permiten llenar las tablas con datos de la base de
 * datos
 */
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * Importancion para informacion de errores
 */
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */
public class frmEstadisticas extends javax.swing.JFrame {

    /*variables para ingreso de informacion de las tablas*/
    ResultSet resultados;
    JTable tabla;
    DefaultTableModel dfm;
    BaseDatos coneccion = Proyecto_AnalisesyDiseño.coneccion; //instancia a la base de datos

    /**
     * Metodo ingresa la informacion en la tabla 1 de jugador contra la
     * computadora
     */
    private void tabla1() {
        tabla = this.table_jugador;
        dfm = new DefaultTableModel();
        tabla.setModel(dfm);
        dfm.setColumnIdentifiers(new Object[]{"Nick", "Ganadas", "Perdidas", "Empates"});
        resultados = coneccion.todos_usuarios(1);
        try {
            while (resultados.next()) {
                dfm.addRow(new Object[]{resultados.getString("nick"), resultados.getInt("ganadas"), resultados.getInt("perdidas"), resultados.getInt("empatadas")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Metodo ingresa la informacion en la tabla 2 de jugador contra jugadores
     */
    private void tabla2() {
        tabla = this.table_jugadores;
        dfm = new DefaultTableModel();
        tabla.setModel(dfm);
        dfm.setColumnIdentifiers(new Object[]{"Nick", "Ganadas", "Perdidas", "Empates"});
        resultados = coneccion.todos_usuarios(2);
        try {
            while (resultados.next()) {
                dfm.addRow(new Object[]{resultados.getString("nick"), resultados.getInt("ganadas"), resultados.getInt("perdidas"), resultados.getInt("empatadas")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Costructor del formulario metodos para llenar campos de la tabla
     */
    public frmEstadisticas() {
        initComponents();
        //pone el formulario al centro del escritorio
        setLocationRelativeTo(null);
        //establece los datos de las tablas
        tabla1();
        tabla2();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_Ujugador = new javax.swing.JLabel();
        lb_Djugador = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_jugador = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_jugadores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ESTADISTICAS");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_Ujugador.setText("Un Jugador");
        getContentPane().add(lb_Ujugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lb_Djugador.setText("Dos Jugadores");
        getContentPane().add(lb_Djugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, 77, -1));

        table_jugador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_jugador);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 249, 377));

        table_jugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(table_jugadores);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 250, 377));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/trofeo_1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 110, 210));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cierra el formulario de estadisticas
     *
     * @param evt
     */
    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

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
            java.util.logging.Logger.getLogger(frmEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEstadisticas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_Djugador;
    private javax.swing.JLabel lb_Ujugador;
    private javax.swing.JTable table_jugador;
    private javax.swing.JTable table_jugadores;
    // End of variables declaration//GEN-END:variables
}
