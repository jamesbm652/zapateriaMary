/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.BL_Cliente;
import BL.BL_ManejadorCliente;
import BL.BL_ManejadorProducto;
import BL.BL_Producto;
import BL.BL_ManejadorProductoFactura;
import BL.BL_ProductoFactura;
import BL.BL_Cliente;
import BL.BL_ProductoFactura;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author oscal
 */
public class Facturar extends javax.swing.JFrame {

    ArrayList<BL_Producto> listaTotalProductos = new ArrayList<BL_Producto>();
    BL_ManejadorProducto manejador = new BL_ManejadorProducto();
    BL_ManejadorCliente manejadorCliente = new BL_ManejadorCliente();
    BL_ManejadorProductoFactura manejadorDetalles = new BL_ManejadorProductoFactura();
    DefaultTableModel modelo;
    DefaultTableModel modeloDetalles;

    /**
     * Creates new form
     */
    public Facturar() {
        initComponents();

        modelo = (DefaultTableModel) tablaInventario.getModel();
        modeloDetalles = (DefaultTableModel) tablaDetalles.getModel();
        jpanBusquedaAvanzada.setVisible(false);

        manejador.CargarProductos();
        manejadorCliente.cargarClientes();
        listaTotalProductos = manejador.ObtenerListaProductos();

        cargarProductosEnTabla(listaTotalProductos);

        ocultarColumnaID();

        // Combo box autoCompletar
        
    }
    
