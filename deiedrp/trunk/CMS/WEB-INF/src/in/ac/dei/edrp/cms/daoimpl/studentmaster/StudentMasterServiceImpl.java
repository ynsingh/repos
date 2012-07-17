/**
 * @(#) StudentMasterServiceImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.studentmaster;

import in.ac.dei.edrp.cms.dao.studentmaster.StudentMasterService;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.*;

/**
 * The server side implementation of the RPC service.
 * 
 * @version 1.0 24 MAR 2011
 * @author MOHD AMIR
 */
public class StudentMasterServiceImpl extends SqlMapClientDaoSupport implements
		StudentMasterService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(UniversityReservationServiceImpl.class);

	/** Creating object of TransactionTemplate for transaction Management */
	TransactionTemplate transactionTemplate = null;

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * This method add student Details into database
	 * 
	 * @param studentMasterInfoBean
	 *            , object of the StudentMasterInfoBean
	 * @return boolean containing info whether a record is inserted or not
	 */
	public Boolean addStudentMaster(
			final StudentMasterInfoBean studentMasterInfoBean)
			throws IllegalArgumentException {

		StudentMasterInfoBean sequenceInfoBean = (StudentMasterInfoBean) getSqlMapClientTemplate()
				.queryForObject("addStduentInMaster.getSequenceNumber",
						studentMasterInfoBean.getUniversityId());

		int num = Integer.parseInt(sequenceInfoBean.getSequenceNumber());
		num = num + 1;
		final String newSeqNumber = String.format("%07d", num);
		final String currentYear = new Date().toString().substring(
				new Date().toString().length() - 2);

		studentMasterInfoBean.setStudentId("S"
				+ studentMasterInfoBean.getParentEntity() + currentYear
				+ newSeqNumber);
		studentMasterInfoBean.setSequenceNumber(newSeqNumber);

		final Map<String, StudentMasterInfoBean> addressMap = studentMasterInfoBean
				.getStudentAddress();

		return (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Boolean doInTransaction(TransactionStatus tStatus) {
				Boolean b = false;
				Object savepoint = null;
				try {
					savepoint = tStatus.createSavepoint();
					getSqlMapClientTemplate().insert(
							"addStduentInMaster.insertstudentmaster",
							studentMasterInfoBean);
					for (Map.Entry<String, StudentMasterInfoBean> e : addressMap
							.entrySet()) {

						String key = e.getKey();
						StudentMasterInfoBean address = e.getValue();
						address.setUserType("STD");
						address.setAddressKey(key);
						address.setStudentId("S"
								+ studentMasterInfoBean.getParentEntity()
								+ currentYear + newSeqNumber);

						address.setUserId(studentMasterInfoBean.getUserId());
						getSqlMapClientTemplate().insert(
								"addStduentInMaster.insertaddressmaster",
								address);
						getSqlMapClientTemplate().update(
								"addStduentInMaster.updateSequenceNumber",
								studentMasterInfoBean);
					}
					b = true;
				} catch (Exception e) {
					tStatus.rollbackToSavepoint(savepoint);
					logObj.error(e.getMessage());
					b = false;
				}
				return b;
			}
		});
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
						.queryForList("addStduentInMaster.getStudentDetail",
								input);
				for (StudentMasterInfoBean detail : stduentDetail) {
					studentMasterInfoBean = detail;
				}
				List<StudentMasterInfoBean> studentAddress = getSqlMapClientTemplate()
						.queryForList(
								"addStduentInMaster.getStudentAddressDetail",
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
	 * This method update student Details into database
	 * 
	 * @param studentMasterInfoBean
	 *            , object of the StudentMasterInfoBean
	 * @return boolean containing info whether a record is updated or not
	 */
	public Boolean updateStudentMaster(
			final StudentMasterInfoBean studentMasterInfoBean) {
		final Map<String, StudentMasterInfoBean> addressMap = studentMasterInfoBean
				.getStudentAddress();

		return (Boolean) transactionTemplate.execute(new TransactionCallback() {

			public Boolean doInTransaction(TransactionStatus tStatus) {
				Object savepoint = null;
				Boolean b = false;
				try {
					savepoint = tStatus.createSavepoint();
					getSqlMapClientTemplate().update(
							"addStduentInMaster.updatestudentmaster",
							studentMasterInfoBean);
					for (Map.Entry<String, StudentMasterInfoBean> e : addressMap
							.entrySet()) {
						String key = e.getKey();
						StudentMasterInfoBean address = e.getValue();
						address.setUserType("STD");
						address.setAddressKey(key);
						address.setStudentId(studentMasterInfoBean
								.getStudentId());
						if (checkKeyForAddress(address)) {
							address.setUserId(studentMasterInfoBean.getUserId());
							getSqlMapClientTemplate().update(
									"addStduentInMaster.updateaddressmaster",
									address);
						} else {
							address.setUserId(studentMasterInfoBean.getUserId());
							getSqlMapClientTemplate().insert(
									"addStduentInMaster.insertaddressmaster",
									address);
						}
						b = true;
					}
				} catch (Exception e) {
					tStatus.rollbackToSavepoint(savepoint);
					logObj.error(e.getMessage());
					b = false;
				}
				return b;
			}
		});
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
					.queryForList("addStduentInMaster.getStudentId",
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
	 * This method checkKey For Address from database
	 * 
	 * @param address
	 *            , object of the StudentMasterInfoBean containing address info
	 * @return boolean containing info whether a key exist or not in addresses
	 *         master
	 */
	@SuppressWarnings("unchecked")
	public boolean checkKeyForAddress(StudentMasterInfoBean address) {
		boolean b = false;
		try {

			List<StudentMasterInfoBean> studentCount = getSqlMapClientTemplate()
					.queryForList("addStduentInMaster.getStudentAddresskey",
							address);

			for (StudentMasterInfoBean student : studentCount) {
				if (student.getCount() == 1) {
					b = true;
				}
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	public List<StudentInfoGetter> getStudentTrackingRecords(
			StudentInfoGetter studentBean) {
		List studentReords = null;
		try {
			studentReords = getSqlMapClientTemplate().queryForList(
					"studentenrollment.getstudenttrackingrecords", studentBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}

		return studentReords;
	}
}