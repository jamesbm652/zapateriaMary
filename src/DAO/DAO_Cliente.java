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
import javax.swing.JOptionPane;
import BL.BL_TelefonoCliente;

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
            con = DriverManager.getConnection("jdbc:mysql://localhost/zapateriamary", "root", "1234");
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

    public DefaultComboBoxModel obtenerListaComboBox(String cadena, String tipoFiltro) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        String query = "";
        if (tipoFiltro.equals("Cedula")) {
            query = "SELECT Cedula, NombreCompleto FROM cliente WHERE cedula LIKE ?;";
        }else{
            query = "SELECT Cedula, NombreCompleto FROM cliente WHERE NombreCompleto LIKE ?;";
        }

        PreparedStatement ps;
        ResultSet rs;

        conexion();
        try {
            ps = this.con.prepareStatement(query);
            ps.setString(1, cadena + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                model.addElement(rs.getString(tipoFiltro));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    public ArrayList<BL_Cliente> cargarTodosClientes() {
        ArrayList<BL_Cliente> lista = new ArrayList<>();

        conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM cliente");
            rs = ps.executeQuery();

            while (rs.next()) {
                BL_Cliente cliente = new BL_Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setCedula(rs.getString("Cedula"));
                cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                cliente.setDireccion(rs.getString("Direccion"));

                lista.add(cliente);
            }
            for (BL_Cliente c : lista) {
                ps = con.prepareStatement("SELECT * FROM cliente C INNER JOIN telefonocliente TC ON C.IdCliente = TC.IdCliente WHERE C.IdCliente = ?");
                ps.setInt(1, c.getIdCliente());
                rs = ps.executeQuery();
                
                ArrayList<BL_TelefonoCliente> listaTels = new ArrayList<>();
                while (rs.next()) {
                    BL_TelefonoCliente tel = new BL_TelefonoCliente();
                    tel.setTelefono(rs.getString("Telefono"));
                    listaTels.add(tel);
                }
                c.setListaTelefonos(listaTels);
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
                cliente.setIdCliente(idCliente);
                validarTelefonos(cliente);
            }

            if (idCliente == 0) {
                ps = con.prepareStatement("Insert into cliente (NombreCompleto, Cedula, Direccion) Values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, cliente.getNombreCompleto());
                ps.setString(2, cliente.getCedula());
                ps.setString(3, cliente.getDireccion());

                ps.executeUpdate();
                rs = ps.getGeneratedKeys();

                while (rs.next()) {
                    idCliente = rs.getInt(1);
                    cliente.setIdCliente(idCliente);
                    validarTelefonos(cliente);
                }
            }

            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCliente;
    }

    public void validarTelefonos(BL_Cliente cliente) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String telHabitacion = "";
        String telCelular = "";

        for (int i = 0; i < cliente.getListaTelefonos().size(); i++) {
            if (cliente.getListaTelefonos().get(i).getTipoTelefono().equals("Habitacion")) {
                telHabitacion = cliente.getListaTelefonos().get(i).getTelefono();
            } else if (cliente.getListaTelefonos().get(i).getTipoTelefono().equals("Celular")) {
                telCelular = cliente.getListaTelefonos().get(i).getTelefono();
            }
        }

        try {
            if (!telHabitacion.equals("")) {
                ps = con.prepareStatement("Select Telefono from telefonocliente Where TipoTelefono = ? "
                        + "And IdCliente = ?");
                ps.setString(1, "Habitacion");
                ps.setInt(2, cliente.getIdCliente());
                rs = ps.executeQuery();
                if (rs.next()) {
                    actualizarTelefonoCliente(cliente.getIdCliente(), telHabitacion, "Habitacion");
                } else {
                    insertarTelefonoCliente(cliente.getIdCliente(), telHabitacion, "Habitacion");
                }
            }
            if (!telCelular.equals("")) {
                ps = con.prepareStatement("Select Telefono from telefonocliente Where TipoTelefono = ? "
                        + "And IdCliente = ?");
                ps.setString(1, "Celular");
                ps.setInt(2, cliente.getIdCliente());
                rs = ps.executeQuery();
                if (rs.next() && (!telHabitacion.equals(""))) {
                    actualizarTelefonoCliente(cliente.getIdCliente(), telCelular, "Celular");
                } else {
                    insertarTelefonoCliente(cliente.getIdCliente(), telCelular, "Celular");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertarTelefonoCliente(int idCliente, String numeroTelef, String tipoTelefono) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertado = 0;

        try {
            ps = con.prepareStatement("Insert into telefonocliente (IdCliente, Telefono, TipoTelefono) Values (?, ?, ?)");
            ps.setInt(1, idCliente);
            ps.setString(2, numeroTelef);
            ps.setString(3, tipoTelefono);
            
            insertado = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarTelefonoCliente(int idCliente, String numeroTelef, String tipoTelefono) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertado = 0;

        try {
            ps = con.prepareStatement("Update telefonocliente Set Telefono = ? Where IdCliente = ? And TipoTelefono = ?");
            ps.setString(1, numeroTelef);
            ps.setInt(2, idCliente);
            ps.setString(3, tipoTelefono);
            
            insertado = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
