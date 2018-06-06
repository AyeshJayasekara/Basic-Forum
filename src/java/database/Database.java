/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Shanelm1
 */
import config.Configurations;
import java.sql.* ;  
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    public static Database instance=null; 
    public Connection connect = null;
    public Statement statement = null;
    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;
    
    public static Database getDatabase(){
        if(instance==null)
            instance = new Database();
        return instance;
    }
    
    public ResultSet readTable(String TableName){
        try {
            resultSet =null;
            
            resultSet = statement
                    .executeQuery("select * from "+TableName);
            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }
    
    public ResultSet Query(String query){
        try {
            resultSet =null;
            resultSet = statement
                    .executeQuery(query);
            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }

    public Database() {
       
        String url = Configurations.DBUrl;
        String user = Configurations.DBUser;
        String password = Configurations.DBPassword;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
            statement = connect.createStatement();
        }catch(Exception e){
            System.out.println("Something went wrong! "+e.toString());
        }
    }
    
    
    
    
    
    
    
}
