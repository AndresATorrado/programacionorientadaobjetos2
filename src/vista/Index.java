/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.DAOManager;
import dao.mySQL.MySQLDaoManager;
import java.sql.SQLException;

/**
 *
 * @author Jean
 */
public class Index extends javax.swing.JFrame {
    
    public Index() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnRegistrarClinica = new javax.swing.JButton();
        btnRegistrarPaciente = new javax.swing.JButton();
        btnRegistrarPersona = new javax.swing.JButton();
        btnRegistrarPersonal = new javax.swing.JButton();
        btnRegistrarPrueba = new javax.swing.JButton();
        btnEstadisticas = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnPacientes = new javax.swing.JButton();
        btnPersonas = new javax.swing.JButton();
        btnPersonal = new javax.swing.JButton();
        btnPruebas = new javax.swing.JButton();
        btnClinicas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnRegistrarClinica.setText("Registrar Clinica");

        btnRegistrarPaciente.setText("Registrar Paciente");
        btnRegistrarPaciente.setFocusable(false);
        btnRegistrarPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrarPaciente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnRegistrarPersona.setText("Registrar Persona");
        btnRegistrarPersona.setFocusable(false);
        btnRegistrarPersona.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrarPersona.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnRegistrarPersonal.setText("Registrar Personal");
        btnRegistrarPersonal.setFocusable(false);
        btnRegistrarPersonal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrarPersonal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistrarPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPersonalActionPerformed(evt);
            }
        });

        btnRegistrarPrueba.setText("Hacer Prueba");

        btnEstadisticas.setText("Estadísticas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegistrarPersonal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarClinica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEstadisticas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegistrarClinica)
                .addGap(11, 11, 11)
                .addComponent(btnRegistrarPaciente)
                .addGap(11, 11, 11)
                .addComponent(btnRegistrarPersona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrarPersonal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrarPrueba)
                .addGap(18, 18, 18)
                .addComponent(btnEstadisticas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jToolBar1.setRollover(true);

        btnPacientes.setText("Pacientes");
        btnPacientes.setFocusable(false);
        btnPacientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPacientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPacientes);

        btnPersonas.setText("Personas");
        btnPersonas.setFocusable(false);
        btnPersonas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPersonas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPersonas);

        btnPersonal.setText("Personal");
        btnPersonal.setFocusable(false);
        btnPersonal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPersonal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPersonal);

        btnPruebas.setText("Pruebas");
        btnPruebas.setFocusable(false);
        btnPruebas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPruebas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPruebas);

        btnClinicas.setText("Clinicas");
        btnClinicas.setFocusable(false);
        btnClinicas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClinicas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnClinicas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPersonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarPersonalActionPerformed

    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnClinicas;
    public javax.swing.JButton btnEstadisticas;
    public javax.swing.JButton btnPacientes;
    public javax.swing.JButton btnPersonal;
    public javax.swing.JButton btnPersonas;
    public javax.swing.JButton btnPruebas;
    public javax.swing.JButton btnRegistrarClinica;
    public javax.swing.JButton btnRegistrarPaciente;
    public javax.swing.JButton btnRegistrarPersona;
    public javax.swing.JButton btnRegistrarPersonal;
    public javax.swing.JButton btnRegistrarPrueba;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
