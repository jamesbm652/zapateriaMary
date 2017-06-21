/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.BL_Logueo;
import BL.BL_ManejadorUsuario;
import BL.BL_Usuario;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author oscal
 */
public class DetalleUsuario extends javax.swing.JFrame {
    BL_Usuario usuario;
    BL_Logueo sesion;
    BL_ManejadorUsuario manejador;
    ArrayList<BL_Usuario> listaTotalUsuarios;
    int identificador;
    
    /**
     * Creates new form DetalleUsuario
     */
    public DetalleUsuario(BL_Usuario usu, ArrayList<BL_Usuario> lista, int id, BL_ManejadorUsuario manejador) {
        usuario = usu;
        listaTotalUsuarios = lista;
        identificador = id;
        this.manejador = manejador;
        initComponents();
        
        mostrarDatosUsuario();
    }

    private void mostrarDatosUsuario(){
        txtNombreCompleto.setText(usuario.getNombreCompleto());
        txtNombreUsuario.setText(usuario.getNombreUsuario());
        txtContrasena.setText(usuario.getContrasena());
        checkAdministrador.setSelected(usuario.isAdministrador());
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        labClose = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labTituloAccion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        txtContrasena = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        checkAdministrador = new javax.swing.JCheckBox();
        btnPanelModificar = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnPanelEliminar = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 57, 66));
        jPanel4.setMinimumSize(new java.awt.Dimension(700, 74));
        jPanel4.setPreferredSize(new java.awt.Dimension(700, 74));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/close.png"))); // NOI18N
        labClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labCloseMouseEntered(evt);
            }
        });
        jPanel4.add(labClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        labTituloAccion.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        labTituloAccion.setForeground(new java.awt.Color(102, 102, 102));
        labTituloAccion.setText("Información del usuario:");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Nombre completo: ");

        txtNombreCompleto.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtNombreCompleto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Nombre de usuario: ");

        txtNombreUsuario.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtNombreUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtContrasena.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Contraseña:");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Administrador:");

        checkAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        checkAdministrador.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        checkAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAdministradorActionPerformed(evt);
            }
        });

        btnPanelModificar.setBackground(new java.awt.Color(33, 150, 243));
        btnPanelModificar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPanelModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPanelModificarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPanelModificarMouseEntered(evt);
            }
        });
        btnPanelModificar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/update.png"))); // NOI18N
        jLabel14.setToolTipText("");
        btnPanelModificar.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, -1));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Modificar");
        btnPanelModificar.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, -1));

        btnPanelEliminar.setBackground(new java.awt.Color(211, 47, 47));
        btnPanelEliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPanelEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPanelEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPanelEliminarMouseEntered(evt);
            }
        });
        btnPanelEliminar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/delete.png"))); // NOI18N
        btnPanelEliminar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, -1));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Eliminar");
        btnPanelEliminar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labTituloAccion)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkAdministrador)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                        .addComponent(jSeparator5)
                                        .addComponent(txtNombreUsuario)
                                        .addComponent(jSeparator4)
                                        .addComponent(txtContrasena)
                                        .addComponent(jSeparator2))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnPanelEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPanelModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labTituloAccion)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(checkAdministrador))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPanelModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPanelEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 390, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseClicked
        this.dispose();
        new AdministrarUsuarios().setVisible(true);
    }//GEN-LAST:event_labCloseMouseClicked

    private void labCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labCloseMouseEntered

    private void checkAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAdministradorActionPerformed
        // TODO add your handling code here:
        if(!manejador.BuscarAdministradores() && usuario.isAdministrador()){
            JOptionPane.showMessageDialog(null, "Error","Error",JOptionPane.ERROR_MESSAGE);
            checkAdministrador.setSelected(true);
        }
    }//GEN-LAST:event_checkAdministradorActionPerformed

    private void btnPanelModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelModificarMouseClicked
        if(txtNombreCompleto.getText().trim().equals("") || txtNombreUsuario.getText().trim().equals("") || txtContrasena.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos deben estar completos","Error",JOptionPane.ERROR_MESSAGE);
        }else{            
        
            usuario.setIdUsuario(listaTotalUsuarios.get(identificador).getIdUsuario());
            if(JOptionPane.showConfirmDialog(null, "¿Desea actualizar los datos?", "Actualizar datos", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                usuario.setNombreCompleto(txtNombreCompleto.getText().trim());
                usuario.setNombreUsuario(txtNombreUsuario.getText().trim());
                usuario.setContrasena(txtContrasena.getText().trim());
                
                if(checkAdministrador.isSelected()){
                    usuario.setAdministrador(true);
                }else{
                    usuario.setAdministrador(false);
                }
                
                if(usuario.modificarUsuario()){
                    JOptionPane.showMessageDialog(null, "El nuevo usuario se modificó correctamente ","Modificación exitosa",JOptionPane.INFORMATION_MESSAGE);
                    manejador.Modificar(identificador, usuario);
                    listaTotalUsuarios = manejador.ObtenerListaUsuarios();  
                    
                    this.dispose();
                    new AdministrarUsuarios().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al modificar el usuario seleccionado","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnPanelModificarMouseClicked

    private void btnPanelModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelModificarMouseEntered
        btnPanelModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelModificarMouseEntered

    private void btnPanelEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelEliminarMouseClicked
        if(txtNombreCompleto.getText().trim().equals("") || txtNombreUsuario.getText().trim().equals("") || txtContrasena.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos deben estar completos","Error",JOptionPane.ERROR_MESSAGE);
        }else{            
            
            usuario.setIdUsuario(listaTotalUsuarios.get(identificador).getIdUsuario());
            if(JOptionPane.showConfirmDialog(null, "¿Desea eleminar este usuario?", "Eliminar usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(usuario.getIdUsuario() != sesion.getIdUsuario()){
                    if(usuario.eliminarUsuario()){
                        manejador.Eliminar(identificador);
                        JOptionPane.showMessageDialog(null, "El usuario seleccionado se eliminó correctamente del sistema","Eliminación exitosa",JOptionPane.INFORMATION_MESSAGE);
                        
                        listaTotalUsuarios = manejador.ObtenerListaUsuarios();
                        this.dispose();
                        new AdministrarUsuarios().setVisible(true);
                    }
                }else{
                    if(manejador.BuscarAdministradores()){
                        if(JOptionPane.showConfirmDialog(null, "¿Desea eliminar su cuenta?\n" + "Se cerrará la sesión y no podra ingresar con sus credenciales en otra ocasión", "Confirmar eliminación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            if(usuario.eliminarUsuario()){
                                manejador.Eliminar(identificador); 
                                JOptionPane.showMessageDialog(null, "El usuario seleccionado se eliminó correctamente del sistema","Eliminación exitosa",JOptionPane.INFORMATION_MESSAGE);
                                
                                listaTotalUsuarios = manejador.ObtenerListaUsuarios();
                                        
                                this.dispose();
                                new Login().setVisible(true);
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El usuario que desea eliminar es un Administrador y no existen más administradores en el sistema","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnPanelEliminarMouseClicked

    private void btnPanelEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelEliminarMouseEntered
        btnPanelModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelEliminarMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnPanelEliminar;
    private javax.swing.JPanel btnPanelModificar;
    private javax.swing.JCheckBox checkAdministrador;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel labClose;
    private javax.swing.JLabel labTituloAccion;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
