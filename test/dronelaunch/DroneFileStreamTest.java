/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mario
 */
public class DroneFileStreamTest {
    
    public DroneFileStreamTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testReadFiles() {
        System.out.println("Testing list files");
        String pathDirectory = "res/input";
        DroneFileStream instance = new DroneFileStream();
        ArrayList<String> files = instance.getFiles(pathDirectory);
        assertEquals(10, files.size());
    }
    
    @Test
    public void testStreamContent() {
        System.out.println("Testing content of file");
        String pathFile = "res/input/01.txt";
        DroneFileStream instance = new DroneFileStream();
        try {
            instance.readFile(pathFile);
        } catch (IOException ex) {
            Logger.getLogger(DroneFileStreamTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotEquals(0, instance.getStreamContent().size());
    }
    
    @Test
    public void writeFiles() {
        System.out.println("Testing reports generate");
        
        String pathFile = "res/input/01.txt";
        String pathOutfile = "res/output/01.txt";
        
        DroneFileStream instance = new DroneFileStream();
        
        try {
            instance.readFile(pathFile);
            instance.writeFile(pathOutfile, instance.getStreamContent());
            
            instance.readFile(pathOutfile);
        } catch (IOException ex) {
            Logger.getLogger(DroneFileStreamTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(3, instance.getStreamContent().size());
    }
}
