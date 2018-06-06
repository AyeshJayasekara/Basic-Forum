/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posts;

import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shanelm1
 */
public class showservices extends HttpServlet {

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
       try{
            response.setContentType("text/html;charset=UTF-8");
            List<Map<String, Object>> ll ;
            List<Service> services = new ArrayList<>();
            ResultSet rs = Database.getDatabase().Query("select * from servicepost");
            while(rs.next()){
                Service p  = new Service();
                p.setId(rs.getInt(1));
                p.setCreated(rs.getTimestamp(2).toString());
                p.setModified(rs.getTimestamp(3).toString());
                p.setPostedby(rs.getInt(4));
                p.setTitle(rs.getString(5));
                p.setDescri(rs.getString(6));
                p.setExpl(rs.getString(7));
                p.setURL1(rs.getString(8));
                p.setURL2(rs.getString(9));
                p.setURL3(rs.getString(10));
                p.setCate(rs.getInt(11));
                services.add(p);
            }
            request.setAttribute("post", services);
            RequestDispatcher dispatcher = null;
            dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/services.jsp" );
            dispatcher.include(request, response);
            
            
        }
            
       catch (SQLException ex) {
            Logger.getLogger(showposts.class.getName()).log(Level.SEVERE, null, ex);
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
