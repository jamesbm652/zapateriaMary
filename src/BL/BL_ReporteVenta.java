/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;

/**
 *
 * @author oscal
 */
public class BL_ReporteVenta {

    private ArrayList<BL_Factura> facturasContado;
    private ArrayList<BL_Factura> facturasTarjeta;
    private ArrayList<BL_Factura> facturasAbono;
    private ArrayList<BL_Factura> facturasCredito;
    private BL_ReportePorTipo reporteContado = new BL_ReportePorTipo();
    private BL_ReportePorTipo reporteTarjeta = new BL_ReportePorTipo();
    private BL_ReportePorTipo reporteAbonos = new BL_ReportePorTipo();
    private BL_ReportePorTipo reporteCredito = new BL_ReportePorTipo();

    public BL_ReporteVenta(ArrayList<BL_Factura> facturasContado, ArrayList<BL_Factura> facturasTarjeta, ArrayList<BL_Factura> facturasAbono, ArrayList<BL_Factura> facturasCredito) {
        this.facturasContado = facturasContado;
        this.facturasTarjeta = facturasTarjeta;
        this.facturasAbono = facturasAbono;
        this.facturasCredito = facturasCredito;
    }

    public BL_ReporteVenta() {
        this.facturasContado = new ArrayList<>();
        this.facturasTarjeta = new ArrayList<>();
        this.facturasAbono = new ArrayList<>();
        this.facturasCredito = new ArrayList<>();
    }

    public BL_ReportePorTipo getReporteContado() {
        return reporteContado;
    }

    public void setReporteContado(BL_ReportePorTipo reporteContado) {
        this.reporteContado = reporteContado;
    }

    public BL_ReportePorTipo getReporteTarjeta() {
        return reporteTarjeta;
    }

    public void setReporteTarjeta(BL_ReportePorTipo reporteTarjeta) {
        this.reporteTarjeta = reporteTarjeta;
    }

    public BL_ReportePorTipo getReporteAbonos() {
        return reporteAbonos;
    }

    public void setReporteAbonos(BL_ReportePorTipo reporteAbonos) {
        this.reporteAbonos = reporteAbonos;
    }

    public BL_ReportePorTipo getReporteCredito() {
        return reporteCredito;
    }

    public void setReporteCredito(BL_ReportePorTipo reporteCredito) {
        this.reporteCredito = reporteCredito;
    }

    
    public void agregarFacturaContado(BL_Factura factura) {
        facturasContado.add(factura);
    }

    public void agregarFacturaTarjeta(BL_Factura factura) {
        facturasTarjeta.add(factura);
    }

    public void agregarFacturaAbono(BL_Factura factura) {
        facturasAbono.add(factura);
    }

    public void agregarFacturaCredito(BL_Factura factura) {
        facturasCredito.add(factura);
    }

    public void vaciarListas() {
        facturasContado.clear();
        facturasTarjeta.clear();
        facturasAbono.clear();
        facturasCredito.clear();
    }

    public double obtenerGananciaFacturasContado() {
        for (int i = 0; i < facturasContado.size(); i++) {
            for (BL_Factura facturasContado1 : facturasContado) {

            }
        }
        return 0;
    }

    public ArrayList<BL_Factura> getFacturasContado() {
        return facturasContado;
    }

    public void setFacturasContado(ArrayList<BL_Factura> facturasContado) {
        this.facturasContado = facturasContado;
    }

    public ArrayList<BL_Factura> getFacturasTarjeta() {
        return facturasTarjeta;
    }

    public void setFacturasTarjeta(ArrayList<BL_Factura> facturasTarjeta) {
        this.facturasTarjeta = facturasTarjeta;
    }

    public ArrayList<BL_Factura> getFacturasAbono() {
        return facturasAbono;
    }

    public void setFacturasAbono(ArrayList<BL_Factura> facturasAbono) {
        this.facturasAbono = facturasAbono;
    }

    public ArrayList<BL_Factura> getFacturasCredito() {
        return facturasCredito;
    }

    public void setFacturasCredito(ArrayList<BL_Factura> facturasCredito) {
        this.facturasCredito = facturasCredito;
    }

