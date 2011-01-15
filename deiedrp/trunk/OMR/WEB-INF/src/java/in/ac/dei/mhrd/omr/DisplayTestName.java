/**
 * 
 */
package in.ac.dei.mhrd.omr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import in.ac.dei.mhrd.omr.img.*;


/**
 * This class retrieves Test conduct Date and Test Name according to the requirements. 
 * @author Anshul
 * @create Date 2010-11-22
 *
 */
public class DisplayTestName {
	
	private Locale obj = new Locale("en", "US");
	private ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);
	
	private static Logger log = Logger.getLogger(testSetUpAction.class);


    /**
     * This function retrieves test name for the "upload Response sheet" interface
     * @param from
     * @param to
     * @return
     */	
	public String[] populateNameList(String from, String to){
		
		int i=0;
		String[] testName=null;
	
	try {
		
        Connection con = Connect.prepareConnection();
        ResultSet rs;
        con.setAutoCommit(false);
        
        //count number of records 
        
        PreparedStatement ps = con.prepareStatement(
        "select count(*) from testheader where upload_status='NUPD' AND Conduct_date<=now()");
        rs = ps.executeQuery();
        rs.next();
        System.out.println("count inside name list: " + rs.getInt(1));
        //set the size of the array as there are number of records in the table 
        testName = new String[rs.getInt(1)];

        //parase the date in the specified format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		
         ps = con.prepareStatement(
                "SELECT distinct Test_name FROM testheader where upload_status='NUPD' AND Conduct_Date BETWEEN ? AND ?");
         ps.setTimestamp(1, timest);
         ps.setTimestamp(2, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  //populate the array with testnames retrieved from the database
       		  testName[i]=rs.getString(1);
       		   i++;
       		   log.info("Test name retrieved for wrong Question");
         }
         
       	 con.commit(); 
        con.close();
        }
        catch(Exception e){
       log.error("Error in retrieving Date for wrong Ques " + e);        
       }
        
        	return testName;
     
}
	
	/**
	 * This function populates the Test name combo box of 'Select Result' UI 
	 * @param from
	 * @param to
	 * @return
	 */
public String[] populateResultNameList(String from, String to){
		
		int i=0;
		String[] testName=null;
	
	try {
		
        Connection con = Connect.prepareConnection();
        con.setAutoCommit(false);
        ResultSet rs;

        PreparedStatement ps = con.prepareStatement(
        "select count(*) from testheader where Test_status='P' AND  ResultDisplayedfrom<=now() AND ResultDisplayedTo>=now()");
        rs = ps.executeQuery();
        rs.next();
        System.out.println("count inside name list: " + rs.getInt(1));
        testName = new String[(rs.getInt(1))+1];

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		System.out.println("time Stamp value" + timest);
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		System.out.println("time Stamp value" + timeen);
		
		System.out.println("Executing select statemt ");
         ps = con.prepareStatement(
                "select Test_name from testheader where ResultDisplayedFrom<=now() AND ResultDisplayedTo >=now() AND Test_Status='P' AND Conduct_date BETWEEN ? AND ?");
         ps.setTimestamp(1, timest);
         ps.setTimestamp(2, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  testName[i]=rs.getString(1);
       		   i++;
         }
         con.commit();
         con.close();
       	  
        
        }
        catch(Exception e){
        	System.out.println("error in try " + e);
        }
        
        	return testName;
     
	
}



