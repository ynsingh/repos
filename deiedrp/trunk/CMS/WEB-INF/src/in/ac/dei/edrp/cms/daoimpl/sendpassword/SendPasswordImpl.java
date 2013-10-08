/**
 * @(#) SendPasswordImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.sendpassword;

import in.ac.dei.edrp.cms.dao.sendpassword.SendPasswordService;
import in.ac.dei.edrp.cms.daoimpl.employee.sendmail;
import in.ac.dei.edrp.cms.daoimpl.utility.ResetCodes;
import in.ac.dei.edrp.cms.domain.sendpassword.SendPasswordInfoGetter;
import in.ac.dei.edrp.cms.domain.utility.SystemValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * this is Server side Implementation class for send password
 *
 * @version 1.0 12 July 2011
 * @author MOHD AMIR
 */
public class SendPasswordImpl extends SqlMapClientDaoSupport implements
		SendPasswordService {
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(SendPasswordImpl.class);
	/** Creating object of TransactionTemplate for transaction Management */
	private TransactionTemplate transactionTemplate;
	String sep = System.getProperty("file.separator");

	ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
			+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
			+ "databasesetting" + sep + "MessageProperties", new Locale("en",
			"US"));

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * This method retrieves User List from database
	 *
	 * @return list containing User List
	 */
	@SuppressWarnings("unchecked")
	public List<SendPasswordInfoGetter> getUserList(String universityId) {
		List<SendPasswordInfoGetter> list = new ArrayList<SendPasswordInfoGetter>();
		try {
			list = getSqlMapClientTemplate().queryForList(
					"sendPassword.getUserList",universityId);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return list;
	}

	/**
	 * This method insert username /Password into database
	 *
	 * @param sendPasswordInfoGetter
	 *            Object of type SendPasswordInfoGetter
	 *
	 * @return s true/False
	 */
	public Boolean setApplicantUserInfo(
			final SendPasswordInfoGetter sendPasswordInfoGetter) {
		
		String s = (String) transactionTemplate
				.execute(new TransactionCallback() {
					Object savePoint = new Object();

					public String doInTransaction(TransactionStatus ts) {
						savePoint = ts.createSavepoint();
						//DS
						String universityId = sendPasswordInfoGetter.getUniversityId();
						try {
							if (sendPasswordInfoGetter.getAIorUI()
									.equalsIgnoreCase("AI")) {
								getSqlMapClientTemplate().insert(
										"sendPassword.setApplicantInfo",
										sendPasswordInfoGetter);
							} else {
								getSqlMapClientTemplate().insert(
										"sendPassword.setUserInfo",
										sendPasswordInfoGetter);
							}
							getSqlMapClientTemplate().delete(
									"sendPassword.deleteEmailEntry",
									sendPasswordInfoGetter);
							try {
								String msg = "<h2 align='center'>Welcome To Course Registration System</h2><br>Your Account Information is as follows:<br><b>User Name:</b>"
										+ sendPasswordInfoGetter.getUserName()
										+ "<br><b>Role:</b>"
										+ sendPasswordInfoGetter
												.getUserGroupName()
										+ "<br><b>Password:</b>" + sendPasswordInfoGetter.getPassword();
								msg = msg
										+ "<br><br><br><a href='"
										+ resourceBundle.getString("url")
										+ "/sendPassword/activateAccount.htm?userId="
										+ sendPasswordInfoGetter.getUserId()
										+ "&UI="
										+ sendPasswordInfoGetter
												.getUniversityId() + "&AIUI="
										+ sendPasswordInfoGetter.getAIorUI()
										+ "&asahs=asnasa&dssssss=%ab$$gfff'>Click Here to Activate Account</a>";
								
								//DS
								sendmail.main(msg,
										sendPasswordInfoGetter.getEmailId(),
										"User Account Details", universityId);
							} catch (Exception e) {
								logObj.error(e.getMessage());
							}
							getSqlMapClientTemplate().getDataSource()
									.getConnection().commit();
							return "true";
						} catch (Exception e) {
							ts.rollbackToSavepoint(savePoint);
							logObj.error(e.getMessage());
							return "false";
						}
					}
				});
		return new Boolean(s);
	}

	/**
	 * This method update User Status in to database
	 *
	 * @param sendPasswordInfoGetter
	 *            Object of type SendPasswordInfoGetter
	 *
	 * @return i, 0/1
	 */
	public Integer updateAIUIStatus(
			SendPasswordInfoGetter sendPasswordInfoGetter) {
		int i = 0;
		try {
			if (sendPasswordInfoGetter.getAIorUI().equalsIgnoreCase("AI")) {
				i = getSqlMapClientTemplate().update(
						"sendPassword.updateApplicantStatus",
						sendPasswordInfoGetter);
			} else {
				i = getSqlMapClientTemplate()
						.update("sendPassword.updateUserStatus",
								sendPasswordInfoGetter);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logObj.error(e.getMessage());
		}
		return i;
	}

	public String resetCounter(String reqNo) {
		ResetCodes resetCodes = new ResetCodes(getSqlMapClient(),
				transactionTemplate);
		String result = "";
		System.out.println(reqNo);
		if (reqNo.equalsIgnoreCase("1")) {
			/*
			 * reset system values
			 */
			resetCodes.resetSystemValues(new SystemValue());
		} else if (reqNo.equalsIgnoreCase("2")) {

			/*
			 * clear temporary tables
			 */
			resetCodes.clearTempTables(new SystemValue());

		} else if (reqNo.equalsIgnoreCase("3")) {
			/*
			 * semester processing control
			 */
			resetCodes.semesterProcessingControl(new SystemValue());
		}
		return result;
	}
}