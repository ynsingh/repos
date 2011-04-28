/*
 * @(#) ManageWrongQuesAction.java
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



import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This class defines the method that retrieves the existing wrong 
 * questions of the selected test
 *  
 * MyEclipse Struts
 * Creation date: 12-02-2010
 * @author Anshul Agarwal
 * @version 1.0
 * XDoclet definition:
 * @struts.action path="/manageWrongQues" name="manageWrongQues" input="/manageWrongQues.jsp" validate="true"
 * @struts.action-forward name="editWrongQues" path="/delQues.jsp"
 * 
 */
public class ManageWrongQuesAction extends Action {
	/*
	 * Generated Methods
	 */
	private static Logger log = Logger.getLogger(ManageWrongQuesAction.class);

	/** 
	 * This method retrieves the existing wrong questions of the selected test
	 * 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * 
	 */
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ManageWrongQuesForm manageWrongQues = (ManageWrongQuesForm) form;// TODO Auto-generated method stub
		
		if(isCancelled(request)){
			return mapping.findForward("home");
		}
		HttpSession hs = request.getSession(true);
		
		//This method accepts the testname as parameter and returns the list of existing wrong questions 
	
TreeMap<Integer, String> qnoList = ExistingWrongQues.getWrongQues(manageWrongQues.getTestName()); 	
		if(qnoList.size()==0){
			log.info("Wrong Ques not retrieved");
			request.setAttribute("wrongmsg", "No mis printed Question entered for this test");
			return mapping.findForward("WrongQ");
		}
		else{
		hs.setAttribute("testName", manageWrongQues.getTestName());
		hs.setAttribute("qnoList", qnoList);
	
		return mapping.findForward("editWrongQues");
		}
		}
	}
