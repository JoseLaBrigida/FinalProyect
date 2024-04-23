/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import movimientos.Input;
import movimientos.Movimiento;
import pieces.Alfil;
import pieces.King;
import pieces.Caballo;
import pieces.Peon;
import pieces.Pieza;
import pieces.Reina;
import pieces.Torre;

/**
 *
 * @author User
 */
public class Tablero extends JPanel {
    
    public int  tamanioTablero = 85; //Tamanio de tablero
    
    int cols = 8; //Columnas
    int filas = 8; //Filas
    
    ArrayList<Pieza> listaPiezas = new ArrayList<>();
    
    public Pieza piezaSeleccionada;
    
    Input input = new Input(this);
    
    public Tablero(){
        this.setPreferredSize(new Dimension(cols * tamanioTablero, filas * tamanioTablero)); //Dimensiones del tablero
        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPieces();
    }
    
    public Pieza getPieza(int col, int fila){
        
        for(Pieza pieza : listaPiezas){
            if(pieza.col == col && pieza.fila == fila){
                return pieza;
            }
        }
        
        
        return null;
    }
    
    public boolean esMovimientoValido(Movimiento move) {
        //Si son del mismo equipo el movimiento no es valido
        if(mismoEquipo(move.pieza, move.captura)){
            return false;
        }
        
        
        return true;
    }
    
    public boolean mismoEquipo(Pieza p1, Pieza p2){
        if(p1 == null || p2 == null){
            return false;
        }
        
        //Retorna true si las piezas son del mismo equipo
        return p1.esBlanco == p2.esBlanco;
    }

    public void hacerMovimiento(Movimiento move) {
        
        move.pieza.col = move.colNueva;
        move.pieza.fila = move.filaNueva;
        move.pieza.xPos = move.colNueva * tamanioTablero;
        move.pieza.yPos = move.filaNueva * tamanioTablero;
        
        captura(move);
    }
    
    public void captura(Movimiento move){
        listaPiezas.remove(move.captura);
        
        
    }
    
    public void addPieces(){
        
        //Aniadimos piezas negras
        listaPiezas.add(new Caballo(this, 1, 0, false));
        listaPiezas.add(new Caballo(this, 6, 0, false));
        listaPiezas.add(new Alfil(this, 2, 0, false));
        listaPiezas.add(new Alfil(this, 5, 0, false));
        listaPiezas.add(new Torre(this, 0, 0, false));
        listaPiezas.add(new Torre(this, 7, 0, false));
        listaPiezas.add(new Reina(this, 3, 0, false));
        listaPiezas.add(new King(this, 4, 0, false));
        
        for(int i = 0; i < 8; i++){
            listaPiezas.add(new Peon(this, i, 1, false));
        }
        
        //Aniadimos las piezas blancas
        listaPiezas.add(new Caballo(this, 1, 7, true));
        listaPiezas.add(new Caballo(this, 6, 7, true));
        listaPiezas.add(new Alfil(this, 2,7, true));
        listaPiezas.add(new Alfil(this, 5, 7, true));
        listaPiezas.add(new Torre(this, 0, 7, true));
        listaPiezas.add(new Torre(this, 7, 7, true));
        listaPiezas.add(new Reina(this, 3, 7, true));
        listaPiezas.add(new King(this, 4, 7, true));
        
        for(int i = 0; i < 8; i++){
            listaPiezas.add(new Peon(this, i, 6, true));
        }
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        //Por cada fila/columna par, se pone el cuadrado de un color, si no es par, se pone de otro
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < cols; j++){
                g2d.setColor((i+j) % 2 ==0 ? new Color(226, 200, 180) : new Color(155, 103, 50));
                g2d.fillRect(j * tamanioTablero, i * tamanioTablero, tamanioTablero, tamanioTablero);
            }
        }
        
        for(Pieza piece : listaPiezas){
            piece.paint(g2d);
        }
    }

    
    
}
