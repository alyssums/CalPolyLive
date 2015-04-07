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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrabComments {
    ArrayList<ArrayList<String>> commentsList = new ArrayList<ArrayList<String>>();
    Connection connection;
    public GrabComments () {
        try {            
                Class.forName("com.mysql.jdbc.Driver");
                String dbURL = "jdbc:mysql://localhost:3306/calpolylivedb";
                String username = "root";
                String password = "sesame";
                String sql = "select * from comments join users on users.User_ID = comments.User_ID order by Posted_TimeStamp desc";
                connection = DriverManager.getConnection(dbURL, username, password);
                
                Statement statement = connection.createStatement();
                ResultSet comments = statement.executeQuery(sql);
                
                while (comments.next()) {
                    ArrayList<String> com = new ArrayList<String>();
                    com.add(comments.getString("Posted_TimeStamp"));
                    com.add(comments.getString("User_Name"));
                    com.add(comments.getString("User_Comment"));
                    com.add(comments.getString("User_Major"));
                    com.add(comments.getString("User_ID") + ".jpg");
                    
                    commentsList.add(com);
                }
        }
        catch (SQLException e){
            e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GrabComments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<ArrayList<String>> getComments() {
        return commentsList;
    }
}
