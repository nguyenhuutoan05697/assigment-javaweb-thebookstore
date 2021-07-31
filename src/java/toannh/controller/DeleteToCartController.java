/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toannh.dao.BookDAO;
import toannh.dto.DetailDTO;

/**
 *
 * @author HuuToan
 */
public class DeleteToCartController extends HttpServlet {
  private static final String ERROR = "cart.jsp";
  private static final String SUCCESS = "cart.jsp";
  
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
        String url = ERROR;
        int Bid = Integer.parseInt(request.getParameter("BID"));
        DetailDTO detailDTO = new DetailDTO();
        ArrayList<DetailDTO> list = null;
        try {
            int BookID = Integer.parseInt(request.getParameter("BID"));
            BookDAO dao = new BookDAO();
            HttpSession session = request.getSession();
            list = (ArrayList<DetailDTO>) session.getAttribute("CART");
            if (list != null) {//nếu đã có cart rồi
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBook().getBookID() == BookID ) {
                         list.remove(i);                      
                    }
                }
            } else if (list == null) {
                list = new ArrayList<>();
            }
            session.setAttribute("CART", list);
            url = SUCCESS;
            
        } catch (Exception e) {
              log("Error at DeleteController: "+e.toString());
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
