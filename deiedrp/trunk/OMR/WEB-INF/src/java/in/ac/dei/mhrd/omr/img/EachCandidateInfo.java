package in.ac.dei.mhrd.omr.img;

import java.io.File;

import java.sql.*;
import com.mysql.jdbc.exceptions.*;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;


/*
 * This class displays the details filled by the student
 */
public class EachCandidateInfo {
	private static Logger log = Logger.getLogger(EachCandidateInfo.class);

	 String roll = " -1";
	  
	    ArrayList<String> tid = new ArrayList<String>();
		ArrayList<String> rno = new ArrayList<String>();
	    
	    public EachCandidateInfo() {
	        rno.add(0, "NA");
	        rno.add(1, "NA");
	        rno.add(2, "NA");
	        rno.add(3, "NA");
	        rno.add(4, "NA");
			rno.add(5, "NA");
			rno.add(6, "NA");
			rno.add(7, "NA");
			
			 tid.add(0, "NA");
		        tid.add(1, "NA");
		        tid.add(2, "NA");
		        tid.add(3, "NA");
		        tid.add(4, "NA");
				tid.add(5, "NA");
				
	    }    
   /* public void insertCandidateInfo(String filename, int testid) {
        File filepath = new File(filename);
        boolean flag=true;

        
            
			 * Entry in log table if there is any error
			 
            
          
            if(this.faculty==null ){
            	CandidateDetail.errorMsg=CandidateDetail.errorMsg+" Faculty is missing";
            	RotateImg.infoFlag = false;
            	
            }
           if( this.sem==null ){
        	   
        	   CandidateDetail.errorMsg=CandidateDetail.errorMsg+" Semester is missing";
        	   RotateImg.infoFlag = false;
         }
           if(this.qt==null){
        	    CandidateDetail.errorMsg=CandidateDetail.errorMsg+" QT is missing";           
        	   
            	
            	RotateImg.infoFlag = false;
            }
           if(this.rno.contains("NA"));
          // System.out.println("Faculty: " +faculty + "sem : " + sem +" Qt :" + qt);
       //	System.out.println("roll: " + rno.get(0)+ rno.get(1) + rno.get(2) + rno.get(3) + rno.get(4) + rno.get(5));
      	 
            if(RotateImg.infoFlag){
            	try {
                    
                    Connection con = Connect.prepareConnection();

            	System.out.println("b4 insert");
            	System.out.println("TEstid : "+ testid);
            	 
            	roll = (rno.get(0)+ rno.get(1) + rno.get(2) + rno.get(3) + rno.get(4) + rno.get(5));
            	 
            	System.out.println("RollNo : " + Integer.parseInt(this.roll));
            	PreparedStatement ps = con.prepareStatement(
                "insert into candidate_detail(TestId, RollNo, Faculty, Semester, Qt) values (?,?,?,?,?)");

            	ps.setInt(1, testid);
        ps.setInt(2, Integer.parseInt(this.roll));
        ps.setString(3, this.faculty);
        ps.setString(4, this.sem);
        ps.setString(5, this.qt);
        ps.executeUpdate();
            	
            
            	con.close();
            } 
            catch (MySQLIntegrityConstraintViolationException e){
            	LogEntry.insert_Log(testid, filepath.getName(),
                "This Roll No has already been processed .");
            }
            
            catch (SQLException e) {
            	LogEntry.insert_Log(testid, filepath.getName(),
                "Err while processing the sheet! Reload the Sheet");
               
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }
}
            	
            else{
            	System.out.println("Rotate in insert" + RotateImg.infoFlag);
            	LogEntry.insert_Log(testid, filepath.getName(),
                CandidateDetail.errorMsg);
            	CandidateDetail.errorMsg=" ";
            
            }
            	
            	    }
*/
   
	    public String display(String fileName, int testid)
		{
	    	Locale obj = new Locale("en", "US");
			ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);

	    	String roll="-1";
	    	String testno ="-1";
	    	try{
			
			System.out.println("roll : " +(rno.get(0)+ rno.get(1) + rno.get(2) + rno.get(3) + rno.get(4) + rno.get(5)+ rno.get(6)+rno.get(7)));
			System.out.println("tid : " +(tid.get(0)+ tid.get(1) + tid.get(2) + tid.get(3) + tid.get(4) + tid.get(5)));
			if(tid.contains("NA") || tid.contains("error")){
				System.out.print("  error in Test no");
				LogEntry.insert_Log(testid, new File(fileName).getName(), message.getString("code.E107"), message.getString("msg.E107"));
             	log.error(message.getString("msg.E107"));
				
			}
			else{
						
			  testno = (tid.get(0)+ tid.get(1) +tid.get(2) + tid.get(3) + tid.get(4) + tid.get(5));
			  if(Integer.parseInt(testno)!=testid){
				  LogEntry.insert_Log(testid, new File(fileName).getName(), message.getString("code.E107"), message.getString("msg.E107"));
	             	log.error(message.getString("msg.E107"));
	  			  System.out.println("tid in display " + testno);

				  
			  }
			  }
			
			

			if(rno.contains("NA") || rno.contains("error")){ 
				System.out.print("  error in Roll no");
				LogEntry.insert_Log(testid, new File(fileName).getName(), message.getString("code.E106"), message.getString("msg.E106"));
	           	log.error(message.getString("msg.E106"));
			}
			else{
						
			  roll = (rno.get(0)+ rno.get(1) + rno.get(2) + rno.get(3) + rno.get(4) + rno.get(5)+ rno.get(6)+ rno.get(7));
			  System.out.println("roll in display " + roll);
			  
				
			  }
	    	} catch (Exception e) {
				System.out.println(" in each cand info");// TODO: handle exception
			}
	    	//System.out.println("b4 return : " + roll);
	    	return roll;

		}
		
	}
