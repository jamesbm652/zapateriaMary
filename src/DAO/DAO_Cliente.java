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
import java.util.logging.Level;
import java.util.logging.Logger;
import BL.BL_Cliente;

/**
 *
 * @author Joseph
 */
public class DAO_Cliente {

    Connection con = null;

    public DAO_Cliente() {
    }

    public void conexion() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/zapateriamary", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarCliente() {

    }

    public int obtenerClienteFactura(BL_Cliente cliente) {
        int idCliente = 0;
        conexion();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("Select IdCliente from cliente Where Cedula = ?");
            ps.setString(1, cliente.getCedula());
            rs = ps.executeQuery();

            while (rs.next()) {
                idCliente = rs.getInt(1);
            }

            if (idCliente == 0) {
                ps = con.prepareStatement("Insert into cliente Values (?, ?, ?)");

                ps.setString(1, cliente.getNombreCompleto());
                ps.setString(2, cliente.getCedula());
                ps.setString(3, cliente.getDireccion());

                ps.executeUpdate();
                rs = ps.getGeneratedKeys();

                while (rs.next()) {
                    idCliente = rs.getInt(1);
                }
            }
            
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCliente;
    }
}
