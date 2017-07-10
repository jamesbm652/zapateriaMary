/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.BL_Factura;
import BL.BL_ManejadorFacturas;
import BL.BL_ReportePorTipo;
import BL.BL_ReporteVenta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author oscal
 */
public class ReporteVentas extends javax.swing.JFrame {
    
    ArrayList<BL_Factura> listaFacturas;
    BL_ManejadorFacturas manejadorFacturas = new BL_ManejadorFacturas();
    BL_ReporteVenta reporte = new BL_ReporteVenta();
    DefaultTableModel modeloContado;
    DefaultTableModel modeloTarjeta;
    DefaultTableModel modeloAbono;
    DefaultTableModel modeloCredito;
    Date fActual;
    /**
     * Creates new form ReporteVentas
     */
    public ReporteVentas() {
        initComponents();
        modeloContado = (DefaultTableModel) tablaCon.getModel();
        modeloTarjeta = (DefaultTableModel) tablaTar.getModel();
        modeloAbono = (DefaultTableModel) tablaAbono.getModel();
        modeloCredito = (DefaultTableModel) tablaCredito.getModel();
        tablaCon.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        tablaTar.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        tablaAbono.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        tablaCredito.getTableHeader().setDefaultRenderer(new Inventario.HeaderColor());
        
        fActual = new Date(new java.util.Date().getTime());
        jDateFechaInicio.setDate(fActual);
        jDateFechaFInal.setDate(fActual);
        
        
        manejadorFacturas.cargarFacturasPorFecha(fActual.toString(), fActual.toString());
        listaFacturas = manejadorFacturas.ObtenerLista();
        separarListasSegunTipo();
        limpiarTablas();
        cargarProductosEnTabla("Contado", reporte.getFacturasContado());
        cargarProductosEnTabla("Tarjeta", reporte.getFacturasTarjeta());
        cargarProductosEnTabla("Abono", reporte.getFacturasAbono());
        cargarProductosEnTabla("Credito", reporte.getFacturasCredito());
        
        reporte.generarReporteIndividual();
        
        llenarInformacion();
    }

    private void llenarInformacion (){
        BL_ReportePorTipo abono = reporte.getReporteAbonos();
        BL_ReportePorTipo credito = reporte.getReporteCredito();
        BL_ReportePorTipo contado = reporte.getReporteContado();
        BL_ReportePorTipo tarjeta = reporte.getReporteTarjeta();
        
        txtConCanceladas.setText(contado.getCantidadCanceladas() + "");
        txtConSinCancelar.setText(contado.getCantidadSinCancelar() + "");
        txtConZapVendidos.setText(contado.getCantidadZapatosVendidos() + "");
        txtConBolsosVendidos.setText(contado.getCantidadBolsosVendidos() + "");
        txtConTotal.setText(contado.getGanancias() + "");
        txtGanFacContado.setText(contado.getGanancias() + "");
        
        txtAbonCanceladas.setText(abono.getCantidadCanceladas() + "");
        txtAbonSinCancelar.setText(abono.getCantidadSinCancelar() + "");
        txtAbonZapVendidos.setText(abono.getCantidadZapatosVendidos() + "");
        txtAbonBolsosVendidos.setText(abono.getCantidadBolsosVendidos() + "");
        txtAbonTotal.setText(abono.getGanancias() + "");
        txtGanFacAbono.setText(abono.getGanancias() + "");
        
        txtCreCanceladas.setText(credito.getCantidadCanceladas() + "");
        txtCreSinCancelar.setText(credito.getCantidadSinCancelar() + "");
        txtCreZapVendidos.setText(credito.getCantidadZapatosVendidos() + "");
        txtCreBolsosVendidos.setText(credito.getCantidadBolsosVendidos() + "");
        txtCreTotal.setText(credito.getGanancias() + "");
        txtGanFacCredito.setText(credito.getGanancias() + "");
        
        txtTarCanceladas.setText(tarjeta.getCantidadCanceladas() + "");
        txtTarSinCancelar.setText(tarjeta.getCantidadSinCancelar() + "");
        txtTarZapVendidos.setText(tarjeta.getCantidadZapatosVendidos() + "");
        txtTarBolsosVendidos.setText(tarjeta.getCantidadBolsosVendidos() + "");
        txtTarTotal.setText(tarjeta.getGanancias() + "");
        txtGanFacTarjeta.setText(tarjeta.getGanancias() + "");
        
        txtTotalGanancia.setText(contado.getGanancias() + abono.getGanancias() + tarjeta.getGanancias() + credito.getGanancias() + "");
    }
    
