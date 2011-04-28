/*
 * @(#) WrongQuesValidation.java
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



package in.ac.dei.mhrd.omr.misprintedQues;

import in.ac.dei.mhrd.omr.SelectTestId;
import in.ac.dei.mhrd.omr.dbConnection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.struts.validator.Resources;


public class WrongQuesValidation {
	private static Logger log = Logger.getLogger(WrongQuesValidation.class);

	
	public static String getQno(String testName, String qno){
	
	    Connection con =null;
         int quesNo=0;
	    String totalQuesStatus="true";
	    System.out.println("inside existin validation");
	    //get the name of the test name selected by the user
	    //String testName = request.getParameter("testName");
	    //String status = request.getParameter("status");
	    //get the testid of the selected test name
	   // String msg="false";
	    try{
		     quesNo = Integer.parseInt(qno);

		    int testid = SelectTestId.getTestId(testName);

          con =  Connect.prepareConnection();
          
          PreparedStatement ps = con.prepareStatement(
                  "select Total_question from testheader where Test_name=?");
           ps.setString(1, testName);
          ResultSet rs = ps.executeQuery();
          rs.next();
          
	   if(Integer.parseInt(qno)>rs.getInt(1) || Integer.parseInt(qno)<1)
	   {
		   totalQuesStatus="false";
	   }
	    }catch(Exception e){
	    	log.error("error in validating existing wrong questions");
	    }
	   finally{
		   Connect.freeConnection(con);

	   }
	  return totalQuesStatus;
	}
		
	public static String getDeleteQno(String testName, String qno){
		
	    boolean existingQuesStatus=true;
	    
	    //get the name of the test name selected by the user
	    //String testName = request.getParameter("testName");
	    //String status = request.getParameter("status");
	    //get the testid of the selected test name
	    String msg="true";
    	if(!qno.equals("")){
         
	    try{
	    	String q = qno.trim();
	    	System.out.println("q : " + q);
	    	int qno1= Integer.parseInt(q);
	    	TreeMap<Integer, String> mapw = ExistingWrongQues.getWrongQues(testName);
			 
          if(!mapw.containsKey(Integer.parseInt(qno))){			 
	     // msg = "Question number " +qno +" does not exist.";
        	  msg="false";
	      return msg;
	   }
	    }catch(NumberFormatException e){
	    	//msg= "Question number must be an integer.";
	    	
	    	log.error(" error in del existing Q " + e);
	    }
	    }
    return msg;
	}
        
}
