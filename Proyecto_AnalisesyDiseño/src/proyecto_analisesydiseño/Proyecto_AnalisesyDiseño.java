/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_analisesydiseño;

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */
public class Proyecto_AnalisesyDiseño {
    //conexion a la base de datos unica
    public static BaseDatos coneccion = new BaseDatos();
    /**
     * Instancia el frmMenu para abrir formulario
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        coneccion.conectar();
        frmMenu menu = new frmMenu();
        menu.show();
    }

}
