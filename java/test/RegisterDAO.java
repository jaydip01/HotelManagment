package test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public class RegisterDAO 
{
	int k=0;
	public int register(HttpServletRequest req)
	{
		try
		{
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("insert into HOTELLIVIA_USER values(?,?,?,?,?)");
			ps.setString(1,req.getParameter("uname"));
			ps.setString(2,req.getParameter("pass"));
			ps.setString(3,req.getParameter("fname"));
			ps.setString(4,req.getParameter("lname"));
			ps.setLong(5, Long.parseLong(req.getParameter("phno")));
			k=ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
}
