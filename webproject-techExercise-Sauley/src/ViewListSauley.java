
/**
 * @file ViewListSauley.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewList")
public class ViewListSauley extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public ViewListSauley() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;

      try {
    	  // Grab item to insert
         DBConnectionSauley.getDBConnection();
         connection = DBConnectionSauley.connection;
         
         // Display items after insert
         String displaySQL = "SELECT * FROM MyTableSauleyToDoList";
         PreparedStatement preparedStmtDisplay = connection.prepareStatement(displaySQL);
         
         ResultSet rs = preparedStmtDisplay.executeQuery();
         while (rs.next()) {
             int id = rs.getInt("id");
             String itemToDisplay = rs.getString("item").trim();
             out.print("Item " + id + ": ");
             out.println(itemToDisplay + "<br>");
             
          }
         
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      out.println(docType + //
            "<html>\n" + //
            "<ul>\n" + //

            "</ul>\n");
      
      out.println("<br><br><a href=/webproject-techExercise-Sauley/insert_sauley.html>Insert Item</a> <br>");
      out.println("      <a href=/webproject-techExercise-Sauley/remove_sauley.html>Remove Item</a> <br>");
      out.println("</body></html>");
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
