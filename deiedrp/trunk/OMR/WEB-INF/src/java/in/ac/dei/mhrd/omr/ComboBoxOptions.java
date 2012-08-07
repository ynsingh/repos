/*
 * @(#) ComboBoxOptions.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */

package in.ac.dei.mhrd.omr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import in.ac.dei.mhrd.omr.SelectTestId;

import org.apache.log4j.Logger;
import in.ac.dei.mhrd.omr.dbConnection.Connect;


/**
 * This class retrieves Test conduct Date and Test Name from the database and 
 * populate respective comboBoxes. 
 * Methods of this class are called via Ajax
 * @author Anshul Agarwal
 * @create Date 2010-11-22
 * @version 1.0
 *
 */
public class ComboBoxOptions {
	
	private Locale obj = new Locale("en", "US");
	private ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);
	
	private static Logger log = Logger.getLogger(ComboBoxOptions.class);

	/**
     * This method retrieves test name for the "upload Response sheet" interface.
     * Only those testnames will be retrieved for which response sheets has yet not been uploaded
     * and that lies in the time period selected
     * by the user.
     * @param from
     * @param to
     * @return
     */	
	public ArrayList<String> populateNameList(String from, String to){
		
		Connection con=null;
	ArrayList<String> testNameList = new ArrayList<String>();
	try {
		//establish database connection
         con = Connect.prepareConnection();
        ResultSet rs=null;
        con.setAutoCommit(false);
        
        //count number of records 
        System.out.println("inside upld "+message.getString("sheetsNotUploaded"));

        PreparedStatement ps = null;       
        //parse the date in the specified format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		
         ps = con.prepareStatement(
                "SELECT distinct Test_name FROM testheader where upload_status=? AND Conduct_date<=now() AND Conduct_date BETWEEN ? AND ? order By Test_name");
         ps.setString(1, message.getString("sheetsNotUploaded"));
         ps.setTimestamp(2, timest);
         ps.setTimestamp(3, timeen);
         
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  //populate the arraylist with testnames retrieved from the database
       		  testNameList.add(rs.getString(1));
       		  
       		   log.info("Test name retrieved for upload Response Sheets");
         }
         
       	con.commit(); 
        
        }
        catch(Exception e){
       log.error("Error in retrieving TestName for upload response sheet interface " + e);        
       }
        finally{
        	Connect.freeConnection(con);
        
        }
        	return testNameList;
     
}
	
	/**
	 * This method populates the Test name combo box of 'Select Result' UI 
	 * Only those test names will be retrieved for which response sheets have been completely processed, and
	 * duration for which result should be displayed is valid
	 * @param from
	 * @param to
	 * @return
	 */
public ArrayList<String> populateResultNameList(String from, String to){
	Connection con=null;

		ArrayList<String> testNameList=new ArrayList<String>();
	
	try {
		
         con = Connect.prepareConnection();
        con.setAutoCommit(false);
        ResultSet rs = null;

        PreparedStatement ps = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		
         ps = con.prepareStatement(
                "select Test_name from testheader where ResultDisplayedFrom<=now() AND ResultDisplayedTo >=now() AND Test_Status=? AND Conduct_date BETWEEN ? AND ? order By Test_name");
         ps.setString(1, message.getString("processed"));
         ps.setTimestamp(2, timest);
         ps.setTimestamp(3, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  testNameList.add(rs.getString(1));
         }
         con.commit();
         
        }
        catch(Exception e){
        	log.error("error in retrieving test name for result " + e);
        }
        finally{
        	Connect.freeConnection(con);
        }	
        return testNameList;
  }

/**
 * This method returns the list of test names for the 'Log' interface, that lie in the time period
 * selected by the user. 
 * @param from
 * @param to
 * @return
 */

