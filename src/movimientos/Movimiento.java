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
    
    public int colAnterior;
    public int colNueva;
    public int filaAnterior;
    public int filaNueva;
    
    public Pieza pieza;
    public Pieza captura;

    public Movimiento(Tablero tablero, Pieza pieza, int colNueva, int filaNueva) {
        
        this.colAnterior = colNueva;
        this.filaAnterior = filaNueva;
        this.colNueva = colNueva;
        this.filaNueva = filaNueva;
        
        this.pieza = pieza;
        this.captura = tablero.getPieza(colNueva, filaNueva);
    }
    
    
    
}
