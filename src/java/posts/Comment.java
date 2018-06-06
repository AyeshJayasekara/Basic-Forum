/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posts;

import database.Database;
import database.Posts;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shanelm1
 */
@WebServlet(name = "Comment", urlPatterns = {"/Comment"})
public class Comment extends HttpServlet {

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
        String id= request.getParameter("id");
        //String user= request.getParameter("user");
        String comment = request.getParameter("comment");
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        int user = (int) session.getAttribute("userid");
        dispatcher=getServletContext().getRequestDispatcher( "/error.jsp" );
        try{
        String sql = "Insert into comment (createdon,modifiedon,refid,comment,postid) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = Database.getDatabase().connect.prepareStatement(sql);
            Calendar cal = Calendar.getInstance(); 
            java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, user);
            preparedStatement.setString(4, comment);
            preparedStatement.setInt(5, Integer.parseInt(id));
            preparedStatement.executeUpdate();
            
        }
    catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
    }
            List<Comments> com = new ArrayList<>();
            ResultSet rs = Database.getDatabase().Query("select * from commentview");
            try{
            while(rs.next()){

               Comments e = new Comments();
               e.setId(rs.getInt(1));
               e.setCreatedon(rs.getDate(2).toString());
               e.setModifiedon(rs.getDate(3).toString());
               e.setRefid(rs.getInt(4));
               e.setComment(rs.getString(5));
               e.setPostid(rs.getInt(6));
               e.setFullname(rs.getString(7));
               com.add(e);
            
            }
            dispatcher=getServletContext().getRequestDispatcher("/WEB-INF/home.jsp" );
            request.setAttribute("comment", com);
            dispatcher.include(request, response);
            }
            catch(Exception e){
                
            }
            
        dispatcher.include(request, response);
        
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