    private void separarListasSegunTipo(){
        for (BL_Factura f : listaFacturas) {
            switch(f.getTipoFactura()){
                case "Contado":
                    reporte.agregarFacturaContado(f);
                    break;
                case "Tarjeta":
                    reporte.agregarFacturaTarjeta(f);
                    break;
                case "Apartado":
                    reporte.agregarFacturaAbono(f);
                    break;
                case "Crédito":
                    reporte.agregarFacturaCredito(f);
                    break;
            }
        }
        txtCantTotalFacturas.setText(listaFacturas.size() + "");
        txtTotalFacContado.setText(reporte.getFacturasContado().size() + "");
        txtTotalFacTarjeta.setText(reporte.getFacturasTarjeta().size() + "");
        txtTotalFacAbono.setText(reporte.getFacturasAbono().size() + "");
        txtTotalFacCredito.setText(reporte.getFacturasCredito().size() + "");
        
        txtConCantFacturas.setText(reporte.getFacturasContado().size() + "");
        txtTarCantFacturas.setText(reporte.getFacturasTarjeta().size() + "");
        txtAbonCantFacturas.setText(reporte.getFacturasAbono().size() + "");
        txtCreCantFacturas.setText(reporte.getFacturasCredito().size() + "");
    }
    
    
    private void cargarProductosEnTabla(String tabla,ArrayList<BL_Factura> listaParaMostrar) {
        switch(tabla){
            case "Contado":
                Object[] fila = new Object[modeloContado.getColumnCount()];

                for (int i = 0; i < listaParaMostrar.size(); i++) {
                    fila[0] = listaParaMostrar.get(i).getIdFactura();
                    fila[1] = listaParaMostrar.get(i).getFechaFactura().toString();
                    fila[2] = listaParaMostrar.get(i).getTipoFactura();
                    if (listaParaMostrar.get(i).isCancelada()) {
                        fila[3] = "Cancelada";
                    } else {
                        fila[3] = "Sin cancelar";
                    }
                    modeloContado.addRow(fila);
                }
                break;
            case "Tarjeta":
                Object[] filaTar = new Object[modeloTarjeta.getColumnCount()];

                for (int i = 0; i < listaParaMostrar.size(); i++) {
                    filaTar[0] = listaParaMostrar.get(i).getIdFactura();
                    filaTar[1] = listaParaMostrar.get(i).getFechaFactura().toString();
                    filaTar[2] = listaParaMostrar.get(i).getTipoFactura();
                    if (listaParaMostrar.get(i).isCancelada()) {
                        filaTar[3] = "Cancelada";
                    } else {
                        filaTar[3] = "Sin cancelar";
                    }
                    modeloTarjeta.addRow(filaTar);
                }
                break;
            case "Abono":
                Object[] filaAbono = new Object[modeloAbono.getColumnCount()];

                for (int i = 0; i < listaParaMostrar.size(); i++) {
                    filaAbono[0] = listaParaMostrar.get(i).getIdFactura();
                    filaAbono[1] = listaParaMostrar.get(i).getFechaFactura().toString();
                    filaAbono[2] = listaParaMostrar.get(i).getTipoFactura();
                    if (listaParaMostrar.get(i).isCancelada()) {
                        filaAbono[3] = "Cancelada";
                    } else {
                        filaAbono[3] = "Sin cancelar";
                    }
                    modeloAbono.addRow(filaAbono);
                }
                break;
            case "Credito":
                Object[] filaCredito = new Object[modeloCredito.getColumnCount()];

                for (int i = 0; i < listaParaMostrar.size(); i++) {
                    filaCredito[0] = listaParaMostrar.get(i).getIdFactura();
                    filaCredito[1] = listaParaMostrar.get(i).getFechaFactura().toString();
                    filaCredito[2] = listaParaMostrar.get(i).getTipoFactura();
                    if (listaParaMostrar.get(i).isCancelada()) {
                        filaCredito[3] = "Cancelada";
                    } else {
                        filaCredito[3] = "Sin cancelar";
                    }
                    modeloCredito.addRow(filaCredito);
                }
                break;
        }
        
        
    }
    
