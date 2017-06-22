/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class BL_Cliente {
    private int idCliente;
    private String nombreCompleto;
    private String cedula;
    private String direccion;
    private ArrayList<BL_TelefonoCliente> listaTelefonos = new ArrayList<>();

    public BL_Cliente() {
    }

    public BL_Cliente(int idCliente, String nombreCompleto, String cedula, String direccion) {
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public ArrayList<BL_TelefonoCliente> getListaTelefonos() {
        return listaTelefonos;
    }

    public void setListaTelefonos(ArrayList<BL_TelefonoCliente> listaTelefonos) {
        this.listaTelefonos = listaTelefonos;
    }
    
    
}
