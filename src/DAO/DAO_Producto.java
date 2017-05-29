/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseph
 */
public class DAO_Producto {

    Connection con = null;

    public DAO_Producto() {
    }

    public void conexion() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/zapateriamary", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarProducto(BL.BL_Producto producto) {
        conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (!producto.isEsZapato()) {

            try {
                ps = con.prepareStatement("Insert Into Producto Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1, producto.getCodigoUnico());
                ps.setString(2, producto.getFechaIngreso().toString());
                ps.setString(3, producto.getColor());
                ps.setString(4, producto.getMarca());
                ps.setString(5, producto.getEmpresa());
                ps.setDouble(6, producto.getPrecioCosto());
                ps.setDouble(7, producto.getPrecioImpuesto());
                ps.setDouble(8, producto.getPrecioGanancia());
                ps.setString(9, producto.getDescripcion());
                ps.setInt(10, producto.getCantidad());
                ps.setInt(11, 0);

                ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                int idProductoInsertado = 0;

                ps = con.prepareStatement("Insert Into Producto Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, producto.getCodigoUnico());
                ps.setString(2, producto.getFechaIngreso().toString());
                ps.setString(3, producto.getColor());
                ps.setString(4, producto.getMarca());
                ps.setString(5, producto.getEmpresa());
                ps.setDouble(6, producto.getPrecioCosto());
                ps.setDouble(7, producto.getPrecioImpuesto());
                ps.setDouble(8, producto.getPrecioGanancia());
                ps.setString(9, producto.getDescripcion());
                ps.setInt(10, producto.getCantidad());
                ps.setInt(11, 0);

                ps.executeUpdate();
                rs = ps.getGeneratedKeys();

                while (rs.next()) {
                    idProductoInsertado = rs.getInt(1);
                }

                ps = con.prepareStatement("Insert Into zapatotallacategoria Values "
                        + "(?, (Select IdCategoria From categoriatalla Where Descripcion = " + producto.getTallaZapato().getCategoriaZapato() + "), ?, ?)");
                ps.setInt(1, idProductoInsertado);
                ps.setDouble(2, producto.getTallaZapato().getTalla());
                ps.setString(3, producto.getTallaZapato().getGeneroZapato());

                ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cerrarConexion();
    }

    public int obtenerSiguienteCodigo() {

        conexion();
        int siguienteCodigo = 1, cantidadProductos = 0;
        Statement st = null;
        ResultSet rs = null;

        try {
            st = this.con.createStatement();
            rs = st.executeQuery("Select count(IdProducto) as CantidadProductos From producto");

            while (rs.next()) {
                cantidadProductos = rs.getInt(1);
            }

            if (cantidadProductos > 0) {
                rs = st.executeQuery("Select max(IdProducto) From Producto");
                while (rs.next()) {
                    siguienteCodigo = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        cerrarConexion();

        return siguienteCodigo;
    }

    public void cargarTodosProductos() {
        conexion();

        cerrarConexion();
    }
}
