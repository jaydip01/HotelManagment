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
@WebServlet("/food")
public class FoodServlet extends HttpServlet {
    @SuppressWarnings("unused")
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        HttpSession hs = req.getSession(false);
        if (hs == null) {
            pw.print("<h1>Session Expired....</h1><br>");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, res);
        } else {
            ArrayList<String> al = new ArrayList<String>();
            CustBean cb = (CustBean) hs.getAttribute("cb");
            Enumeration<String> e = req.getParameterNames();
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                System.out.println("Name:"+name);
                if (req.getParameter(name) != "" && Integer.parseInt(req.getParameter(name)) > 0) {
                    al.add(name);
                }
            }
            hs.setAttribute("al", al);
            RequestDispatcher rd = req.getRequestDispatcher("bill");
            rd.include(req, res);
        }

    }
}
