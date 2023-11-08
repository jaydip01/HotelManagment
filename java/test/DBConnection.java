package test;

import java.sql.*;

public class DBConnection 
{
	private static Connection con;
	public DBConnection()
	{
	}
	static
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","ORACLE");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static Connection getCon()
	{
		return con;
	}
}
