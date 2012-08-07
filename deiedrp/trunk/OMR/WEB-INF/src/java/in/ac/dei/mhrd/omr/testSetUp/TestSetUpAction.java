/*
 * @(#) TestSetUpAction.java
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
package in.ac.dei.mhrd.omr.testSetUp;
import java.sql.*;
import java.sql.*;
import java.util.*;
import java.text.*;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;


/** 
 * This class inserts the details of new Test Set Up 
 * into the database
 * MyEclipse Struts
 * Creation date: 09-27-2010
 * @version 1.0
 * @author Anshul Agarwal
 * XDoclet definition:
 * @struts.action path="/test" name="TestSetUpForm" input="/testSetUpjsp.jsp" scope="request" validate="true"
 */
public class TestSetUpAction extends Action {
	/*
	 * Generated Methods
	 */
	private static Logger log = Logger.getLogger(TestSetUpAction.class);

   /**
    * This method computes the unique testid for each test 
    * @param lastTestId
    * @return
    */
	
	private String generateTestId(String lastTestId){
		String newTestId="0";
		Calendar cal = Calendar.getInstance();
		//get current year
		 String currentYr =  ""+cal.get(Calendar.YEAR);
		 
		 //if test is generated for the first time
		 
		 if(lastTestId=="0"){
			 newTestId= currentYr+"1000";
		 }
		 else{
			 String Yr = lastTestId.substring(0,4);
			 String code = lastTestId.substring(4, 8);
			 //If year remains the same, increment the last code by 1
			 if(currentYr.compareToIgnoreCase(Yr)==0){
				 
				 String newCode = ""+(Integer.parseInt(code)+1);
				 newTestId=currentYr+newCode;
			 }
			 else{//If year changes, reset the code to 1000

				 newTestId=currentYr+"1000";
			 }
		 } 
		 log.info("New test id generated");
		return newTestId;
		
	}
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * 
	 * This method fetch the details entered by the user and inserts  into database
	 */
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TestSetUpForm testSetUpForm = (TestSetUpForm) form;// TODO Auto-generated method stub
		
		String testDate= testSetUpForm.getSdate();
		String sheetFormat=testSetUpForm.getSheet();
		String groupFlag=testSetUpForm.getGroupExists();

		if(groupFlag==null){
			groupFlag="N";
			
		}
		

		
		Connection con=null;

		if(isCancelled(request)){
			return mapping.findForward("home");
		}
		String lastTestId="0";
		int totalSec = Integer.parseInt(testSetUpForm.getTotalSec());
		String[] ques = testSetUpForm.getSectionDetail1();
				String[] marks = testSetUpForm.getSectionDetail2();
		String[] negMarks = testSetUpForm.getSectionDetail3();
				String[] s4 = testSetUpForm.getSectionDetail4();
				try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date d = java.sql.Date.valueOf(testDate);
			     con = Connect.prepareConnection();
                    con.setAutoCommit(false);
                    /*
                     * Get the maximum(last) testId from the testheader to compute the next testid
                     */
                PreparedStatement psTestDetail = con.prepareStatement("select max(TestId) from testheader");
                ResultSet rsTestId=psTestDetail.executeQuery();                
                while(rsTestId.next()){
                	if(rsTestId.getString(1)!=null){
                    lastTestId=rsTestId.getString(1);
                	}
                }
                /*
                 * this method compute and returns the unique testid
                 */
                String newTestId=generateTestId(lastTestId);
                
                /*
                 * This query inserts data into testheader table
                 */
                		
                		
                     psTestDetail = con.prepareStatement(
	                        "insert into testheader(TestId, Test_name, Total_question, TestNo, Conduct_date, Total_section,sheet_format,group_flag) values(?,?,?,?,?,?,?,?)");
	                psTestDetail.setInt(1, Integer.parseInt(newTestId));
	                psTestDetail.setString(2,testSetUpForm.getTestName());
	                psTestDetail.setInt(3, Integer.parseInt(testSetUpForm.getTotalQues()));
	                psTestDetail.setString(4,testSetUpForm.getTestNo());
	                psTestDetail.setDate(5, d);
	                psTestDetail.setInt(6, totalSec);
	                psTestDetail.setString(7, sheetFormat);
	                psTestDetail.setString(8, groupFlag);
	                int q = psTestDetail.executeUpdate();
	               log.info("insert in test header: " + q);
	               
	               /*
	                * This query inserts detail into testSectionDetail
	                */
	               psTestDetail = con.prepareStatement(
                   "insert into testsectiondetail(TestId, Section_number, No_of_question, Marks_each_question, Neg_Marks) values(?,?,?,?,?)");
	                
	                for(int i=0;i<totalSec;i++){
	                	System.out.println("insert ing sec: i : " + i);
	                psTestDetail.setInt(1, Integer.parseInt(newTestId));
	                psTestDetail.setInt(2, (i+1));
	                psTestDetail.setInt(3, Integer.parseInt(ques[i]));
	                psTestDetail.setFloat(4, Float.parseFloat(marks[i]));
	                psTestDetail.setFloat(5, Math.abs(Float.parseFloat(negMarks[i])));
	                //add query into the batch
	                psTestDetail.addBatch();
	                }
	                //execute batch
	                int countInsert[] = psTestDetail.executeBatch();
	                
	                  con.commit(); 
	                  //check if data inserted into the database or not
	                  if(countInsert.length>=1){
	  	        	request.setAttribute("updateMsg", "Test created Successfully!!");
                    log.info("Test created Successfully.");
	                  }
	                //int r = psTestDetail.executeUpdate();
	                //System.out.println("insert section: "+ r);
            //con.close();
	        } catch (Exception e) {
	        	request.setAttribute("updateMsg", "Test cannot be created Successfully!!");
	            log.error("Error while setting up test : "+e);
	        }
	        finally{
	        	Connect.freeConnection(con);
	        
	        }

		return mapping.findForward("success");
	}
}