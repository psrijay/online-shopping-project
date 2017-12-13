/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.controller;

import com.onlineshopping.DAO.CartDAO;
import com.onlineshopping.DAO.ProductDAO;
import com.onlineshopping.DAO.UserDAO;
import com.onlineshopping.model.Cart;
import com.onlineshopping.model.CartItem;
import com.onlineshopping.model.Product;
import com.onlineshopping.model.User;
import com.onlineshopping.service.CartDAOImpl;
import com.onlineshopping.service.ProductDAOImpl;
import com.onlineshopping.service.UserDAOImpl;
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
 * @author iamsu
 */
@WebServlet(name = "CartController", urlPatterns = {"/Cart"})
public class CartController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        try{
            String mode=request.getParameter("m");
            
            int userId=Integer.parseInt(request.getParameter("u"));
            int pid=Integer.parseInt(request.getParameter("p"));
            boolean isInCart=false;
            
            UserDAO ud=new UserDAOImpl();
            ProductDAO pd=new ProductDAOImpl();
            CartDAO cd=new CartDAOImpl();
            User u=new User(userId);
            Product p=pd.read(new Product(pid));
            Cart c=new Cart(u);
            c=cd.read(c);
            session.setAttribute("cart",c);
            CartItem ci=new CartItem(c, p,1);
            if(mode.equals("a")){
            c.setCartItems(cd.read(c,u));
            out.print(c.getCartItems().size());
            for(CartItem i:c.getCartItems()){
                if(i.getProduct().getId() == ci.getProduct().getId()){
                    i.setQuantity(i.getQuantity()+1);
                    c.getCartItems().add(i);
                    isInCart=true;
                }
            }
            
            if(!isInCart){
                c.getCartItems().add(ci);
            }
            c.setCartItems(c.getCartItems());
            for(CartItem item:c.getCartItems()){
                out.println(item.getQuantity());
            }
            cd.add(c);
            }
            else if(mode.equals("p")){
                c.getCartItems().add(ci);
                cd.add(c);
                response.sendRedirect("Order.jsp");
            }
            response.sendRedirect("Cart.jsp");
        }catch(Exception e){
            out.print(e);
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