public ArrayList<String> populateLogNameList(String from, String to){
	Connection con=null;
	
		ArrayList<String> testNameList=new ArrayList<String>();
	
	try {
		
         con = Connect.prepareConnection();
        con.setAutoCommit(false);
        ResultSet rs=null;
         //count number of records for which processing of sheets has been started or completed
        PreparedStatement ps = null;        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		
         ps = con.prepareStatement(
                "SELECT distinct Test_name FROM testheader where (Test_status=? OR Test_status=?) AND Conduct_date BETWEEN ? AND ? order by Test_name");
         ps.setString(1, message.getString("processed"));
         ps.setString(2, message.getString("processed"));
         ps.setTimestamp(3, timest);
         ps.setTimestamp(4, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  testNameList.add(rs.getString(1));
         }
         
       	  con.commit();
        
        }
        catch(Exception e){
        	log.error("error in retrieving test name for log interface " + e);
        }
        finally{
        	Connect.freeConnection(con);
        }
        	return testNameList;
     
}

/**
 * This method returns the list of test names for wrong question number
 * interface. Only those names will be retrieved for which sheets has 
 * not yet been processed
 * @param from
 * @param to
 * @return
 */	
public ArrayList<String> populateWrongQuesName(String from, String to){
	Connection con=null;
		ArrayList<String> testNameList=new ArrayList<String>();
	
	try {
		
         con = Connect.prepareConnection();
        con.setAutoCommit(false);
        ResultSet rs;

        PreparedStatement ps =null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		
		testNameList.add(0, message.getString("msg.select"));
		//System.out.println("Executing select statemt ");
         ps = con.prepareStatement(
                "SELECT distinct Test_name FROM testheader where Test_status=? AND Conduct_date BETWEEN ? AND ? order by Test_name");
         ps.setString(1, message.getString("TestReady"));
         ps.setTimestamp(2, timest);
         ps.setTimestamp(3, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  log.info("Test name retrieved for wrong Question" +rs.getString(1));
       		  testNameList.add(rs.getString(1));
         }
         con.commit();
       	  
        
        }
        catch(Exception e){
        	log.error("error in retrieving Test name for wrong ques interface " + e);
        }
        finally{
        	Connect.freeConnection(con);
        }
        	return testNameList;
  
}

/**
 * This method returns the list of test name for "correct Answer" interface
 * Only those names will be retrieved for which test has been conducted but not yet processed
 * 
 * @param from
 * @param to
 * @return
 */
public ArrayList<String> populateCorrectAnsName(String from, String to){ 
	Connection con=null;

	ArrayList<String> testNameList=new ArrayList<String>();

try {
	
     con = Connect.prepareConnection();
    con.setAutoCommit(false);
    
    ResultSet rs=null;

    PreparedStatement ps = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	java.util.Date fromdate = sdf.parse(from); 
	java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
	
	java.util.Date todate = sdf.parse(to); 
	java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
	testNameList.add(0, message.getString("msg.select"));
     ps = con.prepareStatement(
            "SELECT t.Test_name, t.TestId FROM testheader t where Test_status=? AND Conduct_date BETWEEN ? AND ? AND t.TestId Not IN (Select distinct TestId from correctans where group_code  not in (select distinct(group_code) from group_table)) order by Test_name");
     ps.setString(1, message.getString("TestReady"));
     ps.setTimestamp(2, timest);
     ps.setTimestamp(3, timeen);
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  log.info("Test name retrieved for correct answer interface "+rs.getString(1));
   		  testNameList.add(rs.getString(1));
     }
     con.commit();
   }
    catch(Exception e){
    	log.error("Error in retrieving testname for correct answer " + e);
    }
    finally{
    	Connect.freeConnection(con);
    }
    	return testNameList;
}

/**
 * This method returns the list of testnames for which sheets has been uploaded but not yet processed.
 * Only those test names will be retrieved whose test conduct date is less than the current date.
 * @param from
 * @param to
 * @return
 */
public ArrayList<String> populateNameListProcess(String from, String to){
	Connection con=null;
		ArrayList<String> testNameList=new ArrayList<String>();
	
	try {
		
         con = Connect.prepareConnection();
        con.setAutoCommit(false);
        ResultSet rs=null;

        PreparedStatement ps = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		
		
         ps = con.prepareStatement(
                "select distinct t.Test_name from testheader t where t.upload_status=? AND (t.Test_Status=? OR t.Test_Status=?) AND (t.TestId IN (Select distinct TestId from correctans))AND t.Conduct_Date<=now() AND t.Conduct_date BETWEEN ? AND ? order by Test_name");
         ps.setString(1, message.getString("sheetsUploaded"));
         ps.setString(2, message.getString("processingStart"));
         ps.setString(3, message.getString("TestReady"));
         ps.setTimestamp(4, timest);
         ps.setTimestamp(5, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		 log.info("Test names in Process Sheet interface "+rs.getString(1));
       		  testNameList.add(rs.getString(1));
         }
     
       	  con.commit();
       	  
        }
        catch(Exception e){
        	log.error("Error in retrieving test names for process sheet interface" + e);
        }
        finally{
        	Connect.freeConnection(con);
        }
        	return testNameList;
}
	
