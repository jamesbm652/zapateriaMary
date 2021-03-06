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
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.JTextComponent;
import BL.BL_Factura;
import BL.BL_TelefonoCliente;
import javax.swing.ImageIcon;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author oscal
 */
public class Facturar extends javax.swing.JFrame {

    ArrayList<BL_Producto> listaTotalProductos = new ArrayList<BL_Producto>();
    BL_ManejadorProducto manejador = new BL_ManejadorProducto();
    BL_ManejadorCliente manejadorCliente = new BL_ManejadorCliente();
    BL_ManejadorProductoFactura manejadorDetalles = new BL_ManejadorProductoFactura();
    BL_Cliente clienteInsertar = new BL_Cliente();
    DefaultTableModel modelo;
    DefaultTableModel modeloDetalles;
    List<Map<String, Integer>> listaParaActualizar = new ArrayList<>();
    int totalPagar;

    /**
     * Creates new form
     */
    public Facturar() {
        initComponents();

        modelo = (DefaultTableModel) tablaInventario.getModel();
        modeloDetalles = (DefaultTableModel) tablaDetalles.getModel();
        tablaInventario.getTableHeader().setDefaultRenderer(new Facturar.HeaderColor());
        tablaDetalles.getTableHeader().setDefaultRenderer(new Facturar.HeaderColor());
        tablaInventario.getTableHeader().setReorderingAllowed(false);
        tablaDetalles.getTableHeader().setReorderingAllowed(false);
        
        jpanBusquedaAvanzada.setVisible(false);
        SpinnerNumberModel spn = new SpinnerNumberModel(1, 1, 100, 1);
        txt_Cantidad.setModel(spn);
        JFormattedTextField tf = ((JSpinner.DefaultEditor) txt_Cantidad.getEditor()).getTextField();
        tf.setEditable(false);
        tipo1.setSelected(true);

        manejador.CargarProductos();
        manejadorCliente.cargarClientes();
        listaTotalProductos = manejador.ObtenerListaProductos();

        cargarProductosEnTabla(listaTotalProductos);

        ocultarColumnaID();
        ocultarColumnaDetalles();

        // Combo box autoCompletar
        comboBoxAutocompletaCedula();
        comboBoxAutocompletaNombre();
        jLabel7.setText(new BL_Factura().siguienteNumFactura() + "");
    }
    
