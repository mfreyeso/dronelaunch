/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mario
 */
public class Drone implements DroneMovement, Runnable {
    
    private String identify;
    private int positionX;
    private int positionY;
    private char direction;
    private ArrayList<String> orders;
    private ArrayList <String> deliveries;
    private int maxDeliveries;
    private int limitScope;
    private Thread thread;
    
    private Map steps;
    private Map mapIndexDirection;
    private Map mapIndexOrientation;
    private char [][] mapDirections;
    
    public Drone(String identify, int maxDeliveries){
        this.identify = identify;
        this.maxDeliveries = maxDeliveries;
        this.positionX = 0;
        this.positionY = 0;
        this.direction = 'N';
        this.deliveries = new ArrayList<>();
        this.initializeSteps();
        this.initializeDirectionsAtributes();
    }
    
    private void initializeSteps(){
      this.steps = new HashMap();
      this.steps.put('N', 1);
      this.steps.put('E', 1);
      this.steps.put('S', -1);
      this.steps.put('O', -1);
    }
    
    private void initializeDirectionsAtributes(){
        this.mapDirections = new char [][] {{'O', 'E'},
            {'E', 'O'}, {'S', 'N'}, {'N', 'S'}};
        
        this.mapIndexDirection = new HashMap();
        this.mapIndexDirection.put('N', 0);
        this.mapIndexDirection.put('S', 1);
        this.mapIndexDirection.put('O', 2);
        this.mapIndexDirection.put('E', 3);
        
        this.mapIndexOrientation = new HashMap();
        this.mapIndexOrientation.put('I', 0);
        this.mapIndexOrientation.put('D', 1);
        
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
    
    public Map getMapIndexDirection() {
        return mapIndexDirection;
    }

    public void setMapIndexDirection(Map mapIndexDirection) {
        this.mapIndexDirection = mapIndexDirection;
    }

    public Map getMapIndexOrientation() {
        return mapIndexOrientation;
    }

    public void setMapIndexOrientation(Map mapIndexOrientation) {
        this.mapIndexOrientation = mapIndexOrientation;
    }

    public char[][] getMapDirections() {
        return mapDirections;
    }

    public void setMapDirections(char[][] mapDirections) {
        this.mapDirections = mapDirections;
    }
    
    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
    
    public Boolean deliverLaunchs(int limScope){
        this.setLimitScope(limScope);
        Boolean response = false;

        if (this.getOrders().size() <= this.getMaxDeliveries()){
            String initDescription = "== Reporte de entregas ==";
            this.getDeliveries().add(initDescription);

            try {
                this.start();
                response = true;
            } catch (Exception e) {
                Logger.getLogger(RoutesManager.class.getName()).
                        log(Level.SEVERE,
                        "An error occurred in the develiveries.");
            }
        }else{
            Logger.getLogger(RoutesManager.class.getName()).
                        log(Level.INFO,
                        "The number of orders must be less than or equals to Drone capacity.");
        }
        return response;
    }
    
    public String formatResult(Boolean indicator){
        String result;
        if(!indicator){
            String [] labelDirections = new String [] {"Norte", "Sur", "Occidente", "Oriente"};
            int x = this.getPositionX();
            int y = this.getPositionY();
            String d = labelDirections[(int) this.getMapIndexDirection().get(this.getDirection())];

            result = String.format("(%d, %d) dirección %s", x, y, d);
        }else{
            result = "The order is beyond the scope";
        }
        return result;
    } 
 
    @Override
    public void changeDirection(char orientation) {
        
        char newDirection;
        char actualDirection = this.getDirection();
        
        int indexDirection = (int) this.getMapIndexDirection().get(actualDirection);
        int indexOrientation = (int) this.getMapIndexOrientation().get(orientation);
        
        newDirection = this.getMapDirections()[indexDirection][indexOrientation];
        this.setDirection(newDirection);
    }

    @Override
    public Boolean changePosition() {
        Boolean response = false;
        char actualDirection = this.getDirection();
        int indexDirection = (int) this.getSteps().get(actualDirection);
        
        if(actualDirection == 'S' || actualDirection == 'N'){
            if (Math.abs(this.getPositionY() + indexDirection) <= this.getLimitScope()){
                this.setPositionY(this.getPositionY() + indexDirection);
            }else{ response = true; }
     
        }else{
            if (Math.abs(this.getPositionX() + indexDirection) <= this.getLimitScope()){
                this.setPositionX(this.getPositionX() + indexDirection);
            }else{ response = true; }
        }
        
        return response;
    }

    @Override
    public void translateCommand(String command) {
        Boolean flatLimit = false;
        
        for(char action: command.toCharArray()){
            if(!flatLimit){
                if(action == 'D' ||  action == 'I') { this.changeDirection(action); }
                else{ 
                    flatLimit = this.changePosition(); 
                }
            }else{ break; }
        }
        this.getDeliveries().add(this.formatResult(flatLimit));
    }

    @Override
    public void run() {
        System.out.println("Sending Thread" + this.getIdentify());
        this.getOrders().stream().forEach((order) -> {
            try {
                System.out.println(order);
                this.translateCommand(order);
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Drone.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void start(){
        if (this.getThread() == null){
            this.thread = new Thread(this, this.getIdentify());
            this.getThread().start();
        }
    }

}
