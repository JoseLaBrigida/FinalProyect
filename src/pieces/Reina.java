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

    /**
     * Constructor para la reina.
     * @param board El tablero en el que se encuentra la reina.
     * @param col La columna inicial de la reina.
     * @param fila La fila inicial de la reina.
     * @param esBlanco Indica si la reina es blanca o negra.
     */
    public Reina(Tablero board, int col, int fila, boolean esBlanco) {
        super(board); // Llama al constructor de la clase base, Pieza.
        this.col = col; // Establece la columna actual de la reina.
        this.fila = fila; // Establece la fila actual de la reina.
        this.xPos = col * board.tamanioTablero; // Calcula la posición x en píxeles en función de la columna.
        this.yPos = fila * board.tamanioTablero; // Calcula la posición y en píxeles en función de la fila.

        this.esBlanco = esBlanco; // Establece el color de la reina.
        this.nombre = "Reina"; // Asigna el nombre de la pieza.

        // Configura la imagen de la pieza utilizando una subimagen de la plantilla de imágenes.
        this.sprite = plantillaImagen.getSubimage(1 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla)
            .getScaledInstance(board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }


    /**
     * Verifica si el movimiento es válido para la reina.
     * @param col La columna a la que se desea mover la reina.
     * @param fila La fila a la que se desea mover la reina.
     * @return true si el movimiento es en línea recta o diagonal; de lo contrario, false.
     */
    public boolean esMovimientoValido(int col, int fila) {
        // Permite movimiento en cualquier dirección, tanto en líneas rectas como en diagonales.
        return this.col == col || this.fila == fila || Math.abs(this.col - col) == Math.abs(this.fila - fila);
    }

    /**
     * Determina si el movimiento del reina colisiona con otra pieza.
     * @param col La columna a la que se desea mover la reina.
     * @param fila La fila a la que se desea mover la reina.
     * @return true si hay una pieza entre la posición inicial y final; de lo contrario, false.
     */
    public boolean movimientoColisionaConPieza(int col, int fila) {

        if (this.col == col || this.fila == fila) { // Movimiento en línea recta.
            // Izquierda
            if (this.col > col) {
                for (int c = this.col - 1; c > col; c--) {
                    if (tablero.getPieza(c, this.fila) != null) {
                        return true;
                    }
                }
            }

            // Derecha
            if (this.col < col) {
                for (int c = this.col + 1; c < col; c++) {
                    if (tablero.getPieza(c, this.fila) != null) {
                        return true;
                    }
                }
            }

            // Arriba
            if (this.fila > fila) {
                for (int c = this.fila - 1; c > fila; c--) {
                    if (tablero.getPieza(this.col, c) != null) {
                        return true;
                    }
                }
            }

            // Abajo
            if (this.fila < fila) {
                for (int c = this.fila + 1; c < fila; c++) {
                    if (tablero.getPieza(this.col, c) != null) {
                        return true;
                    }
                }
            }
        } else { // Movimiento diagonal.
            // Arriba Izquierda
            if (this.col > col && this.fila > fila) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (tablero.getPieza(this.col - i, this.fila - i) != null) {
                        return true;
                    }
                }
            }

            // Arriba derecha
            if (this.col < col && this.fila > fila) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (tablero.getPieza(this.col + i, this.fila - i) != null) {
                        return true;
                    }
                }
            }

            // Abajo Izquierda
            if (this.col > col && this.fila < fila) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (tablero.getPieza(this.col - i, this.fila + i) != null) {
                        return true;
                    }
                }
            }

            // Abajo derecha
            if (this.col < col && this.fila < fila) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (tablero.getPieza(this.col + i, this.fila + i) != null) {
                        return true;
                    }
                }
            }

        }

        return false; // Retorna false si no hay colisiones en el camino.
    }

}
