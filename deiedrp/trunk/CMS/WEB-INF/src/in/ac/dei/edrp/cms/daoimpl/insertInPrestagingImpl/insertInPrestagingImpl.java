/**
 * @(#) insertInPrestagingImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.insertInPrestagingImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.ac.dei.edrp.cms.dao.insertToPrestagingService.insertToPrestagingService;

import in.ac.dei.edrp.cms.daoimpl.prestaging.PrestagingServiceImpl;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.prestaging.PrestagingInfoGetter;
import in.ac.dei.edrp.cms.domain.sendpassword.SendPasswordInfoGetter;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * this is Server side Implementation class for prestaging
 * 
 * @version 1.0 08 October 2012
 * @author Ashish Mohan
 */




public class insertInPrestagingImpl extends SqlMapClientDaoSupport implements insertToPrestagingService {
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
	 * This method check for existence of enrollment number and if exist
	 * retrieves all student Details from database and map to a jsp
	 * 
	 * @param enrollmentNumber
	 *            , enrollment Number
	 * @return studentMasterInfoBean containing student Details
	 */
	@SuppressWarnings("unchecked")
	public StudentMasterInfoBean checkExistanceOfEnroll(
			StudentMasterInfoBean input) {
		StudentMasterInfoBean studentMasterInfoBean = new StudentMasterInfoBean();
		Map<String, StudentMasterInfoBean> addressMap = new HashMap<String, StudentMasterInfoBean>();
		try {
			if (this.checkDuplicateRollNumber(input.getEnrollmentNumber())) {
				List<StudentMasterInfoBean> stduentDetail = getSqlMapClientTemplate()
						.queryForList("insertInPrestaging.getStudentDetail",
								input);
				for (StudentMasterInfoBean detail : stduentDetail) {
					studentMasterInfoBean = detail;
				}
				List<StudentMasterInfoBean> studentAddress = getSqlMapClientTemplate()
						.queryForList(
								"insertInPrestaging.getStudentAddressDetail",
								studentMasterInfoBean);
				for (StudentMasterInfoBean address : studentAddress) {
					addressMap.put(address.getAddressKey(), address);
				}
				studentMasterInfoBean.setStudentAddress(addressMap);
			} else {
				studentMasterInfoBean = new StudentMasterInfoBean();
				addressMap.put("PER", new StudentMasterInfoBean());
				addressMap.put("COR", new StudentMasterInfoBean());
				studentMasterInfoBean.setStudentAddress(addressMap);
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return studentMasterInfoBean;
	}
	
	
	/**
	 * This method check for existence of enrollment number in database
	 * 
	 * @param enrollmentNumber
	 *            , enrollment Number
	 * @return boolean containing info whether a record exist or not
	 */
	@SuppressWarnings("unchecked")
	public Boolean checkDuplicateRollNumber(String enrollmentNumber) {
		Boolean b = false;
		StudentMasterInfoBean studentInfo = new StudentMasterInfoBean();
		studentInfo.setEnrollmentNumber(enrollmentNumber);
		// studentInfo.setStudentId(enrollmentNumber);
		try {
			List<StudentMasterInfoBean> studentCount = getSqlMapClientTemplate()
					.queryForList("insertInPrestaging.getStudentId",
							studentInfo);

			if (studentCount.get(0).getCount() >= 1) {
				b = true;
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return b;
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
								.queryForList("insertInPrestaging.getSysValue",
										inputRow).get(0)).getSeqNum();
						Integer n = Integer.parseInt(regSeqNum);
						n = n + 1;

						regSeqNum = n.toString();
						while (regSeqNum.length() < 6) {
							regSeqNum = "0" + regSeqNum;
						}

						inputRow.setSeqNum(regSeqNum);
						getSqlMapClientTemplate().update(
								"insertInPrestaging.updateSysValue", inputRow);

						String registrationNo =year.substring(2)+ regSeqNum;
						inputRow.setCode("STUID");
						String studentIdSeqNum = ((EnrollmentInfo) getSqlMapClientTemplate()
								.queryForList("insertInPrestaging.getSysValue",
										inputRow).get(0)).getSeqNum();
						n = Integer.parseInt(studentIdSeqNum);
						n = n + 1;
						studentIdSeqNum = n.toString();
						while (studentIdSeqNum.length() < 5) {
							studentIdSeqNum = "0" + studentIdSeqNum;
						}

						inputRow.setSeqNum(studentIdSeqNum);
						getSqlMapClientTemplate().update(
								"insertInPrestaging.updateSysValue", inputRow);

						String studentId = "X" + inputRow.getEntityCode()
								+ year + studentIdSeqNum;
						inputRow.setStudentId(studentId);
						
						
						inputRow.setRegistrationNo(registrationNo);			
						

						getSqlMapClientTemplate().insert(
								"insertInPrestaging.setPersonalInfo", inputRow);
						getSqlMapClientTemplate().insert(
								"insertInPrestaging.setAddress", inputRow);
						getSqlMapClientTemplate().update(
								"insertInPrestaging.updateTempDataNewStudent",
								inputRow);
						
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

						sendPasswordInfoGetter.setUserName(inputRow
								.getRegistrationNo());
						sendPasswordInfoGetter.setUserId(inputRow
								.getStudentId());

						getSqlMapClientTemplate().insert(
								"insertInPrestaging.setEmailTableData",
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
	
	
	/**
	 * This method insert enrollment data into prestaging table
	 * 
	 * @param studentData
	 *            , List of type PrestagingInfoGetter
	 * @return b, True/False
	 */
	public List<PrestagingInfoGetter> setPrestagingDataOld(final List<PrestagingInfoGetter> studentDataList) {
		
		final List<PrestagingInfoGetter> insertedData = new ArrayList<PrestagingInfoGetter>();
		for (int i = 0; i < studentDataList.size(); i++) {
			final PrestagingInfoGetter studentData = studentDataList.get(i);
			transactionTemplate.execute(new TransactionCallback() {
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
							.queryForList("insertInPrestaging.getSysValue", inputRow)
							.get(0)).getSeqNum();
					Integer n = Integer.parseInt(regSeqNum);
					n = n + 1;

					regSeqNum = n.toString();
					while (regSeqNum.length() < 6) {
						regSeqNum = "0" + regSeqNum;
					}

					inputRow.setSeqNum(regSeqNum);
					getSqlMapClientTemplate().update(
							"insertInPrestaging.updateSysValue", inputRow);

					//studentData.setRegistrationNo(studentData.getNickName()+year.substring(2) + regSeqNum);
					studentData.setRegistrationNo(year.substring(2) + regSeqNum);
					inputRow.setCode("STUID");
					String studentIdSeqNum = ((EnrollmentInfo) getSqlMapClientTemplate()
							.queryForList("insertInPrestaging.getSysValue", inputRow)
							.get(0)).getSeqNum();
					n = Integer.parseInt(studentIdSeqNum);
					n = n + 1;
					studentIdSeqNum = n.toString();
					while (studentIdSeqNum.length() < 5) {
						studentIdSeqNum = "0" + studentIdSeqNum;
					}

					inputRow.setSeqNum(studentIdSeqNum);
					getSqlMapClientTemplate().update(
							"insertInPrestaging.updateSysValue", inputRow);

					studentData.setStudentId("X" + studentData.getEntityId()
							+ year + studentIdSeqNum);

					getSqlMapClientTemplate().insert(
							"insertInPrestaging.insertDataIntoPreStaging",
							studentData);
					
					getSqlMapClientTemplate().update(
							"insertInPrestaging.updateTempData",
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
							"insertInPrestaging.setEmailTableData",
							sendPasswordInfoGetter);

					bool = true;
					insertedData.add(studentData);
				} catch (Exception e) {
					ts.rollbackToSavepoint(savePoint);
					logObj.error(e.getMessage());
				}
				return bool;
			}
		});
		}
		return insertedData;
		
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
					"insertInPrestaging.getRegistrationDueDate", inputBean);
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
					"insertInPrestaging.getSemSeqNo", chartBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return semSeqList;
	}



	public List<StudentMasterInfoBean> getStudent(EnrollmentInfo enrollmentInfo) {
		List<StudentMasterInfoBean> st = new ArrayList<StudentMasterInfoBean>();
		try {
			st = getSqlMapClientTemplate().queryForList(
					"insertInPrestaging.getStudent", enrollmentInfo);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return st;
	}



	public List<EnrollmentInfo> setTempData(
			List<EnrollmentInfo> inputPrestagingData) {
		final List<EnrollmentInfo> insertedData = new ArrayList<EnrollmentInfo>();
		try {
			getSqlMapClientTemplate().delete(
					"insertInPrestaging.deleteTempData", inputPrestagingData.get(1));
			for (int i = 0; i < inputPrestagingData.size(); i++) {
			final EnrollmentInfo inputRow = inputPrestagingData.get(i);
				getSqlMapClientTemplate().insert(
								"insertInPrestaging.setTempData", inputRow);
				insertedData.add(inputRow);
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return insertedData;
	}



	public String updateTempData(List<StudentInfoGetter> inputEnrollmentInfo) {
		int updateCount=0;
		try {
			for (int i = 0; i < inputEnrollmentInfo.size(); i++) {
			final StudentInfoGetter inputRow = inputEnrollmentInfo.get(i);
				getSqlMapClientTemplate().insert(
								"insertInPrestaging.changeTempData", inputRow);
				updateCount++;
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return Integer.toString(updateCount);
	}
	
	
}
