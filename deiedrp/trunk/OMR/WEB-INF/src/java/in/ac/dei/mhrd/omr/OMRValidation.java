
/*
 * @(#) OMRValidation.java
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

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.Field;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.validator.Resources;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.*;
import in.ac.dei.mhrd.omr.misprintedQues.ExistingWrongQues;

import javax.servlet.http.HttpServletRequest;

/**
 * This class defines the methods that 
 * defines custom validations
 * Creation date: 09-29-2010
 * @author Anshul Agarwal
 *  
 *
 */

public class OMRValidation {
	
	private static Logger log = Logger.getLogger(OMRValidation.class);
	
	
	public String validateTestNameJs(String testName)
	{
	    Connection  con = null;
	    String testNameExist="";
	    try{
			/*
			 * Check if the testname entered by the user already exist 
			 */
			con = (Connection) Connect.prepareConnection();
	            
           PreparedStatement ps = con.prepareStatement(
                   "select Test_name from testheader where Test_name=?");
            ps.setString(1, testName);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
           testNameExist=rs.getString(1);
           }
           
	    }catch(Exception e){
	    	log.error("Error in validation of unique name.");
	    }finally{
	    	Connect.freeConnection(con);
	    }    
           
	  
	    return testNameExist;
	  
	}


	
	/**
	 * This function imposes validation on the testname entered by the user.
	 *  The entered testname should be unique.
	 * @param bean
	 * @param action
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateUniqueTestName(Object bean,
			ValidatorAction action, Field field, ActionMessages errors, 
			Validator validator, HttpServletRequest request)
	{
	    String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
	    Connection  con = null;
	    boolean testNameExist=false;
	    try{
			/*
			 * Check if the testname entered by the user already exist 
			 */
			con = (Connection) Connect.prepareConnection();
	            
           PreparedStatement ps = con.prepareStatement(
                   "select * from testheader where Test_name=?");
            ps.setString(1, value);
           ResultSet rs = ps.executeQuery();
           testNameExist=rs.next();
           
           /*
            * If test name already exists then throw error message
            */
           
	    }catch(Exception e){
	    	log.error("Error in validation of unique name.");
	    }finally{
	    	Connect.freeConnection(con);
	    }    
           
	   if(testNameExist)
	   {
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    
	    return true;
	  
	}
	
	/**
	 * Validate Total Quetions. 
	 * Total Questions should be in the range 1 to 120
	 * @param bean
	 * @param action
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	
	public static boolean validateMaxQues(Object bean,
			ValidatorAction action, Field field, ActionMessages errors, 
			Validator validator, HttpServletRequest request)
	{
	    String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
	   
	   if((Integer.parseInt(value)>120) ||(Integer.parseInt(value)<1))
	   {
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    
	  return true;
	  
	}
	/**
	 * This function validates the correct answer sheet uploaded by the 
	 * user. The file should be in  bitmap format.
	 * @param bean
	 * @param action
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateSheet(Object bean,
			ValidatorAction action, Field field, ActionMessages errors, 
			Validator validator, HttpServletRequest request)
	{
	    String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
	   
	   if(!((value.endsWith((".bmp"))||(value.endsWith(".BMP")))))
	   {
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    
	  return true;
	  
	}
	
/**
 * This funcyion validates the uploaded response sheets.
 * Response sheets should be uploaded in azipped folder
 * @param bean
 * @param action
 * @param field
 * @param errors
 * @param validator
 * @param request
 * @return
 */	
	public static boolean validateResponseSheet(Object bean,
			ValidatorAction action, Field field, ActionMessages errors, 
			Validator validator, HttpServletRequest request)
	{
	    String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
	   System.out.println("value :" + value);
	   if(!((value.endsWith((".zip"))||(value.endsWith(".ZIP")))))
	   {
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    
	  return true;
	  
	}
	
	/**
	 * This function validates the size of correct answer sheets.
	 * The size of the correct answer sheet cannot be greater than 2MB
	 * @param bean
	 * @param action
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateCorrectSheetSize(Object bean,
			ValidatorAction action, Field field, ActionMessages errors, 
			Validator validator, HttpServletRequest request)
	{
	    String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
	    File fsize = new File(value);
	    
	   if((fsize.length()/1048576) > 3)
	   {
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    
	  return true;
	  
	}
	
	/**
	 * This function validates the misprinted question number entered by the
	 * user. The entered question number cannot be greater than total questions and less than 1.
	 * @param bean
	 * @param action
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	
	public static boolean validateWrongQuesNo(Object bean,
			ValidatorAction action, Field field, ActionMessages errors, 
			Validator validator, HttpServletRequest request)
	{
	    String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
	    String testName = request.getParameter("testName");
	    System.out.println("test id in validation :"+testName);
	    Connection con = null;
	    
	    try{
			
			  con =  Connect.prepareConnection();
	            
           PreparedStatement ps = con.prepareStatement(
                   "select Total_question from testheader where Test_name=?");
            ps.setString(1, testName);
           ResultSet rs = ps.executeQuery();
           rs.next();
           System.out.println("wrong Quesx : " + rs.getInt(1));
           
	   if(Integer.parseInt(value)>rs.getInt(1) || Integer.parseInt(value)<1)
	   {
		   log.info("return false in validation");
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   Connect.freeConnection(con);
		   return false;
	   }
	   Connect.freeConnection(con);
	    }catch(Exception e){
	    	log.error("error in validating wrong question shouldn't be greater than total question");
	    }
	   
	    return true;
	  
	}
	
	/**
	 * This function generates the error message if user attempts to insert the wrong question that has already been 
	 * inserted.
	 * @param bean
	 * @param action
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateExistingWrongQuesNo(Object bean,
			ValidatorAction action, Field field, ActionMessages errors, 
			Validator validator, HttpServletRequest request)
	{
	    String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
	    boolean existingQuesStatus=true;
	    System.out.println("inside existin validation");
	    //get the name of the test name selected by the user
	    String testName = request.getParameter("testName");
	    //get the testid of the selected test name
	    int testid = SelectTestId.getTestId(testName);
	    Connection con =null;
	    try{
			
			  con = Connect.prepareConnection();
	            
           PreparedStatement psExistQues = con.prepareStatement(
                   "select status from wrongquestion where TestId=? AND WrongQuestionNo=?");
           psExistQues.setInt(1, testid);
           psExistQues.setInt(2, Integer.parseInt(value));
           ResultSet rsExistQues = psExistQues.executeQuery();
           
           //check if question no. already exist
           while(rsExistQues.next()){
        	   System.out.println("statuts : "+ rsExistQues.getString(1));
        		existingQuesStatus=false;
        	}
           System.out.println("inside existing wrong Ques validation");
           Connect.freeConnection(con);
          if(existingQuesStatus){	
	   return true;
		   }
	   else{
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   
	   }
	    }catch(Exception e){
	    	log.error("error in validating existing wrong questions");
	    }
	   
	  return true;
	}
		
	
/**
 * The question number entered by the user for deletion should exist.
 * @param bean
 * @param action
 * @param field
 * @param errors
 * @param validator
 * @param request
 * @return
 */
	

public static boolean validateDeleteWrongQuesNo(Object bean,
		ValidatorAction action, Field field, ActionMessages errors, 
		Validator validator, HttpServletRequest request){

	String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
    String testName = request.getParameter("testName");
    TreeMap<Integer, String> wrongQuesList = ExistingWrongQues.getWrongQues(testName);
    
    for (Map.Entry<Integer, String> entry : wrongQuesList.entrySet()) {
        String val = entry.getValue();
        int key = entry.getKey();
        System.out.println("val : " + val +" key : "+ key);
   }
System.out.println(wrongQuesList.containsKey(Integer.parseInt(value)));
     
   /* for(ExistingWrongQues ob : wrongQuesList){
    	System.out.println("ob="+ob);
    	System.out.println(ob.quesNo + "  " + ob.status);
    }*/
   /* System.out.println("inside delete");
    String quesStatus ="";
    Connection con=null;
    int qno = Integer.parseInt(value);
    try{
		
		  con = Connect.prepareConnection();
            
       PreparedStatement psExistQues = con.prepareStatement(
               "select status from wrongquestion where TestId=? AND WrongQuestionNo=?");
       psExistQues.setInt(1, testid);
       psExistQues.setInt(2, Integer.parseInt(value));
       ResultSet rsExistQues = psExistQues.executeQuery();
       rsExistQues.next();
       quesStatus = rsExistQues.getString(1);
       System.out.println("quesStatus : "+quesStatus + " Q no " + Integer.parseInt(value));
    }catch(Exception e){
    	log.error("error in validating wrong question to be deleted");
    }finally{
    	Connect.freeConnection(con);
    }
    obj.add(new ExistingWrongQues(qno, quesStatus));
    System.out.println("obj="+obj);
    System.out.println("if value "+ wrongQuesList.contains(obj));*/
       if(!wrongQuesList.containsKey(Integer.parseInt(value))){
    	   System.out.println("inside if valid ");
    	   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    System.out.println("Aftr if");
	    return true;
	  
	}

/**
 * This method validates that the entered date should be greater than current date
 * @param bean
 * @param action
 * @param field
 * @param errors
 * @param validator
 * @param request
 * @return
 */

public static boolean validateCurrentDate(Object bean,
		ValidatorAction action, Field field, ActionMessages errors, 
		Validator validator, HttpServletRequest request)
{
    System.out.println("inside del validate");
	String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
    
    DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
    boolean b = true;
    try{

      Date d1 = df.parse(value);

      Date currentDate = new Date();
		if(d1.before(currentDate)){
			System.out.println("less than current date");
			
		
    	   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    }catch(Exception e){
	    	log.error("error in validating Current date "+e);
	    }
	    return true;
	  
	}
/**
 * This method validate that the date entered by the user in Manage Result interface 
 * is not less than the current date.
 * @param bean
 * @param action
 * @param field
 * @param errors
 * @param validator
 * @param request
 * @return
 */
public static boolean validateResultFromDate(Object bean,
		ValidatorAction action, Field field, ActionMessages errors, 
		Validator validator, HttpServletRequest request)
{
	String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
    String processEndDate = request.getParameter("firstResultDate");
	DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
    boolean b = true;
    try{

      Date d1 = df.parse(value);

      Date processDate = df.parse(processEndDate);
		if(d1.before(processDate)){
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    }catch(Exception e){
	    	log.error("error in validating current date");
	    }
	    return true;
	  
	}

public static boolean validateResultDisplayPeriod(Object bean,
		ValidatorAction action, Field field, ActionMessages errors, 
		Validator validator, HttpServletRequest request)
{
	System.out.println("inside result display period");
	String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
    String resultLastDate = request.getParameter("lastResultDate");
	DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
    try{

      Date d1 = df.parse(value);

      Date resultDate = df.parse(resultLastDate);
		if(d1.after(resultDate)){
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    }catch(Exception e){
	    	log.error("error in validating result last date");
	    }
	    return true;
	  
	}

}



