
package tablero;

import movimientos.Movimiento;
import pieces.Pieza;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class ComprobadorJaque {

    Tablero tablero; // Referencia al tablero de ajedrez para acceder a las piezas y sus posiciones.

    /**
     * Constructor que inicializa el comprobador con un tablero específico.
     * @param tablero El tablero de ajedrez sobre el cual se realizarán las comprobaciones.
     */
    public ComprobadorJaque(Tablero tablero) {
        this.tablero = tablero; // Establece el tablero.
    }

    /**
     * Evalúa si un movimiento resulta en un jaque al rey.
     * @param move El movimiento que se evalúa.
     * @return true si el movimiento pone al rey en jaque, false en caso contrario.
     */
    public boolean reyJaque(Movimiento move) {
        Pieza rey = tablero.encontrarRey(move.pieza.esBlanco); // Encuentra el rey basado en el color de la pieza que se mueve.
        assert rey != null; // Asegura que el rey existe en el tablero.
        
        int colRey = rey.col; // Columna actual del rey.
        int filaRey = rey.fila; // Fila actual del rey.
        
        // Ajusta la posición del rey si el movimiento es del propio rey.
        if(tablero.piezaSeleccionada != null && tablero.piezaSeleccionada.nombre.equals("Rey")){
            colRey = move.colNueva;
            filaRey = move.filaNueva;
        }
        
        // Verifica jaque desde varias direcciones y por diferentes tipos de piezas.
        return jaquePorTorre(move.colNueva, move.filaNueva, rey, colRey, filaRey, 0, 1) || // Arriba
                jaquePorTorre(move.colNueva, move.filaNueva, rey, colRey, filaRey, 1, 0) || // Derecha
                jaquePorTorre(move.colNueva, move.filaNueva, rey, colRey, filaRey, 0, -1) || // Abajo
                jaquePorTorre(move.colNueva, move.filaNueva, rey, colRey, filaRey, -1, 0) || // Izquierda
                jaquePorAlfil(move.colNueva, move.filaNueva, rey, colRey, filaRey, -1, -1) || // Arriba Izquierda
                jaquePorAlfil(move.colNueva, move.filaNueva, rey, colRey, filaRey, 1, -1) || // Arriba derecha
                jaquePorAlfil(move.colNueva, move.filaNueva, rey, colRey, filaRey, 1, 1) || // Abajo derecha
                jaquePorAlfil(move.colNueva, move.filaNueva, rey, colRey, filaRey, -1, 1) || // Abajo izquierda
                jaquePorCaballo(move.colNueva, move.filaNueva, rey, colRey, filaRey) ||
                jaquePorPeon(move.colNueva, move.filaNueva, rey, colRey, filaRey) ||
                jaquePorRey(rey, colRey, filaRey);
    }
    
 
        /**
     * Verifica si un movimiento de tipo torre o reina puede poner en jaque al rey.
     * @param col Columna desde donde se evalúa el jaque.
     * @param fila Fila desde donde se evalúa el jaque.
     * @param rey La pieza del rey que se está comprobando.
     * @param colRey Columna actual del rey.
     * @param filaRey Fila actual del rey.
     * @param valorCol Dirección horizontal del chequeo (1, -1, o 0).
     * @param valorFila Dirección vertical del chequeo (1, -1, o 0).
     * @return true si el rey está en jaque desde la dirección especificada, false en caso contrario.
     */
    private boolean jaquePorTorre(int col, int fila, Pieza rey, int colRey, int filaRey, int valorCol, int valorFila){
        for(int i = 1; i < 8; i++){
            if(colRey + (i*valorCol) == col && filaRey + (i*valorFila) == fila){
                break; // Sale del bucle si alcanza la posición del movimiento evaluado.
            }
            
            Pieza pieza = tablero.getPieza(colRey + (i * valorCol), filaRey + (i * valorFila));
            if(pieza != null && pieza != tablero.piezaSeleccionada){
                if(!tablero.mismoEquipo(pieza, rey) && (pieza.nombre.equals("Torre") || pieza.nombre.equals("Reina"))){
                    return true; // Retorna true si una torre o reina enemiga está en la línea de ataque al rey.
                }
                
                break; // Sale del bucle si encuentra una pieza que bloquea el camino.
            }
        }
        
        return false;
    }
    
    
    /**
     * Verifica si un movimiento de tipo alfil o reina puede poner en jaque al rey.
     * @param col Columna desde donde se evalúa el jaque.
     * @param fila Fila desde donde se evalúa el jaque.
     * @param rey La pieza del rey que se está comprobando.
     * @param colRey Columna actual del rey.
     * @param filaRey Fila actual del rey.
     * @param valorCol Dirección horizontal del chequeo (1, -1).
     * @param valorFila Dirección vertical del chequeo (1, -1).
     * @return true si el rey está en jaque desde la dirección diagonal especificada, false en caso contrario.
     */
    private boolean jaquePorAlfil(int col, int fila, Pieza rey, int colRey, int filaRey, int valorCol, int valorFila){
        for(int i = 1; i < 8; i++){
            if(colRey - (i*valorCol) == col && filaRey - (i*valorFila) == fila){
                break; // Sale del bucle si alcanza la posición del movimiento evaluado.
            }
            
            Pieza pieza = tablero.getPieza(colRey - (i * valorCol), filaRey - (i * valorFila));
            if(pieza != null && pieza != tablero.piezaSeleccionada){
                if(!tablero.mismoEquipo(pieza, rey) && (pieza.nombre.equals("Alfil") || pieza.nombre.equals("Reina"))){
                    return true; // Retorna true si un alfil o reina enemiga está en la diagonal de ataque al rey.
                }
                
                break; // Sale del bucle si encuentra una pieza que bloquea el camino.
            }
            
        }
        
        return false;
    }
   

    
    /**
     * Verifica si un caballo puede poner en jaque al rey.
     *
     * @param col Columna destino del movimiento que se está evaluando.
     * @param fila Fila destino del movimiento que se está evaluando.
     * @param rey La pieza del rey que se está comprobando.
     * @param colRey Columna actual del rey.
     * @param filaRey Fila actual del rey.
     * @return true si un caballo puede atacar al rey desde alguna de las
     * posiciones de movimiento en "L", false en caso contrario.
     */
    private boolean jaquePorCaballo(int col, int fila, Pieza rey, int colRey, int filaRey) {
        return comprobarCaballo(tablero.getPieza(colRey - 1, filaRey - 2), rey, col, fila)
                || comprobarCaballo(tablero.getPieza(colRey + 1, filaRey - 2), rey, col, fila)
                || comprobarCaballo(tablero.getPieza(colRey + 2, filaRey - 1), rey, col, fila)
                || comprobarCaballo(tablero.getPieza(colRey + 2, filaRey + 1), rey, col, fila)
                || comprobarCaballo(tablero.getPieza(colRey + 1, filaRey + 2), rey, col, fila)
                || comprobarCaballo(tablero.getPieza(colRey - 1, filaRey + 2), rey, col, fila)
                || comprobarCaballo(tablero.getPieza(colRey - 2, filaRey + 1), rey, col, fila)
                || comprobarCaballo(tablero.getPieza(colRey - 2, filaRey - 1), rey, col, fila);
    }

    
    /**
     * Comprueba si la pieza dada es un caballo que puede atacar al rey.
     *
     * @param p La pieza a comprobar.
     * @param rey La pieza del rey que se está comprobando.
     * @param col Columna destino del movimiento que se está evaluando.
     * @param fila Fila destino del movimiento que se está evaluando.
     * @return true si la pieza es un caballo del equipo contrario que no está
     * en la posición destino, false en caso contrario.
     */
    private boolean comprobarCaballo(Pieza p, Pieza rey, int col, int fila) {
        return p != null && !tablero.mismoEquipo(p, rey) && p.nombre.equals("Caballo") && !(p.col == col && p.fila == fila);
    }

    
    /**
     * Evalúa si otro rey puede poner en jaque al rey actual.
     *
     * @param rey La pieza del rey que se está comprobando.
     * @param colRey Columna actual del rey.
     * @param filaRey Fila actual del rey.
     * @return true si otro rey está suficientemente cerca para poner en jaque
     * al rey, false en caso contrario.
     */
    private boolean jaquePorRey(Pieza rey, int colRey, int filaRey) {
        return comprobarRey(tablero.getPieza(colRey - 1, filaRey - 1), rey)
                || comprobarRey(tablero.getPieza(colRey + 1, filaRey - 1), rey)
                || comprobarRey(tablero.getPieza(colRey, filaRey - 1), rey)
                || comprobarRey(tablero.getPieza(colRey - 1, filaRey), rey)
                || comprobarRey(tablero.getPieza(colRey + 1, filaRey), rey)
                || comprobarRey(tablero.getPieza(colRey - 1, filaRey + 1), rey)
                || comprobarRey(tablero.getPieza(colRey + 1, filaRey + 1), rey)
                || comprobarRey(tablero.getPieza(colRey, filaRey + 1), rey);
    }

    
    /**
     * Comprueba si la pieza dada es un rey que puede atacar al rey comprobado.
     *
     * @param p La pieza a comprobar.
     * @param rey La pieza del rey que se está comprobando.
     * @return true si la pieza es un rey del equipo contrario, false en caso
     * contrario.
     */
    private boolean comprobarRey(Pieza p, Pieza rey) {
        return p != null && !tablero.mismoEquipo(p, rey) && p.nombre.equals("Rey");
    }

    
    
    /**
     * Evalúa si un peón puede poner en jaque al rey.
     *
     * @param col Columna destino del movimiento que se está evaluando.
     * @param fila Fila destino del movimiento que se está evaluando.
     * @param rey La pieza del rey que se está comprobando.
     * @param colRey Columna actual del rey.
     * @param filaRey Fila actual del rey.
     * @return true si un peón puede atacar al rey desde su posición diagonal,
     * false en caso contrario.
     */
    private boolean jaquePorPeon(int col, int fila, Pieza rey, int colRey, int filaRey) {
        int valorColor = rey.esBlanco ? -1 : 1; // Determina la dirección del ataque basado en el color del peón.
        return comprobarPeon(tablero.getPieza(colRey + 1, filaRey + valorColor), rey, col, fila)
                || comprobarPeon(tablero.getPieza(colRey - 1, filaRey + valorColor), rey, col, fila);
    }


    /**
     * Comprueba si la pieza dada es un peón que puede atacar al rey.
     *
     * @param p La pieza a comprobar.
     * @param rey La pieza del rey que se está comprobando.
     * @param col Columna destino del movimiento que se está evaluando.
     * @param fila Fila destino del movimiento que se está evaluando.
     * @return true si la pieza es un peón del equipo contrario que no está en
     * la posición destino, false en caso contrario.
     */
    private boolean comprobarPeon(Pieza p, Pieza rey, int col, int fila) {
        return p != null && !tablero.mismoEquipo(p, rey) && p.nombre.equals("Peon") && !(p.col == col && p.fila == fila);
    }

    
    /**
     * Verifica jaque mate evaluando si el rey tiene movimientos legales que lo liberen del jaque.
     * @param rey La pieza del rey que se está comprobando.
     * @return true si el rey está en jaque mate, false si tiene al menos un movimiento legal para escapar.
     */
    public boolean esJaqueMate(Pieza rey) {
        for (Pieza pieza : tablero.listaPiezas) {
            if (tablero.mismoEquipo(pieza, rey)) {
                tablero.piezaSeleccionada = pieza == rey ? rey : null;
                for (int fila = 0; fila < tablero.filas; fila++) {
                    for (int col = 0; col < tablero.cols; col++) {
                        Movimiento move = new Movimiento(tablero, pieza, col, fila);
                        if (tablero.esMovimientoValido(move)) {
                            return false; // Retorna false si hay al menos un movimiento válido que no deja al rey en jaque.
                        }
                    }
                }
            }

        }

        return true; // Retorna true si no hay movimientos legales que eviten el jaque, indicando jaque mate.
    }

}