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
        this.nombre = "Reina";

        this.sprite = plantillaImagen.getSubimage(1 * escalaPlantilla, isWhite ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla).getScaledInstance(board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }

    public boolean esMovimientoValido(int col, int fila) {
        return this.col == col || this.fila == fila || Math.abs(this.col - col) == Math.abs(this.fila - fila);
    }

    public boolean movimientoColisionaConPieza(int col, int fila) {

        if (this.col == col || this.fila == fila) {
            //Izquierda
            if (this.col > col) {
                for (int c = this.col - 1; c > col; c--) {
                    if (tablero.getPieza(c, this.fila) != null) {
                        return true;
                    }
                }
            }

            //Derecha
            if (this.col < col) {
                for (int c = this.col + 1; c < col; c++) {
                    if (tablero.getPieza(c, this.fila) != null) {
                        return true;
                    }
                }
            }

            //Arriba
            if (this.fila > fila) {
                for (int c = this.fila - 1; c > fila; c--) {
                    if (tablero.getPieza(this.col, c) != null) {
                        return true;
                    }
                }
            }

            //Abajo
            if (this.fila < fila) {
                for (int c = this.fila + 1; c < fila; c++) {
                    if (tablero.getPieza(this.col, c) != null) {
                        return true;
                    }
                }
            }
        } else {
            //Arriba Izquierda
            if (this.col > col && this.fila > fila) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (tablero.getPieza(this.col - i, this.fila - i) != null) {
                        return true;
                    }
                }
            }

            //Arriba derecha
            if (this.col < col && this.fila > fila) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (tablero.getPieza(this.col + i, this.fila - i) != null) {
                        return true;
                    }
                }
            }

            //Abajo Izquierda
            if (this.col > col && this.fila < fila) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (tablero.getPieza(this.col - i, this.fila + i) != null) {
                        return true;
                    }
                }
            }

            //Abajo derecha
            if (this.col < col && this.fila < fila) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (tablero.getPieza(this.col + i, this.fila + i) != null) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

}
