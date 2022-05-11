

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class ClassReport
 */
@WebServlet("/ClassReport")
public class ClassReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("button");
			PrintWriter out = response.getWriter();
			out.print("<html> <body>");

			// TODO Auto-generated method stub
			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			Statement statement = con.createStatement();
			//String query = "SELECT * FROM student";
			String query="SELECT * from student where class_id="+id;

			//Step-4  Execute Query and get the results
			ResultSet resultSet = 	statement.executeQuery(query);			

			out.println("<table border=1 width=50% height=50%>");  
			out.println("<tr><th>Id</th><th>Student Name</th><th>Age</th><th>Subject</th><th>Teacher</th><th>Student_Contact</th><tr>"); 
			while(resultSet.next()) {
				String str = resultSet.getString("class_id");
				Map<String, String> details = getDetails(str);
				out.print("<tr><td>" + resultSet.getString("student_id") + "</td><td>" 
						+ resultSet.getString("student_name") + "</td><td>"
						+ resultSet.getString("age") 
						+ "</td><td>"+ details.get("subject")
						+ "</td><td>"+ details.get("teacher") + "</td><td>"+
						resultSet.getString("contact")  +"</td></tr>" );
			}

			out.println("</table>"); 
			out.println("<form action=ViewClassServlet method=post>");
			out.println("<input type=submit value= Back>");
			out.println("&ensp;");
			//			out.println("<a href=ViewClassServlet>Back</a>");
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
	private Map<String, String> getDetails(String class_id){
		Map<String, String> details = new HashMap<>();
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			Statement statement = con.createStatement();
			String query2 = "SELECT * FROM subject where id="+class_id;
			ResultSet resultSet = 	statement.executeQuery(query2);

			if(!resultSet.isBeforeFirst()) {
				details.put("subject", "No Subject registered for this class"); 
				details.put("teacher", "No Teacher available for this class");
			}
			else {
				while(resultSet.next()) {
					String teacherName = getTeacher(resultSet.getString("teacher_id"));
					String subjectName = resultSet.getString("subject");
					details.put("teacher", teacherName);
					details.put("subject", subjectName);
				}		
			}
			resultSet.close();

			statement.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return details;

	}

	private String getTeacher(String teacher_id){
		String teacherName = "";
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			Statement statement = con.createStatement();
			String query2 = "SELECT * FROM teacher where id="+teacher_id;
			ResultSet resultSet = 	statement.executeQuery(query2);
			if(!resultSet.isBeforeFirst()) {
				teacherName = "No Teacher available for this class"; 
			}
			else {
				while(resultSet.next()) {
					teacherName = resultSet.getString("teacher_name");
				}
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

