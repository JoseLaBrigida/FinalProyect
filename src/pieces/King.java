/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import tablero.Tablero;
import java.awt.image.BufferedImage;
import movimientos.Movimiento;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class King extends Pieza {
    
    public King(Tablero board, int col, int fila, boolean esBlanco) {
        super(board);
        this.col = col;
        this.fila = fila;
        this.xPos = col * board.tamanioTablero;
        this.yPos = fila * board.tamanioTablero;
        
        this.esBlanco = esBlanco;
        this.nombre = "Rey";
        
        this.sprite = plantillaImagen.getSubimage(0 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla).getScaledInstance
            (board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }
    
    public boolean esMovimientoValido(int col, int fila){
        return Math.abs((col - this.col) * (fila - this.fila)) == 1 || Math.abs(col - this.col) + Math.abs(fila - this.fila) == 1 || puedeEnroque(col, fila);
    }
    
    
    
    private boolean puedeEnroque(int col, int fila){
        
        if(this.fila == fila){
            if(col == 6){
                Pieza torre = tablero.getPieza(7, fila);
                if(torre != null && torre.esPrimerMovimiento && esPrimerMovimiento){
                    return tablero.getPieza(5, fila) == null &&
                            tablero.getPieza(6, fila) == null &&
                            !tablero.comprobadorJaque.reyJaque(new Movimiento(tablero, this, 5, fila));
                }
            } else if(col == 2){
                Pieza torre = tablero.getPieza(0, fila);
                if(torre != null && torre.esPrimerMovimiento && esPrimerMovimiento){
                    return tablero.getPieza(3, fila) == null &&
                            tablero.getPieza(2, fila) == null &&
                            tablero.getPieza(1, fila) == null &&
                            !tablero.comprobadorJaque.reyJaque(new Movimiento(tablero, this, 3, fila));
                }
            } 
        }
        
        return false;
    }
    
}
