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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
        modelo = (DefaultTableModel) tablaFacturas.getModel();
        tablaFacturas.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        modeloDetalles = (DefaultTableModel)tablaDetalles.getModel();
        tablaDetalles.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        
        tablaFacturas.getTableHeader().setReorderingAllowed(false);
        tablaDetalles.getTableHeader().setReorderingAllowed(false);
        
        manejadorCliente.cargarClientes();
        
        ocultarColumnaID();
        comboBoxAutocompleta();
        setColorCampos();
    }

    
    private void comboBoxAutocompleta(){
        cbx_Cedula.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent evt){
               
                String cadena = cbx_Cedula.getEditor().getItem().toString();  
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(buscar(cadena)){
                        cargarFacturas();
                    }else{
                        limpiarTabla(modelo);
                        limpiarTablaDetalles(modeloDetalles);
                        vaciarCampos();
                    }
                }
                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <=90 || evt.getKeyCode() >=97 && evt.getKeyCode() <= 122 || evt.getKeyCode() == 45 || evt.getKeyCode() >= 48 && evt.getKeyCode() <=57 || evt.getKeyCode() == 45 || evt.getKeyCode() == 8) {
                    DefaultComboBoxModel dcbxCedula = manejadorCliente.obtenerListaComboBox(cadena, "Cedula");
                    DefaultComboBoxModel dcbxNombreCompleto = manejadorCliente.obtenerListaComboBox(cadena, "NombreCompleto");
                    cbx_Cedula.setModel(dcbxCedula);
                    if (dcbxNombreCompleto.getSize() > 0) cbx_Cedula.setModel(dcbxNombreCompleto);
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
            if (c.getCedula().contentEquals(cadena) || c.getNombreCompleto().contentEquals(cadena)) {
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
        int filas = tablaFacturas.getRowCount();
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
        
        tablaFacturas.getColumn("HiddenID").setMaxWidth(0);
        tablaFacturas.getColumn("HiddenID").setMinWidth(0);
        tablaFacturas.getColumn("HiddenID").setPreferredWidth(0);
        tablaFacturas.getColumn("HiddenID").setWidth(0);
        tablaFacturas.getColumn("HiddenID").setResizable(false);

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
        tablaFacturas = new javax.swing.JTable();
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
        txtCedula = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        panelCamposAbonar = new javax.swing.JPanel();
        btnPanelAbonar = new javax.swing.JPanel();
        labAbonar = new javax.swing.JLabel();
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
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
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
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 10, 640));

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

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 240, 490, 310));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("N° Factura:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 30, -1, 20));

        txtNumFactura.setBackground(new java.awt.Color(255, 255, 255));
        txtNumFactura.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtNumFactura.setForeground(new java.awt.Color(51, 51, 51));
        txtNumFactura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtNumFactura.setText("0");
        txtNumFactura.setEnabled(false);
        jPanel2.add(txtNumFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 30, 80, 20));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Señor:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, -1, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Dirección:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, -1, -1));

        txt_Senor.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txt_Senor.setForeground(new java.awt.Color(51, 51, 51));
        txt_Senor.setBorder(null);
        txt_Senor.setEnabled(false);
        jPanel2.add(txt_Senor, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 130, 370, 20));

        txt_Direccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txt_Direccion.setForeground(new java.awt.Color(51, 51, 51));
        txt_Direccion.setBorder(null);
        txt_Direccion.setEnabled(false);
        jPanel2.add(txt_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 160, 370, 20));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Cedula:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, -1, 20));

        cbx_Cedula.setEditable(true);
        cbx_Cedula.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cbx_Cedula.setForeground(new java.awt.Color(51, 51, 51));
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
        jPanel2.add(cbx_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 310, 30));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tablaFacturas.setBackground(new java.awt.Color(232, 232, 232));
        tablaFacturas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        tablaFacturas.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
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
        if (tablaFacturas.getColumnModel().getColumnCount() > 0) {
            tablaFacturas.getColumnModel().getColumn(0).setResizable(false);
            tablaFacturas.getColumnModel().getColumn(1).setResizable(false);
            tablaFacturas.getColumnModel().getColumn(2).setResizable(false);
            tablaFacturas.getColumnModel().getColumn(3).setResizable(false);
            tablaFacturas.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 700, 430));

        labBuscar1.setBackground(new java.awt.Color(255, 255, 255));
        labBuscar1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labBuscar1.setForeground(new java.awt.Color(102, 102, 102));
        labBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar.png"))); // NOI18N
        labBuscar1.setText("Buscar cliente:");
        jPanel2.add(labBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 150, 370, -1));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, 370, 10));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Tipo factura:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, -1, -1));

        rdbContado.setBackground(new java.awt.Color(255, 255, 255));
        rdbContado.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        rdbContado.setForeground(new java.awt.Color(51, 51, 51));
        rdbContado.setText("Contado");
        rdbContado.setEnabled(false);
        jPanel2.add(rdbContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, 80, -1));

        rdbCredito.setBackground(new java.awt.Color(255, 255, 255));
        rdbCredito.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        rdbCredito.setForeground(new java.awt.Color(51, 51, 51));
        rdbCredito.setText("Crédito");
        rdbCredito.setEnabled(false);
        jPanel2.add(rdbCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 60, 70, -1));

        rdbApartado.setBackground(new java.awt.Color(255, 255, 255));
        rdbApartado.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        rdbApartado.setForeground(new java.awt.Color(51, 51, 51));
        rdbApartado.setText("Apartado");
        rdbApartado.setEnabled(false);
        jPanel2.add(rdbApartado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 60, 90, -1));

        rdbTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        rdbTarjeta.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        rdbTarjeta.setForeground(new java.awt.Color(51, 51, 51));
        rdbTarjeta.setText("Tarjeta");
        rdbTarjeta.setEnabled(false);
        jPanel2.add(rdbTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, 70, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Teléfono cel:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 200, -1, -1));

        txtTelHab.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtTelHab.setForeground(new java.awt.Color(51, 51, 51));
        txtTelHab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTelHab.setEnabled(false);
        jPanel2.add(txtTelHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 200, 120, 20));

        txtTelCel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtTelCel.setForeground(new java.awt.Color(51, 51, 51));
        txtTelCel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTelCel.setEnabled(false);
        jPanel2.add(txtTelCel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 200, 130, 20));
        jPanel2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 220, 130, 10));
        jPanel2.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 220, 120, 10));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Teléfono hab:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 120, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Facturas del cliente");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        txtCedula.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(51, 51, 51));
        txtCedula.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCedula.setEnabled(false);
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 100, 140, 20));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 120, 140, -1));

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

        labAbonar.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        labAbonar.setForeground(new java.awt.Color(255, 255, 255));
        labAbonar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labAbonar.setText("Abonar");
        labAbonar.setToolTipText("");
        labAbonar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labAbonarMouseClicked(evt);
            }
        });
        btnPanelAbonar.add(labAbonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, 30));

        panelCamposAbonar.add(btnPanelAbonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 50));

        txtMontoAbonar.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        txtMontoAbonar.setForeground(new java.awt.Color(51, 51, 51));
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
        panelCamposAbonar.add(txtMontoAbonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 120, 20));
        panelCamposAbonar.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 120, 10));

        txt_PrecioTotal.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal.setForeground(new java.awt.Color(51, 51, 51));
        txt_PrecioTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal.setText("₡ ");
        panelCamposAbonar.add(txt_PrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 30, 40));

        labMontoAbonar.setBackground(new java.awt.Color(51, 51, 51));
        labMontoAbonar.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        labMontoAbonar.setForeground(new java.awt.Color(102, 102, 102));
        labMontoAbonar.setText("Monto para abonar:");
        panelCamposAbonar.add(labMontoAbonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.add(panelCamposAbonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 550, 180, 120));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Total:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 560, -1, 30));

        txt_PrecioTotal1.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal1.setForeground(new java.awt.Color(51, 51, 51));
        txt_PrecioTotal1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal1.setText("₡ ");
        jPanel2.add(txt_PrecioTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 610, 30, 50));

        txtTotalAPagar.setEditable(false);
        txtTotalAPagar.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalAPagar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalAPagar.setText("0");
        txtTotalAPagar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTotalAPagar.setEnabled(false);
        jPanel2.add(txtTotalAPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 570, 110, 20));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Restante:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 620, -1, -1));
        jPanel2.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 590, 110, 10));

        txt_PrecioTotal2.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal2.setForeground(new java.awt.Color(51, 51, 51));
        txt_PrecioTotal2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal2.setText("₡ ");
        jPanel2.add(txt_PrecioTotal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 550, 30, 60));

        txtRestante.setEditable(false);
        txtRestante.setBackground(new java.awt.Color(255, 255, 255));
        txtRestante.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtRestante.setText("0");
        txtRestante.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtRestante.setEnabled(false);
        jPanel2.add(txtRestante, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 620, 110, -1));
        jPanel2.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 640, 110, 10));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Información de la factura");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, -1, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 1, 28)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Facturas para abonar");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1370, 680));

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
        jPanel4.add(labClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 10, -1, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 50));

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

    private void tablaFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturasMouseClicked
        txtRestante.setText("0");
        txtTotalAPagar.setText("0");
        
        
        if(tablaFacturas.getSelectedRow() >= 0){
            facturaSeleccionada = listaFacturas.get((int)tablaFacturas.getValueAt(tablaFacturas.getSelectedRow(), 4));
        
            if(tablaFacturas.getSelectedRow() != 0){
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
        
    }//GEN-LAST:event_tablaFacturasMouseClicked

    private void validarTipoFactura(String tipo){
        switch(tipo){
            case "Contado":
                rdbContado.setSelected(true);
                rdbTarjeta.setSelected(false);
                rdbApartado.setSelected(false);
                rdbCredito.setSelected(false);
                break;
            case "Tarjeta":
                rdbContado.setSelected(false);
                rdbTarjeta.setSelected(true);
                rdbApartado.setSelected(false);
                rdbCredito.setSelected(false);
                break;
            case "Apartado":
                rdbContado.setSelected(false);
                rdbTarjeta.setSelected(false);
                rdbApartado.setSelected(true);
                rdbCredito.setSelected(false);
                break;
            case "Crédito":
                rdbContado.setSelected(false);
                rdbTarjeta.setSelected(false);
                rdbApartado.setSelected(false);
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
        abonar();
    }//GEN-LAST:event_btnPanelAbonarMouseClicked

    private void abonar(){
        if(tablaFacturas.getSelectedRow() >= 0){
            if(!txtMontoAbonar.getText().equals("")){
            if(Double.parseDouble(txtMontoAbonar.getText()) <= Double.parseDouble(txtRestante.getText())){
                if(JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea abonar ₡" + txtMontoAbonar.getText() + " a esta factura?","Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon("src/recursos/pregunta.png")) == JOptionPane.YES_OPTION){

                    if(Double.parseDouble(txtMontoAbonar.getText()) == Double.parseDouble(txtRestante.getText())){
                        facturaSeleccionada.cancelarFactura();
                        listaFacturas.remove(facturaSeleccionada);

                        limpiarTablaDetalles(modeloDetalles);
                        limpiarTabla(modelo);
                        vaciarCampos();
                        cargarProductosEnTabla(listaFacturas);
                        JOptionPane.showMessageDialog(null, "Se ha cancelado esta factura","Factura cancelada",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/recursos/exito.png"));
                    }else{
                        JOptionPane.showMessageDialog(null, "Se ha abonado ₡" + txtMontoAbonar.getText() + " a la factura.","Abono realizado",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/recursos/exito.png"));
                        facturaSeleccionada.abonarAFactura(Double.parseDouble(txtMontoAbonar.getText()));
                        facturaSeleccionada.setMontoAbonado(facturaSeleccionada.getMontoAbonado() + Double.parseDouble(txtMontoAbonar.getText()));
                        txtRestante.setText((Double.parseDouble(txtTotalAPagar.getText()) - facturaSeleccionada.getMontoAbonado()) + "");
                        txtMontoAbonar.setText("");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "El monto ingresado para abonar es mayor a la deuda.\n"
                        + "Ingrese un monto menor para cancelar la factura.","Monto incorrecto",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
            }
            }else{
                JOptionPane.showMessageDialog(null, "Debe ingresar un monto.","Datos incompletos",JOptionPane.WARNING_MESSAGE,new ImageIcon("src/recursos/warning.png"));
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una factura","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
        }
    }
    
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
        if(buscar(cadena)){
            cargarFacturas();
        }
    }//GEN-LAST:event_cbx_CedulaActionPerformed

    private void cbx_CedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_CedulaKeyReleased
        
    }//GEN-LAST:event_cbx_CedulaKeyReleased

    private void labAbonarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labAbonarMouseClicked
        abonar();
    }//GEN-LAST:event_labAbonarMouseClicked


  
    private void validarNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && c != evt.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Este campo solo admite valores numericos", "Tipo de dato incorrecto", JOptionPane.WARNING_MESSAGE,new ImageIcon("src/recursos/warning.png"));
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel labAbonar;
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
    private javax.swing.JTable tablaFacturas;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtMontoAbonar;
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
