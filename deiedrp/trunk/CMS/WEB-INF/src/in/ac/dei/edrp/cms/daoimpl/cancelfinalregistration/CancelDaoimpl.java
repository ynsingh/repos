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
package in.ac.dei.edrp.cms.daoimpl.cancelfinalregistration;

import in.ac.dei.edrp.cms.dao.cancelfinalregistration.*;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Ashish Mohan
 * 
 */
public class CancelDaoimpl extends SqlMapClientDaoSupport implements CancelDao {
	private static Logger logObj = Logger.getLogger(CancelDaoimpl.class);
	TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * Method to fetch student record
	 * @author Ashish Mohan
	 * @param input
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<StudentInfoGetter> getStudentDetails(String input) {

		List<StudentInfoGetter> resultList = getSqlMapClientTemplate().queryForList("cancel.getDetails", input);
		return resultList;

	}

	/**
	 * Method to cancel student's registration
	 * @author Ashish Mohan
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

				try {	// for terminate
						if(inputObj.getProcessedFlag().equalsIgnoreCase("TRM")){
							getSqlMapClientTemplate().update("cancel.updateTerSRSH",inputObj);
							getSqlMapClientTemplate().update("cancel.updateTerStdCourse", inputObj);
							getSqlMapClientTemplate().update("cancel.updateTerStdProg", inputObj);
							getSqlMapClientTemplate().update("cancel.updateTerStdMaster", inputObj);
						}
						
						// for withdraw semester
						else if(inputObj.getProcessedFlag().equalsIgnoreCase("WDR")){
							getSqlMapClientTemplate().update("cancel.updateWthSRSH",inputObj);
							getSqlMapClientTemplate().update("cancel.updateWthStdCourse", inputObj);
							getSqlMapClientTemplate().update("cancel.updateWthSRSHPreviousRem", inputObj);
							getSqlMapClientTemplate().update("cancel.updateWthSRSHPreviousPass", inputObj);
							getSqlMapClientTemplate().update("cancel.deleteFromAppInfo", inputObj);
							
							//for set next semester code of the withdrawing semester
							inputObj.setSemester((String)getSqlMapClientTemplate().queryForObject("cancel.getNextSem", inputObj));
							
							getSqlMapClientTemplate().update("cancel.deleteFromStaging", inputObj);
							getSqlMapClientTemplate().update("cancel.prestagingBackUp", inputObj);
							getSqlMapClientTemplate().update("cancel.deleteFromPrestaging", inputObj);
							getSqlMapClientTemplate().update("cancel.tempCoursesBackUp", inputObj);
							getSqlMapClientTemplate().update("cancel.tempMasterBackUp", inputObj);
							getSqlMapClientTemplate().update("cancel.tempProgramBackUp", inputObj);
							getSqlMapClientTemplate().update("cancel.deleteFromTempCourses", inputObj);
							getSqlMapClientTemplate().update("cancel.deleteFromTempMaster", inputObj);
							getSqlMapClientTemplate().update("cancel.deleteFromTempProgram", inputObj);
						}
						
						// for cancel
						else{
							getSqlMapClientTemplate().update("cancel.updateSRSH",inputObj);
							getSqlMapClientTemplate().update("cancel.updateStdCourse", inputObj);
							if (inputObj.getProcessedFlag().equalsIgnoreCase("ALL")) {
								getSqlMapClientTemplate().update("cancel.updateStdProg", inputObj);
								getSqlMapClientTemplate().update("cancel.updateStdMaster", inputObj);
							}
						}
						getSqlMapClientTemplate().getDataSource().getConnection().commit();	
						result="success";
					}  
				catch (Exception e) {
					logObj.error(e);
					status.rollbackToSavepoint(savepoint);					
				}

				return result;

			}
		});
		
		return message;
	}

}
