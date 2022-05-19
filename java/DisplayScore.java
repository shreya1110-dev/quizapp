import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class DisplayScore extends HttpServlet{
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
		String title = "Quiz Score";
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

        String ans1 = request.getParameter("answer1");
        String ans2 = request.getParameter("answer2");
        String name = request.getParameter("name");
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			//Class.forName("com.mysql.cj.jdbc.Driver");

			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			// Execute SQL query
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM quiz";
			ResultSet rs = stmt.executeQuery(sql);

			// Extract data from result set
            int qn=1;
            int score1=0;
            int score2=0;
            int totalscore=0;
			while(rs.next()){
				//Retrieve by column name
                if(qn==1) {
                    String answer1 = rs.getString("answer");
                    if(ans1.equals(answer1)) {
                        score1 = 1;
                        totalscore++;
                    }
                }
                if(qn==2){
                    String answer2 = rs.getString("answer");
                    if(ans2.equals(answer2)) {
                        score2 = 1;
                        totalscore++;
                    }
                }
                qn++;
			}
            out.println("<h1>QUIZ SCORES</h1><br><br>");
            out.println("<h3>Hello, "+name+"</h3>");
                out.println("<div class='credentials-container'>\n"+
                "<table>\n"+
                "<tr>\n"+
                "<td><label>Question 1</label>/td>\n"+
                "<td>"+"<label>"+score1+"</label></td>\n"+
                "</tr>"+
                "<tr>\n"+
                "<td><label>Question 2</label></td>\n"+
                "<td><label>"+score2+"</label></td>\n"+
                "</tr>"+
                "<tr>\n"+
                "<td><label>Total Score</label></td>\n"+
                "<td><label>"+totalscore+"</label></td>\n"+
                "</tr>"+
                "</table>\n"+
                "</div>");
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