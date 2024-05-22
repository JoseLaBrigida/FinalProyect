/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tablero.Tablero;

/**
 *
 * @author User
 */
public class Dificultades extends javax.swing.JDialog {

    /**
     * Creates new form Dificultades
     */
    public Dificultades(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelUsuario = new javax.swing.JLabel();
        btnFacil = new javax.swing.JButton();
        btnNormal = new javax.swing.JButton();
        btnDificil = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(112, 145, 255));

        labelUsuario.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario.setText("Dificultad");

        btnFacil.setBackground(new java.awt.Color(60, 78, 234));
        btnFacil.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        btnFacil.setForeground(new java.awt.Color(255, 255, 255));
        btnFacil.setText("Facil");
        btnFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacilActionPerformed(evt);
            }
        });

        btnNormal.setBackground(new java.awt.Color(60, 78, 234));
        btnNormal.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        btnNormal.setForeground(new java.awt.Color(255, 255, 255));
        btnNormal.setText("Normal");
        btnNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNormalActionPerformed(evt);
            }
        });

        btnDificil.setBackground(new java.awt.Color(60, 78, 234));
        btnDificil.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        btnDificil.setForeground(new java.awt.Color(255, 255, 255));
        btnDificil.setText("Dificil");
        btnDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDificilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNormal, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(btnFacil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDificil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelUsuario)
                .addGap(37, 37, 37)
                .addComponent(btnFacil)
                .addGap(48, 48, 48)
                .addComponent(btnNormal)
                .addGap(51, 51, 51)
                .addComponent(btnDificil)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacilActionPerformed
        // Creamos JFrame y asignamos sus atributos
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(700, 800));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Añadimos el tablero al frame
        Tablero tablero = new Tablero(1);
        GridBagConstraints gbcTablero = new GridBagConstraints();
        gbcTablero.gridx = 0;
        gbcTablero.gridy = 0;
        gbcTablero.gridwidth = 3; // El tablero ocupa el ancho de tres columnas (donde estarán los botones)
        gbcTablero.fill = GridBagConstraints.BOTH;
        gbcTablero.weightx = 1.0;
        gbcTablero.weighty = 1.0;
        frame.add(tablero, gbcTablero);

        // Creamos los botones
        JButton botonTablas = new JButton("Tablas");
        JButton botonRendirse = new JButton("Rendirse");

        // Establecemos el tamaño de los botones
        Dimension buttonSize = new Dimension(150, 50);
        botonTablas.setPreferredSize(buttonSize);
        botonRendirse.setPreferredSize(buttonSize);

        // Añadimos ActionListener para el botón Ofrecer Tablas
        botonTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int confirmacion = JOptionPane.showConfirmDialog(frame, "¿Estás seguro que quieres ofrecer tablas?", "Confirmar Tablas", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Aquí puedes añadir la lógica para ofrecer tablas
                    JOptionPane.showMessageDialog(frame, "Has ofrecido tablas.");
                }
            }
        });

        // Añadimos ActionListener para el botón Rendirse
        botonRendirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int confirmacion = JOptionPane.showConfirmDialog(frame, "¿Estás seguro que quieres rendirte?", "Confirmar Rendición", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Aquí puedes añadir la lógica para rendirse
                    JOptionPane.showMessageDialog(frame, "Te has rendido.");
                    
                    frame.dispose();
                }
            }
        });

        // Añadimos los botones al frame con GridBagConstraints para separarlos
        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridy = 1;
        gbcBotones.insets = new Insets(10, 10, 10, 10); // Márgenes para separar los botones

        gbcBotones.gridx = 0;
        gbcBotones.weightx = 0.0;
        gbcBotones.fill = GridBagConstraints.NONE;

        gbcBotones.gridx = 1;
        gbcBotones.insets = new Insets(10, 10, 10, 0); // Márgenes para el botón Tablas
        frame.add(botonTablas, gbcBotones);
        

        gbcBotones.gridx = 2;
        gbcBotones.insets = new Insets(10, 350, 10, 0); // Márgenes para el botón Rendirse
        frame.add(botonRendirse, gbcBotones);
        
        // Lo ponemos visible
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnFacilActionPerformed

    private void btnNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNormalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNormalActionPerformed

    private void btnDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDificilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDificilActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDificil;
    private javax.swing.JButton btnFacil;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnInicio1;
    private javax.swing.JButton btnNormal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelUsuario;
    // End of variables declaration//GEN-END:variables
}