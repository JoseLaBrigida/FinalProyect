/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
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
 * @author Jose Fernandez Cobo
 */
public class Tablero extends JPanel {
    
    public int tamanioTablero = 85;
    int cols = 8;
    int filas = 8;
    public static int tipo = 0;
    ArrayList<Pieza> listaPiezas = new ArrayList<>();
    public Pieza piezaSeleccionada;
    Input input = new Input(this);
    public int capturaAlPaso = -1;
    private boolean esTurnoBlanca = true;
    private boolean isGameOver = false;
    private boolean turnoJugador;
    public boolean jugadorEsBlanco = true;
    public ComprobadorJaque comprobadorJaque = new ComprobadorJaque(this);
    
    public Tablero(int tipo) {
        this.setPreferredSize(new Dimension(cols * tamanioTablero, filas * tamanioTablero));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.tipo = tipo;
        
        if(tipo != 0){
           elegirColorJugador();
        }
        
        addPieces();
        
        moverIA();
    }
    
    private void elegirColorJugador() {
        int eleccion = JOptionPane.showOptionDialog(this, 
            "Elige tu color", 
            "Selección de Color", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            new Object[] { "Blancas", "Negras" }, 
            "Blancas");
        jugadorEsBlanco = (eleccion == JOptionPane.YES_OPTION);
        
    }
    
    public void moverIA(){
        // Si la IA controla las piezas blancas, la IA hace el primer movimiento.
        if (!jugadorEsBlanco) {
            turnoJugador = false;
            realizarMovimientoIA();
            
        }else{
            turnoJugador = true;
        }
    }
    
    public Pieza getPieza(int col, int fila) {
        for(Pieza pieza : listaPiezas){
            if(pieza.col == col && pieza.fila == fila){
                return pieza;
            }
        }
        return null;
    }
    
    public boolean esMovimientoValido(Movimiento move) {
        if (isGameOver) {
            return false;
        }

        // Verifica que el movimiento esté dentro de los límites del tablero.
        if (move.colNueva < 0 || move.colNueva >= cols || move.filaNueva < 0 || move.filaNueva >= filas) {
            return false;
        }

        // Verifica que el movimiento corresponda al color del turno.
        if (move.pieza.esBlanco != esTurnoBlanca) {
            return false;
        }

        if(tipo != 0){
           // Verifica que el jugador mueva sus propias piezas.
            if (turnoJugador && move.pieza.esBlanco != jugadorEsBlanco) {
                return false;
            } 
        }
        

        if(tipo != 0){
            // Verifica que la IA mueva sus propias piezas.
            if (!turnoJugador && move.pieza.esBlanco == jugadorEsBlanco) {
                return false;
            }
        }
        

        if (mismoEquipo(move.pieza, move.captura)) {
            return false;
        }

        if (!move.pieza.esMovimientoValido(move.colNueva, move.filaNueva)) {
            return false;
        }

        if (move.pieza.movimientoColisionaConPieza(move.colNueva, move.filaNueva)) {
            return false;
        }
        
        // Verifica que el movimiento no ponga al propio rey en jaque.
        if(comprobadorJaque.reyJaque(move)){
            return false;
        }
        
        
        return true;
    }



    
    public boolean mismoEquipo(Pieza p1, Pieza p2){
        if(p1 == null || p2 == null){
            return false;
        }
        return p1.esBlanco == p2.esBlanco;
    }
    
    public void hacerMovimiento(Movimiento move) {
        if (move.pieza.nombre.equals("Peon")) {
            moverPeon(move);
        } else if (move.pieza.nombre.equals("Rey")) {
            moverRey(move);
        }
        move.pieza.col = move.colNueva;
        move.pieza.fila = move.filaNueva;
        move.pieza.xPos = move.colNueva * tamanioTablero;
        move.pieza.yPos = move.filaNueva * tamanioTablero;
        
        move.pieza.esPrimerMovimiento = false;
        
        captura(move);
        
        esTurnoBlanca = !esTurnoBlanca;
        turnoJugador = !turnoJugador;
        
        actualizarJuego();
        
        if (tipo != 0) {
            if (!turnoJugador && !isGameOver) {
                realizarMovimientoIA();
            }
        }
        
        
    }

    
    private void realizarMovimientoIA() {
        List<Movimiento> movimientosValidos = new ArrayList<>();
        for (Pieza pieza : listaPiezas) {
            if (pieza.esBlanco != jugadorEsBlanco) {
                for (int col = 0; col < cols; col++) {
                    for (int fila = 0; fila < filas; fila++) {
                        Movimiento movimiento = new Movimiento(this, pieza, col, fila);
                        if (esMovimientoValido(movimiento)) {
                                movimientosValidos.add(movimiento);
                        }
                    }
                }
            }
        }

        if (!movimientosValidos.isEmpty()) {
            Random random = new Random();
            Movimiento movimientoIA = movimientosValidos.get(random.nextInt(movimientosValidos.size()));
            hacerMovimiento(movimientoIA);
        }

        movimientosValidos.clear();
    }

