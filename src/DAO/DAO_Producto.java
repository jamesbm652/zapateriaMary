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
import BL.BL_ManejadorProducto;
import BL.BL_Producto;
import BL.BL_TallaZapato;
import java.util.ArrayList;

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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/zapateriamary", "root", "1234");
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

    public boolean insertarProducto(BL.BL_Producto producto) {
        conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertado = 0;

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
                ps.setInt(11, 1);

                insertado = ps.executeUpdate();

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

                insertado = ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        cerrarConexion();

        if (insertado > 0) {
            return true;
        } else {
            return false;
        }
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

    public ArrayList<BL_Producto> cargarTodosProductos() {
        conexion();

        Statement st = null;
        ResultSet rs = null;
        BL_ManejadorProducto manejador = new BL_ManejadorProducto();

        try {
            st = this.con.createStatement();
            rs = st.executeQuery("SELECT P.IdProducto, P.CodigoUnico, P.FechaIngreso, P.Color, P.Marca, P.Empresa, P.PrecioImpuesto, P.Descripcion, "
                    + "P.Cantidad, P.EsZapato, ZC.Talla, ZC.Genero, CT.Descripcion as Categoria FROM zapateriamary.producto as P INNER JOIN zapateriamary.zapatotallacategoria as ZC on "
                    + "P.IdProducto = ZC.IdProducto INNER JOIN zapateriamary.categoriatalla as CT on "
                    + "ZC.IdCategoria = CT.IdCategoria Where P.EsZapato = 1;");

            while (rs.next()) {
                BL_Producto prod = new BL_Producto();
                BL_TallaZapato talla = new BL_TallaZapato();

                prod.setIdProducto(rs.getInt("IdProducto"));
                prod.setCodigoUnico(rs.getString("CodigoUnico"));
                prod.setFechaIngreso(rs.getDate("FechaIngreso"));
                prod.setColor(rs.getString("Color"));
                prod.setMarca("Marca");
                prod.setEmpresa("Empresa");
                prod.setPrecioCosto(rs.getDouble("PrecioCosto"));
                prod.setPrecioImpuesto(rs.getDouble("PrecioImpuesto"));
                prod.setPrecioGanancia(rs.getDouble("PrecioGanancia"));
                prod.setDescripcion(rs.getString("Descripcion"));
                prod.setCantidad(rs.getInt("Cantidad"));
                prod.setEsZapato(true);

                talla.setTalla(rs.getDouble("Talla"));
                talla.setGeneroZapato(rs.getString("Genero"));
                talla.setCategoriaZapato("Categoria");

                prod.setTallaZapato(talla);

                manejador.Agregar(prod);
            }

            rs = st.executeQuery("SELECT P.IdProducto, P.CodigoUnico, P.FechaIngreso, P.Color, P.Marca, P.Empresa, P.PrecioImpuesto, P.Descripcion, "
                    + "P.Cantidad, P.EsZapato, ZC.Talla, ZC.Genero, CT.Descripcion as Categoria FROM zapateriamary.producto Where P.EsZapato = 0");

            while (rs.next()) {
                BL_Producto prod = new BL_Producto();

                prod.setIdProducto(rs.getInt("IdProducto"));
                prod.setCodigoUnico(rs.getString("CodigoUnico"));
                prod.setFechaIngreso(rs.getDate("FechaIngreso"));
                prod.setColor(rs.getString("Color"));
                prod.setMarca("Marca");
                prod.setEmpresa("Empresa");
                prod.setPrecioCosto(rs.getDouble("PrecioCosto"));
                prod.setPrecioImpuesto(rs.getDouble("PrecioImpuesto"));
                prod.setPrecioGanancia(rs.getDouble("PrecioGanancia"));
                prod.setDescripcion(rs.getString("Descripcion"));
                prod.setCantidad(rs.getInt("Cantidad"));
                prod.setEsZapato(false);
                manejador.Agregar(prod);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion();
        return manejador.ObtenerListaProductos();
    }

    public ArrayList<BL_Producto> cargarProductosPorFiltro() {
        BL_ManejadorProducto manejador = new BL_ManejadorProducto();
        conexion();
        Statement st = null;
        ResultSet rs = null;

        String queryBase = "SELECT P.IdProducto, P.CodigoUnico, P.FechaIngreso, P.Color, P.Marca, P.Empresa, P.PrecioImpuesto, P.Descripcion, "
                + "P.Cantidad, P.EsZapato ";

        String queryWhere = "Where ";

        String queryFromJoins = "From producto as P";

        cerrarConexion();
        return manejador.ObtenerListaProductos();
    }

}
