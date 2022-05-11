

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if ((username.equals("Admin") && username !=null) &&(password.equals("Admin123") && password !=null) ){
			response.sendRedirect("Home.html");
		}else {
			PrintWriter pw=response.getWriter();
			pw.print("<style>\r\n"
					+ "body{\r\n"
					+ "background: olive;\r\n"
					+ "}\r\n"
					+ "div{\r\n"
					+ "text-align: center;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ "</style>\r\n"
					+ "");
			pw.print("<html><body>");
			pw.print("<div>");
			pw.print("<form action=Home.html method=POST >");
			pw.print("Username: <input type=text name=username /><br><br>\r\n"
					+ "		Password: <input type=text name=password /><br><br>");
			pw.print("<html><body>");
			pw.print("<button type=submit> Login </button>");
			pw.print("</form>");
			pw.print("</div>");
			pw.print("</body></html>");
			
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
