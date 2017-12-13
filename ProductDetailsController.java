/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.controller;

import com.onlineshopping.DAO.ProductDAO;
import com.onlineshopping.DAO.ProductDetailsDAO;
import com.onlineshopping.model.Product;
import com.onlineshopping.model.ProductDetails;
import com.onlineshopping.service.ProductDAOImpl;
import com.onlineshopping.service.ProductDetailsDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ProductDetailsController", urlPatterns = {"/ProductDetails"})
public class ProductDetailsController extends HttpServlet {

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
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        
        String mode=request.getParameter("m");
        ProductDetailsDAO pdd=new ProductDetailsDAOImpl();
        ProductDAO pdo=new ProductDAOImpl();
        if(mode.equals("r")){
            try{
             int id=Integer.parseInt(request.getParameter("id"));
                session.setAttribute("products",pdo.read(new Product(id)));
            }catch(Exception e){
                session.setAttribute("products",pdo.read());
            }
        }
        else{
        long serialNumber=Long.parseLong(request.getParameter("serialNumber"));
        int sellerId=0;
        
        if(mode.equals("c")){
            int productId=Integer.parseInt(request.getParameter("productId"));
            String sManufactureDate=request.getParameter("manufactureDate");
            String sExpieryDate=request.getParameter("expieryDate");
            String size=request.getParameter("size");
            String color=request.getParameter("color");
        
            out.print(sManufactureDate);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date manufactureDate=null,expieryDate=null;
            try {
                manufactureDate = sdf.parse(sManufactureDate);
                expieryDate=sdf.parse(sExpieryDate);
            } catch (ParseException ex) {}
        
            ProductDetails pd=new ProductDetails(serialNumber, productId, 
                new java.sql.Date(manufactureDate.getYear(),manufactureDate.getMonth(),manufactureDate.getDate()), 
                new java.sql.Date(expieryDate.getYear(),expieryDate.getMonth(),expieryDate.getDate()), size, color, sellerId);
        
            if(!mode.equals("u")){
                pdd.create(pd);
            }
            if(mode.equals("u")){
                pdd.update(pd);
                session.setAttribute("productDetails", null);
            }
        } 
        else {
                serialNumber=Integer.parseInt(request.getParameter("id"));
                ProductDetails p=pdd.read(new ProductDetails(serialNumber));
                if(mode.equals("e")){
                    session.setAttribute("productDetails", p);
                    List<Product> products=new ArrayList<>();
                    Product pc=pdo.read(new Product(p.getProductId()));
                    out.println("Product "+pc.getName());
                    products.add(pc);
                    for(Product c:pdo.read()){
                    if(!c.getName().equals(pc.getName())){
                        products.add(c);
                    }
                }
                session.setAttribute("products", products);
            }
            if(mode.equals("d")){
                pdd.delete(p);
            }
        }
        response.sendRedirect("productDetails.jsp");
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