/**
 * This method returns the list of test dates for which sheets has not yet been uploaded
 * @return
 */
public ArrayList<String> selectDate(){
	Connection con=null;

	ArrayList<String> testDateList = new ArrayList<String>();
	    
	try {
		
         con = Connect.prepareConnection();
        ResultSet rs=null;
        con.setAutoCommit(false);
        //count the number of records
        PreparedStatement ps = null;
        //set first option as "select"
        testDateList.add(0, message.getString("msg.select"));
       
         // retrieve test conduct date
         ps = con.prepareStatement(
                "select distinct Conduct_date FROM testheader where upload_status=? AND Conduct_date<=now() order BY(Conduct_date)");
         ps.setString(1, message.getString("sheetsNotUploaded"));

         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  log.info("value in select Date function="+rs.getString(1));
       		  testDateList.add(rs.getString(1));
         }
         
       	  con.commit();
        }
        catch(Exception e){
        	log.error("error in select date function " + e);
        }
        finally{
        	Connect.freeConnection(con);
        }
        return testDateList;
 }

/**
 * This method returns the list of test conduct date for wrong question interface
 *   
 * @return
 */
public ArrayList<String> selectDateWrongQues(){
	Connection con=null;
	ArrayList<String> testDateList = new ArrayList<String>();
	 try {
	
     con = Connect.prepareConnection();
    ResultSet rs=null;
   con.setAutoCommit(false);
    PreparedStatement ps = null;
    //set first option as select
    testDateList.add(0, message.getString("msg.select"));
    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader where Test_Status=? order by Conduct_date");
     ps.setString(1,message.getString("TestReady"));

     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  log.info("value in date for wrong QUes"+rs.getString(1));
   		  testDateList.add(rs.getString(1));
     }
     con.commit();
    }
    catch(Exception e){
    	log.error("error in wromg ques Date " + e);
    }
    finally{
    	Connect.freeConnection(con);
    }
    return testDateList;
}

/**
 * This method returns the list of test conduct date for the 
 * "Process Sheet" interface 
 * @return
 */
public ArrayList<String> selectDateProcess(){
	Connection con=null;

		ArrayList<String> testDateList=new ArrayList<String>();
	
	try {
		
         con = Connect.prepareConnection();
        ResultSet rs;
        con.setAutoCommit(false);
        PreparedStatement ps = null;
        testDateList.add(0, message.getString("msg.select"));

        ps = con.prepareStatement(
                "select distinct t.conduct_date from testheader t where t.upload_status=? AND (t.Test_Status=? OR t.Test_Status=?) AND t.Conduct_Date<=now() AND (t.TestId IN (Select distinct TestId from correctans))order BY Conduct_date");
        ps.setString(1, message.getString("sheetsUploaded"));
        ps.setString(2, message.getString("processingStart"));
        ps.setString(3, message.getString("TestReady"));
        
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  log.info("value in process date method="+rs.getString(1));
       		  testDateList.add(rs.getString(1));
         }
        con.commit();
        }
        catch(Exception e){
        	log.error("error in select date for process method " + e);
        }
        finally{
        	Connect.freeConnection(con);
        }
      return testDateList;
 
}

/**
 * This method returns the list of test conduct date for the
 * "correct Answer" interface
 * @return
 */
