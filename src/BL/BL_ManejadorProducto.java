/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author oscal
 */
public class BL_ManejadorProducto {

    private ArrayList<BL_Producto> listaProducto = new ArrayList<>();

    public ArrayList<BL_Producto> ObtenerListaProductos() {
        return listaProducto;
    }

    public void Agregar(BL_Producto producto) {
        listaProducto.add(producto);
    }

    public void Eliminar(BL_Producto producto) {
        listaProducto.remove(producto);
    }

    public void CargarProductos() {
        listaProducto = new DAO.DAO_Producto().cargarTodosProductos();
    }

    public void BuscarPorFiltro(String genero, String color, double talla, String marca, String empresa, double precio, Date fecha, String categoria, boolean tipoProd) {
        listaProducto = new DAO.DAO_Producto().cargarProductosPorFiltro(genero, color, talla, marca, empresa, precio, fecha, categoria, tipoProd);
    }

    public void metodoPrueba() {
    }
}
