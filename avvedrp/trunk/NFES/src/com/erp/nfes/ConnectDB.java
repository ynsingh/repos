package com.erp.nfes;

//import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public   class ConnectDB {
	
	Connection conn=null;
	
		public  Connection getMysqlConnection(){

			

			try{
				Properties properties = new Properties();
				properties.load(new FileInputStream("../conf/db.properties"));
			    String dbname = properties.getProperty("dbname");
			    String username = properties.getProperty("username");
			    String password = properties.getProperty("password");
				Class.forName("org.gjt.mm.mysql.Driver");
				conn=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password);
				
			}catch(Exception e){

				e.printStackTrace();}
			return conn;
		 }
		
	}


