package in.ac.dei.mhrd.omr.img;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 
 * @author Anshul Agarwal
 * This class insert the path of the sheet and cause of error into the database 
 */

public class LogEntry {

	/**
	 * 
	 * @param filename
	 * @param description
	 */
	
	public static void insert_Log(int testid, String filename, String errCode, String description ){
		

		try{
			
			// establish connection with the database
			System.out.println("file name in log"+ filename);
			Connection con = Connect.prepareConnection();
			con.setAutoCommit(false);
			System.out.println("b4 log");
			System.out.println("Testid : " + testid);
			System.out.println("filenAME: " + filename);
			System.out.println("dec : " + description);
			 
	     PreparedStatement ps= con.prepareStatement("insert into log(Testid, FileName, ErrCode, description) values (?,?,?,?)");
	     
	     ps.setInt(1, testid);
	     ps.setString(2, filename);
	     ps.setString(3, errCode);
	     ps.setString(4, description); 
	    
	     ps.executeUpdate();
	     con.commit();
	  // close the connection
		con.close();
		
		}
		catch(Exception e){
			System.out.println("error while insert in log:" + e);
		}

	
		
		
	}
	
	
}
