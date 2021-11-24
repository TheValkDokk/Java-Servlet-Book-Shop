/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDTO;
import dao.OrderDTO;
import dao.bookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TotalCheckOutController", urlPatterns = {"/TotalCheckOutController"})
public class TotalCheckOutController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        bookDAO dao = new bookDAO();
        int phone = 0;
        try {
            phone = Integer.parseInt(request.getParameter("phone"));
        } catch (Exception e) {
            phone = Integer.parseInt(request.getParameter("hidPhone"));
        }
        String addr = request.getParameter("addr");
        System.out.println(phone);
        System.out.println(addr);
        HttpSession session = request.getSession(false);
        List<String> listbk;
        listbk = (List<String>) session.getAttribute("BookBuyList");
        String userID = (String) session.getAttribute("USER_ID");
        int total = Integer.parseInt((String) session.getAttribute("Total_Price"));
        boolean check = true;
        int orderID = dao.IDGen("order");
        OrderDTO order = new OrderDTO(orderID, userID, addr, "1", total);
        dao.CreateOrder(order);
        for (String s : listbk) {
            int cartID = dao.IDGen("cart");
            String[] theproductBuy = s.split("-");
            String bookid = theproductBuy[0];
            int quan = Integer.parseInt(theproductBuy[1]);
            float price = dao.getBookbyID(bookid).getPrice();
            CartDTO cart = new CartDTO(cartID, bookid, quan, price, orderID);
            dao.addCart(cart);
            check = dao.reduceQuan(bookid, quan);
        }
        out.println("<div class=\"payment_header\">\n"
                + "               <div class=\"check\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i></div>\n"
                + "            </div>\n"
                + "            <div class=\"content\">\n"
                + "               <h1>Payment Success !</h1>\n"
                + "               <a href=\"/BookShop/home\">Go to Home</a>\n <p>Page Refesh after 3 sec</p>"
                + "            </div><link rel=\"stylesheet\" type=\"text/css\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" />\n"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\" />\n"
                + "<style type=\"text/css\">\n"
                + "\n"
                + "    body\n"
                + "    {\n"
                + "        background:#f2f2f2;\n"
                + "    }\n"
                + "\n"
                + "    .payment\n"
                + "	{\n"
                + "		border:1px solid #f2f2f2;\n"
                + "		height:280px;\n"
                + "        border-radius:20px;\n"
                + "        background:#fff;\n"
                + "	}\n"
                + "   .payment_header\n"
                + "   {\n"
                + "	   background:rgba(220, 20, 60,1);\n"
                + "	   padding:20px;\n"
                + "       border-radius:20px 20px 0px 0px;\n"
                + "	   \n"
                + "   }\n"
                + "   \n"
                + "   .check\n"
                + "   {\n"
                + "	   margin:0px auto;\n"
                + "	   width:50px;\n"
                + "	   height:50px;\n"
                + "	   border-radius:100%;\n"
                + "	   background:#fff;\n"
                + "	   text-align:center;\n"
                + "   }\n"
                + "   \n"
                + "   .check i\n"
                + "   {\n"
                + "	   vertical-align:middle;\n"
                + "	   line-height:50px;\n"
                + "	   font-size:30px;\n"
                + "   }\n"
                + "\n"
                + "    .content \n"
                + "    {\n"
                + "        text-align:center;\n"
                + "    }\n"
                + "\n"
                + "    .content  h1\n"
                + "    {\n"
                + "        font-size:25px;\n"
                + "        padding-top:25px;\n"
                + "    }\n"
                + "\n"
                + "    .content a\n"
                + "    {\n"
                + "        width:200px;\n"
                + "        height:35px;\n"
                + "        color:#fff;\n"
                + "        border-radius:30px;\n"
                + "        padding:5px 10px;\n"
                + "        background:rgba(255,102,0,1);\n"
                + "        transition:all ease-in-out 0.3s;\n"
                + "    }\n"
                + "\n"
                + "    .content a:hover\n"
                + "    {\n"
                + "        text-decoration:none;\n"
                + "        background:#000;\n"
                + "    }\n"
                + "   \n"
                + "</style");

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
