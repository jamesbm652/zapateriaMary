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
public class BL_ManejadorProductoFactura {
    private ArrayList<BL_ProductoFactura> listaProdFactura = new ArrayList<>();

    public BL_ManejadorProductoFactura() {
    }
    
    public void Agregar(BL_ProductoFactura prod){
        listaProdFactura.add(prod);
    }
    
    public void Eliminar(BL_Producto producto) {
        listaProdFactura.remove(producto);
    }
    
    public ArrayList<BL_ProductoFactura> ObtenerLista(){
        return listaProdFactura;
    }
}
