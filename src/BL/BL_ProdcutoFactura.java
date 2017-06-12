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
public class BL_ProdcutoFactura {
    private int idProductoFactura;
    private int idProducto;
    private int cantidadVendida;
    private double precioVenta;

    public BL_ProdcutoFactura() {
    }

    public BL_ProdcutoFactura(int idProductoFactura, int idProducto, int cantidadVendida, double precioVenta) {
        this.idProductoFactura = idProductoFactura;
        this.idProducto = idProducto;
        this.cantidadVendida = cantidadVendida;
        this.precioVenta = precioVenta;
    }

    public int getIdProductoFactura() {
        return idProductoFactura;
    }

    public void setIdProductoFactura(int idProductoFactura) {
        this.idProductoFactura = idProductoFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    
}
