/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;

/**
 *
 * @author James
 */
public class BL_ManejadorCliente {
    private ArrayList<BL_Cliente> listaClientes = new ArrayList<>();

    public BL_ManejadorCliente() {
    }
    
    public void agregar(BL_Cliente c){
        listaClientes.add(c);
    }
    
    public void eliminar(BL_Cliente c){
        listaClientes.remove(c);
    }
    
    public ArrayList<BL_Cliente> obtenerLista(){
        return listaClientes;
    }
    
    public void cargarClientes(){
        // llamar al DAO
    }
}