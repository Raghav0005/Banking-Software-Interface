import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	PreparedStatement ps;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		int pin = Integer.parseInt(request.getParameter("pin"));
		
		try {
			Connection con = DBConnection.getConn();
			ps = con.prepareStatement("select * from bank_check where email=? and pin=?");
			ps.setString(1, email);
			ps.setInt(2, pin);
			ResultSet rs  = ps.executeQuery();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			if(rs.next()) {
				HttpSession hs = request.getSession();
				hs.setAttribute("email", email);
				hs.setAttribute("pin", pin);
				
				ServletContext ctx = getServletContext();
				ctx.setAttribute("email", email);
				ctx.setAttribute("pin", pin);
				
				response.sendRedirect("homepage");
				
			}
			else {
				out.println("<html><body> <p style=\"color:red\"> USER INVALID! Choose one of the options below. </p>");
				out.println("<br><a href=\"Registeration.html\"> Register an Account </a>");
				out.println("<br><a href=\"Login.html\"> Login Again </a>");
			}
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}