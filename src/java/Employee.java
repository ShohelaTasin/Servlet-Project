import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Employee")

public class Employee extends HttpServlet {
    
    Connection con;
    PreparedStatement pst;
    int row;
    
    public void doPost(HttpServletRequest req,HttpServletResponse rsp ) throws IOException,ServletException
    {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "12345");
            String empid = req.getParameter("empid");
            String empfname = req.getParameter("fname");
            String emplname= req.getParameter("lname");
            
            pst = con.prepareStatement("insert into register.employee(id,fname,lname)values(?,?,?) ");
            pst.setString(1, empid);
            pst.setString(2, empfname);
            pst.setString(3, emplname);
            row = pst.executeUpdate();
            
            out.println("<font color='green'>  Record Addedddd   </font>");
            
            //RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            //rd.include(req, rsp);
            rsp.sendRedirect("index.jsp");
 
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");
        } 
    }
  
}