public String[] populateLogNameList(String from, String to){
		
		int i=0;
		String[] testName=null;
	
	try {
		
        Connection con = Connect.prepareConnection();
        con.setAutoCommit(false);
        ResultSet rs;

        PreparedStatement ps = con.prepareStatement(
        "select count(*) from testheader where Test_status='S' OR Test_status='P'");
        rs = ps.executeQuery();
        rs.next();
        System.out.println("count inside name list: " + rs.getInt(1));
        testName = new String[rs.getInt(1)];

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		
         ps = con.prepareStatement(
                "SELECT distinct Test_name FROM testheader where Test_status='P' AND Conduct_date BETWEEN ? AND ?");
         ps.setTimestamp(1, timest);
         ps.setTimestamp(2, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  testName[i]=rs.getString(1);
       		   i++;
         }
         
       	  con.commit();
       	  con.close();
        
        }
        catch(Exception e){
        	log.error("error in try " + e);
        }
        
        	return testName;
     
	
}

	
public String[] populateWrongQuesName(String from, String to){
		
		int i=0;
		String[] testName=null;
	
	try {
		
        Connection con = Connect.prepareConnection();
        con.setAutoCommit(false);
        ResultSet rs;

        PreparedStatement ps = con.prepareStatement(
        "select count(*) from testheader where Test_status='R'");
        rs = ps.executeQuery();
        rs.next();
        System.out.println("count inside name list: " + rs.getInt(1));
        testName = new String[rs.getInt(1)];

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		System.out.println("time Stamp value" + timest);
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		System.out.println("time Stamp value" + timeen);
		
		System.out.println("Executing select statemt ");
         ps = con.prepareStatement(
                "SELECT distinct Test_name FROM testheader where Test_status='R' AND Conduct_date BETWEEN ? AND ?");
         ps.setTimestamp(1, timest);
         ps.setTimestamp(2, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  System.out.println("value in wrong ques database="+rs.getString(1));
       		  testName[i]=rs.getString(1);
       		   i++;
         }
         con.commit();
         con.close();
       	  
        
        }
        catch(Exception e){
        	System.out.println("error in try " + e);
        }
        
        	return testName;
     
	
}

public String[] populateCorrectAnsName(String from, String to){ 
	
	int i=0;
	String[] testName=null;

try {
	
    Connection con = Connect.prepareConnection();
    con.setAutoCommit(false);
    
    ResultSet rs;

    PreparedStatement ps = con.prepareStatement(
    "select count(*) from testheader t where t.Test_status='R' AND t.Conduct_date<=now() AND t.TestId NOT IN (Select distinct TestId from correctans)");
    rs = ps.executeQuery();
    rs.next();
    System.out.println("count inside name list: " + rs.getInt(1));
    testName = new String[rs.getInt(1)];

    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	java.util.Date fromdate = sdf.parse(from); 
	java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
	System.out.println("time Stamp value" + timest);
	
	java.util.Date todate = sdf.parse(to); 
	java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
	System.out.println("time Stamp value" + timeen);
	
	System.out.println("Executing select statemt ");
     ps = con.prepareStatement(
            "SELECT t.Test_name, t.TestId FROM testheader t where Test_status='R' AND Conduct_date<=now() AND Conduct_date BETWEEN ? AND ? AND t.TestId Not IN(Select distinct TestId from correctans)");
     ps.setTimestamp(1, timest);
     ps.setTimestamp(2, timeen);
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  System.out.println("value in correct ques database="+rs.getString(1));
   		  testName[i]=rs.getString(1);
   		   i++;
     }
     con.commit();
   	  con.close();
   	  
    
    }
    catch(Exception e){
    	System.out.println("error in try " + e);
    }
    
    	return testName;
 

}


public String[] populateNameListProcess(String from, String to){
		
		int i=0;
		String[] testName=null;
	
	try {
		
        Connection con = Connect.prepareConnection();
        con.setAutoCommit(false);
        ResultSet rs;

        PreparedStatement ps = con.prepareStatement(
        "select count(*) from testheader t where t.upload_status='UPD' AND (t.Test_Status='S' OR t.Test_Status='R') AND (t.TestId IN (Select distinct TestId from correctans)) AND t.Conduct_date<=now();");
        rs = ps.executeQuery();
        rs.next();
        System.out.println("count inside name process: " + rs.getInt(1));
        testName = new String[rs.getInt(1)];

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		System.out.println("time Stamp value" + timest);
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		System.out.println("time Stamp value" + timeen);
		
		
         ps = con.prepareStatement(
                "select distinct t.Test_name from testheader t where t.upload_status='UPD' AND (t.Test_Status='S' OR t.Test_Status='R') AND (t.TestId IN (Select distinct TestId from correctans))AND t.Conduct_Date<=now() AND t.Conduct_date BETWEEN ? AND ?");
         ps.setTimestamp(1, timest);
         ps.setTimestamp(2, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  System.out.println("value in database="+rs.getString(1));
       		  testName[i]=rs.getString(1);
       		   i++;
         }
     
       	  con.commit();
       	  con.close();
        }
        catch(Exception e){
        	System.out.println("error in try " + e);
        }
        
        	return testName;
     
	
}
	
