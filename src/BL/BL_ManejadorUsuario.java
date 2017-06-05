/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DAO.DAO_Usuario;
import java.util.ArrayList;

/**
 *
 * @author oscal
 */
public class BL_ManejadorUsuario {
    private ArrayList<BL_Usuario> listaUsuarios = new ArrayList<>();

    public ArrayList<BL_Usuario> ObtenerListaUsuarios() {
        return listaUsuarios;
    }

    public void Agregar(BL_Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public void Eliminar(int identificador) {
        listaUsuarios.remove(identificador);
    }
    
    public void Modificar(int identificador, BL_Usuario usuario){
        listaUsuarios.set(identificador, usuario);
    }
    
    public void CargarUsuarios(){
        listaUsuarios = new DAO_Usuario().cargarUsuarios();
    }
    
}
