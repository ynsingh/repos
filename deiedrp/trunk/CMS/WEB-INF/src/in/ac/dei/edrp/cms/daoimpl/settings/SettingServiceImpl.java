/**
 * @(#) SettingServiceImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.settings;

import in.ac.dei.edrp.cms.dao.settings.SettingService;
import in.ac.dei.edrp.cms.domain.login.Login;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * this is Server side Implementation class for Settings
 * 
 * @version 1.0 12 July 2011
 * @author MOHD AMIR
 */
public class SettingServiceImpl extends SqlMapClientDaoSupport implements
		SettingService {
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(SettingServiceImpl.class);

	/**
	 * This method retrieves user address from database
	 * 
	 * @param studentId
	 *            , user id of student
	 * @return addressData containing user address
	 */
	@SuppressWarnings("unchecked")
	public List<StudentMasterInfoBean> getUserAddress(String studentId) {
		List<StudentMasterInfoBean> addressData = new ArrayList<StudentMasterInfoBean>();
		try {
			addressData = getSqlMapClientTemplate().queryForList(
					"addStduentInMaster.getStudentAddressDetail", studentId);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return addressData;
	}

	/**
	 * This method change address Details into database
	 * 
	 * @param studentMasterInfoBean
	 *            list of type StudentMasterInfoBean
	 * @return bool , True/False
	 */
	public Boolean changeUserAddress(
			List<StudentMasterInfoBean> studentMasterInfoBean) {
		Boolean bool = false;
		try {
			for (int i = 0; i < studentMasterInfoBean.size(); i++) {
				int j = getSqlMapClientTemplate().update(
						"addStduentInMaster.updateaddressmaster",
						studentMasterInfoBean.get(i));

				if (j == 0) {
					getSqlMapClientTemplate().update(
							"addStduentInMaster.insertaddressmaster",
							studentMasterInfoBean.get(i));
				}
			}
			bool = true;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return bool;
	}

	/**
	 * This method change password Details into database
	 * 
	 * @param login
	 *            Object of class Login
	 * @return bool , True/False
	 */
	public Boolean changeUserPassword(Login login) {
		Boolean bool = false;
		try {
			getSqlMapClientTemplate().update("login.changePassword", login);
			bool = true;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return bool;
	}
}