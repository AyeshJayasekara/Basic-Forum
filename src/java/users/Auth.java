/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import database.Users;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shanelm1
 */
public class Auth extends HttpServlet {

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
        RequestDispatcher dispatcher = null;
        dispatcher=getServletContext().getRequestDispatcher( "/error.jsp" );  //Set error fallback page
        if(Users.isValid(request.getParameter("user"), request.getParameter("pass"))){ //Check if use is valid
            
            String username = Users.GetName(request.getParameter("user")); //Get the email from request and get the users real name from db
            Cookie loginCookie = new Cookie("user",username); //create a new cookie with name user and value username
            //setting cookie to expiry in 30 mins
            loginCookie.setMaxAge(30*60);
            response.addCookie(loginCookie); //add cookie to response
            
            HttpSession session = request.getSession(); //create new session
            session.setAttribute("username", username); //set session attribute username with real username
            session.setAttribute("userid", Users.GetId(request.getParameter("user"))); // get id of the user from the db and set to session
            switch(Users.GetType(request.getParameter("user"))){ //Get the type type of the user from db
                case 2:
                    dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/service_providers.jsp" ); //send service providers to this view
                    break;
                case 1:
                    dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/home.jsp" ); //send regular users to this view
                    break;            
            }
            //dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/home.jsp");
            dispatcher.include(request, response); //finalize the respone to client
        }
        else{
            HttpSession session = request.getSession(); //create a new session
            session.setAttribute("error", "Something is not right! Check your credentials and try again!"); //set error msg
            request.getRequestDispatcher("login.jsp").include(request, response); //redirect to login for next attempts
            //response.sendRedirect("");
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
