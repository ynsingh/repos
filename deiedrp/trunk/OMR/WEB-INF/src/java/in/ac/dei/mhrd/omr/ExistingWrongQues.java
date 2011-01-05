package in.ac.dei.mhrd.omr;

import java.sql.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import in.ac.dei.mhrd.omr.img.*; 
import java.util.ArrayList;

/**
 * This class defines the function that retrieves the wrong Question and its status from the database
 * @author Anshul
 *
 */

public class ExistingWrongQues {
	public int quesNo;
	public String status;
	
	private ExistingWrongQues(){
		
	}
	
	public ExistingWrongQues(int quesNo, String status){
		this.quesNo=quesNo;
		this.status = status;
	}
	private static Logger log = Logger.getLogger(ExistingWrongQues.class);
	
	/**
	 * This function retrieves the WrongQuestion Number and its status
	 * from the database and 
	 * @param testName
	 * @return
	 */
	public static ArrayList<ExistingWrongQues> getWrongQues(String testName){
		System.out.println("inside existing");
		ArrayList<ExistingWrongQues> quesList =new ArrayList<ExistingWrongQues>();
		//get the testid of the selected test name 
		int testid = SelectTestId.getTestId(testName);
		try{
		Connection con =  Connect.prepareConnection();
        
		PreparedStatement psWrongQues = con.prepareStatement("select WrongQuestionNo, status from wrongquestion where TestId=?");
		psWrongQues.setInt(1, testid);
		ResultSet  rsWrong = psWrongQues.executeQuery();
		while(rsWrong.next()){
		quesList.add(new ExistingWrongQues(rsWrong.getInt(1), rsWrong.getString(2)));
		System.out.println("Q : "+ rsWrong.getInt(1));
		System.out.println("S: "+ rsWrong.getString(2));
			
		}
		con.close();
        }catch(Exception e){
        	
        	log.error("error in retrieving Wrong Ques : " + e);
        }
       return quesList; 

	}

}
