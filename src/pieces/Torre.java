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
public class Torre extends Pieza {
    
    public Torre(Tablero board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.fila = row;
        this.xPos = col * board.tamanioTablero;
        this.yPos = row * board.tamanioTablero;
        
        this.esBlanco = isWhite;
        this.nombre = "Torre";
        
        this.sprite = plantillaImagen.getSubimage(4 * escalaPlantilla, isWhite ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla).getScaledInstance
            (board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }
    
    public boolean esMovimientoValido(int col, int fila){
        return this.col == col || this.fila == fila;
    }
    
    public boolean movimientoColisionaConPieza(int col, int fila){
        
        //Izquierda
        if(this.col > col){
            for(int c = this.col - 1; c > col; c--){
                if(tablero.getPieza(c, this.fila) != null){
                    return true;
                }
            }
        }
        
        //Derecha
        if(this.col < col){
            for(int c = this.col + 1; c < col; c++){
                if(tablero.getPieza(c, this.fila) != null){
                    return true;
                }
            }
        }
        
        //Arriba
        if(this.fila > fila){
            for(int c = this.fila - 1; c > fila; c--){
                if(tablero.getPieza(this.col, c) != null){
                    return true;
                }
            }
        }
        
        //Abajo
        if(this.fila < fila){
            for(int c = this.fila + 1; c < fila; c++){
                if(tablero.getPieza(this.col, c) != null){
                    return true;
                }
            }
        }
        
        return false;
    }
    
}