public ArrayList<String> selectDateCorrectAns(){
	Connection con=null;

	ArrayList<String> testDateList=new ArrayList<String>();

try {
	
     con = Connect.prepareConnection();
    ResultSet rs=null;
    con.setAutoCommit(false);
    PreparedStatement ps = null;
    
    testDateList.add(0, message.getString("msg.select"));
    ps=con.prepareStatement("select Date(now()+ INTERVAL -3 DAY)");
    ResultSet rs1=ps.executeQuery();
    rs1.next();
    System.out.println("rs11.getString(1)" + rs1.getDate(1));
    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader t where t.Test_Status=?" +
            "AND t.TestId NOT IN (Select distinct TestId from correctans where group_code  not in (select distinct(group_code) from group_table)) AND (t.Conduct_date + INTERVAL -3 DAY)<=now() order by t.Conduct_date");
     ps.setString(1, message.getString("TestReady"));
     System.out.println("");
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  System.out.println("1 " + rs.getString(1));
   		  testDateList.add(rs.getString(1));
     }
     
   	  con.commit();
    }
    catch(Exception e){
    	log.error("error in correct ans date " + e);
    }finally{
    	Connect.freeConnection(con);
    }
    
    	return testDateList;
}

/**
 * This method returns the list of test date for 
 * "Result" interface.
 * @return
 */
public ArrayList<String> selectDateResult(){
	Connection con=null;

	ArrayList<String> testDateList=new ArrayList<String>();

try {
	
     con = Connect.prepareConnection();
    ResultSet rs;
    con.setAutoCommit(false);
    PreparedStatement ps = null;
    testDateList.add(message.getString("msg.select"));

     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader where Test_Status=? AND  ResultDisplayedFrom<=now() AND ResultDisplayedTo>=now() order BY Conduct_date");
     ps.setString(1, message.getString("processed"));
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  log.info("value in result date ="+rs.getString(1));
   		  testDateList.add(rs.getString(1));
     }
   	  con.commit();
    
    }
    catch(Exception e){
    	log.error("error in result date " + e);
    }
    finally{
    	Connect.freeConnection(con);
    }
    	return testDateList;
 }

/**
 * This method returns the list of test date for
 * the log interface
 * @return
 */

public ArrayList<String> selectDateLog(){
	Connection con=null;
	ArrayList<String> testDateList=new ArrayList<String>();

try {
	
     con = Connect.prepareConnection();
    ResultSet rs=null;
    con.setAutoCommit(false);
    PreparedStatement ps = null;
    testDateList.add(0,message.getString("msg.select"));
    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader where Test_Status=? OR Test_Status=? order BY Conduct_date");
     ps.setString(1, message.getString("processingStart"));
     ps.setString(2, message.getString("processed"));
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  log.info("date in log interface "+rs.getString(1));
   		  testDateList.add(rs.getString(1));
     }
   	  con.commit();
    }
    catch(Exception e){
    	log.error("error in try " + e);
    }
    finally{
    	Connect.freeConnection(con);
    }   
    	return testDateList;
 }
/**
 * This method checks whether the test conduct time period selected by the user is 
 * valid or not, and returns the boolean value accordingly 
 * @param from
 * @param to
 * @return
 * @throws ParseException
 */
public boolean checkTimePeriod(String from, String to) throws ParseException{
	
	DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
    boolean b = true;
      // Get Date 1
      Date d1 = df.parse(from);

      // Get Date 2
      Date d2 = df.parse(to);

      //String relation="";
      if (d1.compareTo(d2)<=0){
    	  b=true;
        // relation = "the  date is less";
      }
      else {
         b=false;
      }
      return b;
}

/**
 * This method returns the test date for manage result interface
 * @return
 */
public ArrayList<String> selectDateManageResult(){
	Connection con=null;

	ArrayList<String> testDateList = new ArrayList<String>();

try {
	
     con = Connect.prepareConnection();
    ResultSet rs=null;
    con.setAutoCommit(false);
    PreparedStatement ps =null;
    testDateList.add(0, message.getString("msg.select"));
    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader where Test_Status=? AND ResultDisplayedTo > now() order by Conduct_date");
     ps.setString(1, message.getString("processed"));
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  log.info("date in manage result"+rs.getString(1));
   		  testDateList.add(rs.getString(1));
     }
     
   	  con.commit();
    }
    catch(Exception e){
    	log.error("error in Manage Result Date " + e);
    }
    finally{
    	Connect.freeConnection(con);
    }
    
    	return testDateList;
 }

