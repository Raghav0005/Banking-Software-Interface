import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class withdraw
 */
@WebServlet("/withdraw")
public class withdraw extends HttpServlet {
	PreparedStatement ps;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		double amount = Double.parseDouble(request.getParameter("withdraw"));
		BigDecimal amt = new BigDecimal(amount);
		BigDecimal zero = BigDecimal.ZERO;
		int testpin = Integer.parseInt(request.getParameter("pin"));
		ServletContext ctx = getServletContext();
		int rpin = (int) ctx.getAttribute("pin");
		String email = (String) ctx.getAttribute("email");
		
		try {
			Connection con = DBConnection.getConn();
			
			ps = con.prepareStatement("select * from bank where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				BigDecimal cb = new BigDecimal(rs.getDouble(5));
				cb = cb.setScale(2, RoundingMode.HALF_UP);
				if (rpin==testpin && cb.subtract(amt).compareTo(zero) >=0) {
					cb = cb.subtract(amt);
					ps = con.prepareStatement("update bank set balance=? where email=?");
					ps.setBigDecimal(1, cb);
					ps.setString(2, email);
					ps.executeUpdate();
					out.println("<html><body><center> Amount Withdrawn From Your Account");
					RequestDispatcher rd = request.getRequestDispatcher("homepage");
					rd.include(request, response);
					
				}
				else {
					out.println("<html><body><center> INVALID PIN or INVALID Amount entered");
					RequestDispatcher rd = request.getRequestDispatcher("Deposit.html");
					rd.include(request, response);
				}
			}
			
			
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