    private void moverPeon(Movimiento move) {
        int indiceColor = move.pieza.esBlanco ? 1 : -1;
        if (getCapturaAlPaso(move.colNueva, move.filaNueva) == capturaAlPaso) {
            move.captura = getPieza(move.colNueva, move.filaNueva + indiceColor);
        }
        if (Math.abs(move.pieza.fila - move.filaNueva) == 2) {
            capturaAlPaso = getCapturaAlPaso(move.colNueva, move.filaNueva + indiceColor);
        } else {
            capturaAlPaso = -1;
        }
        indiceColor = move.pieza.esBlanco ? 0 : 7;
        if (move.filaNueva == indiceColor) {
            peonPromovido(move);
        }
        move.pieza.col = move.colNueva;
        move.pieza.fila = move.filaNueva;
        move.pieza.xPos = move.colNueva * tamanioTablero;
        move.pieza.yPos = move.filaNueva * tamanioTablero;
        move.pieza.esPrimerMovimiento = false;
        captura(move.captura);
    }

    private void moverRey(Movimiento move) {
        if (Math.abs(move.pieza.col - move.colNueva) == 2) {
            Pieza torre;
            if (move.pieza.col < move.colNueva) {
                torre = getPieza(7, move.pieza.fila);
                torre.col = 5;
            } else {
                torre = getPieza(0, move.pieza.fila);
                torre.col = 3;
            }
            torre.xPos = torre.col * tamanioTablero;
        }
    }

    private void peonPromovido(Movimiento move) {
        listaPiezas.add(new Reina(this, move.colNueva, move.filaNueva, move.pieza.esBlanco));
        captura(move.pieza);
    }

    public void captura(Movimiento move) {
        listaPiezas.remove(move.captura);
    }
    

    public void captura(Pieza pieza) {
        listaPiezas.remove(pieza);
    }

    public int getCapturaAlPaso(int col, int fila) {
        return fila * filas + col;
    }

    Pieza encontrarRey(boolean esBlanco) {
        for (Pieza pieza : listaPiezas) {
            if (esBlanco == pieza.esBlanco && pieza.nombre.equals("Rey")) {
                return pieza;
            }
        }
        return null;
    }

    public void addPieces() {
        listaPiezas.add(new Reina(this, 3, 0, false));
        listaPiezas.add(new Caballo(this, 1, 0, false));
        listaPiezas.add(new Caballo(this, 6, 0, false));
        listaPiezas.add(new Alfil(this, 2, 0, false));
        listaPiezas.add(new Alfil(this, 5, 0, false));
        listaPiezas.add(new Torre(this, 0, 0, false));
        listaPiezas.add(new Torre(this, 7, 0, false));
        listaPiezas.add(new King(this, 4, 0, false));

        for (int i = 0; i < 8; i++) {
            listaPiezas.add(new Peon(this, i, 1, false));
        }

        listaPiezas.add(new Caballo(this, 1, 7, true));
        listaPiezas.add(new Caballo(this, 6, 7, true));
        listaPiezas.add(new Alfil(this, 2, 7, true));
        listaPiezas.add(new Alfil(this, 5, 7, true));
        listaPiezas.add(new Torre(this, 0, 7, true));
        listaPiezas.add(new Torre(this, 7, 7, true));
        listaPiezas.add(new Reina(this, 3, 7, true));
        listaPiezas.add(new King(this, 4, 7, true));

        for (int i = 0; i < 8; i++) {
            listaPiezas.add(new Peon(this, i, 6, true));
        }
    }

    private void actualizarJuego() {
        Pieza rey = encontrarRey(esTurnoBlanca);
        if (comprobadorJaque.esJaqueMate(rey)) {
            if (comprobadorJaque.reyJaque(new Movimiento(this, rey, rey.col, rey.fila))) {
                System.out.println(esTurnoBlanca ? "Ganan negras!!" : "Ganan blancas!!");
            } else {
                System.out.println("Ahogado");
            }
            isGameOver = true;
        } else if (sinPiezas(true) && sinPiezas(false)) {
            System.out.println("Sin piezas para continuar!! EMPATE");
            isGameOver = true;
        }
    }

    private boolean sinPiezas(boolean esBlanca) {
        int contador = 0;
        boolean tienePiezasMayores = false;
        for (Pieza pieza : listaPiezas) {
            if (pieza.esBlanco == esBlanca) {
                contador++;
                if (pieza.nombre.equals("Reina") || pieza.nombre.equals("Torre") || pieza.nombre.equals("Peon")) {
                    tienePiezasMayores = true;
                    break;
                }
            }
        }
        return !tienePiezasMayores && contador < 3;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor((f + c) % 2 == 0 ? new Color(226, 200, 180) : new Color(155, 103, 50));
                g2d.fillRect(c * tamanioTablero, f * tamanioTablero, tamanioTablero, tamanioTablero);
            }
        }
        if (piezaSeleccionada != null) {
            for (int f = 0; f < filas; f++) {
                for (int c = 0; c < cols; c++) {
                    if (esMovimientoValido(new Movimiento(this, piezaSeleccionada, c, f))) {
                        g2d.setColor(new Color(68, 180, 57, 150));
                        g2d.fillRect(c * tamanioTablero, f * tamanioTablero, tamanioTablero, tamanioTablero);
                    }
                }
            }
        }
        for (Pieza piece : listaPiezas) {
            piece.paint(g2d);
        }
        int ancho = cols * tamanioTablero;
        int alto = filas * tamanioTablero;
        g2d.setColor(new Color(101, 67, 33));
        g2d.setStroke(new BasicStroke(20));
        g2d.drawRect(0, 0, ancho, alto);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        for (int i = 0; i < filas; i++) {
            g2d.drawString(String.valueOf(filas - i), 0, i * tamanioTablero + tamanioTablero / 2);
        }
        for (int i = 0; i < cols; i++) {
            g2d.drawString(String.valueOf((char) ('A' + i)), i * tamanioTablero + tamanioTablero / 2, 10);
        }
        
    }
}
