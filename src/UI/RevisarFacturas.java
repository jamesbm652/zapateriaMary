/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.BL_Cliente;
import BL.BL_Factura;
import BL.BL_ManejadorCliente;
import BL.BL_ManejadorFacturas;
import BL.BL_ProductoFactura;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author oscal
 */
public class RevisarFacturas extends javax.swing.JFrame {

    DefaultTableModel modelo;
    DefaultTableModel modeloDetalles;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    BL_ManejadorCliente manejadorCliente = new BL_ManejadorCliente();
    BL_ManejadorFacturas manejadorFacturas = new BL_ManejadorFacturas();
    
    ArrayList<BL_Factura> listaFacturas = new ArrayList<BL_Factura>();
    
    BL_Cliente cliente = new BL_Cliente();
    BL_Factura facturaSeleccionada;
    /**
     * Creates new form RevisarFacturas
     */
    public RevisarFacturas() {
        initComponents();
        modelo = (DefaultTableModel) tablaFacturas.getModel();
        modeloDetalles = (DefaultTableModel) tablaDetalles.getModel();
        
        tablaDetalles.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        tablaFacturas.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        jpanBusquedaAvanzada.setVisible(false);
        ocultarColumnaID();
        ocultarColumnaDetalles();
        
        manejadorCliente.cargarClientes();
        Date date = new Date();
        manejadorFacturas.cargarFacturasPorFecha(dateFormat.format(date),dateFormat.format(date));
            
        listaFacturas = manejadorFacturas.ObtenerLista();
        cargarProductosEnTabla(listaFacturas);
        
        comboBoxAutocompleta();
        
        
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
        jSeparator7 = new javax.swing.JSeparator();
        labBuscar1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTelHab = new javax.swing.JTextField();
        txtTelCel = new javax.swing.JTextField();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFacturas = new javax.swing.JTable();
        txtCedula = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbx_Cedula = new javax.swing.JComboBox<String>();
        rdbCredito = new javax.swing.JRadioButton();
        rdbApartado = new javax.swing.JRadioButton();
        rdbTarjeta = new javax.swing.JRadioButton();
        rdbContado = new javax.swing.JRadioButton();
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
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 11, 10, 560));

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

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, 460, 350));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("N° Factura:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel7.setText("0");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 90, 80, 20));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Señor:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, -1, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Dirección:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 150, -1, -1));

        txt_Senor.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txt_Senor.setBorder(null);
        jPanel2.add(txt_Senor, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, 380, 20));

        txt_Direccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txt_Direccion.setBorder(null);
        jPanel2.add(txt_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 380, 20));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Cedula:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, -1, 20));

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 300, 10));

        labBuscar1.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        labBuscar1.setForeground(new java.awt.Color(102, 102, 102));
        labBuscar1.setText("Buscar cliente:");
        jPanel2.add(labBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 140, 380, -1));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 380, 10));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Tipo de factura:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Teléfono cel:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 190, -1, -1));

        txtTelHab.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTelHab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtTelHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 190, 130, -1));

        txtTelCel.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtTelCel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtTelCel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 190, 140, -1));
        jPanel2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 210, 140, 10));
        jPanel2.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 130, 10));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Teléfono hab:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, 90, -1));

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

        jPanel2.add(jpanBusquedaAvanzada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 710, 110));

        labDropdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/dropdown.png"))); // NOI18N
        labDropdown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labDropdownMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labDropdownMouseEntered(evt);
            }
        });
        jPanel2.add(labDropdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Filtrar por:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, -1));

        tablaFacturas.setBackground(new java.awt.Color(232, 232, 232));
        tablaFacturas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        tablaFacturas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaFacturas.setForeground(new java.awt.Color(51, 51, 51));
        tablaFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Factura", "Fecha", "Tipo ", "Estado", "HiddenID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFacturas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaFacturas.setGridColor(new java.awt.Color(153, 153, 153));
        tablaFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaFacturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFacturas);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 710, 350));

        txtCedula.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(102, 102, 102));
        txtCedula.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 180, -1));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 180, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Factura seleccionada");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, -1, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 28)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Facturas");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        cbx_Cedula.setEditable(true);
        cbx_Cedula.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        cbx_Cedula.setBorder(null);
        cbx_Cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_CedulaActionPerformed(evt);
            }
        });
        cbx_Cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbx_CedulaKeyReleased(evt);
            }
        });
        jPanel2.add(cbx_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 300, 20));

        rdbCredito.setBackground(new java.awt.Color(255, 255, 255));
        rdbCredito.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdbCredito.setForeground(new java.awt.Color(102, 102, 102));
        rdbCredito.setText("Crédito");
        rdbCredito.setEnabled(false);
        jPanel2.add(rdbCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 60, -1, -1));

        rdbApartado.setBackground(new java.awt.Color(255, 255, 255));
        rdbApartado.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdbApartado.setForeground(new java.awt.Color(102, 102, 102));
        rdbApartado.setText("Apartado");
        rdbApartado.setEnabled(false);
        jPanel2.add(rdbApartado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 60, -1, -1));

        rdbTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        rdbTarjeta.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdbTarjeta.setForeground(new java.awt.Color(102, 102, 102));
        rdbTarjeta.setText("Tarjeta");
        rdbTarjeta.setEnabled(false);
        jPanel2.add(rdbTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, -1, -1));

        rdbContado.setBackground(new java.awt.Color(255, 255, 255));
        rdbContado.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdbContado.setForeground(new java.awt.Color(102, 102, 102));
        rdbContado.setText("Contado");
        rdbContado.setEnabled(false);
        jPanel2.add(rdbContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1250, 590));

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

    
    private void comboBoxAutocompleta(){
        cbx_Cedula.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent evt){
               
                String cadena = cbx_Cedula.getEditor().getItem().toString();;  
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(buscar(cadena)){
                        cargarFacturas();
                    }
                }
                if (evt.getKeyCode() >= 48 && evt.getKeyCode() <=57 || evt.getKeyCode() == 45 || evt.getKeyCode() == 8) {
                    cbx_Cedula.setModel(manejadorCliente.obtenerListaComboBox(cadena, "Cedula"));
                    if (cbx_Cedula.getItemCount() > 0) {
                        cbx_Cedula.showPopup();
                        if (evt.getKeyCode() != 8) {
                            ((JTextComponent)cbx_Cedula.getEditor().getEditorComponent()).select(cadena.length(), 
                                    cbx_Cedula.getEditor().getItem().toString().length());
                        }else{
                            cbx_Cedula.getEditor().setItem(cadena);
                        }
                    }else{
                        cbx_Cedula.addItem(cadena);
                        int fin = cbx_Cedula.getEditor().getItem().toString().length();
                        ((JTextComponent)cbx_Cedula.getEditor().getEditorComponent()).select(fin, fin);
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    int fin = cbx_Cedula.getEditor().getItem().toString().length();
                    ((JTextComponent)cbx_Cedula.getEditor().getEditorComponent()).select(fin, fin);
                    
                    if(cadena.equals("")){
                        limpiarTabla(modelo);
                        limpiarTablaDetalles(modeloDetalles);
                        vaciarCampos();
                    }
                }
            }
        });
    }
    
    private boolean buscar(String cadena) {
        boolean existe = false;
        for (BL_Cliente c : manejadorCliente.obtenerLista()) {
            if (c.getCedula().contentEquals(cadena)) {
                cliente.setIdCliente(c.getIdCliente());
                cliente.setNombreCompleto(c.getNombreCompleto());
                cliente.setCedula(c.getCedula());
                cliente.setDireccion(c.getDireccion());
                cliente.setListaTelefonos(c.getListaTelefonos());
                existe = true;
            }
        }
        
        return existe;
    }
    
    private void vaciarCampos(){
        txtCedula.setText("");
        txt_Senor.setText("");
        txt_Direccion.setText("");
        txtTelHab.setText("");
        txtTelCel.setText("");
        txtNumFactura.setText("0");
        
        rdbContado.setSelected(false);
        rdbTarjeta.setSelected(false);
        rdbApartado.setSelected(false);
        rdbCredito.setSelected(false);
    }
    
    private void cargarFacturas(){
        manejadorFacturas.cargarFacturasPorCliente(cliente.getIdCliente());
        listaFacturas = manejadorFacturas.ObtenerLista();
        cargarProductosEnTabla(listaFacturas);
    }
    
    private void cargarProductosEnTabla(ArrayList<BL_Factura> listaParaMostrar) {

        limpiarTabla(modelo);
        Object[] fila = new Object[modelo.getColumnCount()];

        for (int i = 0; i < listaParaMostrar.size(); i++) {
            fila[0] = listaParaMostrar.get(i).getIdFactura();
            fila[1] = listaParaMostrar.get(i).getFechaFactura().toString();
            fila[2] = listaParaMostrar.get(i).getTipoFactura();
            if(listaParaMostrar.get(i).isCancelada()){
                fila[3] = "Cancelada";
            }else{
                fila[3] = "Sin cancelar";
            }
            
            fila[4] = i;
            
            modelo.addRow(fila);
        }
        listaFacturas = listaParaMostrar;
    }
    
    private void limpiarTabla(DefaultTableModel modelo) {
        int filas = tablaFacturas.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }
    
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

    private void tablaFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturasMouseClicked
        if(tablaFacturas.getSelectedRow() >= 0){
            facturaSeleccionada = listaFacturas.get((int)tablaFacturas.getValueAt(tablaFacturas.getSelectedRow(), 4));
            if(facturaSeleccionada.getCliente() != null) cliente = facturaSeleccionada.getCliente(); 
            
            validarTipoFactura(facturaSeleccionada.getTipoFactura());
            txtCedula.setText(cliente.getCedula());
            txt_Senor.setText(cliente.getNombreCompleto());
            txt_Direccion.setText(cliente.getDireccion());
            // Validar y setear telefonos
            if (cliente.getListaTelefonos().size() == 1) {
                if (cliente.getListaTelefonos().get(0).getTipoTelefono().equals("Habitacion")) {
                    txtTelHab.setText(cliente.getListaTelefonos().get(0).getTelefono());
                    txtTelCel.setText("");
                }else{
                    txtTelCel.setText(cliente.getListaTelefonos().get(0).getTelefono());
                    txtTelHab.setText("");
                }
            }
            if (cliente.getListaTelefonos().size() == 2) {
                if (cliente.getListaTelefonos().get(0).getTipoTelefono().equals("Habitacion")) {
                    txtTelHab.setText(cliente.getListaTelefonos().get(0).getTelefono());
                    txtTelCel.setText(cliente.getListaTelefonos().get(1).getTelefono());
                }else{
                    txtTelHab.setText(cliente.getListaTelefonos().get(1).getTelefono());
                    txtTelCel.setText(cliente.getListaTelefonos().get(0).getTelefono());
                }
            }
            
            txtNumFactura.setText(facturaSeleccionada.getIdFactura() + "");
            cargarProductosEnTablaDetalles(facturaSeleccionada.getProductosFactura());

            
            setColorCampos();
        }

    }//GEN-LAST:event_tablaFacturasMouseClicked

    private void validarTipoFactura(String tipo){
        rdbContado.setSelected(false);
        rdbTarjeta.setSelected(false);
        rdbApartado.setSelected(false);
        rdbCredito.setSelected(false);
        
        switch(tipo){
            case "Contado":
                rdbContado.setSelected(true);
                break;
            case "Tarjeta":
                rdbTarjeta.setSelected(true);
                break;
            case "Apartado":
                rdbApartado.setSelected(true);
                break;
            case "Crédito":
                rdbCredito.setSelected(true);
                break;
                
        }
    }
    
    private void cbx_CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_CedulaActionPerformed
        // TODO add your handling code here:
        String cadena = cbx_Cedula.getEditor().getItem().toString();
        buscar(cadena);
    }//GEN-LAST:event_cbx_CedulaActionPerformed

    private void cbx_CedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_CedulaKeyReleased

    }//GEN-LAST:event_cbx_CedulaKeyReleased

    private void ocultarColumnaID() {
        
        tablaFacturas.getColumn("HiddenID").setMaxWidth(0);
        tablaFacturas.getColumn("HiddenID").setMinWidth(0);
        tablaFacturas.getColumn("HiddenID").setPreferredWidth(0);
        tablaFacturas.getColumn("HiddenID").setWidth(0);
        tablaFacturas.getColumn("HiddenID").setResizable(false);

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
    
    private void cargarProductosEnTablaDetalles(ArrayList<BL_ProductoFactura> listaParaMostrar) {

        limpiarTablaDetalles(modeloDetalles);
        Object[] fila = new Object[modeloDetalles.getColumnCount()];

        for (int i = 0; i < listaParaMostrar.size(); i++) {
            fila[0] = listaParaMostrar.get(i).getDescripcion();
            fila[1] = listaParaMostrar.get(i).getCantidadVendida();
            fila[2] = listaParaMostrar.get(i).getPrecioVenta();
            
            modeloDetalles.addRow(fila);
            
            //txtTotalAPagar.setText((Double.parseDouble(txtTotalAPagar.getText()) + listaParaMostrar.get(i).getPrecioVenta()) + "");
        }
        //txtTotalAPagar.setBackground(Color.WHITE);
    }
    
    private void setColorCampos(){
        txtCedula.setBackground(Color.WHITE);
        txt_Senor.setBackground(Color.WHITE);
        txt_Direccion.setBackground(Color.WHITE);
        txtTelHab.setBackground(Color.WHITE);
        txtTelCel.setBackground(Color.WHITE);
        txtNumFactura.setBackground(Color.WHITE);
    }
    
    private void limpiarTablaDetalles(DefaultTableModel modelo) {
        int filas = tablaDetalles.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }
    
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jpanBusquedaAvanzada;
    private javax.swing.JLabel labBuscar1;
    private javax.swing.JLabel labBuscarAvanzada;
    private javax.swing.JLabel labClose;
    private javax.swing.JLabel labDropdown;
    private javax.swing.JRadioButton rdbApartado;
    private javax.swing.JRadioButton rdbContado;
    private javax.swing.JRadioButton rdbCredito;
    private javax.swing.JRadioButton rdbTarjeta;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTable tablaFacturas;
    private javax.swing.JTextField txtCedula;
    private com.toedter.calendar.JDateChooser txtFechaFinal;
    private com.toedter.calendar.JDateChooser txtFechaInicio;
    private javax.swing.JTextField txtNumFactura;
    private javax.swing.JTextField txtTelCel;
    private javax.swing.JTextField txtTelHab;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JTextField txt_Senor;
    // End of variables declaration//GEN-END:variables
}
