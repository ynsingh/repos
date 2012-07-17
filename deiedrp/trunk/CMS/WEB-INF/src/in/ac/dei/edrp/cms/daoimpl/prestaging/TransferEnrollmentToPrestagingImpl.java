/**
 * @(#) TransferEnrollmentToPrestagingImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.prestaging;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.prestaging.TransferEnrollmentToPrestagingService;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.prestaging.PrestagingInfoGetter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * this is Server side Implementation class for transfer Enrollment data to
 * prestaging
 * 
 * @version 1.0 11 MAR 2011
 * @author MOHD AMIR
 */
public class TransferEnrollmentToPrestagingImpl extends SqlMapClientDaoSupport
		implements TransferEnrollmentToPrestagingService {
	/** Creating object of TransactionTemplate for transaction Management */
	private TransactionTemplate transactionTemplate;

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(TransferEnrollmentToPrestagingImpl.class);

	/**
	 * This method retrieves Student personal data list from database
	 * 
	 * @return studentData containing Student personal data list
	 */
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getStudentPersonalData(String universityId) {
		List<EnrollmentInfo> studentData = new ArrayList<EnrollmentInfo>();
		try {
			EnrollmentInfo input = new EnrollmentInfo();
			input.setUniversityId(universityId);
			input.setRegistrationNo("%");
			input.setEntityCode("%");
			input.setProgramCode("%");
			input.setBranchCode("%");
			input.setSpecializationCode("%");
			input.setSessionStartDate("%");
			input.setSessionEndDate("%");
			input.setStatus("SUB");
			studentData = getSqlMapClientTemplate().queryForList(
					"enrollment.getPersonalInfo", input);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return studentData;
	}

	/**
	 * This method retrieves Student address data from database
	 * 
	 * @param userId
	 *            , user id of student
	 * 
	 * @return studentData containing Student address data
	 */
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getStudentAddressData(String userId) {
		List<EnrollmentInfo> studentData = new ArrayList<EnrollmentInfo>();
		try {
			studentData = getSqlMapClientTemplate().queryForList(
					"enrollment.getContactInfo", userId);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return studentData;
	}

	/**
	 * This method insert enrollment data into prestaging table
	 * 
	 * @param studentData
	 *            , List of type PrestagingInfoGetter
	 * @return b, True/False
	 */
	public Boolean setPrestagingData(final PrestagingInfoGetter studentData)  throws Exception{
		return (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				Object savePoint = new Object();
				Boolean bool = false;
				try {
					savePoint = ts.createSavepoint();
					getSqlMapClientTemplate().insert(
							"prestagingData.insertDataIntoPreStaging",
							studentData);
					EnrollmentInfo temp = new EnrollmentInfo();
					temp.setStatus("PRC");
					temp.setRegistrationNo(studentData.getRegistrationNo());
					getSqlMapClientTemplate().update(
							"enrollment.updatePersonalStatus", temp);
					bool = true;
				} catch (Exception e) {
					ts.rollbackToSavepoint(savePoint);
					logObj.error(e.getMessage());
					throw new MyException(e.getMessage());
				}
				return bool;
			}
		});
	}

	/**
	 * This method retrieves registration due date from database
	 * 
	 * @param inputBean
	 *            ,Object of class EnrollmentInfo
	 * @return registration dates details
	 */
	@SuppressWarnings("unchecked")
	public PrestagingInfoGetter getRegistrationDueDate(EnrollmentInfo inputBean) {
		List<PrestagingInfoGetter> dateData = new ArrayList<PrestagingInfoGetter>();
		try {
			dateData = getSqlMapClientTemplate().queryForList(
					"prestagingData.getRegistrationDueDate", inputBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		if (dateData.size() > 0) {
			return dateData.get(0);
		} else {
			return (new PrestagingInfoGetter());
		}
	}

	/**
	 * This method semester and sequence number from database
	 * 
	 * @param chartBean
	 *            , object of bean class ConsolidatedChartBean
	 * @return semSeqList,List of type ConsolidatedChartBean containing semester
	 *         and sequence number
	 */
	@SuppressWarnings("unchecked")
	public List<ConsolidatedChartBean> getSemesterAndSeqNo(
			ConsolidatedChartBean chartBean) {
		List<ConsolidatedChartBean> semSeqList = new ArrayList<ConsolidatedChartBean>();
		try {
			semSeqList = getSqlMapClientTemplate().queryForList(
					"consolidatedchart.getSemSeqNo", chartBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return semSeqList;
	}
}