/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_analisesydiseño;

import java.sql.ResultSet; //Importacion para guardar registros
import java.util.Arrays; // Importacion para guardar arregloso
import javax.swing.JOptionPane; //Importacion para Avisos al usuario

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */
public class frmTablero extends javax.swing.JFrame {

    /*Instancia*/
    frmMenu menu = new frmMenu();//instancia de frmMenu de Menu
    BaseDatos coneccion = Proyecto_AnalisesyDiseño.coneccion; //Instancia de la clase BaseDatos para las consultas
    Ficha imagen = new Ficha();//Instancia de clase Fichas para importacion de imagenes
    Jugador jugador1, jugador2;//Instancia para los Jugadores y inicializar atributos
    ComputadoraIA inteligencia;//Instancia a la clase ComputadoraIA
    Sonidos sonido = new Sonidos();//Instancia a la clase para sonidos
    /*Variables para inicializar el Juego de 0*/
    int[] tablero = new int[9];//Tablero del juego
    int turno = 1;//Variable para indicar el turno del jugador
    boolean terminado = false;//Variable sirve para saber si el juego termino
    boolean CPU = false;//Variable que establece si juega contra la computadora 
    int tipo;//tipo de juego si es de 2 o 1 jugador 
    boolean empate = false;//si es empate se activa
    int primero, segundo;//contadores de victortias
    ResultSet resultados;//Variable para guardar registros de la base de datos

    /**
     * Metodo recibe insformacion de frmJugador segun su tipo de juego Guardado,
     * establece la informacion en clase Jugador
     *
     * @param jugador1
     * @param jugador2
     * @param jugadores
     */
    public void iniciarJuego(String jugador1, String jugador2, int jugadores) {
        //jugar vs jugador
        if (2 == jugadores) {
            this.jugador1 = coneccion.cargar_jugadores(jugador1, 2);
            this.jugador2 = coneccion.cargar_jugadores(jugador2, 2);
        } else {
            //jugadores vs computadora
            this.jugador1 = coneccion.cargar_jugadores(jugador1, 1);
            this.jugador2 = coneccion.cargar_jugadores("Computadora", 1);
            CPU = true;
            //Creamos la instancia de la clase ComputadoraIA
            inteligencia = new ComputadoraIA();
        }
        tipo = jugadores;
        //Mostramos su información de jugadores
        mostrarInformacion();
    }

    /**
     * Metodo muestra informacion de los jugadores en el tablero
     */
    private void mostrarInformacion() {
        //Establecemos el título de tablero
        lb_Ujugador.setText(jugador1.nombre);
        lb_Djugador.setText(jugador2.nombre);
        //Establecemos las estadísticas del jugador
        //jugador uno
        this.lb_ganados1.setText("Ganados: " + jugador1.ganadas);
        this.lb_perdidos1.setText("Perdidos: " + jugador1.perdidas);
        this.lb_empatados1.setText("Empatados: " + jugador1.empates);
        //jugador dos
        this.lb_ganados2.setText("Ganados: " + jugador2.ganadas);
        this.lb_perdidos2.setText("Perdidos: " + jugador2.perdidas);
        this.lb_empatados2.setText("Empatados: " + jugador2.empates);
        //establece el turno del jugador en el tablero 
        if (turno == 1) {
            lb_inicia.setText("Turno de " + jugador1.nombre);
        } else {
            lb_inicia.setText("Turno de " + jugador2.nombre);
        }
    }

