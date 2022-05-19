import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class AdminDisplay extends HttpServlet{
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
      String title="Admin Page";
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

        HttpSession session = request.getSession();
        Integer count = (Integer)session.getAttribute("usercount");
        out.println("<h1>Welcome Admin!<h1>");
        out.println("<br><br><div class='credentials-container'><br><br><label>No of Users Taking the Test: "+count+"</label></div></body></html>");
		
	} 
} 