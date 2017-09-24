package serPack;

import java.awt.im.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionProvider {
	
   static Properties prop;
	static
	{
		prop=new Properties();
		try
		{
		 	InputStream in=ConnectionProvider.class.getResourceAsStream("/db.properties");
		 	prop.load(in);
		}
		catch(Exception e)
		{
		      	
		}
	}
	public static Connection getConnection() throws Exception
	{
		Class.forName(prop.getProperty("driverclass"));
		Connection con=DriverManager.getConnection
				(
				    prop.getProperty("url"),
		            prop.getProperty("user"),
		            prop.getProperty("password")
		        );
		
		
		return con;
	}

}