    private void comboBoxAutocompletaCedula(){
 
        cbx_Cedula.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evt){
                String cadena = cbx_Cedula.getEditor().getItem().toString();;  
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarPorCedula(cadena);
                }
                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <=90 || evt.getKeyCode() >=97 && evt.getKeyCode() <= 122 || evt.getKeyCode() == 45 || evt.getKeyCode() >= 48 && evt.getKeyCode() <=57 || evt.getKeyCode() == 45 || evt.getKeyCode() == 8) {
                    cbx_Cedula.setModel(manejadorCliente.obtenerListaComboBox(cadena, "Cedula"));

                    if (cbx_Cedula.getItemCount() > 0) {
                        cbx_Cedula.showPopup();
                        if (evt.getKeyCode() != 8) {
                            ((JTextComponent) cbx_Cedula.getEditor().getEditorComponent()).select(cadena.length(),
                                    cbx_Cedula.getEditor().getItem().toString().length());
                        } else {
                            cbx_Cedula.getEditor().setItem(cadena);
                        }
                    } else {
                        cbx_Cedula.addItem(cadena);
                        int fin = cbx_Cedula.getEditor().getItem().toString().length();
                        ((JTextComponent) cbx_Cedula.getEditor().getEditorComponent()).select(fin, fin);
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    int fin = cbx_Cedula.getEditor().getItem().toString().length();
                    ((JTextComponent) cbx_Cedula.getEditor().getEditorComponent()).select(fin, fin);
                }
                // Metodo para permitir o no la modificacion de los datos
                //permisoParaEscribir(habilitar);
            }
        });
    }
    
    private void comboBoxAutocompletaNombre(){
        cbx_Senor.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent evt){
                String cadena = cbx_Senor.getEditor().getItem().toString();;  
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarPorNombreCompleto(cadena);
                }
                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <=90 || evt.getKeyCode() >=97 && evt.getKeyCode() <= 122 || evt.getKeyCode() == 45 || evt.getKeyCode() == 8) {
                    cbx_Senor.setModel(manejadorCliente.obtenerListaComboBox(cadena, "NombreCompleto"));
                    if (cbx_Senor.getItemCount() > 0) {
                        cbx_Senor.showPopup();
                        if (evt.getKeyCode() != 8) {
                            ((JTextComponent)cbx_Senor.getEditor().getEditorComponent()).select(cadena.length(), 
                                    cbx_Senor.getEditor().getItem().toString().length());
                        }else{
                            cbx_Senor.getEditor().setItem(cadena);
                        }
                    }else{
                        cbx_Senor.addItem(cadena);
                        int fin = cbx_Senor.getEditor().getItem().toString().length();
                        ((JTextComponent)cbx_Senor.getEditor().getEditorComponent()).select(fin, fin);
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    int fin = cbx_Senor.getEditor().getItem().toString().length();
                    ((JTextComponent)cbx_Senor.getEditor().getEditorComponent()).select(fin, fin);
                }
                // Metodo para permitir o no la modificacion de los datos
                //permisoParaEscribir(habilitar);
            }
        });
    }

    private boolean buscarPorNombreCompleto(String cadena){
        boolean existeNombre = false;
        boolean cedCoincide = false;
        String ced = cbx_Cedula.getEditor().getItem().toString();
        for (BL_Cliente c : manejadorCliente.obtenerLista()) {
            if (c.getNombreCompleto().contentEquals(cadena)) {
                cbx_Cedula.getEditor().setItem(c.getCedula());
                txt_Direccion.setText(c.getDireccion());
                // Validar y setear telefonos
                if (c.getListaTelefonos().size() == 1) {
                    if (c.getListaTelefonos().get(0).getTipoTelefono().equals("Habitacion")) {
                        telHabitacion.setText(c.getListaTelefonos().get(0).getTelefono());
                        telCelular.setText("");
                    }else{
                        telCelular.setText(c.getListaTelefonos().get(0).getTelefono());
                        telHabitacion.setText("");
                    }
                }
                if (c.getListaTelefonos().size() == 2) {
                    if (c.getListaTelefonos().get(0).getTipoTelefono().equals("Habitacion")) {
                        telHabitacion.setText(c.getListaTelefonos().get(0).getTelefono());
                        telCelular.setText(c.getListaTelefonos().get(1).getTelefono());
                    }else{
                        telHabitacion.setText(c.getListaTelefonos().get(1).getTelefono());
                        telCelular.setText(c.getListaTelefonos().get(0).getTelefono());
                    }
                }
                existeNombre = true;
            }
            if (c.getCedula().contentEquals(ced)) {
                cedCoincide = true;
            }
        }
        if (!existeNombre && cedCoincide) {
            cbx_Cedula.getEditor().setItem("");
            txt_Direccion.setText("");
            telCelular.setText("");
            telHabitacion.setText("");
        }
        if (existeNombre || cedCoincide) return true;
        return false;
    }
  
    private boolean buscarPorCedula(String cadena) {
        boolean existe = false;
        boolean nomCoincide = false;
        String nombre = cbx_Senor.getEditor().getItem().toString();
        for (BL_Cliente c : manejadorCliente.obtenerLista()) {
            if (c.getCedula().contentEquals(cadena)) {
                cbx_Senor.getEditor().setItem(c.getNombreCompleto());
                txt_Direccion.setText(c.getDireccion());
                // Validar y setear telefonos
                if (c.getListaTelefonos().size() == 1) {
                    if (c.getListaTelefonos().get(0).getTipoTelefono().equals("Habitacion")) {
                        telHabitacion.setText(c.getListaTelefonos().get(0).getTelefono());
                        telCelular.setText("");
                    }else{
                        telCelular.setText(c.getListaTelefonos().get(0).getTelefono());
                        telHabitacion.setText("");
                    }
                }
                if (c.getListaTelefonos().size() == 2) {
                    if (c.getListaTelefonos().get(0).getTipoTelefono().equals("Habitacion")) {
                        telHabitacion.setText(c.getListaTelefonos().get(0).getTelefono());
                        telCelular.setText(c.getListaTelefonos().get(1).getTelefono());
                    }else{
                        telHabitacion.setText(c.getListaTelefonos().get(1).getTelefono());
                        telCelular.setText(c.getListaTelefonos().get(0).getTelefono());
                    }
                }
                existe = true;
            }
            if (c.getNombreCompleto().contentEquals(nombre)) {
                nomCoincide =  true;
            }
        }
        if (!existe && nomCoincide) {
            cbx_Senor.getEditor().setItem("");
            txt_Direccion.setText("");
            telCelular.setText("");
            telHabitacion.setText("");
        }
        if (existe || nomCoincide) return true;
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupTipoFactura = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_Direccion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_PrecioTotal = new javax.swing.JLabel();
        txt_Cantidad = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbx_Cedula = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jpanBusquedaAvanzada = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_Genero1 = new javax.swing.JLabel();
        lbl_Talla1 = new javax.swing.JLabel();
        cbx_Genero = new javax.swing.JComboBox();
        txt_Talla = new javax.swing.JTextField();
        txt_Marca = new javax.swing.JTextField();
        txt_Empresa = new javax.swing.JTextField();
        lbl_Categoria1 = new javax.swing.JLabel();
        cbx_Categoria = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbx_TipoProducto = new javax.swing.JComboBox<>();
        txt_Fecha = new com.toedter.calendar.JDateChooser();
        txt_color = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        labBuscarAvanzada = new javax.swing.JLabel();
        txt_Precio = new javax.swing.JSpinner();
        labDropdown = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtBuscar = new javax.swing.JTextField();
        labBuscar1 = new javax.swing.JLabel();
        btnPanelAgregar = new javax.swing.JPanel();
        labBtnAgregar = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        btnPanelEliminar = new javax.swing.JPanel();
        labBtnEliminar = new javax.swing.JLabel();
        btnPanelFacturar = new javax.swing.JPanel();
        labBtnFacturar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tipo1 = new javax.swing.JRadioButton();
        tipo4 = new javax.swing.JRadioButton();
        tipo3 = new javax.swing.JRadioButton();
        tipo2 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        telHabitacion = new javax.swing.JTextField();
        telCelular = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        cbx_Senor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 10, 640));

        tablaDetalles.setBackground(new java.awt.Color(232, 232, 232));
        tablaDetalles.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
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
        if (tablaDetalles.getColumnModel().getColumnCount() > 0) {
            tablaDetalles.getColumnModel().getColumn(0).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(1).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(2).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(3).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 550, 340));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("N° Factura:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 30, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("0");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 30, 90, 30));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Señor(a):");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, -1, 30));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Dirección:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        txt_Direccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txt_Direccion.setForeground(new java.awt.Color(51, 51, 51));
        txt_Direccion.setBorder(null);
        jPanel2.add(txt_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 190, 420, 20));

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Total:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 630, -1, -1));

        txt_PrecioTotal.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal.setForeground(new java.awt.Color(255, 0, 0));
        txt_PrecioTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal.setText("₡ ");
        jPanel2.add(txt_PrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 630, 120, -1));

        txt_Cantidad.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jPanel2.add(txt_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 630, 50, 30));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Cantidad a agregar:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 630, -1, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Cedula:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, 20));

        cbx_Cedula.setEditable(true);
        cbx_Cedula.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cbx_Cedula.setForeground(new java.awt.Color(51, 51, 51));
        cbx_Cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_CedulaActionPerformed(evt);
            }
        });
        jPanel2.add(cbx_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 120, 130, -1));

        tablaInventario.setBackground(new java.awt.Color(232, 232, 232));
        tablaInventario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        tablaInventario.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        tablaInventario.setForeground(new java.awt.Color(51, 51, 51));
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
        tablaInventario.setGridColor(new java.awt.Color(153, 153, 153));
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInventario);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 708, 350));

        jpanBusquedaAvanzada.setBackground(new java.awt.Color(255, 255, 255));
        jpanBusquedaAvanzada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpanBusquedaAvanzada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Marca:");
        jpanBusquedaAvanzada.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, 22));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Precio:");
        jpanBusquedaAvanzada.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Empresa: ");
        jpanBusquedaAvanzada.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Fecha Ingreso:");
        jpanBusquedaAvanzada.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        lbl_Genero1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lbl_Genero1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_Genero1.setText("Género:");
        jpanBusquedaAvanzada.add(lbl_Genero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        lbl_Talla1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lbl_Talla1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_Talla1.setText("Talla:");
        jpanBusquedaAvanzada.add(lbl_Talla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        cbx_Genero.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        cbx_Genero.setForeground(new java.awt.Color(51, 51, 51));
        cbx_Genero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cualquiera", "Hombre", "Mujer" }));
        jpanBusquedaAvanzada.add(cbx_Genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 90, -1));

        txt_Talla.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txt_Talla.setForeground(new java.awt.Color(51, 51, 51));
        txt_Talla.setBorder(null);
        txt_Talla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TallaKeyTyped(evt);
            }
        });
        jpanBusquedaAvanzada.add(txt_Talla, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 160, 20));

        txt_Marca.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txt_Marca.setForeground(new java.awt.Color(51, 51, 51));
        txt_Marca.setBorder(null);
        jpanBusquedaAvanzada.add(txt_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, 20));

        txt_Empresa.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txt_Empresa.setForeground(new java.awt.Color(51, 51, 51));
        txt_Empresa.setBorder(null);
        jpanBusquedaAvanzada.add(txt_Empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 140, 20));

        lbl_Categoria1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lbl_Categoria1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_Categoria1.setText("Categoría:");
        jpanBusquedaAvanzada.add(lbl_Categoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        cbx_Categoria.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        cbx_Categoria.setForeground(new java.awt.Color(51, 51, 51));
        cbx_Categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Ninos", "Jovenes", "Adulto" }));
        jpanBusquedaAvanzada.add(cbx_Categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 90, -1));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Tipo:");
        jpanBusquedaAvanzada.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbx_TipoProducto.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        cbx_TipoProducto.setForeground(new java.awt.Color(51, 51, 51));
        cbx_TipoProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Zapato", "Bolso" }));
        cbx_TipoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_TipoProductoActionPerformed(evt);
            }
        });
        jpanBusquedaAvanzada.add(cbx_TipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 90, -1));

        txt_Fecha.setBackground(new java.awt.Color(237, 237, 237));
        txt_Fecha.setForeground(new java.awt.Color(51, 51, 51));
        jpanBusquedaAvanzada.add(txt_Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 160, -1));

        txt_color.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txt_color.setForeground(new java.awt.Color(51, 51, 51));
        txt_color.setBorder(null);
        txt_color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_colorActionPerformed(evt);
            }
        });
        jpanBusquedaAvanzada.add(txt_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 140, 20));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Color:");
        jpanBusquedaAvanzada.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, -1));
        jpanBusquedaAvanzada.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 160, 10));
        jpanBusquedaAvanzada.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 140, -1));
        jpanBusquedaAvanzada.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 140, 10));
        jpanBusquedaAvanzada.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 140, 10));

        labBuscarAvanzada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar.png"))); // NOI18N
        labBuscarAvanzada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labBuscarAvanzadaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labBuscarAvanzadaMouseEntered(evt);
            }
        });
        jpanBusquedaAvanzada.add(labBuscarAvanzada, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, -1, -1));

        txt_Precio.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txt_Precio.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        jpanBusquedaAvanzada.add(txt_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 160, -1));

        jPanel2.add(jpanBusquedaAvanzada, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 710, 120));

        labDropdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/dropdown.png"))); // NOI18N
        labDropdown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labDropdownMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labDropdownMouseEntered(evt);
            }
        });
        jPanel2.add(labDropdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Filtrar por:");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, -1, -1));

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 300, 10));

        txtBuscar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 300, 20));

        labBuscar1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labBuscar1.setForeground(new java.awt.Color(102, 102, 102));
        labBuscar1.setText("Buscar:");
        jPanel2.add(labBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        btnPanelAgregar.setBackground(new java.awt.Color(0, 93, 107));
        btnPanelAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPanelAgregarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPanelAgregarMouseEntered(evt);
            }
        });
        btnPanelAgregar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labBtnAgregar.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        labBtnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        labBtnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/plus.png"))); // NOI18N
        labBtnAgregar.setText("Agregar");
        labBtnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labBtnAgregarMouseClicked(evt);
            }
        });
        btnPanelAgregar.add(labBtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 80, 20));

        jPanel2.add(btnPanelAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 620, 150, 40));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 210, 420, 10));

        btnPanelEliminar.setBackground(new java.awt.Color(0, 93, 107));
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

        labBtnEliminar.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        labBtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        labBtnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/delete.png"))); // NOI18N
        labBtnEliminar.setText("Eliminar");
        labBtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labBtnEliminarMouseClicked(evt);
            }
        });
        btnPanelEliminar.add(labBtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, -1));

        jPanel2.add(btnPanelEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 620, 150, 40));

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

        labBtnFacturar.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        labBtnFacturar.setForeground(new java.awt.Color(255, 255, 255));
        labBtnFacturar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBtnFacturar.setText("Facturar");
        labBtnFacturar.setToolTipText("");
        labBtnFacturar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labBtnFacturarMouseClicked(evt);
            }
        });
        btnPanelFacturar.add(labBtnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 60, 40));

        jPanel2.add(btnPanelFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 620, 150, 40));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Tipo factura:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        tipo1.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupTipoFactura.add(tipo1);
        tipo1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        tipo1.setForeground(new java.awt.Color(51, 51, 51));
        tipo1.setText("Contado");
        jPanel2.add(tipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 80, -1, -1));

        tipo4.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupTipoFactura.add(tipo4);
        tipo4.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        tipo4.setForeground(new java.awt.Color(51, 51, 51));
        tipo4.setText("Crédito");
        jPanel2.add(tipo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 80, -1, -1));

        tipo3.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupTipoFactura.add(tipo3);
        tipo3.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        tipo3.setForeground(new java.awt.Color(51, 51, 51));
        tipo3.setText("Apartado");
        jPanel2.add(tipo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 80, -1, -1));

        tipo2.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupTipoFactura.add(tipo2);
        tipo2.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        tipo2.setForeground(new java.awt.Color(51, 51, 51));
        tipo2.setText("Tarjeta");
        jPanel2.add(tipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Teléfono cel:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 230, -1, -1));

        telHabitacion.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        telHabitacion.setForeground(new java.awt.Color(51, 51, 51));
        telHabitacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(telHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 230, 130, 20));

        telCelular.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        telCelular.setForeground(new java.awt.Color(51, 51, 51));
        telCelular.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(telCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 230, 140, 20));
        jPanel2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 250, 140, 10));
        jPanel2.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 250, 130, 10));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Teléfono hab:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, 120, -1));

        cbx_Senor.setEditable(true);
        cbx_Senor.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cbx_Senor.setForeground(new java.awt.Color(51, 51, 51));
        cbx_Senor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_SenorActionPerformed(evt);
            }
        });
        jPanel2.add(cbx_Senor, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 160, 420, 20));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Facturación");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, -1, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 28)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Productos del inventario");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

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
        jPanel4.add(labClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 10, -1, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked

    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void filtro(String filtro) {
        try {
            TableRowSorter<DefaultTableModel> trsFiltro = new TableRowSorter<>(modelo);
            tablaInventario.setRowSorter(trsFiltro);
            trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + filtro));
        } catch (PatternSyntaxException e) {
            txtBuscar.setText(filtro.substring(0, filtro.length() - 1));
            JOptionPane.showMessageDialog(null, "Ha digitado un caracter inválido.", "Caracter inválido",JOptionPane.ERROR_MESSAGE);
        }

        //Validacion para que actualice la cantidad en la lista de productos
        if (listaParaActualizar.size() > 0) {
            for (int i = 0; i < tablaInventario.getRowCount(); i++) {
                int hidden = Integer.parseInt(tablaInventario.getValueAt(i, 5).toString());
                for (int j = 0; j < listaParaActualizar.size(); j++) {
                    if (hidden == listaParaActualizar.get(j).get("pos")) {
                        tablaInventario.setValueAt(listaParaActualizar.get(j).get("cant"), i, 2);
                    }
                }
            }
        }
    }

    private void validarNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && c != '.' && c != evt.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Este campo solo admite valores numericos y ' . '", "Tipo de dato incorrecto", JOptionPane.WARNING_MESSAGE,new ImageIcon("src/recursos/warning.png"));
        }
    }

    private void ocultarColumnaID() {

        tablaInventario.getColumn("HiddenID").setMaxWidth(0);
        tablaInventario.getColumn("HiddenID").setMinWidth(0);
        tablaInventario.getColumn("HiddenID").setPreferredWidth(0);
        tablaInventario.getColumn("HiddenID").setWidth(0);
        tablaInventario.getColumn("HiddenID").setResizable(false);

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
            fila[4] = listaParaMostrar.get(i).getPrecioVenta();
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
        totalPagar = 0;

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

    private void txt_TallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TallaKeyTyped
        validarNumeros(evt);
    }//GEN-LAST:event_txt_TallaKeyTyped

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

    private void labBuscarAvanzadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labBuscarAvanzadaMouseClicked
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
        if (!txt_Precio.getValue().equals("0")) {
            precio = Double.parseDouble(txt_Precio.getValue().toString());
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

        if(talla == 0 || (talla >= 19 && talla <= 46)){
            BL.BL_ManejadorProducto listaProductos = new BL_ManejadorProducto();

            listaProductos.BuscarPorFiltro(genero, txt_color.getText(), talla, txt_Marca.getText(), txt_Empresa.getText(), precio, fecha, categoria, tipoProducto, ambos);

            limpiarTabla(modelo);

            cargarProductosEnTabla(listaProductos.ObtenerListaProductos());
        }else{
            JOptionPane.showMessageDialog(null, "El rango de la talla es incorrecto.", "Rango incorrecto", JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
        }
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

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String filtro = txtBuscar.getText();
        filtro(filtro);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txt_colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_colorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_colorActionPerformed

    private void labCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseClicked
        this.dispose();
        new Menu_Principal().setVisible(true);
    }//GEN-LAST:event_labCloseMouseClicked

    private void labCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labCloseMouseEntered

    private void btnPanelAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAgregarMouseEntered
        btnPanelAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelAgregarMouseEntered

    private void btnPanelAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAgregarMouseClicked
        agregarProductosAFactura();
    }//GEN-LAST:event_btnPanelAgregarMouseClicked

    private void agregarProductosAFactura(){
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
                                + (cantidad * prod.getPrecioVenta()));
                        manejadorDetalles.setearLista(listaDetalles);
                        existente = true;
                    }
                }
                if (!existente) {

                    BL_ProductoFactura prodDetalle = new BL_ProductoFactura();

                    String descDetalle = prod.getDescripcion();
                    prodDetalle.setDescripcion(descDetalle);
                    prodDetalle.setCantidadVendida(cantidad);
                    prodDetalle.setIdProducto(prod.getIdProducto());
                    prodDetalle.setPrecioVenta(cantidad * prod.getPrecioVenta());
                    prodDetalle.setPosicionOriginal(id);
                    manejadorDetalles.Agregar(prodDetalle);
                }
                int nuevaCantidad = listaTotalProductos.get(id).getCantidad() - cantidad;
                listaTotalProductos.get(id).setCantidad(nuevaCantidad);
                tablaInventario.setValueAt(nuevaCantidad, tablaInventario.getSelectedRow(), 2);
                cargarProductosEnTablaDetalles(manejadorDetalles.ObtenerLista());

            } else {
                JOptionPane.showMessageDialog(null, "No existe la cantidad solicitada de productos.", "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto.", "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
        }
    }
    
    private void btnPanelEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelEliminarMouseClicked
        eliminarProductoFactura();
    }//GEN-LAST:event_btnPanelEliminarMouseClicked

    private void eliminarProductoFactura(){
        if (tablaDetalles.getSelectedRow() >= 0) {
            int idDetalle = Integer.parseInt(tablaDetalles.getModel().getValueAt(tablaDetalles.getSelectedRow(), 3).toString());
            int posOriginal = Integer.parseInt(tablaDetalles.getModel().getValueAt(tablaDetalles.getSelectedRow(), 4).toString());
            int cantidadDetalle = Integer.parseInt(tablaDetalles.getModel().getValueAt(tablaDetalles.getSelectedRow(), 1).toString());
            int nuevaCantidad = listaTotalProductos.get(posOriginal).getCantidad() + cantidadDetalle;
            totalPagar = 0;

            //Necesario para actualizar la cantidad en la tabla de inventario
            Map<String, Integer> map = new HashMap<>();
            map.put("pos", posOriginal);
            map.put("cant", nuevaCantidad);
            
            listaParaActualizar.add(map);

            listaTotalProductos.get(posOriginal).setCantidad(nuevaCantidad);
            for (int i = 0; i < tablaInventario.getRowCount(); i++) {
                int hidden = Integer.parseInt(tablaInventario.getValueAt(i, 5).toString());
                if (hidden == posOriginal) {
                    tablaInventario.setValueAt(nuevaCantidad, i, 2);
                }
            }

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
                    } else {
                        totalPagar = 0;
                    }
                }
            }
            txt_PrecioTotal.setText("₡ " + totalPagar + "");
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto.", "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
        }
    }
    
    private void btnPanelEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelEliminarMouseEntered
        btnPanelEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelEliminarMouseEntered

    private void btnPanelFacturarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelFacturarMouseClicked
        facturar();
    }//GEN-LAST:event_btnPanelFacturarMouseClicked

    private void facturar(){
        String cedula = cbx_Cedula.getEditor().getItem().toString();
        String comprador = cbx_Senor.getEditor().getItem().toString();
        String direccion = txt_Direccion.getText();
        String tipoFactura = "";
        Date fechaFactura = new Date(new java.util.Date().getTime());
        String telefHabitacion = "";
        String telefCelular = "";
        if (!telHabitacion.getText().equals("")) {
            telefHabitacion = telHabitacion.getText();
        }
        if (!telCelular.getText().equals("")) {
            telefCelular = telCelular.getText();
        }
        if (tipo1.isSelected()) {
            tipoFactura = tipo1.getText();
        }
        if (tipo2.isSelected()) {
            tipoFactura = tipo2.getText();
        }
        if (tipo3.isSelected()) {
            tipoFactura = tipo3.getText();
        }
        if (tipo4.isSelected()) {
            tipoFactura = tipo4.getText();
        }

        clienteInsertar.setNombreCompleto(comprador);
        clienteInsertar.setCedula(cedula);
        clienteInsertar.setDireccion(direccion);
        
        for (int i = 0; i < clienteInsertar.getListaTelefonos().size(); i++) {
            clienteInsertar.getListaTelefonos().remove(i);
        }
        if (!telefHabitacion.equals("")) {
            clienteInsertar.getListaTelefonos().add(new BL_TelefonoCliente(telefHabitacion, "Habitacion"));
        }
        if (!telefCelular.equals("")) {
            clienteInsertar.getListaTelefonos().add(new BL_TelefonoCliente(telefCelular, "Celular"));
        }

        if (tipoFactura.equals("Apartado") && (telefHabitacion.equals("") && telefCelular.equals(""))) {
            JOptionPane.showMessageDialog(this, "Para el tipo de factura Apartado,\ndebe de introducir al menos un teléfono.");
        } else if (tipoFactura.equals("Crédito") && ((telefHabitacion.equals("") && !telefCelular.equals("")) || (!telefHabitacion.equals("") && telefCelular.equals("")) || (telefHabitacion.equals("") && telefCelular.equals("")))) {
            JOptionPane.showMessageDialog(this, "Para el tipo de factura Crédito,\ndebe de introducir los dos teléfonos.","Datos incompletos",JOptionPane.WARNING_MESSAGE,new ImageIcon("src/recursos/warning.png"));
        } else {
            if (!tipoFactura.equals("") && !comprador.equals("") && !direccion.equals("")
                    && !cedula.equals("") && manejadorDetalles.ObtenerLista().size() > 0) {
                BL_Factura fact = new BL_Factura();
                fact.setFechaFactura(fechaFactura);
                fact.setCliente(clienteInsertar);
                fact.setTipoFactura(tipoFactura);
                fact.setProductosFactura(manejadorDetalles.ObtenerLista());
                new ConfirmarFacturacion(fact, this).setVisible(true);
                this.setEnabled(false);
                
            } else if ((tipoFactura.equals("") || comprador.equals("") || direccion.equals("")
                    || cedula.equals("")) && manejadorDetalles.ObtenerLista().size() == 0) {
                JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos necesarios y productos a la factura.", "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
            } else if (tipoFactura.equals("") || comprador.equals("") || direccion.equals("")
                    || cedula.equals("") && manejadorDetalles.ObtenerLista().size() > 0) {
                JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos necesarios "
                        + "del cliente.", "Datos incompletos", JOptionPane.WARNING_MESSAGE,new ImageIcon("src/recursos/warning.png"));
            } else {
                if (!tipoFactura.equals("") && !comprador.equals("") && !direccion.equals("")
                        && !cedula.equals("") && manejadorDetalles.ObtenerLista().size() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar productos a la factura.",
                             "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
                }
            }
        }
    }
    
    private void btnPanelFacturarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelFacturarMouseEntered
        btnPanelFacturar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelFacturarMouseEntered

    private void cbx_CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_CedulaActionPerformed
        String cadena = cbx_Cedula.getEditor().getItem().toString();
        buscarPorCedula(cadena);
    }//GEN-LAST:event_cbx_CedulaActionPerformed

    private void cbx_SenorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_SenorActionPerformed
        String cadena = cbx_Senor.getEditor().getItem().toString();
        buscarPorNombreCompleto(cadena);
    }//GEN-LAST:event_cbx_SenorActionPerformed

    private void labBtnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labBtnAgregarMouseClicked
        agregarProductosAFactura();
    }//GEN-LAST:event_labBtnAgregarMouseClicked

    private void labBtnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labBtnEliminarMouseClicked
        eliminarProductoFactura();
    }//GEN-LAST:event_labBtnEliminarMouseClicked

    private void labBtnFacturarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labBtnFacturarMouseClicked
        facturar();
    }//GEN-LAST:event_labBtnFacturarMouseClicked

    static public class HeaderColor extends DefaultTableCellRenderer{
        public HeaderColor(){
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable tabla, Object value, boolean selected, boolean fused, int row, int column) {
            super.getTableCellRendererComponent(tabla, value, selected, fused, row, column);

            setBorder(new LineBorder(Color.BLACK, 1));
            setForeground(Color.WHITE);
            setBackground(new java.awt.Color(0,105,120));
            setHorizontalAlignment((int) tabla.CENTER_ALIGNMENT);
            return this;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupTipoFactura;
    private javax.swing.JPanel btnPanelAgregar;
    private javax.swing.JPanel btnPanelEliminar;
    private javax.swing.JPanel btnPanelFacturar;
    private javax.swing.JComboBox<String> cbx_Categoria;
    private javax.swing.JComboBox<String> cbx_Cedula;
    private javax.swing.JComboBox cbx_Genero;
    private javax.swing.JComboBox<String> cbx_Senor;
    private javax.swing.JComboBox<String> cbx_TipoProducto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jpanBusquedaAvanzada;
    private javax.swing.JLabel labBtnAgregar;
    private javax.swing.JLabel labBtnEliminar;
    private javax.swing.JLabel labBtnFacturar;
    private javax.swing.JLabel labBuscar1;
    private javax.swing.JLabel labBuscarAvanzada;
    private javax.swing.JLabel labClose;
    private javax.swing.JLabel labDropdown;
    private javax.swing.JLabel lbl_Categoria1;
    private javax.swing.JLabel lbl_Genero1;
    private javax.swing.JLabel lbl_Talla1;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTextField telCelular;
    private javax.swing.JTextField telHabitacion;
    private javax.swing.JRadioButton tipo1;
    private javax.swing.JRadioButton tipo2;
    private javax.swing.JRadioButton tipo3;
    private javax.swing.JRadioButton tipo4;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JSpinner txt_Cantidad;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JTextField txt_Empresa;
    private com.toedter.calendar.JDateChooser txt_Fecha;
    private javax.swing.JTextField txt_Marca;
    private javax.swing.JSpinner txt_Precio;
    private javax.swing.JLabel txt_PrecioTotal;
    private javax.swing.JTextField txt_Talla;
    private javax.swing.JTextField txt_color;
    // End of variables declaration//GEN-END:variables
}
