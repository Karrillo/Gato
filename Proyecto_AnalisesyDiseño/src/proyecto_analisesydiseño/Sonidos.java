/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_analisesydiseño;

import java.applet.AudioClip;

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */
public class Sonidos {

    //Crea objero de AudioClip para el sonido
    AudioClip sonido;
    //Variable para saber si esta en silencio 
    boolean silencio = false;

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
