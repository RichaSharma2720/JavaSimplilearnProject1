

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ViewSubjectServlet
 */
@WebServlet("/ViewSubjectServlet")
public class ViewSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSubjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.print("<html> <body>");

			// TODO Auto-generated method stub
			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			Statement statement = con.createStatement();
			String query = "SELECT * FROM subject";
			
		//Step-4  Execute Query and get the results
			ResultSet resultSet = 	statement.executeQuery(query);			
			
			out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>Id</th><th>Subject</th><th>Class</th><th>Section</th><th>Teacher</th><tr>"); 
			while(resultSet.next()) {
				String class_id = resultSet.getString("class_id");
				String teacher_id = resultSet.getString("teacher_id");
				
				List<String> class1 = getClass(class_id);
				String teacherName = getTeacher(teacher_id);
				out.print("<tr><td>" + resultSet.getString("id") + "</td><td>" + resultSet.getString("subject") + "</td><td>"+ class1.get(0)
				+ "</td><td>"+ class1.get(1)+"</td><td>"+ teacherName  +"</td></tr>" );
			}
			
			out.println("</table>"); 
			out.println("<form action=AddStudent.html method=post>");
			out.println("<input type=submit value= Add>");
			out.println("&ensp;");
			out.println("<a href=Home.html>Back</a>");
			out.println("<a href=Login.html>logout</a>");

			out.println("</form>");
			out.println("</html></body>"); 
			//close objects
			resultSet.close();
			statement.close();
			con.close();

			// Get a writer pointer 
			// to display the successful result
//			PrintWriter out = response.getWriter();
//			out.println("<html><body><b>Successfully Inserted"
//					+ "</b></body></html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private List<String> getClass(String class_id){
		List<String> classDetails = new ArrayList<>();
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			Statement statement = con.createStatement();
			String query2 = "SELECT * FROM classes where id="+class_id;
			ResultSet resultSet = 	statement.executeQuery(query2);
			while(resultSet.next()) {
			classDetails.add(resultSet.getString("class_number"));
			classDetails.add(resultSet.getString("section"));				
			}
			
			resultSet.close();
			statement.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classDetails;
		
	}
	
	private String getTeacher(String teacher_id){
		String teacherName = "";
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			Statement statement = con.createStatement();
			String query2 = "SELECT * FROM teacher where id="+teacher_id;
			ResultSet resultSet = 	statement.executeQuery(query2);
			while(resultSet.next()) {
				teacherName = resultSet.getString("teacher_name");			
			}
			resultSet.close();
			statement.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherName;
		
	}

}
