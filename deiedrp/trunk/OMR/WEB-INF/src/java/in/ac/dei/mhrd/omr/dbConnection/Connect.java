/*
 * @(#) Connect.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */

package in.ac.dei.mhrd.omr.dbConnection;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *This class load drivers and establish connection to the database
 *Creation Date: 05-18-2010
 *@author Anshul Agarwal
 *@version 1.0
 */

public class Connect {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	
	/*public static Connection prepareConnection() throws Exception
	{
		Locale obj = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);

		Class.forName("com.mysql.jdbc.Driver"); // load driver
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+message.getString("database")+"?user="+ message.getString("username")+"&password="+message.getString("password"));
		//con.setAutoCommit(false);
		return con;
	}
	*/
	
	/**
	 * The method getConnection is used for establishing  database Connection.
	 * @return MySql DataBase connection
	 * 
	 */

	public static synchronized Connection prepareConnection() 
	  throws SQLException
	  {
	    return getJNDIConnection();
	  }

	  /**
	   * Must close the database connection to return it to the pool.
	   */
	  public static synchronized void freeConnection(Connection connection){
	    try {
	    	if(connection!=null) {
	    		connection.close();
	    	}
	    }
	    catch (Exception e){
	    	log("Cannot close connection: " + e);
	    }
	  }

	  /** Uses JNDI and Datasource (preferred style).   */
	public  static Connection getJNDIConnection(){
	    String DATASOURCE_CONTEXT = "java:comp/env/jdbc/omr";
	    
	    Connection result = null;
	    try {
	      Context initialContext = new InitialContext();
	      if (initialContext == null){
	        log("JNDI problem. Cannot get InitialContext.");
	      }
	      DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
	      if (datasource != null) {
	        result = datasource.getConnection();
	      }
	      else {
	        log("Failed to lookup datasource.");
	      }
	    }
	    catch (NamingException ex ) {
	      log("Cannot get connection: " + ex);
	    }
	    catch(SQLException ex){
	      log("Cannot get connection: " + ex);
	    }
	    return result;
	  }
	  private static void log(Object aObject){
		    System.out.println(aObject);
		  }
		  

}
