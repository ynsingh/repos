package in.ac.dei.edrp.pms.dataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * It is used for holding datasource.
 * @author Anil Kumar Tiwari <b>mailto:</b>aniltiwari08@gmail.com
 */

public class MyDataSource {
	//private static Connection con=null;
	static DataSource ds=null;
	/**
	 * The method getConnection is used for established the database Connection.
	 * @return MySql DataBase connection
	 * */
	public static synchronized void setDataSource(DataSource ds1)
	{
		ds=ds1;
	}
//	public static DataSource getDataSource()
//	{
//		return ds;
//	}
//	
	public static synchronized Connection getConnection() 
	  throws SQLException
	  {
	    return ds.getConnection();
	  }

	  /**
	   * Must close the database connection to return it to the pool.
	   */
	  public static synchronized void freeConnection(Connection connection)
	  {
	    try
	    {
	    	if(connection!=null)
			{
	    		connection.close();
	    		//System.out.println("in ds Connection has been closed="+connection); 
	    
			}
	    }
	    catch (Exception e)
	    {
	      //System.err.println("DBBroker: Threw an exception closing a database connection");
	      //e.printStackTrace();
	    }
	  }

	
}
