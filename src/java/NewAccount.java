import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewAccount {
    public NewAccount (String userName, String userPass, String userEmail, String userMajor) {
        try {
            String sql = "Insert Into users (User_Name, User_Pass, User_Email, User_Major)"
                    + " Values ('" + userName + "', '" + userPass + "', '" + userEmail + "', '" + userMajor + "')";
        
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/calpolylivedb";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
           // stm.executeQuery(sql);
        }
        catch(SQLException e) {
            e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
