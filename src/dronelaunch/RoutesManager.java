/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class RoutesManager {
    
    private ArrayList<Drone> drones;
    private DroneFileStream controlFileStream;
    
    
    public RoutesManager(){
        this.drones = new ArrayList<>();
        this.controlFileStream = new DroneFileStream();
    }

    public ArrayList<Drone> getDrones() {
        return drones;
    }

    public void setDrones(ArrayList<Drone> drones) {
        this.drones = drones;
    }

    public DroneFileStream getControlFileStream() {
        return controlFileStream;
    }

    public void setControlFileStream(DroneFileStream controlFileStream) {
        this.controlFileStream = controlFileStream;
    }
    
    
}
