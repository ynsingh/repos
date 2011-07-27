package in.ac.dei.edrp.pms.dataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Used for connection pooling
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 *
 */

public class MyDataSource {
	
	/**
	 * The method getConnection is used for established the database Connection.
	 * @return MySql DataBase connection
	 * */

	public static synchronized Connection getConnection() 
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
	    String DATASOURCE_CONTEXT = "java:comp/env/jdbc/mydb";
	    
	    Connection result = null;
	    try {
	      Context initialContext = new InitialContext();
	      if ( initialContext == null){
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
	    catch ( NamingException ex ) {
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
