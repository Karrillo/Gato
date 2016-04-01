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
public class Jugador {

    public String nombre;//nombre de usuario
    public int tipo, ganadas, perdidas, empates;//record para partidas ganadas, perdidas y empates

    /**
     * Crea un nuevo Jugador
     *
     * @param nombre
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        tipo = 0;
        ganadas = 0;
        perdidas = 0;
        empates = 0;
    }

    /**
     * constructor vacio
     */
    public Jugador() {
    }

    /*Metodos de Get y Set de variables para Jugador*/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGanadas() {
        return ganadas;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(int perdidas) {
        this.perdidas = perdidas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empate) {
        this.empates = empate;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Agrega un gane
     */
    public void gano() {
        ganadas++;
    }

    /**
     * Agrefa una perdida
     */
    public void perdio() {
        perdidas++;
    }

    /**
     * Agrega un empate
     */
    public void empato() {
        empates++;
    }

}
