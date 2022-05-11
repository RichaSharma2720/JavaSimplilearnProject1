

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Servlet implementation class ViewStudentList
 */
@WebServlet("/ViewStudentList")
public class ViewStudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewStudentList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("button");
			PrintWriter out=response.getWriter();
			out.print("<html><body>");
			
			Connection con=DatabaseConnection.initializeDatabase();
			Statement st=con.createStatement();
			String Query="SELECT * from student where class_id="+id;
			ResultSet resultSet = 	st.executeQuery(Query);
			out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>Id</th><th>Student Name</th><th>Age</th><th>Contact</th><tr>"); 
			while(resultSet.next()) {
				out.print("<tr><td>" + resultSet.getString("student_id") 
				+ "</td><td>" + resultSet.getString("student_name") 
				+ "</td><td>"+  resultSet.getString("age") 
				+  "</td><td>"+ resultSet.getString("contact")+"</td></tr>" );
			}
			out.println("</table>"); 
			out.println("<form action=ViewClassServlet method=post>");
			out.println("<input type=submit value= back>");
			out.println("<a href=Login.html>logout</a>");

			
			
			out.println("</form>");

			out.println("</html></body>"); 
			
			resultSet.close();
			st.close();
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
