/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_analisesydiseño;

import javax.swing.ImageIcon;

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */
public class Ficha {

    //Arreglo de ImagenIcon el cual contiene las figuras del tablero
    ImageIcon[] fichas = new ImageIcon[12];

    /**
     * Constructor e inicializacion de Ficha
     */
    public Ficha() {
        fichas[0] = new ImageIcon(getClass().getResource("/Imagenes/cruz.png"));
        fichas[1] = new ImageIcon(getClass().getResource("/Imagenes/circulo.png"));
        fichas[2] = new ImageIcon(getClass().getResource("/Imagenes/raya1.png"));
        fichas[3] = new ImageIcon(getClass().getResource("/Imagenes/raya2.png"));
        fichas[4] = new ImageIcon(getClass().getResource("/Imagenes/raya3.png"));
        fichas[5] = new ImageIcon(getClass().getResource("/Imagenes/raya4.png"));
        fichas[6] = new ImageIcon(getClass().getResource("/Imagenes/raya5.png"));
        fichas[7] = new ImageIcon(getClass().getResource("/Imagenes/raya6.png"));
        fichas[8] = new ImageIcon(getClass().getResource("/Imagenes/raya7.png"));
        fichas[9] = new ImageIcon(getClass().getResource("/Imagenes/raya8.png"));
    }

    /**
     * Metodo retorna la imagen el cual corresponda al jugador
     *
     * @param numero
     * @return
     */
    public ImageIcon fichas(int numero) {
        switch (numero) {
            case 0:
                return fichas[0];
            case 1:
                return fichas[1];
            case 2:
                return fichas[2];
            case 3:
                return fichas[3];
            case 4:
                return fichas[4];
            case 5:
                return fichas[5];
            case 6:
                return fichas[6];
            case 7:
                return fichas[7];
            case 8:
                return fichas[8];
            default:
                return fichas[9];
        }
    }
}
