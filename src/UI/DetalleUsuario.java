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
import javax.swing.ImageIcon;
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
    AdministrarUsuarios ventanaAnterior;
    
    /**
     * Creates new form DetalleUsuario
     */
    public DetalleUsuario(AdministrarUsuarios vAnterior,BL_Usuario usu, ArrayList<BL_Usuario> lista, int id, BL_ManejadorUsuario manejador) {
        usuario = usu;
        listaTotalUsuarios = lista;
        identificador = id;
        this.manejador = manejador;
        ventanaAnterior = vAnterior;
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
        btnPanelEliminar = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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
        jPanel4.add(labClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labTituloAccion.setFont(new java.awt.Font("Yu Gothic UI", 1, 28)); // NOI18N
        labTituloAccion.setForeground(new java.awt.Color(102, 102, 102));
        labTituloAccion.setText("Información del usuario");
        jPanel1.add(labTituloAccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 12, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Nombre completo: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 81, -1, -1));

        txtNombreCompleto.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtNombreCompleto.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreCompleto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 91, 216, 20));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 112, 216, 7));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Nombre de usuario: ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 164, -1, -1));

        txtNombreUsuario.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtNombreUsuario.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 171, 216, 20));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 195, 216, 7));

        txtContrasena.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtContrasena.setForeground(new java.awt.Color(51, 51, 51));
        txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 250, 210, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 271, 216, 10));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Contraseña:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 243, -1, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Administrador:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 302, -1, -1));

        checkAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        checkAdministrador.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        checkAdministrador.setForeground(new java.awt.Color(51, 51, 51));
        checkAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAdministradorActionPerformed(evt);
            }
        });
        jPanel1.add(checkAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 306, -1, -1));

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

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/update.png"))); // NOI18N
        jLabel14.setText("Modificar");
        jLabel14.setToolTipText("");
        btnPanelModificar.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 30));

        jPanel1.add(btnPanelModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 150, 50));

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

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/delete.png"))); // NOI18N
        jLabel12.setText("Eliminar");
        btnPanelEliminar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 30));

        jPanel1.add(btnPanelEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 150, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 450, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseClicked
        this.dispose();
        ventanaAnterior.setEnabled(true);
        ventanaAnterior.toFront();
        
    }//GEN-LAST:event_labCloseMouseClicked

    private void labCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labCloseMouseEntered

    private void checkAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAdministradorActionPerformed
        // TODO add your handling code here:
        if(!manejador.BuscarAdministradores(usuario.getIdUsuario()) && usuario.isAdministrador()){
            JOptionPane.showMessageDialog(null, "Debe existir al menos un Administrador en el Sistema","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
            checkAdministrador.setSelected(true);
        }
    }//GEN-LAST:event_checkAdministradorActionPerformed

    private void btnPanelModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelModificarMouseClicked
        if(txtNombreCompleto.getText().trim().equals("") || txtNombreUsuario.getText().trim().equals("") || txtContrasena.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos deben estar completos","Datos incompletos",JOptionPane.WARNING_MESSAGE,new ImageIcon("src/recursos/warning.png"));
        }else{            
        
            usuario.setIdUsuario(listaTotalUsuarios.get(identificador).getIdUsuario());
            if(JOptionPane.showConfirmDialog(null, "¿Desea actualizar los datos?", "Actualizar datos", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/recursos/pregunta.png")) == JOptionPane.YES_OPTION){
                usuario.setNombreCompleto(txtNombreCompleto.getText().trim());
                usuario.setNombreUsuario(txtNombreUsuario.getText().trim());
                usuario.setContrasena(txtContrasena.getText().trim());
                
                if(checkAdministrador.isSelected()){
                    usuario.setAdministrador(true);
                }else{
                    usuario.setAdministrador(false);
                }
                
                if(sesion.getIdUsuario() == usuario.getIdUsuario() && sesion.isAdmin() && !usuario.isAdministrador()){
                    if(JOptionPane.showConfirmDialog(null, "¿Desea dejar de ser Administrador?", "Confirmar modificación Administrador", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/recursos/pregunta.png")) == JOptionPane.YES_OPTION){
                        if(usuario.modificarUsuario()){
                            JOptionPane.showMessageDialog(null, "El nuevo usuario se modificó correctamente ","Modificación exitosa",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/recursos/exito.png"));
                            manejador.Modificar(identificador, usuario);
                            listaTotalUsuarios = manejador.ObtenerListaUsuarios();  
                            
                            sesion.setAdmin(false);
                            
                            this.dispose();
                            new Menu_Principal().setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(null, "Error al modificar el usuario seleccionado","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
                        }
                    }else{
                        checkAdministrador.setSelected(true);
                    }
            
                }else{
                
                    if(usuario.modificarUsuario()){
                        JOptionPane.showMessageDialog(null, "El nuevo usuario se modificó correctamente ","Modificación exitosa",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/recursos/exito.png"));
                        manejador.Modificar(identificador, usuario);
                        listaTotalUsuarios = manejador.ObtenerListaUsuarios();  

                        this.dispose();
                        ventanaAnterior = new AdministrarUsuarios();
                        ventanaAnterior.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al modificar el usuario seleccionado","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
                    }
                }
            }
        }
    }//GEN-LAST:event_btnPanelModificarMouseClicked

    private void btnPanelModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelModificarMouseEntered
        btnPanelModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelModificarMouseEntered

    private void btnPanelEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelEliminarMouseClicked
        if(txtNombreCompleto.getText().trim().equals("") || txtNombreUsuario.getText().trim().equals("") || txtContrasena.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos deben estar completos","Datos incompletos",JOptionPane.WARNING_MESSAGE,new ImageIcon("src/recursos/warning.png"));
        }else{            
            
            usuario.setIdUsuario(listaTotalUsuarios.get(identificador).getIdUsuario());
            if(JOptionPane.showConfirmDialog(null, "¿Desea eleminar este usuario?", "Eliminar usuario", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/recursos/pregunta.png")) == JOptionPane.YES_OPTION){
                if(usuario.getIdUsuario() != sesion.getIdUsuario()){
                    if(manejador.BuscarAdministradores(usuario.getIdUsuario())){
                        if(usuario.eliminarUsuario()){
                            manejador.Eliminar(identificador);
                            JOptionPane.showMessageDialog(null, "El usuario seleccionado se eliminó correctamente del sistema","Eliminación exitosa",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/recursos/exito.png"));

                            listaTotalUsuarios = manejador.ObtenerListaUsuarios();
                            this.dispose();
                            ventanaAnterior = new AdministrarUsuarios();
                            ventanaAnterior.setVisible(true);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El usuario que desea eliminar es un Administrador y no existen más administradores en el sistema","Error al eliminar",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
                    }
                }else{
                    if(manejador.BuscarAdministradores(usuario.getIdUsuario())){
                        if(JOptionPane.showConfirmDialog(null, "¿Desea eliminar su cuenta?\n" + "Se cerrará la sesión y no podra ingresar con sus credenciales en otra ocasión", "Confirmar eliminación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/recursos/pregunta.png")) == JOptionPane.YES_OPTION){
                            if(usuario.eliminarUsuario()){
                                manejador.Eliminar(identificador); 
                                JOptionPane.showMessageDialog(null, "El usuario seleccionado se eliminó correctamente del sistema","Eliminación exitosa",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/recursos/exito.png"));
                                
                                listaTotalUsuarios = manejador.ObtenerListaUsuarios();
                                        
                                this.dispose();
                                new Login().setVisible(true);
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El usuario que desea eliminar es un Administrador y no existen más administradores en el sistema","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
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
    private javax.swing.JLabel jLabel14;
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
