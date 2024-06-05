/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import pieces.Pieza;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class EstadoMovimiento {
    Pieza piezaMovida;
    int colOriginal, filaOriginal;
    int colNueva, filaNueva;
    Pieza piezaCapturada;
    boolean primerMovimiento;

    public EstadoMovimiento(Pieza piezaMovida, int colOriginal, int filaOriginal, int colNueva, int filaNueva, Pieza piezaCapturada, boolean primerMovimiento) {
        this.piezaMovida = piezaMovida;
        this.colOriginal = colOriginal;
        this.filaOriginal = filaOriginal;
        this.colNueva = colNueva;
        this.filaNueva = filaNueva;
        this.piezaCapturada = piezaCapturada;
        this.primerMovimiento = primerMovimiento;
    }
}
