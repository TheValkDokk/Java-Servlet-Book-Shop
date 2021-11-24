/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDTO;
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "EditController", urlPatterns = {"/EditController"})
public class EditController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        bookDAO dao = new bookDAO();
        bookDTO newB = null;
        List<bookDTO> list = (List<bookDTO>) request.getSession(false).getAttribute("BookList");
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String cateID = request.getParameter("cateID").trim();
        boolean status = true;
        if (request.getParameter("status") == null) {
            status = false;
        }
        String Img = request.getParameter("img").trim();
        String descr = request.getParameter("descr").trim();
        String pub = request.getParameter("pub").trim();
        String pen = request.getParameter("pen").trim();
        String write = request.getParameter("write").trim();
        String cover = request.getParameter("cover").trim();
        newB = new bookDTO(id, name, price, quantity, cateID, Img, descr, cover, write, pen, pub, status);
        System.out.println(newB.toString());
        int count = 1;
        for (bookDTO f : list) {
            System.out.println("Scan");
            if (f.getId().equalsIgnoreCase(id)) {
                dao.UpdateBook(newB);
                System.out.println("Update");
                break;
            }else if(!f.getId().equalsIgnoreCase(id)){
                count += 1;
                if(count > list.size()) {
                    dao.addBook(newB);
                    System.out.println("Add");
                }
                    
            }
        }
        list = dao.listBook();
        PrintWriter out = response.getWriter();
        for (bookDTO o : list) {
            String st;
            if (o.isStatus() == true) {
                st = "<p style=\"color:green\">Active</p></c:if>\n";
            } else {
                st = "<p style=\"color:red\">Inactive</p></c:if>\n";
            }
            out.println("<tr id=\"" + o.getId() + "\">\n"
                    + "                                    <td data-target = \"status\">\n"
                    + "                                        " + st + "\n"
                    + "                                    </td>\n"
                    + "                                    <td data-target = \"id\">" + o.getId() + "</td>\n"
                    + "                                    <td data-target = \"name\">" + o.getName() + "</td>\n"
                    + "                                    <td data-target = \"price\">" + o.getPrice() + "</td>\n"
                    + "                                    <td data-target = \"quantity\">" + o.getQuantity() + "</td>\n"
                    + "                                    <td data-target = \"cateID\">" + o.getCateID() + "</td>\n"
                    + "                                    <td hidden data-target = \"img\"><p hidden>" + o.getImg() + "</p></td>\n"
                    + "                                    <td hidden data-target = \"dercs\"><p hidden>" + o.getDercs() + "</p></td>\n"
                    + "                                    <td data-target = \"pub\">" + o.getPub() + "</td>\n"
                    + "                                    <td data-target = \"write\">" + o.getWrite() + "</td>\n"
                    + "                                    <td data-target = \"cover\">" + o.getCover() + "</td>\n"
                    + "                                    <td data-target = \"pen\">" + o.getPen() + "</td>\n"
                    + "                                    <td>\n"
                    + "                                        <a href=\"#editBookModal\" data-role=\"update\" data-id=\"" + o.getId() + "\" class=\"edit\" data-toggle=\"modal\"><i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Edit\">&#xE254;</i></a>\n"
                    + "                                    </td>\n"
                    + "                                </tr>");
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
