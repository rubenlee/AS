/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;

/**
 *
 * @author ruben
 */
@Stateless
@LocalBean
public class SaveTimer {

    @EJB
    private InactivityLog inactivityLog;
    
    @Schedule(second = "*/6", minute = "*", hour = "*")
    private void writeLogToFile() throws IOException {
        File file = new File("C:\\Users\\ruben\\Desktop\\Timer Log.txt");
        BufferedWriter output = null;
        output = new BufferedWriter(new FileWriter(file, true));
        output.write( inactivityLog.getLog());
        output.newLine();
        output.close();
    }
}
