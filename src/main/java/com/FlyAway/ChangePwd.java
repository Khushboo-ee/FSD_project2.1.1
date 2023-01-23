package com.FlyAway;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet ("/change")
public class ChangePwd extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String s3 = req.getParameter("oldpwd");
		String s4 = req.getParameter("newpwd1");
		String s5 = req.getParameter("newpwd2");
		
		
		if(s3!=null&&s3.equalsIgnoreCase("pwd12345")&&s4!=null&&s5!=null&&s5.equalsIgnoreCase(s4)) {
			
			HttpSession session = req.getSession(true);
			session.setAttribute("newpwd1", s4);

			req.getRequestDispatcher("successPwdChange.html").forward(req,resp);
			
			
		}
		else {
			
			req.getRequestDispatcher("PwdError.html").forward(req,resp);
		}

	}

}


