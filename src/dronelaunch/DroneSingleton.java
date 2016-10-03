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
public class DroneSingleton extends Drone {
    
   private static DroneSingleton instance = null;
   private DroneSingleton() {
      super("DRL001", 3);
   }

   public static DroneSingleton getInstance() {
      if(instance == null) {
         instance = new DroneSingleton();
      }
      return instance;
   }
    
}
