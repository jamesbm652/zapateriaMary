/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.BL_Producto;
import com.mysql.jdbc.AssertionFailedException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import junit.framework.AssertionFailedError;
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
public class DAO_ProductoTest {
    
    public DAO_ProductoTest() {
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
     * Test of conexion method, of class DAO_Producto.
     */
    @Test
    public void testConexion() {
        System.out.println("Método conexion");
        try{
            DAO_Producto instance = new DAO_Producto();
            instance.conexion();
        } catch(Exception e){
            // TODO review the generated test code and remove the default call to fail.
            fail("Exception error");
        }catch(AssertionError e){
            fail("Assertion error");
        }
        
    }

    /**
     * Test of cerrarConexion method, of class DAO_Producto.
     */
    @Test
    public void testCerrarConexion() {
        System.out.println("Método Cerrar Conexion");
         try{
            DAO_Producto instance = new DAO_Producto();
            instance.cerrarConexion();
        }catch(AssertionError e){
            fail("Assertion error");
        }catch(NullPointerException E){
             System.err.println("NullPointerException");
        }
    }

    /**
     * Test of insertarProducto method, of class DAO_Producto.
     */
    @Test
    public void testInsertarProducto() {
        System.out.println("Método Insertar Producto");
        BL_Producto producto = new BL_Producto();
        producto.setCodigoUnico("1");
        producto.setFechaIngreso(Date.valueOf(LocalDate.MAX));
        DAO_Producto instance = new DAO_Producto();
        boolean expResult = false;
        boolean result = instance.insertarProducto(producto);
        assertEquals(expResult, result);
        if(result != expResult){
                // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of obtenerSiguienteCodigo method, of class DAO_Producto.
     */
    @Test
    public void testObtenerSiguienteCodigo() {
        System.out.println("Método Obtener Siguiente Codigo");
        DAO_Producto instance = new DAO_Producto();
        int expResult = 1;
        int result = instance.obtenerSiguienteCodigo();
        assertNotEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result == expResult){
            fail("Error en obtener siguiente código");
        }
    }
    

    /**
     * Test of cargarTodosProductos method, of class DAO_Producto.
     */
    @Test
    public void testCargarTodosProductos() {
        System.out.println("Método Cargar Todos Productos");
        DAO_Producto instance = new DAO_Producto();
        ArrayList<BL_Producto> expResult = null;
        ArrayList<BL_Producto> result = instance.cargarTodosProductos();
        assertNotEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result == expResult){
            fail("Error en cargar productos, viene vacio el manejador");
        }
    }

    /**
     * Test of cargarProductosPorFiltro method, of class DAO_Producto.
     */
    @Test
    public void testCargarProductosPorFiltro() {
        System.out.println("Método Cargar Productos Por Filtro");
        String genero = "";
        String color = "";
        double tallaZapato = 0.0;
        String marca = "";
        String empresa = "";
        double precio = 0.0;
        Date fecha = null;
        String categoria = "";
        boolean tipoProducto = false;
        DAO_Producto instance = new DAO_Producto();
        ArrayList<BL_Producto> expResult = null;
        ArrayList<BL_Producto> result = instance.cargarProductosPorFiltro(genero, color, tallaZapato, marca, empresa, precio, fecha, categoria, tipoProducto);
        assertNotEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result == expResult)
        fail("Error en cargar productos por filtro, existe un producto vacio");
    }

    /**
     * Test of eliminarProducto method, of class DAO_Producto.
     */
    @Test
    public void testEliminarProducto() {
        System.out.println("Método Eliminar Producto");
        int id = 0;
        DAO_Producto instance = new DAO_Producto();
        boolean expResult = true;
        boolean result = instance.eliminarProducto(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result != expResult)
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarEliminacion method, of class DAO_Producto.
     */
    @Test
    public void testValidarEliminacion() {
        System.out.println("Método Validar Eliminacion");
        int id = 0;
        DAO_Producto instance = new DAO_Producto();
        boolean expResult = false;
        boolean result = instance.validarEliminacion(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(expResult != result)
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarProducto method, of class DAO_Producto.
     */
    @Test
    public void testModificarProducto() {
        System.out.println("Método Modificar Producto");
        BL_Producto prod = new BL_Producto();
        prod.setFechaIngreso(Date.valueOf(LocalDate.MAX));
        DAO_Producto instance = new DAO_Producto();
        boolean expResult = false;
        boolean result = instance.modificarProducto(prod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(expResult != result)
        fail("Error");
    }
    
}
