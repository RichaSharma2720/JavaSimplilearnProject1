

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
 * Servlet implementation class ViewClassServlet
 */
@WebServlet("/ViewClassServlet")

public class ViewClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			PrintWriter out=response.getWriter();
			out.print("<html><body>");
			Connection con=DatabaseConnection.initializeDatabase();
			Statement st=con.createStatement();
			String Query="SELECT * from classes";
			ResultSet resultSet = 	st.executeQuery(Query);
			out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>Id</th><th>Class</th><th>Section</th><th>Action</th><tr>"); 
			while(resultSet.next()) {
					
				out.print("<tr><td>" + resultSet.getInt("id") + "</td><td>" + resultSet.getInt("class_number")
				+ "</td><td>"+ resultSet.getString("section")  
				+"</td><td>"
				
				+ "<form action=ClassReport method=post>"
				+ "<button type=submit name=button value="+resultSet.getInt("id")+">View Report</Button>"
				+ "</form>"
//				+ "<form action=ViewSubjectList method=post>"
//				+ "<button type=submit name=button value="+resultSet.getInt("id")+">View Subject</Button>"
//				+ "</form></td></tr>" );
				+ "</td></tr>" );
			}
			out.println("</table>"); 
			out.println("<form action=AddClass.html method=post>");
			out.println("<input type=submit value= Add>");
			out.println("&ensp;");
			out.println("<a href=Home.html>Back</a>");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void abc() {
		System.out.println("I got called here");
	}
}
