package in.ac.dei.mhrd.omr.img;
import java.sql.*;
/**
 * 
 * @author Anshul Agarwal
 *This class load drivers and establish connection to the database
 */

public class Connect {

	/**
	 * creation Date: 05-18-2010
	 * @return
	 * @throws Exception
	 */
	public static Connection prepareConnection() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver"); // load driver
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/omr?user=root&password=mysql");
		//con.setAutoCommit(false);
		return con;
	}
	
}
