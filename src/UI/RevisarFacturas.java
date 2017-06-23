/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author oscal
 */
public class RevisarFacturas extends javax.swing.JFrame {

    /**
     * Creates new form RevisarFacturas
     */
    public RevisarFacturas() {
        initComponents();
        
        tablaDetalles.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        tablaInventario.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_Senor = new javax.swing.JTextField();
        txt_Direccion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbx_Cedula = new javax.swing.JComboBox<String>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jSeparator7 = new javax.swing.JSeparator();
        txtBuscar = new javax.swing.JTextField();
        labBuscar1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jpanBusquedaAvanzada = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtFechaInicio = new com.toedter.calendar.JDateChooser();
        txtFechaFinal = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox();
        cmbTipo = new javax.swing.JComboBox();
        txtNumFactura = new javax.swing.JTextField();
        labBuscarAvanzada = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        labDropdown = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1250, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setMinimumSize(new java.awt.Dimension(1250, 500));
        jPanel2.setPreferredSize(new java.awt.Dimension(1250, 500));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 11, 10, 510));

        tablaDetalles.setBackground(new java.awt.Color(232, 232, 232));
        tablaDetalles.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaDetalles.setForeground(new java.awt.Color(51, 51, 51));
        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Descripción", "Cantidad", "Precio", "HiddenId", "HiddenIdOriginal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalles.setGridColor(new java.awt.Color(153, 153, 153));
        jScrollPane2.setViewportView(tablaDetalles);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, 460, 340));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("N° Factura:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel7.setText("0");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 50, 80, 20));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Señor:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, -1, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Dirección:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, -1, -1));

        txt_Senor.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txt_Senor.setBorder(null);
        jPanel2.add(txt_Senor, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 380, 20));

        txt_Direccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txt_Direccion.setBorder(null);
        jPanel2.add(txt_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 380, 20));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Cedula:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, -1, 20));

        cbx_Cedula.setEditable(true);
        jPanel2.add(cbx_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 130, -1));

        tablaInventario.setBackground(new java.awt.Color(232, 232, 232));
        tablaInventario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        tablaInventario.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaInventario.setForeground(new java.awt.Color(51, 51, 51));
        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Único", "Descripción", "HiddenID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaInventario.setGridColor(new java.awt.Color(153, 153, 153));
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInventario);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 708, 340));

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 40, 300, 10));

        txtBuscar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 312, 20));

        labBuscar1.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        labBuscar1.setForeground(new java.awt.Color(102, 102, 102));
        labBuscar1.setText("Buscar:");
        jPanel2.add(labBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 380, -1));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 380, 10));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Tipo de factura:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, -1));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Contado");
        jPanel2.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, -1));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("Crédito");
        jPanel2.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, -1, -1));

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("Apartado");
        jPanel2.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, -1));

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("Tarjeta");
        jPanel2.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Teléfono cel:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 150, -1, -1));

        jTextField1.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 130, -1));

        jTextField2.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1079, 150, 140, -1));
        jPanel2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 170, 140, 10));
        jPanel2.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 130, 10));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Teléfono hab:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 150, 90, -1));

        jpanBusquedaAvanzada.setBackground(new java.awt.Color(255, 255, 255));
        jpanBusquedaAvanzada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jpanBusquedaAvanzada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Fecha inicial:");
        jpanBusquedaAvanzada.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, 30));

        jLabel9.setText("Fecha Final:");
        jpanBusquedaAvanzada.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 70, -1, 20));

        jLabel12.setText("N° Factura:");
        jpanBusquedaAvanzada.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 14, -1, 30));
        jpanBusquedaAvanzada.add(txtFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 150, -1));
        jpanBusquedaAvanzada.add(txtFechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 150, -1));

        jLabel15.setText("Tipo:");
        jpanBusquedaAvanzada.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        jLabel16.setText("Estado:");
        jpanBusquedaAvanzada.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jpanBusquedaAvanzada.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 150, -1));

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jpanBusquedaAvanzada.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 150, -1));

        txtNumFactura.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jpanBusquedaAvanzada.add(txtNumFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 150, -1));

        labBuscarAvanzada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar.png"))); // NOI18N
        labBuscarAvanzada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labBuscarAvanzadaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labBuscarAvanzadaMouseEntered(evt);
            }
        });
        jpanBusquedaAvanzada.add(labBuscarAvanzada, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 30, -1));
        jpanBusquedaAvanzada.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 150, 10));

        jPanel2.add(jpanBusquedaAvanzada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 710, 110));

        labDropdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/dropdown.png"))); // NOI18N
        labDropdown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labDropdownMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labDropdownMouseEntered(evt);
            }
        });
        jPanel2.add(labDropdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Filtrar por:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1250, 550));

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
        jPanel4.add(labClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 10, -1, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked

    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String filtro = txtBuscar.getText();
        
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void labBuscarAvanzadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labBuscarAvanzadaMouseClicked
        
    }//GEN-LAST:event_labBuscarAvanzadaMouseClicked

    private void labBuscarAvanzadaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labBuscarAvanzadaMouseEntered
        labBuscarAvanzada.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labBuscarAvanzadaMouseEntered

    private void labDropdownMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labDropdownMouseClicked
        if (jpanBusquedaAvanzada.isVisible()) {
            jpanBusquedaAvanzada.setVisible(false);
        } else {
            jpanBusquedaAvanzada.setVisible(true);
        }
    }//GEN-LAST:event_labDropdownMouseClicked

    private void labDropdownMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labDropdownMouseEntered
        labDropdown.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labDropdownMouseEntered

    private void labCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseClicked
        this.dispose();
        new Menu_Principal().setVisible(true);
    }//GEN-LAST:event_labCloseMouseClicked

    private void labCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labCloseMouseEntered

    static public class HeaderColor extends DefaultTableCellRenderer{
        public HeaderColor(){
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable tabla,Object value,boolean selected,boolean fused,int row,int column){
            super.getTableCellRendererComponent(tabla, value, selected, fused, row, column);
            setBorder(new LineBorder(Color.BLACK, 1));
            setForeground(Color.WHITE);
            setBackground(new java.awt.Color(0,105,120));
            setHorizontalAlignment((int) tabla.CENTER_ALIGNMENT);
            return this;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbx_Cedula;
    private javax.swing.JComboBox cmbEstado;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel jpanBusquedaAvanzada;
    private javax.swing.JLabel labBuscar1;
    private javax.swing.JLabel labBuscarAvanzada;
    private javax.swing.JLabel labClose;
    private javax.swing.JLabel labDropdown;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTextField txtBuscar;
    private com.toedter.calendar.JDateChooser txtFechaFinal;
    private com.toedter.calendar.JDateChooser txtFechaInicio;
    private javax.swing.JTextField txtNumFactura;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JTextField txt_Senor;
    // End of variables declaration//GEN-END:variables
}
