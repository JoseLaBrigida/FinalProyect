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
    
    public int  tileSize = 85; //Tamanio de tablero
    
    int cols = 8; //Columnas
    int rows = 8; //Filas
    
    ArrayList<Pieza> pieceList = new ArrayList<>();
    
    public Tablero(){
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize)); //Dimensiones del tablero
        addPieces();
    }
    
    public void addPieces(){
        
        //Aniadimos piezas negras
        pieceList.add(new Caballo(this, 1, 0, false));
        pieceList.add(new Caballo(this, 6, 0, false));
        pieceList.add(new Alfil(this, 2, 0, false));
        pieceList.add(new Alfil(this, 5, 0, false));
        pieceList.add(new Torre(this, 0, 0, false));
        pieceList.add(new Torre(this, 7, 0, false));
        pieceList.add(new Reina(this, 3, 0, false));
        pieceList.add(new King(this, 4, 0, false));
        
        for(int i = 0; i < 8; i++){
            pieceList.add(new Peon(this, i, 1, false));
        }
        
        //Aniadimos las piezas blancas
        pieceList.add(new Caballo(this, 1, 7, true));
        pieceList.add(new Caballo(this, 6, 7, true));
        pieceList.add(new Alfil(this, 2,7, true));
        pieceList.add(new Alfil(this, 5, 7, true));
        pieceList.add(new Torre(this, 0, 7, true));
        pieceList.add(new Torre(this, 7, 7, true));
        pieceList.add(new Reina(this, 3, 7, true));
        pieceList.add(new King(this, 4, 7, true));
        
        for(int i = 0; i < 8; i++){
            pieceList.add(new Peon(this, i, 6, true));
        }
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        //Por cada fila/columna par, se pone el cuadrado de un color, si no es par, se pone de otro
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                g2d.setColor((i+j) % 2 ==0 ? new Color(226, 200, 180) : new Color(155, 103, 50));
                g2d.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
        
        for(Pieza piece : pieceList){
            piece.paint(g2d);
        }
    }
    
}
