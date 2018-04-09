/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;

/**
 *
 * @author ruben
 */
@Singleton
@LocalBean
@Startup
public class DataDump {
    
    int login,logoff,productsClicked,frontServlet,sessionServlet,index
           ,list,cart,profile,Unknown,singletonAccess,log,sign,statistic = 0;

    public int getSign() {
        return sign;
    }

    public void setSign() {
        sign++;
    }

    public int getStatistic() {
        return statistic;
    }

    public void setStatistic() {
        statistic++;
    }

    public int getLog() {
        return log;
    }

    public void setLog() {
        log++;
    }

    public int getTimeOut() {
        return singletonAccess;
    }

    public int getLogin() {
        return login;
    }

    public int getLogoff() {
        return logoff;
    }

    public int getProductsClicked() {
        return productsClicked;
    }

    public int getFrontServlet() {
        return frontServlet;
    }

    public int getSessionServlet() {
        return sessionServlet;
    }

    public int getIndex() {
        return index;
    }

    public int getList() {
        return list;
    }

    public int getCart() {
        return cart;
    }

    public int getProfile() {
        return profile;
    }

    public int getUnknown() {
        return Unknown;
    }

    public void setLogin() {
        login++;
    }

    public void setLogoff() {
        logoff++;
    }

    public void setProductsClicked() {
        productsClicked++;
    }

    public void setFrontServlet() {
        frontServlet++;
    }

    public void setSessionServlet() {
        sessionServlet++;
    }

    public void setIndex() {
        index++;
    }

    public void setList() {
        list++;
    }

    public void setCart() {
        cart++;
    }

    public void setProfile() {
        profile++;
    }

    public void setUnknown() {
        Unknown++;
    }

    public void setTimeOut() {
        singletonAccess++;
    }

    
    
}