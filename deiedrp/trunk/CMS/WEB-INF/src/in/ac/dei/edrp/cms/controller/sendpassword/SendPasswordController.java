/**
 * @(#) SendPasswordController.java
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
 * Redistribution in binary form must reproducuce the above copyright
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
package in.ac.dei.edrp.cms.controller.sendpassword;

import in.ac.dei.edrp.cms.dao.sendpassword.SendPasswordService;
import in.ac.dei.edrp.cms.domain.sendpassword.SendPasswordInfoGetter;
import in.ac.dei.edrp.cms.utility.TransferInST;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for send password in email
 * 
 * @version 1.0 12 AUGUST 2011
 * @author MOHD AMIR
 */
public class SendPasswordController extends MultiActionController {
	
	private static Logger logObj = Logger.getLogger(SendPasswordController.class);
	
	/** creating object of sendPasswordService interface */
	private SendPasswordService sendPasswordService;

	/** defining setter method for object of interface */
	public void setSendPasswordService(SendPasswordService sendPasswordService) {
		this.sendPasswordService = sendPasswordService;
	}

	/**
	 * This method email to users
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing send email info
	 */
	public ModelAndView sendEmail(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		List<SendPasswordInfoGetter> li = sendPasswordService.getUserList(session.getAttribute("universityId").toString());
		String fileName = this.getServletContext().getRealPath("/")
				+ "StudentPassword.xls";
		System.out.println(fileName+li.size());
		Boolean bool = true;
		try {
			WritableWorkbook workbook;
			try {
				workbook = Workbook.createWorkbook(new File(fileName),
						Workbook.getWorkbook(new File(fileName)));
			} catch (Exception fnfe) {
				workbook = Workbook.createWorkbook(new File(fileName));
			}
			WritableSheet sheet;
			if(workbook.getNumberOfSheets()>0)
			{
				sheet= workbook.getSheet(0);
			}else
			{
				sheet= workbook.createSheet("Student Password List",0);
			}
			int r=sheet.getRows();
			for (int i = 0; i < li.size(); i++) {
				String password = TransferInST.generatePassword(4, 8);
				li.get(i).setPassword(password);
				li.get(i).setUniversityId(li.get(i).getUserId().substring(1, 5));
				
				Label label = new Label(0,r+i , li.get(i).getEntity());
				sheet.addCell(label);
				label = new Label(1,r+i ,li.get(i).getProgram());
				sheet.addCell(label);
				label = new Label(2,r+i ,li.get(i).getBranch());
				sheet.addCell(label);
				label = new Label(3,r+i ,li.get(i).getSpecialization());
				sheet.addCell(label);
				label = new Label(4,r+i , li.get(i).getUserName());
				sheet.addCell(label);
				label = new Label(5,r+i ,li.get(i).getPassword());
				sheet.addCell(label);
				// Application Added By Dheeraj on 7/2/2012
				li.get(i).setApplication(session.getAttribute("application").toString());
				bool = sendPasswordService.setApplicantUserInfo(li.get(i))
						&& bool;
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}

		if (bool) {
			return new ModelAndView("sendpassword/accountInfo", "info",
					"Mail Sent successfully.|StudentPassword.xls");
		} else {
			return new ModelAndView("sendpassword/accountInfo", "info",
					"Mail Not Sent.|StudentPassword.xls");
		}
	}

	/**
	 * This method activate the account for user
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView activation info
	 */
	public ModelAndView activateAccount(HttpServletRequest request,
			HttpServletResponse response) {
		SendPasswordInfoGetter sendPasswordInfoGetter = new SendPasswordInfoGetter();
		sendPasswordInfoGetter.setUniversityId(request.getParameter("UI"));
		sendPasswordInfoGetter.setUserId(request.getParameter("userId"));
		sendPasswordInfoGetter.setAIorUI(request.getParameter("AIUI"));
		Integer i = sendPasswordService
				.updateAIUIStatus(sendPasswordInfoGetter);
		if (i > 0) {
			return new ModelAndView("sendpassword/accountInfo", "info",
					"Account activated successfully.");
		} else {
			return new ModelAndView("sendpassword/accountInfo", "info",
					"Page Expired.");
		}
	}

	public ModelAndView resetCounter(HttpServletRequest request,
			HttpServletResponse response) {
		String reqNo = request.getParameter("requestNo");
		reqNo = sendPasswordService.resetCounter(reqNo);
		return new ModelAndView("sendpassword/accountInfo", "info", reqNo);
	}

}
