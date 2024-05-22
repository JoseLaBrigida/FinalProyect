/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movimientos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pieces.Pieza;
import tablero.Tablero;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class Input extends MouseAdapter{
    
    Tablero tablero; // Referencia al tablero de ajedrez para interactuar con las piezas.
    
    /**
     * Constructor que vincula el controlador de entrada con el tablero.
     * @param tablero El tablero de ajedrez al que esta entrada está asociada.
     */
    public Input(Tablero tablero){
        this.tablero = tablero; // Establece la referencia al tablero.
    }


    /**
     * Maneja el evento de presionar el ratón, seleccionando una pieza si hay una en la posición clickeada.
     * @param e Evento del ratón que contiene la información del clic.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        
        int col = e.getX() / tablero.tamanioTablero; // Calcula la columna en el tablero donde se hizo clic.
        int fila = e.getY() / tablero.tamanioTablero; // Calcula la fila en el tablero donde se hizo clic.
        
        Pieza piezaXY = tablero.getPieza(col, fila); // Obtiene la pieza en la posición clickeada, si existe.
        
        if(piezaXY != null){
            tablero.piezaSeleccionada = piezaXY; // Selecciona la pieza si hay alguna en esa posición.
        }
    }
    
    /**
     * Maneja el evento de arrastrar el ratón, moviendo la pieza seleccionada junto con el cursor.
     * @param e Evento del ratón que contiene la información del movimiento.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        
        if(tablero.piezaSeleccionada != null){
            // Ajusta la posición de la pieza seleccionada para que el centro de la pieza siga al cursor.
            tablero.piezaSeleccionada.xPos = e.getX() - tablero.tamanioTablero / 2; 
            tablero.piezaSeleccionada.yPos = e.getY() - tablero.tamanioTablero / 2;
            
            tablero.repaint(); // Redibuja el tablero para actualizar la posición de la pieza.
        }
        
    }

    /**
     * Maneja el evento de liberar el ratón, colocando la pieza seleccionada en la nueva posición si es un movimiento válido.
     * @param e Evento del ratón que contiene la información de la liberación.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        
        int col = e.getX() / tablero.tamanioTablero; // Calcula la columna donde se liberó el ratón.
        int fila = e.getY() / tablero.tamanioTablero; // Calcula la fila donde se liberó el ratón.
        
        if(tablero.piezaSeleccionada != null){
            Movimiento move = new Movimiento(tablero, tablero.piezaSeleccionada, col, fila); // Crea un movimiento potencial con la pieza seleccionada.
            
            if(tablero.esMovimientoValido(move)){
                tablero.hacerMovimiento(move); // Realiza el movimiento si es válido.
            }else{
                // Si el movimiento no es válido, devuelve la pieza a su posición original.
                tablero.piezaSeleccionada.xPos = tablero.piezaSeleccionada.col * tablero.tamanioTablero;
                tablero.piezaSeleccionada.yPos = tablero.piezaSeleccionada.fila * tablero.tamanioTablero;
            }
        }
        
        tablero.piezaSeleccionada = null; // Deselecciona la pieza.
        tablero.repaint(); // Redibuja el tablero para reflejar cualquier cambio.
    }
    
}
