/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import tablero.Tablero;

/**
 *
 * @author Jose Fernandez Cobo
 */
public class Main {
    public static void main(String[] args){
        
        //Creamos JFrame y asignamos sus atributos
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        
        //Añadimos el tablero al frame
        Tablero tablero = new Tablero();
        frame.add(tablero);
        
        //Lo ponemos visible
        frame.setVisible(true);
        
        
    }
    
}
