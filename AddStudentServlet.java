

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class AddStudentServlet
 */
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			  
            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();
  
            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                   .prepareStatement("insert into student (student_name ,class_id,age,contact) values(?, ?,?,?)");
  
  
            // Same for second parameter
            st.setString(1, request.getParameter("stu_name"));
            st.setInt(2,Integer.parseInt(request.getParameter("cl_id")));
            st.setInt(3, Integer.parseInt(request.getParameter("stu_age")));
            st.setString(4,request.getParameter("stu_cont"));
  
            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();
            
            // Close all the connections
            st.close();
            con.close();
  
            // Get a writer pointer 
            // to display the successful result
            PrintWriter out = response.getWriter();
            out.print("<html> <body>");
            out.println("<p>Studen added Succesfully. To go back please click on below link.");
            out.println("<a href=ViewStudentServlet>click here</a>");
            out.println("</body></html>");
        }
        catch (Exception e) {
            e.printStackTrace();
        }	}

}
