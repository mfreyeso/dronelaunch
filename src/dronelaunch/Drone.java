/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import java.util.ArrayList;
import java.util.stream.Stream;


/**
 *
 * @author mario
 */
public class Drone implements DroneMovement {
    
    private String identify;
    private int positionX;
    private int positionY;
    private String direction;
    private Stream<String> orders;
    private ArrayList <String> deliveries;
    private int maxDeliveries;
    
    public Drone(String identify, int maxDeliveries){
        this.identify = identify;
        this.maxDeliveries = maxDeliveries;
        this.positionX = 0;
        this.positionY = 0;
        this.direction = "N";
        this.deliveries = new ArrayList<>();
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ArrayList<String> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(ArrayList<String> deliveries) {
        this.deliveries = deliveries;
    }

    public int getMaxDeliveries() {
        return maxDeliveries;
    }

    public void setMaxDeliveries(int maxDeliveries) {
        this.maxDeliveries = maxDeliveries;
    }
    
    public Stream<String> getOrders() {
        return orders;
    }

    public void setOrders(Stream<String> orders) {
        this.orders = orders;
    }

    @Override
    public void changeDirection(String direction) {
        
    }

    @Override
    public void changePosition(int positionX, int positionY) {
    }

    @Override
    public void translateCommand(String command) {
        for(char action: command.toCharArray()){
            if(action == 'D' ||  action == 'I') { changeDirection(String.valueOf(action)); }
            
        }
    }
    
}
