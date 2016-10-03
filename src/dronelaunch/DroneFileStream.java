/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author mario
 */

public class DroneFileStream {
    
    private ArrayList<String> streamContent;
    
    public DroneFileStream(){
        this.streamContent = new ArrayList<>();
    }
    
    public void readFile(String filename) throws IOException{
        
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        
        String commandLine;
        
        while ((commandLine = br.readLine()) != null) {
            this.getStreamContent().add(commandLine);
            //System.out.println(commandLine);
        }   
    }

    public void writeFile(String filename, ArrayList<String> lines) throws IOException{
        Files.write(Paths.get(filename), lines);
    }

    public ArrayList<String> getStreamContent() {
        return streamContent;
    }

    public void setStreamContent(ArrayList<String> streamContent) {
        this.streamContent = streamContent;
    }
    
}
