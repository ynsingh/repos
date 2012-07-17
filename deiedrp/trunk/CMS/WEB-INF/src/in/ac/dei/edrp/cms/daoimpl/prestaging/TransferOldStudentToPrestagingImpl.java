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

import in.ac.dei.edrp.cms.dao.prestaging.TransferOldStudentToPrestagingService;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.prestaging.PrestagingInfoGetter;
import in.ac.dei.edrp.cms.domain.sendpassword.SendPasswordInfoGetter;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;

import java.util.ArrayList;
import java.util.Date;
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
public class TransferOldStudentToPrestagingImpl extends SqlMapClientDaoSupport
		implements TransferOldStudentToPrestagingService {
	/** Creating object of TransactionTemplate for transaction Management */
	private TransactionTemplate transactionTemplate;

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(TransferOldStudentToPrestagingImpl.class);

	/**
	 * This method retrieves Student personal data list from database
	 * 
	 * @return studentData containing Student personal data list
	 */
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getStudentPersonalData(
			EnrollmentInfo enrollmentInfo) {
		List<EnrollmentInfo> studentData = new ArrayList<EnrollmentInfo>();
		try {
			studentData = getSqlMapClientTemplate().queryForList(
					"addStduentInMaster.getOldStudentDetail", enrollmentInfo);
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
	public List<StudentMasterInfoBean> getStudentAddressData(String userId) {
		List<StudentMasterInfoBean> studentData = new ArrayList<StudentMasterInfoBean>();
		try {
			studentData = getSqlMapClientTemplate().queryForList(
					"addStduentInMaster.getStudentAddressDetail", userId);
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
	public Boolean setPrestagingData(final PrestagingInfoGetter studentData) {
		return (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				Object savePoint = new Object();
				Boolean bool = false;
				try {
					String year = new Date().toString().substring(24, 28);
					savePoint = ts.createSavepoint();
					EnrollmentInfo inputRow = new EnrollmentInfo();
					inputRow.setUniversityId(studentData.getUniversityId());
					inputRow.setCode("REGNUM");
					String regSeqNum = ((EnrollmentInfo) getSqlMapClientTemplate()
							.queryForList("enrollment.getSysValue", inputRow)
							.get(0)).getSeqNum();
					Integer n = Integer.parseInt(regSeqNum);
					n = n + 1;

					regSeqNum = n.toString();
					while (regSeqNum.length() < 6) {
						regSeqNum = "0" + regSeqNum;
					}

					inputRow.setSeqNum(regSeqNum);
					getSqlMapClientTemplate().update(
							"enrollment.updateSysValue", inputRow);

					studentData.setRegistrationNo(studentData.getNickName()+year.substring(2) + regSeqNum);

					inputRow.setCode("STUID");
					String studentIdSeqNum = ((EnrollmentInfo) getSqlMapClientTemplate()
							.queryForList("enrollment.getSysValue", inputRow)
							.get(0)).getSeqNum();
					n = Integer.parseInt(studentIdSeqNum);
					n = n + 1;
					studentIdSeqNum = n.toString();
					while (studentIdSeqNum.length() < 5) {
						studentIdSeqNum = "0" + studentIdSeqNum;
					}

					inputRow.setSeqNum(studentIdSeqNum);
					getSqlMapClientTemplate().update(
							"enrollment.updateSysValue", inputRow);

					studentData.setStudentId("S" + studentData.getEntityId()
							+ year + studentIdSeqNum);

					getSqlMapClientTemplate().insert(
							"prestagingData.insertDataIntoPreStaging",
							studentData);

					SendPasswordInfoGetter sendPasswordInfoGetter = new SendPasswordInfoGetter();
					sendPasswordInfoGetter.setProgram(studentData
							.getProgramId());
					sendPasswordInfoGetter.setBranch(studentData.getBranchId());
					sendPasswordInfoGetter.setSpecialization(studentData
							.getSpecializationId());
					sendPasswordInfoGetter.setSemester(studentData
							.getSemesterId());
					sendPasswordInfoGetter.setAIorUI("AI");
					sendPasswordInfoGetter.setCreatorId(studentData.getUserId());
					sendPasswordInfoGetter.setEntity(studentData.getEntityId());
					sendPasswordInfoGetter.setUserGroupId("STD");
					sendPasswordInfoGetter.setEmailId(studentData
							.getPrimaryEmail());
					sendPasswordInfoGetter.setIsReady("Y");
					sendPasswordInfoGetter.setUserName(studentData
							.getRegistrationNo());
					sendPasswordInfoGetter.setUserId(studentData.getStudentId());

					getSqlMapClientTemplate().insert(
							"sendPassword.setEmailTableData",
							sendPasswordInfoGetter);

					bool = true;
				} catch (Exception e) {
					ts.rollbackToSavepoint(savePoint);
					logObj.error(e.getMessage());
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