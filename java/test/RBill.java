package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/billR")
public class RBill extends HttpServlet {
    @SuppressWarnings("unchecked")
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        HttpSession hs = req.getSession(false);
        if (hs == null) {
            pw.print("<h1>Session Expired...</h1>");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, res);
        } else {
            CustBean cb = (CustBean) hs.getAttribute("cb");
            ArrayList<String> al2 = (ArrayList<String>) hs.getAttribute("al2");

            pw.print("<html>");
            pw.print("<head>");
            pw.print("<title>Hotel Livya</title>");
            pw.print("<style>");
            pw.print("h1 { text-align: center; }");
            pw.print(".container { background-color: #f2f2f2; width: 900px; padding: 50px; margin: auto; }");
            pw.print(".table-container { margin-top: 20px; }");
            pw.print(".table-container table { width: 100%; text-align: center; border-collapse: collapse; }");
            pw.print(".table-container th, .table-container td { padding: 10px; border: 1px solid #ccc; }");
            pw.print(".table-container th { background-color: #99ccff; }");
            pw.print(".button{ text-decoration: none; color: #fff;"
            		+ "background-color: #cc66cc; padding: 10px 20px; border-radius: 5px;"
            		+ " font-weight: bold; font-size: 18px; "
            		+ "transition: background-color 0.3s ease;}");
            pw.print("</style>");
            pw.print("</head>");
            pw.print("<body>");

            pw.print("<h1>Hotel Livya</h1>");
            pw.print("<div class='container'>");
            pw.print("<p>Name: " + cb.getfName() + "</p>");
            pw.print("<p>PhoneNumber: " + cb.getPhNo() + "</p>");
            pw.print("<hr>");

            pw.print("<div class='table-container'>");
            pw.print("<table>");
            pw.print("<tr><th>Sl</th><th>RoomType</th><th>Price</th><th>StartDate</th><th>EndDate</th><th>Days</th><th>Bill</th></tr>");

            int i = 0;
            long tBill = 0;
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (String k : al2) { 
            	 String roomType = "";
                int price = 0;

                if (k.equals("sweet1")) {
                 roomType = "sweet1";
                    price = 699;
                } else if (k.equals("acSweet1")) {
                	roomType = "acSweet1";
                	price = 1299;
                }
                else if (k.equals("single1")) {
                	roomType = "single1";
                	price = 1299;
                }
                else if (k.equals("acSingle1")) {
                	roomType = "acSingle1";
                	price = 1999;
                }
                else if (k.equals("double1")) {
                	roomType = "double1";
                	price = 1999;
                }
                else if (k.equals("acDouble1")) {
                	roomType = "acDouble1";
                	price = 2499;
                }
                
                String startDate = req.getParameter(k);
                String endDate = req.getParameter(k.replace("1", "2"));
                System.out.println("Date="+startDate+"  "+endDate);
                try {
                    Date dt1 = myFormat.parse(startDate);
                    Date dt2 = myFormat.parse(endDate);
                    long diff = dt2.getTime() - dt1.getTime();
                    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    if (days < 0) days = 0;

                    long bill = price * days;
                    tBill += bill;

                    if (days > 0) {
                        i++;
                        pw.print("<tr>");
                        pw.print("<td>" + i + "</td>");
                        pw.print("<td>" + roomType + "</td>");
                        pw.print("<td>" + price + "</td>");
                        pw.print("<td>" + startDate + "</td>");
                        pw.print("<td>" + endDate + "</td>");
                        pw.print("<td>" + days + "</td>");
                        pw.print("<td>" + bill + "</td>");
                        pw.print("</tr>");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    pw.print("<h1>Select Correctly...</h1>");
                    RequestDispatcher rd = req.getRequestDispatcher("rooms.html");
                    rd.include(req, res);
                }
            }

            pw.print("</table>");
            pw.print("</div>");

            pw.print("<hr>");
            pw.print("<p>Total = " + tBill + "</p>");

            long rb = tBill;
            ServletContext sc = this.getServletContext();
            sc.setAttribute("rbill", rb);
            pw.print("<div style='text-align: right; margin-top: 20px;'>");
            pw.print("<a href='food.html'class='button'>Order Food?</a>");
            pw.print("</div>");

            pw.print("<div style='text-align: center; margin-top: 20px;'>");
            pw.print("<form action='logout' method='post'>"
					+ "<button class='button'>Logout</button></form");
			
            pw.print("</div>");
            pw.print("</div>");
            pw.print("</div>");
            pw.print("</body>");
            pw.print("</html>");
        }
    }
}
