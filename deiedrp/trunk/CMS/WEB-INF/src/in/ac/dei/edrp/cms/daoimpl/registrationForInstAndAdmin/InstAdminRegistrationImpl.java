/*
 * @(#) InstAdminRegistrationImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.registrationForInstAndAdmin;

import in.ac.dei.edrp.cms.dao.registrationForInstAndAdmin.InstAdminRegistrationDAO;
import in.ac.dei.edrp.cms.daoimpl.employee.sendmail;
import in.ac.dei.edrp.cms.domain.mailConfiguration.MailConfigurationDomain;
import in.ac.dei.edrp.cms.domain.registrationForInstAndAdmin.InstAdminRegistrationInfo;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * This file consist of the methods used for setting up the institute admin registration 
 * setup.
 * 
 * @author Upasana 
 * @date 21	November 2012
 * @version 1.0
 */
public class InstAdminRegistrationImpl extends SqlMapClientDaoSupport implements InstAdminRegistrationDAO{

	TransactionTemplate transactionTemplate = null;
	private Logger loggerObject = Logger.getLogger(InstAdminRegistrationImpl.class);
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	String sep = System.getProperty("file.separator");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
			+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
			+ "databasesetting" + sep + "MessageProperties", new Locale("en",
			"US"));
	public String setInstituteAdminProfile(final InstAdminRegistrationInfo input) {
		
		InstAdminRegistrationInfo registrationInfoGetter = new InstAdminRegistrationInfo();
		InstAdminRegistrationInfo registrationInfoGetter1 = new InstAdminRegistrationInfo();
		String bool = "false";
	
		try {
			registrationInfoGetter = (InstAdminRegistrationInfo) getSqlMapClient().queryForObject("instAdminRegistration.getduplicaterecords", input);
			registrationInfoGetter1 = (InstAdminRegistrationInfo) getSqlMapClient().queryForObject("instAdminRegistration.getduplicateuser", input);
		} catch (SQLException e1) {
			System.out.println("error caught");
			e1.printStackTrace();
		}
			
		if(registrationInfoGetter.getCount().equalsIgnoreCase("0")){
			if(registrationInfoGetter1.getCount().equalsIgnoreCase("0")){
				String b;
				
				try {
					 
					getSqlMapClientTemplate().insert("instAdminRegistration.setInstituteAdmin",input);
					b = "true";
				
					try {
						String msg = "<h2 align='center'>Welcome To Institute Administrator Registration</h2><br>Your Account Information is as follows:<br><b>User Name:</b>"
								+ input.getPrimaryEmailId()
								+ "<br><b>Password:</b>" + input.getPassword();
						msg = msg
						+ "<br><br><br><a href='"
						+ resourceBundle.getString("url")
						+ "/registrationForInstAndAdmin/activateAccount.htm?userId="
						+ input.getUserId()
						+"&instituteName="+input.getInstituteName()
						+"&instituteNickName="+input.getInstituteNickName()
						+"&city="+input.getCity()
						+"&state="+input.getState()
						+"&country="+input.getCountry()
						+"&adminEmail="+input.getPrimaryEmailId()
						+ "&asahs=asnasa&dssssss=%ab$$gfff'>Click Here to Confirm the request</a>";
						main(msg,
								input.getPrimaryEmailId(),
								"User Account Details");
					} catch (Exception e) {
						logger.error(e.getMessage());
						System.out.println("error in mail "+e);
					}
				} catch (Exception e) {
					loggerObject.info("error caught..." + e);
					b = "false"+e;
				}
				return b;
			}
			else{
				return "duplicateuser";
			}
		}else{
			return "duplicate";
		}
		
	}
	
	public Integer updateRequestStatus(InstAdminRegistrationInfo input) {
		int i = 0;
		try {
			i = getSqlMapClientTemplate().update("instAdminRegistration.updateUserStatus",input);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<InstAdminRegistrationInfo> getAdminInstituteDetails(
			InstAdminRegistrationInfo input) {
		List<InstAdminRegistrationInfo> detaillist;
		detaillist = getSqlMapClientTemplate().queryForList("instAdminRegistration.selectAdminInstituteDetails");

		return detaillist;
	}

	
	public Integer updateAdminInstituteDetails(InstAdminRegistrationInfo input) {
		
		int i = 0;
		try {
			if(input.getStatus().equalsIgnoreCase("REJ")){
				i = getSqlMapClientTemplate().update("instAdminRegistration.updateInstituteStatus",input);
			}
			else{
				getSqlMapClientTemplate().delete("instAdminRegistration.deleteInstituteAdmin",input);
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
		return i;
	}

	public String insertIntoUniversityUserDetails(final InstAdminRegistrationInfo input) {
		InstAdminRegistrationInfo registrationInfoGetter = new InstAdminRegistrationInfo();
		InstAdminRegistrationInfo registrationInfoGetter1 = new InstAdminRegistrationInfo();
		String bool = "false";
		try{
			registrationInfoGetter = (InstAdminRegistrationInfo) getSqlMapClient().queryForObject("instAdminRegistration.getDuplicateUniversityRecords", input);
			registrationInfoGetter1 = (InstAdminRegistrationInfo) getSqlMapClient().queryForObject("instAdminRegistration.getDuplicateUserInUserInfo", input);
		}
		catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		
		if(registrationInfoGetter.getCount().equalsIgnoreCase("0")){
			if(registrationInfoGetter1.getCount().equalsIgnoreCase("0")){
				final InstAdminRegistrationInfo registrationInfo = (InstAdminRegistrationInfo) getSqlMapClientTemplate().queryForObject("instAdminRegistration.getuniversityId",input);
				
				try {
					bool = (String) transactionTemplate.execute(new TransactionCallback() {
						public String doInTransaction(TransactionStatus transaction) {
							String b = null;
							String uniCode;
							List menuList = null;
							/*
							 * to create a point to which the function revert
							 * when an exception is encountered during the
							 * process
							 */
							Object savepoint = null;
							try {
								
								savepoint = transaction.createSavepoint();
								int value = Integer.parseInt(registrationInfo.getCount()) + 1;
								uniCode = String.format("%04d", value);
								input.setUniversityCode(uniCode);
								
								getSqlMapClientTemplate().insert("instAdminRegistration.setUniversityMaster",input);
								
								menuList = getSqlMapClientTemplate().queryForList("unirolesetup.getmenulists");
								Iterator iterator = menuList.iterator();
								
								while (iterator.hasNext()) {
									UnivRoleInfoGetter object = (UnivRoleInfoGetter) iterator.next();
									
									input.setComponentId(object.getComponentId());	
									input.setComponentDescription(object.getComponentDescription());
									input.setGroupCode(object.getGroupCode());//used for dummy flag
									input.setApplicationId(object.getApplicationId());
									
									getSqlMapClientTemplate().insert("instAdminRegistration.populateMenusInSYS2",input);
									if(object.getGroupCode().equalsIgnoreCase("1")){
										getSqlMapClientTemplate().insert("instAdminRegistration.insertdefaultitems",input);
									}
									
								}
								getSqlMapClientTemplate().insert("instAdminRegistration.insertdefrole",input);
								getSqlMapClientTemplate().insert("instAdminRegistration.setUserInfo",input);
								getSqlMapClientTemplate().update("instAdminRegistration.updateInstituteStatus",input);
								getSqlMapClientTemplate().insert("instAdminRegistration.initializeUniversity", input);
								b = "true";
							}
							catch (Exception e) {
								loggerObject.info("connection rollback..." + e);
								transaction.rollbackToSavepoint(savepoint);
								b = "false"+e;
							}
							if(b.equalsIgnoreCase("true")){
								InstAdminRegistrationInfo pass=(InstAdminRegistrationInfo) getSqlMapClientTemplate().queryForObject("instAdminRegistration.getPasswordUser",input);
								
								input.setPassword(pass.getPassword());
								try {
									String msg = "<h2 align='center'>Welcome To Institute Administrator Registration</h2><br>Your account has been created, please login with given details:<br><b>User Name:</b>"
											+ input.getPrimaryEmailId()
											+ "<br><b>Password:</b>" + input.getPassword();
									
									main(msg,
											input.getPrimaryEmailId(),
											"User Account Details");
								} catch (Exception e) {
									logger.error(e.getMessage());
									System.out.println("error in mail "+e);
								}
							}
							return b;
						}
					});
				} catch (Exception e) {
					loggerObject.info("error caught..." + e);
					e.getMessage(); 
				}
				
			}
			else{
				return "duplicateuserinfo";
			}
		}else{
			return "duplicateunivesity";
		}
		return bool;
		
	}
	
	
	 public void main(String text, String to, String subject)
     throws Exception {
 	
 	 String mailTo = to;
     
     Properties properties = System.getProperties();
     
     Logger loggerObject = Logger.getLogger(sendmail.class);

     properties.setProperty("mail.smtp.port", "465");
     properties.setProperty("mail.smtp.socketFactory.port", "465"); 
     properties.setProperty("mail.smtp.host", "smtp.gmail.com");
     properties.setProperty("mail.smtp.startssl.enable","true");
     properties.setProperty("mail.smtp.starttls.enable", "true");
     properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     properties.setProperty("mail.smtp.auth", "true");
     properties.put("mail.store.protocol", "pop3");//for incoming mail
     properties.put("mail.transport.protocol", "smtp");//for outgoing mail

     final String username = resourceBundle.getString("emailId");
     final String password = resourceBundle.getString("password");
     
     Session smtpSession = Session.getDefaultInstance(properties, 
             new Authenticator(){
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }});
     
     smtpSession.setDebug(false);  
     String mailFrom=username;
     MimeMessage message = new MimeMessage(smtpSession);  
     message.setFrom(new InternetAddress(mailFrom)); 
     message.setRecipients(Message.RecipientType.TO, 
             InternetAddress.parse(mailTo,false));
     
     final String encoding = "UTF-8";  
     
     message.setSubject(subject, encoding);  
     message.setText(text, encoding); 
     message.setContent(text,"text/html");
     message.setSentDate(new Date());
     
     
     System.out.println("Sending Message......");
     Transport.send(message);
     System.out.println("Message Sent.......");
     
     loggerObject.info("Message Sent.....");
 }
}
