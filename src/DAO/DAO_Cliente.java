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
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

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
    
    public DefaultComboBoxModel obtenerListaComboBox(String cadena){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        String query = "SELECT Cedula FROM cliente WHERE cedula LIKE ?;";
        
        PreparedStatement ps;
        ResultSet rs;
        
        conexion(); 
        try {
            ps = this.con.prepareStatement(query);
            ps.setString(1, cadena + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                model.addElement(rs.getString("Cedula"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    public ArrayList<BL_Cliente> cargarTodosClientes(){
        ArrayList<BL_Cliente> lista = new ArrayList<>();
        
        conexion();
        Statement st = null;
        ResultSet rs = null;
        String query = "SELECT * FROM cliente;";
        
        try {
            st = this.con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                BL_Cliente cliente = new BL_Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setCedula(rs.getString("Cedula"));
                cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                cliente.setDireccion(rs.getString("Direccion"));
                
                lista.add(cliente);
            }
            
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
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
