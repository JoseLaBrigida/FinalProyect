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
    
    // Tamaño de cada casilla del tablero.
    public int tamanioTablero = 85; 
    
    // Número de columnas y filas en el tablero de ajedrez.
    int cols = 8;
    int filas = 8;
    
    // Lista para almacenar todas las piezas activas en el tablero.
    ArrayList<Pieza> listaPiezas = new ArrayList<>();
    
    // Pieza actualmente seleccionada por el jugador.
    public Pieza piezaSeleccionada;
    
    // Manejador de eventos de ratón para el tablero.
    Input input = new Input(this);
    
    // Posición que podría ser capturada "al paso".
    public int capturaAlPaso = -1;
    
    // Control de turno: true si es el turno de las blancas, false si es el turno de las negras.
    private boolean esTurnoBlanca = true;
    // Indicador de si el juego ha terminado.
    private boolean isGameOver = false;
    
    // Comprobador de condiciones de jaque para el juego.
    public ComprobadorJaque comprobadorJaque = new ComprobadorJaque(this);
    
    /**
     * Constructor que inicializa el tablero de ajedrez con dimensiones y eventos.
     */
    public Tablero(){
        // Establece las dimensiones preferidas del panel basadas en el número de columnas y tamaño de cada casilla.
        this.setPreferredSize(new Dimension(cols * tamanioTablero, filas * tamanioTablero));
        // Registra los listeners para el manejo de eventos de ratón.
        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        // Método para añadir todas las piezas al tablero.
        addPieces();
    }
    
    /**
     * Obtiene la pieza ubicada en la columna y fila especificadas.
     * @param col Columna donde se busca la pieza.
     * @param fila Fila donde se busca la pieza.
     * @return La pieza encontrada, o null si no hay ninguna en esa posición.
     */
    public Pieza getPieza(int col, int fila){
        for(Pieza pieza : listaPiezas){
            if(pieza.col == col && pieza.fila == fila){
                return pieza;
            }
        }
        return null;
    }
    
    /**
     * Verifica si el movimiento propuesto es válido.
     * @param move El movimiento que se desea realizar.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    public boolean esMovimientoValido(Movimiento move) {
        if(isGameOver){
            System.exit(0); // Termina la aplicación si el juego ha concluido.
            return false;
        }

        // Verifica si el turno es correcto.
        if(move.pieza.esBlanco != esTurnoBlanca){
            return false;
        }
        
        // Verifica si las piezas involucradas son del mismo equipo.
        if(mismoEquipo(move.pieza, move.captura)){
            return false;
        }
        
        // Verifica si el movimiento es válido según las reglas de la pieza.
        if(!move.pieza.esMovimientoValido(move.colNueva, move.filaNueva)){
            return false;
        }
        
        // Verifica si el movimiento colisiona con otras piezas en su trayectoria.
        if(move.pieza.movimientoColisionaConPieza(move.colNueva, move.filaNueva)){
            return false;
        }
        
        // Verifica si el movimiento pone al propio rey en jaque, lo cual es ilegal.
        return !comprobadorJaque.reyJaque(move);
    }
    
    /**
     * Comprueba si dos piezas pertenecen al mismo equipo.
     * @param p1 Primera pieza para la comparación.
     * @param p2 Segunda pieza para la comparación.
     * @return true si ambas piezas son del mismo color, false en caso contrario.
     */
    public boolean mismoEquipo(Pieza p1, Pieza p2){
        if(p1 == null || p2 == null){
            return false;
        }
        
        return p1.esBlanco == p2.esBlanco;
    }
    
    /**
     * Realiza el movimiento de una pieza en el tablero, actualizando su posición y gestionando las capturas.
     * @param move El movimiento a realizar.
     */
    public void hacerMovimiento(Movimiento move) {
        // Maneja movimientos especiales según el tipo de pieza.
        if (move.pieza.nombre.equals("Peon")) {
            moverPeon(move);
        } else if (move.pieza.nombre.equals("Rey")) {
            moverRey(move);
        }

        // Actualiza la posición de la pieza en el tablero.
        move.pieza.col = move.colNueva;
        move.pieza.fila = move.filaNueva;
        move.pieza.xPos = move.colNueva * tamanioTablero;
        move.pieza.yPos = move.filaNueva * tamanioTablero;

        // Marca que la pieza ha realizado su primer movimiento (importante para peones y reyes).
        move.pieza.esPrimerMovimiento = false;

        // Gestiona la captura de piezas.
        captura(move);
        
        // Alterna el turno entre blancas y negras.
        esTurnoBlanca = !esTurnoBlanca;
        
        // Verifica si el juego ha concluido.
        actualizarJuego();
    }
    
    /**
     * Gestiona el movimiento de los peones, incluyendo la captura al paso y la
     * promoción de peón.
     *
     * @param move El movimiento realizado por el peón.
     */
    private void moverPeon(Movimiento move) {
        int indiceColor = move.pieza.esBlanco ? 1 : -1; // Determina la dirección del movimiento del peón basada en su color.

        // Captura al paso.
        if (getCapturaAlPaso(move.colNueva, move.filaNueva) == capturaAlPaso) {
            move.captura = getPieza(move.colNueva, move.filaNueva + indiceColor);
        }

        // Configura la captura al paso si el peón se mueve dos posiciones desde su posición inicial.
        if (Math.abs(move.pieza.fila - move.filaNueva) == 2) {
            capturaAlPaso = getCapturaAlPaso(move.colNueva, move.filaNueva + indiceColor);
        } else {
            capturaAlPaso = -1;
        }

        // Promoción del peón.
        indiceColor = move.pieza.esBlanco ? 0 : 7;
        if (move.filaNueva == indiceColor) {
            peonPromovido(move);
        }

        // Actualiza la posición de la pieza.
        move.pieza.col = move.colNueva;
        move.pieza.fila = move.filaNueva;
        move.pieza.xPos = move.colNueva * tamanioTablero;
        move.pieza.yPos = move.filaNueva * tamanioTablero;
        move.pieza.esPrimerMovimiento = false;

        captura(move.captura);
    }

    /**
     * Gestiona el movimiento del rey, incluyendo el enroque.
     *
     * @param move El movimiento realizado por el rey.
     */
    private void moverRey(Movimiento move) {
        // Verifica si el movimiento es un enroque por la distancia movida.
        if (Math.abs(move.pieza.col - move.colNueva) == 2) {
            Pieza torre;
            // Enroque corto.
            if (move.pieza.col < move.colNueva) {
                torre = getPieza(7, move.pieza.fila);
                torre.col = 5;
            } else {
                // Enroque largo.
                torre = getPieza(0, move.pieza.fila);
                torre.col = 3;
            }
            torre.xPos = torre.col * tamanioTablero; // Actualiza la posición gráfica de la torre.
        }
    }

    /**
     * Promueve un peón a una reina cuando alcanza la última fila.
     *
     * @param move El movimiento que lleva a la promoción del peón.
     */
    private void peonPromovido(Movimiento move) {
        listaPiezas.add(new Reina(this, move.colNueva, move.filaNueva, move.pieza.esBlanco)); // Añade una reina en la posición de promoción.
        captura(move.pieza); // Elimina el peón promovido del juego.
    }

    /**
     * Elimina una pieza del tablero.
     *
     * @param move Contiene la pieza a ser capturada.
     */
    public void captura(Movimiento move) {
        listaPiezas.remove(move.captura); // Elimina la pieza capturada de la lista de piezas activas.
    }

    /**
     * Elimina una pieza específica del tablero.
     *
     * @param pieza La pieza a ser eliminada.
     */
    public void captura(Pieza pieza) {
        listaPiezas.remove(pieza); // Elimina la pieza del juego.
    }

    /**
     * Retorna un identificador único para la posición de captura al paso.
     *
     * @param col Columna de la casilla a verificar.
     * @param fila Fila de la casilla a verificar.
     * @return Un identificador único para la posición.
     */
    public int getCapturaAlPaso(int col, int fila) {
        return fila * filas + col; // Calcula un índice basado en la posición.
    }

    /**
     * Encuentra el rey de un color especificado.
     *
     * @param esBlanco true si se busca el rey blanco, false para el rey negro.
     * @return El rey encontrado o null si no se encuentra.
     */
    Pieza encontrarRey(boolean esBlanco) {
        for (Pieza pieza : listaPiezas) {
            if (esBlanco == pieza.esBlanco && pieza.nombre.equals("Rey")) {
                return pieza; // Retorna el rey del color especificado.
            }
        }
        return null;
    }

    /**
     * Añade todas las piezas iniciales al tablero para ambos jugadores.
     */
    public void addPieces() {

        //Aniadimos piezas negras
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

//      Aniadimos las piezas blancas
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

    /**
     * Verifica el estado del juego para determinar si hay jaque mate, ahogado o
     * si el juego debe continuar.
     */
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

        // Recorre todas las piezas y verifica sus tipos directamente.
        for (Pieza pieza : listaPiezas) {
            if (pieza.esBlanco == esBlanca) {
                contador++; // Contamos todas las piezas del color especificado.
                // Verifica si hay alguna pieza mayor que pueda seguir jugando.
                if (pieza.nombre.equals("Reina") || pieza.nombre.equals("Torre") || pieza.nombre.equals("Peon")) {
                    tienePiezasMayores = true;
                    break; // Salimos temprano porque ya encontramos una pieza que invalida la condición.
                }
            }
        }

        // Si hay piezas mayores o si el número de piezas es 3 o más, retorna false.
        return !tienePiezasMayores && contador < 3;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Pinta el tablero
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor((f + c) % 2 == 0 ? new Color(226, 200, 180) : new Color(155, 103, 50));
                g2d.fillRect(c * tamanioTablero, f * tamanioTablero, tamanioTablero, tamanioTablero);
            }
        }

        // Pinta en verde los movimientos disponibles
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

        // Pinta todas las piezas
        for (Pieza piece : listaPiezas) {
            piece.paint(g2d);
        }

        // Pinta el borde marrón oscuro alrededor del tablero
        int width = cols * tamanioTablero;
        int height = filas * tamanioTablero;
        g2d.setColor(new Color(101, 67, 33)); // Color marrón oscuro
        g2d.setStroke(new BasicStroke(20));
        g2d.drawRect(0, 0, width, height);

        // Pinta los números (1-8) y las letras (A-H) alrededor del tablero
        g2d.setColor(Color.white); // Color de los números y letras
        g2d.setFont(new Font("Arial", Font.BOLD, 10)); // Ajusta la fuente según sea necesario

        for (int i = 0; i < filas; i++) {
            // Números a la izquierda del tablero
            g2d.drawString(String.valueOf(filas - i), 0, i * tamanioTablero + tamanioTablero / 2);
        }

        for (int i = 0; i < cols; i++) {
            // Letras en la parte superior del tablero
            g2d.drawString(String.valueOf((char) ('A' + i)), i * tamanioTablero + tamanioTablero / 2, 10);
        }
    }


}
