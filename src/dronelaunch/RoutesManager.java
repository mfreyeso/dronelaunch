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

/**
 *
 * @author mario
 */
public class RoutesManager {
    
    private DroneFileStream controlFileStream;
    private int dimensionMaximum;
    private DroneSingleton instance;
    
    public RoutesManager(int dimesionMaximum){
        this.controlFileStream = new DroneFileStream();
        this.dimensionMaximum = dimesionMaximum;
    }
    
    public DroneFileStream getControlFileStream() {
        return controlFileStream;
    }

    public void setControlFileStream(DroneFileStream controlFileStream) {
        this.controlFileStream = controlFileStream;
    }

    public int getDimensionMaximum() {
        return dimensionMaximum;
    }

    public void setDimensionMaximum(int dimensionMaximum) {
        this.dimensionMaximum = dimensionMaximum;
    }
    
    public void manageDrones(String fullPathFileIn, String fullPathFileOut){
        this.assignRoutes(fullPathFileIn);
        this.startDeliveryTask(fullPathFileOut);
    }
    
    public void startDeliveryTask(String fullPathReport){
        Boolean deliverLaunchs = DroneSingleton.getInstance().deliverLaunchs(this.getDimensionMaximum());
        if(deliverLaunchs){
            try {
                this.getControlFileStream().writeFile(fullPathReport+"/output.txt", DroneSingleton.getInstance().getDeliveries());
            } catch (IOException ex) {
                Logger.getLogger(RoutesManager.class.getName()).log(Level.SEVERE, "The file don't exists in path defined");
            }
        }
    }
    
    public void assignRoutes(String fullPathFile){
        try {
            this.getControlFileStream().readFile(fullPathFile);
            ArrayList<String> routes = this.getControlFileStream().getStreamContent();
            DroneSingleton.getInstance().setOrders(routes);
        } catch (IOException ex) {
            Logger.getLogger(RoutesManager.class.getName()).
                    log(Level.SEVERE,
                    "The file don't exists in path defined");
        }  
    }

    
    
    
}
