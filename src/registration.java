
import java.util.*;
import java.io.IOException;
import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PreparedStatement ps;   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Random rand = new Random();
		int ubound = 9999;
		int PIN = rand.nextInt(ubound);
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		
		try {
			Connection con = DBConnection.getConn();
			ps = con.prepareStatement("insert into bank values(?,?,?,?,?)");
			ps.setString(1,fname);
			ps.setString(2, lname);
			ps.setInt(3, age);
			ps.setString(4, email);
			ps.setInt(5, 0);
			ps.executeUpdate();
			
			ps = con.prepareStatement("insert into bank_check values(?,?)");
			ps.setString(1, email);
			int id = rand.nextInt(1000);
			ps.setInt(2, id);
			ps.executeUpdate();
			
			out.println("<html><body><center> Registration successful. Your Pin is: " + id + ". Use this PIN when logging in.");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
		}
		catch (ClassNotFoundException | SQLException e) {
			
		}
		
	}	

}
