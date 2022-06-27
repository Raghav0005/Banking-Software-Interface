import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class homepage
 */
@WebServlet("/homepage")
public class homepage extends HttpServlet {
	PreparedStatement ps;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Connection con = DBConnection.getConn();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			ServletContext ctx = getServletContext();
			String email = (String) ctx.getAttribute("email");
			HttpSession s = request.getSession(false);
			double current_balance;
			if(s!=null) {
				ps = con.prepareStatement("select * from bank where email=?");
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				out.println("<html><h1><center> Welcome </center></h1>");
				out.println("<html><br><center> Your email is: " + email + " </center>");
				
				while(rs.next()) {
					current_balance = rs.getDouble(5);
					BigDecimal cb = new BigDecimal(current_balance);
					cb = cb.setScale(2, RoundingMode.HALF_UP);
					
					out.println("<html><center> Your current balance is: " + cb + " </center>");
				}
				
				out.println("<html><center> Please choose an option below! </center>");
				
				out.println("<br><center><a href=\"Deposit.html\"> <button> Deposit </button></a>");
				out.println("<br><br><center><a href=\"Withdraw.html\"> <button> Withdraw </button></a>");
				out.println("<br><br><center><a href=\"Transfer.html\"> <button> Transfer </button></a>");
				out.println("<br><br><center><a href=\"index.html\"> <button> Sign Out </button></a>");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
