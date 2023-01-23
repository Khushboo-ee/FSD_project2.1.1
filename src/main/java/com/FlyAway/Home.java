package com.FlyAway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet ("/home")
public class Home extends HttpServlet {
	//jdbc:mysql://127.0.0.1:3305/?user=root
	private String url = "jdbc:mysql://127.0.0.1:3305/p2_project2?user=root";
	private String user = "root";
	private String pwd = "root";
	Connection con;
	PreparedStatement pstmt;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		resp.setContentType("text/html");

		PrintWriter writer = resp.getWriter();

		String s2 = req.getParameter("date");	
			//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
			//Date t_date = null;
//			try {
//				t_date = (Date) formatter.parse(s2);
//			} catch (ParseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		String source = req.getParameter("src");
		String destination = req.getParameter("dest");
		String s1 = req.getParameter("pers");
			int travellers = Integer.parseInt(s1);
		
		int x;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("hi1");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println(con);
			String sql = "insert into client values (?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, source);
			pstmt.setString(2, destination);
			pstmt.setString(3,s2);
			pstmt.setInt(4, travellers);
			
			x = pstmt.executeUpdate();

			if (x > 0)
			{
				writer.println("<div class='success'><h3>List of flights as per your request-</h3></div>");
				req.getRequestDispatcher("retrieveFlightList.java").forward(req,resp);
				
			} 
			else
			{
				writer.println("<div class='error'><h3>Oops!! no flights available as per your request</h3></div>");
				req.getRequestDispatcher("FlyAwayhome.html").forward(req,resp);
			}

			pstmt.close();
			con.close();
		}

		catch (SQLException e)
		{

			e.printStackTrace();
		}

		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
