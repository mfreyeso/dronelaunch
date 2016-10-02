/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author mario
 */

public class DroneFileStream {
    
    private Stream<String> streamContent;
    
    public void readFile(String filename) throws IOException{
        try (Stream<String> stream = Files.lines(Paths.get("res/"+filename))) {
            stream            
                .map(String::trim);
            setStreamContent(stream);
        }    
    }

    public void writeFile(String filename, ArrayList<String> lines) throws IOException{
        Files.write(Paths.get("res/"+filename), lines);
    }

    public Stream<String> getStreamContent() {
        return streamContent;
    }

    public void setStreamContent(Stream<String> streamContent) {
        this.streamContent = streamContent;
    }
    
}
