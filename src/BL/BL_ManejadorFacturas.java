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
public class BL_ManejadorFacturas {
    private ArrayList<BL_Factura> listaFacturas = new ArrayList<>();

    public BL_ManejadorFacturas() {
    }
    
    public void Agregar(BL_Factura prod){
        listaFacturas.add(prod);
    }
    
    public void Eliminar(BL_Producto producto) {
        listaFacturas.remove(producto);
    }
    
    public ArrayList<BL_Factura> ObtenerLista(){
        return listaFacturas;
    }
}
