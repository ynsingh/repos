package com.erp.nfes;

import java.sql.*;
import java.util.*;


public class ConnectDB {
	   Connection conn=null;
	   public Connection getMysqlConnection(){
			try{
				String propFileName = "db.properties";
				Properties properties = GetPropertiesFile.GetPropertiesFileFromCONF(propFileName);
				String dbname = properties.getProperty("dbname");
			    String username = properties.getProperty("username");
			    String password = properties.getProperty("password");
				Class.forName("org.gjt.mm.mysql.Driver");
				conn=DriverManager.getConnection("jdbc:mysql:"+dbname+"?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes",username,password);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return conn;
		 }
}