    private void limpiarTablas() {
        
                int filas = tablaCon.getRowCount();
                for (int i = 0; filas > i; i++) {
                    modeloContado.removeRow(0);
                }
                int filasTar = tablaTar.getRowCount();
                for (int i = 0; filasTar > i; i++) {
                    modeloTarjeta.removeRow(0);
                }
                int filasAbo = tablaAbono.getRowCount();
                for (int i = 0; filasAbo > i; i++) {
                    modeloAbono.removeRow(0);
                }
                int filasCre = tablaCredito.getRowCount();
                for (int i = 0; filasCre > i; i++) {
                    modeloCredito.removeRow(0);
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

        jPanel4 = new javax.swing.JPanel();
        labClose = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateFechaInicio = new com.toedter.calendar.JDateChooser();
        jDateFechaFInal = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        txtConBolsosVendidos = new javax.swing.JTextField();
        txtConCantFacturas = new javax.swing.JTextField();
        txtConCanceladas = new javax.swing.JTextField();
        txtConSinCancelar = new javax.swing.JTextField();
        txtConZapVendidos = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCon = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        txtConTotal = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtTarBolsosVendidos = new javax.swing.JTextField();
        txtTarCantFacturas = new javax.swing.JTextField();
        txtTarCanceladas = new javax.swing.JTextField();
        txtTarSinCancelar = new javax.swing.JTextField();
        txtTarZapVendidos = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        txtTarTotal = new javax.swing.JTextField();
        jSeparator22 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTar = new javax.swing.JTable();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtAbonBolsosVendidos = new javax.swing.JTextField();
        txtAbonCantFacturas = new javax.swing.JTextField();
        txtAbonCanceladas = new javax.swing.JTextField();
        txtAbonSinCancelar = new javax.swing.JTextField();
        txtAbonZapVendidos = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        txtAbonTotal = new javax.swing.JTextField();
        jSeparator23 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAbono = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtCreBolsosVendidos = new javax.swing.JTextField();
        txtCreCantFacturas = new javax.swing.JTextField();
        txtCreCanceladas = new javax.swing.JTextField();
        txtCreSinCancelar = new javax.swing.JTextField();
        txtCreZapVendidos = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        txtCreTotal = new javax.swing.JTextField();
        jSeparator24 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaCredito = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txtTotalGanancia = new javax.swing.JTextField();
        jSeparator25 = new javax.swing.JSeparator();
        txtTotalFacTarjeta = new javax.swing.JTextField();
        jSeparator26 = new javax.swing.JSeparator();
        txtTotalFacAbono = new javax.swing.JTextField();
        jSeparator27 = new javax.swing.JSeparator();
        txtTotalFacCredito = new javax.swing.JTextField();
        jSeparator28 = new javax.swing.JSeparator();
        txtTotalFacContado = new javax.swing.JTextField();
        jSeparator29 = new javax.swing.JSeparator();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        txtGanFacContado = new javax.swing.JTextField();
        jSeparator30 = new javax.swing.JSeparator();
        txtGanFacTarjeta = new javax.swing.JTextField();
        jSeparator31 = new javax.swing.JSeparator();
        jLabel64 = new javax.swing.JLabel();
        txtGanFacAbono = new javax.swing.JTextField();
        jSeparator32 = new javax.swing.JSeparator();
        jLabel65 = new javax.swing.JLabel();
        txtGanFacCredito = new javax.swing.JTextField();
        jSeparator33 = new javax.swing.JSeparator();
        jLabel66 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCantTotalFacturas = new javax.swing.JTextField();
        jSeparator34 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Reportes desde:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("hasta:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, 30));

        jDateFechaInicio.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(jDateFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 220, -1));

        jDateFechaFInal.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(jDateFechaFInal, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, 220, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Zapatería Mary");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Reporte de ventas");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        jTabbedPane2.setForeground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(244, 244, 244));
        jPanel3.setForeground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtConBolsosVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtConBolsosVendidos.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtConBolsosVendidos.setForeground(new java.awt.Color(51, 51, 51));
        txtConBolsosVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtConBolsosVendidos.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtConBolsosVendidos.setEnabled(false);
        jPanel3.add(txtConBolsosVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 100, 20));

        txtConCantFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtConCantFacturas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtConCantFacturas.setForeground(new java.awt.Color(51, 51, 51));
        txtConCantFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtConCantFacturas.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtConCantFacturas.setEnabled(false);
        jPanel3.add(txtConCantFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, 20));

        txtConCanceladas.setBackground(new java.awt.Color(244, 244, 244));
        txtConCanceladas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtConCanceladas.setForeground(new java.awt.Color(51, 51, 51));
        txtConCanceladas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtConCanceladas.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtConCanceladas.setEnabled(false);
        jPanel3.add(txtConCanceladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 100, 20));

        txtConSinCancelar.setBackground(new java.awt.Color(244, 244, 244));
        txtConSinCancelar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtConSinCancelar.setForeground(new java.awt.Color(51, 51, 51));
        txtConSinCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtConSinCancelar.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtConSinCancelar.setEnabled(false);
        jPanel3.add(txtConSinCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 100, 20));

        txtConZapVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtConZapVendidos.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtConZapVendidos.setForeground(new java.awt.Color(51, 51, 51));
        txtConZapVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtConZapVendidos.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtConZapVendidos.setEnabled(false);
        jPanel3.add(txtConZapVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 100, 20));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Facturas:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        tablaCon.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaCon.setForeground(new java.awt.Color(102, 102, 102));
        tablaCon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Factura", "Fecha", "Estado", "Tipo Factura"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCon.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaCon);
        if (tablaCon.getColumnModel().getColumnCount() > 0) {
            tablaCon.getColumnModel().getColumn(0).setResizable(false);
            tablaCon.getColumnModel().getColumn(1).setResizable(false);
            tablaCon.getColumnModel().getColumn(2).setResizable(false);
            tablaCon.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 570, 180));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Total: ₡");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 100, 10));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 100, 10));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 100, 10));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 100, 10));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 100, 10));

        txtConTotal.setBackground(new java.awt.Color(244, 244, 244));
        txtConTotal.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtConTotal.setForeground(new java.awt.Color(51, 51, 51));
        txtConTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtConTotal.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtConTotal.setEnabled(false);
        jPanel3.add(txtConTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 100, 20));
        jPanel3.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 100, 10));

        jLabel72.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(102, 102, 102));
        jLabel72.setText("Cantidad de facturas:");
        jPanel3.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel73.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(102, 102, 102));
        jLabel73.setText("Canceladas:");
        jPanel3.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 40));

        jLabel74.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(102, 102, 102));
        jLabel74.setText("Sin cancelar:");
        jPanel3.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel75.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(102, 102, 102));
        jLabel75.setText("Bolsos vendidos:");
        jPanel3.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel76.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(102, 102, 102));
        jLabel76.setText("Zapatos vendidos:");
        jPanel3.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, 40));

        jTabbedPane2.addTab("Contado", jPanel3);

        jPanel5.setBackground(new java.awt.Color(244, 244, 244));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTarBolsosVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtTarBolsosVendidos.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTarBolsosVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTarBolsosVendidos.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTarBolsosVendidos.setEnabled(false);
        jPanel5.add(txtTarBolsosVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 100, 20));

        txtTarCantFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtTarCantFacturas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTarCantFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTarCantFacturas.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTarCantFacturas.setEnabled(false);
        jPanel5.add(txtTarCantFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, 20));

        txtTarCanceladas.setBackground(new java.awt.Color(244, 244, 244));
        txtTarCanceladas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTarCanceladas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTarCanceladas.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTarCanceladas.setEnabled(false);
        jPanel5.add(txtTarCanceladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 100, 20));

        txtTarSinCancelar.setBackground(new java.awt.Color(244, 244, 244));
        txtTarSinCancelar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTarSinCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTarSinCancelar.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTarSinCancelar.setEnabled(false);
        jPanel5.add(txtTarSinCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 100, 20));

        txtTarZapVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtTarZapVendidos.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTarZapVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTarZapVendidos.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTarZapVendidos.setEnabled(false);
        jPanel5.add(txtTarZapVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 100, 20));

        jLabel41.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(102, 102, 102));
        jLabel41.setText("Facturas:");
        jPanel5.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(102, 102, 102));
        jLabel42.setText("Total: ₡");
        jPanel5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        jPanel5.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 100, 10));
        jPanel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 100, 10));
        jPanel5.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 100, 10));
        jPanel5.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 100, 10));
        jPanel5.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 100, 10));

        txtTarTotal.setBackground(new java.awt.Color(244, 244, 244));
        txtTarTotal.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTarTotal.setForeground(new java.awt.Color(51, 51, 51));
        txtTarTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTarTotal.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTarTotal.setEnabled(false);
        jPanel5.add(txtTarTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 100, 20));
        jPanel5.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 100, 10));

        tablaTar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaTar.setForeground(new java.awt.Color(51, 51, 51));
        tablaTar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Factura", "Fecha", "Estado", "Tipo Factura"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaTar.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaTar);
        if (tablaTar.getColumnModel().getColumnCount() > 0) {
            tablaTar.getColumnModel().getColumn(0).setResizable(false);
            tablaTar.getColumnModel().getColumn(1).setResizable(false);
            tablaTar.getColumnModel().getColumn(2).setResizable(false);
            tablaTar.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 570, 180));

        jLabel67.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(102, 102, 102));
        jLabel67.setText("Cantidad de facturas:");
        jPanel5.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel68.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(102, 102, 102));
        jLabel68.setText("Canceladas:");
        jPanel5.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 40));

        jLabel69.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(102, 102, 102));
        jLabel69.setText("Sin cancelar:");
        jPanel5.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 40));

        jLabel70.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(102, 102, 102));
        jLabel70.setText("Bolsos vendidos:");
        jPanel5.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel71.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(102, 102, 102));
        jLabel71.setText("Zapatos vendidos:");
        jPanel5.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, 40));

        jTabbedPane2.addTab("Tarjeta crédito", jPanel5);

        jPanel6.setBackground(new java.awt.Color(244, 244, 244));
        jPanel6.setForeground(new java.awt.Color(102, 102, 102));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(102, 102, 102));
        jLabel43.setText("Zapatos vendidos:");
        jPanel6.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, 40));

        jLabel44.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
        jLabel44.setText("Bolsos vendidos:");
        jPanel6.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel45.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
        jLabel45.setText("Cantidad de facturas:");
        jPanel6.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel46.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 102, 102));
        jLabel46.setText("Canceladas:");
        jPanel6.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 40));

        jLabel47.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setText("Sin cancelar:");
        jPanel6.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 20));

        txtAbonBolsosVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonBolsosVendidos.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtAbonBolsosVendidos.setForeground(new java.awt.Color(51, 51, 51));
        txtAbonBolsosVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtAbonBolsosVendidos.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtAbonBolsosVendidos.setEnabled(false);
        jPanel6.add(txtAbonBolsosVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 100, 20));

        txtAbonCantFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonCantFacturas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtAbonCantFacturas.setForeground(new java.awt.Color(51, 51, 51));
        txtAbonCantFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtAbonCantFacturas.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtAbonCantFacturas.setEnabled(false);
        jPanel6.add(txtAbonCantFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, 20));

        txtAbonCanceladas.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonCanceladas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtAbonCanceladas.setForeground(new java.awt.Color(51, 51, 51));
        txtAbonCanceladas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtAbonCanceladas.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtAbonCanceladas.setEnabled(false);
        jPanel6.add(txtAbonCanceladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 100, 20));

        txtAbonSinCancelar.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonSinCancelar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtAbonSinCancelar.setForeground(new java.awt.Color(51, 51, 51));
        txtAbonSinCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtAbonSinCancelar.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtAbonSinCancelar.setEnabled(false);
        jPanel6.add(txtAbonSinCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 100, 20));

        txtAbonZapVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonZapVendidos.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtAbonZapVendidos.setForeground(new java.awt.Color(51, 51, 51));
        txtAbonZapVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtAbonZapVendidos.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtAbonZapVendidos.setEnabled(false);
        jPanel6.add(txtAbonZapVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 100, 20));

        jLabel48.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 102, 102));
        jLabel48.setText("Facturas:");
        jPanel6.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel49.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(102, 102, 102));
        jLabel49.setText("Total: ₡");
        jPanel6.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        jPanel6.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 100, 10));
        jPanel6.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 100, 10));
        jPanel6.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 100, 10));
        jPanel6.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 100, 10));
        jPanel6.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 100, 10));

        txtAbonTotal.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonTotal.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtAbonTotal.setForeground(new java.awt.Color(51, 51, 51));
        txtAbonTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtAbonTotal.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtAbonTotal.setEnabled(false);
        jPanel6.add(txtAbonTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 100, 20));
        jPanel6.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 100, 10));

        tablaAbono.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaAbono.setForeground(new java.awt.Color(51, 51, 51));
        tablaAbono.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Factura", "Fecha", "Estado", "Tipo Factura"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAbono.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tablaAbono);
        if (tablaAbono.getColumnModel().getColumnCount() > 0) {
            tablaAbono.getColumnModel().getColumn(0).setResizable(false);
            tablaAbono.getColumnModel().getColumn(1).setResizable(false);
            tablaAbono.getColumnModel().getColumn(2).setResizable(false);
            tablaAbono.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 570, 180));

        jTabbedPane2.addTab("Abonos", jPanel6);

        jPanel7.setBackground(new java.awt.Color(244, 244, 244));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel50.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(102, 102, 102));
        jLabel50.setText("Zapatos vendidos:");
        jPanel7.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));

        jLabel51.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 102, 102));
        jLabel51.setText("Bolsos vendidos:");
        jPanel7.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel52.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(102, 102, 102));
        jLabel52.setText("Cantidad de facturas:");
        jPanel7.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel53.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(102, 102, 102));
        jLabel53.setText("Canceladas:");
        jPanel7.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel54.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(102, 102, 102));
        jLabel54.setText("Sin cancelar:");
        jPanel7.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtCreBolsosVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtCreBolsosVendidos.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCreBolsosVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCreBolsosVendidos.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtCreBolsosVendidos.setEnabled(false);
        jPanel7.add(txtCreBolsosVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 100, 20));

        txtCreCantFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtCreCantFacturas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCreCantFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCreCantFacturas.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtCreCantFacturas.setEnabled(false);
        jPanel7.add(txtCreCantFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, 20));

        txtCreCanceladas.setBackground(new java.awt.Color(244, 244, 244));
        txtCreCanceladas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCreCanceladas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCreCanceladas.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtCreCanceladas.setEnabled(false);
        jPanel7.add(txtCreCanceladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 100, 20));

        txtCreSinCancelar.setBackground(new java.awt.Color(244, 244, 244));
        txtCreSinCancelar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCreSinCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCreSinCancelar.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtCreSinCancelar.setEnabled(false);
        jPanel7.add(txtCreSinCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 100, 20));

        txtCreZapVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtCreZapVendidos.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCreZapVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCreZapVendidos.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtCreZapVendidos.setEnabled(false);
        jPanel7.add(txtCreZapVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 100, 20));

        jLabel55.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(102, 102, 102));
        jLabel55.setText("Facturas:");
        jPanel7.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel56.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(102, 102, 102));
        jLabel56.setText("Total: ₡");
        jPanel7.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        jPanel7.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 100, 10));
        jPanel7.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 100, 10));
        jPanel7.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 100, 10));
        jPanel7.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 100, 10));
        jPanel7.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 100, 10));

        txtCreTotal.setBackground(new java.awt.Color(244, 244, 244));
        txtCreTotal.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCreTotal.setForeground(new java.awt.Color(51, 51, 51));
        txtCreTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCreTotal.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtCreTotal.setEnabled(false);
        jPanel7.add(txtCreTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 100, 20));
        jPanel7.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 100, 10));

        tablaCredito.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        tablaCredito.setForeground(new java.awt.Color(51, 51, 51));
        tablaCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Factura", "Fecha", "Estado", "Tipo Factura"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCredito.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tablaCredito);
        if (tablaCredito.getColumnModel().getColumnCount() > 0) {
            tablaCredito.getColumnModel().getColumn(0).setResizable(false);
            tablaCredito.getColumnModel().getColumn(1).setResizable(false);
            tablaCredito.getColumnModel().getColumn(2).setResizable(false);
            tablaCredito.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 570, 180));

        jTabbedPane2.addTab("Crédito", jPanel7);

        jPanel2.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 163, 600, 390));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Desglose por tipo de factura:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Desglose general:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, -1, -1));

        jPanel8.setBackground(new java.awt.Color(244, 244, 244));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 102, 102));
        jLabel57.setText("Facturas de abono:");
        jPanel8.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel58.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(102, 102, 102));
        jLabel58.setText("Facturas de contado:");
        jPanel8.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel59.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(102, 102, 102));
        jLabel59.setText("Facturas a crédito:");
        jPanel8.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel60.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(102, 102, 102));
        jLabel60.setText("de crédito:");
        jPanel8.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        jLabel61.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(102, 102, 102));
        jLabel61.setText("Total: ₡");
        jPanel8.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 330, 60, -1));

        txtTotalGanancia.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalGanancia.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTotalGanancia.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalGanancia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTotalGanancia.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTotalGanancia.setEnabled(false);
        jPanel8.add(txtTotalGanancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 90, 20));
        jPanel8.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, 90, 10));

        txtTotalFacTarjeta.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalFacTarjeta.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTotalFacTarjeta.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalFacTarjeta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTotalFacTarjeta.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTotalFacTarjeta.setEnabled(false);
        jPanel8.add(txtTotalFacTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 70, 20));
        jPanel8.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 70, 10));

        txtTotalFacAbono.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalFacAbono.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTotalFacAbono.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalFacAbono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTotalFacAbono.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTotalFacAbono.setEnabled(false);
        jPanel8.add(txtTotalFacAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 70, 20));
        jPanel8.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 70, 10));

        txtTotalFacCredito.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalFacCredito.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTotalFacCredito.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalFacCredito.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTotalFacCredito.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTotalFacCredito.setEnabled(false);
        jPanel8.add(txtTotalFacCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 70, 20));
        jPanel8.add(jSeparator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 70, 10));

        txtTotalFacContado.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalFacContado.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtTotalFacContado.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalFacContado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTotalFacContado.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTotalFacContado.setEnabled(false);
        jPanel8.add(txtTotalFacContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 70, 20));
        jPanel8.add(jSeparator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 70, 10));

        jLabel62.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(102, 102, 102));
        jLabel62.setText("Facturas con tarjeta");
        jPanel8.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel63.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(102, 102, 102));
        jLabel63.setText("Ganancia: ₡");
        jPanel8.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        txtGanFacContado.setBackground(new java.awt.Color(244, 244, 244));
        txtGanFacContado.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtGanFacContado.setForeground(new java.awt.Color(51, 51, 51));
        txtGanFacContado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtGanFacContado.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtGanFacContado.setEnabled(false);
        jPanel8.add(txtGanFacContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 90, 20));
        jPanel8.add(jSeparator30, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 90, 10));

        txtGanFacTarjeta.setBackground(new java.awt.Color(244, 244, 244));
        txtGanFacTarjeta.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtGanFacTarjeta.setForeground(new java.awt.Color(51, 51, 51));
        txtGanFacTarjeta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtGanFacTarjeta.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtGanFacTarjeta.setEnabled(false);
        jPanel8.add(txtGanFacTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 90, 20));
        jPanel8.add(jSeparator31, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 90, 10));

        jLabel64.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(102, 102, 102));
        jLabel64.setText("Ganancia: ₡");
        jPanel8.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        txtGanFacAbono.setBackground(new java.awt.Color(244, 244, 244));
        txtGanFacAbono.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtGanFacAbono.setForeground(new java.awt.Color(51, 51, 51));
        txtGanFacAbono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtGanFacAbono.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtGanFacAbono.setEnabled(false);
        jPanel8.add(txtGanFacAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 90, 20));
        jPanel8.add(jSeparator32, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 90, 10));

        jLabel65.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(102, 102, 102));
        jLabel65.setText("Ganancia: ₡");
        jPanel8.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, -1));

        txtGanFacCredito.setBackground(new java.awt.Color(244, 244, 244));
        txtGanFacCredito.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtGanFacCredito.setForeground(new java.awt.Color(51, 51, 51));
        txtGanFacCredito.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtGanFacCredito.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtGanFacCredito.setEnabled(false);
        jPanel8.add(txtGanFacCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 90, 20));
        jPanel8.add(jSeparator33, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 90, 10));

        jLabel66.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(102, 102, 102));
        jLabel66.setText("Ganancia: ₡");
        jPanel8.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Cantidad total de");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        txtCantTotalFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtCantTotalFacturas.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        txtCantTotalFacturas.setForeground(new java.awt.Color(51, 51, 51));
        txtCantTotalFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCantTotalFacturas.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtCantTotalFacturas.setEnabled(false);
        jPanel8.add(txtCantTotalFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 70, 20));
        jPanel8.add(jSeparator34, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 70, 10));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText(" facturas:");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 171, 440, 380));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 1120, 570));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar.png"))); // NOI18N
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarMouseEntered(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1370, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseEntered
        labClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labCloseMouseEntered

    private void labCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labCloseMouseClicked
        this.dispose();
        new Menu_Principal().setVisible(true);
    }//GEN-LAST:event_labCloseMouseClicked

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        if(jDateFechaInicio.getDate() == null && jDateFechaFInal.getDate() == null){
            JOptionPane.showMessageDialog(null, "Debe ingresar ambas fechas\npara poder realizar la búsqueda.","Datos incompletos",JOptionPane.WARNING_MESSAGE ,new ImageIcon("src/recursos/warning.png"));
        }else{
            if(jDateFechaFInal.getDate() == null){
                JOptionPane.showMessageDialog(null, "Debe ingresar una fecha final\npara poder realizar la búsqueda.","Datos incompletos",JOptionPane.WARNING_MESSAGE ,new ImageIcon("src/recursos/warning.png"));
            }

            if(jDateFechaInicio.getDate() == null){
                JOptionPane.showMessageDialog(null, "Debe ingresar una fecha de inicio\npara poder realizar la búsqueda.","Datos incompletos",JOptionPane.WARNING_MESSAGE ,new ImageIcon("src/recursos/warning.png"));
            } 
        } 
        if(jDateFechaInicio.getDate() != null && jDateFechaFInal.getDate() != null){
            if(jDateFechaInicio.getDate().after(jDateFechaFInal.getDate())){
                JOptionPane.showMessageDialog(null, "La fecha inicial es mayor que la fecha final.","Rango de fechas incorrecto",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
            }else{
                if(jDateFechaFInal.getDate().after(fActual)){
                    JOptionPane.showMessageDialog(null, "La fecha final es mayor a la actual.","Rango de fechas incorrecto",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/recursos/error.png"));
                }else{
                    manejadorFacturas.EliminarTodos();
                    reporte.vaciarListas();
                    manejadorFacturas.cargarFacturasPorFecha(formato.format(jDateFechaInicio.getDate().getTime()), formato.format(jDateFechaFInal.getDate().getTime()));
                    listaFacturas = manejadorFacturas.ObtenerLista();
                    separarListasSegunTipo();
                    limpiarTablas();
                    reporte.generarReporteIndividual();
                    cargarProductosEnTabla("Contado", reporte.getFacturasContado());
                    cargarProductosEnTabla("Tarjeta", reporte.getFacturasTarjeta());
                    cargarProductosEnTabla("Abono", reporte.getFacturasAbono());
                    cargarProductosEnTabla("Credito", reporte.getFacturasCredito());

                    llenarInformacion();
                }
            }
        }
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void btnBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseEntered
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnBuscarMouseEntered

  
    
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
    private javax.swing.JLabel btnBuscar;
    private com.toedter.calendar.JDateChooser jDateFechaFInal;
    private com.toedter.calendar.JDateChooser jDateFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel labClose;
    private javax.swing.JTable tablaAbono;
    private javax.swing.JTable tablaCon;
    private javax.swing.JTable tablaCredito;
    private javax.swing.JTable tablaTar;
    private javax.swing.JTextField txtAbonBolsosVendidos;
    private javax.swing.JTextField txtAbonCanceladas;
    private javax.swing.JTextField txtAbonCantFacturas;
    private javax.swing.JTextField txtAbonSinCancelar;
    private javax.swing.JTextField txtAbonTotal;
    private javax.swing.JTextField txtAbonZapVendidos;
    private javax.swing.JTextField txtCantTotalFacturas;
    private javax.swing.JTextField txtConBolsosVendidos;
    private javax.swing.JTextField txtConCanceladas;
    private javax.swing.JTextField txtConCantFacturas;
    private javax.swing.JTextField txtConSinCancelar;
    private javax.swing.JTextField txtConTotal;
    private javax.swing.JTextField txtConZapVendidos;
    private javax.swing.JTextField txtCreBolsosVendidos;
    private javax.swing.JTextField txtCreCanceladas;
    private javax.swing.JTextField txtCreCantFacturas;
    private javax.swing.JTextField txtCreSinCancelar;
    private javax.swing.JTextField txtCreTotal;
    private javax.swing.JTextField txtCreZapVendidos;
    private javax.swing.JTextField txtGanFacAbono;
    private javax.swing.JTextField txtGanFacContado;
    private javax.swing.JTextField txtGanFacCredito;
    private javax.swing.JTextField txtGanFacTarjeta;
    private javax.swing.JTextField txtTarBolsosVendidos;
    private javax.swing.JTextField txtTarCanceladas;
    private javax.swing.JTextField txtTarCantFacturas;
    private javax.swing.JTextField txtTarSinCancelar;
    private javax.swing.JTextField txtTarTotal;
    private javax.swing.JTextField txtTarZapVendidos;
    private javax.swing.JTextField txtTotalFacAbono;
    private javax.swing.JTextField txtTotalFacContado;
    private javax.swing.JTextField txtTotalFacCredito;
    private javax.swing.JTextField txtTotalFacTarjeta;
    private javax.swing.JTextField txtTotalGanancia;
    // End of variables declaration//GEN-END:variables
}
