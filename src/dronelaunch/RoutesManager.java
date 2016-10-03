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
    private int [][] mapDroneScope;
    private DroneSingleton instance;
    
    public RoutesManager(int dimesionMaximum){
        this.controlFileStream = new DroneFileStream();
        this.dimensionMaximum = dimesionMaximum;
        // this.initializeMapDroneScope();
    }
    
    private void initializeMapDroneScope(){
        int dim = this.getDimensionMaximum();
        this.mapDroneScope = new int [(2*dim)+1][(2*dim)+1];
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

    public int[][] getMapDroneScope() {
        return mapDroneScope;
    }

    public void setMapDroneScope(int[][] mapDroneScope) {
        this.mapDroneScope = mapDroneScope;
    }
    
    public void createDrone(String identify, int maxDeliveries){ 
        this.instance = DroneSingleton.getInstance();
        instance.setIdentify(identify);
        instance.setMaxDeliveries(maxDeliveries);
    }
    
    public void manageDrones(String fullPathFile){
        this.assignRoutes(fullPathFile);
        //this.placeDrones();
        this.startDeliveryTask();
    }
    
    public void placeDrones(){
        int centerPoint = this.dimensionMaximum;  
        this.mapDroneScope[centerPoint][centerPoint] = 1;   
    }
    
    public void startDeliveryTask(){
        DroneSingleton.getInstance().deliverLaunchs(this.getDimensionMaximum());
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
