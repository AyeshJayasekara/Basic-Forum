/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posts;

import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import database.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author shanelm1
 */
@MultipartConfig
public class createpost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher dispatcher = null;
            
            if(request.getParameter("title") == null){
            dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/create_post.jsp" );
            dispatcher.include(request, response);
            }else{
                System.out.println(""+request.getParameter("title"));
                dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/home.jsp" );
                dispatcher.include(request, response);
            }
          
    }
    */
    
    
    //Working Code
    protected void processRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    RequestDispatcher dispatcher = null;
    if(request.getParameter("title") == null){
        HttpSession session = request.getSession(); 
        if(session.getAttribute("userid")==null)
            dispatcher=getServletContext().getRequestDispatcher( "/login.jsp" );
        else
            dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/create_post.jsp" );
                
        dispatcher.include(request, response);
            
    }else{
    // Create path components to save the file
    final String path = getServletContext().getRealPath("/resources/uploads");//request.getParameter("destination");
    final Part filePart1 = request.getPart("file1");
    final Part filePart2 = request.getPart("file2");
    final Part filePart3 = request.getPart("file3");
    //final String fileName = getFileName(filePart);
    
    HttpSession session = request.getSession();
    int id = (int) session.getAttribute("userid");
       String filename1 =  UploadFile1(filePart1, id, path);
       String filename2 = UploadFile1(filePart2, id, path);
       String filename3 =  UploadFile1(filePart3, id, path);
//    long millis = System.currentTimeMillis() % 1000;
//    final String fileName= id+"_"+millis+getFileExt(getFileName(filePart));
//
//    OutputStream out = null;
//    InputStream filecontent = null;
//    final PrintWriter writer = response.getWriter();
//
//    try {
//        out = new FileOutputStream(new File(path + File.separator
//                + fileName));
//        filecontent = filePart.getInputStream();
//
//        int read = 0;
//        final byte[] bytes = new byte[1024];
//
//        while ((read = filecontent.read(bytes)) != -1) {
//            out.write(bytes, 0, read);
//        }
//        String url = path+"/"+fileName;
//        writer.println("New file " + fileName + " created at " + path);
//        writer.println("<a href='file:///"+url+"'target=\"_blank\"> asd </a>");
//        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
//                new Object[]{fileName, path});
//    } catch (FileNotFoundException fne) {
//        writer.println("You either did not specify a file to upload or are "
//                + "trying to upload a file to a protected or nonexistent "
//                + "location.");
//        writer.println("<br/> ERROR: " + fne.getMessage());
//
//        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
//                new Object[]{fne.getMessage()});
//    } finally {
//        if (out != null) {
//            out.close();
//        }
//        if (filecontent != null) {
//            filecontent.close();
//        }
//        if (writer != null) {
//            writer.close();
//        }
//    }
    String title = request.getParameter("title");
//    final PrintWriter writer = response.getWriter();
//     writer.println(filename1+" ffffff "+title);
boolean status =false;
        switch(Users.GetType((int) session.getAttribute("userid"))){
            case 1:
                status = database.Posts.CreatePost(request.getParameter("title"), request.getParameter("desc"), request.getParameter("explain"), filename1, filename2, filename3, request.getParameter("cate"), (int)session.getAttribute("userid"));
                dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/create_post.jsp" );
                break;
            case 2:
                dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/service_providers.jsp" );
                status = database.Posts.CreateServices(request.getParameter("title"), request.getParameter("desc"), request.getParameter("explain"), filename1, filename2, filename3, request.getParameter("cate"), (int)session.getAttribute("userid"));
                break;
        }

        if(status){//database.Posts.CreatePost(request.getParameter("title"), request.getParameter("desc"), request.getParameter("explain"), filename1, filename2, filename3, request.getParameter("cate"), (int)session.getAttribute("userid"))){
            //dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/create_post.jsp" );
            dispatcher.include(request, response);
        }
        else{
            dispatcher=getServletContext().getRequestDispatcher( "/error.jsp" );
            dispatcher.include(request, response);
        }
    }
    
}
    
    
    
private String UploadFile1(Part filePart, int id, String path){
    long millis = System.currentTimeMillis() % 1000;
    final String fileName= id+"_"+millis+getFileExt(getFileName(filePart));

    OutputStream out = null;
    InputStream filecontent = null;
    //final PrintWriter writer = response.getWriter();

    try {
        out = new FileOutputStream(new File(path + File.separator
                + fileName));
        filecontent = filePart.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        String url = path+"/"+fileName;
        //writer.println("New file " + fileName + " created at " + path);
        //writer.println("<a href='file:///"+url+"'target=\"_blank\"> asd </a>");
        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
                new Object[]{fileName, path});
    } catch (FileNotFoundException fne) {
        //writer.println("You either did not specify a file to upload or are "
              //  + "trying to upload a file to a protected or nonexistent "
               // + "location.");
        //writer.println("<br/> ERROR: " + fne.getMessage());

        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
                new Object[]{fne.getMessage()});
    } 
    catch(Exception e){
    }
    finally {
        if (out != null) {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(createpost.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (filecontent != null) {
            try {
                filecontent.close();
            } catch (IOException ex) {
                Logger.getLogger(createpost.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    return fileName;
}    

private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
private String getFileExt(String name){
    return name.substring(name.indexOf('.'));
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
    private static final String UPLOAD_DIR = "uploads";
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
