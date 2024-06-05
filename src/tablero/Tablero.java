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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import movimientos.Input;
import movimientos.Movimiento;
import pieces.Alfil;
import pieces.Rey;
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
    private Stack<EstadoMovimiento> historialMovimientos = new Stack<>();
    private static final int PROFUNDIDAD_MINIMAX = 100;
    private int movimientosIniciales = 0;
    private List<Movimiento> movimientosApertura;
    // Definir aperturas
    private final Map<String, List<Movimiento>> aperturas = new HashMap<>();
    
    private static final double[][] valoresPeon = {
        { 0, 0, 0, 0, 0, 0, 0, 0 },
        { 5, 5, 5, 5, 5, 5, 5, 5 },
        { 1, 1, 2, 3, 3, 2, 1, 1 },
        { 0.5, 0.5, 1, 2.5, 2.5, 1, 0.5, 0.5 },
        { 0, 0, 0, 2, 2, 0, 0, 0 },
        { 0.5, -0.5, -1, 0, 0, -1, -0.5, 0.5 },
        { 0.5, 1, 1, -2, -2, 1, 1, 0.5 },
        { 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    private static final double[][] valoresCaballo = {
        { -5, -4, -3, -3, -3, -3, -4, -5 },
        { -4, -2, 0, 0.5, 0.5, 0, -2, -4 },
        { -3, 0.5, 1, 1.5, 1.5, 1, 0.5, -3 },
        { -3, 0, 1.5, 2, 2, 1.5, 0, -3 },
        { -3, 0.5, 1.5, 2, 2, 1.5, 0.5, -3 },
        { -3, 0, 1, 1.5, 1.5, 1, 0, -3 },
        { -4, -2, 0, 0, 0, 0, -2, -4 },
        { -5, -4, -3, -3, -3, -3, -4, -5 }
    };

    private static final double[][] valoresAlfil = {
        { -2, -1, -1, -1, -1, -1, -1, -2 },
        { -1, 0, 0, 0, 0, 0, 0, -1 },
        { -1, 0, 0.5, 1, 1, 0.5, 0, -1 },
        { -1, 0.5, 0.5, 1, 1, 0.5, 0.5, -1 },
        { -1, 0, 1, 1, 1, 1, 0, -1 },
        { -1, 1, 1, 1, 1, 1, 1, -1 },
        { -1, 0.5, 0, 0, 0, 0, 0.5, -1 },
        { -2, -1, -1, -1, -1, -1, -1, -2 }
    };

    private static final double[][] valoresTorre = {
        { 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0.5, 1, 1, 1, 1, 1, 1, 0.5 },
        { -0.5, 0, 0, 0, 0, 0, 0, -0.5 },
        { -0.5, 0, 0, 0, 0, 0, 0, -0.5 },
        { -0.5, 0, 0, 0, 0, 0, 0, -0.5 },
        { -0.5, 0, 0, 0, 0, 0, 0, -0.5 },
        { -0.5, 0, 0, 0, 0, 0, 0, -0.5 },
        { 0, 0, 0, 0.5, 0.5, 0, 0, 0 }
    };

    private static final double[][] valoresReina = {
        { -2, -1, -1, -0.5, -0.5, -1, -1, -2 },
        { -1, 0, 0, 0, 0, 0, 0, -1 },
        { -1, 0, 0.5, 0.5, 0.5, 0.5, 0, -1 },
        { -0.5, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5 },
        { 0, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5 },
        { -1, 0.5, 0.5, 0.5, 0.5, 0.5, 0, -1 },
        { -1, 0, 0.5, 0, 0, 0, 0, -1 },
        { -2, -1, -1, -0.5, -0.5, -1, -1, -2 }
    };

    private static final double[][] valoresRey = {
        { -3, -4, -4, -5, -5, -4, -4, -3 },
        { -3, -4, -4, -5, -5, -4, -4, -3 },
        { -3, -4, -4, -5, -5, -4, -4, -3 },
        { -3, -4, -4, -5, -5, -4, -4, -3 },
        { -2, -3, -3, -4, -4, -3, -3, -2 },
        { -1, -2, -2, -2, -2, -2, -2, -1 },
        { 2, 2, 0, 0, 0, 0, 2, 2 },
        { 2, 3, 1, 0, 0, 1, 3, 2 }
    };
    
    private static final double[][] valoresGenerales = {
        { 5, 8,   9,  9,  9, 9,   8, 5 },
        { 8, 12, 14, 14, 14, 14, 12, 8 },
        { 9, 14, 16, 16, 16, 16, 14, 9 },
        { 9, 14, 16, 16, 16, 16, 14, 9 },
        { 9, 14, 16, 16, 16, 16, 14, 9 },
        { 9, 14, 16, 16, 16, 16, 14, 9 },
        { 8, 12, 14, 14, 14, 14, 12, 8 },
        { 5, 8,   9,  9,  9,  9,  8, 5 }
    };
    
    public Tablero(int tipo) {
        this.setPreferredSize(new Dimension(cols * tamanioTablero, filas * tamanioTablero));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.tipo = tipo;
        ;
        
        if(tipo != 0){
           elegirColorJugador();
        }
        
        addPieces();
        definirAperturas();
        moverIA();
    }
    
    private void definirAperturas() {
        // Aperturas para Blancas
        List<Movimiento> aperturaEspanola = new ArrayList<>();
        aperturaEspanola.add(new Movimiento(this, getPieza(4, 6), 4, 4)); // e4
        aperturaEspanola.add(new Movimiento(this, getPieza(1, 7), 2, 5)); // Nf3
        aperturaEspanola.add(new Movimiento(this, getPieza(3, 7), 3, 4)); // Bc4
        aperturaEspanola.add(new Movimiento(this, getPieza(5, 7), 6, 5)); // Nc3

        List<Movimiento> aperturaItaliana = new ArrayList<>();
        aperturaItaliana.add(new Movimiento(this, getPieza(4, 6), 4, 4)); // e4
        aperturaItaliana.add(new Movimiento(this, getPieza(2, 7), 2, 5)); // Nf3
        aperturaItaliana.add(new Movimiento(this, getPieza(3, 7), 2, 5)); // Bc4

        List<Movimiento> gambitoDama = new ArrayList<>();
        gambitoDama.add(new Movimiento(this, getPieza(3, 6), 3, 4)); // d4
        gambitoDama.add(new Movimiento(this, getPieza(2, 7), 2, 4)); // c4

        aperturas.put("Apertura Española", aperturaEspanola);
        aperturas.put("Apertura Italiana", aperturaItaliana);
        aperturas.put("Gambito de Dama", gambitoDama);

        // Aperturas para Negras
        List<Movimiento> defensaSiciliana = new ArrayList<>();
        defensaSiciliana.add(new Movimiento(this, getPieza(4, 1), 4, 3)); // e5
        defensaSiciliana.add(new Movimiento(this, getPieza(6, 0), 5, 2)); // Nf6
        defensaSiciliana.add(new Movimiento(this, getPieza(3, 0), 5, 1)); // d6

        List<Movimiento> defensaFrancesa = new ArrayList<>();
        defensaFrancesa.add(new Movimiento(this, getPieza(4, 1), 4, 3)); // e5
        defensaFrancesa.add(new Movimiento(this, getPieza(2, 0), 2, 3)); // d5

        List<Movimiento> defensaCaroKann = new ArrayList<>();
        defensaCaroKann.add(new Movimiento(this, getPieza(4, 1), 4, 3)); // e5
        defensaCaroKann.add(new Movimiento(this, getPieza(6, 0), 5, 2)); // d5

        aperturas.put("Defensa Siciliana", defensaSiciliana);
        aperturas.put("Defensa Francesa", defensaFrancesa);
        aperturas.put("Defensa Caro-Kann", defensaCaroKann);
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
    
    
    private void moverIA() {
        // Si la IA controla las piezas blancas, la IA hace el primer movimiento.
        if (!jugadorEsBlanco) {
            turnoJugador = false;
            if (tipo == 1) {
                realizarMovimientoIAFacil();
            } else if (tipo == 2) {
                // Elegir apertura para negras
                movimientosApertura = elegirAperturaNegra();
                realizarMovimientoIANormal();
            }
        } else {
            turnoJugador = true;
            if (tipo == 2) {
                // Elegir apertura para blancas
                movimientosApertura = elegirAperturaBlanca();
            }
        }
    }

    private List<Movimiento> elegirAperturaBlanca() {
        // Elegir una apertura blanca al azar
        
        List<String> aperturasBlancas = Arrays.asList("Defensa Siciliana", "Defensa Francesa", "Defensa Caro-Kann");
        //List<String> aperturasBlancas = Arrays.asList("Apertura Española", "Apertura Italiana", "Gambito de Dama");
        String aperturaElegida = aperturasBlancas.get(new Random().nextInt(aperturasBlancas.size()));
        return aperturas.get(aperturaElegida);
    }

    private List<Movimiento> elegirAperturaNegra() {
        // Elegir una apertura negra al azar
        //List<String> aperturasNegras = Arrays.asList("Defensa Siciliana", "Defensa Francesa", "Defensa Caro-Kann");
        
        List<String> aperturasNegras = Arrays.asList("Apertura Española", "Apertura Italiana", "Gambito de Dama");
        String aperturaElegida = aperturasNegras.get(new Random().nextInt(aperturasNegras.size()));
        return aperturas.get(aperturaElegida);
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
        
        // Guarda el estado actual del movimiento
        guardarEstadoMovimiento(move);
        
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
                
                if(tipo == 1){
                   realizarMovimientoIAFacil(); 
                }else if(tipo == 2){
                    realizarMovimientoIANormal();
                }
                
                
            }
        }
        
        
    }
    
    private void realizarMovimientoIAFacil() {
        List<Movimiento> movimientosValidos = new ArrayList<>();
        for (Pieza pieza : listaPiezas) {
            if (pieza.esBlanco != jugadorEsBlanco) {
                for (int col = 0; col < cols; col++) {
                    for (int fila = 0; fila < filas; fila++) {
                        Movimiento movimiento = new Movimiento(this, pieza, col, fila);
                        piezaSeleccionada = pieza;
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
    
    
    
    private void realizarMovimientoIANormal() {
        if (movimientosIniciales < 4 && movimientosApertura != null && movimientosApertura.size() > movimientosIniciales) {
            Movimiento movimientoApertura = movimientosApertura.get(movimientosIniciales);
            if (esMovimientoValido(movimientoApertura)) {
                hacerMovimiento(movimientoApertura);
                movimientosIniciales++;
                return;
            }
        }
        
        Movimiento mejorMovimiento = obtenerMejorMovimientoMinimax(PROFUNDIDAD_MINIMAX);
        if (mejorMovimiento != null) {
            hacerMovimiento(mejorMovimiento);
            
        }
    }


    
    private List<Movimiento> obtenerMovimientosValidos(boolean esBlanco) {
        List<Movimiento> movimientosValidos = new ArrayList<>();
        for (Pieza pieza : listaPiezas) {
            if (pieza.esBlanco == esBlanco) {
                for (int col = 0; col < cols; col++) {
                    for (int fila = 0; fila < filas; fila++) {
                        Movimiento movimiento = new Movimiento(this, pieza, col, fila);
                        piezaSeleccionada = pieza;
                        if (esMovimientoValido(movimiento)) {
                            movimientosValidos.add(movimiento);
                        }
                    }
                }
            }
        }
        return movimientosValidos;
    }

    private Movimiento obtenerMejorMovimientoMinimax(int profundidad) {
    List<Movimiento> movimientosValidos = obtenerMovimientosValidos(!jugadorEsBlanco);
    Movimiento mejorMovimiento = null;
    double valorAlto = Double.NEGATIVE_INFINITY; // Asegurarse de iniciar con el menor valor posible
    double valorBajo = Double.POSITIVE_INFINITY;
    double valor;
    
    
    System.out.println(esTurnoBlanca + " pensando en profundidad: " + profundidad);

    for (Movimiento movimiento : movimientosValidos) {
        hacerMovimiento(movimiento);
        
        valor = jugadorEsBlanco ? 
                min(profundidad - 1) :
                max(profundidad - 1);
        
        if(jugadorEsBlanco && valor >= valorAlto){
            valorAlto = valor;
            mejorMovimiento = movimiento;
                    
            
        } else if(!jugadorEsBlanco && valor <= valorBajo){
            valorBajo = valor;
            mejorMovimiento = movimiento;
        }
        
        deshacerMovimiento();
    }

    return mejorMovimiento;
}
    
    private double minimax(int profundidad, boolean esMaximizador) {
        if (profundidad == 0 || isGameOver) {
            return evaluarTablero();  // Evalúa el tablero para determinar su valor heurístico
        }

        List<Movimiento> movimientosValidos = obtenerMovimientosValidos(esMaximizador ? jugadorEsBlanco : !jugadorEsBlanco);
        double evaluacion;

        if (esMaximizador) {
            evaluacion = Double.NEGATIVE_INFINITY;  // Inicializa para la maximización
            for (Movimiento movimiento : movimientosValidos) {
                hacerMovimiento(movimiento);  // Realiza el movimiento en el tablero
                evaluacion = Math.max(evaluacion, minimax(profundidad - 1, false));  // Llama recursivamente como minimizador
                deshacerMovimiento();  // Restaura el tablero a su estado anterior
            }
            return evaluacion;  // Retorna el mejor valor evaluado
        } else {
            evaluacion = Double.POSITIVE_INFINITY;  // Inicializa para la minimización
            for (Movimiento movimiento : movimientosValidos) {
                hacerMovimiento(movimiento);  // Realiza el movimiento en el tablero
                evaluacion = Math.min(evaluacion, minimax(profundidad - 1, true));  // Llama recursivamente como maximizador
                deshacerMovimiento();  // Restaura el tablero a su estado anterior
            }
            return evaluacion;  // Retorna el mejor valor evaluado
        }
    }

    
    private double min(int profundidad) {
        if (profundidad == 0 || isGameOver) {
            return evaluarTablero();  // Evalúa el tablero para determinar su valor heurístico
        }

        List<Movimiento> movimientosValidos = obtenerMovimientosValidos(false);
        double evaluacionMin;

        evaluacionMin = Double.POSITIVE_INFINITY;  // Inicializa para la minimización
        for (Movimiento movimiento : movimientosValidos) {
            hacerMovimiento(movimiento);  // Realiza el movimiento en el tablero
            double evaluacionActual = max(profundidad - 1);  // Llama recursivamente como maximizador
            deshacerMovimiento();  // Restaura el tablero a su estado anterior
            
            if(evaluacionActual <= evaluacionMin){
                evaluacionMin = evaluacionActual;
            }
        }
        return evaluacionMin;  // Retorna el mejor valor evaluado

    }
    
    private double max(int profundidad) {
        if (profundidad == 0 || isGameOver) {
            return evaluarTablero();  // Evalúa el tablero para determinar su valor heurístico
        }

        List<Movimiento> movimientosValidos = obtenerMovimientosValidos(true);
        double evaluacionMax;

        evaluacionMax = Double.NEGATIVE_INFINITY;  // Inicializa para la minimización
        for (Movimiento movimiento : movimientosValidos) {
            hacerMovimiento(movimiento);  // Realiza el movimiento en el tablero
            double evaluacionActual = min(profundidad - 1);  // Llama recursivamente como maximizador
            deshacerMovimiento();  // Restaura el tablero a su estado anterior
            
            if(evaluacionActual >= evaluacionMax){
                evaluacionMax = evaluacionActual;
            }
        }
        return evaluacionMax;  // Retorna el mejor valor evaluado

    }


    private void deshacerMovimiento() {
        
        if (historialMovimientos.isEmpty()) {
            return;
        }

        EstadoMovimiento estadoAnterior = historialMovimientos.pop();

        estadoAnterior.piezaMovida.col = estadoAnterior.colOriginal;
        estadoAnterior.piezaMovida.fila = estadoAnterior.filaOriginal;
        estadoAnterior.piezaMovida.xPos = estadoAnterior.colOriginal * tamanioTablero;
        estadoAnterior.piezaMovida.yPos = estadoAnterior.filaOriginal * tamanioTablero;
        estadoAnterior.piezaMovida.esPrimerMovimiento = estadoAnterior.primerMovimiento;

        if (estadoAnterior.piezaCapturada != null) {
            listaPiezas.add(estadoAnterior.piezaCapturada);
        }

        esTurnoBlanca = !esTurnoBlanca;
        turnoJugador = !turnoJugador;
    }

    private void guardarEstadoMovimiento(Movimiento movimiento) {
        Pieza piezaCapturada = null;
        if (movimiento.captura != null) {
            piezaCapturada = movimiento.captura;
            listaPiezas.remove(piezaCapturada);
        }
        EstadoMovimiento estado = new EstadoMovimiento(
            movimiento.pieza,
            movimiento.pieza.col,
            movimiento.pieza.fila,
            movimiento.colNueva,
            movimiento.filaNueva,
            piezaCapturada,
            movimiento.pieza.esPrimerMovimiento
        );
        historialMovimientos.push(estado);
    }

    private double evaluarTablero() {
        double puntuacion = 0;
        for (Pieza pieza : listaPiezas) {
            double valorPieza = pieza.valor;
            double valorPosicional = obtenerValorPosicional(pieza);
            puntuacion += pieza.esBlanco ? valorPieza + valorPosicional : -valorPieza - valorPosicional;
        }
        return puntuacion;
    }
    
    // Obtener el valor posicional de una pieza en su posición actual
    private double obtenerValorPosicional(Pieza pieza) {
        int fila = pieza.fila;
        int col = pieza.col;

        switch (pieza.nombre) {
            case "Peon":
                return valoresPeon[fila][col];
            case "Caballo":
                return valoresCaballo[fila][col];
            case "Alfil":
                return valoresAlfil[fila][col];
            case "Torre":
                return valoresTorre[fila][col];
            case "Reina":
                return valoresReina[fila][col];
            case "Rey":
                return valoresRey[fila][col];
            default:
                return valoresGenerales[fila][col];
        }
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
        listaPiezas.add(new Reina(this, move.colNueva, move.filaNueva, move.pieza.esBlanco, 9));
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
        
        for (int i = 0; i < 8; i++) {
            listaPiezas.add(new Peon(this, i, 6, true, 100));
        }
        
        
        listaPiezas.add(new Reina(this, 3, 0, false, 900));
        listaPiezas.add(new Caballo(this, 1, 0, false, 300));
        listaPiezas.add(new Caballo(this, 6, 0, false, 300));
        listaPiezas.add(new Alfil(this, 2, 0, false, 300));
        listaPiezas.add(new Alfil(this, 5, 0, false, 300));
        listaPiezas.add(new Torre(this, 0, 0, false, 500));
        listaPiezas.add(new Torre(this, 7, 0, false, 500));
        listaPiezas.add(new Rey(this, 4, 0, false, 10000));

        for (int i = 0; i < 8; i++) {
            listaPiezas.add(new Peon(this, i, 1, false, 100));
        }

        listaPiezas.add(new Caballo(this, 1, 7, true, 300));
        listaPiezas.add(new Caballo(this, 6, 7, true, 300));
        listaPiezas.add(new Alfil(this, 2, 7, true, 300));
        listaPiezas.add(new Alfil(this, 5, 7, true, 300));
        listaPiezas.add(new Torre(this, 0, 7, true, 500));
        listaPiezas.add(new Torre(this, 7, 7, true, 500));
        listaPiezas.add(new Reina(this, 3, 7, true, 900));
        listaPiezas.add(new Rey(this, 4, 7, true, 10000));
        
        

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
