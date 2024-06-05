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

    /**
     * Constructor para el alfil.
     * @param board El tablero en el que se encuentra el alfil.
     * @param col La columna inicial del alfil.
     * @param fila La fila inicial del alfil.
     * @param esBlanco Indica si el alfil es blanco o negro.
     */
    public Alfil(Tablero board, int col, int fila, boolean esBlanco, int valor) {
        super(board); // Llama al constructor de la clase base, Pieza.
        this.col = col; // Establece la columna actual del alfil.
        this.fila = fila; // Establece la fila actual del alfil.
        this.xPos = col * board.tamanioTablero; // Calcula la posición x en píxeles en función de la columna.
        this.yPos = fila * board.tamanioTablero; // Calcula la posición y en píxeles en función de la fila.
        this.valor = valor;

        this.esBlanco = esBlanco; // Establece el color del alfil.
        this.nombre = "Alfil"; // Asigna el nombre de la pieza.

        // Configura la imagen de la pieza utilizando una subimagen de la plantilla de imágenes.
        this.sprite = plantillaImagen.getSubimage(2 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla)
            .getScaledInstance(board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }

    /**
     * Verifica si el movimiento es válido para el alfil.
     * @param col La columna a la que se desea mover el alfil.
     * @param fila La fila a la que se desea mover el alfil.
     * @return true si el movimiento es diagonal; de lo contrario, false.
     */
    public boolean esMovimientoValido(int col, int fila){
        // Verifica que el movimiento sea estrictamente diagonal.
        return Math.abs(this.col - col) == Math.abs(this.fila - fila);
    }
    
    @Override
    public Pieza clonar() {
        return new Alfil(this.tablero, this.col, this.fila, this.esBlanco, this.valor);
    }

    /**
     * Determina si el movimiento del alfil colisiona con otra pieza.
     * @param col La columna a la que se desea mover el alfil.
     * @param fila La fila a la que se desea mover el alfil.
     * @return true si hay una pieza entre la posición inicial y final; de lo contrario, false.
     */
    public boolean movimientoColisionaConPieza(int col, int fila){
        // Comprueba colisiones en las cuatro posibles direcciones diagonales.
        
        // Arriba Izquierda
        if(this.col > col && this.fila > fila){
            for(int i = 1; i < Math.abs(this.col - col); i++){
                if(tablero.getPieza(this.col - i, this.fila - i) != null){
                    return true; // Retorna true si encuentra una pieza en el camino.
                }
            }
        }
        
        // Arriba derecha
        if(this.col < col && this.fila > fila){
            for(int i = 1; i < Math.abs(this.col - col); i++){
                if(tablero.getPieza(this.col + i, this.fila - i) != null){
                    return true;
                }
            }
        }
        
        // Abajo Izquierda
        if(this.col > col && this.fila < fila){
            for(int i = 1; i < Math.abs(this.col - col); i++){
                if(tablero.getPieza(this.col - i, this.fila + i) != null){
                    return true;
                }
            }
        }
        
        // Abajo derecha
        if(this.col < col && this.fila < fila){
            for(int i = 1; i < Math.abs(this.col - col); i++){
                if(tablero.getPieza(this.col + i, this.fila + i) != null){
                    return true;
                }
            }
        }
        
        return false; // Retorna false si no hay colisiones en el camino.
    }
}
