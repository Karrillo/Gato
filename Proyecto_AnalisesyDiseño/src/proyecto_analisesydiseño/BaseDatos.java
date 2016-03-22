/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_analisesydiseño;

/**
 * importaciones para conexion y cosultas para la base de datos
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * importacion para mensajes de error en consultas o conexiones en la base de
 * datos
 */
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Miguel Rojas Guerrero
 * @author Jose Carrillo Mendez
 */
public class BaseDatos {

    String driver = "org.postgresql.Driver";  // el nombre de nuestro driver 
    String connectString = "jdbc:postgresql://localhost:5432/BD_GATO"; // llamamos nuestra bd
    String user = "postgres"; // usuario postgres
    String password = "123456"; //password de nuestra bd.

    /**
     * Conexion a la base de datos
     */
    public void conectar() {
        try {
            Class.forName(driver);
            //Hacemos la coneccion.
            Connection conn = DriverManager.getConnection(connectString, user, password);
        } //Si se produce una Excepcion y no nos podemos conectar, muestra el sgte. mensaje.
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }//Si se produce una Excepcion y no nos podemos conectar, muestra el sgte. mensaje.
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Busca un nick igual en la base de datos para restaurar datos si inicia
     * otro juego
     *
     * @param jugador
     * @param tipo
     * @return
     */
    public boolean buscar_jugador(String jugador, int tipo) {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            ResultSet resultado = consulta.executeQuery("select * from schgato.jugadores");
            while (resultado.next()) {
                if (jugador.equals(resultado.getString("nick")) && tipo == resultado.getInt("tipo")) {
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
        return false;
    }

    /**
     * Restaura los datos de un jugador ya ingresado en la base de datos
     *
     * @param jugador
     * @param tipo
     * @return
     */
    public Jugador buscar_historial(String jugador, int tipo) {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            ResultSet resultado = consulta.executeQuery("select * from schgato.jugadores");
            while (resultado.next()) {
                if (jugador.equals(resultado.getString("nick")) && tipo == resultado.getInt("tipo")) {
                    Jugador datos = new Jugador();
                    datos.nombre = jugador;
                    datos.tipo = resultado.getInt("tipo");
                    datos.ganadas = resultado.getInt("ganadas");
                    datos.empates = resultado.getInt("empatadas");
                    datos.perdidas = resultado.getInt("perdidas");
                    return datos;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
        return null;
    }

    /**
     * Cargar los datos en la base de datos de un usuario nuevo
     *
     * @param jugador
     * @param tipo
     * @return
     */
    public Jugador cargar_jugadores(String jugador, int tipo) {
        Jugador datos = new Jugador();
        if (buscar_jugador(jugador, tipo) == true) {
            return buscar_historial(jugador, tipo);
        } else {
            datos.nombre = jugador;
            datos.tipo = tipo;
            datos.ganadas = 0;
            datos.empates = 0;
            datos.perdidas = 0;
            insertar_jugador(datos);
        }
        return datos;
    }

    /**
     * Ingresar los datos de un usuario en la base de datos
     *
     * @param datos
     */
    public void insertar_jugador(Jugador datos) {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            consulta.executeUpdate("insert into schgato.jugadores(nick,tipo,ganadas,empatadas,perdidas) "
                    + "values('" + datos.nombre + "'," + datos.tipo + "," + datos.ganadas + "," + datos.empates + "," + datos.perdidas + ")");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Metodo que elimina un usuario por su nick
     *
     * @param nick
     */
    public void EliminarJugador(String nick) {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            consulta.execute("delete FROM schgato.jugadores where nick ='" + nick + "'");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Actualizar los datos de un usuario en la base de datos
     *
     * @param datos
     */
    public void actualizar_jugador(Jugador datos) {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            consulta.executeUpdate("update schgato.jugadores set ganadas = " + datos.ganadas + ","
                    + "empatadas = " + datos.empates + ",perdidas = " + datos.perdidas + " "
                    + "where nick='" + datos.nombre + "' and tipo=" + datos.tipo + "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Insertar datos de un juego no finalizado en la base de datos
     *
     * @param jugador1
     * @param Jugador2
     * @param turno
     * @param tipo
     * @param tabla
     */
    public void insertar_tabla(String jugador1, String Jugador2, int turno, int tipo, int[] tabla) {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            consulta.executeUpdate("insert into schgato.partidas(tipo,nick_jugador1,nick_jugador2,turno,tabla) "
                    + "values(" + tipo + ",'" + jugador1 + "','" + Jugador2 + "'," + turno + ",'{" + tabla[0] + "," + tabla[1] + ","
                    + tabla[2] + "," + tabla[3] + "," + tabla[4] + "," + tabla[5] + "," + tabla[6] + "," + tabla[7] + "," + tabla[8] + "}')");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Restaurar los datos de un juego aun no terminado
     *
     * @return
     */
    public ResultSet EstablecerTablero() {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            ResultSet resultado = consulta.executeQuery("select * from schgato.partidas");
            while (resultado.next()) {
                if (resultado.getInt("tipo") == 1 || resultado.getInt("tipo") == 2) {
                    return resultado;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
        return null;
    }

    /**
     * Verifica si hay una partida aun no terminada en la base de datos
     *
     * @return
     */
    public boolean ExistePartidas() {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            ResultSet resultado = consulta.executeQuery("select * from schgato.partidas");
            while (resultado.next()) {
                if (resultado.getInt("tipo") == 1 || resultado.getInt("tipo") == 2) {
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
        return false;
    }

    /**
     * Eliminar juego guardado
     */
    public void EliminarPartida() {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            consulta.execute("delete FROM schgato.partidas");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Actualizar juego no terminado
     *
     * @param turno
     * @param tabla
     */
    public void ActualizarTabla(int turno, int[] tabla) {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            consulta.executeUpdate("update schgato.partidas set turno = " + turno + ",tabla = '{" + tabla[0] + "," + tabla[1] + ","
                    + tabla[2] + "," + tabla[3] + "," + tabla[4] + "," + tabla[5] + "," + tabla[6] + "," + tabla[7] + "," + tabla[8] + "}'");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }

    /**
     * Metodo el cual consulta los datos de los jugadores para mostrarlos en las
     * estadisticas
     *
     * @param tipo
     * @return
     */
    public ResultSet todos_usuarios(int tipo) {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            Statement consulta = (Statement) conne.createStatement();
            ResultSet resultado = consulta.executeQuery("select * from schgato.jugadores where tipo = " + tipo + "order by ganadas desc");
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
        return null;
    }

    /**
     * Cerrar conexion a la base de datos
     */
    public void CerrarConexion() {
        try {
            Connection conne = (Connection) DriverManager.getConnection(connectString, user, password);
            conne.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion a la base de datos! " + e.getMessage());
        }
    }
}
