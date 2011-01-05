package in.ac.dei.mhrd.omr.img;

import ij.*;
import in.ac.dei.mhrd.omr.ProcessSheetAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Anshul Agarwal
 * this class insert correct answer s into database
 */
public class CorrectAns {
    /**
     * Inserts correct answers into the database
     * @param correctAns : array contains correct answers
     * @param sec " section number
     */
	
	private static Logger log = Logger.getLogger(CorrectAns.class);
    public static int insertCorrectAns(byte[] correctAns, int testid) {
        int j=0;
        int sec=0;
        int qno=0;
        int totalSec=0;
        int totalQues=0;
        int ques=0;
        int[] noOfCorrectAns=null;
    	try {
            //establish connection with the database
            Connection con = Connect.prepareConnection();

            con.setAutoCommit(false);
            
           /* PreparedStatement ps = con.prepareStatement("select Total_question, Total_section from testheader where Testid=?" );
            ps.setInt(1, testid);
            ResultSet secDetail = ps.executeQuery();
            while(secDetail.next()){
            	totalQues = secDetail.getInt(1);
            	totalSec = secDetail.getInt(2);
            }
            sec= new int[totalSec];
            ps = con.prepareStatement(
             "select Section_number, No_of_question from testsectiondetail where TestID=?");
            ps.setInt(1, testid);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
    	  System.out.println("section number : " + rs.getString(1));
    	  sec[i]=rs.getInt(2);
    	  i++;
    	  
    	  System.out.println("no of quest : "+ rs.getString(2));
    	  
      }
      System.out.println("section detail , sec length :" + sec.length + " i : "+ i);
      System.out.println("total Sec : " + totalSec);
      for(int k=0;k<totalSec;k++){
    	  System.out.println("sec: " + k+ " : " + sec[k]);
      
         int secNo = k+1;
            System.out.println("sec[k]: " + sec[k]);
            for (int m = 1; m<=sec[k]; m++) {
            	 ques++;
             	System.out.println("Q " + ques+ ", Ans " + correctAns[ques]);
             	//System.out.println("correct ans len :" +correctAns.length);
              
               ps = con.prepareStatement(
                        "insert into correctans(TestId, Ques_no, Answer, SectionNumber) values (?,?,?,?)");
                ps.setInt(1, testid);
                ps.setInt(2, ques);
                ps.setInt(3, correctAns[ques]);
                ps.setInt(4, secNo);

                j= ps.executeUpdate();
                
            }
          }  
            
*/
            PreparedStatement ps = con.prepareStatement(
            "SELECT Section_number, No_of_question FROM omrwebdb2.testsectiondetail where TestId = ?");
            ps.setInt(1, testid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	sec = rs.getInt(1);
            	ques=rs.getInt(2);
            
            
            	//System.out.println("corerct ans : Q "+i+ " " + correctAns[i]);
            	ps = con.prepareStatement(
                "insert into correctans(TestId, Ques_no, Answer, SectionNumber) values (?,?,?,?)");
            	for (int i = 1; i <= ques; i++) {
                	qno++;
        ps.setInt(1, testid);
        ps.setInt(2, qno);
        ps.setInt(3, correctAns[qno]);
        ps.setInt(4, sec);                  
        ps.addBatch();
             
        }//end for
            	noOfCorrectAns= ps.executeBatch();
                System.out.println("total Rows inserted in correct : "+ noOfCorrectAns.length);
           }    
            
           con.commit();
      
            con.close();
            log.info("rows inserted : "+ noOfCorrectAns.length);
        } catch (Exception e) {
            log.error("error while insert in correct Ans: " + e);
        }
        return noOfCorrectAns.length;
    }
}
