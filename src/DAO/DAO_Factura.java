/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import BL.BL_ManejadorFacturas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.naming.spi.DirStateFactory;

import BL.BL_Factura;
import BL.BL_Cliente;
import BL.BL_ManejadorFacturas;
import BL.BL_ProductoFactura;
import BL.BL_ManejadorProductoFactura;
import com.mysql.jdbc.Statement;

/**
 *
 * @author Joseph
 */
public class DAO_Factura {

    Connection con = null;

    public DAO_Factura() {
    }

    public void conexion() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/zapateriamary", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertarFactura(BL_Factura factura, BL_Cliente cliente) {
        int completado = 0;
        int facturaIngresada = 0;
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formato.format(factura.getFechaFactura());

        conexion();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("Insert into factura (Fecha, Cancelada, TipoFactura) Values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fecha);
            if (factura.getTipoFactura().equals("Crédito") || factura.getTipoFactura().equals("Apartado")) {
                ps.setInt(2, 0);
            } else {
                ps.setInt(2, 1);
            }
            ps.setString(3, factura.getTipoFactura());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            while (rs.next()) {
                facturaIngresada = rs.getInt(1);
            }

            //Se procede a insertar ClienteFactura
            int idCliente = new DAO_Cliente().obtenerClienteFactura(cliente);
            ps = con.prepareStatement("Insert into clienteFactura (IdFactura, IdCliente) Values (?, ?)");
            ps.setInt(1, facturaIngresada);
            ps.setInt(2, idCliente);
            ps.executeUpdate();

            //Se proceden a insertar los detalles de la factura
            for (int i = 0; i < factura.getProductosFactura().size(); i++) {
                ps = con.prepareStatement("Insert into productofactura (IdProducto, IdFactura, Cantidad"
                        + ", PrecioVenta, Descripcion) Values(?, ?, ?, ?, ?)");
                ps.setInt(1, factura.getProductosFactura().get(i).getIdProducto());
                ps.setInt(2, facturaIngresada);
                ps.setInt(3, factura.getProductosFactura().get(i).getCantidadVendida());
                ps.setDouble(4, factura.getProductosFactura().get(i).getPrecioVenta());
                ps.setString(5, factura.getProductosFactura().get(i).getDescripcion());
                completado = ps.executeUpdate();
            }
            
            
            //Se proceden a actualizar los productos vendidos.
            if (completado > 0) {
                completado = 0;
                for (int i = 0; i < factura.getProductosFactura().size(); i++) {
                BL_ProductoFactura prod = factura.getProductosFactura().get(i);
                ps = con.prepareStatement("Update producto Set Cantidad = (Cantidad - ?) Where IdProducto = ?");
                ps.setInt(1, prod.getCantidadVendida());
                ps.setInt(2, factura.getProductosFactura().get(i).getIdProducto());
                completado = ps.executeUpdate();
            }
            }
            
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (completado > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void cargarFacturasPorCliente(BL_ManejadorFacturas listaFactura, String cedula) {

        conexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsDetalles = null;

        try {
            ps = con.prepareStatement("Select F.IdFactura, F.Fecha, F.Cancelada, "
                    + "F.TipoFactura, C.IdCliente, C.NombreCompleto, C.Cedula, "
                    + "C.Direccion From factura as F Inner Join clientefactura "
                    + "as CF on F.IdFactura = CF.IdFactura Inner Join cliente as "
                    + "C on C.IdCliente = CF.IdCliente Where C.Cedula = ? ");
            ps.setString(1, cedula);
            rs = ps.executeQuery();

            while (rs.next()) {
                BL_Cliente cliente = new BL_Cliente();
                BL_Factura factura = new BL_Factura();
                BL_ManejadorProductoFactura detallesFactura = new BL_ManejadorProductoFactura();
                
                factura.setIdFactura(rs.getInt(1));
                factura.setFechaFactura(rs.getDate(2));
                if (rs.getInt(3) == 1) {
                    factura.setCancelada(true);
                }else{
                    factura.setCancelada(false);
                }
                factura.setTipoFactura(rs.getString(4));
                
                cliente.setIdCliente(rs.getInt(5));
                cliente.setNombreCompleto(rs.getString(6));
                cliente.setCedula(rs.getString(7));
                cliente.setDireccion(rs.getString(cedula));
                factura.setCliente(cliente);
                
                ps = con.prepareStatement("Select Cantidad, PrecioVenta, Descripcion "
                        + "From productofactura Where IdFactura = ?");
                ps.setInt(1, factura.getIdFactura());
                rsDetalles = ps.executeQuery();
                
                while (rsDetalles.next()) {                    
                    BL_ProductoFactura prodFactura = new BL_ProductoFactura();
                    
                    prodFactura.setCantidadVendida(rsDetalles.getInt(1));
                    prodFactura.setPrecioVenta(rsDetalles.getDouble(2));
                    prodFactura.setDescripcion(rs.getString(3));
                    detallesFactura.Agregar(prodFactura);
                }
                factura.setProductosFactura(detallesFactura.ObtenerLista());
                listaFactura.Agregar(factura);
            }
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
