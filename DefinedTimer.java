/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo;

import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class DefinedTimer {
    
    public void startTime(){
        
        
        
        Timer updateTime = new Timer(5000, (ae) -> {
            Date fecha = new Date();
            System.out.println(fecha);
        });
        
        updateTime.start();
        
        
    }
    
    
}
