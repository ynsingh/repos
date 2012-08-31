/*
 * @(#) AccountLoginEDEIImpl.java
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


import in.ac.dei.edrp.client.applicantAccountEDEI.AccountLoginEDEI;
import in.ac.dei.edrp.client.applicantAccountEDEI.ApplicantAccountEDEIBean;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @version 1.0 9 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 */

@SuppressWarnings("serial")
public class AccountLoginEDEIImpl extends RemoteServiceServlet implements AccountLoginEDEI {

	SqlMapClient client = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();
    
	@SuppressWarnings("unchecked")
	public Integer getLogInConfirmation(String userName, String password) {
		int flag=0;
		ApplicantAccountEDEIBean accountBean=new ApplicantAccountEDEIBean();
		List<ApplicantAccountEDEIBean> count;
		try{
			accountBean.setEmail(userName);
			accountBean.setPassword(password);
			count=client.queryForList("getLogInConfirmationEDEI", accountBean);
			flag=Integer.parseInt(count.get(0).getCount());
		}
		catch(Exception ex){
			System.out.println("getLogInConfirmation "+ex.getStackTrace()+" "+ex);
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public String getUserNameForAccount(String email) {
		String name = null;
		List<ApplicantAccountEDEIBean> listDetail=null;
		try{
			listDetail=client.queryForList("getUserNameForAccountEDEI",email);
			name=listDetail.get(0).getFirstName()+" "+listDetail.get(0).getLastName();
		}
		catch(Exception ex){
			System.out.println("getUserNameForAccount "+ex.getStackTrace()+" "+ex);
		}
		return name;
	}

	@SuppressWarnings("unchecked")
	public List<ApplicantAccountEDEIBean> getUniversityDetailsDEINew(String uniNickName) {
		
		List<ApplicantAccountEDEIBean> listDetail=null;
		try{
			listDetail=client.queryForList("getUniversityDetailDEINewEDEI",uniNickName);
		}
		catch(Exception ex){
			System.out.println("getUniversityDetailDEINew "+ex.getStackTrace()+" "+ex);
		}
		return listDetail;
	}
}
