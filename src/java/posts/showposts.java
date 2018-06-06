/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posts;

import java.io.IOException;
import database.*;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
public class showposts extends HttpServlet {

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
        try{
            response.setContentType("text/html;charset=UTF-8");
            List<Map<String, Object>> ll ;
            List<Post> posts = new ArrayList<>();
            ResultSet rs = Database.getDatabase().Query("select * from forumpost");
            
            while(rs.next()){
                Post p  = new Post();
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
                posts.add(p);
            }
            request.setAttribute("post", posts);

            
            ll = resultSetToList(rs);
            for (int i=0; i<ll.size(); i++) {
                System.out.print(" " + ll.get(i).get("created"));
            }
            request.setAttribute("posts", ll);
            request.setAttribute("result", rs);
            RequestDispatcher dispatcher = null;
            dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/posts.jsp" );
            dispatcher.include(request, response);
            
            
        }
            
       catch (SQLException ex) {
            Logger.getLogger(showposts.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    private List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
    while (rs.next()){
        Map<String, Object> row = new HashMap<String, Object>(columns);
        for(int i = 1; i <= columns; ++i){
            row.put(md.getColumnName(i), rs.getObject(i));
        }
        rows.add(row);
    }
    return rows;
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
