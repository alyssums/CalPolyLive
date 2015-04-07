/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class User {
    String userID = "", userName = "", userEmail = "", userPass = "";
    
    public User(String userName, String password) {
        openConnection("Select * from Users where User_Name = '" + userName +"' or User_Email = '" + userName + "'");
    }
    
    private void openConnection(String sql) {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/calpolylivedb";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);

            Statement statement = connection.createStatement();
            ResultSet userResults = statement.executeQuery(sql);

            while (userResults.next()) {
                userID = userResults.getString("User_ID");
                userName = userResults.getString("User_Name");
                userEmail = userResults.getString("User_Email");
                userPass = userResults.getString("User_Pass");
            }
        
        }
        catch(SQLException e) {
            e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getUserId () {
        return userID;
    } 
    
    public String getPass () {
        return userPass;
    }
    
    public String getUserName () {
        return userName;
    }
    
    public String getEmail () {
        return userEmail;
    }
}
