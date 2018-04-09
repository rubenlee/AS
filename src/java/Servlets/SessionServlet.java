package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Session.Cart;
import Session.Item;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {
    @EJB
    Cart cart;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String userSession = (String) session.getAttribute("user");
        if(!logout(request,session)){
            initializeCart(request, session);   
        }
        RequestDispatcher dp = getServletContext().getRequestDispatcher("/FrontServlet");
        dp.forward(request, response);
    }
    
    private void initializeCart(HttpServletRequest request, HttpSession session){
        if(cart.isActive() == false){
            cart = new Cart(); 
            cart.initialize();
            session.setAttribute("cart", cart);
            if(request.getParameter("username")  == null){
                session.setAttribute("user", "Anonimo");  
                addItem(request, cart);
            }else{
                session.setAttribute("user", request.getParameter("username"));        
            }
        }else{
            addItem(request, cart);
        }
    }
    
    private void addItem(HttpServletRequest request, Cart cart){
        if(request.getParameter("name") != null){
                cart.addItem(new Item(request.getParameter("id"),request.getParameter("name"),request.getParameter("value")));
        }
    }
    
    private Boolean logout(HttpServletRequest request, HttpSession session){
        String logout = request.getParameter("logout");
        if(logout == null){
            return false;
        }else{
            session.removeAttribute("user");
            session.invalidate();
            return true;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Cart lookupCartBean() {
        try {
            Context c = new InitialContext();
            return (Cart) c.lookup("java:global/WebShop/Cart!Session.Cart");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
