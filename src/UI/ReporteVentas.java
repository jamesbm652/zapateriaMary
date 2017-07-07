/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.BL_Factura;
import BL.BL_ManejadorFacturas;
import BL.BL_ReporteVenta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
    /**
     * Creates new form ReporteVentas
     */
    public ReporteVentas() {
        initComponents();
        modeloContado = (DefaultTableModel) tablaCon.getModel();
        modeloTarjeta = (DefaultTableModel) tablaTar.getModel();
        modeloAbono = (DefaultTableModel) tablaAbono.getModel();
        modeloCredito = (DefaultTableModel) tablaCredito.getModel();
        
        Date fActual = new Date(new java.util.Date().getTime());
        jDateFechaInicio.setDate(fActual);
        jDateFechaFInal.setDate(fActual);
        
        
        manejadorFacturas.cargarFacturasPorFecha(fActual.toString(), fActual.toString());
        listaFacturas = manejadorFacturas.ObtenerLista();
        separarListasSegunTipo();
        cargarProductosEnTabla("Contado", reporte.getFacturasContado());
        cargarProductosEnTabla("Tarjeta", reporte.getFacturasTarjeta());
        cargarProductosEnTabla("Abono", reporte.getFacturasAbono());
        cargarProductosEnTabla("Credito", reporte.getFacturasCredito());
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
        limpiarTablas(tabla);
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
    
    private void limpiarTablas(String tabla) {
        switch(tabla){
            case "Contado":
                int filas = tablaCon.getRowCount();
                for (int i = 0; filas > i; i++) {
                    modeloContado.removeRow(0);
                }
                break;
            case "Tarjeta":
                int filasTar = tablaTar.getRowCount();
                for (int i = 0; filasTar > i; i++) {
                    modeloTarjeta.removeRow(0);
                }
                break;
            case "Abono":
                int filasAbo = tablaAbono.getRowCount();
                for (int i = 0; filasAbo > i; i++) {
                    modeloAbono.removeRow(0);
                }
                break;
            case "Credito":
                int filasCre = tablaCredito.getRowCount();
                for (int i = 0; filasCre > i; i++) {
                    modeloCredito.removeRow(0);
                }
                break;
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
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
        jPanel5 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
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
        jPanel1.add(jDateFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 220, -1));
        jPanel1.add(jDateFechaFInal, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, 220, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel3.setText("Zapatería Mary");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Reporte de ventas");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, -1, -1));

        jPanel3.setBackground(new java.awt.Color(244, 244, 244));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Zapatos vendidos:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel8.setText("Bolsos vendidos:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jLabel9.setText("Cantidad de facturas:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel10.setText("Canceladas:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabel11.setText("Sin cancelar:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        txtConBolsosVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtConBolsosVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtConBolsosVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 100, 20));

        txtConCantFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtConCantFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtConCantFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 20));

        txtConCanceladas.setBackground(new java.awt.Color(244, 244, 244));
        txtConCanceladas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtConCanceladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 100, 20));

        txtConSinCancelar.setBackground(new java.awt.Color(244, 244, 244));
        txtConSinCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtConSinCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 100, 20));

        txtConZapVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtConZapVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtConZapVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 100, 20));

        jLabel12.setText("Facturas:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

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
        jScrollPane1.setViewportView(tablaCon);
        if (tablaCon.getColumnModel().getColumnCount() > 0) {
            tablaCon.getColumnModel().getColumn(0).setResizable(false);
            tablaCon.getColumnModel().getColumn(1).setResizable(false);
            tablaCon.getColumnModel().getColumn(2).setResizable(false);
            tablaCon.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 570, 180));

        jLabel13.setText("Total: ₡");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 100, 10));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 100, 10));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 100, 10));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 100, 10));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 100, 10));

        txtConTotal.setBackground(new java.awt.Color(244, 244, 244));
        txtConTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtConTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 100, 20));
        jPanel3.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 100, 10));

        jTabbedPane2.addTab("Contado", jPanel3);

        jPanel5.setBackground(new java.awt.Color(244, 244, 244));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setText("Zapatos vendidos:");
        jPanel5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel37.setText("Bolsos vendidos:");
        jPanel5.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jLabel38.setText("Cantidad de facturas:");
        jPanel5.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel39.setText("Canceladas:");
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabel40.setText("Sin cancelar:");
        jPanel5.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        txtTarBolsosVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtTarBolsosVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtTarBolsosVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 100, 20));

        txtTarCantFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtTarCantFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtTarCantFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 20));

        txtTarCanceladas.setBackground(new java.awt.Color(244, 244, 244));
        txtTarCanceladas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtTarCanceladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 100, 20));

        txtTarSinCancelar.setBackground(new java.awt.Color(244, 244, 244));
        txtTarSinCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtTarSinCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 100, 20));

        txtTarZapVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtTarZapVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtTarZapVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 100, 20));

        jLabel41.setText("Facturas:");
        jPanel5.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel42.setText("Total: ₡");
        jPanel5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        jPanel5.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 100, 10));
        jPanel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 100, 10));
        jPanel5.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 100, 10));
        jPanel5.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 100, 10));
        jPanel5.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 100, 10));

        txtTarTotal.setBackground(new java.awt.Color(244, 244, 244));
        txtTarTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtTarTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 100, 20));
        jPanel5.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 100, 10));

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
        jScrollPane2.setViewportView(tablaTar);
        if (tablaTar.getColumnModel().getColumnCount() > 0) {
            tablaTar.getColumnModel().getColumn(0).setResizable(false);
            tablaTar.getColumnModel().getColumn(1).setResizable(false);
            tablaTar.getColumnModel().getColumn(2).setResizable(false);
            tablaTar.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 570, 180));

        jTabbedPane2.addTab("Tarjeta crédito", jPanel5);

        jPanel6.setBackground(new java.awt.Color(244, 244, 244));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setText("Zapatos vendidos:");
        jPanel6.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel44.setText("Bolsos vendidos:");
        jPanel6.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jLabel45.setText("Cantidad de facturas:");
        jPanel6.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel46.setText("Canceladas:");
        jPanel6.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabel47.setText("Sin cancelar:");
        jPanel6.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        txtAbonBolsosVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonBolsosVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txtAbonBolsosVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 100, 20));

        txtAbonCantFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonCantFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txtAbonCantFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 20));

        txtAbonCanceladas.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonCanceladas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txtAbonCanceladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 100, 20));

        txtAbonSinCancelar.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonSinCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txtAbonSinCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 100, 20));

        txtAbonZapVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonZapVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txtAbonZapVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 100, 20));

        jLabel48.setText("Facturas:");
        jPanel6.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel49.setText("Total: ₡");
        jPanel6.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        jPanel6.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 100, 10));
        jPanel6.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 100, 10));
        jPanel6.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 100, 10));
        jPanel6.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 100, 10));
        jPanel6.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 100, 10));

        txtAbonTotal.setBackground(new java.awt.Color(244, 244, 244));
        txtAbonTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.add(txtAbonTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 100, 20));
        jPanel6.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 100, 10));

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
        jScrollPane3.setViewportView(tablaAbono);
        if (tablaAbono.getColumnModel().getColumnCount() > 0) {
            tablaAbono.getColumnModel().getColumn(0).setResizable(false);
            tablaAbono.getColumnModel().getColumn(1).setResizable(false);
            tablaAbono.getColumnModel().getColumn(2).setResizable(false);
            tablaAbono.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 570, 180));

        jTabbedPane2.addTab("Abonos", jPanel6);

        jPanel7.setBackground(new java.awt.Color(244, 244, 244));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel50.setText("Zapatos vendidos:");
        jPanel7.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel51.setText("Bolsos vendidos:");
        jPanel7.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jLabel52.setText("Cantidad de facturas:");
        jPanel7.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel53.setText("Canceladas:");
        jPanel7.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabel54.setText("Sin cancelar:");
        jPanel7.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        txtCreBolsosVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtCreBolsosVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel7.add(txtCreBolsosVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 100, 20));

        txtCreCantFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtCreCantFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel7.add(txtCreCantFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 20));

        txtCreCanceladas.setBackground(new java.awt.Color(244, 244, 244));
        txtCreCanceladas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel7.add(txtCreCanceladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 100, 20));

        txtCreSinCancelar.setBackground(new java.awt.Color(244, 244, 244));
        txtCreSinCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel7.add(txtCreSinCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 100, 20));

        txtCreZapVendidos.setBackground(new java.awt.Color(244, 244, 244));
        txtCreZapVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel7.add(txtCreZapVendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 100, 20));

        jLabel55.setText("Facturas:");
        jPanel7.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel56.setText("Total: ₡");
        jPanel7.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        jPanel7.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 100, 10));
        jPanel7.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 100, 10));
        jPanel7.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 100, 10));
        jPanel7.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 100, 10));
        jPanel7.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 100, 10));

        txtCreTotal.setBackground(new java.awt.Color(244, 244, 244));
        txtCreTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel7.add(txtCreTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 100, 20));
        jPanel7.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 100, 10));

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
        jScrollPane4.setViewportView(tablaCredito);
        if (tablaCredito.getColumnModel().getColumnCount() > 0) {
            tablaCredito.getColumnModel().getColumn(0).setResizable(false);
            tablaCredito.getColumnModel().getColumn(1).setResizable(false);
            tablaCredito.getColumnModel().getColumn(2).setResizable(false);
            tablaCredito.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 570, 180));

        jTabbedPane2.addTab("Crédito", jPanel7);

        jPanel2.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 163, 600, 390));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel5.setText("Desglose por tipo de factura:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel6.setText("Desglose general:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, -1, -1));

        jPanel8.setBackground(new java.awt.Color(244, 244, 244));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setText("Facturas de abono:");
        jPanel8.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel58.setText("Facturas de contado:");
        jPanel8.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel59.setText("Facturas a crédito:");
        jPanel8.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel60.setText("de crédito:");
        jPanel8.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        jLabel61.setText("Total: ₡");
        jPanel8.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, -1, -1));

        txtTotalGanancia.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalGanancia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtTotalGanancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 90, 20));
        jPanel8.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, 90, 10));

        txtTotalFacTarjeta.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalFacTarjeta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtTotalFacTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 70, 20));
        jPanel8.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 10));

        txtTotalFacAbono.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalFacAbono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtTotalFacAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 70, 20));
        jPanel8.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 70, 10));

        txtTotalFacCredito.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalFacCredito.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtTotalFacCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 70, 20));
        jPanel8.add(jSeparator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 70, 10));

        txtTotalFacContado.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalFacContado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtTotalFacContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 70, 20));
        jPanel8.add(jSeparator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 70, 10));

        jLabel62.setText("Facturas con tarjeta");
        jPanel8.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel63.setText("Ganancia: ₡");
        jPanel8.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        txtGanFacContado.setBackground(new java.awt.Color(244, 244, 244));
        txtGanFacContado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtGanFacContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 90, 20));
        jPanel8.add(jSeparator30, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 90, 10));

        txtGanFacTarjeta.setBackground(new java.awt.Color(244, 244, 244));
        txtGanFacTarjeta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtGanFacTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 90, 20));
        jPanel8.add(jSeparator31, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 90, 10));

        jLabel64.setText("Ganancia: ₡");
        jPanel8.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        txtGanFacAbono.setBackground(new java.awt.Color(244, 244, 244));
        txtGanFacAbono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtGanFacAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 90, 20));
        jPanel8.add(jSeparator32, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 90, 10));

        jLabel65.setText("Ganancia: ₡");
        jPanel8.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        txtGanFacCredito.setBackground(new java.awt.Color(244, 244, 244));
        txtGanFacCredito.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtGanFacCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 90, 20));
        jPanel8.add(jSeparator33, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 90, 10));

        jLabel66.setText("Ganancia: ₡");
        jPanel8.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        jLabel14.setText("Cantidad total de");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        txtCantTotalFacturas.setBackground(new java.awt.Color(244, 244, 244));
        txtCantTotalFacturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.add(txtCantTotalFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 70, 20));
        jPanel8.add(jSeparator34, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 70, 10));

        jLabel15.setText(" facturas:");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 171, 420, 380));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1370, 680));

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
        
        manejadorFacturas.cargarFacturasPorFecha(formato.format(jDateFechaInicio.getDate().getTime()), formato.format(jDateFechaFInal.getDate().getTime()));
        listaFacturas = manejadorFacturas.ObtenerLista();
        separarListasSegunTipo();
        cargarProductosEnTabla("Contado", reporte.getFacturasContado());
        cargarProductosEnTabla("Tarjeta", reporte.getFacturasTarjeta());
        cargarProductosEnTabla("Abono", reporte.getFacturasAbono());
        cargarProductosEnTabla("Credito", reporte.getFacturasCredito());
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