/**
 * This method retrieves test name for the "ManageResult" interface.
 * Only those test names will be retrieved for which response sheets has been completely processed
 * and that lies in the time period selected
 * by the user .
 * @param from
 * @param to
 * @return
 */
public ArrayList<String> populateNameListManageResult(String from, String to){
	Connection con=null;

	ArrayList<String> testNameList=new ArrayList<String>();

try {
	
     con = Connect.prepareConnection();
    ResultSet rs=null;
    con.setAutoCommit(false);
    PreparedStatement ps = null;
    
    testNameList.add(0, message.getString("msg.select"));
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	java.util.Date fromdate = sdf.parse(from); 
	java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
	
	java.util.Date todate = sdf.parse(to); 
	java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
	
     ps = con.prepareStatement(
            "SELECT Test_name FROM testheader where Test_Status=? AND ResultDisplayedTo>now() AND Conduct_date BETWEEN ? AND ? order by Test_name");
     ps.setString(1, message.getString("processed"));
     ps.setTimestamp(2, timest);
     ps.setTimestamp(3, timeen);
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  log.info("value in manage Result test name="+rs.getString(1));
   		  testNameList.add(rs.getString(1));
     }
 con.commit();
    }
    catch(Exception e){
    	log.error("error in manage Result Test name " + e);
    }finally{
    	Connect.freeConnection(con);
    }
    
    	return testNameList;
 }

/**
 * This method returns the list of section numbers for "Link Groups to Section" interface for adding or deleting groups
 * @author Dheeraj Singh
 * @param testName, name of the test
 * @return sectionList containing all section numbers of Test  
 */

public ArrayList<String> populateSection(String testName)
{
  Connection con = null;
  SelectTestId selectTestId = new SelectTestId();

  ArrayList<String> sectionList = new ArrayList<String>();
  sectionList.add("--Select--");
  try
  {
    con = Connect.prepareConnection();
    con.setAutoCommit(false);

    ResultSet rs = null;

    PreparedStatement ps = null;
    
    ps = con.prepareStatement("select Section_number from testsectiondetail where TestId=?");
    ps.setString(1, selectTestId.getTestId(testName) + "");
    

    rs = ps.executeQuery();

    while (rs.next()) {
      sectionList.add(rs.getString(1));
    }
    con.commit();
  }
  catch (Exception e) {
    log.error("Error in retrieving section numbers from testSectionDetail table " + e);
  }
  finally {
    Connect.freeConnection(con);
  }
  return sectionList;
}

/**
 * This method returns the list of test name for "Link Groups to Section" interface
 * Only those names will be retrieved for which test has been conducted but not yet processed
 * @author Dheeraj Singh
 * @param from i.e., from date
 * @param to i.e., to date
 * @return testNameList containing list of test names
 */

public ArrayList<String> populateSectionGroupsName(String from, String to){ 
	Connection con=null;

	ArrayList<String> testNameList=new ArrayList<String>();

try {
	
    con = Connect.prepareConnection();
    con.setAutoCommit(false);
    
    ResultSet rs=null;

    PreparedStatement ps = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	java.util.Date fromdate = sdf.parse(from); 
	java.sql.Timestamp timeobj1 = new java.sql.Timestamp(fromdate.getTime()); 
	
	java.util.Date todate = sdf.parse(to); 
	java.sql.Timestamp timeobj2 = new java.sql.Timestamp(todate.getTime()); 
	
     ps = con.prepareStatement(
                        "SELECT t.Test_name, t.TestId FROM testheader t where Test_status=? AND sheet_format=? AND Conduct_date BETWEEN ? AND ? AND t.TestId Not IN (Select distinct TestId from correctans where group_code  not in (select distinct(group_code) from group_table)) order by Test_name");
     ps.setString(1, message.getString("TestReady"));
     ps.setString(2, "GRC");
     ps.setTimestamp(3, timeobj1);
     ps.setTimestamp(4, timeobj2);
   
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  testNameList.add(rs.getString(1));
     }
     con.commit();
   }
    catch(Exception e){
    	log.error("Error in retrieving testname for link groups to section " + e);
    }
    finally{
    	Connect.freeConnection(con);
    }
    	return testNameList;
}

