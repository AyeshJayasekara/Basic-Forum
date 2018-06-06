/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shanelm1
 */

public class Users {
    private static MessageDigest md;
    public static boolean isValid(String UserName, String Password){
        Password = cryptWithMD5(Password);
        try {
            ResultSet rs = Database.getDatabase().Query("select count(ID) from users where email='"+UserName+"' and password='"+Password+"'");
            rs.first();
            if(rs.getInt(1)!=0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static String GetName(String UserName){
        try {
            ResultSet rs = Database.getDatabase().Query("select fullname from users where email='"+UserName+"'");
            rs.first();
            String s = rs.getString(1);
                return s;
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Guest";
    }
    
     public static int GetId(String UserName){
        try {
            ResultSet rs = Database.getDatabase().Query("select id from users where email='"+UserName+"'");
            rs.first();
            int s = rs.getInt(1);
                return s;
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static int GetType(String UserName){
        try {
            ResultSet rs = Database.getDatabase().Query("select type from users where email='"+UserName+"'");
            System.out.println("select type from users where email='"+UserName+"'");
            rs.first();
            int s = rs.getInt(1);
            System.out.println(""+s);
                return s;
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static int GetType(int Userid){
        try {
            ResultSet rs = Database.getDatabase().Query("select type from users where id='"+Userid+"'");
            rs.first();
            int s = rs.getInt(1);
                return s;
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static int RegUser(String FullName,String email, String username, String pass, String type){
        try {
            String sql = "Insert Into users (email,fullname,username,password,type) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = Database.getDatabase().connect.prepareStatement(sql); //Singleton design pattern
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, FullName);
            preparedStatement.setString(3, username);
           
            preparedStatement.setString(4, cryptWithMD5(pass));
            preparedStatement.setString(5, type);
            preparedStatement.executeUpdate(); 
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static User GetUser(int id){
        String sql = "select * from users where id = "+id;
        ResultSet rs = Database.getDatabase().Query(sql);
        User u = new User();
        try{
            rs.first();
            u.setId(rs.getInt(1));
            u.setEmail(rs.getString(2));
            u.setFullname(rs.getString(3));
            u.setUsername(rs.getString(4));
            if(rs.getInt(6)==1)
            u.setType("Regular User");
            else
                u.setType("Service Provide");
            if(rs.getInt(7)==1)
                u.setStatus("Active");
            else
                u.setStatus("Inactive");
        }catch(Exception ex){
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public static String cryptWithMD5(String pass){// encrpting method
    try {
        md = MessageDigest.getInstance("MD5");
        byte[] passBytes = pass.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;


   }

    
}

