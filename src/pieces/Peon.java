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
    
}
