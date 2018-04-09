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
public class Disccount {

    public int remainder(int a , int b) {
        int temp = 0;
        if (b != 0) {
            temp = a - (a * b / 100);
        }
        return temp;
    }
}
