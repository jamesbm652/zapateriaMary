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
    private double gananciaContado = 0;
    private double gananciaTarjeta = 0;
    private double gananciaAbono = 0;
    private double gananciaCredito = 0;

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

    public void agregarFacturaContado(BL_Factura factura){
        facturasContado.add(factura);
    }
    public void agregarFacturaTarjeta(BL_Factura factura){
        facturasTarjeta.add(factura);
    }
    public void agregarFacturaAbono(BL_Factura factura){
        facturasAbono.add(factura);
    }
    public void agregarFacturaCredito(BL_Factura factura){
        facturasCredito.add(factura);
    }
    
    public void vaciarListas(){
        facturasContado.clear();
        facturasTarjeta.clear();
        facturasAbono.clear();
        facturasCredito.clear();
    }
    
    public double obtenerGananciaFacturasContado(){
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
    
    
    
    
}
