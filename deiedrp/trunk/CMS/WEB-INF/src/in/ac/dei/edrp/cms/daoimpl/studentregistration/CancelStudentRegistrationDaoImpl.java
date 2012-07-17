/*
 * @(#) CancelStudentRegistrationDaoImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.studentregistration;

import in.ac.dei.edrp.cms.dao.studentregistration.CancelStudentRegistrationDao;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Manpreet
 * 
 */
public class CancelStudentRegistrationDaoImpl extends SqlMapClientDaoSupport
		implements CancelStudentRegistrationDao {
	private static Logger logObj = Logger
			.getLogger(StudentRegistrationFormImpl.class);
	TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * Method to fetch student record
	 * 
	 * @param input
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<StudentInfoGetter> getStudentDetails(String input) {

		List<StudentInfoGetter> resultList = getSqlMapClientTemplate()
				.queryForList("cancelRegistration.getDetails", input);
		return resultList;

	}

	/**
	 * Method to cancel student's registration
	 * 
	 * @param inputObj
	 * @return
	 */
	public String cancelRegistration(final StudentInfoGetter inputObj) {
		String message="failure";
		
		message=(String)transactionTemplate.execute(new TransactionCallback() {
			Object savepoint = null;
			String result="failure";
			public String doInTransaction(TransactionStatus status) {
				savepoint = status.createSavepoint();

				try {
					getSqlMapClientTemplate().update("cancelRegistration.updateTempStdProg", inputObj);
					getSqlMapClientTemplate().update("cancelRegistration.updateTempStdMaster", inputObj);
					getSqlMapClientTemplate().update("cancelRegistration.updateTempStdCourse", inputObj);
					
					getSqlMapClientTemplate().insert("cancelRegistration.insertTmpStdMasterHistory", inputObj);
					getSqlMapClientTemplate().insert("cancelRegistration.insertTmpStdProgHistory", inputObj);					
					getSqlMapClientTemplate().insert("cancelRegistration.insertTmpStdCourseHistory", inputObj);
					
					getSqlMapClientTemplate().delete("cancelRegistration.deleteTmpStdMaster", inputObj);
					getSqlMapClientTemplate().delete("cancelRegistration.deleteTmpStdProg", inputObj);
					getSqlMapClientTemplate().delete("cancelRegistration.deleteTmpStdCourse", inputObj);
					
					if (inputObj.getProcessedFlag().equalsIgnoreCase("yes")) {
						getSqlMapClientTemplate().update("cancelRegistration.updateStagingTable",inputObj);
						getSqlMapClientTemplate().delete("cancelRegistration.deleteStudVerifiStatus", inputObj);
						getSqlMapClientTemplate().delete("cancelRegistration.deleteAppInfo", inputObj);
					}
					getSqlMapClientTemplate().getDataSource().getConnection().commit();	
					result="success";
				} catch (Exception e) {
					logger.error(e);
					status.rollbackToSavepoint(savepoint);					
				}

				return result;

			}
		});
		
		return message;
	}

}
