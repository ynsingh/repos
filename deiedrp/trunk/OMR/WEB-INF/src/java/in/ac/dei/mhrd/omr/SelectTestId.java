package in.ac.dei.mhrd.omr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import in.ac.dei.mhrd.omr.img.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;


/**
 * Creation date: 10-27-2010
 * @author Anshul Agarwal
 * This class retrieves the testid from the database according to the testname 
 * 
 */
public class SelectTestId {
	
	/**
	 * This function accepts the testname and returns its corresponding system generated testid from the testheader table 
	 * @param TestName
	 * @return
	 */
	private static Logger log = Logger.getLogger(UpdateTestSetupAction.class);

	public static int getTestId(String TestName){
		int testid=0;
		 try {
             Connection con = Connect.prepareConnection();
             ResultSet rsTestid=null;          
             PreparedStatement psTestid = con.prepareStatement(
                     "select TestId from testheader where Test_name=?");
             psTestid.setString(1, TestName);
            rsTestid= psTestid.executeQuery();
            rsTestid.next();
            testid=rsTestid.getInt(1);
            if(testid!=0){
            	log.info("Test Id successfully retrieved");
            }
            con.close();
            }
             catch(Exception e){
                log.error("Error while retrieving testid");             }
		return testid;

	}
	
}
