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
import BL.BL_Producto;
import BL.BL_ProductoFactura;
import BL.BL_TelefonoCliente;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author oscal
 */
public class Abonar extends javax.swing.JFrame {

    DefaultTableModel modelo;
    DefaultTableModel modeloDetalles;
    
    BL_ManejadorCliente manejadorCliente = new BL_ManejadorCliente();
    ArrayList<BL_Factura> listaFacturas = new ArrayList<BL_Factura>();
    BL_Cliente cliente = new BL_Cliente();
    BL_Factura facturaSeleccionada;
    /**
     * Creates new form Abonar
     */
    public Abonar() {
        initComponents();
        modelo = (DefaultTableModel) tablaInventario.getModel();
        tablaInventario.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        modeloDetalles = (DefaultTableModel)tablaDetalles.getModel();
        tablaDetalles.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        
        manejadorCliente.cargarClientes();
        
        ocultarColumnaID();
        comboBoxAutocompleta();
        setColorCampos();
    }

    
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
                txtNombreCompleto.setText(c.getNombreCompleto());

               
                cliente.setIdCliente(c.getIdCliente());
                cliente.setNombreCompleto(c.getNombreCompleto());
                cliente.setCedula(c.getCedula());
                cliente.setDireccion(c.getDireccion());
                cliente.setListaTelefonos(c.getListaTelefonos());
                existe = true;
            }
        }
        if (!existe) {
            txtNombreCompleto.setText("");
        }
        return existe;
    }
    
    
    private void cargarFacturas(){
        BL_ManejadorFacturas mFacturas = new BL_ManejadorFacturas();
        mFacturas.cargarFacturasPorCliente(cliente.getIdCliente());
        listaFacturas = mFacturas.ObtenerLista();
        cargarProductosEnTabla(listaFacturas);
    }
    
    private void cargarProductosEnTabla(ArrayList<BL_Factura> listaParaMostrar) {

        limpiarTabla(modelo);
        Object[] fila = new Object[modelo.getColumnCount()];

        for (int i = 0; i < listaParaMostrar.size(); i++) {
            fila[0] = listaParaMostrar.get(i).getIdFactura();
            fila[1] = listaParaMostrar.get(i).getFechaFactura().toString();
            fila[2] = listaParaMostrar.get(i).getTipoFactura();
            fila[3] = "Sin cancelar";
            fila[4] = i;
            
            modelo.addRow(fila);
        }
        listaFacturas = listaParaMostrar;
    }
    
    private void cargarProductosEnTablaDetalles(ArrayList<BL_ProductoFactura> listaParaMostrar) {

        limpiarTablaDetalles(modeloDetalles);
        Object[] fila = new Object[modeloDetalles.getColumnCount()];

        for (int i = 0; i < listaParaMostrar.size(); i++) {
            fila[0] = listaParaMostrar.get(i).getDescripcion();
            fila[1] = listaParaMostrar.get(i).getCantidadVendida();
            fila[2] = listaParaMostrar.get(i).getPrecioVenta();
            
            modeloDetalles.addRow(fila);
            
            txtTotalAPagar.setText((Double.parseDouble(txtTotalAPagar.getText()) + listaParaMostrar.get(i).getPrecioVenta()) + "");
        }
        txtTotalAPagar.setBackground(Color.WHITE);
    }
    
    
    private void limpiarTabla(DefaultTableModel modelo) {
        int filas = tablaInventario.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }
    
    private void limpiarTablaDetalles(DefaultTableModel modelo) {
        int filas = tablaDetalles.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }
    
    private void vaciarCampos(){
        txtCedula.setText("");
        txt_Senor.setText("");
        txt_Direccion.setText("");
        txtTelHab.setText("");
        txtTelCel.setText("");
        txtNumFactura.setText("0");
        txtTotalAPagar.setText("0");
        txtRestante.setText("0");
        txtMontoAbonar.setText("");
        
        rdbContado.setSelected(false);
        rdbTarjeta.setSelected(false);
        rdbApartado.setSelected(false);
        rdbCredito.setSelected(false);
    }
    
    private void ocultarColumnaID() {
        
        tablaInventario.getColumn("HiddenID").setMaxWidth(0);
        tablaInventario.getColumn("HiddenID").setMinWidth(0);
        tablaInventario.getColumn("HiddenID").setPreferredWidth(0);
        tablaInventario.getColumn("HiddenID").setWidth(0);
        tablaInventario.getColumn("HiddenID").setResizable(false);

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtNumFactura = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_Senor = new javax.swing.JTextField();
        txt_Direccion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbx_Cedula = new javax.swing.JComboBox<String>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jSeparator7 = new javax.swing.JSeparator();
        labBuscar1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        rdbContado = new javax.swing.JRadioButton();
        rdbCredito = new javax.swing.JRadioButton();
        rdbApartado = new javax.swing.JRadioButton();
        rdbTarjeta = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtTelHab = new javax.swing.JTextField();
        txtTelCel = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtCedula = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        panelCamposAbonar = new javax.swing.JPanel();
        btnPanelAbonar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMontoAbonar = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        txt_PrecioTotal = new javax.swing.JLabel();
        labMontoAbonar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_PrecioTotal1 = new javax.swing.JLabel();
        txtTotalAPagar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        txt_PrecioTotal2 = new javax.swing.JLabel();
        txtRestante = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        labClose = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        labClose1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        labClose2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 11, 10, 560));

        tablaDetalles.setBackground(new java.awt.Color(232, 232, 232));
        tablaDetalles.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaDetalles.setForeground(new java.awt.Color(51, 51, 51));
        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Descripción", "Cantidad", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalles.setGridColor(new java.awt.Color(153, 153, 153));
        jScrollPane2.setViewportView(tablaDetalles);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, 460, 310));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("N° Factura:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 50, -1, 20));

        txtNumFactura.setBackground(new java.awt.Color(255, 255, 255));
        txtNumFactura.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtNumFactura.setText("0");
        txtNumFactura.setEnabled(false);
        jPanel2.add(txtNumFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 50, 80, 20));

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
        txt_Senor.setEnabled(false);
        jPanel2.add(txt_Senor, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 380, 20));

        txt_Direccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txt_Direccion.setBorder(null);
        txt_Direccion.setEnabled(false);
        jPanel2.add(txt_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 380, 20));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Cedula:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, -1, 20));

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
        jPanel2.add(cbx_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 310, -1));

        tablaInventario.setBackground(new java.awt.Color(232, 232, 232));
        tablaInventario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        tablaInventario.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaInventario.setForeground(new java.awt.Color(51, 51, 51));
        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaInventario.setGridColor(new java.awt.Color(153, 153, 153));
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInventario);
        if (tablaInventario.getColumnModel().getColumnCount() > 0) {
            tablaInventario.getColumnModel().getColumn(0).setResizable(false);
            tablaInventario.getColumnModel().getColumn(1).setResizable(false);
            tablaInventario.getColumnModel().getColumn(2).setResizable(false);
            tablaInventario.getColumnModel().getColumn(3).setResizable(false);
            tablaInventario.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 150, 700, 380));

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 310, 10));

        labBuscar1.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        labBuscar1.setForeground(new java.awt.Color(102, 102, 102));
        labBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar.png"))); // NOI18N
        labBuscar1.setText("Buscar:");
        jPanel2.add(labBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 380, -1));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 380, 10));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Tipo de factura:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, -1));

        rdbContado.setBackground(new java.awt.Color(255, 255, 255));
        rdbContado.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdbContado.setForeground(new java.awt.Color(102, 102, 102));
        rdbContado.setText("Contado");
        rdbContado.setEnabled(false);
        jPanel2.add(rdbContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, -1));

        rdbCredito.setBackground(new java.awt.Color(255, 255, 255));
        rdbCredito.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdbCredito.setForeground(new java.awt.Color(102, 102, 102));
        rdbCredito.setText("Crédito");
        rdbCredito.setEnabled(false);
        jPanel2.add(rdbCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, -1, -1));

        rdbApartado.setBackground(new java.awt.Color(255, 255, 255));
        rdbApartado.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdbApartado.setForeground(new java.awt.Color(102, 102, 102));
        rdbApartado.setText("Apartado");
        rdbApartado.setEnabled(false);
        jPanel2.add(rdbApartado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, -1));

        rdbTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        rdbTarjeta.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdbTarjeta.setForeground(new java.awt.Color(102, 102, 102));
        rdbTarjeta.setText("Tarjeta");
        rdbTarjeta.setEnabled(false);
        jPanel2.add(rdbTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Teléfono cel:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 150, -1, -1));

        txtTelHab.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTelHab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTelHab.setEnabled(false);
        jPanel2.add(txtTelHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 130, -1));

        txtTelCel.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtTelCel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTelCel.setEnabled(false);
        jPanel2.add(txtTelCel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1079, 150, 140, -1));
        jPanel2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 170, 140, 10));
        jPanel2.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 130, 10));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Teléfono hab:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 150, 90, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Facturas de: ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtNombreCompleto.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtNombreCompleto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 310, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 310, 20));

        txtCedula.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCedula.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCedula.setEnabled(false);
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 180, -1));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, 180, -1));

        panelCamposAbonar.setBackground(new java.awt.Color(255, 255, 255));
        panelCamposAbonar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPanelAbonar.setBackground(new java.awt.Color(0, 93, 107));
        btnPanelAbonar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPanelAbonarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPanelAbonarMouseEntered(evt);
            }
        });
        btnPanelAbonar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Abonar");
        jLabel1.setToolTipText("");
        btnPanelAbonar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 50, 40));

        panelCamposAbonar.add(btnPanelAbonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 100, 40));

        txtMontoAbonar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMontoAbonar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtMontoAbonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoAbonarActionPerformed(evt);
            }
        });
        txtMontoAbonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoAbonarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoAbonarKeyTyped(evt);
            }
        });
        panelCamposAbonar.add(txtMontoAbonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 120, -1));
        panelCamposAbonar.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 120, 10));

        txt_PrecioTotal.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal.setForeground(new java.awt.Color(51, 51, 51));
        txt_PrecioTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal.setText("₡ ");
        panelCamposAbonar.add(txt_PrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 30, 40));

        labMontoAbonar.setBackground(new java.awt.Color(51, 51, 51));
        labMontoAbonar.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        labMontoAbonar.setForeground(new java.awt.Color(102, 102, 102));
        labMontoAbonar.setText("Monto para abonar:");
        panelCamposAbonar.add(labMontoAbonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel2.add(panelCamposAbonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 550, 480, 50));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Total:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 512, -1, 30));

        txt_PrecioTotal1.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal1.setForeground(new java.awt.Color(51, 51, 51));
        txt_PrecioTotal1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal1.setText("₡ ");
        jPanel2.add(txt_PrecioTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 510, 30, 50));

        txtTotalAPagar.setEditable(false);
        txtTotalAPagar.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalAPagar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalAPagar.setText("0");
        txtTotalAPagar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTotalAPagar.setEnabled(false);
        jPanel2.add(txtTotalAPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 520, 100, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Restante:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 520, -1, -1));
        jPanel2.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 540, 100, 10));

        txt_PrecioTotal2.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal2.setForeground(new java.awt.Color(51, 51, 51));
        txt_PrecioTotal2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal2.setText("₡ ");
        jPanel2.add(txt_PrecioTotal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 500, 30, 50));

        txtRestante.setEditable(false);
        txtRestante.setBackground(new java.awt.Color(255, 255, 255));
        txtRestante.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtRestante.setText("0");
        txtRestante.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtRestante.setEnabled(false);
        jPanel2.add(txtRestante, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 520, 110, -1));
        jPanel2.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 540, 110, 10));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1250, 610));

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

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 50));

        jPanel5.setBackground(new java.awt.Color(0, 57, 66));
        jPanel5.setMinimumSize(new java.awt.Dimension(700, 74));
        jPanel5.setPreferredSize(new java.awt.Dimension(700, 74));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labClose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/close.png"))); // NOI18N
        labClose1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labClose1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labClose1MouseEntered(evt);
            }
        });
        jPanel5.add(labClose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 18, -1, 20));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel6.setBackground(new java.awt.Color(0, 57, 66));
        jPanel6.setMinimumSize(new java.awt.Dimension(700, 74));
        jPanel6.setPreferredSize(new java.awt.Dimension(700, 74));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/close.png"))); // NOI18N
        labClose2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labClose2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labClose2MouseEntered(evt);
            }
        });
        jPanel6.add(labClose2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 18, -1, 20));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked
        txtRestante.setText("0");
        txtTotalAPagar.setText("0");
        
        if(tablaInventario.getSelectedRow() >= 0){
            facturaSeleccionada = listaFacturas.get((int)tablaInventario.getValueAt(tablaInventario.getSelectedRow(), 4));
        
            if(tablaInventario.getSelectedRow() != 0){
                panelCamposAbonar.setVisible(false);
            }else{
                panelCamposAbonar.setVisible(true);
            }
            
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
            validarTipoFactura(facturaSeleccionada.getTipoFactura());
            txtNumFactura.setText(facturaSeleccionada.getIdFactura() + "");
            cargarProductosEnTablaDetalles(facturaSeleccionada.getProductosFactura());
            
            
            txtRestante.setText((Double.parseDouble(txtTotalAPagar.getText()) - facturaSeleccionada.getMontoAbonado()) + "");
            txtRestante.setBackground(Color.WHITE);
            setColorCampos();
        }
        
    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void validarTipoFactura(String tipo){
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
    
    private void setColorCampos(){
        txtCedula.setBackground(Color.WHITE);
        txt_Senor.setBackground(Color.WHITE);
        txt_Direccion.setBackground(Color.WHITE);
        txtTelHab.setBackground(Color.WHITE);
        txtTelCel.setBackground(Color.WHITE);
        txtNumFactura.setBackground(Color.WHITE);
    }
    
    private void btnPanelAbonarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAbonarMouseClicked
        if(!txtMontoAbonar.getText().equals("")){
        if(Double.parseDouble(txtMontoAbonar.getText()) <= Double.parseDouble(txtRestante.getText())){
            facturaSeleccionada.abonarAFactura(Double.parseDouble(txtMontoAbonar.getText()));
            if(JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea abonar " + txtMontoAbonar.getText() + " a esta factura?") == JOptionPane.YES_OPTION){
                if(Double.parseDouble(txtMontoAbonar.getText()) == Double.parseDouble(txtRestante.getText())){
                    facturaSeleccionada.cancelarFactura();
                    JOptionPane.showMessageDialog(null, "Se ha cancelado esta factura","",JOptionPane.INFORMATION_MESSAGE);
                }
                this.dispose();
                new Abonar().setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "El monto ingresado para abonar es mayor a la deuda\n"
                    + "Ingrese un monto menor para cancelar la factura","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar un monto");
        }
    }//GEN-LAST:event_btnPanelAbonarMouseClicked

    private void btnPanelAbonarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAbonarMouseEntered
        btnPanelAbonar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelAbonarMouseEntered

    private void labCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseClicked
        this.dispose();
        new Menu_Principal().setVisible(true);
    }//GEN-LAST:event_labCloseMouseClicked

    private void labCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labCloseMouseEntered

    private void labClose1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labClose1MouseClicked
        this.dispose();
        new Menu_Principal().setVisible(true);
    }//GEN-LAST:event_labClose1MouseClicked

    private void labClose1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labClose1MouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labClose1MouseEntered

    private void labClose2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labClose2MouseClicked
        this.dispose();
        new Menu_Principal().setVisible(true);
    }//GEN-LAST:event_labClose2MouseClicked

    private void labClose2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labClose2MouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labClose2MouseEntered

    private void txtMontoAbonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoAbonarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoAbonarActionPerformed

    private void txtMontoAbonarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAbonarKeyTyped
        validarNumeros(evt);
    }//GEN-LAST:event_txtMontoAbonarKeyTyped

    private void txtMontoAbonarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAbonarKeyReleased
        if(!txtMontoAbonar.getText().equals("")){
            if(Double.parseDouble(txtMontoAbonar.getText().trim()) <= 0){
                txtMontoAbonar.setText("");
            }
        }
    }//GEN-LAST:event_txtMontoAbonarKeyReleased

    private void cbx_CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_CedulaActionPerformed
        // TODO add your handling code here:
        String cadena = cbx_Cedula.getEditor().getItem().toString();
        buscar(cadena);
    }//GEN-LAST:event_cbx_CedulaActionPerformed

    private void cbx_CedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_CedulaKeyReleased
        
    }//GEN-LAST:event_cbx_CedulaKeyReleased


  
    private void validarNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && c != '.' && c != evt.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Este campo solo admite valores numericos y ' . '", "Tipo de dato incorrecto", JOptionPane.WARNING_MESSAGE);
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
    private javax.swing.JPanel btnPanelAbonar;
    private javax.swing.JComboBox<String> cbx_Cedula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel labBuscar1;
    private javax.swing.JLabel labClose;
    private javax.swing.JLabel labClose1;
    private javax.swing.JLabel labClose2;
    private javax.swing.JLabel labMontoAbonar;
    private javax.swing.JPanel panelCamposAbonar;
    private javax.swing.JRadioButton rdbApartado;
    private javax.swing.JRadioButton rdbContado;
    private javax.swing.JRadioButton rdbCredito;
    private javax.swing.JRadioButton rdbTarjeta;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtMontoAbonar;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JLabel txtNumFactura;
    private javax.swing.JTextField txtRestante;
    private javax.swing.JTextField txtTelCel;
    private javax.swing.JTextField txtTelHab;
    private javax.swing.JTextField txtTotalAPagar;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JLabel txt_PrecioTotal;
    private javax.swing.JLabel txt_PrecioTotal1;
    private javax.swing.JLabel txt_PrecioTotal2;
    private javax.swing.JTextField txt_Senor;
    // End of variables declaration//GEN-END:variables
}
