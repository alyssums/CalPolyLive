import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddComment {
    public AddComment (String userID, String userComment) {
        try {
            String sql = "Insert Into comments (User_ID, User_Comment)"
                    + " Value ('" + userID + "', '" + userComment + "')";
        
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/calpolylivedb";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
        }
        catch(SQLException e) {
            e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
