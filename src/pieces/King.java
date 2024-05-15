/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import tablero.Tablero;
import java.awt.image.BufferedImage;
import movimientos.Movimiento;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class King extends Pieza {

    /**
     * Constructor para el rey.
     * @param board El tablero en el que se encuentra el rey.
     * @param col La columna inicial del rey.
     * @param fila La fila inicial del rey.
     * @param esBlanco Indica si el rey es blanco o negro.
     */
    public King(Tablero board, int col, int fila, boolean esBlanco) {
        super(board); // Llama al constructor de la clase base, Pieza.
        this.col = col; // Establece la columna actual del rey.
        this.fila = fila; // Establece la fila actual del rey.
        this.xPos = col * board.tamanioTablero; // Calcula la posición x en píxeles en función de la columna.
        this.yPos = fila * board.tamanioTablero; // Calcula la posición y en píxeles en función de la fila.

        this.esBlanco = esBlanco; // Establece el color del rey.
        this.nombre = "Rey"; // Asigna el nombre de la pieza.

        // Configura la imagen de la pieza utilizando una subimagen de la plantilla de imágenes.
        this.sprite = plantillaImagen.getSubimage(0 * escalaPlantilla, esBlanco ? 0 : escalaPlantilla, escalaPlantilla, escalaPlantilla)
            .getScaledInstance(board.tamanioTablero, board.tamanioTablero, BufferedImage.SCALE_SMOOTH);
    }

    /**
     * Verifica si el movimiento es válido para el rey.
     * @param col La columna a la que se desea mover el rey.
     * @param fila La fila a la que se desea mover el rey.
     * @return true si el movimiento es uno adyacente en cualquier dirección o un enroque válido; de lo contrario, false.
     */
    public boolean esMovimientoValido(int col, int fila){
        // Verifica que el movimiento sea a una casilla adyacente o un enroque válido.
        return Math.abs((col - this.col) * (fila - this.fila)) == 1 || 
               Math.abs(col - this.col) + Math.abs(fila - this.fila) == 1 || 
               puedeEnroque(col, fila);
    }
    
    /**
     * Verifica si el enroque es posible para el rey.
     * @param col La columna destino del rey en un enroque.
     * @param fila La fila destino del rey, que debe ser la misma que la fila inicial.
     * @return true si el enroque es posible; de lo contrario, false.
     */
    private boolean puedeEnroque(int col, int fila){
        // Verifica si la fila es la misma y si las condiciones para el enroque se cumplen.
        if(this.fila == fila){
            if(col == 6){ // Enroque corto
                Pieza torre = tablero.getPieza(7, fila);
                // Verifica que la torre esté en su posición, que sea su primer movimiento y que no haya piezas entre el rey y la torre.
                if(torre != null && torre.esPrimerMovimiento && esPrimerMovimiento){
                    return tablero.getPieza(5, fila) == null &&
                           tablero.getPieza(6, fila) == null &&
                           !tablero.comprobadorJaque.reyJaque(new Movimiento(tablero, this, 5, fila));
                }
            } else if(col == 2){ // Enroque largo
                Pieza torre = tablero.getPieza(0, fila);
                // Similar al enroque corto, pero verificando una casilla adicional.
                if(torre != null && torre.esPrimerMovimiento && esPrimerMovimiento){
                    return tablero.getPieza(3, fila) == null &&
                           tablero.getPieza(2, fila) == null &&
                           tablero.getPieza(1, fila) == null &&
                           !tablero.comprobadorJaque.reyJaque(new Movimiento(tablero, this, 3, fila));
                }
            } 
        }
        
        return false; // Si no se cumplen las condiciones, retorna false.
    }
    
}
