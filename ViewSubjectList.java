

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
 * Servlet implementation class ViewSubjectList
 */
@WebServlet("/ViewSubjectList")
public class ViewSubjectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSubjectList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			String id = request.getParameter("button");
			PrintWriter out = response.getWriter();
		out.print("<html> <body>");

		// TODO Auto-generated method stub
		// Initialize the database
		Connection con = DatabaseConnection.initializeDatabase();

		// Create a SQL query to insert data into demo table
		// demo table consists of two columns, so two '?' is used
		Statement statement = con.createStatement();
		String query = "SELECT * FROM subject where class_id="+id;
		
	//Step-4  Execute Query and get the results
		ResultSet resultSet = 	statement.executeQuery(query);	
		
		if(!resultSet.isBeforeFirst()) {
			out.println("No subject for this class please add some subject."); 
		}
		else {
		
		
		out.println("<table border=1 width=50% height=50%>");  
        out.println("<tr><th>Id</th><th>Subject</th><tr>"); 
		while(resultSet.next()) {
			out.print("<tr><td>" + resultSet.getString("id") + "</td><td>" + resultSet.getString("subject")  +"</td></tr>" );
		}
		
		out.println("</table>"); 
		}
		out.println("<form action=ViewClassServlet method=post>");
		out.println("<input type=submit value= back>");
		out.println("<a href=Login.html>logout</a>");
			
		out.println("</form>");
		out.println("</html></body>"); 
		//close objects
		resultSet.close();
		statement.close();
		con.close();

		// Get a writer pointer 
		// to display the successful result
//		PrintWriter out = response.getWriter();
//		out.println("<html><body><b>Successfully Inserted"
//				+ "</b></body></html>");
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

}
