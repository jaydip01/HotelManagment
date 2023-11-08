package test;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

@SuppressWarnings("serial")
@WebServlet("/logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session = request.getSession(false);
         if (session != null) {
             session.invalidate(); // Invalidate the current session
         }
    	
        // Remove the rbill attribute from ServletContext
        ServletContext context = getServletContext();
        context.removeAttribute("rbill");
        
        response.sendRedirect("login.html"); // Redirect to the login page
    }
}
