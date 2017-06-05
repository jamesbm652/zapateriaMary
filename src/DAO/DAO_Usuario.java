/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.BL_ManejadorProducto;
import BL.BL_ManejadorUsuario;
import BL.BL_Usuario;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hermes
 */
public class DAO_Usuario {

    Connection con = null;

    public DAO_Usuario() {
    }

    public void conexion() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/zapateriamary", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean logueo(BL_Usuario usuario, String nombreUsuario, String contrasena) {
        conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean logueo = false;
        try {
            ps = con.prepareStatement("Select * From usuario where NombreUsuario = ? And Contrasena = ?;");
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            rs = ps.executeQuery("Select * From usuario where NombreUsuario = '" + nombreUsuario + "' And Contrasena = '" + contrasena + "';");
            while (rs.next()) {
                logueo = true;
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombreCompleto(rs.getString(2));
                usuario.setNombreUsuario(rs.getString(3));
                usuario.setContrasena(rs.getString(4));
                if (rs.getInt(5) == 1) {
                    usuario.setAdministrador(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        cerrarConexion();
        return logueo;
    }

    public ArrayList<BL_Usuario> cargarUsuarios() {
        conexion();

        Statement st = null;
        ResultSet rs = null;
        BL_ManejadorUsuario manejador = new BL_ManejadorUsuario();

        try {
            st = this.con.createStatement();
            rs = st.executeQuery("SELECT U.IdUsuario,U.NombreCompleto,U.NombreUsuario,U.Contrasena,U.Administrador FROM usuario U");

            while (rs.next()) {
                BL_Usuario usuario = new BL_Usuario();

                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setNombreCompleto(rs.getString("NombreCompleto"));
                usuario.setNombreUsuario(rs.getString("NombreUsuario"));
                usuario.setContrasena(rs.getString("Contrasena"));
                if (rs.getInt("Administrador") == 1) {
                    usuario.setAdministrador(true);
                } else {
                    usuario.setAdministrador(false);
                }

                manejador.Agregar(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        cerrarConexion();
        return manejador.ObtenerListaUsuarios();
    }

    public Boolean agregarUsuario(BL_Usuario usuario) {
        conexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertado = 0;

        try {

            ps = con.prepareStatement("SELECT * FROM usuario WHERE NombreUsuario = ?");
            ps.setString(1, usuario.getNombreUsuario());

            if ((ps.executeQuery().next())) {
                return false;
            }

            ps = con.prepareStatement("INSERT INTO usuario (NombreCompleto,NombreUsuario,Contrasena,Administrador) VALUES(?,?,?,?)", ps.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNombreCompleto());
            ps.setString(2, usuario.getNombreUsuario());
            ps.setString(3, usuario.getContrasena());
            if (usuario.isAdministrador()) {
                ps.setInt(4, 1);
            } else {
                ps.setInt(4, 0);
            }

            insertado = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (insertado > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean modificarUsuario(BL_Usuario usuarioModificado) {
        return true;
    }

    public Boolean eliminarUsuario(int id) {
        return true;
    }

}
