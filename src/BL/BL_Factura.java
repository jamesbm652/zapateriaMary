/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.sql.Date;
import java.util.ArrayList;
import DAO.DAO_Factura;

/**
 *
 * @author Joseph
 */
public class BL_Factura {
    private int idFactura;
    private Date fechaFactura;
    private boolean cancelada;
    private String tipoFactura;
    private double montoAbonado;
    private ArrayList<BL_ProductoFactura> productosFactura  = new ArrayList<>();
    private BL_Cliente cliente;
    
    public BL_Factura() {
    }

    public BL_Factura(int idFactura, Date fechaFactura, boolean cancelada, String tipoFactura, BL_Cliente cliente) {
        this.idFactura = idFactura;
        this.fechaFactura = fechaFactura;
        this.cancelada = cancelada;
        this.tipoFactura = tipoFactura;
        this.cliente = cliente;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public double getMontoAbonado() {
        return montoAbonado;
    }

    public void setMontoAbonado(double montoAbonado) {
        this.montoAbonado = montoAbonado;
    }
    
    public ArrayList<BL_ProductoFactura> getProductosFactura() {
        return productosFactura;
    }

    public void setProductosFactura(ArrayList<BL_ProductoFactura> productosFactura) {
        this.productosFactura = productosFactura;
    }

    public BL_Cliente getCliente() {
        return cliente;
    }

    public void setCliente(BL_Cliente cliente) {
        this.cliente = cliente;
    }
    
    public boolean insertarFactura(){
        boolean insertado = false;
        insertado = new DAO_Factura().insertarFactura(this, cliente);
        return insertado;
    }
    
    public void abonarAFactura(double monto){
        new DAO_Factura().abonarAFactura(idFactura, monto);
    }
    
    public void cancelarFactura(){
        new DAO_Factura().cancelarFactura(idFactura);
    }
    
    public int siguienteNumFactura(){
        return new DAO_Factura().cargarSiguienteNumeroFactura();
    }
}
