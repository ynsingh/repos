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

import in.ac.dei.edrp.api.StudentMasterBeanAPI;
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
	
	/**
	 * This method check for existence of emailid and if exist
	 * retrieves all student Details from database and map to a jsp
	 * 
	 * @param enrollmentNumber
	 *            , enrollment Number
	 * @return studentMasterInfoBean containing student Details
	 */
	@SuppressWarnings("unchecked")
	public String checkExistanceOfEmailId(StudentMasterBeanAPI input) {
		String b = "false";
		try {
			List<StudentMasterBeanAPI> studentCount = getSqlMapClientTemplate().queryForList("addStduentInMaster.chkUserExist",input);
			for (StudentMasterBeanAPI student : studentCount) {
				if (student.getCount() == 1) {
					b = "true";
				}
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	public List<StudentMasterBeanAPI> getStudentInfo(StudentMasterBeanAPI studentBean) throws Exception{
		List studentReords = new ArrayList<StudentMasterBeanAPI>();
		studentReords = getSqlMapClientTemplate().queryForList("addStduentInMaster.getStudentInfo", studentBean);
		return studentReords;
	}
	
	public String updateStudentPersonalInfo(StudentMasterBeanAPI studentBean) throws Exception{
		String result ="false";
		int a =	getSqlMapClientTemplate().update("addStduentInMaster.updateStudentInfo",studentBean);
			if(a==0){
				result = "Sorry! No user personal record is available for this emailId :"+studentBean.getAddressKey();
			}
			result="true";
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentMasterBeanAPI> getContactInfo(StudentMasterBeanAPI studentBean) throws Exception{
		List studentReords = new ArrayList<StudentMasterBeanAPI>();
		studentReords = getSqlMapClientTemplate().queryForList("addStduentInMaster.getContactDetail", studentBean);
		return studentReords;
	}
	
	public String updateContactInfo(StudentMasterBeanAPI studentBean) throws Exception {
		String result ="false";
		int a = getSqlMapClientTemplate().update("addStduentInMaster.updateContactInfo",studentBean);
		if(a==0){
			result = "Sorry! No contact record is available for this address key :"+studentBean.getAddressKey();
		}
		System.out.println("int after update "+a);
		result="true";
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentMasterBeanAPI> getParentInfo(StudentMasterBeanAPI studentBean) {
		List studentReords = new ArrayList<StudentMasterBeanAPI>();
		studentReords = getSqlMapClientTemplate().queryForList("addStduentInMaster.getParentDetail", studentBean);
		return studentReords;
	}
    
    @SuppressWarnings("unchecked")
	public List<StudentMasterBeanAPI> getAcademicInfo(StudentMasterBeanAPI studentBean) {
		List studentReords = new ArrayList<StudentMasterBeanAPI>();
		List finalStudentRecords = new ArrayList<StudentMasterBeanAPI>();
		List<StudentMasterBeanAPI> finalSemesterRecords = new ArrayList<StudentMasterBeanAPI>();
		StudentMasterBeanAPI finalBean = new StudentMasterBeanAPI();
		String formerEntity,formerProgram,formerBranch,formerSpecialization;
		String entity,program,branch,specialization;
		String switchFlag="false";
		StudentMasterBeanAPI errorBean = new StudentMasterBeanAPI();
		studentReords = getSqlMapClientTemplate().queryForList("addStduentInMaster.getAcademicProgramSwitch", studentBean);
		if(studentReords.size()==0){			
			errorBean.setErrorMsg("This roll Number student is not yet registered in any program");
			studentReords.add(errorBean);
			return studentReords;
		}
		for(int i=0;i<studentReords.size();i++){
			System.out.println("inside iterate : "+studentReords.size());
			if(i!=0){
				System.out.println("inside i!=o ");
				formerEntity = ((StudentMasterBeanAPI)studentReords.get(i-1)).getEntityId();
				formerProgram = ((StudentMasterBeanAPI)studentReords.get(i-1)).getProgramId();
				formerBranch = ((StudentMasterBeanAPI)studentReords.get(i-1)).getBranchId();
				formerSpecialization = ((StudentMasterBeanAPI)studentReords.get(i-1)).getSpecializationId();
				entity = ((StudentMasterBeanAPI)studentReords.get(i)).getEntityId();
				program = ((StudentMasterBeanAPI)studentReords.get(i)).getProgramId();
				branch = ((StudentMasterBeanAPI)studentReords.get(i)).getBranchId();
				specialization = ((StudentMasterBeanAPI)studentReords.get(i)).getSpecializationId();
				if(formerEntity.equalsIgnoreCase(entity)&&formerProgram.equalsIgnoreCase(program)&&formerBranch.equalsIgnoreCase(branch)&&
						formerSpecialization.equalsIgnoreCase(specialization)){
					switchFlag = "false";
				}
				else{
					switchFlag = "true";
				}
				System.out.println("end i!=o: switch flag "+switchFlag);
			}
			if(switchFlag.equalsIgnoreCase("true")){
				finalBean.setSemesterList(finalSemesterRecords);
				finalStudentRecords.add(finalBean);
			}
			if((i==0 && switchFlag.equalsIgnoreCase("false")) || switchFlag.equalsIgnoreCase("true")){
				finalBean = new StudentMasterBeanAPI();
				finalBean.setRollNumber(studentBean.getRollNumber());
				finalBean.setEnrollmentNumber(((StudentMasterBeanAPI)studentReords.get(i)).getEnrollmentNumber());
				finalBean.setUniversityId(studentBean.getUniversityId());
				finalBean.setProgramId(((StudentMasterBeanAPI)studentReords.get(i)).getProgramId());
				finalBean.setBranchId(((StudentMasterBeanAPI)studentReords.get(i)).getBranchId());
				finalBean.setSpecializationId(((StudentMasterBeanAPI)studentReords.get(i)).getSpecializationId());
				finalBean.setEntityId(((StudentMasterBeanAPI)studentReords.get(i)).getEntityId());
				finalBean.setProgramName(((StudentMasterBeanAPI)studentReords.get(i)).getProgramName());
				finalBean.setBranchName(((StudentMasterBeanAPI)studentReords.get(i)).getBranchName());
				finalBean.setSpecialization(((StudentMasterBeanAPI)studentReords.get(i)).getSpecialization());
				finalBean.setEntityName(((StudentMasterBeanAPI)studentReords.get(i)).getEntityName());
				finalBean.setCgpa(((StudentMasterBeanAPI)studentReords.get(i)).getCgpa());
				finalBean.setDivision(((StudentMasterBeanAPI)studentReords.get(i)).getDivision());
			}
			
			finalBean.setProgramCourseKey(((StudentMasterBeanAPI)studentReords.get(i)).getProgramCourseKey());
			List<StudentMasterBeanAPI> semesterRecords = new ArrayList<StudentMasterBeanAPI>();						
			semesterRecords = getSqlMapClientTemplate().queryForList("addStduentInMaster.getAcademicSemester", finalBean);
			for(StudentMasterBeanAPI semBean:semesterRecords){
				semBean.setSemesterCode(((StudentMasterBeanAPI)studentReords.get(i)).getSemesterCode());
				semBean.setSemesterName(((StudentMasterBeanAPI)studentReords.get(i)).getSemesterName());
				List<StudentMasterBeanAPI> courseRecords = new ArrayList<StudentMasterBeanAPI>();						
				courseRecords = getSqlMapClientTemplate().queryForList("addStduentInMaster.getAcademicCourse", finalBean);
				semBean.setCourseList(courseRecords);
				finalSemesterRecords.add(semBean);
			}			
		}
		finalBean.setSemesterList(finalSemesterRecords);
		finalStudentRecords.add(finalBean);
		return finalStudentRecords;
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentMasterBeanAPI> getRegistrationInfo(StudentMasterBeanAPI studentBean) {
		List studentReords = new ArrayList<StudentMasterBeanAPI>();
		studentReords = getSqlMapClientTemplate().queryForList("addStduentInMaster.getRegistrationDetail", studentBean);
		return studentReords;
	}
}