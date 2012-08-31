/*
 * @(#) ApplicantAccountImpl.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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

package in.ac.dei.edrp.server;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;
import in.ac.dei.edrp.client.EdeiAdmission.EDEIStudentBean;
import in.ac.dei.edrp.client.applicantAccount.ApplicantAccount;
import in.ac.dei.edrp.client.applicantAccount.ApplicantAccountBean;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;
import in.ac.dei.edrp.server.summarysheet.SendEmail;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

@SuppressWarnings("serial")
public class ApplicantAccountImpl extends RemoteServiceServlet implements ApplicantAccount {

	SqlMapClient client = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();
    
	
	@SuppressWarnings({
        "static-access", "deprecation"
    })
	public String insertAccountdetails(ApplicantAccountBean accountInfo) {
		try{
			accountInfo.setDob(new SimpleDateFormat("yyyy-MM-dd").format(
	                new Date(accountInfo.getDob())));
			client.insert("applicantInfoSetup",accountInfo);
			
		}
		catch(Exception ex){
			System.out.println(ex.getStackTrace()+" "+ex.getMessage());
			
		}
		return null;
	}

	public boolean performSignup(String userCaptcha) {
		try{
			HttpServletRequest request = getThreadLocalRequest();
			
			HttpSession session = request.getSession();
	
			Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
			return captcha.isCorrect(userCaptcha);
		}
		catch(Exception ex){
			System.out.println(ex.getStackTrace());
		}
		return false;
		//return captcha.isCorrect(userCaptcha);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer checkEmailDuplicacy(ApplicantAccountBean accountInfo) {
		
		List<ApplicantAccountBean> count;
		int flag=0;
		try{
			count=client.queryForList("duplicateEmail", accountInfo);
			flag=Integer.parseInt(count.get(0).getCount());
		}
		catch(Exception ex){
			System.out.println("checkEmailDuplicacy "+ex.getStackTrace()+" "+ex);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public Integer getLogInConfirmation(String userName, String password) {
		int flag=0;
		ApplicantAccountBean accountBean=new ApplicantAccountBean();
		List<ApplicantAccountBean> count;
		try{
			accountBean.setEmail(userName);
			accountBean.setPassword(password);
			count=client.queryForList("getLogInConfirmation", accountBean);
			flag=Integer.parseInt(count.get(0).getCount());
		}
		catch(Exception ex){
			System.out.println("getLogInConfirmation "+ex.getStackTrace()+" "+ex);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<ApplicantAccountBean> getUniversitiesToApply() {
		List<ApplicantAccountBean> accountBean = null;
		try{
			accountBean=client.queryForList("getUniversitiesToApply");
		}
		catch(Exception ex){
			System.out.println("getUniversitiesToApply "+ex.getStackTrace()+" "+ex);
		}

		return accountBean;
	}

	@SuppressWarnings("unchecked")
	public Integer changeApplicantPassword(String userName,String userId, String oldPass,
			String newPass) {
		ApplicantAccountBean accountInfo = new ApplicantAccountBean();
		List<ApplicantAccountBean> count;
		int i=0;
		
		try{
			accountInfo.setEmail(userName);
			accountInfo.setPassword(oldPass);
			accountInfo.setUserId(userId);
			count=client.queryForList("applicantPasswordDetails",accountInfo);
			
			if(Integer.parseInt(count.get(0).getCount())>0){
				
				accountInfo.setNewPassword(newPass);
				client.insert("changeApplicantPassword",accountInfo);
				i++;
			}
			
		}
		catch(Exception ex){
			System.out.println("changeApplicantPassword "+ex.getStackTrace()+" "+ex);
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public String getUserNameForAccount(String email) {
		String name = null;
		List<ApplicantAccountBean> listDetail=null;
		try{
			listDetail=client.queryForList("getUserNameForAccount",email);
			name=listDetail.get(0).getFirstName()+" "+listDetail.get(0).getLastName();
		}
		catch(Exception ex){
			System.out.println("getUserNameForAccount "+ex.getStackTrace()+" "+ex);
		}
		return name;
	}

	
	@SuppressWarnings("unchecked")
	public List<ApplicantAccountBean> getAppliedProgramList(String userEmailId) {
		List<ApplicantAccountBean> programList=null;
		try{
			programList=client.queryForList("getAppliedProgramList",userEmailId);
		}
		catch(Exception ex){
			System.out.println("getAppliedProgramList "+ex.getStackTrace()+" "+ex);
		}
		
		return programList;
	}

	@SuppressWarnings("static-access")
	public String sendEmailAccount(ApplicantAccountBean accountInfo) {
		try {
            String message = "<h2 align='center'>Registered Successfully</h2><br>" +
                "Dear "+accountInfo.getFirstName()+",<br>" +
                "You have successfully registered. Your registration details are as follows...<br>" +
                "<b>Full Name</b>: " + accountInfo.getFirstName()+" "+accountInfo.getLastName()+"<br>"+
                "<b>User Name</b>: " + accountInfo.getEmail()+ "<br>" +
                "<b>Password</b>: " + accountInfo.getPassword()+ "<br>" 
               ;
            System.out.println(message);
            new SendEmail().send(accountInfo.getEmail(),"Registered Successfully", message);
            System.out.println("after send mail");
        } catch (Exception mailexc) {
            System.out.println("Mail exception=" + mailexc.getStackTrace());
        }
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ApplicantAccountBean> getAccountDetails(String userEmailId) {
		List<ApplicantAccountBean> appBean=new ArrayList<ApplicantAccountBean>();
		ApplicantAccountBean appObj=new ApplicantAccountBean();
		try{
			appObj.setEmail(userEmailId);
			
			appBean=client.queryForList("getAccountDetails", appObj);
		}
		catch(Exception e){
			System.out.println("Exception"+e);
		}
		return appBean;
	}

	@SuppressWarnings("deprecation")
	public String updateAccountdetails(ApplicantAccountBean accountInfo) {
		try{
		accountInfo.setDob(new SimpleDateFormat("yyyy-MM-dd").format(
                new Date(accountInfo.getDob())));
		client.update("updateAccountdetails", accountInfo);
		}
		
		catch(Exception e){
			System.out.println("Exception "+e);
		}
		return null;
	}

	@Override
	public List<EDEIStudentBean> getAvailableModule(EDEIStudentBean bean) {
		List<EDEIStudentBean>list=null;
		try{
			list=client.queryForList("getAvaiableModuleForExistingProgram", bean);			
		}
		catch(Exception e){
			logObj.logger.error("Error inside getAvailableModule in class ApplicationAccountImpl during getting Avaiable Modules : "+e);
		}
		return list;
	}

	@Override
	public List<EDEIStudentBean> getExistingProgramDetail(EDEIStudentBean bean) {
		List<EDEIStudentBean>list=null;
		try{
			list=client.queryForList("getExistingProgramDetails", bean);			
		}
		catch(Exception e){
			logObj.logger.error("Error inside getExistingProgramDetail in class ApplicationAccountImpl during getting Existing program Detail : "+e);
		}
		return list;
	}
}
