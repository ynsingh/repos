/*
 * @(#) DelQuesAction.java
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import in.ac.dei.mhrd.omr.SelectTestId;
import in.ac.dei.mhrd.omr.dbConnection.Connect;

import org.apache.log4j.Logger;

/** 
 *This Class defines the method that deletes the misprinted Question number entered by the user
 * 
 * MyEclipse Struts
 * Creation date: 12-05-2010
 * @author Anshul Agarwal
 * XDoclet definition:
 * @struts.action path="/delQuesPath" name="delQues" input="/delQues.jsp" scope="request" validate="true"
 * @struts.action-forward name="delQuesSuccess" path="/delQuesSuccess.jsp"
 * @version 1.0
 * 
 */
public class DelQuesAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * 
	 * This method deletes the misprinted Questions that has
	 * been inserted by the user. Questions can be deleted for only those
	 * test that has not yet been processed.
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	
	private static Logger log = Logger.getLogger(DelQuesAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DelQuesForm delQues = (DelQuesForm) form;// TODO Auto-generated method stub
		System.out.println("1");
		Connection con=null;
		PreparedStatement psWrongQues=null;
		if(isCancelled(request)){
			return mapping.findForward("manageWrongQues");
		}
		try{
			con = Connect.prepareConnection();
			con.setAutoCommit(false);
			int testid = SelectTestId.getTestId(delQues.getTestName());
	        System.out.println("2 tst " + testid);
			 psWrongQues = con.prepareStatement("delete from wrongquestion where TestId=? AND WrongQuestionNo=?");
			psWrongQues.setInt(1, testid);
			psWrongQues.setInt(2, Integer.parseInt(delQues.getQno()));

			int countDelRow = psWrongQues.executeUpdate();
			if(countDelRow>=1){
				System.out.println("count on del: "+countDelRow);
				log.info("Mis Printed Ques deleted : "+ delQues.getQno());
				request.setAttribute("wrongmsg", "Question No. "+ delQues.getQno()+" deleted successfully");
			}
			else{
				System.out.println("Question nt del");
				request.setAttribute("wrongmsg", "This question no. does not exist");

			}
			con.commit();
		}catch (Exception e) {
			log.error("Error while deleting Mis-Printed Question" + e);
			// TODO: handle exception
		}
		finally{
			Connect.freeConnection(con);
		}
		return mapping.findForward("WrongQ");
	}
}