/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.userDAO;
import dao.userDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateUserController", urlPatterns = {"/CreateUserController"})
public class CreateUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String f = request.getParameter("i");
        String[] a = f.split("&");
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i].substring(a[i].indexOf("=") + 1);
        }
        for (String s : a) {
            System.out.println(s);
        }
        String userID = a[0];
        String password = a[1];
        String confirm = a[2];
        String fullName = a[3];
        String roleID = a[4];
        String addr = a[5];
        int phone = Integer.parseInt(a[6]);
        userDAO dao = new userDAO();
        userDTO user = new userDTO(userID, password, fullName, roleID, phone, addr);
        try {
            System.out.println(dao.insertUser(user));
        } catch (Exception e) {
            System.out.println(e);
        }
        PrintWriter out = response.getWriter();
        out.println("Account Created");
    }

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
