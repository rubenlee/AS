/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author ruben
 */
@Singleton
@LocalBean
public class InactivityLog {

    @EJB
    private DataDump dataDump;
    private String log = "";

    public void Log(String clase, String funcion) {
        this.log += clase + "::" + funcion + ";";
    }

    public String getLog() {
        dataDump.setTimeOut();
        return log;
    }
}