    /**
     * Verifica si el juego ha termino Si el juego aun no ha termino y juega
     * contra la computadora la computadora jugara Verifica si algun jugador ha
     * ganano o empatado o si el tablero esta lleno
     *
     * @param ficha
     */
    private void movimiento(int ficha) {
        /*Colocamos la fichasde de jugadores*/
        if (!terminado) {
            //Reproducimos el sonido
            sonido.sonidoFicha();
            PonerFicha(ficha, turno - 1);

            //si la computadora juega o no
            if (CPU == true && turno == 2) {
                PonerFicha(inteligencia.movimiento(tablero), turno - 1);
                //Reproducimos el sonido
                sonido.sonidoFicha();
            }
        }

        /*Comenzar un juego nuevo si el juego termino*/
        if (terminado) {
            reiniciarJuego();
            if (coneccion.ExistePartidas()) {
                coneccion.EliminarPartida();
            }
            return;
        }

        /*Pregunta si el juego termino o si un jugador ha ganado*/
        if (terminado() != 0) {
            //Sonido
            sonido.sonidoGano();
            //Actualizamos la informacion de jugador 
            if (terminado() == 1) {
                jugador1.gano();
                coneccion.actualizar_jugador(jugador1);
                jugador2.perdio();
                coneccion.actualizar_jugador(jugador2);
                coneccion.EliminarPartida();
                primero++;
                JOptionPane.showMessageDialog(null, jugador1.nombre + " ganó!", "Informativo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                jugador2.gano();
                coneccion.actualizar_jugador(jugador2);
                jugador1.perdio();
                coneccion.actualizar_jugador(jugador1);
                coneccion.EliminarPartida();
                segundo++;
                JOptionPane.showMessageDialog(null, jugador2.nombre + " ganó!", "Informativo", JOptionPane.INFORMATION_MESSAGE);
            }
            //Muestra la información
            mostrarInformacion();
            //Detenemos el juego
            terminado = true;
            empate = false;
            //Verifica si el tablero esta lleno para establecer un empate
        } else if (lleno()) {
            jugador1.empato();
            coneccion.actualizar_jugador(jugador1);
            jugador2.empato();
            coneccion.actualizar_jugador(jugador2);
            JOptionPane.showMessageDialog(null, "Empate !!!", "Informativo", JOptionPane.INFORMATION_MESSAGE);
            //Muestra la información
            mostrarInformacion();
            //Detenemos el juego
            terminado = true;
            empate = true;
        }
        //muestra informacion
        mostrarInformacion();
    }

    /**
     * Metodo Verifica si la casilla esta ocupada la casilla seleccionada
     * retornando el estado true o false
     *
     * @param casilla
     * @return
     */
    private boolean estaOcupada(int casilla) {
        return tablero[casilla] != 0;
    }

    /**
     * Metodo que verifica si el juego tiene una linea completa y muestra una
     * linea ubicando el gane Si una linea esta completa retorna el numero de la
     * linea
     *
     * @return
     */
    private int terminado() {
        /*Comprobamos si el juego terminó.*/
 /*Filas*/
        if (tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != 0) {
            lb_raya.setIcon(imagen.fichas(4));
            return tablero[0];
        } else if (tablero[3] == tablero[4] && tablero[3] == tablero[5] && tablero[3] != 0) {
            lb_raya.setIcon(imagen.fichas(5));
            return tablero[3];
        } else if (tablero[6] == tablero[7] && tablero[6] == tablero[8] && tablero[6] != 0) {
            lb_raya.setIcon(imagen.fichas(6));
            return tablero[6];
        } /*Columnas*/ else if (tablero[0] == tablero[3] && tablero[0] == tablero[6] && tablero[0] != 0) {
            lb_raya.setIcon(imagen.fichas(7));
            return tablero[0];
        } else if (tablero[1] == tablero[4] && tablero[1] == tablero[7] && tablero[1] != 0) {
            lb_raya.setIcon(imagen.fichas(8));
            return tablero[1];
        } else if (tablero[2] == tablero[5] && tablero[2] == tablero[8] && tablero[2] != 0) {
            lb_raya.setIcon(imagen.fichas(9));
            return tablero[2];
        } /*Diagonales*/ else if (tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] != 0) {
            lb_raya.setIcon(imagen.fichas(2));
            return tablero[0];
        } else if (tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != 0) {
            lb_raya.setIcon(imagen.fichas(3));
            return tablero[2];
        }
        return 0;
    }

