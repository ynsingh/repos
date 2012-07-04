package edu.avv.util;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

public class ConnectDB {
	   private static Connection conn=null;
	   public static Connection getMysqlConnection(){
			try{
				GetConsole.info("Connecting DB");
				/*
				 * for development environment please use the following code for db connection
				 */
				/*
				String propFileName = "gms-ws.properties";
				Properties properties = GetPropertiesFile.GetPropertiesFileFromCONF(propFileName);
				String dbname = properties.getProperty("dbname");
			    String username = properties.getProperty("username");
			    String password = properties.getProperty("password");
				Class.forName("org.gjt.mm.mysql.Driver");
				conn=DriverManager.getConnection("jdbc:mysql:"+dbname+"?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes",username,password);
				*/
				/*
				 * for production db connection
				 */
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/MySqlGrailsDS");
				conn = ds.getConnection();
			
			}catch(Exception e){
				e.printStackTrace();
			}
			return conn;
		 }
}
