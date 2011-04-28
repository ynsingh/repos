/*
 * @(#) LogInterAction.java
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
package in.ac.dei.mhrd.omr.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import in.ac.dei.mhrd.omr.SelectTestId;

/** 
 * This class determines the log table to be displayed based on the 
 * selections made by the user 
 *
 * MyEclipse Struts
 * Creation date: 12-04-2010
 * @author Anshul Agarwal
 * XDoclet definition:
 * @struts.action path="/logInter" name="logInterfaceForm" input="/Menu.jsp" scope="request" validate="true"
 * @version 1.0
 * 
 */
public class LogInterAction extends Action {
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
		LogInterfaceForm logInterfaceForm = (LogInterfaceForm) form;// TODO Auto-generated method stub
		
		String testName="";
	     testName= logInterfaceForm.getTestName();
	     int testId = SelectTestId.getTestId(testName);
		String selectedLog=logInterfaceForm.getLog();
		request.setAttribute("TestName", testName);
		request.setAttribute("testid", testId);
		/*
		 * if user opt to view the error log
		 */
		if(isCancelled(request)){
			return mapping.findForward("home");
		}
		if(selectedLog.equalsIgnoreCase("E")){
			request.setAttribute("logName", "Error Log");
		request.setAttribute("tableName", "log");
		}
		else{
			/*
			 * If user opt to view the test log
			 */
			request.setAttribute("logName", "Test Log");

			request.setAttribute("tableName", "testlog");
		}
			return mapping.findForward("erroLog");
		

		
	}
}