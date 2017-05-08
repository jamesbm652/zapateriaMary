/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;
import DAO.DAO_Usuario;
/**
 *
 * @author James
 */
public class BL_Usuario {
    private int idUsuario;
    private String nombreCompleto;
    private String nombreUsuario;
    private String contrasena;
    private boolean administrador;

    public BL_Usuario() {
    }

    public BL_Usuario(int idUsuario, String nombreCompleto, String nombreUsuario, String contrasena, boolean administrador) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.administrador = administrador;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    
    
    public boolean login(String nombreUsuario, String contrasena){
        boolean logueo = false;
        DAO_Usuario daousuario = new DAO_Usuario();
        logueo = daousuario.logueo(this, nombreUsuario, contrasena);
        return logueo;
    }
    
    
}
