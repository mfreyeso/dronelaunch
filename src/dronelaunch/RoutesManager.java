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
    private ArrayList<Drone> droneCollection;
    private int numberDrones;
    
    public RoutesManager(int dimesionMaximum){
        this.controlFileStream = new DroneFileStream();
        this.dimensionMaximum = dimesionMaximum;
        this.numberDrones = 20;
        this.droneCollection = new ArrayList<>();
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
    
    public ArrayList<Drone> getDroneCollection() {
        return droneCollection;
    }

    public void setDroneCollection(ArrayList<Drone> droneCollection) {
        this.droneCollection = droneCollection;
    }

    public int getNumberDrones() {
        return numberDrones;
    }

    public void setNumberDrones(int numberDrones) {
        this.numberDrones = numberDrones;
    }
    
    public void manageDrones(String fullPathFileIn){
        ArrayList<String> files = this.getControlFileStream().getFiles(fullPathFileIn);

        for(int i=0; i < files.size(); i++){
            String identify = String.format("dr0%d", (i+1));
            Drone drone = new Drone(identify, 10);
            
            this.assignRoutes(files.get(i), drone);
            this.getDroneCollection().add(drone);
        }
    }
    
    public void sendDrones(String fullPathFileOut){
        for(Drone drone: this.getDroneCollection()){
            this.startDeliveryTask(fullPathFileOut, drone);
        }

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
    
    public void startDeliveryTask(String fullPathReport, Drone drone){
        Boolean deliverLaunchs = drone.deliverLaunchs(this.getDimensionMaximum());
        if(deliverLaunchs){
            try {
                String fileOutput = String.format("%s/out%s.txt", fullPathReport, drone.getIdentify());
                this.getControlFileStream().writeFile(fileOutput, drone.getDeliveries());
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
    
    public void assignRoutes(String fullPathFile, Drone drone){
        try {
            this.getControlFileStream().readFile(fullPathFile);
            ArrayList<String> routes = this.getControlFileStream().getStreamContent();
            drone.setOrders(routes);
        } catch (IOException ex) {
            Logger.getLogger(RoutesManager.class.getName()).
                    log(Level.SEVERE,
                    "The file don't exists in path defined");
        }
        
    }
    
}
