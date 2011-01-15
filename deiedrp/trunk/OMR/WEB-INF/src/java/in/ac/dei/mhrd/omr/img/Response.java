package in.ac.dei.mhrd.omr.img;

import java.io.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

import com.mysql.jdbc.exceptions.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;



/**
 *
 * @author Anshul Agarwal
 *This class inserts the ques no, option, section into the database
 */
public class Response {
	
	/**
	 * This function accepts CandidateID alias RollNo and an array of responses into the database
	 * @param attemptAns
	 * @param TestNo
	 * @param str
	 * @param sec
	 */
	private static Logger log = Logger.getLogger(Response.class);

    public static void insert_response(byte[] attemptAns, int testid,
        int rno, int noOfQues, String filepath) {
    	Locale obj = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);
		int[] responseCount=null;
    	File fileName = new File(filepath);
    	System.out.println("inside reponse insert");
    	Connection con=null;
    	int ques=0, sec=0;
    	int qno=0;
        try {
             con = Connect.prepareConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
            "SELECT Section_number, No_of_question FROM testsectiondetail where TestId = ?");
            ps.setInt(1, testid);
            ResultSet rs = ps.executeQuery();
            
            /*
             * Check for duplicate Roll No.
             */
            ps = con.prepareStatement(
            "SELECT distinct FileName FROM response where TestId=? AND RollNo=?");
             ps.setInt(1, testid);
             ps.setInt(2, rno);
             ResultSet rsDuplicateRno = ps.executeQuery();
             while(rsDuplicateRno.next()){
            	 if(rno!=-1){
            	 LogEntry.insert_Log(testid, fileName.getName(), message.getString("code.E102"), message.getString("msg.E102")+"in file "+ fileName.getName());
             	log.error(message.getString("msg.E102"));
             	System.out.println("Duplicate Roll No found filename ,"+ fileName.getName());
            	 }
             }
             ps = con.prepareStatement(
             "insert into Response(Testid, RollNo, ques_no, ans, SectionNumber, FileName) values (?,?,?,?,?,?)");

            while(rs.next()){
            	sec = rs.getInt(1);
            	ques=rs.getInt(2);
            	

                    for (int i = 1; i <= ques; i++) {
                    	
                    	                     	qno++;
                     	    if (attemptAns[qno] != 0) {
                    ps.setInt(1, testid);
                    ps.setInt(2, rno);
                    ps.setInt(3, qno);
                    ps.setString(4, "" + attemptAns[qno]);
                    ps.setInt(5, sec);
                    ps.setString(6, fileName.getName());
                    ps.addBatch();     

                }
            }//end for

            }
            responseCount = ps.executeBatch();
                       if(responseCount.length==attemptAns.length-1){
                    	   System.out.println("All responses inserted successfully");
            	log.info("All responses inserted ");
            }
           // log.info("Response inserted : " + responseCount.length);
            con.commit();
            con.close();

        } catch(MySQLIntegrityConstraintViolationException e){
        	
        	LogEntry.insert_Log(testid, fileName.getName(), message.getString("code.E105"), message.getString("msg.E105"));
        	log.error(message.getString("msg.E105"));
        }
        
        catch (Exception e) {
            System.out.println("error while insert in Response : " + e);
             log.error("Error while inserting responses");
        	LogEntry.insert_Log(testid, fileName.getName(),
            message.getString("code.E101"), message.getString("msg.E101")+e);
            System.out.println("error while insert in Response : " + e);
        }
        finally{
        	try{
        	con.close();
        	}catch(Exception e){
        		
        	}
        }
    }
}
