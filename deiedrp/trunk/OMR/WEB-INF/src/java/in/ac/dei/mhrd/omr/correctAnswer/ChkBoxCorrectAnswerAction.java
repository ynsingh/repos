
/*
 * @(#) ChkBoxCorrectAnswerAction.java
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

 
package in.ac.dei.mhrd.omr.correctAnswer;

import java.util.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;

/** 
 * This class defines the method that inserts correct Answers into the database
 * entered by the user manually(via checkboxes)
 * 
 * MyEclipse Struts
 * Creation date: 12-30-2010
 * @author Ashutosh
 * XDoclet definition:
 * @struts.action input="/ValidateAnswer.jsp" validate="true"
 * @version 1.0
 */
public class ChkBoxCorrectAnswerAction extends Action {
	
	private static Logger log = Logger.getLogger(ChkBoxCorrectAnswerAction.class);

	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AnswerForm answerForm=(AnswerForm)form;
		int totalSection; 
		//total no. of sections 
		  
		int questionTotal;
		//total no. of questions
		
		 HttpSession hs = request.getSession(true);
		 ArrayList<Integer> quesNo=new ArrayList<Integer>((ArrayList<Integer>)hs.getAttribute("qno")); 
          		//ArrayList for Question no. 
         
         ArrayList<Integer> sectionNo=new ArrayList<Integer>((ArrayList<Integer>)hs.getAttribute("secno")); 
          		//ArrayList for the section no. 
         
        ArrayList<Byte> byteAnsValues=new ArrayList<Byte>((ArrayList<Byte>)hs.getAttribute("byteTransffer")); 
               //for getting the ArrayList of byte for questions from Validate answer page		 
 		 
 		 int testid = (Integer)hs.getAttribute("testid");
 		 Connection con = null;
 		 PreparedStatement ps=null;  
 		 ResultSet rsCorrectAnswer=null; 
 		 int k=1; 
 		 int count=1; 
 		 int i=1; 
 		 questionTotal=1; 
 		
 		Object elementQuestion;
 		Object elementAnswer;
 			 
 		try{   
    			 con = Connect.prepareConnection(); 
    			 con.setAutoCommit(false);
 			    ps=con.prepareStatement("select Total_section from testheader where TestId=?"); 
 			    ps.setInt(1, testid);
 			    rsCorrectAnswer=ps.executeQuery(); 
 			 	rsCorrectAnswer.next(); 
 			  	totalSection=rsCorrectAnswer.getInt(1); 
         
        	 
        	//updating correct answer  
        	 ps = con.prepareStatement("insert into correctans(TestId, SectionNumber, Ques_no, Answer) values (?,?,?,?)"); 
        	
        	while(i<=totalSection) 
        		{ 
               	 elementQuestion=quesNo.get(i-1);
        	 for(k=1;k<=elementQuestion.hashCode();k++) 
        	{ 
        	elementAnswer=byteAnsValues.get(k-1);
            ps.setInt(1, testid); 
            ps.setInt(2, i); 
            ps.setInt(3,questionTotal); 
            ps.setInt(4,elementAnswer.hashCode()); 
             //add answers in a batch
            ps.addBatch(); 
            	 
              			questionTotal++; 
              			} 
              	i++; 
        		} 
        	//execute batch
        		int c[] = ps.executeBatch();
        		//check if answers inserted successfully
        	 if(c.length>=1) 
              { 
              log.info("Correct Answer inserted Successfully!! " + c.length); 
              request.setAttribute("wrongmsg","Correct Answers inserted Successfully!! ");
              } 
              con.commit();
        	} 
        	 catch(Exception e) 
        	 { 
        	 log.error("Exception while inserting correct Answers : "+e.getMessage()); 
        	 }  finally{
             	Connect.freeConnection(con);
                
             }
 	
		return mapping.findForward("correctAnsMsg");
	}
}