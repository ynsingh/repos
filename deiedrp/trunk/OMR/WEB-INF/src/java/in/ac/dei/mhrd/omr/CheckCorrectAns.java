package in.ac.dei.mhrd.omr;
import java.sql.*;
import in.ac.dei.mhrd.omr.img.Connect;
public class CheckCorrectAns {
		private int testId;
		public CheckCorrectAns(int testId) {
			this.testId=testId;
			}
	public String returnCorrectRs()
	{
	PreparedStatement ps=null;
	Connection con=null;
	ResultSet rsCheckCorrect=null;
	String qry;
		try{
		con=Connect.prepareConnection();
		qry="select testid from correctans where TestId=?";
		ps=con.prepareStatement(qry);
		ps.setInt(1,testId );
		rsCheckCorrect=ps.executeQuery();
		rsCheckCorrect.next();
		
		if(rsCheckCorrect.next())
			{  con.close();
				return("Exist"); 
			}
			else return("notExist");
		}catch (Exception e) {
			System.out.println("checkCorrectAns Exception: "+e.getMessage());
		}
		return null;
	}
}
