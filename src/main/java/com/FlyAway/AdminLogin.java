package com.FlyAway;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class AdminLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String s1 = req.getParameter("username");
		String s2 = req.getParameter("password");

		

		if (s1 != null && s1.equalsIgnoreCase("admin123") && s2 != null && s2.equalsIgnoreCase("pwd12345")) {

			HttpSession session = req.getSession(true);
			session.setAttribute("password", s2);

			req.getRequestDispatcher("adminpage1.html").forward(req, resp);

		} else {

			req.getRequestDispatcher("LoginFail.html").forward(req, resp);
		}
	}

}
