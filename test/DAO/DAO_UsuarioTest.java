/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.BL_Usuario;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hermes
 */
public class DAO_UsuarioTest {
    
    public DAO_UsuarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of conexion method, of class DAO_Usuario.
     */
    @Test
    public void testConexion() {
        System.out.println("conexion");
        DAO_Usuario instance = new DAO_Usuario();
        instance.conexion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cerrarConexion method, of class DAO_Usuario.
     */
    @Test
    public void testCerrarConexion() {
        System.out.println("cerrarConexion");
        DAO_Usuario instance = new DAO_Usuario();
        instance.cerrarConexion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logueo method, of class DAO_Usuario.
     */
    @Test
    public void testLogueo() {
        System.out.println("logueo");
        BL_Usuario usuario = null;
        String nombreUsuario = "";
        String contrasena = "";
        DAO_Usuario instance = new DAO_Usuario();
        boolean expResult = false;
        boolean result = instance.logueo(usuario, nombreUsuario, contrasena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarUsuarios method, of class DAO_Usuario.
     */
    @Test
    public void testCargarUsuarios() {
        System.out.println("cargarUsuarios");
        DAO_Usuario instance = new DAO_Usuario();
        ArrayList<BL_Usuario> expResult = null;
        ArrayList<BL_Usuario> result = instance.cargarUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarUsuario method, of class DAO_Usuario.
     */
    @Test
    public void testAgregarUsuario() {
        System.out.println("agregarUsuario");
        BL_Usuario usuario = null;
        DAO_Usuario instance = new DAO_Usuario();
        Boolean expResult = null;
        Boolean result = instance.agregarUsuario(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarUsuario method, of class DAO_Usuario.
     */
    @Test
    public void testModificarUsuario() {
        System.out.println("modificarUsuario");
        BL_Usuario usuarioModificado = null;
        DAO_Usuario instance = new DAO_Usuario();
        Boolean expResult = null;
        Boolean result = instance.modificarUsuario(usuarioModificado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarUsuario method, of class DAO_Usuario.
     */
    @Test
    public void testEliminarUsuario() {
        System.out.println("eliminarUsuario");
        int id = 0;
        DAO_Usuario instance = new DAO_Usuario();
        Boolean expResult = null;
        Boolean result = instance.eliminarUsuario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
