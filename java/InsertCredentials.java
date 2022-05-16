import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class InsertCredentials extends HttpServlet{
		// JDBC driver name and database URL
      static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
      static final String DB_URL="jdbc:mysql://localhost:3306/quizapp";

      //  Database credentials
      static final String USER = "root";
      static final String PASS = "Shreya@123";

	  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   
		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        String title="Insert Credentials";
        String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      
		out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title>\n"+
         "<link rel = \"stylesheet\" href = \"../css/cred.css\" type = \"text/css\">\n"+
         "<link rel = \"stylesheet\" href = \"../css/index.css\" type = \"text/css\">\n"+"</head>\n");
           
		try {
			// Register JDBC driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName(JDBC_DRIVER);
         
			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	
			// Execute SQL query 
			PreparedStatement st = conn
                   .prepareStatement("insert into credentials values(?,?)");
			st.setString(1, request.getParameter("username"));
			st.setString(2, request.getParameter("password"));
			st.executeUpdate();
  
            // Close all the connections
            st.close();
            conn.close();
  
            // Get a writer pointer 
            // to display the successful result
            response.sendRedirect("html/login.html");
            // forwarding the request to Welcome.java
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
} 