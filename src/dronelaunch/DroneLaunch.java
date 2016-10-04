/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dronelaunch;

import java.util.Scanner;

/**
 *
 * @author mario
 */
public class DroneLaunch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){

        System.out.println("Drone Launch");
        System.out.println ("Please enter the number limit blocks for managing drone routes:");
        Scanner sc = new Scanner(System.in);
  
        int limBlocks = sc.nextInt();
        RoutesManager rm = new RoutesManager(limBlocks);
        
        System.out.println ("Please enter the fullpath of file with drone routes: ");
        String fullPathIn = sc.next();
        System.out.println ("Please enter the fullpath of directory for deliveries report: ");
        String fullPathOut = sc.next();
        rm.manageDrones(fullPathIn, fullPathOut);
        
        System.out.println ("The tasks completed successfully, please review the reports generated in output directory");
    }
    
}
