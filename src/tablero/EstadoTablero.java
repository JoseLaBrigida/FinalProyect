/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import java.util.ArrayList;
import pieces.Pieza;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class EstadoTablero {
    ArrayList<Pieza> listaPiezas;
    boolean esTurnoBlanca;
    boolean turnoJugador;
    boolean isGameOver;

    public EstadoTablero(ArrayList<Pieza> listaPiezas, boolean esTurnoBlanca, boolean turnoJugador, boolean isGameOver) {
        this.listaPiezas = new ArrayList<>(listaPiezas);
        this.esTurnoBlanca = esTurnoBlanca;
        this.turnoJugador = turnoJugador;
        this.isGameOver = isGameOver;
    }
}
