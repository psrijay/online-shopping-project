/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.controller;

import com.onlineshopping.DAO.CartDAO;
import com.onlineshopping.DAO.RoleDAO;
import com.onlineshopping.DAO.UserDAO;
import com.onlineshopping.model.Cart;
import com.onlineshopping.model.Role;
import com.onlineshopping.model.User;
import com.onlineshopping.service.CartDAOImpl;
import com.onlineshopping.service.MailService;
import com.onlineshopping.service.RoleDAOImpl;
import com.onlineshopping.service.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iamsu
 */
@WebServlet(name = "UserController", urlPatterns = {"/User"})
public class UserController extends HttpServlet {

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
        String mailId=request.getParameter("mailId");
        String password=request.getParameter("password");
        UserDAO ud=new UserDAOImpl();
        RoleDAO rdo=new RoleDAOImpl();
         RequestDispatcher rd=request.getRequestDispatcher("SignUp.jsp");
        String reqPage=request.getHeader("referer");
        if(reqPage.contains("Login")){
            User u=new User(mailId, password);
            HttpSession session=request.getSession();
            u=ud.read(u);
            String OTP=request.getParameter("OTP");
            if(u.getId()>0){
                session.setAttribute("roles",rdo.readRole(new Role(u.getId())));
                if(!ud.validateUser(u,OTP)){
                    out.print("Invalid OTP Please try again!!");
                    rd=request.getRequestDispatcher("Login.jsp?otp=r");
                }else{
                    session.setAttribute("user", u);
                    rd=request.getRequestDispatcher("Welcome.jsp?name="+u.getFirstName()+" "+u.getLastName());
                }
                rd.forward(request, response);
            }else{
                rd=request.getRequestDispatcher("Login.jsp");
                out.print("Login Failed!!");
                rd.include(request, response);
            }
        }else{
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String phoneNumber=request.getParameter("phoneNumber");
        String gender=request.getParameter("gender");
        String confirmpassword=request.getParameter("confirmpassword");
       
        
        if(password.equals(confirmpassword)){
            User u=new User(firstName, lastName, mailId, phoneNumber, password);
            u.setGender(gender.charAt(0));
                if(ud.create(u)>0){
                    u=ud.read(u);
                    Role r=new Role("Customer", u.getId());
                    rdo.createRole(r);
                    Cart cart=new Cart(u);
                    CartDAO cd=new CartDAOImpl();
                    cd.create(cart);
                    MailService ms=new MailService();
                try {
                    ms.verifyMail(mailId, firstName+" "+lastName);
                    out.print("OTP Sent!!");
                } catch (SQLException ex) {
                    out.print("please check your Internet Status");
                }
                    out.println("User Registered Successfully!!");
                    out.println("Click <a href='Login.jsp'>here</a> to Login!!");
                }else{
                    out.println("Mail Id Already Existing!!");
                }
        }
        else{
             //request.setAttribute("pass", "auto");
            rd=request.getRequestDispatcher("SignUp.jsp?pass=auto");
             out.println("Password Mismatch!!");
        }
        rd.include(request, response);
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
