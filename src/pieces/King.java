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
    
}
