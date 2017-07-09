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
import BL.BL_TelefonoCliente;
import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.util.ArrayList;

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
            con = DriverManager.getConnection("jdbc:mysql://localhost/zapateriamary", "root", "1234");
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

    public void cargarFacturasPorCliente(BL_ManejadorFacturas mFacturas, int idCliente) {

        conexion();

        PreparedStatement ps = null;
        PreparedStatement psM = null;
        ResultSet rs = null;
        ResultSet rsDetalles = null;
        ResultSet rsMonto = null;

        try {
            ps = con.prepareStatement("SELECT distinct f.IdFactura,f.Fecha,f.Cancelada,f.TipoFactura "
                    + "FROM factura f INNER JOIN clientefactura cf ON cf.IdFactura = f.IdFactura "
                    + "WHERE f.Cancelada = 0 AND cf.IdCliente = ? GROUP BY f.IdFactura ORDER BY f.Fecha ASC");
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                BL_Factura factura = new BL_Factura();
                BL_ManejadorProductoFactura detallesFactura = new BL_ManejadorProductoFactura();

                factura.setIdFactura(rs.getInt(1));
                factura.setFechaFactura(rs.getDate(2));
                if (rs.getInt(3) == 1) {
                    factura.setCancelada(true);
                } else {
                    factura.setCancelada(false);
                }
                factura.setTipoFactura(rs.getString(4));
     
                psM = con.prepareStatement("Select Sum(MontoAbonar) FROM abono WHERE IdFactura = ?");

                psM.setInt(1, factura.getIdFactura());

                rsMonto = psM.executeQuery();

                if (rsMonto.next()) {
                    factura.setMontoAbonado(rsMonto.getDouble(1));
                } else {
                    factura.setMontoAbonado(0);
                }

                ps = con.prepareStatement("Select Cantidad, PrecioVenta, Descripcion "
                        + "From productofactura Where IdFactura = ?");
                ps.setInt(1, factura.getIdFactura());
                rsDetalles = ps.executeQuery();

                while (rsDetalles.next()) {
                    BL_ProductoFactura prodFactura = new BL_ProductoFactura();

                    prodFactura.setCantidadVendida(rsDetalles.getInt(1));
                    prodFactura.setPrecioVenta(rsDetalles.getDouble(2));
                    prodFactura.setDescripcion(rsDetalles.getString(3));
                    detallesFactura.Agregar(prodFactura);
                }
                factura.setProductosFactura(detallesFactura.ObtenerLista());
                mFacturas.Agregar(factura);
            }
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abonarAFactura(int idFactura, double abono) {
        conexion();

        PreparedStatement ps = null;
        ResultSet rs = null;

        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formato.format(new Date(new java.util.Date().getTime()));

        try {
            ps = con.prepareStatement("Insert into abono (idFactura, Fecha, MontoAbonar) Values (?,?,?)");
            ps.setInt(1, idFactura);
            ps.setString(2, fechaActual);
            ps.setDouble(3, abono);

            ps.executeUpdate();

            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelarFactura(int idFactura) {
        conexion();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("UPDATE factura SET Cancelada = 1 WHERE idFactura = ?");
            ps.setInt(1, idFactura);

            ps.executeUpdate();

            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarFacturasPorFecha(BL_ManejadorFacturas mFacturas, String fInicio, String fFinal) {
        conexion();

        PreparedStatement ps = null;
        PreparedStatement psM = null;
        PreparedStatement psCliente = null;
        ResultSet rs = null;
        ResultSet rsDetalles = null;
        ResultSet rsMonto = null;
        ResultSet rsCliente = null;
        ResultSet rsTelefonos = null;

        try {
            ps = con.prepareStatement("SELECT f.IdFactura, f.Fecha, f.Cancelada, f.TipoFactura FROM factura f"
                    + " WHERE f.Fecha BETWEEN '" + fInicio + "' AND '" + fFinal + "'");

            rs = ps.executeQuery();

            while (rs.next()) {
                BL_Factura factura = new BL_Factura();
                BL_ManejadorProductoFactura detallesFactura = new BL_ManejadorProductoFactura();

                factura.setIdFactura(rs.getInt(1));
                factura.setFechaFactura(rs.getDate(2));
                if (rs.getInt(3) == 1) {
                    factura.setCancelada(true);
                } else {
                    factura.setCancelada(false);
                }
                factura.setTipoFactura(rs.getString(4));

                psM = con.prepareStatement("Select MontoAbonar FROM abono WHERE IdFactura = ?");
                psM.setInt(1, factura.getIdFactura());

                rsMonto = psM.executeQuery();

                if (rsMonto.next()) {
                    factura.setMontoAbonado(rsMonto.getDouble(1));
                } else {
                    factura.setMontoAbonado(0);
                }

                ps = con.prepareStatement("Select IdProducto, Cantidad, PrecioVenta, Descripcion "
                        + "From productofactura Where IdFactura = ?");
                ps.setInt(1, factura.getIdFactura());
                rsDetalles = ps.executeQuery();

                while (rsDetalles.next()) {
                    BL_ProductoFactura prodFactura = new BL_ProductoFactura();
                    prodFactura.setIdProductoFactura(factura.getIdFactura());
                    
                    prodFactura.setIdProducto(rsDetalles.getInt(1));
                    prodFactura.setCantidadVendida(rsDetalles.getInt(2));
                    prodFactura.setPrecioVenta(rsDetalles.getDouble(3));
                    prodFactura.setDescripcion(rsDetalles.getString(4));
                    detallesFactura.Agregar(prodFactura);
                }
                factura.setProductosFactura(detallesFactura.ObtenerLista());

                psCliente = con.prepareStatement("SELECT c.IdCliente,c.NombreCompleto, c.Cedula, c.Direccion FROM cliente c INNER JOIN clientefactura cf "
                        + " ON cf.IdCliente = c.IdCliente WHERE cf.IdFactura = ?");
                psCliente.setInt(1, factura.getIdFactura());

                rsCliente = psCliente.executeQuery();

                if (rsCliente.next()) {
                    BL_Cliente cliente = new BL_Cliente(rsCliente.getInt(1), rsCliente.getString(2), rsCliente.getString(3), rsCliente.getString(4));

                    psCliente = con.prepareStatement("SELECT Telefono, TipoTelefono FROM telefonocliente WHERE IdCliente = ?");
                    psCliente.setInt(1, cliente.getIdCliente());

                    rsTelefonos = psCliente.executeQuery();

                    while (rsTelefonos.next()) {
                        cliente.getListaTelefonos().add(new BL_TelefonoCliente(rsTelefonos.getString(1), rsTelefonos.getString(2)));
                    }
                    factura.setCliente(cliente);
                }

                mFacturas.Agregar(factura);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarFacturasPorFiltro(BL_ManejadorFacturas mFacturas, Date fechaInicial, Date fechaFinal, 
            int estado, String tipo, int numFactura) {
        
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        boolean fechasCorrectas = false;
        String fechaInicio= "";
        String fechaFin = "";
        if (fechaInicial != null && fechaFinal != null) {
            fechaInicio = formato.format(fechaInicial) + "";
            fechaFin = formato.format(fechaFinal) + "";
            fechasCorrectas = true;
        }

        conexion();

        PreparedStatement ps = null;
        PreparedStatement psM = null;
        PreparedStatement psCliente = null;
        ResultSet rs = null;
        ResultSet rsDetalles = null;
        ResultSet rsMonto = null;
        ResultSet rsCliente = null;
        ResultSet rsTelefonos = null;
        
        mFacturas.EliminarTodos();
        
        String query = "SELECT f.IdFactura, f.Fecha, f.Cancelada, f.TipoFactura FROM factura f Where IdFactura > 0 ";

        try {
            if (fechasCorrectas) {
                query += "And (Fecha Between '"+fechaInicio+"' And '"+fechaFin+"') ";
            }
            if (estado == 1) {
                query += "And Cancelada = 1 ";
            }else if (estado == 0){
                query += "And Cancelada = 0 ";
            }
            if (!tipo.equals("") && !tipo.equals("Cualquiera")) {
                query += "And TipoFactura = ? ";
            }
            if (numFactura > 0) {
                query += "And IdFactura = ? ";
            }
            ps = con.prepareStatement(query);
            
            if (fechasCorrectas && (!tipo.equals("") && !tipo.equals("Cualquiera")) && numFactura > 0) { // fechas corre, tipo corre, numFact corre
                ps.setString(1, tipo);
                ps.setInt(2, numFactura);
            }else if (!fechasCorrectas && (!tipo.equals("") && !tipo.equals("Cualquiera")) && numFactura > 0){ // fechas incorre, tipo corre, numFact corre 
                ps.setString(1, tipo);
                ps.setInt(2, numFactura);
            }else if (!fechasCorrectas && tipo.equals("Cualquiera") && numFactura > 0){ // fechas incorre, tipo incorre, numFact corre
                ps.setInt(1, numFactura);
            }else if (fechasCorrectas && !tipo.equals("Cualquiera") && numFactura == 0){ //fechas corre, tipo corre, numFact incorre
                ps.setString(1, tipo);
            }else if (!fechasCorrectas && (!tipo.equals("") && !tipo.equals("Cualquiera") ) && numFactura == 0){ // fechas incorre, tipo corre, numFact incorre
                ps.setString(1, tipo);
            }else if (fechasCorrectas && tipo.equals("Cualquiera") && numFactura > 0){ // fechas corre, tipo incorre, numFact corre
                ps.setInt(1, numFactura);
            }
            
            rs = ps.executeQuery();

            while (rs.next()) {
                BL_Factura factura = new BL_Factura();
                BL_ManejadorProductoFactura detallesFactura = new BL_ManejadorProductoFactura();

                factura.setIdFactura(rs.getInt(1));
                factura.setFechaFactura(rs.getDate(2));
                if (rs.getInt(3) == 1) {
                    factura.setCancelada(true);
                } else {
                    factura.setCancelada(false);
                }
                factura.setTipoFactura(rs.getString(4));

                psM = con.prepareStatement("Select MontoAbonar FROM abono WHERE IdFactura = ?");
                psM.setInt(1, factura.getIdFactura());

                rsMonto = psM.executeQuery();

                if (rsMonto.next()) {
                    factura.setMontoAbonado(rsMonto.getDouble(1));
                } else {
                    factura.setMontoAbonado(0);
                }

                ps = con.prepareStatement("Select Cantidad, PrecioVenta, Descripcion "
                        + "From productofactura Where IdFactura = ?");
                ps.setInt(1, factura.getIdFactura());
                rsDetalles = ps.executeQuery();

                while (rsDetalles.next()) {
                    BL_ProductoFactura prodFactura = new BL_ProductoFactura();

                    prodFactura.setCantidadVendida(rsDetalles.getInt(1));
                    prodFactura.setPrecioVenta(rsDetalles.getDouble(2));
                    prodFactura.setDescripcion(rsDetalles.getString(3));
                    detallesFactura.Agregar(prodFactura);
                }
                factura.setProductosFactura(detallesFactura.ObtenerLista());

                psCliente = con.prepareStatement("SELECT c.IdCliente,c.NombreCompleto, c.Cedula, c.Direccion FROM cliente c INNER JOIN clientefactura cf "
                        + " ON cf.IdCliente = c.IdCliente WHERE cf.IdFactura = ?");
                psCliente.setInt(1, factura.getIdFactura());

                rsCliente = psCliente.executeQuery();

                if (rsCliente.next()) {
                    BL_Cliente cliente = new BL_Cliente(rsCliente.getInt(1), rsCliente.getString(2), rsCliente.getString(3), rsCliente.getString(4));

                    psCliente = con.prepareStatement("SELECT Telefono, TipoTelefono FROM telefonocliente WHERE IdCliente = ?");
                    psCliente.setInt(1, cliente.getIdCliente());

                    rsTelefonos = psCliente.executeQuery();

                    while (rsTelefonos.next()) {
                        cliente.getListaTelefonos().add(new BL_TelefonoCliente(rsTelefonos.getString(1), rsTelefonos.getString(2)));
                    }
                    factura.setCliente(cliente);
                }

                mFacturas.Agregar(factura);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion();
    }
    
    public int cargarSiguienteNumeroFactura(){
        int numeroFactura = 0;
        conexion();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement("Select Max(IdFactura) From factura");
            rs = ps.executeQuery();
            while (rs.next()) {                
                numeroFactura = rs.getInt(1) +1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cerrarConexion();
        return numeroFactura;
    }
}