    public void generarReporteIndividual() {
        //Se leen las facturas por contado
        calcularContado();
        // Se leen las facturas de tarjeta
        calcularTarjeta();
        // Se leen las facturas de apartados
        calcularAbonos();
        // Se leen las facturas de credito
        calcularCredito();
    }
    
    private void calcularContado(){
        BL_ManejadorProducto todosProductos = new BL_ManejadorProducto();
        todosProductos.CargarProductos();
        ArrayList<BL_Producto> listaTodosProductos = todosProductos.ObtenerListaProductos();
        
        double ganancias = 0;
        int cantidadZapatos = 0;
        int cantidadBolsos = 0;
        
        reporteContado.setTipoFactura("Contado");
        reporteContado.setCantidadFacturas(facturasContado.size());
        reporteContado.setCantidadCanceladas(facturasContado.size());
        reporteContado.setCantidadSinCancelar(0);

        for (int i = 0; i < facturasContado.size(); i++) {
            for (int j = 0; j < facturasContado.get(i).getProductosFactura().size(); j++) {
                for (int k = 0; k < listaTodosProductos.size(); k++) {
                    if (facturasContado.get(i).getProductosFactura().get(j).getIdProducto() == listaTodosProductos.get(k).getIdProducto()
                            && listaTodosProductos.get(k).isEsZapato()) {
                        cantidadZapatos += facturasContado.get(i).getProductosFactura().get(j).getCantidadVendida();
                        break;
                    } else if (facturasContado.get(i).getProductosFactura().get(j).getIdProducto() == listaTodosProductos.get(k).getIdProducto()
                            && !listaTodosProductos.get(k).isEsZapato()) {
                        cantidadBolsos += facturasContado.get(i).getProductosFactura().get(j).getCantidadVendida();
                    }
                }
                ganancias += facturasContado.get(i).getProductosFactura().get(j).getPrecioVenta();
            }
        }
        reporteContado.setCantidadBolsosVendidos(cantidadBolsos);
        reporteContado.setCantidadZapatosVendidos(cantidadZapatos);
        reporteContado.setGanancias(ganancias);
    }
    
    private void calcularTarjeta(){
        BL_ManejadorProducto todosProductos = new BL_ManejadorProducto();
        todosProductos.CargarProductos();
        ArrayList<BL_Producto> listaTodosProductos = todosProductos.ObtenerListaProductos();
        
        double ganancias = 0;
        int cantidadZapatos = 0;
        int cantidadBolsos = 0;
        
        reporteTarjeta.setTipoFactura("Tarjeta");
        reporteTarjeta.setCantidadFacturas(facturasTarjeta.size());
        reporteTarjeta.setCantidadCanceladas(facturasTarjeta.size());
        reporteTarjeta.setCantidadSinCancelar(0);

        for (int i = 0; i < facturasTarjeta.size(); i++) {
            ArrayList<BL_ProductoFactura> listaProductos = facturasTarjeta.get(i).getProductosFactura();
            for (int j = 0; j < listaProductos.size(); j++) {
                for (int k = 0; k < listaTodosProductos.size(); k++) {
                    if (listaProductos.get(j).getIdProducto() == listaTodosProductos.get(k).getIdProducto()
                            && listaTodosProductos.get(k).isEsZapato()) {
                        cantidadZapatos += listaProductos.get(j).getCantidadVendida();
                        break;
                    } else if (listaProductos.get(j).getIdProducto() == listaTodosProductos.get(k).getIdProducto()
                            && !listaTodosProductos.get(k).isEsZapato()) {
                        cantidadBolsos += listaProductos.get(j).getCantidadVendida();
                    }
                }
                ganancias += listaProductos.get(j).getPrecioVenta();
            }
        }
        
        reporteTarjeta.setCantidadBolsosVendidos(cantidadBolsos);
        reporteTarjeta.setCantidadZapatosVendidos(cantidadZapatos);
        reporteTarjeta.setGanancias(ganancias);
    }
    
