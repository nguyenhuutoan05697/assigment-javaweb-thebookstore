/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HuuToan
 */
public class MainController extends HttpServlet {

    private static final String ERROR_CONTROLLER = "login.jsp";
    //---User---//
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    //--Home--//
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String MANAGER_CONTROLLER = "ManagerBook.jsp";
    private static final String HOME_CONTROLLER = "HomeController";
    private static final String CATEGORY_CONTROLLER = "CategoryController";
    //---Manager Book---//
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String EDIT_CONTROLLER = "EditController";
    //---Cart---//
    private static final String ADDTOCART_CONTROLLER = "AddtoCartController";
    private static final String SHOWCART_CONTROLLER = "cart.jsp";
    private static final String CHECKOUT_CONTROLLER = "CheckOutController";
    private static final String HISTORY_CONTROLLER = "HistoryController";
    private static final String MOREDETAIL_CONTROLLER = "MoreDetailController";
    private static final String DELETETOCART_CONTROLLER = "DeleteToCartController";
    

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
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_CONTROLLER;
         HttpSession session = request.getSession();
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if ("Search".equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if ("manager".equals(action)) {
                url = MANAGER_CONTROLLER;
            } else if ("home".equals(action)) {
                url = HOME_CONTROLLER;
            } else if ("Update".equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if ("CheckOut".equals(action)) {
                url = CHECKOUT_CONTROLLER;
            } else if ("Edit".equals(action)) {
                url = EDIT_CONTROLLER;
            } else if ("Add".equals(action)) {
                url = ADD_CONTROLLER;
            } else if ("Delete".equals(action)) {
                url = DELETE_CONTROLLER;
            } else if ("Logout".equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if ("category".equals(action)) {
                url = CATEGORY_CONTROLLER;
            } else if ("Cancel".equals(action)) {
                url = MANAGER_CONTROLLER;
            } else if ("AddtoCart".equals(action)) {
                url = ADDTOCART_CONTROLLER;
            } else if ("showcart".equals(action)) {
                url = SHOWCART_CONTROLLER;
            }  else if ("History".equals(action)) {
                url = HISTORY_CONTROLLER;
            }else if ("MoreDetail".equals(action)) {
                url = MOREDETAIL_CONTROLLER;
            }else if ("DeleteToCart".equals(action)) {
                url = DELETETOCART_CONTROLLER;
            }
            else{
                session.setAttribute("ERROR_MESSAGE", "Function is not support!");
            }
        } catch (Exception e) {
            log("Error at MainController: "+e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
