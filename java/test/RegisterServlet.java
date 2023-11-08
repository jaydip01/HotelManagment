package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@SuppressWarnings("serial")
@WebServlet("/reg")
public class RegisterServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		int k=new RegisterDAO().register(req);
		if(k>0)
		{
			pw.print("<h3>Registered successfull....</h3>");
		}
		else
		{
			pw.print("<h3>Registered failed....</h3>");
		}
		RequestDispatcher rd=req.getRequestDispatcher("login.html");
		rd.include(req, res);
	}
}
