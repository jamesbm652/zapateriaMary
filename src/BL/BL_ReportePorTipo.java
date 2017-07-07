/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

/**
 *
 * @author Joseph
 */
public class BL_ReportePorTipo {
    private String tipoFactura;
    private int cantidadFacturas = 0;
    private int cantidadCanceladas = 0;
    private int cantidadSinCancelar = 0;
    private int cantidadZapatosVendidos = 0;
    private int cantidadBolsosVendidos = 0;
    private double ganancias = 0;

    public BL_ReportePorTipo() {
    }

    public BL_ReportePorTipo(String tipoFactura, int cantidadFacturas, int cantidadCanceladas, int cantidadSinCancelar, int cantidadZapatosVendidos, int cantidadBolsosVendidos, double ganancias) {
        this.tipoFactura = tipoFactura;
        this.cantidadFacturas = cantidadFacturas;
        this.cantidadCanceladas = cantidadCanceladas;
        this.cantidadSinCancelar = cantidadSinCancelar;
        this.cantidadZapatosVendidos = cantidadZapatosVendidos;
        this.cantidadBolsosVendidos = cantidadBolsosVendidos;
        this.ganancias = ganancias;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public int getCantidadFacturas() {
        return cantidadFacturas;
    }

    public void setCantidadFacturas(int cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }

    public int getCantidadCanceladas() {
        return cantidadCanceladas;
    }

    public void setCantidadCanceladas(int cantidadCanceladas) {
        this.cantidadCanceladas = cantidadCanceladas;
    }

    public int getCantidadSinCancelar() {
        return cantidadSinCancelar;
    }

    public void setCantidadSinCancelar(int cantidadSinCancelar) {
        this.cantidadSinCancelar = cantidadSinCancelar;
    }

    public int getCantidadZapatosVendidos() {
        return cantidadZapatosVendidos;
    }

    public void setCantidadZapatosVendidos(int cantidadZapatosVendidos) {
        this.cantidadZapatosVendidos = cantidadZapatosVendidos;
    }

    public int getCantidadBolsosVendidos() {
        return cantidadBolsosVendidos;
    }

    public void setCantidadBolsosVendidos(int cantidadBolsosVendidos) {
        this.cantidadBolsosVendidos = cantidadBolsosVendidos;
    }

    public double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }

        
}
