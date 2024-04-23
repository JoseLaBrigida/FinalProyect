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
    
    Tablero tablero;
    
    public Input(Tablero tablero){
        this.tablero = tablero;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        int col = e.getX() / tablero.tamanioTablero;
        int fila = e.getY() / tablero.tamanioTablero;
        
        Pieza piezaXY = tablero.getPieza(col, fila);
        
        if(piezaXY != null){
            tablero.piezaSeleccionada = piezaXY;
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        
        if(tablero.piezaSeleccionada != null){
            tablero.piezaSeleccionada.xPos = e.getX() - tablero.tamanioTablero / 2; //Partido por 2, para que la pieza este centrada en el raton
            tablero.piezaSeleccionada.yPos = e.getY() - tablero.tamanioTablero / 2;
            
            tablero.repaint();
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        int col = e.getX() / tablero.tamanioTablero;
        int fila = e.getY() / tablero.tamanioTablero;
        
        if(tablero.piezaSeleccionada != null){
            Movimiento move = new Movimiento(tablero, tablero.piezaSeleccionada, col, fila);
            
            if(tablero.esMovimientoValido(move)){
                tablero.hacerMovimiento(move);
            }else{
                tablero.piezaSeleccionada.xPos = tablero.piezaSeleccionada.col * tablero.tamanioTablero;
                tablero.piezaSeleccionada.yPos = tablero.piezaSeleccionada.fila * tablero.tamanioTablero;
            }
        }
        
        tablero.piezaSeleccionada = null;
        tablero.repaint();
    }

    

    

}
