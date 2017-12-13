/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.controller;

import com.onlineshopping.DAO.CategoryDAO;
import com.onlineshopping.DAO.ProductDAO;
import com.onlineshopping.model.Category;
import com.onlineshopping.model.Product;
import com.onlineshopping.service.CategoryDAOImpl;
import com.onlineshopping.service.ProductDAOImpl;
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

/**
 *
 * @author iamsu
 */
@WebServlet(name = "ProductController", urlPatterns = {"/Product"})
public class ProductController extends HttpServlet {

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
        CategoryDAO cd=new CategoryDAOImpl();
        ProductDAO pd=new ProductDAOImpl();
        HttpSession session=request.getSession();
        session.setAttribute("categories", cd.read());
        session.setAttribute("products", pd.read());
        String mode=request.getParameter("m");
        if(mode.equals("c") || mode.equals("u")){
            String name=request.getParameter("productName");
            String description=request.getParameter("description");
            float price=Float.parseFloat(request.getParameter("price"));
            int categoryId=Integer.parseInt(request.getParameter("categoryId"));
            String manufactureName=request.getParameter("manufactureName");
            Product p=new Product(name, description, price, categoryId, manufactureName);
            if(!mode.equals("u")){
                pd.create(p);
            }
            if(mode.equals("u")){
                int productId=Integer.parseInt(request.getParameter("pid"));
                p.setId(productId);
                pd.update(p);
                session.setAttribute("product", null);
            }
        }else if(mode.equals("v")){
            session.setAttribute("vproducts", pd.read());
            response.sendRedirect("ViewProducts.jsp");
        }
        else if(mode.equals("r")){
            try{
                int categoryId=Integer.parseInt(request.getParameter("id"));
                Category c=new Category(categoryId);
                session.setAttribute("products", pd.read(c));
            }catch(Exception e){
                
            }
        }
        else{
            int productId=Integer.parseInt(request.getParameter("id"));
            Product p=pd.read(new Product(productId));
            if(mode.equals("e")){
                session.setAttribute("product", p);
                List<Category> categories=new ArrayList<>();
                Category pc=cd.read(new Category(p.getCategoryId()));
                out.println("Category "+pc.getName());
                categories.add(pc);
                for(Category c:cd.read()){
                    if(!c.getName().equals(pc.getName())){
                        categories.add(c);
                    }
                }
                session.setAttribute("categories", categories);
            }
            if(mode.equals("d")){
                pd.delete(p);
            }
        }
        response.sendRedirect("Product.jsp");
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
