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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class retrieveFlightList extends HttpServlet{
	String url = "jdbc:mysql://127.0.0.1:3305/FlyAway";
	String user = "root";
	 String pwd = "root";
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String src1 = null;
		String dest1 = null;
		int t_dt;
		int trav = 0;

		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		int x;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println(con);
			System.out.println("connectn established");
			
			
			//first operation
			String sql1 = "select * from client";
			PreparedStatement pstmt = con.prepareStatement(sql1);
			ResultSet res1 = pstmt.executeQuery();
			
			while(res1.next()==true) {
				 src1 = res1.getString(1);
				dest1 = res1.getString(2);
				t_dt = res1.getInt(3);
				trav = res1.getInt(4);
				System.out.println("operation1 completed");
			}
			
			 out.print ("<link rel=\"stylesheet\" href=\"/FlyAway1/FlyAway.css\">");
			
			 out.print ("<table width=50% border=1>");
	            out.print ("<caption>Available Flights:</caption>");
			
			// second operation
			String sql2 = "select * from flight where source=? and destination=? and date=?";
			 pstmt = con.prepareStatement(sql2);
			pstmt.setString(1,src1);
			pstmt.setString(2,dest1);
			pstmt.setInt(3,trav);
			
			ResultSet res2 = pstmt.executeQuery();
			ResultSetMetaData rsmd1 = res2.getMetaData();

			int total = rsmd1.getColumnCount ();
			
            out.print ("<tr>");
            for (int i = 1; i <= total; i++)
         {
             out.print ("<th>" + rsmd1.getColumnName (i) + "</th>");
         }
            out.print ("</tr>");
            /* Printing result */
            while (res2.next ()) {
            	for (int i=1; i<=total;i++){
            		 out.print (" <tr><td><input type='radio' name='detail' onclick='/payment.java' value= "+i+">" 
            	+ res2.getInt(1)+"</td><td>"+ res2.getString(2)+"</td><td>"+ res2.getString(3)+"</td><td>"+
                 res2.getString(4)+"</td><td>"+res2.getInt(5)+"</td><td>"+res2.getInt(6)+"</td><td></tr>");
            	}
         }
            out.print ("</table>");
        }
        catch (Exception e2)
        {
            e2.printStackTrace ();
        }
     
	}
}
