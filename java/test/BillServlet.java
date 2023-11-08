package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/bill")
public class BillServlet extends HttpServlet {
	@SuppressWarnings("unchecked")
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
			ArrayList<String> al = (ArrayList<String>) hs.getAttribute("al");
			CustBean cb = (CustBean) hs.getAttribute("cb");
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.print("<head>");
			pw.print("<meta charset='UTF-8'>");
			pw.print("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
			pw.print("<title>Hotel Livya - Bill</title>");
			pw.print("<style>");
			pw.print("body {");
			pw.print("    font-family: Arial, sans-serif;");
			pw.print("    padding: 20px;");
			pw.print("}");

			pw.print("h1 {");
			pw.print("    text-align: center;");
			pw.print("    color: #333;");
			pw.print("    margin-bottom: 20px;");
			pw.print("}");

			pw.print("h2 {");
			pw.print("    text-align: center;");
			pw.print("    margin-bottom: 10px;");
			pw.print("}");

			pw.print("table {");
			pw.print("    width: 100%;");
			pw.print("    margin-bottom: 20px;");
			pw.print("    border-collapse: collapse;");
			pw.print("}");

			pw.print("th, td {");
			pw.print("    padding: 10px;");
			pw.print("    border-bottom: 1px solid #ccc;");
			pw.print("}");

			pw.print("th {");
			pw.print("    background-color: #99ccff;");
			pw.print("    font-weight: bold;");
			pw.print("}");

			pw.print(".container {");
			pw.print("    background-color: #f2f2f2;");
			pw.print("    padding: 50px;");
			pw.print("    border-radius: 10px;");
			pw.print("    text-align: center;");
			pw.print("}");

			pw.print(".total {");
			pw.print("    font-weight: bold;");
			pw.print("    margin-top: 20px;");
			pw.print("}");

			pw.print(".Home-link {");
			pw.print("    display: inline-block;");
			pw.print("    padding: 10px 20px;");
			pw.print("    margin-top: 20px;");
			pw.print("    background-color: #cc66cc;");
			pw.print("    color: #fff;");
			pw.print("    text-decoration: none;");
			pw.print("    border-radius: 5px;");
			pw.print("    font-weight: bold;");
			pw.print("    transition: background-color 0.3s ease;");
			pw.print("}");

			pw.print(".exit-link:hover {");
			pw.print("    background-color: #993399;");
			pw.print("}");
			pw.print("</style>");
			pw.print("</head>");
			pw.print("<body>");
			pw.print("<h1>Hotel Taj</h1>");
			pw.print("<h2>Welcome, " + cb.getfName() + "</h2>");
			pw.print("<div class='container'>");
			pw.print("<table>");
			pw.print("<tr><th>Sl</th><th>Item</th><th>Price</th><th>Qty</th><th>Bill</th></tr>");
			int i = 1, tbill = 0;
			for (String k : al) {
				int price = 0, quantity = 0, bill = 0;
				String item = "";

				switch (k) {
				case "lolly":
					item = "Chicken Lollipop";
					price = 160;
					quantity = Integer.parseInt(req.getParameter(k));
					bill = price * quantity;
					break;
				case "crispy":
					item = "Crispy Chicken";
					price = 180;
					quantity = Integer.parseInt(req.getParameter(k));
					bill = price * quantity;
					break;
				case "manchu":
					item = "Chicken Manchurian";
					price = 140;
					quantity = Integer.parseInt(req.getParameter(k));
					bill = price * quantity;
					break;
				case "cButter":
					item = "Chicken Butter Masala";
					price = 160;
					quantity = Integer.parseInt(req.getParameter(k));
					bill = price * quantity;
					break;
				case "cBiryani":
					item = "Chicken Biryani";
					price = 190;
					quantity = Integer.parseInt(req.getParameter(k));
					bill = price * quantity;
					break;
				case "mBiryani":
					item = "Mutton Biryani";
					price = 210;
					quantity = Integer.parseInt(req.getParameter(k));
					bill = price * quantity;
					break;
				case "ice":
					item = "Fried Ice Cream";
					price = 60;
					quantity = Integer.parseInt(req.getParameter(k));
					bill = price * quantity;
					break;
				case "gulab":
					item = "Hot Gulab Jamun";
					price = 50;
					quantity = Integer.parseInt(req.getParameter(k));
					bill = price * quantity;
					break;
				case "double":
					item = "Double Ka Meeta";
					price = 70;
					quantity = Integer.parseInt(req.getParameter(k));
					bill = price * quantity;
					break;
				}

				tbill += bill;

				pw.print("<tr><td>" + i + "</td><td>" + item + "</td><td>" + price + "</td><td>" + quantity
						+ "</td><td>" + bill + "</td></tr>");
				i++;
			}
			pw.print("</table>");
			pw.print("<hr>");
			pw.print("<p class='total'><strong>Total Food: " + tbill + "</strong></p>");
			HttpSession session = req.getSession(false);
			session.invalidate();
			
			ServletContext sc = this.getServletContext();
			long rb = ((Long) sc.getAttribute("rbill"));
			if (rb != 0) {
				pw.print("<p class='total'><strong>Rooms Bill: " + rb + "</strong></p>");
				pw.print("<p class='total'><strong>Total Amount: " + (tbill + rb) + "</strong></p>");
				rb = 0;
			}
			pw.print("<a href='front.html' class='Home-link'>HOME</a>");
			sc.removeAttribute("rbill");
			pw.print("</div>");
			pw.print("</body>");
			pw.print("</html>");
		}
	}
}