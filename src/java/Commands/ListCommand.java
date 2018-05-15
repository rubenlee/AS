/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import JPA.Product;
import JPA.ProductFacade;
import Session.DataDump;
import Session.InactivityLog;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "ListCommand", urlPatterns = {"/ListCommand"})
public class ListCommand extends FrontCommand {

    ProductFacade productFacade = lookupProductFacadeBean();

    DataDump dataDump = lookupDataDumpBean();
    InactivityLog inactivityLog = lookupInactivityLogBean();

    @Override
    public void process() {
        inactivityLog.Log("ListCommand", "process");
        inactivityLog.Log("List.jsp", "Pagina");
        dataDump.setList();
        String algo = (String) request.getParameter("index");
        System.out.println(algo);
        if(request.getParameter("index") == null){
            List<Product> products = productFacade.getProducts(0);
            session.setAttribute("products", products);
            session.setAttribute("indexM", "0");
        }else{
            List<Product> products = productFacade.getProducts((Integer.parseInt((String) request.getParameter("index"))-1)*3);
            session.setAttribute("products", products);
            session.setAttribute("indexM", ""+(Integer.parseInt((String) request.getParameter("index"))-1));
        }
        if(session.getAttribute("size") == null){
            session.setAttribute("size", ""+productFacade.findAll().size());
        }
        forward("/List.jsp");
    }

    private InactivityLog lookupInactivityLogBean() {
        try {
            Context c = new InitialContext();
            return (InactivityLog) c.lookup("java:global/WebShop/InactivityLog!Session.InactivityLog");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DataDump lookupDataDumpBean() {
        try {
            Context c = new InitialContext();
            return (DataDump) c.lookup("java:global/WebShop/DataDump!Session.DataDump");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ProductFacade lookupProductFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProductFacade) c.lookup("java:global/WebShop/ProductFacade!JPA.ProductFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
