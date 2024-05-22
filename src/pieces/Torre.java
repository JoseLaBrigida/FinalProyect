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

    /**
     * Constructor para la torre.
     * @param board El tablero en el que se encuentra la torre.
     * @param col La columna inicial de la torre.
     * @param fila La fila inicial de la torre.
     * @param esBlanco Indica si la torre es blanca o negra.
     */
    public Torre(Tablero board, int col, int fila, boolean esBlanco) {
        super(board); // Llama al constructor de la clase base, Pieza.
        this.col = col; // Establece la columna actual de la torre.
        this.fila = fila; // Establece la fila actual de la torre.
        this.xPos = col * board.tamanioTablero; // Calcula la posición x en píxeles en función de la columna.
        this.yPos = fila * board.tamanioTablero; // Calcula la posición y en píxeles en función de la fila.

        this.esBlanco = esBlanco; // Establece el color de la torre.
        this.nombre = "Torre"; // Asigna el nombre de la pieza.

        // Configura la imagen de la pieza utilizando una subimagen de la plantilla de imágenes.
        this.sprite = plantillaImagen.getSubimage(4 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla)
            .getScaledInstance(board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }
    

    /**
     * Verifica si el movimiento es válido para la torre.
     * @param col La columna a la que se desea mover la torre.
     * @param fila La fila a la que se desea mover la torre.
     * @return true si el movimiento es en línea recta horizontal o vertical; de lo contrario, false.
     */
    public boolean esMovimientoValido(int col, int fila){
        // Permite movimiento en cualquier dirección horizontal o vertical.
        return this.col == col || this.fila == fila;
    }

    /**
     * Determina si el movimiento de la torre colisiona con otra pieza.
     * @param col La columna a la que se desea mover la torre.
     * @param fila La fila a la que se desea mover la torre.
     * @return true si hay una pieza entre la posición inicial y final; de lo contrario, false.
     */
    public boolean movimientoColisionaConPieza(int col, int fila){
        // Izquierda
        if(this.col > col){
            for(int c = this.col - 1; c > col; c--){
                if(tablero.getPieza(c, this.fila) != null){
                    return true;
                }
            }
        }
        
        // Derecha
        if(this.col < col){
            for(int c = this.col + 1; c < col; c++){
                if(tablero.getPieza(c, this.fila) != null){
                    return true;
                }
            }
        }
        
        // Arriba
        if(this.fila > fila){
            for(int c = this.fila - 1; c > fila; c--){
                if(tablero.getPieza(this.col, c) != null){
                    return true;
                }
            }
        }
        
        // Abajo
        if(this.fila < fila){
            for(int c = this.fila + 1; c < fila; c++){
                if(tablero.getPieza(this.col, c) != null){
                    return true;
                }
            }
        }
        
        return false; // Retorna false si no hay colisiones en el camino.
    }
    
}
