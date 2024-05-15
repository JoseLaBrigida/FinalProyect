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
public class Caballo extends Pieza {

    /**
     * Constructor para el caballo.
     * @param board El tablero en el que se encuentra el caballo.
     * @param col La columna inicial del caballo.
     * @param fila La fila inicial del caballo.
     * @param esBlanco Indica si el caballo es blanco o negro.
     */
    public Caballo(Tablero board, int col, int fila, boolean esBlanco) {
        super(board); // Llama al constructor de la clase base, Pieza.
        this.col = col; // Establece la columna actual del caballo.
        this.fila = fila; // Establece la fila actual del caballo.
        this.xPos = col * board.tamanioTablero; // Calcula la posición x en píxeles en función de la columna.
        this.yPos = fila * board.tamanioTablero; // Calcula la posición y en píxeles en función de la fila.

        this.esBlanco = esBlanco; // Establece el color del caballo.
        this.nombre = "Caballo"; // Asigna el nombre de la pieza.

        // Configura la imagen de la pieza utilizando una subimagen de la plantilla de imágenes.
        this.sprite = plantillaImagen.getSubimage(3 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla)
            .getScaledInstance(board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }

    /**
     * Verifica si el movimiento es válido para el caballo.
     * @param col La columna a la que se desea mover el caballo.
     * @param fila La fila a la que se desea mover el caballo.
     * @return true si el movimiento es uno de los movimientos válidos de "L" (tres casillas en una dirección y una en perpendicular); de lo contrario, false.
     */
    public boolean esMovimientoValido(int col, int fila){
        // Verifica que el movimiento forme una "L" en términos de desplazamiento en el tablero.
        return Math.abs(col - this.col) * Math.abs(fila - this.fila) == 2;
    }

}

//public class Caballo extends Pieza {
//    
//    public Caballo(Tablero board, int col, int fila, boolean esBlanco) {
//        super(board);
//        this.col = col;
//        this.fila = fila;
//        this.xPos = col * board.tamanioTablero;
//        this.yPos = fila * board.tamanioTablero;
//        
//        this.esBlanco = esBlanco;
//        this.nombre = "Caballo";
//        
//        this.sprite = plantillaImagen.getSubimage(3 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla).getScaledInstance
//            (board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
//    }
//    
//    public boolean esMovimientoValido(int col, int fila){
//        return Math.abs(col - this.col) * Math.abs(fila - this.fila) == 2;
//    }
//    
//}