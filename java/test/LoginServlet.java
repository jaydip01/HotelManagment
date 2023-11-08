package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		CustBean cb=new LoginDAO().login(req);
		
		if(cb==null)
		{
			pw.print("Invalid user/password");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
		else
		{
			HttpSession hs=req.getSession();
			hs.setAttribute("cb",cb);
			pw.print("<html><head><meta charset='UTF-8'><title>Welcome</title><style>body{margin:0;padding:0;"
					+ "background-image:url('https://images.unsplash.com/photo-1445019980597-93fa8acb246c?"
					+ "ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=874&q=80');"
					+ "background-size:cover;background-position:center;font-family:Arial,sans-serif;} "
					+ ".container{display:flex;flex-direction:column;justify-content:center;align-items:center;min-height:100vh;} "
					+ "h1{text-align:center;margin-bottom:30px;} .button-container{display:flex;justify-content:center;} "
					+ ".button{background-color:#4CAF50;color:white;padding:10px 20px;border:none;border-radius:4px;cursor:pointer;margin:10px;}</style>"
					+ "</head><body><div class='container'><h1>Welcome " + cb.getfName() + "</h1>"
					+ "<div class='button-container'><a href='food.html'><button class='button'>Foods</button></a>"
					+ "<a href='rooms.html'><button class='button'>Rooms</button></a></div></div></body></html>");

}
	}
}



//border: none;

