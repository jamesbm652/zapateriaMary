/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.BL_Producto;
import BL.BL_TallaZapato;
import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author oscal
 */
public class DetalleProducto extends javax.swing.JFrame {

    ArrayList<BL_Producto> lista;
    int posicion;

    /**
     * Creates new form AgregarProducto
     */
    public DetalleProducto() {
        initComponents();
    }

    public DetalleProducto(int ventana, ArrayList<BL_Producto> listaProductos, int id) {
        initComponents();
        lista = listaProductos;
        this.posicion = id;
        verificarVentana(ventana);
        calcularPrecioDeVenta();
    }
    
    private void calcularPrecioDeVenta(){
        double precioCosto = Double.parseDouble(txtPrecioCosto.getValue().toString());
        double impuesto = Double.parseDouble(txtImpuesto.getValue().toString());
        double ganancia = Double.parseDouble(txtGanancia.getValue().toString());
        double precioVenta = precioCosto + (precioCosto*(impuesto/100)) + (precioCosto*(ganancia/100));
      
        txtPrecioVenta.setText(precioVenta+"");

    }
    
    private void verificarVentana(int ventana) {
        if (ventana == 1) {
            habilitarCampos(true);
            labInstruccion.setText("Ingrese los datos del nuevo producto:");
            rdbZapato.setSelected(true);
            labBtnAgregar.setText("Agregar");
            btnPanelAgregar.setVisible(true);
            btnGenerarCodigo.setVisible(true);
        } else if (ventana == 2) {
            labInstruccion.setText("Modifique los datos necesarios:");
            btnPanelAgregar.setVisible(true);
            labBtnAgregar.setText("Modificar");
            btnGenerarCodigo.setVisible(false);

            habilitarCampos(true);
            
            cmbGenero.setEnabled(false);
            cmbCategoria.setEnabled(false);
            txtTalla.setEnabled(false);
            txtTalla.setText("");
            cargarProducto();
        } else {
            labInstruccion.setText("Detalles del producto:");
            btnPanelAgregar.setVisible(false);
            btnGenerarCodigo.setVisible(false);
            habilitarCampos(false);
            cargarProducto();
        }
    }

    private void habilitarCampos(boolean valor) {
        txtColor.setEditable(valor); 
        txtColor.setBackground(Color.WHITE);
        
        txtDescripcion.setEditable(valor);
        txtDescripcion.setBackground(Color.WHITE);
        
        txtFechaIngreso.setEnabled(valor);
        txtFechaIngreso.setBackground(Color.WHITE);
        
        cmbGenero.setEnabled(valor);
        cmbCategoria.setEnabled(valor);
        txtTalla.setEditable(valor);
        txtTalla.setBackground(Color.WHITE);
        
        txtImpuesto.setEnabled(valor);
        txtImpuesto.setBackground(Color.WHITE);
        
        txtGanancia.setEnabled(valor);
        txtGanancia.setBackground(Color.WHITE);
        
        txtEmpresa.setEditable(valor);
        txtEmpresa.setBackground(Color.WHITE);
        
        txtImpuesto.setEnabled(valor);
        txtImpuesto.setBackground(Color.WHITE);
        
        txtMarca.setEditable(valor);
        txtMarca.setBackground(Color.WHITE);
        
        txtPrecioCosto.setEnabled(valor);
        txtPrecioCosto.setBackground(Color.WHITE);
        
        txtPrecioVenta.setEnabled(valor);
        txtPrecioVenta.setBackground(Color.WHITE);
        
        rdbBolso.setEnabled(valor);
        rdbZapato.setEnabled(valor);
        
    }

