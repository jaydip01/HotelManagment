package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import test.*;
import javax.servlet.http.HttpServletRequest;

public class LoginDAO
{
	public CustBean login(HttpServletRequest req)
	{
		CustBean cb=null;
		try
		{
			Connection con=DBConnection.getCon();
			PreparedStatement ps1=con.prepareStatement("Select * from HOTELLIVIA_USER where username=? and password=?");
			ps1.setString(1, req.getParameter("uname"));
			ps1.setString(2, req.getParameter("pass"));
//			System.out.println(req.getParameter("uname")+req.getParameter("pass"));
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
			{
				
				cb=new CustBean();
				cb.setUserName(rs.getString(1));
				cb.setPassword(rs.getString(2));
				cb.setfName(rs.getString(3));
				cb.setlName(rs.getString(4));
				cb.setPhNo(rs.getLong(5));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cb;
	}
}
