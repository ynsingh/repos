/*
 * @(#) WrongQuesAction.java
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
import in.ac.dei.mhrd.omr.img.*;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.Resources;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/** 
 *  This class fetches information from the misprinted Question UI and 
 *  inserts into the table WrongQuestion in the database
 *
 * MyEclipse Struts
 * Creation date: 09-28-2010
 * @author Anshul Agarwal
 * @version 1.0
 * XDoclet definition:
 * @struts.action path="/wrong" name="wrongQuesForm" input="/WrongQues.jsp" validate="true"
 * 
 */
public class WrongQuesAction extends Action {
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
	
	private static Logger log = Logger.getLogger(WrongQuesAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WrongQuesForm wrongQuesForm = (WrongQuesForm) form;// TODO Auto-generated method stub
		ActionErrors error = new ActionErrors();
		ActionMessage msg = new ActionMessage("msg.existingQues");
		
		if(isCancelled(request)){
			return mapping.findForward("home");
		}
		//get Test Id of the selected test name
		int testid = SelectTestId.getTestId(wrongQuesForm.getTestName());
		Connection con = null;
				try {
					//getting connection from the database
             con = Connect.prepareConnection();
            con.setAutoCommit(false);
            //insert data into wromgQuestion table
                        PreparedStatement  psWrongQues=con.prepareStatement("insert into wrongquestion(TestId, WrongQuestionNo, status) values(?,?,?)");
                   psWrongQues.setInt(1, testid);
                   psWrongQues.setInt(2,Integer.parseInt(wrongQuesForm.getQno()));
                   psWrongQues.setString(3, wrongQuesForm.getStatus());
                 int i=  psWrongQues.executeUpdate();
            	 con.commit();
                  //check if wrong question inserted successfully
                 if(i==1){
                	 request.setAttribute("wrongmsg", " Question Added Successfully!! ");
                	 log.info("Wrong Question inserted successfully");
                 }
       		//closing database connection
Connect.freeConnection(con);
}catch(Exception e){
           log.error("Error in inerting Wrong Question");     
  }
 
     return mapping.findForward("WrongQ");
		
	}
}