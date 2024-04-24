/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import tablero.Tablero;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class Pieza {
    
    public int col, fila;
    public int xPos, yPos;
    
    public boolean esBlanco;
    public String nombre;
    public int valor;
    
    public boolean esPrimerMovimiento = true;
    
    
    BufferedImage plantillaImagen;
    {
        try {
            String path = "src/res/chessSprites.png";
            plantillaImagen = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected int escalaPlantilla = plantillaImagen.getWidth()/6;
    
    Image sprite;
    
    Tablero tablero;
    
    public Pieza(Tablero board){
        this.tablero = board;
    }
    
    public boolean esMovimientoValido(int col, int fila){
        return true;
    }
    
    public boolean movimientoColisionaConPieza(int col, int fila){
        return false;
    }
    
    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite, xPos, yPos, null);
    }
    
}
