package proyecto_analisesydiseño;

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */
public class ComputadoraIA {

    public static int tiradas = 0;//variable de tiradas en el juego
    NodoG arbol = new NodoG();//Raiz del arbol
    int[] tablero;//Atributos
    public final int miFICHA = 2;//Mi ficha

    /**
     * Arboles para los movimientos
     */
    class NodoG {

        int mejorMovimiento;//Mejor movimiento        
        NodoG nodos[];//Nodos hijos       
        public int tablero[];//Tablero del juego       
        boolean miTurno = false;//Turno de la computadora       
        int indice;//Indice de la pocision
        int ganador = 0;//Ganador

        /*Constructor*/
        NodoG() {
            tablero = new int[9];
        }
    }

    /**
     * Metodo Verifica si esta ocupado o esta disponible Retorna un int el cual
     * es el espacio disponible
     *
     * @param tablero
     * @return
     */
    public int movDisponibles(int[] tablero) {
        int cont = 0;
        for (int i = 0; i < 9; i++) {
            if (tablero[i] == 0) {
                cont++;
            }
        }
        return cont;
    }

    /**
     * Metodo que nos devuelve los indices del tablero de las pocisiones vacias
     *
     * @param tablero
     * @return
     */
    public int[] posVacias(int[] tablero) {
        //Creamos el vector con los indices disponibles 
        int[] indices = new int[movDisponibles(tablero)];
        int indice = 0;
        //Recorremos y guardamos los indices
        for (int i = 0; i < 9; i++) {
            if (tablero[i] == 0) {
                indices[indice] = i;
                indice++;
            }
        }
        return indices;
    }

    /**
     * Metodo verifica espacios disponibles y retorna indice del tablero
     *
     * @param tablero
     * @return
     */
    public int movimiento(int[] tablero) {
        /*Asignamos el tablero.*/
        this.tablero = tablero;
        tiradas++;
        //Copiamos el tablero a nuestro nodo raiz
        System.arraycopy(this.tablero, 0, this.arbol.tablero, 0, 9);
        //Calculamos el mejor movimiento del �rbol, desde las hojas hasta la raiz
        movComputadora(arbol);
        //Devolvemos el mejor movimiento
        return arbol.mejorMovimiento;
    }

    /**
     * Metodo recursivo, que genera los nodos con los movimientos
     *
     * @param raiz
     */
    public void movComputadora(NodoG raiz) {
        //Numero de movimientos disponibles y sus indices en el tablero
        int movimientos = movDisponibles(raiz.tablero);
        int indices[] = posVacias(raiz.tablero);
        //Inicializamos el nodo
        raiz.nodos = new NodoG[movimientos];
        //Verificamos si hay un ganador
        int ganador = terminado(raiz.tablero);
        if (ganador == 1) {
            ganador = -1;
        } else if (ganador == 2) {
            ganador = 1;
        }

        if (ganador != 0 || movimientos == 0) {
            raiz.ganador = ganador;
        } else {

            //Creamos los datos de cada hijo
            for (int i = 0; i < movimientos; i++) {
                //Inicializamos los nodos hijos del arbol
                raiz.nodos[i] = new NodoG();
                //Les pasamos su tablero
                System.arraycopy(raiz.tablero, 0, raiz.nodos[i].tablero, 0, 9);
                //Creamos los diferentes movimientos posibles
                if (raiz.miTurno) {
                    raiz.nodos[i].tablero[indices[i]] = 1;
                } else {
                    raiz.nodos[i].tablero[indices[i]] = 2;
                }
                //Cambiamos el turno de los hijos
                raiz.nodos[i].miTurno = !raiz.miTurno;
                //Guardamos el indice de su movimiento
                raiz.nodos[i].indice = indices[i];
                movComputadora(raiz.nodos[i]);
            }
            //Minimax
            if (!raiz.miTurno) {
                raiz.ganador = Max(raiz);
            } else {
                raiz.ganador = Min(raiz);
            }
        }
    }

    /**
     * Metodo que calcula el MAXIMO de los nodos hijos
     *
     * @param raiz
     * @return
     */
    public int Max(NodoG raiz) {
        int Max = -111;
        //Maximo para la computadora, buscamos el valor donde gane
        for (NodoG nodo : raiz.nodos) {
            //Preguntamos por un nodo con un valor alto MAX
            if (nodo.ganador > Max) {
                //Lo asignamos y pasamos el mejor movimiento a la raiz
                Max = nodo.ganador;
                raiz.mejorMovimiento = nodo.indice;
                //Terminamos de buscar
                if (Max == 1) {
                    break;
                }
            }
        }
        //Borramos los nodos
        raiz.nodos = null;
        return Max;
    }

    /**
     * Metodo que calcula el MINIMO de los nodos hijos
     *
     * @param raiz
     * @return
     */
    public int Min(NodoG raiz) {
        int Min = 111;
        //Minimo para el jugador
        for (NodoG nodo : raiz.nodos) {
            if (nodo.ganador < Min) {
                Min = nodo.ganador;
                raiz.mejorMovimiento = nodo.indice;
                if (Min == -1) {
                    break;
                }
            }
        }
        //Borramos los nodos
        raiz.nodos = null;
        return Min;
    }

    /**
     * Metodo que comprueba si gano
     *
     * @param tablero
     * @return
     */
    public int terminado(int[] tablero) {
        //Filas
        if (tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[3] == tablero[4] && tablero[3] == tablero[5] && tablero[3] != 0) {
            return tablero[3];
        } else if (tablero[6] == tablero[7] && tablero[6] == tablero[8] && tablero[6] != 0) {
            return tablero[6];
        } //Columnas
        else if (tablero[0] == tablero[3] && tablero[0] == tablero[6] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[1] == tablero[4] && tablero[1] == tablero[7] && tablero[1] != 0) {
            return tablero[1];
        } else if (tablero[2] == tablero[5] && tablero[2] == tablero[8] && tablero[2] != 0) {
            return tablero[2];
        } //Diagonales
        else if (tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != 0) {
            return tablero[2];
        }
        return 0;
    }
}
