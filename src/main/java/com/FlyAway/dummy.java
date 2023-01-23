package com.FlyAway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet ("/dummy")
public class dummy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jdbc:mysql://127.0.0.1:3305/FlyAway";
		String user = "root";
		 String pwd = "root";
		Connection con;
		Statement stmt;
		PreparedStatement pstmt;
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String gender = req.getParameter("sex");
		String s1 = req.getParameter("age");
			int age = Integer.parseInt(s1);
		String email = req.getParameter("mail");
		String s2 = req.getParameter("date");
		int date = Integer.parseInt(s2);
		String passenger = req.getParameter("pers");
		String source = req.getParameter("src");
		String destination = req.getParameter("dest");
		 out.print ("<link rel=\"stylesheet\" href=\"/FlyAway1/FlyAway.css\">");
		
		 out.println("<table>");
		out.println("<tr><td><label class='opt'>Name of Passenger: "+name+"</label></td></tr><br>");
		out.println("<tr><td><label class='opt'>Gender: "+gender+"</label></td></tr><br>");
		out.println("<tr><td><label class='opt'>Age: "+age+"</label></td></tr><br>");
		out.println("<tr><td><label class='opt'>Email: "+email+"</label></td></tr><br>");
		out.println("<tr><td><label class='opt'>Travel date: "+date+"</label></td></tr><br>");
		out.println("<tr><td><label class='opt'>No. of travellers: "+passenger+"</label></td></tr><br>");
		out.println("</table>");
		
			
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println(con);
			System.out.println("connectn established");
			
			
			
			//full join
			String sql;
			sql = "SELECT * FROM client FULL JOIN flight using (source,destination,travel_d)";
			
			pstmt = con.prepareStatement(sql);
			 ResultSet res2 = pstmt.executeQuery();
				ResultSetMetaData rsmd2 = res2.getMetaData();
			
			 out.print ("<link rel=\"stylesheet\" href=\"/FlyAway1/FlyAway.css\">");
				
			 out.print ("<table width=50% border=1>");
	            out.print ("<caption>Payment details:</caption>");
	            
	       
	            /* Printing result */
	            while (res2.next ()) {
	            	out.print (" <tr><td><label class='opt'>Source: "+ res2.getString(1)+ "</label></td></tr>"+
	            			" <tr><td><label class='opt'>Destination: "+ res2.getString(2)+"</label></td></tr>"+
	            			" <tr><td><label class='opt'>Date of traveling : "+ res2.getString(3)+"</label></td></tr>"+
	            			" <tr><td><label class='opt'>No. of travellers: "+ res2.getInt(4)+"</label></td></tr>"+
	            			" <tr><td><label class='opt'>Per person cost of ticket: "+ res2.getInt(7)+"</label></td></tr>"+
	      " <tr><td><label class='opt'>Total cost for the ticket: "+ res2.getInt(7)*res2.getInt(4)+"</label></td></tr>"+
	      " <tr><td><label class='opt'>GST: "+ res2.getInt(7)*res2.getInt(4)*0.12+"</label></td></tr>"+
	      " <tr><td><label class='opt'>Total payable amount for the ticket: "+ res2.getInt(7)*res2.getInt(4)*1.12+"</label></td></tr>");
	        out.print ("</table>");
	 	    out.print ("<button type='button'><a href='paid.html'>Click to pay</a></button><input type='reset' value='reload'>");    	
	 	   	
	         }
	           
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
}


