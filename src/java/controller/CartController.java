/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDTO;
import dao.OrderDTO;
import dao.bookDAO;
import dao.bookDTO;
import dao.userDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        bookDAO dao = new bookDAO();
        String bookID = request.getParameter("BookID");
        bookDTO book = dao.getBookbyID(bookID);
        HttpSession session = request.getSession();
        int quantity = 1;
        List<CartDTO> cartList = new ArrayList<>();
        String user = ((userDTO) session.getAttribute("LOGIN_USER")).getId();
        if (request.getParameter("removeId") != null) {
            String id = request.getParameter("removeId");
            cartList = (List<CartDTO>) session.getAttribute("CART_LIST");
            for (CartDTO o : cartList) {
                if (o.getBookID().equalsIgnoreCase(id)) {
                    cartList.remove(o);
                }
            }
        } else {
            if (request.getParameter("quantity") != null) {
                try {
                    if (request.getParameter("quantity") == "") {
                        quantity = 1;
                    } else {
                        quantity = Integer.parseInt(request.getParameter("quantity"));
                    }
                } catch (Exception e) {
                }
            }
            CartDTO cart = new CartDTO();
            cart.setId(dao.IDGen("cart"));
            cart.setBookID(bookID);
            cart.setQuantity(quantity);
            cart.setOrderID(1);
            cart.setPrice(book.getPrice());
            cart.setBook(book);
            if (session.getAttribute("CART_LIST") == null) {
                cartList.add(cart);
                session.setAttribute("CART_LIST", cartList);
            } else {
                cartList = (List<CartDTO>) session.getAttribute("CART_LIST");
                boolean c = true;
                for (CartDTO f : cartList) {
                    if (f.getBookID().equalsIgnoreCase(cart.getBookID())) {
                        f.setQuantity(f.getQuantity() + quantity);
                        c = false;
                    }
                }
                if (c) {
                    cartList.add(cart);
                }
                session.setAttribute("CART_LIST", cartList);
            }

        }
        PrintWriter out = response.getWriter();
        if (cartList.isEmpty()) {
            out.print("<h1>No book Added</h1>");
        } else {
            for (CartDTO o : cartList) {
                out.println("<tr class=\"cart-row\">\n"
                        + "                                                    <th scope=\"row\">\n"
                        + "                                                        <div class=\"p-2\">\n"
                        + "                                                            <img src=\"" + o.getBook().getImg() + "\" alt=\"\" width=\"70\" class=\"img-fluid rounded shadow-sm\">\n"
                        + "                                                            <div class=\"ml-3 d-inline-block align-middle\">\n"
                        + "                                                                <h5 class=\"mb-0\"> <a href=\"#\" class=\"text-dark d-inline-block\">" + o.getBook().getName() + "</a></h5><span class=\"text-muted font-weight-normal font-italic\"></span>\n"
                        + "                                                            </div>\n"
                        + "                                                        </div>\n"
                        + "                                                    </th>\n"
                        + "                                                    <td class=\"align-middle\"><strong class=\"cart-price\" value=\"" + o.getPrice() + "\">" + o.getPrice() + "</strong></td>\n"
                        + "                                                    <td class=\"align-middle\" id=\"cart" + o.getBookID() + "\">\n"
                        + "                                                        <a href=\"#\"><button class=\"btnSub\">-</button></a> \n"
                        + "                                                        <input min=\"0\" data-role=\"changeQuan\" class=\"cart-quantity\" type=\"number\" onclick=\"changeQuan(this)\" data-id=\"" + o.getBookID() + "\" data-target=\"quantity\" value=\"" + o.getQuantity() + "\"></input>\n"
                        + "                                                        <a href=\"#\"><button class=\"btnAdd\">+</button></a>\n"
                        + "                                                    </td>\n"
                        + "                                                    <td class=\"align-middle\" id=\"removeSect\">"
                        + "                                                            <button type=\"button\" onclick=\"contentLoaded(this)\" id=\"removeCartBt\" value=" + o.getBookID() + " class=\"btn btn-danger\">Delete</button>\n"
                        + "                                                    </td>\n"
                        + "                                                </tr> ");
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
