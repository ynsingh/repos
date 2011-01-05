package in.ac.dei.mhrd.omr;
import in.ac.dei.mhrd.omr.img.*;
import java.sql.*;

/**
 * This class retrieves section detail from the database
 * @author Ashutosh 
 *
 */
public class SectionResultSet {
	
		public ResultSet returnRs(int testId)
		{
			Connection con=null;
			ResultSet rs=null;
			PreparedStatement ps=null;
				try{
					con=Connect.prepareConnection();
					ps=con.prepareStatement("select * from testsectiondetail where TestId=?");
					ps.setInt(1, testId);
					rs=ps.executeQuery();
					//con.close();
						return rs;
				}catch(Exception e)
				{
					System.out.println("SectionResultSet"+e.getMessage());
				}
			return null;
		}
}