    private void comboBoxAutocompleta(){
        cbx_Cedula.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent evt){
               
                String cadena = cbx_Cedula.getEditor().getItem().toString();;  
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscar(cadena);
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
    
    private void buscar(String cadena) {
        boolean existe = false;
        for (BL_Cliente c : manejadorCliente.obtenerLista()) {
            if (c.getCedula().contentEquals(cadena)) {
                txt_Senor.setText(c.getNombreCompleto());
                txt_Direccion.setText(c.getDireccion());
                existe = true;
            }
        }
        if (!existe) {
            txt_Senor.setText("");
            txt_Direccion.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnBusquedaAvanzada = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jpanBusquedaAvanzada = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_Genero = new javax.swing.JLabel();
        lbl_Talla = new javax.swing.JLabel();
        cbx_Genero = new javax.swing.JComboBox();
        txt_Talla = new javax.swing.JTextField();
        txt_Marca = new javax.swing.JTextField();
        txt_Empresa = new javax.swing.JTextField();
        txt_Precio = new javax.swing.JTextField();
        lbl_Categoria = new javax.swing.JLabel();
        cbx_Categoria = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        cbx_TipoProducto = new javax.swing.JComboBox<String>();
        txt_Fecha = new com.toedter.calendar.JDateChooser();
        txt_color = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btnAgregarDetalle = new javax.swing.JButton();
        btnRegresar2 = new javax.swing.JButton();
        btnRegresar3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_Senor = new javax.swing.JTextField();
        txt_Direccion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_PrecioTotal = new javax.swing.JLabel();
        txt_Cantidad = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_Eliminar = new javax.swing.JButton();
        cbx_Cedula = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                volverAlMenu(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labBuscar.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        labBuscar.setForeground(new java.awt.Color(102, 102, 102));
        labBuscar.setText("Buscar:");
        getContentPane().add(labBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 24, -1, -1));

        txtBuscar.setBackground(new java.awt.Color(237, 237, 237));
        txtBuscar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 24, 312, -1));

        btnBuscar.setBackground(new java.awt.Color(177, 177, 177));
        btnBuscar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(102, 102, 102));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar.png"))); // NOI18N
        btnBuscar.setBorderPainted(false);
        btnBuscar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 6, 44, 40));

        btnBusquedaAvanzada.setBackground(new java.awt.Color(177, 177, 177));
        btnBusquedaAvanzada.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        btnBusquedaAvanzada.setForeground(new java.awt.Color(51, 51, 51));
        btnBusquedaAvanzada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/dropdown.png"))); // NOI18N
        btnBusquedaAvanzada.setText("Busqueda Avanzada");
        btnBusquedaAvanzada.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnBusquedaAvanzada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBusquedaAvanzadaMouseClicked(evt);
            }
        });
        btnBusquedaAvanzada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaAvanzadaActionPerformed(evt);
            }
        });
        getContentPane().add(btnBusquedaAvanzada, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 6, 180, 40));

        tablaInventario.setBackground(new java.awt.Color(237, 237, 237));
        tablaInventario.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaInventario.setForeground(new java.awt.Color(102, 102, 102));
        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Único", "Descripción", "Cantidad", "Fecha Ingreso", "Precio Venta", "HiddenID", "Tipo  Producto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInventario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 187, 708, 208));

        jpanBusquedaAvanzada.setBackground(new java.awt.Color(175, 201, 201));
        jpanBusquedaAvanzada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpanBusquedaAvanzada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Marca:");
        jpanBusquedaAvanzada.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 12, -1, 22));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Precio:");
        jpanBusquedaAvanzada.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, 14));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Empresa: ");
        jpanBusquedaAvanzada.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 54, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Fecha Ingreso:");
        jpanBusquedaAvanzada.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 15, -1, -1));

        lbl_Genero.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        lbl_Genero.setForeground(new java.awt.Color(102, 102, 102));
        lbl_Genero.setText("Género:");
        jpanBusquedaAvanzada.add(lbl_Genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 15, -1, -1));

        lbl_Talla.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        lbl_Talla.setForeground(new java.awt.Color(102, 102, 102));
        lbl_Talla.setText("Talla:");
        jpanBusquedaAvanzada.add(lbl_Talla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        cbx_Genero.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        cbx_Genero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cualquiera", "Hombre", "Mujer" }));
        jpanBusquedaAvanzada.add(cbx_Genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 13, 143, -1));

        txt_Talla.setBackground(new java.awt.Color(237, 237, 237));
        txt_Talla.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txt_Talla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TallaKeyTyped(evt);
            }
        });
        jpanBusquedaAvanzada.add(txt_Talla, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 90, 160, -1));

        txt_Marca.setBackground(new java.awt.Color(237, 237, 237));
        txt_Marca.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jpanBusquedaAvanzada.add(txt_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 13, 180, -1));

        txt_Empresa.setBackground(new java.awt.Color(237, 237, 237));
        txt_Empresa.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jpanBusquedaAvanzada.add(txt_Empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 52, 170, -1));

        txt_Precio.setBackground(new java.awt.Color(237, 237, 237));
        txt_Precio.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txt_Precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PrecioKeyTyped(evt);
            }
        });
        jpanBusquedaAvanzada.add(txt_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 180, -1));

        lbl_Categoria.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        lbl_Categoria.setForeground(new java.awt.Color(102, 102, 102));
        lbl_Categoria.setText("Categoria:");
        jpanBusquedaAvanzada.add(lbl_Categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 54, -1, -1));

        cbx_Categoria.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        cbx_Categoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cualquiera", "Ninos", "Jovenes", "Adulto" }));
        jpanBusquedaAvanzada.add(cbx_Categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 52, 137, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Tipo Producto:");
        jpanBusquedaAvanzada.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        cbx_TipoProducto.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        cbx_TipoProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cualquiera", "Zapato", "Bolso" }));
        cbx_TipoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_TipoProductoActionPerformed(evt);
            }
        });
        jpanBusquedaAvanzada.add(cbx_TipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 110, -1));

        txt_Fecha.setBackground(new java.awt.Color(237, 237, 237));
        jpanBusquedaAvanzada.add(txt_Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(537, 14, 151, -1));

        txt_color.setBackground(new java.awt.Color(237, 237, 237));
        txt_color.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jpanBusquedaAvanzada.add(txt_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 150, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Color:");
        jpanBusquedaAvanzada.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 54, -1, -1));

        getContentPane().add(jpanBusquedaAvanzada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 710, 120));

        jPanel2.setBackground(new java.awt.Color(175, 201, 201));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 11, 10, 570));

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Descripción del detalle", "Cantidad a vender.", "Precio de venta", "HiddenId", "HiddenIdOriginal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaDetalles);
        if (tablaDetalles.getColumnModel().getColumnCount() > 0) {
            tablaDetalles.getColumnModel().getColumn(0).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(1).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(2).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(3).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 144, -1, 284));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("N° Factura:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, -1));

        btnAgregarDetalle.setBackground(new java.awt.Color(177, 177, 177));
        btnAgregarDetalle.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        btnAgregarDetalle.setForeground(new java.awt.Color(51, 51, 51));
        btnAgregarDetalle.setText("Agregar");
        btnAgregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDetalleActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, 80, 40));

        btnRegresar2.setBackground(new java.awt.Color(177, 177, 177));
        btnRegresar2.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        btnRegresar2.setForeground(new java.awt.Color(51, 51, 51));
        btnRegresar2.setText("Atrás");
        btnRegresar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegresar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 541, 70, 40));

        btnRegresar3.setBackground(new java.awt.Color(177, 177, 177));
        btnRegresar3.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        btnRegresar3.setForeground(new java.awt.Color(51, 51, 51));
        btnRegresar3.setText("Facturar");
        btnRegresar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegresar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1122, 541, 90, 40));

        jLabel7.setText("0");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(823, 20, 40, -1));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Señor:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 59, -1, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Dirección:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 97, -1, -1));

        txt_Senor.setBackground(new java.awt.Color(237, 237, 237));
        jPanel2.add(txt_Senor, new org.netbeans.lib.awtextra.AbsoluteConstraints(798, 58, 414, -1));

        txt_Direccion.setBackground(new java.awt.Color(237, 237, 237));
        jPanel2.add(txt_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(818, 96, 394, -1));

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Total:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 489, -1, -1));

        txt_PrecioTotal.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal.setForeground(new java.awt.Color(255, 0, 0));
        txt_PrecioTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal.setText("₡ ");
        jPanel2.add(txt_PrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1092, 481, 120, -1));
        jPanel2.add(txt_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 400, 50, 30));

        jLabel13.setText("Cantidad a Agregar:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, -1, -1));

        jLabel14.setText("Cedula:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 20, -1, 20));

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 440, -1, 30));

        cbx_Cedula.setEditable(true);
        jPanel2.add(cbx_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 20, 120, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverAlMenu(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_volverAlMenu
        new Menu_Principal().setVisible(true);
    }//GEN-LAST:event_volverAlMenu

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String filtro = txtBuscar.getText();
        filtro(filtro);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        boolean tipoProducto = true;
        boolean ambos = false;
        double talla = 0;
        double precio = 0;
        Date fecha = null;
        String genero = cbx_Genero.getSelectedItem().toString();
        String categoria = cbx_Categoria.getSelectedItem().toString();
        if (genero.equals("Cualquiera")) {
            genero = "";
        }
        if (categoria.equals("Cualquiera")) {
            categoria = "";
        }

        if (!txt_Talla.getText().equals("")) {
            talla = Double.parseDouble(txt_Talla.getText());
        }
        if (!txt_Precio.getText().equals("")) {
            precio = Double.parseDouble(txt_Precio.getText());
        }
        if (cbx_TipoProducto.getSelectedItem().toString().equals("Cualquiera")) {
            ambos = true;
        }
        if (cbx_TipoProducto.getSelectedItem().toString().equals("Bolso")) {
            tipoProducto = false;
            genero = "";
            categoria = "";
        }
        if (txt_Fecha.getDate() != null) {
            fecha = new java.sql.Date(txt_Fecha.getDate().getTime());
        }

        BL.BL_ManejadorProducto listaProductos = new BL_ManejadorProducto();

        listaProductos.BuscarPorFiltro(genero, txt_color.getText(), talla, txt_Marca.getText(), txt_Empresa.getText(), precio, (java.sql.Date) txt_Fecha.getDate(), categoria, tipoProducto, ambos);

        limpiarTabla(modelo);

        cargarProductosEnTabla(listaProductos.ObtenerListaProductos());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBusquedaAvanzadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBusquedaAvanzadaMouseClicked
        if (jpanBusquedaAvanzada.isVisible()) {
            jpanBusquedaAvanzada.setVisible(false);
        } else {
            jpanBusquedaAvanzada.setVisible(true);
        }
    }//GEN-LAST:event_btnBusquedaAvanzadaMouseClicked

    private void btnBusquedaAvanzadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaAvanzadaActionPerformed

    }//GEN-LAST:event_btnBusquedaAvanzadaActionPerformed

    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked

    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void txt_TallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TallaKeyTyped
        validarNumeros(evt);
    }//GEN-LAST:event_txt_TallaKeyTyped

    private void txt_PrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PrecioKeyTyped
        validarNumeros(evt);
    }//GEN-LAST:event_txt_PrecioKeyTyped

    private void filtro(String filtro) {
        TableRowSorter<DefaultTableModel> trsFiltro = new TableRowSorter<>(modelo);
        tablaInventario.setRowSorter(trsFiltro);
        trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + filtro));
    }

    private void validarNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && c != '.' && c != evt.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Este campo solo admite valores numericos y ' . '", "Tipo de dato incorrecto", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ocultarColumnaID() {
        /*
        tablaInventario.getColumn("HiddenID").setMaxWidth(0);
        tablaInventario.getColumn("HiddenID").setMinWidth(0);
        tablaInventario.getColumn("HiddenID").setPreferredWidth(0);
        tablaInventario.getColumn("HiddenID").setWidth(0);
        tablaInventario.getColumn("HiddenID").setResizable(false);
*/
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

    private void cargarProductosEnTabla(ArrayList<BL_Producto> listaParaMostrar) {

        limpiarTabla(modelo);
        Object[] fila = new Object[modelo.getColumnCount()];

        for (int i = 0; i < listaParaMostrar.size(); i++) {
            fila[0] = listaParaMostrar.get(i).getCodigoUnico();
            fila[1] = listaParaMostrar.get(i).getDescripcion();
            fila[2] = listaParaMostrar.get(i).getCantidad();
            fila[3] = listaParaMostrar.get(i).getFechaIngreso().toString();
            fila[4] = listaParaMostrar.get(i).getPrecioGanancia();
            fila[5] = i;
            if (listaParaMostrar.get(i).isEsZapato()) {
                fila[6] = "Zapato";
            } else {
                fila[6] = "Bolso";
            }

            modelo.addRow(fila);
        }
        listaTotalProductos = listaParaMostrar;
    }

    private void cargarProductosEnTablaDetalles(ArrayList<BL_ProductoFactura> listaParaMostrar) {

        limpiarTablaDetalles(modeloDetalles);
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
        txt_PrecioTotal.setText("₡ " + totalPagar + "");
        ocultarColumnaDetalles();
        manejadorDetalles.setearLista(listaParaMostrar);
    }

    private void limpiarTabla(DefaultTableModel modelo) {
        int filas = tablaInventario.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    private void limpiarTablaDetalles(DefaultTableModel modelo) {
        int filas = tablaDetalles.getRowCount();
        if (filas > 0) {
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        }
    }

    private void cbx_TipoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_TipoProductoActionPerformed
        if (cbx_TipoProducto.getSelectedItem().toString().equals("Bolso")) {
            txt_Talla.setText("");
            txt_Talla.setEnabled(false);
            cbx_Categoria.setEnabled(false);
            cbx_Genero.setEnabled(false);
            cbx_Categoria.setSelectedItem("Cualquiera");
            cbx_Genero.setSelectedItem("Cualquiera");
        } else {
            txt_Talla.setEnabled(true);
            cbx_Categoria.setEnabled(true);
            cbx_Genero.setEnabled(true);
        }
    }//GEN-LAST:event_cbx_TipoProductoActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        // TODO add your handling code here:
        if (tablaDetalles.getSelectedRow() >= 0) {
            int idDetalle = Integer.parseInt(tablaDetalles.getModel().getValueAt(tablaDetalles.getSelectedRow(), 3).toString());
            int posOriginal = Integer.parseInt(tablaDetalles.getModel().getValueAt(tablaDetalles.getSelectedRow(), 4).toString());
            int cantidadDetalle = Integer.parseInt(tablaDetalles.getModel().getValueAt(tablaDetalles.getSelectedRow(), 1).toString());
            int totalPagar = 0;

            listaTotalProductos.get(posOriginal).setCantidad(listaTotalProductos.get(posOriginal).getCantidad() + cantidadDetalle);
            modeloDetalles.removeRow(tablaDetalles.getSelectedRow());
            ArrayList<BL_ProductoFactura> listaDetalles = manejadorDetalles.ObtenerLista();
            BL_ProductoFactura detalleEliminar = new BL_ProductoFactura();

            for (int i = 0; i < listaDetalles.size(); i++) {
                if (listaDetalles.get(i).getIdProducto() == idDetalle) {
                    detalleEliminar = listaDetalles.get(i);
                    listaDetalles.remove(detalleEliminar);
                    if (listaDetalles.size() > 0) {
                        for (int j = 0; j < listaDetalles.size(); j++) {
                            totalPagar += listaDetalles.get(j).getPrecioVenta();
                        }
                    }else{
                        totalPagar = 0;
                    }
                }
            }

            txt_PrecioTotal.setText("₡ "+ totalPagar + "");
            cargarProductosEnTabla(listaTotalProductos);

        } else {

        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btnRegresar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresar3ActionPerformed

    private void btnRegresar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar2ActionPerformed
        this.dispose();
        new Menu_Principal().setVisible(true);
    }//GEN-LAST:event_btnRegresar2ActionPerformed

    private void btnAgregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDetalleActionPerformed
        if (tablaInventario.getSelectedRow() >= 0) {
            int id = Integer.parseInt(tablaInventario.getValueAt(tablaInventario.getSelectedRow(), 5).toString());
            BL_Producto prod = listaTotalProductos.get(id);
            int cantidad = Integer.parseInt(txt_Cantidad.getValue().toString());
            ArrayList<BL_ProductoFactura> listaDetalles = manejadorDetalles.ObtenerLista();
            boolean existente = false;

            if ((prod.getCantidad() >= cantidad) && (cantidad > 0)) {
                for (int i = 0; i < listaDetalles.size(); i++) {
                    if (listaDetalles.get(i).getIdProducto() == prod.getIdProducto()) {
                        listaDetalles.get(i).setCantidadVendida(listaDetalles.get(i).getCantidadVendida() + cantidad);
                        listaDetalles.get(i).setPrecioVenta(listaDetalles.get(i).getPrecioVenta()
                            + (cantidad * prod.getPrecioGanancia()));
                        manejadorDetalles.setearLista(listaDetalles);
                        existente = true;
                    }
                }
                if (!existente) {

                    BL_ProductoFactura prodDetalle = new BL_ProductoFactura();

                    String descDetalle = prod.getDescripcion() + "\n" + prod.getMarca()
                    + "\n" + prod.getColor() + "";
                    prodDetalle.setDescripcion(descDetalle);
                    prodDetalle.setCantidadVendida(cantidad);
                    prodDetalle.setIdProducto(prod.getIdProducto());
                    prodDetalle.setPrecioVenta(cantidad * prod.getPrecioGanancia());
                    prodDetalle.setPosicionOriginal(id);
                    manejadorDetalles.Agregar(prodDetalle);
                }
                listaTotalProductos.get(id).setCantidad(listaTotalProductos.get(id).getCantidad() - cantidad);
                cargarProductosEnTabla(listaTotalProductos);
                cargarProductosEnTablaDetalles(manejadorDetalles.ObtenerLista());

            } else {
                JOptionPane.showMessageDialog(null, "No existe la cantidad solicitada de productos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarDetalleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarDetalle;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBusquedaAvanzada;
    private javax.swing.JButton btnRegresar2;
    private javax.swing.JButton btnRegresar3;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JComboBox<String> cbx_Categoria;
    private javax.swing.JComboBox<String> cbx_Cedula;
    private javax.swing.JComboBox cbx_Genero;
    private javax.swing.JComboBox<String> cbx_TipoProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpanBusquedaAvanzada;
    private javax.swing.JLabel labBuscar;
    private javax.swing.JLabel lbl_Categoria;
    private javax.swing.JLabel lbl_Genero;
    private javax.swing.JLabel lbl_Talla;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JSpinner txt_Cantidad;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JTextField txt_Empresa;
    private com.toedter.calendar.JDateChooser txt_Fecha;
    private javax.swing.JTextField txt_Marca;
    private javax.swing.JTextField txt_Precio;
    private javax.swing.JLabel txt_PrecioTotal;
    private javax.swing.JTextField txt_Senor;
    private javax.swing.JTextField txt_Talla;
    private javax.swing.JTextField txt_color;
    // End of variables declaration//GEN-END:variables
}
