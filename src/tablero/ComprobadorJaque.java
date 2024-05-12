
package tablero;

import movimientos.Movimiento;
import pieces.Pieza;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class ComprobadorJaque {

    Tablero tablero;

    public ComprobadorJaque(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean reyJaque(Movimiento move) {
        Pieza rey = tablero.encontrarRey(move.pieza.esBlanco);
        assert rey != null;
        
        int colRey = rey.col;
        int filaRey = rey.fila;
        
        if(tablero.piezaSeleccionada != null && tablero.piezaSeleccionada.nombre.equals("Rey")){
            colRey = move.colNueva;
            filaRey = move.filaNueva;
        }
        
        return jaquePorTorre(move.colNueva, move.filaNueva, rey, colRey, filaRey,0,1) || // Arriba
                jaquePorTorre(move.colNueva, move.filaNueva, rey, colRey, filaRey,1,0) || // Derecha
                jaquePorTorre(move.colNueva, move.filaNueva, rey, colRey, filaRey,0,-1) || // Abajo
                jaquePorTorre(move.colNueva, move.filaNueva, rey, colRey, filaRey,-1,0) || //Izquierda
                
                jaquePorAlfil(move.colNueva, move.filaNueva, rey, colRey, filaRey,-1,-1) || //Arriba Izquierda
                jaquePorAlfil(move.colNueva, move.filaNueva, rey, colRey, filaRey,1,-1) || // Arriba derecha
                jaquePorAlfil(move.colNueva, move.filaNueva, rey, colRey, filaRey,1,1) || // Abajo derecha
                jaquePorAlfil(move.colNueva, move.filaNueva, rey, colRey, filaRey,-1,1) || // Abajo izquierda
                
                jaquePorCaballo(move.colNueva, move.filaNueva, rey, colRey, filaRey) ||
                jaquePorPeon(move.colNueva, move.filaNueva, rey, colRey, filaRey) ||
                jaquePorRey(rey, colRey, filaRey);
    }
    
 
    private boolean jaquePorTorre(int col, int fila, Pieza rey, int colRey, int filaRey, int valorCol, int valorFila){
        for(int i = 1; i < 8; i++){
            if(colRey + (i*valorCol) == col && filaRey + (i*valorFila) == fila){
                break;
            }
            
            Pieza pieza = tablero.getPieza(colRey + (i * valorCol), filaRey + (i * valorFila));
            if(pieza != null && pieza != tablero.piezaSeleccionada){
                if(!tablero.mismoEquipo(pieza, rey) && (pieza.nombre.equals("Torre") || pieza.nombre.equals("Reina"))){
                    return true;
                }
                
                break;
            }
            
        }
        
        
        return false;
    }
    
    
    private boolean jaquePorAlfil(int col, int fila, Pieza rey, int colRey, int filaRey, int valorCol, int valorFila){
        for(int i = 1; i < 8; i++){
            if(colRey - (i*valorCol) == col && filaRey - (i*valorFila) == fila){
                break;
            }
            
            Pieza pieza = tablero.getPieza(colRey - (i * valorCol), filaRey - (i * valorFila));
            if(pieza != null && pieza != tablero.piezaSeleccionada){
                if(!tablero.mismoEquipo(pieza, rey) && (pieza.nombre.equals("Alfil") || pieza.nombre.equals("Reina"))){
                    return true;
                }
                
                break;
            }
            
        }
        
        
        return false;
    }
   

    
    private boolean jaquePorCaballo(int col, int fila, Pieza rey, int colRey, int filaRey){
        return comprobarCaballo(tablero.getPieza(colRey - 1, filaRey - 2), rey, col, fila) ||
                comprobarCaballo(tablero.getPieza(colRey + 1, filaRey - 2), rey, col, fila) ||
                comprobarCaballo(tablero.getPieza(colRey + 2, filaRey - 1), rey, col, fila) ||
                comprobarCaballo(tablero.getPieza(colRey + 2, filaRey + 1), rey, col, fila) ||
                comprobarCaballo(tablero.getPieza(colRey + 1, filaRey + 2), rey, col, fila) ||
                comprobarCaballo(tablero.getPieza(colRey - 1, filaRey + 2), rey, col, fila) ||
                comprobarCaballo(tablero.getPieza(colRey - 2, filaRey + 1), rey, col, fila) ||
                comprobarCaballo(tablero.getPieza(colRey - 2, filaRey - 1), rey, col, fila);
    }
    
    private boolean comprobarCaballo(Pieza p, Pieza rey, int col, int fila){

        return p != null && !tablero.mismoEquipo(p, rey) && p.nombre.equals("Caballo") && !(p.col == col && p.fila == fila);
    }
    
    private boolean jaquePorRey(Pieza rey, int colRey, int filaRey){
        return comprobarRey(tablero.getPieza(colRey - 1, filaRey - 1), rey) ||
                comprobarRey(tablero.getPieza(colRey + 1, filaRey - 1), rey) ||
                comprobarRey(tablero.getPieza(colRey, filaRey - 1), rey) ||
                comprobarRey(tablero.getPieza(colRey - 1, filaRey), rey) ||
                comprobarRey(tablero.getPieza(colRey + 1, filaRey), rey) ||
                comprobarRey(tablero.getPieza(colRey - 1, filaRey + 1), rey) ||
                comprobarRey(tablero.getPieza(colRey + 1, filaRey +1), rey) ||
                comprobarRey(tablero.getPieza(colRey, filaRey + 1), rey);
    }
    
    private boolean comprobarRey(Pieza p, Pieza rey){
        return p != null && !tablero.mismoEquipo(p, rey) && p.nombre.equals("Rey");
    }
    
    
    private boolean jaquePorPeon(int col, int fila, Pieza rey, int colRey, int filaRey){
        int valorColor = rey.esBlanco ? -1 : 1;
        
        return comprobarPeon(tablero.getPieza(colRey + 1, filaRey + valorColor), rey, col, fila) ||
                comprobarPeon(tablero.getPieza(colRey - 1, filaRey + valorColor), rey, col, fila);
    }

    private boolean comprobarPeon(Pieza p, Pieza rey, int col, int fila){
       return p != null && !tablero.mismoEquipo(p, rey) && p.nombre.equals("Peon") && !(p.col == col && p.fila == fila);
    }
    
    public boolean esJaqueMate(Pieza rey) {
        for (Pieza pieza : tablero.listaPiezas) {
            if (tablero.mismoEquipo(pieza, rey)) {
                tablero.piezaSeleccionada = pieza == rey ? rey : null;
                for (int fila = 0; fila < tablero.filas; fila++) {
                    for (int col = 0; col < tablero.cols; col++) {
                        Movimiento move = new Movimiento(tablero, pieza, col, fila);
                        if (tablero.esMovimientoValido(move)) {
                            return false;
                        }
                    }
                }
            }

        }

        return true;
    }

}