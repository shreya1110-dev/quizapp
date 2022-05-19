import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class RenderQuiz extends HttpServlet{
		// JDBC driver name and database URL
      static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
      static final String DB_URL="jdbc:mysql://localhost:3306/quizapp";

      //  Database credentials
      static final String USER = "root";
      static final String PASS = "Shreya@123";


      int count = 1;
      

	  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
 
        
      // creating HTTP Session
		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Quiz";
		String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

		try {
            HttpSession session = request.getSession();
            String id = session.getId();

      session.setAttribute("usercount",Integer.valueOf(count));
      count++;
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			//Class.forName("com.mysql.cj.jdbc.Driver");

			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			// Execute SQL query
			Statement stmt = conn.createStatement();
			String sql,n="", contact="";

            Cookie[] cookie = request.getCookies();
            for(int i=0; i<cookie.length; i++) {
                if(cookie[i].getName().equals("Name")) {
                    n = cookie[i].getValue();
                }
                if(cookie[i].getName().equals("Contact")) {
                    contact = cookie[i].getValue();
                }
            }


			sql = "SELECT * FROM quiz";
			ResultSet rs = stmt.executeQuery(sql);

			// Extract data from result set
            out.println("<h1>QUIZ</h1><br>");
            out.println("<h3>Name: "+n+"</h3>");
            out.println("<h3>Contact: "+contact+"</h3><br><br>");
            out.println("<form class='credentials-container' action='http://localhost:8080/quizapp/DispScore'>\n<table width='100%'>\n");
            int qn = 1;
			while(rs.next()){
				//Retrieve by column name
				String question  = rs.getString("question");
                String answer = rs.getString("answer");
				String options = rs.getString("options");
                String[] option = options.split(",");
                out.println("<tr>\n<td colspan='4'><label>"+qn+") "+question+"</label></td>\n</tr>");
                for(int i=0; i<option.length; i++) {
                    out.println("<td><label>"+(i+1)+") "+option[i]+"</label></td>\n");
                }
                out.println("</tr>\n<td colspan='4'><input type='text' name='answer"+ qn +"'placeholder='Enter answer'></td></tr>");
                qn++;
			}
            out.println("<input type='hidden' name='id' value="+id+">");
            out.println("<input type='hidden' name='name' value="+name+"><tr><td colspan='4'><input type='submit' value='Submit'</td></tr>");
			out.println("</body></html>");

			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
		} 
		catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
		} 
	} 
} 