/**
 * This method returns the list of test conduct date for the
 * "Link Groups to Section" interface
 * @author Dheeraj Singh
 * @return testDateList containing conduct dates of unprocessed tests
 */

public ArrayList<String> selectDateGroupSection(){
	Connection con=null;
	ArrayList<String> testDateList=new ArrayList<String>();

try {
	
    con = Connect.prepareConnection();
    ResultSet rs=null;
    con.setAutoCommit(false);
    PreparedStatement ps = null;
    
    testDateList.add(0, message.getString("msg.select"));
    
     ps = con.prepareStatement(
                		 "select distinct Conduct_date from testheader t where t.Test_Status=?" +
             "AND sheet_format=? AND t.TestId NOT IN (Select distinct TestId from correctans where group_code  not in (select distinct(group_code) from group_table)) AND (t.Conduct_date + INTERVAL -3 DAY)<=now() order by t.Conduct_date");
     ps.setString(1, message.getString("TestReady"));
     ps.setString(2, "GRC");
     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  testDateList.add(rs.getString(1));
     }
     
   	  con.commit();
    }
    catch(Exception e){
    	log.error("error in group section date " + e);
    }finally{
    	Connect.freeConnection(con);
    }
    
    	return testDateList;
}
/**
 * This method returns the list of test conduct date for Group sheet test
 *   
 * @return
 */
public ArrayList<String> selectDateGroupSetup(){
	Connection con=null;
	ArrayList<String> testDateList = new ArrayList<String>();
	 try {
	
     con = Connect.prepareConnection();
    ResultSet rs=null;
   con.setAutoCommit(false);
    PreparedStatement ps = null;
    //set first option as select
    testDateList.add(0, message.getString("msg.select"));
    
     ps = con.prepareStatement(
            "select distinct Conduct_date from testheader where Test_Status=? AND sheet_format=? order by Conduct_date");
     ps.setString(1,message.getString("TestReady"));
     ps.setString(2,"GRC");

     rs = ps.executeQuery();
     
   	  while(rs.next()){
   		  log.info("value in date for group test date "+rs.getString(1));
   		  testDateList.add(rs.getString(1));
     }
     con.commit();
    }
    catch(Exception e){
    	log.error("error in Group test date " + e);
    }
    finally{
    	Connect.freeConnection(con);
    }
    return testDateList;
}
/**
 * This method returns the list of test names for group format sheet
 * interface. Only those names will be retrieved for which sheets has 
 * not yet been processed
 * @param from
 * @param to
 * @return
 */	
public ArrayList<String> populateNameGroupSetUp(String from, String to){
	Connection con=null;
		ArrayList<String> testNameList=new ArrayList<String>();
	
	try {
		
         con = Connect.prepareConnection();
        con.setAutoCommit(false);
        ResultSet rs;

        PreparedStatement ps =null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		java.util.Date fromdate = sdf.parse(from); 
		java.sql.Timestamp timest = new java.sql.Timestamp(fromdate.getTime()); 
		
		java.util.Date todate = sdf.parse(to); 
		java.sql.Timestamp timeen = new java.sql.Timestamp(todate.getTime()); 
		
		testNameList.add(0, message.getString("msg.select"));
		System.out.println("Group Set up ");
         ps = con.prepareStatement(
                "SELECT distinct Test_name FROM testheader where Test_status=? AND sheet_format=? AND Conduct_date BETWEEN ? AND ? order by Test_name");
         ps.setString(1, message.getString("TestReady"));
         ps.setString(2, "GRC");
         ps.setTimestamp(3, timest);
         ps.setTimestamp(4, timeen);
       
         rs = ps.executeQuery();
         
       	  while(rs.next()){
       		  log.info("Test name retrieved for Group Sheet" +rs.getString(1));
       		  testNameList.add(rs.getString(1));
         }
         con.commit();
       	  
        
        }
        catch(Exception e){
        	log.error("error in retrieving Test name for group Sheet " + e);
        }
        finally{
        	Connect.freeConnection(con);
        }
        	return testNameList;
  
}

}