    private void cargarProducto() {
        if (lista.get(posicion).isEsZapato()) {
            rdbZapato.setSelected(true);

            cmbCategoria.setSelectedItem(lista.get(posicion).getTallaZapato().getCategoriaZapato());
            if (lista.get(posicion).getTallaZapato().getGeneroZapato().equals("1")) {
                cmbGenero.setSelectedItem("M");
            } else {
                cmbGenero.setSelectedItem("F");
            }

            txtTalla.setText(lista.get(posicion).getTallaZapato().getTalla() + "");
        } else {
            rdbBolso.setSelected(true);

        }
        txtCodigo.setText(lista.get(posicion).getCodigoUnico());
        txtFechaIngreso.setDate(lista.get(posicion).getFechaIngreso());
        txtDescripcion.setText(lista.get(posicion).getDescripcion());
        txtImpuesto.setValue(lista.get(posicion).getCantidad());
        txtMarca.setText(lista.get(posicion).getMarca());
        txtColor.setText(lista.get(posicion).getColor());
        txtCantidad.setValue(lista.get(posicion).getCantidad());
        txtEmpresa.setText(lista.get(posicion).getEmpresa());
        txtImpuesto.setValue(lista.get(posicion).getPrecioImpuesto());
        txtPrecioCosto.setValue(lista.get(posicion).getPrecioCosto());
        txtPrecioVenta.setText(lista.get(posicion).getPrecioVenta()+ "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        labInstruccion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JTextField();
        rdbBolso = new javax.swing.JRadioButton();
        txtFechaIngreso = new com.toedter.calendar.JDateChooser();
        rdbZapato = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        txtTalla = new javax.swing.JTextField();
        txtImpuesto = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        btnGenerarCodigo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        btnPanelAgregar = new javax.swing.JPanel();
        labBtnAgregar = new javax.swing.JLabel();
        labErrorTalla = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JSpinner();
        txtPrecioCosto = new javax.swing.JSpinner();
        lblGanancia = new javax.swing.JLabel();
        txtGanancia = new javax.swing.JSpinner();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
      
        txt_PrecioTotal = new javax.swing.JLabel();

        jPanel4 = new javax.swing.JPanel();
        labClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Producto");
        setMinimumSize(new java.awt.Dimension(1370, 725));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1370, 725));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.setMinimumSize(new java.awt.Dimension(1240, 590));
        jPanel1.setPreferredSize(new java.awt.Dimension(1240, 590));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labInstruccion.setFont(new java.awt.Font("Yu Gothic UI", 1, 32)); // NOI18N
        labInstruccion.setForeground(new java.awt.Color(102, 102, 102));
        labInstruccion.setText("Ingrese los datos del nuevo producto:");
        jPanel1.add(labInstruccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Descripción:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Fecha de ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Color:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, -1, 30));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Marca:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Precio a");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 260, -1, 20));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Precio de");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 440, -1, 20));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Empresa:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 90, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("% Impuesto:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 330, -1, -1));

        txtDescripcion.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        txtDescripcion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 790, 30));

        txtColor.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtColor.setForeground(new java.awt.Color(51, 51, 51));
        txtColor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColorActionPerformed(evt);
            }
        });
        jPanel1.add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 200, 20));

        txtCodigo.setEditable(false);
        txtCodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigo.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(51, 51, 51));
        txtCodigo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCodigo.setEnabled(false);
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 200, 30));

        txtMarca.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtMarca.setForeground(new java.awt.Color(51, 51, 51));
        txtMarca.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 200, 20));

        txtEmpresa.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtEmpresa.setForeground(new java.awt.Color(51, 51, 51));
        txtEmpresa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 200, 20));

        rdbBolso.setBackground(new java.awt.Color(255, 255, 255));
        grup1.add(rdbBolso);
        rdbBolso.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        rdbBolso.setForeground(new java.awt.Color(102, 102, 102));
        rdbBolso.setText("Bolso");
        rdbBolso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbBolsoActionPerformed(evt);
            }
        });
        jPanel1.add(rdbBolso, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 80, -1, -1));

        txtFechaIngreso.setBackground(new java.awt.Color(237, 237, 237));
        jPanel1.add(txtFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 150, 150, 30));

        rdbZapato.setBackground(new java.awt.Color(255, 255, 255));
        grup1.add(rdbZapato);
        rdbZapato.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        rdbZapato.setForeground(new java.awt.Color(102, 102, 102));
        rdbZapato.setText("Zapato");
        rdbZapato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbZapatoActionPerformed(evt);
            }
        });
        jPanel1.add(rdbZapato, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 80, -1, -1));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Género:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, -1, 30));

        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));
        jPanel1.add(cmbGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, 110, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Categoría:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 90, 30));

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninos", "Jovenes", "Adulto" }));
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 110, -1));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Talla:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, -1, -1));

        txtTalla.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtTalla.setForeground(new java.awt.Color(51, 51, 51));
        txtTalla.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTallaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaKeyTyped(evt);
            }
        });
        jPanel1.add(txtTalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 110, 20));

        txtImpuesto.setModel(new javax.swing.SpinnerNumberModel(1.0d, 0.0d, 100.0d, 1.0d));
        txtImpuesto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtImpuestoStateChanged(evt);
            }
        });
        jPanel1.add(txtImpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 330, 150, 30));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Cantidad:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, -1, -1));

        btnGenerarCodigo.setBackground(new java.awt.Color(177, 177, 177));
        btnGenerarCodigo.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnGenerarCodigo.setForeground(new java.awt.Color(51, 51, 51));
        btnGenerarCodigo.setText("Generear código");
        btnGenerarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarCodigoActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenerarCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 150, 40));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Código:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("costo:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 280, -1, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText(" venta:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 460, -1, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 200, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 790, 10));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 200, 20));

        jSeparator6.setFont(new java.awt.Font("Yu Gothic UI", 0, 10)); // NOI18N
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 110, 10));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 200, 10));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 200, 10));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 470, 150, 10));

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
        btnPanelAgregar.add(labBtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, -1));

        jPanel1.add(btnPanelAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 530, 150, 40));

        labErrorTalla.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        labErrorTalla.setForeground(new java.awt.Color(255, 0, 51));
        labErrorTalla.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labErrorTalla.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(labErrorTalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 110, 20));

        txtCantidad.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 450, 110, 30));

        txtPrecioCosto.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(1.0d), null, Double.valueOf(1.0d)));
        txtPrecioCosto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtPrecioCostoStateChanged(evt);
            }
        });
        jPanel1.add(txtPrecioCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 270, 150, 30));

        lblGanancia.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblGanancia.setForeground(new java.awt.Color(102, 102, 102));
        lblGanancia.setText("% Ganancia:");
        jPanel1.add(lblGanancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 380, -1, -1));

        txtGanancia.setModel(new javax.swing.SpinnerNumberModel(1.0d, 0.0d, 100.0d, 1.0d));
        txtGanancia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtGananciaStateChanged(evt);
            }
        });
        jPanel1.add(txtGanancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 380, 150, 30));

        txtPrecioVenta.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtPrecioVenta.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecioVenta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 450, 150, 20));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("ingreso:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, -1, -1));

        txt_PrecioTotal.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        txt_PrecioTotal.setForeground(new java.awt.Color(51, 51, 51));
        txt_PrecioTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_PrecioTotal.setText("₡ ");
        jPanel1.add(txt_PrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 440, 30, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1370, 680));


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

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyTyped
        validarNumeros(evt);
    }//GEN-LAST:event_txtTallaKeyTyped

    private void txtColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColorActionPerformed

    private void btnGenerarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarCodigoActionPerformed
        if (txtMarca.getText().trim().equals("") || txtColor.getText().trim().equals("") || txtEmpresa.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe completar los datos para generar el código", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            txtCodigo.setText(txtColor.getText().charAt(0) + "" + txtMarca.getText().charAt(0) + "" + txtEmpresa.getText().charAt(0) + "");
            txtCodigo.setText(txtCodigo.getText() + "-" + new BL_Producto().obtenerSiguienteCodigo());
        }
    }//GEN-LAST:event_btnGenerarCodigoActionPerformed

    private void rdbBolsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbBolsoActionPerformed
        cmbGenero.setEnabled(false);
        cmbCategoria.setEnabled(false);
        txtTalla.setEnabled(false);
        txtTalla.setText("");
    }//GEN-LAST:event_rdbBolsoActionPerformed

    private void rdbZapatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbZapatoActionPerformed
        cmbGenero.setEnabled(true);
        cmbCategoria.setEnabled(true);
        txtTalla.setEnabled(true);
    }//GEN-LAST:event_rdbZapatoActionPerformed

    private void labCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseClicked
        this.dispose();
        new Inventario(lista).setVisible(true);
    }//GEN-LAST:event_labCloseMouseClicked

    private void labCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labCloseMouseEntered

    private void btnPanelAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAgregarMouseEntered
        btnPanelAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPanelAgregarMouseEntered

    private void btnPanelAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAgregarMouseClicked
        if (txtFechaIngreso.getDate() == null || txtDescripcion.getText().trim().equals("") || txtMarca.getText().trim().equals("") || txtColor.getText().trim().equals("") || txtEmpresa.getText().trim().equals("")
             || txtPrecioCosto.getValue().equals("") || (rdbZapato.isSelected() && txtTalla.getText().trim().equals(""))   ) {
            
            JOptionPane.showMessageDialog(null, "Debe completar los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!rdbZapato.isSelected() && !rdbBolso.isSelected()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de producto", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                if(validarRangos()){
                    BL_Producto producto = new BL_Producto();
                producto.setIdProducto(producto.obtenerSiguienteCodigo());
                producto.setCodigoUnico(txtCodigo.getText());
                producto.setFechaIngreso(new java.sql.Date(txtFechaIngreso.getDate().getTime()));
                producto.setColor(txtColor.getText().trim());
                producto.setMarca(txtMarca.getText().trim());
                producto.setEmpresa(txtEmpresa.getText().trim());
                producto.setPrecioImpuesto(Double.parseDouble(txtImpuesto.getValue().toString()));
                producto.setPrecioCosto(Double.parseDouble(txtPrecioCosto.getValue().toString()));
                producto.setPrecioGanancia(Double.parseDouble(txtGanancia.getValue().toString()));
                producto.setPrecioVenta(Double.parseDouble(txtPrecioVenta.getText()));
                producto.setDescripcion(txtDescripcion.getText().trim());
                producto.setCantidad(Integer.parseInt(txtCantidad.getValue().toString()));
                producto.setCodigoUnico(txtColor.getText().charAt(0) + "" + txtMarca.getText().charAt(0) + "" + txtEmpresa.getText().charAt(0) + "-" + new BL_Producto().obtenerSiguienteCodigo());
                if (rdbZapato.isSelected()) {
                    producto.setEsZapato(true);
                    producto.setTallaZapato(new BL_TallaZapato(cmbGenero.getSelectedItem().toString(), cmbCategoria.getSelectedItem().toString(), Double.parseDouble(txtTalla.getText().toString())));
                } else {
                    producto.setEsZapato(false);
                }
                

                if (labBtnAgregar.getText().equals("Agregar")) {
                    if (producto.insertarProducto()) {
                        txtCodigo.setText("");
                        txtDescripcion.setText("");
                        txtFechaIngreso.setDate(null);
                        txtTalla.setText("");
                        txtImpuesto.setValue(1);
                        txtMarca.setText("");
                        txtColor.setText("");
                        txtEmpresa.setText("");
                        txtImpuesto.setValue(0);
                        txtGanancia.setValue(0);
                        txtPrecioCosto.setValue(1);
                        txtPrecioVenta.setText("0.0");
                        JOptionPane.showMessageDialog(null, "Producto insertado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        lista.add(producto);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar el producto", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    producto.setIdProducto(lista.get(posicion).getIdProducto());
                    if (producto.modificarProducto(posicion, lista)) {
                        lista.set(posicion, producto);
                        JOptionPane.showMessageDialog(null, "Datos modificados", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new Inventario(lista).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar el producto", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
                }
                
            }
        }
    }//GEN-LAST:event_btnPanelAgregarMouseClicked

    private void txtTallaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyReleased
        if(!txtTalla.getText().equals("")){
            if(Double.parseDouble(txtTalla.getText().trim().toString()) >= 19 && Double.parseDouble(txtTalla.getText().trim().toString()) <= 46){
                labErrorTalla.setText("");
            }else{
                labErrorTalla.setText("Rango incorrecto");
            }
        }else{
            labErrorTalla.setText("");
        }
    }//GEN-LAST:event_txtTallaKeyReleased

    private void txtPrecioCostoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtPrecioCostoStateChanged
        calcularPrecioDeVenta();
    }//GEN-LAST:event_txtPrecioCostoStateChanged

    private void txtImpuestoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtImpuestoStateChanged
        calcularPrecioDeVenta();
    }//GEN-LAST:event_txtImpuestoStateChanged

    private void txtGananciaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtGananciaStateChanged
        calcularPrecioDeVenta();
    }//GEN-LAST:event_txtGananciaStateChanged

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void validarNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && c != '.' && c != evt.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Este campo solo admite valores numericos y ' . '", "Tipo de dato incorrecto", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public Boolean validarRangos(){
        boolean datosCorrectos = true;
        String hileraError = "Se encontraron los siguientes errores:\n";
        if(rdbZapato.isSelected()){
            double rangoTalla = Double.parseDouble(txtTalla.getText().trim());
            if(rangoTalla < 19 || rangoTalla > 46){
            hileraError += "* Rango de la talla incorrecto\n";
            datosCorrectos = false;
        }
        }
        double valorPrecioCosto = Double.parseDouble(txtPrecioCosto.getValue().toString());
        //double valorPrecioVenta = Double.parseDouble(txtPrecioVenta.getValue().toString());
        double valorImpuesto = Double.parseDouble(txtImpuesto.getValue().toString());
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        if(Calendar.getInstance().getTime().before(txtFechaIngreso.getDate())){
            hileraError += "* La fecha seleccionada es mayor a la actual\n";
            datosCorrectos = false;
        }
        
        if(valorPrecioCosto < 0){
            hileraError += "* Precio de costo incorrecto\n";
            datosCorrectos = false;
        }
        /*
        if(valorPrecioVenta < 0) {
            hileraError += "* Precio de venta incorrecto\n";
            datosCorrectos = false;
        }
        */
        if(valorImpuesto < 0) {
            hileraError += "* Precio con impuesto incorrecto\n";
            datosCorrectos = false;
        }
        
        if(!datosCorrectos){
            JOptionPane.showMessageDialog(null, hileraError,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return datosCorrectos;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarCodigo;
    private javax.swing.JPanel btnPanelAgregar;
    private javax.swing.JComboBox cmbCategoria;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.ButtonGroup grup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel labBtnAgregar;
    private javax.swing.JLabel labClose;
    private javax.swing.JLabel labErrorTalla;
    private javax.swing.JLabel labInstruccion;
    private javax.swing.JLabel lblGanancia;
    private javax.swing.JRadioButton rdbBolso;
    private javax.swing.JRadioButton rdbZapato;
    private javax.swing.JSpinner txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtEmpresa;
    private com.toedter.calendar.JDateChooser txtFechaIngreso;
    private javax.swing.JSpinner txtGanancia;
    private javax.swing.JSpinner txtImpuesto;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JSpinner txtPrecioCosto;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtTalla;
    private javax.swing.JLabel txt_PrecioTotal;
    // End of variables declaration//GEN-END:variables
}
