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
    
    // Variables para almacenar la posición de la pieza en términos de columnas y filas en el tablero.
    public int col, fila;
    // Variables para almacenar la posición de la pieza en píxeles, útil para la representación gráfica.
    public int xPos, yPos;
    
    // Indica si la pieza es blanca (true) o negra (false).
    public boolean esBlanco;
    // Nombre de la pieza (por ejemplo, "Rey", "Peon").
    public String nombre;
    // Valor numérico asociado a la pieza, utilizado en algunos sistemas de puntuación.
    public int valor;
    
    // Bandera para determinar si la pieza ha realizado su primer movimiento, relevante para piezas como el peón y el rey.
    public boolean esPrimerMovimiento = true;
    
    // Imagen que contiene los sprites de las piezas.
    BufferedImage plantillaImagen;
    // Bloque de inicialización para cargar la imagen desde un recurso local.
    {
        try {
            String path = "src/res/chessSprites.png"; // Ruta al archivo de sprites.
            plantillaImagen = ImageIO.read(new File(path)); // Carga la imagen.
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error si no se puede cargar la imagen.
        }
    }
    
    // Tamaño de cada sprite en la plantilla de imagen.
    protected int escalaPlantilla = plantillaImagen.getWidth()/6;
    
    // Imagen de la pieza específica extraída de la plantilla.
    Image sprite;
    
    // Referencia al tablero en el que se encuentra la pieza.
    Tablero tablero;
    
    /**
     * Constructor para la pieza.
     * @param board Referencia al tablero de ajedrez.
     */
    public Pieza(Tablero board){
        this.tablero = board; // Establece el tablero.
    }
    
    /**
     * Método para verificar si un movimiento es válido. Puede ser sobreescrito por clases derivadas.
     * @param col Columna destino.
     * @param fila Fila destino.
     * @return true si el movimiento es válido; de lo contrario, false.
     */
    public boolean esMovimientoValido(int col, int fila){
        return true; // Por defecto, cualquier movimiento es considerado válido (debe ser sobreescrito).
    }
    
    /**
     * Método para determinar si un movimiento resulta en una colisión con otra pieza.
     * @param col Columna destino.
     * @param fila Fila destino.
     * @return false por defecto, indicando que no hay colisión (debe ser sobreescrito).
     */
    public boolean movimientoColisionaConPieza(int col, int fila){
        return false; // Por defecto, no hay colisiones (debe ser sobreescrito).
    }
    
    /**
     * Método para pintar la pieza en el tablero usando gráficos 2D.
     * @param g2d Contexto gráfico en el que se dibuja la pieza.
     */
    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite, xPos, yPos, null); // Dibuja la imagen de la pieza en sus coordenadas.
    }
    
}

