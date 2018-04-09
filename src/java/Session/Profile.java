/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * @author ruben
 */
@Stateful
@LocalBean
public class Profile {

    private String name;
    private String email;
    private String adress;
    
    public Profile(){
        name = new String();
        email = new String();
        adress = new String();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
