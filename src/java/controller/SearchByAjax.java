/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.bookDAO;
import dao.bookDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dokk
 */
@WebServlet(name = "SearchByAjax", urlPatterns = {"/SearchByAjax"})
public class SearchByAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String search = request.getParameter("search");
        bookDAO dao = new bookDAO();
        List<bookDTO> list = dao.SearchByName(search);
        PrintWriter out = response.getWriter();
        for (bookDTO o : list) {
            if (o.isStatus() == true) {    
                out.println("<div class=\"book\">\n" +
"                            <div class=\"card\">\n" +
"                                <div class=\"frontcard\">\n" +
"                                    <img src=\"" + o.getImg() + "\" alt=\"Book Cover\" style=\"width:100%\">\n" +
"                                </div>\n" +
"                                <div class=\"backcard\">\n" +
"                                    <a href=\"detail?pid=${o.getId()}\" title=\"View Product\" class=\"title\">" + o.getName() + "</a>\n" +
"                                    <p>" + o.getQuantity() + " in stock</p>\n" +
"                                    <p>Pub: " + o.getPub() + "</p>\n" +
"                                    <p><button class=\"Ibuy\" name=\"addToCart\">Buy $" + o.getPrice() + "</button></p>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        </div>");
            }
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
