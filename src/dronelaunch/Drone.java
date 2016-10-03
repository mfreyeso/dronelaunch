/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author mario
 */
public class Drone implements DroneMovement {
    
    private String identify;
    private int positionX;
    private int positionY;
    private char direction;
    private ArrayList<String> orders;
    private ArrayList <String> deliveries;
    private int maxDeliveries;
    private int limitScope;
    
    private char [] directionsX;
    private char [] directionsY;
    private Map steps;
    private Map orientations;
    
    public Drone(String identify, int maxDeliveries){
        this.identify = identify;
        this.maxDeliveries = maxDeliveries;
        this.positionX = 0;
        this.positionY = 0;
        this.direction = 'N';
        this.deliveries = new ArrayList<>();
         
        this.directionsX = new char[] {'O','0','E'};
        this.directionsY = new char [] {'N', '0','S'};
        this.initializeSteps();
        this.initializeDirections();
    }
    
    private void initializeSteps(){
      this.steps = new HashMap();
      this.steps.put('N', 1);
      this.steps.put('E', 1);
      this.steps.put('S', -1);
      this.steps.put('O', -1);
    }
    
    private void initializeDirections(){
        this.orientations = new HashMap();
        this.orientations.put('I', -1);
        this.orientations.put('D', 1);
        
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

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
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
    
    public ArrayList<String> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<String> orders) {
        this.orders = orders;
    }
    
    public char[] getDirectionsX() {
        return directionsX;
    }

    public void setDirectionsX(char[] directionsX) {
        this.directionsX = directionsX;
    }

    public char[] getDirectionsY() {
        return directionsY;
    }

    public void setDirectionsY(char[] directionsY) {
        this.directionsY = directionsY;
    }

    public Map getSteps() {
        return steps;
    }

    public void setSteps(Map steps) {
        this.steps = steps;
    }
    
    public int getLimitScope() {
        return limitScope;
    }

    public void setLimitScope(int limitScope) {
        this.limitScope = limitScope;
    }
    
    public Map getOrientations() {
        return orientations;
    }

    public void setOrientations(Map orientations) {
        this.orientations = orientations;
    }
    
    public Boolean deliverLaunchs(int limScope){
        this.setLimitScope(limScope);
        Boolean response = false;
        
        this.getOrders().stream().forEach((order) -> {
            this.translateCommand(order);
        });
        
        System.out.println(String.valueOf(this.positionX) + String.valueOf(this.positionY));
        return response;
    }
 
    @Override
    public void changeDirection(char orientation) {
        char newDirection;
        char actualDirection = this.getDirection();
        
        int index = (int) this.getSteps().get(actualDirection) * 
                (int) this.getOrientations().get(orientation);
        
        if(actualDirection == 'S' || actualDirection == 'N'){
            newDirection = this.getDirectionsX()[index];
        }
        else{
            newDirection = this.getDirectionsY()[index];
        }
        
        this.setDirection(newDirection);
        System.out.println(String.valueOf(this.getDirection()));
    }

    @Override
    public void changePosition() {
        char actualDirection = this.getDirection();
        int indexDirection = (int) this.getSteps().get(actualDirection);
        
        if(actualDirection == 'S' || actualDirection == 'N'){
            if (Math.abs(this.getPositionY() + indexDirection) <= this.getLimitScope()){
                this.setPositionY(this.getPositionY() + indexDirection);
            }
     
        }else{
            if (Math.abs(this.getPositionX() + indexDirection) <= this.getLimitScope()){
                this.setPositionX(this.getPositionX() + indexDirection);
            }
        }
    }

    @Override
    public void translateCommand(String command) {
        for(char action: command.toCharArray()){
            System.out.println(String.valueOf(action));
            if(action == 'D' ||  action == 'I') { this.changeDirection(action); }
            else{ this.changePosition(); }    
        }
    }

}
