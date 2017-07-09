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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

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
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formato.format(producto.getFechaIngreso());

        conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertado = 0;

        if (!producto.isEsZapato()) {
            try {
                
                ps = con.prepareStatement("SELECT * FROM producto as P "
                        + "WHERE P.FechaIngreso = ? AND P.Marca = ? AND P.Empresa = ? AND P.Color = ?");
                ps.setString(1, fecha);
                ps.setString(2, producto.getMarca());
                ps.setString(3, producto.getEmpresa());
                ps.setString(4, producto.getColor());

                if ((ps.executeQuery().next())) {
                    return false;
                }
                
                ps = con.prepareStatement("Insert Into producto (CodigoUnico,FechaIngreso,Color,Marca,Empresa,PrecioCosto,PrecioImpuesto,PrecioGanancia, PrecioVenta,Descripcion,Cantidad,EsZapato) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1, producto.getCodigoUnico());
                ps.setString(2, fecha);
                ps.setString(3, producto.getColor());
                ps.setString(4, producto.getMarca());
                ps.setString(5, producto.getEmpresa());
                ps.setDouble(6, producto.getPrecioCosto());
                ps.setDouble(7, producto.getPrecioImpuesto());
                ps.setDouble(8, producto.getPrecioGanancia());
                ps.setDouble(9, producto.getPrecioVenta());
                ps.setString(10, producto.getDescripcion());
                ps.setInt(11, producto.getCantidad());
                ps.setInt(12, 0);

                insertado = ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                int idProductoInsertado = 0;
                
                ps = con.prepareStatement("SELECT * FROM producto as P INNER JOIN zapatotallacategoria as ZTC "
                        + "ON P.IdProducto = ZTC.IdProducto "
                        + "WHERE P.FechaIngreso = ? AND P.Marca = ? AND P.Empresa = ? AND P.Color = ? AND ZTC.Talla = ?");
                ps.setString(1, fecha);
                ps.setString(2, producto.getMarca());
                ps.setString(3, producto.getEmpresa());
                ps.setString(4, producto.getColor());
                ps.setDouble(5, producto.getTallaZapato().getTalla());

                if ((ps.executeQuery().next())) {
                    return false;
                }
                ps = con.prepareStatement("Insert Into producto (CodigoUnico,FechaIngreso,Color,Marca,Empresa,PrecioCosto,PrecioImpuesto,PrecioGanancia, PrecioVenta,Descripcion,Cantidad,EsZapato) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", ps.RETURN_GENERATED_KEYS);
                ps.setString(1, producto.getCodigoUnico());
                ps.setString(2, fecha);
                ps.setString(3, producto.getColor());
                ps.setString(4, producto.getMarca());
                ps.setString(5, producto.getEmpresa());
                ps.setDouble(6, producto.getPrecioCosto());
                ps.setDouble(7, producto.getPrecioImpuesto());
                ps.setDouble(8, producto.getPrecioGanancia());
                ps.setDouble(9, producto.getPrecioVenta());
                ps.setString(10, producto.getDescripcion());
                ps.setInt(11, producto.getCantidad());
                ps.setInt(12, 1);

                ps.executeUpdate();
                rs = ps.getGeneratedKeys();

                while (rs.next()) {
                    idProductoInsertado = rs.getInt(1);
                }

                ps = con.prepareStatement("Insert Into zapateriamary.zapatotallacategoria (IdProducto,IdCategoria,Talla,Genero) Values "
                        + "(?, (Select IdCategoria From zapateriamary.categoriatalla Where Descripcion = '" + producto.getTallaZapato().getCategoriaZapato() + "'), ?, ?)");
                ps.setInt(1, idProductoInsertado);
                ps.setDouble(2, producto.getTallaZapato().getTalla());
                if (producto.getTallaZapato().getGeneroZapato().equals("M")) {
                    ps.setInt(3, 1);
                } else {
                    ps.setInt(3, 0);
                }

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
                rs = st.executeQuery("Select max(IdProducto) From producto");
                while (rs.next()) {
                    siguienteCodigo = rs.getInt(1) + 1;
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
            rs = st.executeQuery("SELECT DISTINCT P.IdProducto, P.CodigoUnico, P.FechaIngreso, P.Color, P.Marca, P.Empresa, P.PrecioCosto, P.PrecioImpuesto, P.PrecioGanancia, P.PrecioVenta, P.Descripcion, "
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
                prod.setMarca(rs.getString("Marca"));
                prod.setEmpresa(rs.getString("Empresa"));
                prod.setPrecioCosto(rs.getDouble("PrecioCosto"));
                prod.setPrecioImpuesto(rs.getDouble("PrecioImpuesto"));
                prod.setPrecioGanancia(rs.getDouble("PrecioGanancia"));
                prod.setPrecioVenta(rs.getDouble("PrecioVenta"));
                prod.setDescripcion(rs.getString("Descripcion"));
                prod.setCantidad(rs.getInt("Cantidad"));
                prod.setEsZapato(true);

                talla.setTalla(rs.getDouble("Talla"));
                talla.setGeneroZapato(rs.getString("Genero"));
                talla.setCategoriaZapato(rs.getString("Categoria"));

                prod.setTallaZapato(talla);

                manejador.Agregar(prod);
            }
            rs = st.executeQuery("SELECT DISTINCT P.IdProducto, P.CodigoUnico, P.FechaIngreso, P.Color, P.Marca, P.Empresa, P.PrecioCosto, P.PrecioImpuesto, P.PrecioGanancia, P.PrecioVenta, P.Descripcion, "
                    + "P.Cantidad, P.EsZapato FROM zapateriamary.producto P Where P.EsZapato = 0");

            while (rs.next()) {
                BL_Producto prod = new BL_Producto();

                prod.setIdProducto(rs.getInt("IdProducto"));
                prod.setCodigoUnico(rs.getString("CodigoUnico"));
                prod.setFechaIngreso(rs.getDate("FechaIngreso"));
                prod.setColor(rs.getString("Color"));
                prod.setMarca(rs.getString("Marca"));
                prod.setEmpresa(rs.getString("Empresa"));
                prod.setPrecioCosto(rs.getDouble("PrecioCosto"));
                prod.setPrecioImpuesto(rs.getDouble("PrecioImpuesto"));
                prod.setPrecioGanancia(rs.getDouble("PrecioGanancia"));
                prod.setPrecioVenta(rs.getDouble("PrecioVenta"));
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

    public ArrayList<BL_Producto> cargarProductosPorFiltro(String genero, String color, double tallaZapato, String marca, String empresa,
            double precio, Date fecha, String categoria, boolean tipoProducto, boolean ambos) {

        BL_ManejadorProducto manejador = new BL_ManejadorProducto();

        conexion();

        Statement st = null;
        ResultSet rs = null;
        
        int c = 1;
        if (ambos && genero.equals("") && tallaZapato == 0 && categoria.equals("")) c = 2;  
        
        // For por si la busquedas de tipo de producto es cualquiera
        for (int i = 0; i < c; i++) {
            if (i == 1) {
                tipoProducto = false;
                genero = "";
                categoria = "";
                tallaZapato = 0;
            }

            String query = "";

            if (!tipoProducto && genero.equals("") && tallaZapato == 0 && categoria.equals("")) {
                query = "SELECT DISTINCT P.IdProducto, P.CodigoUnico, P.FechaIngreso, P.Color, P.Marca, P.Empresa, P.PrecioCosto, P.PrecioImpuesto, P.PrecioGanancia, P.PrecioVenta, P.Descripcion, "
                        + "P.Cantidad, P.EsZapato FROM zapateriamary.producto as P Where P.EsZapato = 0";
            } else {
                query = "SELECT DISTINCT P.IdProducto, P.CodigoUnico, P.FechaIngreso, P.Color, P.Marca, P.Empresa, P.PrecioCosto, P.PrecioImpuesto, P.PrecioGanancia, P.PrecioVenta, P.Descripcion, "
                        + "P.Cantidad, P.EsZapato, ZC.Talla, ZC.Genero, CT.Descripcion as Categoria FROM zapateriamary.producto as P INNER JOIN zapateriamary.zapatotallacategoria as ZC on "
                        + "P.IdProducto = ZC.IdProducto INNER JOIN zapateriamary.categoriatalla as CT on "
                        + "ZC.IdCategoria = CT.IdCategoria Where P.EsZapato = 1";
            }
            if (genero != "") {
                if (genero.equals("Hombre")) {
                    query += " And ZC.Genero = " + 1 + "";
                } else {
                    query += " And ZC.Genero = " + 0 + "";
                }
            }
            if (!color.equals("")) {
                query += " And P.Color = '" + color + "'";
            }
            if (tallaZapato > 0) {
                query += " And ZC.Talla = " + tallaZapato + "";
            }
            if (!marca.equals("")) {
                query += " And P.Marca = '" + marca + "'";
            }
            if (!empresa.equals("")) {
                query += " And P.Empresa = '" + empresa + "'";
            }
            if (precio > 0) {
                query += " And P.PrecioVenta = " + precio + "";
            }
            if (fecha != null) {
                query += " And P.FechaIngreso = '" + fecha.toString() + "'";
            }
            if (!categoria.equals("")) {
                query += " And CT.Descripcion = '" + categoria + "'";
            }

            try {
                st = this.con.createStatement();
                rs = st.executeQuery(query);

                if (tipoProducto) {
                    while (rs.next()) {
                        BL_Producto prod = new BL_Producto();
                        BL_TallaZapato talla = new BL_TallaZapato();

                        prod.setIdProducto(rs.getInt("IdProducto"));
                        prod.setCodigoUnico(rs.getString("CodigoUnico"));
                        prod.setFechaIngreso(rs.getDate("FechaIngreso"));
                        prod.setColor(rs.getString("Color"));
                        prod.setMarca(rs.getString("Marca"));
                        prod.setEmpresa(rs.getString("Empresa"));
                        prod.setPrecioCosto(rs.getDouble("PrecioCosto"));
                        prod.setPrecioImpuesto(rs.getDouble("PrecioImpuesto"));
                        prod.setPrecioGanancia(rs.getDouble("PrecioGanancia"));
                        prod.setPrecioVenta(rs.getDouble("PrecioVenta"));
                        prod.setDescripcion(rs.getString("Descripcion"));
                        prod.setCantidad(rs.getInt("Cantidad"));
                        prod.setEsZapato(true);

                        talla.setTalla(rs.getDouble("Talla"));
                        talla.setGeneroZapato(rs.getString("Genero"));
                        talla.setCategoriaZapato(rs.getString("Categoria"));

                        prod.setTallaZapato(talla);

                        manejador.Agregar(prod);
                    }
                } else {
                    while (rs.next()) {
                        BL_Producto prod = new BL_Producto();

                        prod.setIdProducto(rs.getInt("IdProducto"));
                        prod.setCodigoUnico(rs.getString("CodigoUnico"));
                        prod.setFechaIngreso(rs.getDate("FechaIngreso"));
                        prod.setColor(rs.getString("Color"));
                        prod.setMarca(rs.getString("Marca"));
                        prod.setEmpresa(rs.getString("Empresa"));
                        prod.setPrecioCosto(rs.getDouble("PrecioCosto"));
                        prod.setPrecioImpuesto(rs.getDouble("PrecioImpuesto"));
                        prod.setPrecioGanancia(rs.getDouble("PrecioGanancia"));
                        prod.setPrecioVenta(rs.getDouble("PrecioVenta"));
                        prod.setDescripcion(rs.getString("Descripcion"));
                        prod.setCantidad(rs.getInt("Cantidad"));
                        prod.setEsZapato(false);
                        manejador.Agregar(prod);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } // Cierre del for por si la busquedas de tipo de producto es cualquiera

        cerrarConexion();
        return manejador.ObtenerListaProductos();
    }

    public boolean eliminarProducto(int id) {

        if (!validarEliminacion(id)) {
            conexion();

            PreparedStatement ps = null;

            try {
                ps = con.prepareStatement("Delete From zapatotallacategoria Where IdProducto = ?");
                ps.setInt(1, id);
                ps.execute();

                ps = con.prepareStatement("Delete From producto Where IdProducto = ?");
                ps.setInt(1, id);
                ps.execute();

            } catch (SQLException ex) {
                Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
            cerrarConexion();
            return true;
        }
        return false;
    }

    public boolean validarEliminacion(int id) {
        boolean eliminar = false;
        conexion();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("Select * From productofactura Where IdProducto = ?");
            ps.setInt(1, id);
            if (ps.executeQuery().next()) {
                eliminar = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        cerrarConexion();

        return eliminar;
    }

    public boolean modificarProducto(BL_Producto prod) {
        boolean modificado = false;
        int insertado = 0;
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formato.format(prod.getFechaIngreso());

        conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (prod.isEsZapato()) {
                ps = con.prepareStatement("Update producto Set CodigoUnico = ?, FechaIngreso = ?, "
                        + " Color = ?, Marca = ?, Empresa = ?, PrecioCosto = ?, PrecioImpuesto = ?,"
                        + " PrecioGanancia = ?, PrecioVenta = ?, Descripcion = ?, Cantidad = ?, EsZapato = 1 Where IdProducto = ?");
                ps.setString(1, prod.getCodigoUnico());
                ps.setString(2, fecha);
                ps.setString(3, prod.getColor());
                ps.setString(4, prod.getMarca());
                ps.setString(5, prod.getEmpresa());
                ps.setDouble(6, prod.getPrecioCosto());
                ps.setDouble(7, prod.getPrecioImpuesto());
                ps.setDouble(8, prod.getPrecioGanancia());
                ps.setDouble(9, prod.getPrecioVenta());
                ps.setString(10, prod.getDescripcion());
                ps.setInt(11, prod.getCantidad());
                ps.setInt(12, prod.getIdProducto());

                insertado = ps.executeUpdate();
                if (insertado > 0) {
                    ps = con.prepareStatement("Update zapatotallacategoria "
                            + "Set IdCategoria = (Select IdCategoria From categoriatalla Where Descripcion = '" + prod.getTallaZapato().getCategoriaZapato() + "'), "
                            + " Talla = ?, Genero = ?");
                    ps.setDouble(1, prod.getTallaZapato().getTalla());

                    if (prod.getTallaZapato().getGeneroZapato().equals("M")) {
                        ps.setInt(2, 1);
                    } else {
                        ps.setInt(2, 0);
                    }
                    insertado = ps.executeUpdate();
                }

            } else {
                ps = con.prepareStatement("Update producto Set CodigoUnico = ?, FechaIngreso = ?, "
                        + " Color = ?, Marca = ?, Empresa = ?, PrecioCosto = ?, PrecioImpuesto = ?,"
                        + " PrecioGanancia = ?, Descripcion = ?, Cantidad = ?, EsZapato = 0 Where IdProducto = ?");
                ps.setString(1, prod.getCodigoUnico());
                ps.setString(2, fecha);
                ps.setString(3, prod.getColor());
                ps.setString(4, prod.getMarca());
                ps.setString(5, prod.getEmpresa());
                ps.setDouble(6, prod.getPrecioCosto());
                ps.setDouble(7, prod.getPrecioImpuesto());
                ps.setDouble(8, prod.getPrecioGanancia());
                ps.setString(9, prod.getDescripcion());
                ps.setInt(10, prod.getCantidad());
                ps.setInt(11, prod.getIdProducto());

                insertado = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (insertado > 0) {
            modificado = true;
        }

        cerrarConexion();
        return modificado;
    }

}
