package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Session.Cart;
import Session.DataDump;
import Session.InactivityLog;
import Session.Item;
import Session.Wallet;
import java.io.IOException;
import javax.ejb.EJB;
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
    private Cart cart;

    @EJB
    private InactivityLog inactivityLog;

    @EJB
    private DataDump datadump;

    @EJB
    private Wallet wallet;
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
        datadump.setSessionServlet();
        if (!logout(request, session)) {
            initializeCart(request, session);
        }
        RequestDispatcher dp = getServletContext().getRequestDispatcher("/FrontServlet");
        dp.forward(request, response);
    }

    private void initializeCart(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("user") == null) {
            cart = new Cart();
            wallet = new Wallet();
            cart.initialize();
            session.setAttribute("cart", cart);
            datadump.setLogin();
            if (request.getParameter("username") == null) {
                session.setAttribute("user", "Anonimo");
                session.setAttribute("money", wallet.getAmount());
                session.setAttribute("cart", cart);
                addItem(request,session);
            } else {
                session.setAttribute("user", request.getParameter("username"));
                session.setAttribute("money", "100");
                session.setAttribute("cart", cart);
            }
        } else {
            addItem(request,session);
        }
    }

    private void addItem(HttpServletRequest request, HttpSession session) {
        if (request.getParameter("name") != null) {
            Cart cart = (Cart)session.getAttribute("cart");
            datadump.setProductsClicked();
            Item item = new Item();
            item.setId(request.getParameter("id"));
            item.setName(request.getParameter("name"));
            item.setValue(request.getParameter("value"));
            cart.addItem(item);
            
        }
    }

    private Boolean logout(HttpServletRequest request, HttpSession session) {
        String logout = request.getParameter("logout");
        if (logout == null) {
            return false;
        } else {
            cart.setActive();
            datadump.setLogoff();
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


}
