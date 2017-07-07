/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
import BL.BL_Factura;
import BL.BL_ProductoFactura;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Joseph
 */
public class ConfirmarFacturacion extends javax.swing.JFrame {
    /**
     * Creates new form ConfirmarFacturacion
     */
    BL_Factura factura;
    Facturar uifactura;
    DefaultTableModel modeloDetalles;
    
    public ConfirmarFacturacion() {
        initComponents();
    }
    
    public ConfirmarFacturacion(BL_Factura fact, Facturar uifacturar) {
        initComponents();
        modeloDetalles = (DefaultTableModel) tablaDetalles.getModel();
        tablaDetalles.getTableHeader().setReorderingAllowed(false);
        txt_TipoFactura.setText(fact.getTipoFactura());
        txt_NombreCompleto.setText(fact.getCliente().getNombreCompleto());
        txt_Cedula.setText(fact.getCliente().getCedula());
        txt_Direccion.setText(fact.getCliente().getDireccion());
        for (int i = 0; i < fact.getCliente().getListaTelefonos().size(); i++) {
            if (i == 0) {
                txt_TelHabitacion.setText(fact.getCliente().getListaTelefonos().get(i).getTelefono());
            }else{
                txt_TelCelular.setText(fact.getCliente().getListaTelefonos().get(i).getTelefono());
            }
        }
        cargarProductosEnTablaDetalles(fact.getProductosFactura());
        factura = fact;
        uifactura = uifacturar;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        labClose = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        btnAceptar = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_Cedula = new javax.swing.JTextField();
        txt_NombreCompleto = new javax.swing.JTextField();
        txt_Direccion = new javax.swing.JTextField();
        txt_TelHabitacion = new javax.swing.JTextField();
        txt_TelCelular = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_TipoFactura = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_PrecioTotal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel6.setEnabled(false);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel4.add(labClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 10, -1, 30));

