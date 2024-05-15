/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movimientos;

import pieces.Pieza;
import tablero.Tablero;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class Movimiento {
    
    // Campos que representan la columna y fila de la posición inicial de la pieza.
    public int colAnterior;
    public int colNueva;
    public int filaAnterior;
    public int filaNueva;
    
    // La pieza que se mueve.
    public Pieza pieza;
    // La pieza que es capturada en el movimiento, si existe.
    public Pieza captura;

    /**
     * Constructor para crear un nuevo movimiento.
     * @param tablero El tablero en el que se realiza el movimiento.
     * @param pieza La pieza que se está moviendo.
     * @param colNueva La columna destino de la pieza.
     * @param filaNueva La fila destino de la pieza.
     */
    public Movimiento(Tablero tablero, Pieza pieza, int colNueva, int filaNueva) {
        
        this.colAnterior = pieza.col; // Almacena la columna original de la pieza antes del movimiento.
        this.filaAnterior = pieza.fila; // Almacena la fila original de la pieza antes del movimiento.
        this.colNueva = colNueva; // Almacena la nueva columna a la que se mueve la pieza.
        this.filaNueva = filaNueva; // Almacena la nueva fila a la que se mueve la pieza.
        
        this.pieza = pieza; // Referencia a la pieza que se mueve.
        this.captura = tablero.getPieza(colNueva, filaNueva); // Almacena cualquier pieza que esté en la posición destino y que será capturada.
    }
}