public String[] selectDate(){
		
		int i=1;
		String[] testDate=null;
	    
	try {
		
        Connection con = Connect.prepareConnection();
        ResultSet rs;
        con.setAutoCommit(false);
        PreparedStatement ps = con.prepareStatement(
        "select count(*) from testheader where upload_status='NUPD' AND Conduct_date<=now()");
        rs = ps.executeQuery();
        rs.next();
        System.out.println("count : " + rs.getInt(1));
        testDate = new String[(rs.getInt(1))+1];
        testDate[0]=message.getString("msg.select");
 
        
         ps = con.prepareStatement(
                "select distinct Conduct_date FROM testheader where upload_status='NUPD' AND Conduct_date<=now() order BY(Conduct_date)");
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  System.out.println("value in database="+rs.getString(1));
       		  testDate[i]=rs.getString(1);
       		   i++;
         }
         
       	  con.commit();
       	  con.close();
        
        }
        catch(Exception e){
        	System.out.println("error in try " + e);
        }
        
        	return testDate;
     
	
}

public String[] selectDateWrongQues(){
	
	int i=1;
	String[] testDate=null;
	 try {
	
    Connection con = Connect.prepareConnection();
    ResultSet rs;
   con.setAutoCommit(false);
    PreparedStatement ps = con.prepareStatement(
    "select count(*) from testheader where Test_Status='R'");
    rs = ps.executeQuery();
    rs.next();
    System.out.println("count : " + rs.getInt(1));
    testDate = new String[(rs.getInt(1))+1];
    testDate[0]=message.getString("msg.select");
    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader where Test_Status='R' order by Conduct_date");
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  System.out.println("value in database="+rs.getString(1));
   		  testDate[i]=rs.getString(1);
   		   i++;
     }
     con.commit();
     con.close();
   	  
    
    }
    catch(Exception e){
    	System.out.println("error in try " + e);
    }
    
    	return testDate;
 

}

public String[] selectDateProcess(){
		
		int i=1;
		String[] testDate=null;
	
	try {
		
        Connection con = Connect.prepareConnection();
        ResultSet rs;
        con.setAutoCommit(false);
        PreparedStatement ps = con.prepareStatement(
        "select count(*) from testheader t where t.upload_status='UPD' AND (t.Test_Status='S' OR t.Test_Status='R') AND (t.TestId IN (Select distinct TestId from correctans)) AND t.Conduct_date<=now()");
        rs = ps.executeQuery();
        rs.next();
        System.out.println("count inside date process: " + rs.getInt(1));
        testDate = new String[(rs.getInt(1))+1];
        testDate[0]=message.getString("msg.select");

        ps = con.prepareStatement(
                "select distinct t.conduct_date from testheader t where t.upload_status='UPD' AND (t.Test_Status='S' OR t.Test_Status='R') AND t.Conduct_Date<=now() AND (t.TestId IN (Select distinct TestId from correctans))order BY Conduct_date");
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  System.out.println("value in database="+rs.getString(1));
       		  testDate[i]=rs.getString(1);
       		   i++;
         }
        con.commit();
        con.close();
        }
        catch(Exception e){
        	System.out.println("error in try " + e);
        }
        
        	return testDate;
     
	
}

public String[] selectDateCorrectAns(){
	
	int i=1;
	String[] testDate=null;

try {
	
    Connection con = Connect.prepareConnection();
    ResultSet rs;
    con.setAutoCommit(false);
    PreparedStatement ps = con.prepareStatement(
    "select count(*) from testheader t where Test_Status='R' AND Conduct_date<=now() AND t.TestId NOT IN (Select distinct TestId from correctans)");
    rs = ps.executeQuery();
    rs.next();
    System.out.println("count : " + rs.getInt(1));
    testDate = new String[(rs.getInt(1))+1];
    testDate[0]=message.getString("msg.select");

    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader t where t.Test_Status='R' AND t.Conduct_date<=now() AND t.TestId NOT IN (Select distinct TestId from correctans) order BY Conduct_date");
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  System.out.println("value in database="+rs.getString(1));
   		  testDate[i]=rs.getString(1);
   		   i++;
     }
     
   	  con.commit();
      con.close();
    }
    catch(Exception e){
    	System.out.println("error in try " + e);
    }
    
    	return testDate;
 

}

