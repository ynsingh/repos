/*
 * @(#) ExistingWrongQues.java
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

import java.sql.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import in.ac.dei.mhrd.omr.SelectTestId;
import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.*; 

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class defines the function that retrieves the wrong Question and 
 * its status from the database
 * @author Anshul
 * @version 1.0
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
	 * This method retrieves the WrongQuestion Number and its status
	 * from the database
	 * @param testName
	 * @return
	 */
	public static TreeMap<Integer, String> getWrongQues(String testName){
		TreeMap<Integer, String> quesList =new TreeMap<Integer, String>();
		//get the testid of the selected test name 
		int testid = SelectTestId.getTestId(testName);
		System.out.println("S e");
		Connection con = null;
		try{
		 con =  Connect.prepareConnection();
		PreparedStatement psWrongQues = con.prepareStatement("select WrongQuestionNo, status from wrongquestion where TestId=?");
		psWrongQues.setInt(1, testid);
		ResultSet  rsWrong = psWrongQues.executeQuery();
		while(rsWrong.next()){
		quesList.put(rsWrong.getInt(1), rsWrong.getString(2));
			
		}
        }catch(Exception e){
        	
        	log.error("error in retrieving Wrong Ques : " + e);
        }
        finally{
        	System.out.println("E E");
        	Connect.freeConnection(con);
        }
       return quesList; 

	}

}
