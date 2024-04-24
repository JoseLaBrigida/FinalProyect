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
public class Alfil extends Pieza {
    
    public Alfil(Tablero board, int col, int fila, boolean esBlanco) {
        super(board);
        this.col = col;
        this.fila = fila;
        this.xPos = col * board.tamanioTablero;
        this.yPos = fila * board.tamanioTablero;
        
        this.esBlanco = esBlanco;
        this.nombre = "Alfil";
        
        this.sprite = plantillaImagen.getSubimage(2 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla).getScaledInstance
            (board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }
    
    public boolean esMovimientoValido(int col, int fila){
        return Math.abs(this.col - col) == Math.abs(this.fila - fila);
    }
    
    public boolean movimientoColisionaConPieza(int col, int fila){
        
        //Arriba Izquierda
        if(this.col > col && this.fila > fila){
            for(int i = 1; i < Math.abs(this.col - col); i++){
                if(tablero.getPieza(this.col - i, this.fila - i) != null){
                    return true;
                }
            }
        }
        
        //Arriba derecha
        if(this.col < col && this.fila > fila){
            for(int i = 1; i < Math.abs(this.col - col); i++){
                if(tablero.getPieza(this.col + i, this.fila - i) != null){
                    return true;
                }
            }
        }
        
        //Abajo Izquierda
        if(this.col > col && this.fila < fila){
            for(int i = 1; i < Math.abs(this.col - col); i++){
                if(tablero.getPieza(this.col - i, this.fila + i) != null){
                    return true;
                }
            }
        }
        
        //Abajo derecha
        if(this.col < col && this.fila < fila){
            for(int i = 1; i < Math.abs(this.col - col); i++){
                if(tablero.getPieza(this.col + i, this.fila + i) != null){
                    return true;
                }
            }
        }
        
        return false;
    }
}