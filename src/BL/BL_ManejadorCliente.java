/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

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
        listaClientes = new DAO.DAO_Cliente().cargarTodosClientes();
    }
    
    public DefaultComboBoxModel obtenerListaComboBox(String cadena, String tipoFiltro){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        switch(tipoFiltro){
            case "NombreCompleto":
                for (BL_Cliente c : listaClientes) {
                    if (c.getNombreCompleto().toLowerCase().startsWith(cadena.toLowerCase())) model.addElement(c.getNombreCompleto());
                }       
                break;
            case "Cedula":
                for (BL_Cliente c : listaClientes) {
                    if(c.getCedula().toLowerCase().startsWith(cadena.toLowerCase())) model.addElement(c.getCedula());
                }
                break;
        }
        return model;
        //return new DAO.DAO_Cliente().obtenerListaComboBox(cadena, tipoFiltro);
    }
    
}
