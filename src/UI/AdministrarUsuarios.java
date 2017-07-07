
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.BL_ManejadorUsuario;
import BL.BL_Usuario;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author oscal
 */
public class AdministrarUsuarios extends javax.swing.JFrame {
    ArrayList<BL_Usuario> listaTotalUsuarios = new ArrayList<BL_Usuario>();
    BL_ManejadorUsuario manejador = new BL_ManejadorUsuario();
    DefaultTableModel modelo;
    /**
     * Creates new form AdministrarUsuarios
     */
    
    public AdministrarUsuarios() {
        initComponents();
        modelo = (DefaultTableModel) tablaUsuarios.getModel();
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);
        
        manejador.CargarUsuarios();
        listaTotalUsuarios = manejador.ObtenerListaUsuarios();
        cargarUsuariosEnTabla(listaTotalUsuarios);
        ocultarColumnaID();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 100), new java.awt.Dimension(0, 100), new java.awt.Dimension(32767, 100));
        jSeparator1 = new javax.swing.JSeparator();
        labMensajeAlUsuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JTextField();
        checkAdministrador = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        labTituloAccion = new javax.swing.JLabel();
        btnPanelAgregar = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento de Usuarios");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Buscar:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Usuarios registrados en el sistema:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        filler1.setBackground(new java.awt.Color(0, 0, 0));
        filler1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        jSeparator1.setMinimumSize(new java.awt.Dimension(50, 1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, -1, 380));

        labMensajeAlUsuario.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        labMensajeAlUsuario.setForeground(new java.awt.Color(102, 102, 102));
        labMensajeAlUsuario.setText(" ");
        getContentPane().add(labMensajeAlUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 323, 20));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("completo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Nombre ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("usuario:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, -1, -1));

        txtNombreCompleto.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtNombreCompleto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 160, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Contraseña:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, -1));

        txtContrasena.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 160, -1));

        checkAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        checkAdministrador.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        checkAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAdministradorActionPerformed(evt);
            }
        });
        jPanel1.add(checkAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Nombre de ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, -1, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 160, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 320, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, 160, 10));

        tablaUsuarios.setBackground(new java.awt.Color(244, 244, 244));
        tablaUsuarios.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        tablaUsuarios.setForeground(new java.awt.Color(102, 102, 102));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Nombre Usuario", "Contraseña", "Administrador", "HiddenID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);
        if (tablaUsuarios.getColumnModel().getColumnCount() > 0) {
            tablaUsuarios.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 463, 290));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 160, 10));

        txtNombreUsuario.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtNombreUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 160, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Administrador:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, -1, -1));

        labTituloAccion.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        labTituloAccion.setForeground(new java.awt.Color(102, 102, 102));
        labTituloAccion.setText("Información del usuario:");
        jPanel1.add(labTituloAccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        btnPanelAgregar.setBackground(new java.awt.Color(0, 93, 107));
        btnPanelAgregar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPanelAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPanelAgregarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPanelAgregarMouseEntered(evt);
            }
        });
        btnPanelAgregar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/plus.png"))); // NOI18N
        btnPanelAgregar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 20, 40));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Agregar");
        btnPanelAgregar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 70, 40));

        jPanel1.add(btnPanelAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 170, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 760, 440));

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
        jPanel4.add(labClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        filtro(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        if(evt.getClickCount() == 2){
            this.dispose();
            BL_Usuario usuario = new BL_Usuario();
            usuario.setNombreCompleto(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0).toString());
            usuario.setNombreUsuario(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 1).toString());
            usuario.setContrasena(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 2).toString());
            
            if(tablaUsuarios.getModel().getValueAt(tablaUsuarios.getSelectedRow(), 3).toString().equals("Si")){
                usuario.setAdministrador(true);
            }else{
                usuario.setAdministrador(false);
            }
            
            new DetalleUsuario(usuario,listaTotalUsuarios,Integer.parseInt(tablaUsuarios.getModel().getValueAt(tablaUsuarios.getSelectedRow(), 4).toString()),manejador).setVisible(true);
            
            
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void labCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseClicked
        this.dispose();
        new Menu_Principal().setVisible(true);
    }//GEN-LAST:event_labCloseMouseClicked

    private void labCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labCloseMouseEntered

    private void checkAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAdministradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkAdministradorActionPerformed

    private void btnPanelAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAgregarMouseClicked
        int identificador;
        if(txtNombreCompleto.getText().trim().equals("") || txtNombreUsuario.getText().trim().equals("") || txtContrasena.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            BL_Usuario usuario = new BL_Usuario(txtNombreCompleto.getText().trim(),txtNombreUsuario.getText().trim(),txtContrasena.getText().trim(),checkAdministrador.isSelected());
                if(usuario.agregarUsuario()){
                    JOptionPane.showMessageDialog(null, "El nuevo usuario se agregó correctamente en el sistema","Ingreso exitoso",JOptionPane.INFORMATION_MESSAGE);
                    manejador.Agregar(usuario);
                    txtNombreCompleto.setText("");
                    txtNombreUsuario.setText("");
                    txtContrasena.setText("");
                    checkAdministrador.setSelected(false);
                    listaTotalUsuarios = manejador.ObtenerListaUsuarios();
                    cargarUsuariosEnTabla(listaTotalUsuarios);
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe un usuario con el mismo nombre de usuario","Error",JOptionPane.ERROR_MESSAGE);
                }
        }

    }//GEN-LAST:event_btnPanelAgregarMouseClicked

    private void btnPanelAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAgregarMouseEntered
        btnPanelAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelAgregarMouseEntered

    private void cargarUsuariosEnTabla(ArrayList<BL_Usuario> listaParaMostrar) {

        limpiarTabla(modelo);
        Object[] fila = new Object[modelo.getColumnCount()];

        for (int i = 0; i < listaParaMostrar.size(); i++) {
            fila[0] = listaParaMostrar.get(i).getNombreCompleto();
            fila[1] = listaParaMostrar.get(i).getNombreUsuario();
            fila[2] = listaParaMostrar.get(i).getContrasena();
            if(listaParaMostrar.get(i).isAdministrador()){
                fila[3] = "Si";
            }else{
                fila[3] = "No";
            }
            fila[4] = i;

            modelo.addRow(fila);
        }
        listaTotalUsuarios = listaParaMostrar;
    }
    
    private void habilitarCampos(boolean valor){
        txtNombreCompleto.setEditable(valor);
        txtNombreUsuario.setEditable(valor);
        txtContrasena.setEditable(valor);
        checkAdministrador.setEnabled(valor);
    }

    private void limpiarTabla(DefaultTableModel modelo) {
        int filas = tablaUsuarios.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }
    
    private void ocultarColumnaID() {
        tablaUsuarios.getColumn("HiddenID").setMaxWidth(0);
        tablaUsuarios.getColumn("HiddenID").setMinWidth(0);
        tablaUsuarios.getColumn("HiddenID").setPreferredWidth(0);
        tablaUsuarios.getColumn("HiddenID").setWidth(0);
        tablaUsuarios.getColumn("HiddenID").setResizable(false);
    }
    
    private void filtro(String filtro) {
        TableRowSorter<DefaultTableModel> trsFiltro = new TableRowSorter<>(modelo);
        tablaUsuarios.setRowSorter(trsFiltro);
        trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + filtro));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnPanelAgregar;
    private javax.swing.JCheckBox checkAdministrador;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel labClose;
    private javax.swing.JLabel labMensajeAlUsuario;
    private javax.swing.JLabel labTituloAccion;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}