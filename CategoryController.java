

package com.onlineshopping.controller;

import com.onlineshopping.DAO.CategoryDAO;
import com.onlineshopping.model.Category;
import com.onlineshopping.service.CategoryDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CategoryController", urlPatterns = {"/Category"})
public class CategoryController extends HttpServlet {

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
        String mode=request.getParameter("m");
        CategoryDAO cd=new CategoryDAOImpl();
        if(mode.equals("c")){
            String name=request.getParameter("categoryName");
            String description= request.getParameter("description");
            Category c=new Category(name,description);
            cd.create(c);
        }else if(mode.equals("r")){
            
        }
        else{
            int id=Integer.parseInt(request.getParameter("id"));
            if(mode.equals("e")){
                Category c=cd.read(new Category(id));
                session.setAttribute("Category",c);
            }else if(mode.equals("u")){
                String name=request.getParameter("categoryName");
                String description= request.getParameter("description");
                Category c=new Category(name,description);
                c.setId(id);
                cd.update(c);
                session.setAttribute("Category", null);
            }
            else if(mode.equals("d")){
                cd.delete(new Category(id));
            }
        }
        session.setAttribute("categories", cd.read());
        response.sendRedirect("Category.jsp?r=t");
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
