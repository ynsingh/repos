/**
 * @(#) PrestagingServiceImpl.java
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

import in.ac.dei.edrp.cms.dao.prestaging.PrestagingService;
import in.ac.dei.edrp.cms.domain.coursegroup.CourseGroupBean;
import in.ac.dei.edrp.cms.domain.coursemaster.CourseMasterBean;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.sendpassword.SendPasswordInfoGetter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * this is Server side Implementation class for prestaging
 * 
 * @version 1.0 12 July 2011
 * @author MOHD AMIR
 */
public class PrestagingServiceImpl extends SqlMapClientDaoSupport implements
		PrestagingService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(PrestagingServiceImpl.class);
	/** Creating object of TransactionTemplate for transaction Management */
	private TransactionTemplate transactionTemplate;

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * This method retrieves child entity list from database
	 * 
	 * @param prestagingInfoGetter, Object of class CourseGroupBean
	 * @return dataList containing child entity list
	 */
	@SuppressWarnings("unchecked")
	public List<CourseMasterBean> getChildEntityList(
			CourseGroupBean prestagingInfoGetter) {
		List<CourseMasterBean> dataList = new ArrayList<CourseMasterBean>();
		try {
			dataList = getSqlMapClientTemplate().queryForList(
					"prestagingData.getChildEntity", prestagingInfoGetter);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return dataList;
	}

	/**
	 * This method retrieves program list from database
	 * 
	 * @param entityId, id of entity
	 * @return dataList containing program list
	 */
	@SuppressWarnings("unchecked")
	public List<CourseMasterBean> getProgramList(String entityId) {
		List<CourseMasterBean> dataList = new ArrayList<CourseMasterBean>();
		try {
			dataList = getSqlMapClientTemplate().queryForList(
					"prestagingData.getEntityProgram", entityId);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return dataList;
	}

	/**
	 * This method retrieves branch list from database
	 * 
	 * @param prestagingInfoGetter Object of class CourseGroupBean
	 * @return dataList containing branch list
	 */
	@SuppressWarnings("unchecked")
	public List<CourseMasterBean> getBranchList(
			CourseGroupBean prestagingInfoGetter) {
		List<CourseMasterBean> dataList = new ArrayList<CourseMasterBean>();
		try {
			dataList = getSqlMapClientTemplate().queryForList(
					"prestagingData.getEntityProgramBranch",
					prestagingInfoGetter);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return dataList;
	}

	/**
	 * This method retrieves Specialization list from database
	 * 
	 * @param prestagingInfoGetter Object of class CourseGroupBean
	 * @return dataList containing Specialization list
	 */
	@SuppressWarnings("unchecked")
	public List<CourseMasterBean> getSpecializationList(
			CourseGroupBean prestagingInfoGetter) {
		List<CourseMasterBean> dataList = new ArrayList<CourseMasterBean>();
		try {
			dataList = getSqlMapClientTemplate().queryForList(
					"prestagingData.getEntityProgramBranchSpecialization",
					prestagingInfoGetter);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return dataList;
	}

	/**
	 * This method insert prestaging data into database
	 * 
	 * @param inputPrestagingData, List of type EnrollmentInfo
	 * @return insertedData containing inserted records
	 */
	public List<EnrollmentInfo> setPrestagingData(
			List<EnrollmentInfo> inputPrestagingData) {
		final List<EnrollmentInfo> insertedData = new ArrayList<EnrollmentInfo>();
		for (int i = 0; i < inputPrestagingData.size(); i++) {
			final EnrollmentInfo inputRow = inputPrestagingData.get(i);
			transactionTemplate.execute(new TransactionCallback() {
				Object savePoint = new Object();

				public Object doInTransaction(TransactionStatus ts) {
					try {
						String year = new Date().toString().substring(24, 28);
						savePoint = ts.createSavepoint();
						inputRow.setCode("REGNUM");
						String regSeqNum = ((EnrollmentInfo) getSqlMapClientTemplate()
								.queryForList("enrollment.getSysValue",
										inputRow).get(0)).getSeqNum();
						Integer n = Integer.parseInt(regSeqNum);
						n = n + 1;

						regSeqNum = n.toString();
						while (regSeqNum.length() < 6) {
							regSeqNum = "0" + regSeqNum;
						}

						inputRow.setSeqNum(regSeqNum);
						getSqlMapClientTemplate().update(
								"enrollment.updateSysValue", inputRow);

						String registrationNo =inputRow.getNickName()+ regSeqNum;
						inputRow.setCode("STUID");
						String studentIdSeqNum = ((EnrollmentInfo) getSqlMapClientTemplate()
								.queryForList("enrollment.getSysValue",
										inputRow).get(0)).getSeqNum();
						n = Integer.parseInt(studentIdSeqNum);
						n = n + 1;
						studentIdSeqNum = n.toString();
						while (studentIdSeqNum.length() < 5) {
							studentIdSeqNum = "0" + studentIdSeqNum;
						}

						inputRow.setSeqNum(studentIdSeqNum);
						getSqlMapClientTemplate().update(
								"enrollment.updateSysValue", inputRow);

						String studentId = "X" + inputRow.getEntityCode()
								+ year + studentIdSeqNum;
						inputRow.setStudentId(studentId);
						// Change Done By Dheeraj For By-Passing The Logic of Enrollment Form
						// Starts
						
						inputRow.setRegistrationNo(registrationNo);			//Commented By Dheeraj
						

						getSqlMapClientTemplate().insert(
								"enrollment.setPersonalInfo", inputRow);
						getSqlMapClientTemplate().insert(
								"enrollment.setAddress", inputRow);
						SendPasswordInfoGetter sendPasswordInfoGetter = new SendPasswordInfoGetter();
						sendPasswordInfoGetter.setProgram(inputRow
								.getProgramCode());
						sendPasswordInfoGetter.setBranch(inputRow
								.getBranchCode());
						sendPasswordInfoGetter.setSpecialization(inputRow
								.getSpecializationCode());
						sendPasswordInfoGetter.setSemester(inputRow
								.getSemesterCode());
						sendPasswordInfoGetter.setAIorUI("AI");
						sendPasswordInfoGetter.setCreatorId(inputRow
								.getCreatorId());
						sendPasswordInfoGetter.setEntity(inputRow
								.getEntityCode());
						sendPasswordInfoGetter.setUserGroupId("STD");
						sendPasswordInfoGetter.setEmailId(inputRow
								.getPrimaryMail());
						sendPasswordInfoGetter.setIsReady("Y");
						//New Change By Dheeraj
//						sendPasswordInfoGetter.setUserName(inputRow
//								.getPrimaryMail());
						sendPasswordInfoGetter.setUserName(inputRow
								.getRegistrationNo());
						sendPasswordInfoGetter.setUserId(inputRow
								.getStudentId());

						getSqlMapClientTemplate().insert(
								"enrollment.setEmailTableData",
								sendPasswordInfoGetter);

						insertedData.add(inputRow);
					} catch (Exception e) {
						ts.rollbackToSavepoint(savePoint);
						logObj.error(e.getMessage());
					}
					return null;
				}
			});
		}
		return insertedData;
	}
}
