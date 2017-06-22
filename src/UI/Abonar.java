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
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author oscal
 */
public class Abonar extends javax.swing.JFrame {

    DefaultTableModel modelo;
    BL_ManejadorCliente manejadorCliente = new BL_ManejadorCliente();
    ArrayList<BL_Factura> listaFacturas = new ArrayList<BL_Factura>();
    BL_Cliente cliente = new BL_Cliente();
    /**
     * Creates new form Abonar
     */
    public Abonar() {
        initComponents();
        modelo = (DefaultTableModel) tablaInventario.getModel();
        
        manejadorCliente.cargarClientes();
        
        ocultarColumnaID();
        comboBoxAutocompleta();
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
                    cbx_Cedula.setModel(manejadorCliente.obtenerListaComboBox(cadena));
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
    
    private void limpiarTabla(DefaultTableModel modelo) {
        int filas = tablaInventario.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_Senor = new javax.swing.JTextField();
        txt_Direccion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_PrecioTotal = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbx_Cedula = new javax.swing.JComboBox<String>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jSeparator7 = new javax.swing.JSeparator();
        labBuscar1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        btnPanelFacturar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtTelHab = new javax.swing.JTextField();
        txtTelCel = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtCedula = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
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

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, 460, 340));

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

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Monto para abonar:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 550, -1, -1));

        txt_PrecioTotal.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal.setForeground(new java.awt.Color(51, 51, 51));
        txt_PrecioTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal.setText("₡ ");
        jPanel2.add(txt_PrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 540, 30, 40));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Cedula:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, -1, 20));

        cbx_Cedula.setEditable(true);
        cbx_Cedula.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        cbx_Cedula.setBorder(null);
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

        btnPanelFacturar.setBackground(new java.awt.Color(0, 93, 107));
        btnPanelFacturar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPanelFacturarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPanelFacturarMouseEntered(evt);
            }
        });
        btnPanelFacturar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Abonar");
        jLabel1.setToolTipText("");
        btnPanelFacturar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 5, 60, 30));

        jPanel2.add(btnPanelFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 540, 100, 40));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Tipo de factura:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, -1));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton1.setText("Contado");
        jPanel2.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, -1));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton2.setText("Crédito");
        jPanel2.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, -1, -1));

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton3.setText("Apartado");
        jPanel2.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, -1));

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton4.setText("Tarjeta");
        jPanel2.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Teléfono cel:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 150, -1, -1));

        txtTelHab.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTelHab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtTelHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 130, -1));

        txtTelCel.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtTelCel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtTelCel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1079, 150, 140, -1));
        jPanel2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 170, 140, 10));
        jPanel2.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 130, 10));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Teléfono hab:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 150, 90, -1));

        jTextField8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 550, 120, -1));
        jPanel2.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 570, 120, 10));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Facturas de: ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtNombreCompleto.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtNombreCompleto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 310, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 310, 20));

        txtCedula.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCedula.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 180, -1));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, 180, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1250, 600));

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
        if(evt.getClickCount() == 2){
            txtCedula.setText(cliente.getCedula());
            txt_Senor.setText(cliente.getNombreCompleto());
            txt_Direccion.setText(cliente.getDireccion());
            
        }
    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void btnPanelFacturarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelFacturarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPanelFacturarMouseClicked

    private void btnPanelFacturarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelFacturarMouseEntered
        btnPanelFacturar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelFacturarMouseEntered

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnPanelFacturar;
    private javax.swing.JComboBox<String> cbx_Cedula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel labBuscar1;
    private javax.swing.JLabel labClose;
    private javax.swing.JLabel labClose1;
    private javax.swing.JLabel labClose2;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtTelCel;
    private javax.swing.JTextField txtTelHab;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JLabel txt_PrecioTotal;
    private javax.swing.JTextField txt_Senor;
    // End of variables declaration//GEN-END:variables
}
