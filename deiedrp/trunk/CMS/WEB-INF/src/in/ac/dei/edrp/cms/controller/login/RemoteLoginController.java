/**
 * @(#) RemoteLoginController.java
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
package in.ac.dei.edrp.cms.controller.login;

import in.ac.dei.edrp.cms.dao.login.RemoteLoginDao;
import in.ac.dei.edrp.cms.domain.login.Login;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Remote Login
 * 
 * @version 1.0 24 May 2012
 * @author Nupur Dixit
 */
public class RemoteLoginController extends MultiActionController {

	/** creating object of LoginService interface */
	private RemoteLoginDao remoteLoginDao;

	/** defining setter method for object of LoginService interface */
	public void setRemoteLoginDao(RemoteLoginDao remoteLoginDao) {
		this.remoteLoginDao = remoteLoginDao;
	}
	
	public ModelAndView remoteLogin(HttpServletRequest request,HttpServletResponse response) {		
		return new ModelAndView("login/remoteLogin");
	}

	/**
	 * This method is used for getting response of brihaspati server for this remote login user
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing login user details details
	 */
	public ModelAndView getRemoteLoginDetails(HttpServletRequest request,HttpServletResponse response) {
		String emailId = request.getParameter("emailId");
		String successUrl = request.getParameter("successUrl");
		String sourceId="dei_admission";
		String resp=RemoteAuth.AuthR(emailId,successUrl,sourceId);
		System.out.println("response from brihaspati :"+resp);
		return new ModelAndView("login/remoteLoginResult", "resp", resp);
	}
	
	/**
	 * This method is used to check the authenticity on the local server if brihaspati server returns a success msg
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing success/failure details
	 */
	public ModelAndView returnAuthenticate(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String encriptData = request.getParameter("encd");
		String randomStr = request.getParameter("rand");
		String hashCode = request.getParameter("hash");
		if(encriptData==null || randomStr==null|| hashCode==null){
			return new ModelAndView("login/remoteLoginResult", "msg", "not a valid user");
		}
//		get security key
		String hdir=System.getProperty("user.home");
		String path=hdir+File.separator+"remote_auth"+File.separator+"brihaspati3-remote-access.properties";
		String filePath = hdir+File.separator+"remote_auth"+File.separator+"remote-user.txt";
		String line=ReadNWriteInTxt.readLin(path,"dei_admission");
		String skey=StringUtils.substringBetween(line,";",";");
		String serverurl=StringUtils.substringAfterLast(line,";");
//		decript received data
		String enUrl=EncrptDecrpt.decrypt(encriptData, "dei_admission");
//		get email and session from enUrl
		String arrEncUrl[] = enUrl.split("&");
		String email = StringUtils.substringAfter(arrEncUrl[0], "=");
		String sess = StringUtils.substringAfter(arrEncUrl[1], "=");
		String chkCode = email+";"+sess;
		String hashcode=EncrptDecrpt.keyedHash(email,randomStr,skey);
		if(hashCode.equalsIgnoreCase(hashcode)){
//			verify the email and session from remote_user.txt file
			boolean verified=ReadNWriteInTxt.readF(filePath, chkCode);
			if(verified){
				session.setAttribute("emailId", email);
				session.removeAttribute("msg");
				return new ModelAndView("login/remoteLoginResult", "email", email);
			}
			else{
				session.setAttribute("msg", "illegal user");
				session.removeAttribute("emailId");
				return new ModelAndView("login/remoteLoginResult", "msg", "not a valid user");
			}
		}
		else{
			session.setAttribute("msg", "illegal user");
			session.removeAttribute("emailId");
			return new ModelAndView("login/remoteLoginResult", "msg", "not a valid user");
		}	
	}
	
	/**
	 * This method is used to get the role/group of the authorize user
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing login details of the login user
	 */
	public ModelAndView getRole(HttpServletRequest request,HttpServletResponse response) {	
		HttpSession session = request.getSession(true);
		Login login = new Login();
		login.setUserId(request.getParameter("emailId"));
		List<Login> loginDetails = remoteLoginDao.getLoginDetails(login);
		if (loginDetails.size() > 0) {
			login.setUserId(loginDetails.get(0).getUserId());
			login.setApplication(loginDetails.get(0).getApplication());
			login.setUniversityId(loginDetails.get(0).getUniversityId());
			login.setUserGroupId(loginDetails.get(0).getUserGroupId());
			int i = remoteLoginDao.updateLastLogin(login);
			if (i > 0) {
				session.setAttribute("userName", loginDetails.get(0).getUserName());
				session.setAttribute("userId", loginDetails.get(0).getUserId());
				session.setAttribute("universityId", loginDetails.get(0).getUniversityId());
				session.setAttribute("universityName", loginDetails.get(0).getUniversityName());
				session.setAttribute("startDate", loginDetails.get(0).getStartDate());
				session.setAttribute("endDate", loginDetails.get(0).getEndDate());
				session.setAttribute("nickName", loginDetails.get(0).getNickName());
				session.setAttribute("address", loginDetails.get(0).getAddress());
				session.setAttribute("city", loginDetails.get(0).getCity());
				session.setAttribute("state", loginDetails.get(0).getState());
				session.setAttribute("pin", loginDetails.get(0).getPinCode());
				session.setAttribute("phone", loginDetails.get(0).getPhone());
				session.setAttribute("otherPhone", loginDetails.get(0).getOtherPhone());
				session.setAttribute("fax", loginDetails.get(0).getFax());
				session.setAttribute("country", loginDetails.get(0).getCountry());
				session.setAttribute("userGroupId", loginDetails.get(0).getUserGroupId());
				session.setAttribute("userGroupName", loginDetails.get(0).getUserGroupName());
			}
		}
		return new ModelAndView("login/remoteLoginInfo", "loginDetails", loginDetails);
	}
}
