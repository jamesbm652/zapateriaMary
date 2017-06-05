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
        System.out.println("Método conexion");
        try{
            DAO_Usuario instance = new DAO_Usuario();
            instance.conexion();
        }catch(Exception e){
            fail("Exception error");
        }catch(AssertionError e){
            fail("Assertion error");
        }
    }

    /**
     * Test of cerrarConexion method, of class DAO_Usuario.
     */
    @Test
    public void testCerrarConexion() {
        System.out.println("cerrarConexion");
        try{
        DAO_Usuario instance = new DAO_Usuario();
        instance.cerrarConexion();
        }catch(AssertionError e){
            fail("Assertion error");
        }catch(NullPointerException e){
        }
    }

    /**
     * Test of logueo method, of class DAO_Usuario.
     */
    @Test
    public void testLogueo() {
        System.out.println("Método Logueo");
        BL_Usuario usuario = null;
        String nombreUsuario = "";
        String contrasena = "";
        DAO_Usuario instance = new DAO_Usuario();
        boolean expResult = false;
        boolean result = instance.logueo(usuario, nombreUsuario, contrasena);
        assertEquals(expResult, result);
        if(expResult != result)
            fail("Error el logueo");
    }

    /**
     * Test of cargarUsuarios method, of class DAO_Usuario.
     */
    @Test
    public void testCargarUsuarios() {
        System.out.println("Método Cargar Usuarios");
        DAO_Usuario instance = new DAO_Usuario();
        ArrayList<BL_Usuario> expResult = null;
        ArrayList<BL_Usuario> result = instance.cargarUsuarios();
        assertNotEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(expResult == result)    
        fail("Error en cargar usuarios");
    }

    /**
     * Test of agregarUsuario method, of class DAO_Usuario.
     */
    @Test
    public void testAgregarUsuario() {
        System.out.println("Método Agregar Usuario");
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
        BL_Usuario usuarioModificado = new BL_Usuario();
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
        Boolean expResult = false;
        Boolean result = instance.eliminarUsuario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result != expResult)
            fail("The test case is a prototype.");
    }
    
}