    /**
     * verifica si el arreglo del tablero esta vacio, retorna un false o true
     * segun se estado
     *
     * @return
     */
    private boolean lleno() {
        boolean lleno = true;
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i] == 0) {
                lleno = false;
            }
        }
        return lleno;
    }

    /**
     * Metodo verifica si el campo esta ocupado sino para poner imagen y seguir
     * el juego
     *
     * @param indice
     */
    private void PonerFicha(int ficha, int jugador) {
        /*Verifica si el campo del tablero esta ocupado*/
        if (estaOcupada(ficha)) {
            return;
        }
        /*Importa la imagen segun la casilla seleccionada*/
        CargarFicha(ficha, jugador);
        /*Cambiamos el turno*/
        if (1 == turno) {
            tablero[ficha] = 1;
            turno = 2;
        } else {
            tablero[ficha] = 2;
            turno = 1;
        }
    }

    /**
     * Metodo que muestra imagen en el label segun el cual fue seleccionado y
     * segun el jugador que lo selecciono
     *
     * @param indice
     */
    private void CargarFicha(int ficha, int jugador) {
        switch (ficha) {
            case 0:
                c1.setIcon(imagen.fichas(jugador));
                break;
            case 1:
                c2.setIcon(imagen.fichas(jugador));
                break;
            case 2:
                c3.setIcon(imagen.fichas(jugador));
                break;
            case 3:
                c4.setIcon(imagen.fichas(jugador));
                break;
            case 4:
                c5.setIcon(imagen.fichas(jugador));
                break;
            case 5:
                c6.setIcon(imagen.fichas(jugador));
                break;
            case 6:
                c7.setIcon(imagen.fichas(jugador));
                break;
            case 7:
                c8.setIcon(imagen.fichas(jugador));
                break;
            case 8:
                c9.setIcon(imagen.fichas(jugador));
                break;
        }
    }

    /**
     * Método que inicia un nuevo juego
     */
    private void reiniciarJuego() {
        /*cambiar turno si es un empate*/
        if (CPU == false) {
            if (empate != true) {
                if (1 == turno) {
                    turno = 2;
                } else {
                    turno = 1;
                }
            }
            //Contra la Computadora
        } else {
            turno = 1;
        }
        /*Llena el tablero con 0*/
        Arrays.fill(tablero, 0);
        /*Limpiar imagenes de los label*/
        c1.setIcon(null);
        c2.setIcon(null);
        c3.setIcon(null);
        c4.setIcon(null);
        c5.setIcon(null);
        c6.setIcon(null);
        c7.setIcon(null);
        c8.setIcon(null);
        c9.setIcon(null);
        lb_raya.setIcon(null);
        terminado = false;
        //Mostramos su información
        mostrarInformacion();
    }

    /**
     * Carga los datos de un juego no terminado
     */
    public void cargar() {
        try {
            resultados = coneccion.EstablecerTablero();
            String cadena = resultados.getString("tabla");
            String[] split_cadena;
            int[] opciones = new int[9];
            split_cadena = cadena.split(",");
            for (int x = 0; x < split_cadena.length; x++) {
                if (split_cadena[x].contains("{")) {
                    String letra = String.valueOf(split_cadena[x].charAt(1));
                    opciones[x] = Integer.parseInt(letra);
                } else if (split_cadena[x].contains("}")) {
                    String letra = String.valueOf(split_cadena[x].charAt(0));
                    opciones[x] = Integer.parseInt(letra);
                } else {
                    opciones[x] = Integer.parseInt(split_cadena[x]);
                }
            }
            this.tablero = opciones;
            for (int x = 0; x < opciones.length; x++) {
                if (opciones[x] != 0) {
                    CargarFicha(x, opciones[x] - 1);
                }
            }
            primero = resultados.getInt("ganadas");
            segundo = resultados.getInt("ganadas2");
            turno = resultados.getInt("turno");
            iniciarJuego(resultados.getString("nick_jugador1"), resultados.getString("nick_jugador2"), resultados.getInt("tipo"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Costructor del formulario
     */
    public frmTablero() {
        initComponents();
        //localiza el form en el centro del escritorio
        setLocationRelativeTo(null);
        //ocuta el label de desactivar el volumen
        lb_Bvolumen.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_UJugador = new javax.swing.JPanel();
        lb_nombre1 = new javax.swing.JLabel();
        lb_Ujugador = new javax.swing.JLabel();
        lb_ganados1 = new javax.swing.JLabel();
        lb_empatados1 = new javax.swing.JLabel();
        lb_perdidos1 = new javax.swing.JLabel();
        lb_figura1 = new javax.swing.JLabel();
        lb_ficha1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        panel_DJugador = new javax.swing.JPanel();
        lb_figura2 = new javax.swing.JLabel();
        lb_ficha2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lb_perdidos2 = new javax.swing.JLabel();
        lb_empatados2 = new javax.swing.JLabel();
        lb_ganados2 = new javax.swing.JLabel();
        lb_Djugador = new javax.swing.JLabel();
        lb_nombre2 = new javax.swing.JLabel();
        lb_inicia = new javax.swing.JLabel();
        btn_Salir = new javax.swing.JButton();
        lb_Avolumen = new javax.swing.JLabel();
        c1 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        c3 = new javax.swing.JLabel();
        c4 = new javax.swing.JLabel();
        c5 = new javax.swing.JLabel();
        c6 = new javax.swing.JLabel();
        c7 = new javax.swing.JLabel();
        c8 = new javax.swing.JLabel();
        c9 = new javax.swing.JLabel();
        lblTablero1 = new javax.swing.JLabel();
        lb_Bvolumen = new javax.swing.JLabel();
        lb_raya = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("GATO");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_UJugador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Jugador 1"));

        lb_nombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_nombre1.setText("Nombre:");

        lb_Ujugador.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lb_Ujugador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lb_ganados1.setText("Juegos Ganados: 0");

        lb_empatados1.setText("Juegos Empatados: 0");

        lb_perdidos1.setText("Juegos Perdidos: 0");

        lb_figura1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_figura1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cruz.png"))); // NOI18N

        lb_ficha1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_ficha1.setText("Ficha:");

        javax.swing.GroupLayout panel_UJugadorLayout = new javax.swing.GroupLayout(panel_UJugador);
        panel_UJugador.setLayout(panel_UJugadorLayout);
        panel_UJugadorLayout.setHorizontalGroup(
            panel_UJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_UJugadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_UJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_Ujugador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_figura1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_nombre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_ficha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_UJugadorLayout.createSequentialGroup()
                        .addGroup(panel_UJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_ganados1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_perdidos1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_empatados1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_UJugadorLayout.setVerticalGroup(
            panel_UJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_UJugadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_nombre1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_Ujugador, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_ganados1)
                .addGap(18, 18, 18)
                .addComponent(lb_empatados1)
                .addGap(18, 18, 18)
                .addComponent(lb_perdidos1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_ficha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_figura1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(panel_UJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 180, 360));

        panel_DJugador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Jugador 2"));

        lb_figura2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_figura2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo.png"))); // NOI18N

        lb_ficha2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_ficha2.setText("Ficha:");

        lb_perdidos2.setText("Juegos Perdidos: 0");

        lb_empatados2.setText("Juegos Empatados: 0");

        lb_ganados2.setText("Juegos Ganados: 0");

        lb_Djugador.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lb_Djugador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lb_nombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_nombre2.setText("Nombre:");

        javax.swing.GroupLayout panel_DJugadorLayout = new javax.swing.GroupLayout(panel_DJugador);
        panel_DJugador.setLayout(panel_DJugadorLayout);
        panel_DJugadorLayout.setHorizontalGroup(
            panel_DJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DJugadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_DJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_Djugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(lb_nombre2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_DJugadorLayout.createSequentialGroup()
                        .addGroup(panel_DJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_ganados2)
                            .addComponent(lb_perdidos2)
                            .addComponent(lb_empatados2))
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(panel_DJugadorLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lb_ficha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lb_figura2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_DJugadorLayout.setVerticalGroup(
            panel_DJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DJugadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_nombre2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_Djugador, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_ganados2)
                .addGap(18, 18, 18)
                .addComponent(lb_empatados2)
                .addGap(18, 18, 18)
                .addComponent(lb_perdidos2)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lb_ficha2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_figura2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(panel_DJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 60, 180, 360));

        lb_inicia.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lb_inicia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_inicia.setText("Inicia el Juego!!");
        getContentPane().add(lb_inicia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 630, -1));

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, 71, -1));

        lb_Avolumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sonido_max.png"))); // NOI18N
        lb_Avolumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_AvolumenMouseClicked(evt);
            }
        });
        getContentPane().add(lb_Avolumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, 40));

        c1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        c1.setName("p1"); // NOI18N
        c1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c1MouseClicked(evt);
            }
        });
        getContentPane().add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 90, 80));

        c2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        c2.setName("p1"); // NOI18N
        c2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c2MouseClicked(evt);
            }
        });
        getContentPane().add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 90, 80));

        c3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        c3.setName("p1"); // NOI18N
        c3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c3MouseClicked(evt);
            }
        });
        getContentPane().add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 90, 80));

        c4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        c4.setName("p1"); // NOI18N
        c4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c4MouseClicked(evt);
            }
        });
        getContentPane().add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 190, 90, 80));

        c5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        c5.setName("p1"); // NOI18N
        c5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c5MouseClicked(evt);
            }
        });
        getContentPane().add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 90, 80));

        c6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        c6.setName("p1"); // NOI18N
        c6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c6MouseClicked(evt);
            }
        });
        getContentPane().add(c6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 90, 80));

        c7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        c7.setName("p1"); // NOI18N
        c7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c7MouseClicked(evt);
            }
        });
        getContentPane().add(c7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 90, 90));

        c8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        c8.setName("p1"); // NOI18N
        c8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c8MouseClicked(evt);
            }
        });
        getContentPane().add(c8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 90, 90));

        c9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        c9.setName("p1"); // NOI18N
        c9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c9MouseClicked(evt);
            }
        });
        getContentPane().add(c9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 100, 90));

        lblTablero1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTablero1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tablero.png"))); // NOI18N
        getContentPane().add(lblTablero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 310, 320));

        lb_Bvolumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sonido_mute.png"))); // NOI18N
        lb_Bvolumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_BvolumenMouseClicked(evt);
            }
        });
        getContentPane().add(lb_Bvolumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, -1));

        lb_raya.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_raya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tablero.png"))); // NOI18N
        getContentPane().add(lb_raya, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 310, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Label del Juego para X o O
     *
     * @param evt
     */
    private void c1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c1MouseClicked
        movimiento(0);
    }//GEN-LAST:event_c1MouseClicked

    private void c2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c2MouseClicked
        movimiento(1);
    }//GEN-LAST:event_c2MouseClicked

    private void c3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c3MouseClicked
        movimiento(2);
    }//GEN-LAST:event_c3MouseClicked

    private void c4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c4MouseClicked
        movimiento(3);
    }//GEN-LAST:event_c4MouseClicked

    private void c5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c5MouseClicked
        movimiento(4);
    }//GEN-LAST:event_c5MouseClicked

    private void c6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c6MouseClicked
        movimiento(5);
    }//GEN-LAST:event_c6MouseClicked

    private void c7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c7MouseClicked
        movimiento(6);
    }//GEN-LAST:event_c7MouseClicked

    private void c8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c8MouseClicked
        movimiento(7);
    }//GEN-LAST:event_c8MouseClicked

    private void c9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c9MouseClicked
        movimiento(8);
    }//GEN-LAST:event_c9MouseClicked

    /**
     * Si el Juego no ha termino se guarda en la base de datos si no solo sale y
     * muestra quien gano
     *
     * @param evt
     */
    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        //Verifica si el juego termino
        if (!terminado) {
            //Si no termino verifica si en la base de datos existe una para crear un registro o actualizar
            if (coneccion.ExistePartidas()) {
                coneccion.ActualizarTabla(turno, tablero);
            } else {
                coneccion.insertar_tabla(jugador1.nombre, jugador2.nombre, turno, tipo, tablero, primero, segundo);
            }
            JOptionPane.showMessageDialog(null, "Su partida sera Guardada si deseas continuar", "Informativo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            coneccion.EliminarJugador("Computadora");
            if (primero > segundo) {
                JOptionPane.showMessageDialog(null, "Gracias por utilizar este juego\n El ganador de esta ronda: " + jugador1.nombre + " \nSumando: " + primero + " a su ranking", "Ganador", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Gracias por utilizar este juego\n El ganador de esta ronda: " + jugador2.nombre + " \nSumando: " + segundo + " a su ranking", "Ganador", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        this.dispose();
        menu.show();
    }//GEN-LAST:event_btn_SalirActionPerformed

    /**
     * Label q Habilita el sonido
     *
     * @param evt
     */
    private void lb_AvolumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_AvolumenMouseClicked
        //silencia el sonido
        sonido.silencio = true;
        //valida los label de activacion o desactivacion de volumen
        lb_Avolumen.setVisible(false);
        lb_Bvolumen.setVisible(true);
    }//GEN-LAST:event_lb_AvolumenMouseClicked

    /**
     * Label q Deshabilita el sonido
     *
     * @param evt
     */
    private void lb_BvolumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_BvolumenMouseClicked
        //activa el sonido
        sonido.silencio = false;
        //valida los label de activacion o desactivacion de volumen
        lb_Bvolumen.setVisible(false);
        lb_Avolumen.setVisible(true);
    }//GEN-LAST:event_lb_BvolumenMouseClicked

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
            java.util.logging.Logger.getLogger(frmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Salir;
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel c3;
    private javax.swing.JLabel c4;
    private javax.swing.JLabel c5;
    private javax.swing.JLabel c6;
    private javax.swing.JLabel c7;
    private javax.swing.JLabel c8;
    private javax.swing.JLabel c9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lb_Avolumen;
    private javax.swing.JLabel lb_Bvolumen;
    private javax.swing.JLabel lb_Djugador;
    private javax.swing.JLabel lb_Ujugador;
    private javax.swing.JLabel lb_empatados1;
    private javax.swing.JLabel lb_empatados2;
    private javax.swing.JLabel lb_ficha1;
    private javax.swing.JLabel lb_ficha2;
    private javax.swing.JLabel lb_figura1;
    private javax.swing.JLabel lb_figura2;
    private javax.swing.JLabel lb_ganados1;
    private javax.swing.JLabel lb_ganados2;
    private javax.swing.JLabel lb_inicia;
    private javax.swing.JLabel lb_nombre1;
    private javax.swing.JLabel lb_nombre2;
    private javax.swing.JLabel lb_perdidos1;
    private javax.swing.JLabel lb_perdidos2;
    private javax.swing.JLabel lb_raya;
    private javax.swing.JLabel lblTablero1;
    private javax.swing.JPanel panel_DJugador;
    private javax.swing.JPanel panel_UJugador;
    // End of variables declaration//GEN-END:variables
}
