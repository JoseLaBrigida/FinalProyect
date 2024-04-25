/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import tablero.Tablero;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class Peon extends Pieza {
    
    public Peon(Tablero board, int col, int fila, boolean esBlanco) {
        super(board);
        this.col = col;
        this.fila = fila;
        this.xPos = col * board.tamanioTablero;
        this.yPos = fila * board.tamanioTablero;
        
        this.esBlanco = esBlanco;
        this.nombre = "Peon";
        
        this.sprite = plantillaImagen.getSubimage(5 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla).getScaledInstance
            (board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }
    
    public boolean esMovimientoValido(int col, int fila){
        
        int indiceColor = esBlanco ? 1 : -1;
        
        //Peon 1 cuadrito
        if(this.col == col && this.fila - indiceColor == fila && tablero.getPieza(col, fila) == null){
            return true;
        }
        
        //Peon 2 cuadritos
        if(esPrimerMovimiento && this.col == col && this.fila - indiceColor * 2 == fila && tablero.getPieza(col, fila) == null &&
                tablero.getPieza(col, fila + indiceColor) == null){
            return true;
        }
        
        //Captura a la izquierda
        if(col == this.col - 1 && this.fila - indiceColor == fila && tablero.getPieza(col, fila) != null){
            return true;
        }
        
        //Captura a la derecha
        if(col == this.col + 1 && this.fila - indiceColor == fila && tablero.getPieza(col, fila) != null){
            return true;
        }
        
        //Captura al paso izquierda
        if(tablero.getCapturaAlPaso(col, fila) == tablero.capturaAlPaso && col == this.col - 1 && fila == this.fila - indiceColor && 
                tablero.getPieza(col, fila + indiceColor) != null){
            return true;
        }
        
        //Captura al paso derecha
        if(tablero.getCapturaAlPaso(col, fila) == tablero.capturaAlPaso && col == this.col + 1 && fila == this.fila - indiceColor && 
                tablero.getPieza(col, fila + indiceColor) != null){
            return true;
        }
        
        
        return false;
    }
    
}
