import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalPolyLiveServlet extends HttpServlet {

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
            GrabComments getComments = new GrabComments();
            ArrayList<ArrayList<String>> commentArray = getComments.getComments();
           
        
            request.setAttribute("previousComments", commentArray);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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

        
        String type = request.getParameter("type");
        
        if (type.equals("login")) { 
            
            String loginName = request.getParameter("loginName");
            String loginPassword = request.getParameter("loginPassword");
            boolean validCredentials;
            //check if username and pass are valid
            User checkPass = new User(loginName, loginPassword);
            
            if ((checkPass.getUserName().equals(loginName) || checkPass.getEmail().equals(loginName)) && checkPass.getPass().equals(loginPassword) && checkPass.getUserId() != null)
                validCredentials = true;
            else 
                validCredentials = false;
            
            HttpSession mySession = request.getSession(false);
            
            if(mySession != null && validCredentials == true)
            {  
                mySession.setAttribute("userID", checkPass.getUserId());
                mySession.setAttribute("userName", checkPass.getUserName());
            }

            request.setAttribute("validCredentials", validCredentials);
        }
        else if (type.equals("logout")) {
            HttpSession mySession = request.getSession(false);
            mySession.invalidate();
        }
        else if (type.equals("account")) {
            String userName = request.getParameter("newName");
            String userPass = request.getParameter("newPassword");
            String userEmail = request.getParameter("email");
            String userMajor = request.getParameter("major");

            NewAccount account = new NewAccount(userName, userPass, userEmail, userMajor);     
        
        }else if (type.equals("comment")) {
            String userComment = request.getParameter("newComment");
            String userID = request.getSession().getAttribute("userID").toString();
            AddComment comment = new AddComment(userID, userComment);
        }
        
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
