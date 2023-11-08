package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/rooms")
public class RoomServlet extends HttpServlet
{
	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		HttpSession hs=req.getSession(false);
		
		
		if(hs==null)
		{
			pw.print("<br>Sesion Expired....");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
		else
		{
			CustBean cb=(CustBean)hs.getAttribute("cb");
			ArrayList<String> al2=new ArrayList<String>();
			Enumeration<String> e=req.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				if((req.getParameter(name)!=""))
				{
					al2.add(name);
					System.out.println("Al2"+al2);
				}
				
			}
			hs.setAttribute("al2", al2);
			RequestDispatcher rd=req.getRequestDispatcher("billR");
			rd.include(req, res);
		}
	}
}
