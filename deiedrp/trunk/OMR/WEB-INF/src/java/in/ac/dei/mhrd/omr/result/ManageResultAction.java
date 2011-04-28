/*
 * @(#) ManageResultAction.java
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
package in.ac.dei.mhrd.omr.result;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import in.ac.dei.mhrd.omr.SelectTestId;
import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This class updates the duration for which result will be displayed
 * 
 * MyEclipse Struts Creation date: 12-02-2010
 * @version 1.0
 * @author Anshul Agarwal XDoclet definition:
 * @struts.action path="/manageResult" name="resultSchedule"
 *                input="/manageResult.jsp" scope="request" validate="true"
 * 
 * 
 */
public class ManageResultAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward This method updates the ResultDisplayedFrom and
	 *         ResultDisplayedTo fields of the testheader table
	 */
	private static Logger log = Logger.getLogger(ManageResultAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ResultManageForm resultSchedule = (ResultManageForm) form;// TODO
																	// Auto-generated
																	// method
																	// stub
		System.out.println("dcfds");
		int testid = SelectTestId.getTestId(resultSchedule.getTestName());
		System.out.println("from : " + resultSchedule.getTestName());
		Connection con = null;
		PreparedStatement psUpdateResultPeriod = null;
		//ActionErrors errors = new ActionErrors();
		//ActionMessage msg = new ActionMessage("errors.resultLastDate");
		//MessageResources msgResrc = getResources(request);
		//errors = new ActionMessage(errors.resultLastDate);
		Locale obj = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle(
				"in//ac//dei//mhrd//omr//ApplicationResources", obj);

		if (isCancelled(request)) {
			return mapping.findForward("home");
		}

		try {
			/*
			 * Converts date into timestamp
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date ResultDisplayedFrom = sdf.parse(resultSchedule
					.getResultFrom());
			java.util.Date ResultDisplayedTo = sdf.parse(resultSchedule
					.getResultTo());
			java.util.Date ResultDisplayedLAst = sdf.parse(resultSchedule.getLastResultDate());
                System.out.println("jhds " + ResultDisplayedTo.compareTo(ResultDisplayedLAst));
//			if(ResultDisplayedTo.compareTo(ResultDisplayedLAst)>0){
//				errors.add("error", new ActionMessage("errors.resultLastDate", "anshul", "gfg"));
//				saveErrors(request, errors);
//				
//				return mapping.findForward("samePage");
//				
//			}
			
			java.sql.Timestamp timest = new java.sql.Timestamp(
					ResultDisplayedFrom.getTime());

			con = Connect.prepareConnection();
			con.setAutoCommit(false);
			psUpdateResultPeriod = con
					.prepareStatement("Update testheader set ResultDisplayedFrom=?, ResultDisplayedTo=? where TestId=?");
			psUpdateResultPeriod.setTimestamp(1, new java.sql.Timestamp(
					ResultDisplayedFrom.getTime()));
			psUpdateResultPeriod.setTimestamp(2, new java.sql.Timestamp(
					ResultDisplayedTo.getTime()));
			psUpdateResultPeriod.setInt(3, testid);
			int checkUpdate = psUpdateResultPeriod.executeUpdate();
			con.commit();
			// con.close();

			/*
			 * check whether testheader table updated successfully or not
			 */
			if (checkUpdate >= 1) {
				request.setAttribute("confirmAnsMsg", message
						.getString("msg.updateResultDuration"));
				log.info("Result duration updated");

			} else {
				request.setAttribute("confirmAnsMsg", message
						.getString("error.updateResultDuration"));
				log.error("Result duration cannot updated");

			}

		} catch (Exception e) {
			log.error("Error while updating result duration : " + e);
			// TODO: handle exception
		} finally {
			System.out.println("inside ManageResult Action final");
			Connect.freeConnection(con);
		}
		return mapping.findForward("success1");
	}
}