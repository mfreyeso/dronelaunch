/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

/**
 *
 * @author mario
 */
public interface DroneMovement {
    
    public void changeDirection(String direction);
    public void changePosition(int positionX, int positionY);
    public void translateCommand(String command);
    
    
}
