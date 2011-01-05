package in.ac.dei.mhrd.omr;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import in.ac.dei.mhrd.omr.img.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Creation date: 09-29-2010
 * @author Anshul Agarwal
 * This class defines the custom validations 
 *
 */

public class OMRValidation {
	
	private static Logger log = Logger.getLogger(OMRValidation.class);

	
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
	    try{
			/*
			 * Check if the testname entered by the user already exist 
			 */
			 Connection con = (Connection) Connect.prepareConnection();
	            
           PreparedStatement ps = con.prepareStatement(
                   "select * from testheader where Test_name=?");
            ps.setString(1, value);
           ResultSet rs = ps.executeQuery();
           /*
            * If test name already exists then throw error message
            */
	   if(rs.next())
	   {
		   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	   con.close();
	    }catch(Exception e){
	    	log.error("Error in validation of unique name.");
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
	    
	   if((fsize.length()/1048576) > 2)
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
	    
	    
	    try{
			
			 Connection con =  Connect.prepareConnection();
	            
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
		  con.close();
		   return false;
	   }
	   con.close();
	    }catch(Exception e){
	    	log.info("error in validating wrong question shouldn't be greater than total question");
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
	    //get the name of the test name selected by the user
	    String testName = request.getParameter("testName");
	    //get the testid of the selected test name
	    int testid = SelectTestId.getTestId(testName);
	    
	    try{
			
			 Connection con = Connect.prepareConnection();
	            
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
           con.close();

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
		Validator validator, HttpServletRequest request)
{
	String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
    String testName = request.getParameter("testName");
    int testid = SelectTestId.getTestId(testName);
    
    try{
		
		 Connection con = Connect.prepareConnection();
            
       PreparedStatement psExistQues = con.prepareStatement(
               "select status from wrongquestion where TestId=? AND WrongQuestionNo=?");
       psExistQues.setInt(1, testid);
       psExistQues.setInt(2, Integer.parseInt(value));
       ResultSet rsExistQues = psExistQues.executeQuery();
       rsExistQues.next();
       con.close();
       ArrayList<ExistingWrongQues> wrongQuesList = ExistingWrongQues.getWrongQues(testName);
       if(!(wrongQuesList.contains(new ExistingWrongQues(Integer.parseInt(value), rsExistQues.getString(1))))){
    	   
    	   errors.add(field.getKey(), Resources.getActionMessage(validator, request, action, field ));
		   return false;
	   }
	    }catch(Exception e){
	    	log.error("error in validating wrong question to be deleted");
	    }
	    return true;
	  
	}

/**
 * This function validates that the entered date should be greater than current date
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
	    	System.out.println("error in validation");
	    }
	    return true;
	  
	}

public static boolean validateResultFromDate(Object bean,
		ValidatorAction action, Field field, ActionMessages errors, 
		Validator validator, HttpServletRequest request)
{
    System.out.println("inside del validate");
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


}