        jLabel8.setBackground(new java.awt.Color(196, 196, 196));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(237, 237, 237));
        jLabel8.setText("Informe de la venta.");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 220, 30));

        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 476, 50));

        tablaDetalles.setBackground(new java.awt.Color(232, 232, 232));
        tablaDetalles.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaDetalles.setForeground(new java.awt.Color(51, 51, 51));
        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción", "Cantidad", "Precio", "HiddenId", "HiddenIdOriginal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalles.setGridColor(new java.awt.Color(153, 153, 153));
        jScrollPane2.setViewportView(tablaDetalles);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 234, 460, 153));

        btnAceptar.setBackground(new java.awt.Color(0, 57, 66));
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAceptarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAceptarMouseEntered(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Confirmar");

        javax.swing.GroupLayout btnAceptarLayout = new javax.swing.GroupLayout(btnAceptar);
        btnAceptar.setLayout(btnAceptarLayout);
        btnAceptarLayout.setHorizontalGroup(
            btnAceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAceptarLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(25, 25, 25))
        );
        btnAceptarLayout.setVerticalGroup(
            btnAceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAceptarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, -1, 50));

        btnCancelar.setBackground(new java.awt.Color(0, 57, 66));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cancelar");

        javax.swing.GroupLayout btnCancelarLayout = new javax.swing.GroupLayout(btnCancelar);
        btnCancelar.setLayout(btnCancelarLayout);
        btnCancelarLayout.setHorizontalGroup(
            btnCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCancelarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btnCancelarLayout.setVerticalGroup(
            btnCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCancelarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        jLabel1.setText("Cédula: ");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 98, -1, -1));

        jLabel2.setText("Nombre Completo: ");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 129, -1, -1));

        jLabel3.setText("Dirección: ");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 165, -1, -1));

        jLabel4.setText("Teléfono cel: ");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, -1, 20));

        txt_Cedula.setEditable(false);
        txt_Cedula.setBackground(new java.awt.Color(255, 255, 255));
        txt_Cedula.setForeground(new java.awt.Color(153, 153, 153));
        txt_Cedula.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txt_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 120, -1));

        txt_NombreCompleto.setEditable(false);
        txt_NombreCompleto.setBackground(new java.awt.Color(255, 255, 255));
        txt_NombreCompleto.setForeground(new java.awt.Color(153, 153, 153));
        txt_NombreCompleto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txt_NombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 346, -1));

        txt_Direccion.setEditable(false);
        txt_Direccion.setBackground(new java.awt.Color(255, 255, 255));
        txt_Direccion.setForeground(new java.awt.Color(153, 153, 153));
        txt_Direccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txt_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 346, -1));

        txt_TelHabitacion.setEditable(false);
        txt_TelHabitacion.setBackground(new java.awt.Color(255, 255, 255));
        txt_TelHabitacion.setForeground(new java.awt.Color(153, 153, 153));
        txt_TelHabitacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txt_TelHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 120, -1));

        txt_TelCelular.setEditable(false);
        txt_TelCelular.setBackground(new java.awt.Color(255, 255, 255));
        txt_TelCelular.setForeground(new java.awt.Color(153, 153, 153));
        txt_TelCelular.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_TelCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TelCelularActionPerformed(evt);
            }
        });
        jPanel6.add(txt_TelCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 200, 120, -1));

        jLabel5.setText("Tipo de Factura: ");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 62, -1, -1));

        txt_TipoFactura.setEditable(false);
        txt_TipoFactura.setBackground(new java.awt.Color(255, 255, 255));
        txt_TipoFactura.setForeground(new java.awt.Color(153, 153, 153));
        txt_TipoFactura.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txt_TipoFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 120, -1));

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Total:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 401, -1, -1));

        txt_PrecioTotal.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal.setForeground(new java.awt.Color(255, 0, 0));
        txt_PrecioTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal.setText("₡ ");
        jPanel6.add(txt_PrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(341, 398, 130, 22));

        jLabel7.setText("Teléfono hab: ");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 199, -1, -1));
        jPanel6.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 120, -1));
        jPanel6.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 350, 10));
        jPanel6.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 120, 10));
        jPanel6.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 350, 10));
        jPanel6.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 120, 10));
        jPanel6.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 120, 10));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 510));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseClicked
        
    }//GEN-LAST:event_labCloseMouseClicked

    private void labCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseEntered

    }//GEN-LAST:event_labCloseMouseEntered

    private void txt_TelCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TelCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TelCelularActionPerformed

    private void btnAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseClicked
        // TODO add your handling code here:
        validarInserción(factura, uifactura);
    }//GEN-LAST:event_btnAceptarMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        // TODO add your handling code here:
        uifactura.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseEntered
        // TODO add your handling code here:
        btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnAceptarMouseEntered

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        // TODO add your handling code here:
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnCancelarMouseEntered

    /**
     * @param args the command line arguments
     */
    
    public void validarInserción(BL_Factura factura, Facturar uifacturar){
        
        if (factura.insertarFactura()) {
                    JOptionPane.showMessageDialog(this, "La factura se ha ingresado con éxito");
                    this.dispose();
                    uifacturar.dispose();
                    uifacturar = new Facturar();
                    uifacturar.setVisible(true);
                }
    }
    
    private void cargarProductosEnTablaDetalles(ArrayList<BL_ProductoFactura> listaParaMostrar) {

        Object[] fila = new Object[modeloDetalles.getColumnCount()];
        int totalPagar = 0;

        for (int i = 0; i < listaParaMostrar.size(); i++) {
            fila[0] = listaParaMostrar.get(i).getDescripcion();
            fila[1] = listaParaMostrar.get(i).getCantidadVendida();
            fila[2] = listaParaMostrar.get(i).getPrecioVenta();
            fila[3] = listaParaMostrar.get(i).getIdProducto();
            fila[4] = listaParaMostrar.get(i).getPosicionOriginal();
            totalPagar += listaParaMostrar.get(i).getPrecioVenta();
            modeloDetalles.addRow(fila);
        }
        ocultarColumnaDetalles();
        txt_PrecioTotal.setText("₡ " + totalPagar + "");
    }
    
    private void ocultarColumnaDetalles() {
        tablaDetalles.getColumn("HiddenId").setMaxWidth(0);
        tablaDetalles.getColumn("HiddenId").setMinWidth(0);
        tablaDetalles.getColumn("HiddenId").setPreferredWidth(0);
        tablaDetalles.getColumn("HiddenId").setWidth(0);
        tablaDetalles.getColumn("HiddenId").setResizable(false);
        tablaDetalles.getColumn("HiddenIdOriginal").setMaxWidth(0);
        tablaDetalles.getColumn("HiddenIdOriginal").setMinWidth(0);
        tablaDetalles.getColumn("HiddenIdOriginal").setPreferredWidth(0);
        tablaDetalles.getColumn("HiddenIdOriginal").setWidth(0);
        tablaDetalles.getColumn("HiddenIdOriginal").setResizable(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAceptar;
    private javax.swing.JPanel btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel labClose;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTextField txt_Cedula;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JTextField txt_NombreCompleto;
    private javax.swing.JLabel txt_PrecioTotal;
    private javax.swing.JTextField txt_TelCelular;
    private javax.swing.JTextField txt_TelHabitacion;
    private javax.swing.JTextField txt_TipoFactura;
    // End of variables declaration//GEN-END:variables
}
