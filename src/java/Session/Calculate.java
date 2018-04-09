/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author ruben
 */
@Stateless
@LocalBean
public class Calculate {

    public void setValue(int value) {
        this.value = value;
    }
    private int value = 0; 

    public int getValue() {
        return value;
    }
    
    public void add(int a) {
        value += a;
    }
    
    public void remove(int a) {
        value -= a;
    }
}
