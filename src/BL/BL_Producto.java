/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DAO.DAO_Producto;
import java.sql.Date;

/**
 *
 * @author joseph
 */
public class BL_Producto {

    private int idProducto;
    private String codigoUnico;
    private Date fechaIngreso;
    private String color;
    private String marca;
    private String empresa;
    private double precioCosto;
    private double precioImpuesto;
    private double precioGanancia;
    private String descripcion;
    private int cantidad;
    private boolean esZapato;
    private BL_TallaZapato tallaZapato;

    public BL_Producto() {
    }

    public BL_Producto(int idProducto, String codigoUnico, Date fechaIngreso, String color, String marca, String empresa, double precioCosto, double precioImpuesto, double precioGanancia, String descripcion, int cantidad, boolean esZapato) {
        this.idProducto = idProducto;
        this.codigoUnico = codigoUnico;
        this.fechaIngreso = fechaIngreso;
        this.color = color;
        this.marca = marca;
        this.empresa = empresa;
        this.precioCosto = precioCosto;
        this.precioImpuesto = precioImpuesto;
        this.precioGanancia = precioGanancia;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.esZapato = esZapato;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public double getPrecioImpuesto() {
        return precioImpuesto;
    }

    public void setPrecioImpuesto(double precioImpuesto) {
        this.precioImpuesto = precioImpuesto;
    }

    public double getPrecioGanancia() {
        return precioGanancia;
    }

    public void setPrecioGanancia(double precioGanancia) {
        this.precioGanancia = precioGanancia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEsZapato() {
        return esZapato;
    }

    public void setEsZapato(boolean esZapato) {
        this.esZapato = esZapato;
    }

    public BL_TallaZapato getTallaZapato() {
        return tallaZapato;
    }

    public void setTallaZapato(BL_TallaZapato tallaZapato) {
        this.tallaZapato = tallaZapato;
    }

    public Boolean insertarZapato() {
        return new DAO_Producto().insertarProducto(this);
    }

}
