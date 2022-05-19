import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class InsertDetails extends HttpServlet{
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
        String title="Insert Details";
        String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      
		out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title>\n"+
         "<link rel = \"stylesheet\" href = \"../css/cred.css\" type = \"text/css\">\n"+
         "<link rel = \"stylesheet\" href = \"../css/index.css\" type = \"text/css\">\n"+
         "<script>document.getElementById('hidden-form').submit()</script>"
         +"</head>\n"+
         "<body>\n");
        
         out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title>\n"+"<style>\n"+
         "body {\n"+
            "background: linear-gradient(#5EC2B7, #2CA6A4);\n"+ 
             "background-size: cover;\n"+ 
             "font-family: Verdana;\n"+ 
             "font-size: 20px;\n"+
             "height: 100vh;\n"+
             "width:100vw;\n"+
             "margin: 0;\n"+
             "overflow: auto;\n"+
             "overflow-X: hidden;\n"+
             "text-align: center;\n"+
      "}\n"+
         ".credentials-container {\n"+
             "text-align: center;\n"+
             "width: 60%;\n"+
             "margin: auto;\n"+
             "background: black;\n"+
             "border-radius: 30px;\n"+
      "}\n"+
     
         "table {\n"+
             "text-align: center;\n"+
         "}\n"+
     
         "label {\n"+
             "color: white;\n"+
             "font-size: 30px;\n"+
         "}\n"+
     
         "tr {\n"+
             "width: 100%;\n"+
             "text-align: center;\n"+
             "padding: 20px;\n"+
         "}\n"+
     
         "td {\n"+
             "width: 25%;\n"+
             "text-align: center;\n"+
             "padding: 20px;\n"+
         "}\n"+
     
         "input[type=\"submit\"] {\n"+
             "background: white;\n"+
             "border-radius: 5px;\n"+
             "padding: 10px;\n"+
             "color: black;\n"+
             "font-size: 20px;\n"+
             "width: 40%;\n"+
         "}\n"+
     
         "input[type=\"text\"] {\n"+
             "background: white;\n"+
             "border-radius: 5px;\n"+
             "padding: 10px;\n"+
             "color: black;\n"+
             "font-size: 20px;\n"+
             "width: 80%;\n"+
         "}\n"+
     
         "</style>\n");

        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
		try {
			// Register JDBC driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName(JDBC_DRIVER);
         
			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	
			// Execute SQL query 
			PreparedStatement st = conn
                   .prepareStatement("insert into registration values(?,?,?,?,?,?,?,?,?,?,?,?)");
			st.setString(1, request.getParameter("name"));
			st.setString(2, request.getParameter("cname"));
            st.setString(3, request.getParameter("cadd"));
			st.setInt(4, Integer.valueOf(request.getParameter("pin")));
            st.setInt(5, Integer.valueOf(request.getParameter("age")));
			st.setString(6, request.getParameter("dob"));
			st.setString(7, request.getParameter("gender"));
            st.setString(8, request.getParameter("dept"));
			st.setString(9, request.getParameter("contact"));
			st.setString(10, request.getParameter("email"));
            st.setString(11, request.getParameter("progskills"));
			st.setString(12, request.getParameter("hobb"));
			st.executeUpdate();
            Cookie cookie1 = new Cookie("Name", name);
            // Add cookie to HTTP response.
            response.addCookie(cookie1);
            Cookie cookie2 = new Cookie("Contact", contact);
            // Add cookie to HTTP response.
            response.addCookie(cookie2);
            out.println("<br><br><h1>Registered Successfully!<h1><br><br>");
            out.println("<form id='hidden-form' class='credentials-container' action='http://localhost:8080/quizapp/RenQuiz'>\n"+
            "<input type='hidden' name='name' value='"+request.getParameter("name")+"'>\n"+"<label>Do You Want To Proceed To Quiz?</label>\n<br><br><input type='submit' value='Yes, Proceed'><br><br>\n"+"</form></body></html>");
  
            // Close all the connections
            st.close();
            conn.close();
  
            // Get a writer pointer 
            // to display the successful result
            // forwarding the request to Welcome.java
            //out.println("Successfully updated!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
} 