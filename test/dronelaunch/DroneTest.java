/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mario
 */
public class DroneTest {
    
    public DroneTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Hola");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getIdentify method, of class Drone.
     */
    @Test
    public void testGetIdentify() {
        System.out.println("getIdentify");
        Drone instance = new Drone("DR001", 10);
        String expResult = "DR001";
        String result = instance.getIdentify();
        assertEquals(expResult, result);
    }
    
}
