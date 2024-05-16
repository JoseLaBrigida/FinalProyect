/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import menus.InicioSesion;
import tablero.Tablero;

/**
 *
 * @author Jose Fernandez Cobo
 */
//public class Main {
//    public static void main(String[] args){
//        
//        //Creamos JFrame y asignamos sus atributos
//        JFrame frame = new JFrame();
//        frame.getContentPane().setBackground(Color.black);
//        frame.setLayout(new GridBagLayout());
//        frame.setMinimumSize(new Dimension(1000, 1000));
//        frame.setLocationRelativeTo(null);
//        
//        //Añadimos el tablero al frame
//        Tablero tablero = new Tablero();
//        frame.add(tablero);
//        
//        //Lo ponemos visible
//        frame.setVisible(true);
//        
//        
//    }
//    
//}



public class Main {
    public static void main(String[] args){
        
        InicioSesion inicio = new InicioSesion();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        
        /*
        // Creamos JFrame y asignamos sus atributos
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        
        // Obtenemos el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Ajustamos el tamaño del frame al tamaño de la pantalla
        frame.setSize(screenSize);
        
        // Maximizar el frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Añadimos el tablero al frame
        Tablero tablero = new Tablero();
        frame.add(tablero);
        
        // Lo ponemos visible
        frame.setVisible(true);*/
    }
}
