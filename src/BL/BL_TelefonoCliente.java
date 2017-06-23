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
public class BL_TelefonoCliente {
    private String telefono;
    private String tipoTelefono;

    public BL_TelefonoCliente() {
    }

    public String getTelefono() {
        return telefono;
    }

    public BL_TelefonoCliente(String telefono, String tipoTelefono) {
        this.telefono = telefono;
        this.tipoTelefono = tipoTelefono;
    }
    

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(String tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }
    
    
}
