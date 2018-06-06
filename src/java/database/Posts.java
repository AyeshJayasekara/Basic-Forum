/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayeshjayasekara1
 */
public class Posts {
    
    public static boolean AddPost(String topic, String Content, String URL){
        try {
            String sql = "Insert into posts (created,postedby,topic,content,picurl) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = Database.getDatabase().connect.prepareStatement(sql);
            Calendar cal = Calendar.getInstance(); 
            java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, topic);
            preparedStatement.setString(4, Content);
            preparedStatement.setString(5, URL);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean CreatePost(String title, String descri ,String expl, String URL1,String URL2,String URL3, String cate, int id){
        try {
            String sql = "Insert into forumpost (created,modified,postedby,title,descri,expl,URL1,URL2,URL3,cate) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = Database.getDatabase().connect.prepareStatement(sql);
            Calendar cal = Calendar.getInstance(); 
            java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, id);
            preparedStatement.setString(4, title);
            preparedStatement.setString(5, descri);
            preparedStatement.setString(6, expl);
            preparedStatement.setString(7, URL1);
            preparedStatement.setString(8, URL2);
            preparedStatement.setString(9, URL3);
            preparedStatement.setString(10, cate);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean CreateServices(String title, String descri ,String expl, String URL1,String URL2,String URL3, String cate, int id){
        try {
            String sql = "Insert into servicepost (created,modified,postedby,title,descri,expl,URL1,URL2,URL3,cate) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = Database.getDatabase().connect.prepareStatement(sql);
            Calendar cal = Calendar.getInstance(); 
            java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, id);
            preparedStatement.setString(4, title);
            preparedStatement.setString(5, descri);
            preparedStatement.setString(6, expl);
            preparedStatement.setString(7, URL1);
            preparedStatement.setString(8, URL2);
            preparedStatement.setString(9, URL3);
            preparedStatement.setString(10, cate);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean AddComment(int postid, int refid, String Comment){
        try{
        String sql = "Insert into comment (createdon,modifiedon,refid,comment,postid) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = Database.getDatabase().connect.prepareStatement(sql);
            Calendar cal = Calendar.getInstance(); 
            java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, refid);
            preparedStatement.setString(4, Comment);
            preparedStatement.setInt(5, postid);
            preparedStatement.executeUpdate();
            return true;
        }
    catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
    }
        return false;
    }
    
}
