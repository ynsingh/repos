package dao;

import java.sql.Connection;
import java.sql.DriverManager;


import java.sql.SQLException;


public class test {

	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
Class.forName("com.mysql.jdbc.Driver");
Connection con= DriverManager.getConnection("jdbc:mysql://192.168.1.8:3306/jdbc_example","root","1234");
System.out.println(con);
	}}