    private void calcularAbonos(){
        BL_ManejadorProducto todosProductos = new BL_ManejadorProducto();
        todosProductos.CargarProductos();
        ArrayList<BL_Producto> listaTodosProductos = todosProductos.ObtenerListaProductos();
        
        double ganancias = 0;
        int cantidadZapatos = 0;
        int cantidadBolsos = 0;
        int cantidadCanceladas = 0;
        int cantidadSinCancelar = 0;
        
        reporteAbonos.setTipoFactura("Abonos");
        reporteAbonos.setCantidadFacturas(facturasAbono.size());
        

        for (int i = 0; i < facturasAbono.size(); i++) {
            if (facturasAbono.get(i).isCancelada()) {
                cantidadCanceladas ++;
            }else{
                cantidadSinCancelar ++;
            }
            ArrayList<BL_ProductoFactura> listaProductos = facturasAbono.get(i).getProductosFactura();
            for (int j = 0; j < listaProductos.size(); j++) {
                for (int k = 0; k < listaTodosProductos.size(); k++) {
                    if (listaProductos.get(j).getIdProducto() == listaTodosProductos.get(k).getIdProducto()
                            && listaTodosProductos.get(k).isEsZapato()) {
                        cantidadZapatos += listaProductos.get(j).getCantidadVendida();
                        break;
                    } else if (listaProductos.get(j).getIdProducto() == listaTodosProductos.get(k).getIdProducto()
                            && !listaTodosProductos.get(k).isEsZapato()) {
                        cantidadBolsos += listaProductos.get(j).getCantidadVendida();
                    }
                }
                ganancias += listaProductos.get(j).getPrecioVenta();
            }
        }
        
        reporteAbonos.setCantidadBolsosVendidos(cantidadBolsos);
        reporteAbonos.setCantidadZapatosVendidos(cantidadZapatos);
        reporteAbonos.setGanancias(ganancias);
        reporteAbonos.setCantidadCanceladas(cantidadCanceladas);
        reporteAbonos.setCantidadSinCancelar(cantidadSinCancelar);
    }
    
    public void calcularCredito(){
        BL_ManejadorProducto todosProductos = new BL_ManejadorProducto();
        todosProductos.CargarProductos();
        ArrayList<BL_Producto> listaTodosProductos = todosProductos.ObtenerListaProductos();
        
        double ganancias = 0;
        int cantidadZapatos = 0;
        int cantidadBolsos = 0;
        int cantidadCanceladas = 0;
        int cantidadSinCancelar = 0;
        
        reporteCredito.setTipoFactura("Abonos");
        reporteCredito.setCantidadFacturas(facturasCredito.size());
        

        for (int i = 0; i < facturasCredito.size(); i++) {
            if (facturasCredito.get(i).isCancelada()) {
                cantidadCanceladas ++;
            }else{
                cantidadSinCancelar ++;
            }
            ArrayList<BL_ProductoFactura> listaProductos = facturasCredito.get(i).getProductosFactura();
            for (int j = 0; j < listaProductos.size(); j++) {
                for (int k = 0; k < listaTodosProductos.size(); k++) {
                    if (listaProductos.get(j).getIdProducto() == listaTodosProductos.get(k).getIdProducto()
                            && listaTodosProductos.get(k).isEsZapato()) {
                        cantidadZapatos += listaProductos.get(j).getCantidadVendida();
                        break;
                    } else if (listaProductos.get(j).getIdProducto() == listaTodosProductos.get(k).getIdProducto()
                            && !listaTodosProductos.get(k).isEsZapato()) {
                        cantidadBolsos += listaProductos.get(j).getCantidadVendida();
                    }
                }
                ganancias += listaProductos.get(j).getPrecioVenta();
            }
        }
        
        reporteCredito.setCantidadBolsosVendidos(cantidadBolsos);
        reporteCredito.setCantidadZapatosVendidos(cantidadZapatos);
        reporteCredito.setGanancias(ganancias);
        reporteCredito.setCantidadCanceladas(cantidadCanceladas);
        reporteCredito.setCantidadSinCancelar(cantidadSinCancelar);
    }

}