public String[] selectDateResult(){
	
	int i=1;
	String[] testDate=null;

try {
	
    Connection con = Connect.prepareConnection();
    ResultSet rs;
    con.setAutoCommit(false);
    PreparedStatement ps = con.prepareStatement(
    "select count(*) from testheader where Test_Status='P' AND  ResultDisplayedFrom<=now() AND ResultDisplayedTo>=now()");
    
    rs = ps.executeQuery();
    rs.next();
    System.out.println("count : " + rs.getInt(1));
    testDate = new String[(rs.getInt(1))+1];
    testDate[0]=message.getString("msg.select");

    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader where Test_Status='P' AND  ResultDisplayedFrom<=now() AND ResultDisplayedTo>=now() order BY Conduct_date");
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  System.out.println("value in database="+rs.getString(1));
   		  testDate[i]=rs.getString(1);
   		   i++;
     }
     
   	  con.commit();
   	  con.close();
    
    }
    catch(Exception e){
    	System.out.println("error in try " + e);
    }
    
    	return testDate;
 

}



public String[] selectDateLog(){
	
	int i=1;
	String[] testDate=null;

try {
	
    Connection con = Connect.prepareConnection();
    ResultSet rs;
    con.setAutoCommit(false);
    PreparedStatement ps = con.prepareStatement(
    "select count(*) from testheader where Test_Status='S' OR Test_Status='P'");
    rs = ps.executeQuery();
    rs.next();
    System.out.println("count : " + rs.getInt(1));
    testDate = new String[(rs.getInt(1))+1];
    testDate[0]=message.getString("msg.select");

    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader where Test_Status='S' OR Test_Status='P' order BY Conduct_date");
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  System.out.println("value in database="+rs.getString(1));
   		  testDate[i]=rs.getString(1);
   		   i++;
     }
   	  
   	  con.commit();
   	  con.close();
    }
    catch(Exception e){
    	System.out.println("error in try " + e);
    }
    
    	return testDate;
 }

public boolean checkTimePeriod(String from, String to) throws ParseException{
	
	System.out.println("inside time period java class : from - " + from +" to : "+ to );
	DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
    boolean b = true;
      // Get Date 1
      Date d1 = df.parse(from);

      // Get Date 2
      Date d2 = df.parse(to);

      String relation="";
      System.out.println("compare : " + d1.compareTo(d2));
      if (d1.compareTo(d2)<=0){
    	  System.out.println("inside if");
         relation = "the  date is less";
      }
      else {
    	  System.out.println("inside else ");
         relation = "greater";
         b=false;
      }
      
      return b;
}

public String[] selectDateManageResult(){
	
	int i=1;
	String[] testDate=null;

try {
	
    Connection con = Connect.prepareConnection();
    ResultSet rs;
    con.setAutoCommit(false);
    PreparedStatement ps = con.prepareStatement(
    "select count(*) from testheader where Test_Status='P' AND ResultDisplayedTo>now()");
    rs = ps.executeQuery();
    rs.next();
    System.out.println("count : " + rs.getInt(1));
    testDate = new String[(rs.getInt(1))+1];
    testDate[0]=message.getString("msg.select");
    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader where Test_Status='P' AND ResultDisplayedTo > now()");
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  System.out.println("value in database="+rs.getString(1));
   		  testDate[i]=rs.getString(1);
   		   i++;
     }
     
   	  con.commit();
      con.close();
    }
    catch(Exception e){
    	System.out.println("error in try Manage Result Date " + e);
    }
    
    	return testDate;
 

}

public String[] populateNameListManageResult(String from, String to){
	
	int i=1;
	String[] testName=null;

try {
	
    Connection con = Connect.prepareConnection();
    ResultSet rs;
    con.setAutoCommit(false);
    PreparedStatement ps = con.prepareStatement(
    "select count(*) from testheader where Test_Status='P' AND ResultDisplayedTo>now()");
    rs = ps.executeQuery();
    rs.next();
    System.out.println("count : " + rs.getInt(1));
    testName = new String[(rs.getInt(1))+1];
    testName[0]=message.getString("msg.select");
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	java.util.Date fromdate = sdf.parse(from); 
	java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
	System.out.println("time Stamp value" + timest);
	
	java.util.Date todate = sdf.parse(to); 
	java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
	System.out.println("time Stamp value" + timeen);
	
	
     ps = con.prepareStatement(
            "SELECT Test_name FROM testheader where Test_Status='P' AND ResultDisplayedTo>now() AND Conduct_date BETWEEN ? AND ?");
     ps.setTimestamp(1, timest);
     ps.setTimestamp(2, timeen);
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  System.out.println("value in database="+rs.getString(1));
   		  testName[i]=rs.getString(1);
   		   i++;
     }
 con.commit();
 con.close();
    }
    catch(Exception e){
    	System.out.println("error in try " + e);
    }
    
    	return testName;
 

}




}
