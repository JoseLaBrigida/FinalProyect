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

    /**
     * Constructor para el peón.
     * @param board El tablero en el que se encuentra el peón.
     * @param col La columna inicial del peón.
     * @param fila La fila inicial del peón.
     * @param esBlanco Indica si el peón es blanco o negro.
     */
    public Peon(Tablero board, int col, int fila, boolean esBlanco) {
        super(board); // Llama al constructor de la clase base, Pieza.
        this.col = col; // Establece la columna actual del peón.
        this.fila = fila; // Establece la fila actual del peón.
        this.xPos = col * board.tamanioTablero; // Calcula la posición x en píxeles en función de la columna.
        this.yPos = fila * board.tamanioTablero; // Calcula la posición y en píxeles en función de la fila.

        this.esBlanco = esBlanco; // Establece el color del peón.
        this.nombre = "Peon"; // Asigna el nombre de la pieza.

        // Configura la imagen de la pieza utilizando una subimagen de la plantilla de imágenes.
        this.sprite = plantillaImagen.getSubimage(5 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla)
            .getScaledInstance(board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }
    

    /**
     * Verifica si el movimiento es válido para el peón.
     * @param col La columna a la que se desea mover el peón.
     * @param fila La fila a la que se desea mover el peón.
     * @return true si el movimiento es válido según las reglas del ajedrez para peones; de lo contrario, false.
     */
    public boolean esMovimientoValido(int col, int fila){
        
        int indiceColor = esBlanco ? 1 : -1; // Dirección del movimiento basada en el color del peón.
        
        // Movimiento estándar de un cuadro hacia adelante.
        if(this.col == col && this.fila - indiceColor == fila && tablero.getPieza(col, fila) == null){
            return true;
        }
        
        // Movimiento inicial del peón de dos cuadros hacia adelante.
        if(esPrimerMovimiento && this.col == col && this.fila - indiceColor * 2 == fila && tablero.getPieza(col, fila) == null &&
                tablero.getPieza(col, fila + indiceColor) == null){
            return true;
        }
        
        // Captura diagonal a la izquierda.
        if(col == this.col - 1 && this.fila - indiceColor == fila && tablero.getPieza(col, fila) != null){
            return true;
        }
        
        // Captura diagonal a la derecha.
        if(col == this.col + 1 && this.fila - indiceColor == fila && tablero.getPieza(col, fila) != null){
            return true;
        }
        
        // Captura al paso a la izquierda.
        if(tablero.getCapturaAlPaso(col, fila) == tablero.capturaAlPaso && col == this.col - 1 && fila == this.fila - indiceColor && 
                tablero.getPieza(col, fila + indiceColor) != null){
            return true;
        }
        
        // Captura al paso a la derecha.
        if(tablero.getCapturaAlPaso(col, fila) == tablero.capturaAlPaso && col == this.col + 1 && fila == this.fila - indiceColor && 
                tablero.getPieza(col, fila + indiceColor) != null){
            return true;
        }
        
        return false; // Retorna false si ninguna de las condiciones anteriores es verdadera.
    }
    
}
