/*
 * @(#) EachCandidateInfo.java
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


package in.ac.dei.mhrd.omr.img;

import java.io.File;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * This class holds the CandidateID and testid
 * @author Anshul
 * Creation date:09-28-2010
 * @version 1.0
 *
 */
public class EachCandidateInfo {
	private static Logger log = Logger.getLogger(EachCandidateInfo.class);

	 String roll = " -1";//initial value
	  
	    ArrayList<String> tid = new ArrayList<String>();
		ArrayList<String> rno = new ArrayList<String>();
		
		/**
		 * This constructor initializes the 8 digit rno and six digit testid 
		 *  
		 */
	    
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
      
	    /**
	     * This method checks whether the candidate has filled
	     * the rollno or test id properly or not 
	     * @param fileName
	     * @param testid
	     * @return
	     */
	    public String getCandidateId(String fileName, int testid)
		{
	    	Locale obj = new Locale("en", "US");
			ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);

	    	String candidateId="-1";
	    	String testno ="-1";
	    	try{
			
			//System.out.println("roll : " +(rno.get(0)+ rno.get(1) + rno.get(2) + rno.get(3) + rno.get(4) + rno.get(5)+ rno.get(6)+rno.get(7)));
			//System.out.println("tid : " +(tid.get(0)+ tid.get(1) + tid.get(2) + tid.get(3) + tid.get(4) + tid.get(5)));
			if(tid.contains("NA") || tid.contains("error")){
				LogEntry.insert_Log(testid, new File(fileName).getName(), message.getString("code.E107"), message.getString("msg.E107"));
             	log.error(message.getString("msg.E107"));
				
			}
			else{
						
			  testno = (tid.get(0)+ tid.get(1) +tid.get(2) + tid.get(3) + tid.get(4) + tid.get(5));
			  if(Integer.parseInt(testno)!=testid){
				  LogEntry.insert_Log(testid, new File(fileName).getName(), message.getString("code.E107"), message.getString("msg.E107"));
	             	log.info("error in roll no"+message.getString("msg.E107"));
	  		 }
			}
			
			

			if(rno.contains("NA") || rno.contains("error")){ 
				LogEntry.insert_Log(testid, new File(fileName).getName(), message.getString("code.E106"), message.getString("msg.E106"));
	           	log.info("error in roll no"+message.getString("msg.E106"));
			}
			else{
						
				candidateId = (rno.get(0)+ rno.get(1) + rno.get(2) + rno.get(3) + rno.get(4) + rno.get(5)+ rno.get(6)+ rno.get(7));
			  
				
			  }
	    	} catch (Exception e) {
		log.error("error in each cand info : " +e);// TODO: handle exception
			}
	    	//System.out.println("b4 return : " + roll);
	    	return candidateId;

		}
		
	}
