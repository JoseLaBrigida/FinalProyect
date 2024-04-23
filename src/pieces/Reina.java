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
public class Reina extends Pieza {
    
    public Reina(Tablero board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.fila = row;
        this.xPos = col * board.tamanioTablero;
        this.yPos = row * board.tamanioTablero;
        
        this.esBlanco = isWhite;
        this.nombre = "Queen";
        
        this.sprite = plantillaImagen.getSubimage(1 * escalaPlantilla, isWhite ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla).getScaledInstance
            (board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }
    
}