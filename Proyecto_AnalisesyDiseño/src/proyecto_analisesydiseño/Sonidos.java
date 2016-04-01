/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_analisesydiseño;

/**
 * importacion el cual es para importacion de imagenes
 */
import java.applet.AudioClip;

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */

public class Sonidos {

    AudioClip sonido;//Crea objero de AudioClip para el sonido
    boolean silencio = false;//Variable para saber si esta en silencio 

    /**
     * Constructor Vacio
     */
    public Sonidos() {
    }

    /**
     * Sonido de la ficha
     */
    public void sonidoFicha() {
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/movimiento.wav"));
        if (!silencio) {
            sonido.play();
        }
    }

    /**
     * Sonido de gane
     */
    public void sonidoGano() {
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/victoria.wav"));
        if (!silencio) {
            sonido.play();
        }
    }
}
