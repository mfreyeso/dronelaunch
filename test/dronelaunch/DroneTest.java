/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mario
 */
public class DroneTest {
    
    public DroneTest() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEncapsulation() {
        System.out.println("Testing encapsulation attributes");
        DroneSingleton instanceA = DroneSingleton.getInstance();
        int positionX = 0;
        int positionY = 0;
        assertEquals(positionX, instanceA.getPositionX());
        assertEquals(positionY, instanceA.getPositionY());
    }
    
    @Test
    public void testSingleton() {
        System.out.println("Testing Singleton object");
        DroneSingleton instanceA = DroneSingleton.getInstance();
        DroneSingleton instanceB = DroneSingleton.getInstance();
        assertEquals(instanceB, instanceA);
    }
    
    @Test
    public void testTranslateCommand() {
        String [] commands = new String [] {"AAAAIAAD", "DDAIAD", "AAIADAD"};
        String [][] results = new String[][] {{"-2", "4", "N"}, {"-1", "3", "S"}, {"0", "0", "O"}};
        System.out.println("Testing translate commands");
        DroneSingleton instanceA = DroneSingleton.getInstance();
        instanceA.setLimitScope(10);
        
        for(int i=0; i<commands.length; i++){
            instanceA.translateCommand(commands[i]);
            assertEquals(results[i][0], String.valueOf(instanceA.getPositionX()));
            assertEquals(results[i][1], String.valueOf(instanceA.getPositionY()));
            assertEquals(results[i][2], String.valueOf(instanceA.getDirection()));
        }
    }
    
    @Test
    public void testMaxDeliveries() {
        System.out.println("Testing maximum deliveries");
        ArrayList<String> orders = new ArrayList<>();
        
        String [] commands = new String [] {"AAAAIAAD", "DDAIAD", "AAIADAD"};
        String [] otherCommands = new String [] {"AAAAIAAD", "DDAIAD", "AAIADAD", "AAAAIAAD", "DDAIAD", "AAIADAD"};
        
        orders.addAll(Arrays.asList(commands));
        
        DroneSingleton instance = DroneSingleton.getInstance();
        instance.setOrders(orders);
        Boolean response = instance.deliverLaunchs(10);
        
        assertEquals(true, response);
        
        orders.addAll(Arrays.asList(otherCommands));
        response = instance.deliverLaunchs(10);
        
        assertEquals(false, response);
        
    }
    
    @Test
    public void testLimitScope() {
        System.out.println("Testing limit scope");
        String [] deliveries = new String [] {"AAAAAAAAAAAAIAAD", "DDAAAAIAAAADAAAADAAA"};
        
        Drone drone = new Drone("DR001", 3);
        drone.setLimitScope(10);
        
        for (String delivery : deliveries) {
            drone.translateCommand(delivery);
        }
        
        String response = drone.getDeliveries().get(0);
        assertEquals("The order is beyond the scope" ,response);
        
        response = drone.getDeliveries().get(1);
        assertNotEquals("The order is beyond the scope" ,response);   
    }
}
