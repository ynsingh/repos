/*
 * @(#) AdmissionIntegrationDaoImpl.java
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

package in.ac.dei.edrp.cms.daoimpl.admissionIntegration;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.admissionIntegration.AdmissionIntegrationDao;
import in.ac.dei.edrp.cms.daoimpl.correctionInAwardBlank.AwardBlankCorrectionDaoImpl;
import in.ac.dei.edrp.cms.domain.admissionIntegration.AdmissionIntegrationInfo;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Implementation class of AdmissionIntegrationDao interface
 * @author Dheeraj Singh
 * @date 14-MAY-2012
 * @version 1.0
 */

public class AdmissionIntegrationDaoImpl extends SqlMapClientDaoSupport
			implements AdmissionIntegrationDao{
	
	private static Logger logObj = Logger.getLogger(AwardBlankCorrectionDaoImpl.class);
	
	/**
     * Method for getting entities
     * @param input
     * @return entityList
     */
	public List<AdmissionIntegrationInfo> getEntities(
			AdmissionIntegrationInfo input) {
		List<AdmissionIntegrationInfo> entityList = null;
		List<AdmissionIntegrationInfo> sessionList = null;
		try{
			logObj.info("Inside getEntities method");
			sessionList = getSqlMapClientTemplate().queryForList("admissionIntegration.getSessionDates");
			input.setSessionStartDate(sessionList.get(0).getSessionStartDate());
			input.setSessionEndDate(sessionList.get(0).getSessionEndDate());
			entityList = getSqlMapClientTemplate().queryForList("admissionIntegration.getEntities", input);
		}catch(Exception ex){
			logObj.info("Error in getting entities");
		}
		return entityList;
	}

	/**
     * Method for getting programs of related entity
     * @param input
     * @return programList
     */
	public List<AdmissionIntegrationInfo> getPrograms(
			AdmissionIntegrationInfo input) {
		List<AdmissionIntegrationInfo> programList = null;
		List<AdmissionIntegrationInfo> sessionList = null;
		try{
			logObj.info("Inside getPrograms method");
			sessionList = getSqlMapClientTemplate().queryForList("admissionIntegration.getSessionDates");
			input.setSessionStartDate(sessionList.get(0).getSessionStartDate());
			input.setSessionEndDate(sessionList.get(0).getSessionEndDate());
			programList = getSqlMapClientTemplate().queryForList("admissionIntegration.getPrograms", input);
		}catch(Exception ex){
			logObj.info("Error in getting programs");
		}
		return programList;
	}

	/**
     * Method for getting students which were selected by Admission System
     * @param input
     * @return studentsList
     */
	public List<AdmissionIntegrationInfo> getStudents(
			AdmissionIntegrationInfo input) {
		List<AdmissionIntegrationInfo> studentsList = null;
		List<AdmissionIntegrationInfo> sessionList = null;
		try{
			logObj.info("Inside getStudents Method");
			sessionList = getSqlMapClientTemplate().queryForList("admissionIntegration.getSessionDates");
			input.setSessionStartDate(sessionList.get(0).getSessionStartDate());
			input.setSessionEndDate(sessionList.get(0).getSessionEndDate());
			studentsList = getSqlMapClientTemplate().queryForList("admissionIntegration.getStudentDetails", input);
		}catch(Exception ex){
			logObj.info("Error in getting Student Details");
		}
		return studentsList;
	}

	/**
     * Method for getting branch and specialization details of related program
     * @param input
     * @return branchList
     */
	public List<AdmissionIntegrationInfo> getBranches(
			AdmissionIntegrationInfo input) {
		List<AdmissionIntegrationInfo> branchList = null;
		try{
			logObj.info("Inside getStudents Method");
			branchList = getSqlMapClientTemplate().queryForList("admissionIntegration.getBranchDetails", input);
		}catch(Exception ex){
			logObj.info("Error in getting Student Details");
		}
		return branchList;
	}

	/**
     * Method inserting data in CMS tables of selected candidates
     * @param input, data
     * @return null
     */
	public String submitDetails(AdmissionIntegrationInfo input, StringTokenizer data) {
		AdmissionIntegrationInfo inputObj = new AdmissionIntegrationInfo();
		
		int count=0;
		List<AdmissionIntegrationInfo> sessionList = null;
		try{
			sessionList = getSqlMapClientTemplate().queryForList("admissionIntegration.getSessionDates");
			inputObj.setSessionStartDate(sessionList.get(0).getSessionStartDate());
			inputObj.setSessionEndDate(sessionList.get(0).getSessionEndDate());
			inputObj.setBranchId(input.getBranchId());
			inputObj.setSpecializationId(input.getSpecializationId());
			inputObj.setCreatorId(input.getCreatorId());
			
			while(data.hasMoreTokens()){
				
				StringTokenizer rowData = new StringTokenizer(data.nextToken(),"$");
				inputObj.setEntityId(rowData.nextToken());
				inputObj.setProgramId(rowData.nextToken());
				inputObj.setRegistrationNo(rowData.nextToken());
				
				String enrNo = rowData.nextToken();
				if(enrNo.equalsIgnoreCase("undefined")){
					inputObj.setEnrollmentNo(null);
				}else{
					inputObj.setEnrollmentNo(enrNo);
				}
				inputObj.setStudentId(rowData.nextToken());
				inputObj.setStudentFirstName(rowData.nextToken());
				
				String studentMiddleName = rowData.nextToken();
				if(studentMiddleName.equalsIgnoreCase("undefined")){
					inputObj.setStudentMiddleName(null);
				}else{
					inputObj.setStudentMiddleName(studentMiddleName);
				}
					
				String studentLastName = rowData.nextToken();
				if(studentLastName.equalsIgnoreCase("undefined")){
					inputObj.setStudentLastName(null);
				}else{
					inputObj.setStudentLastName(studentLastName);
				}
					
				inputObj.setFatherFirstName(rowData.nextToken());
					
				String fatherMiddleName = rowData.nextToken();
				if(fatherMiddleName.equalsIgnoreCase("undefined")){
					inputObj.setFatherMiddleName(null);
				}else{
					inputObj.setFatherMiddleName(fatherMiddleName);
				}
					
				String fatherLastName = rowData.nextToken();
				if(fatherLastName.equalsIgnoreCase("undefined")){
					inputObj.setFatherLastName(null);
				}else{
					inputObj.setFatherLastName(fatherLastName);
				}
					
				inputObj.setMotherFirstName(rowData.nextToken());
					
				String motherMiddleName = rowData.nextToken();
				if(motherMiddleName.equalsIgnoreCase("undefined")){
					inputObj.setMotherMiddleName(null);
				}else{
					inputObj.setMotherMiddleName(motherMiddleName);
				}
					
				String motherLastName = rowData.nextToken();
				if(motherLastName.equalsIgnoreCase("undefined")){
					inputObj.setMotherLastName(null);
				}else{
					inputObj.setMotherLastName(motherLastName);
				}
					
				inputObj.setDateOfBirth(rowData.nextToken());
				inputObj.setCategory(rowData.nextToken());
				inputObj.setPrimaryMail(rowData.nextToken());
					
				String secondaryMail = rowData.nextToken();
				if(secondaryMail.equalsIgnoreCase("undefined")){
					inputObj.setSecondaryMail(null);
				}else{
					inputObj.setSecondaryMail(secondaryMail);
				}
					
				inputObj.setNationality(rowData.nextToken());
				inputObj.setReligion(rowData.nextToken());
					
				String guardianName = rowData.nextToken();
				if(guardianName.equalsIgnoreCase("undefined")){
					inputObj.setGuardian(null);
				}else{
					inputObj.setGuardian(guardianName);
				}
					
				inputObj.setGender(rowData.nextToken());
				inputObj.setAddressKey(rowData.nextToken());
				inputObj.setPerAddress(rowData.nextToken());
				inputObj.setPerCity(rowData.nextToken());
				inputObj.setPerState(rowData.nextToken());
				inputObj.setPerPincode(rowData.nextToken());
				inputObj.setPhone(rowData.nextToken());

//				String year = new Date().toString().substring(24, 28);
//				input.setCode("STUID");
//				String studentIdSequenceNo = ((AdmissionIntegrationInfo)getSqlMapClientTemplate().queryForList("admissionIntegration.getSystemValue",input)
//												.get(0)).getCodeValue();
//				Integer num = Integer.parseInt(studentIdSequenceNo);
//				num = num + 1;
//				studentIdSequenceNo = num.toString();
//				while (studentIdSequenceNo.length() < 5) {
//					studentIdSequenceNo = "0" + studentIdSequenceNo;
//				}
//				input.setCodeValue(studentIdSequenceNo);
//				getSqlMapClientTemplate().update("admissionIntegration.updateSystemValue", input);
//				String studentId = "S" + input.getEntityId() + year + studentIdSequenceNo;
//				inputObj.setStudentId(studentId);
				
				getSqlMapClientTemplate().insert("admissionIntegration.insertIntoEnrollmentPersonalDetails", inputObj);
				getSqlMapClientTemplate().insert("admissionIntegration.insertIntoEnrollmentAddressesMaster", inputObj);
				getSqlMapClientTemplate().insert("admissionIntegration.insertIntoEmailTable", inputObj);
				
				List<AdmissionIntegrationInfo> semesterDates = null;
				semesterDates = getSqlMapClientTemplate().queryForList("admissionIntegration.getSemesterDates", inputObj);
				inputObj.setRegStartDate(semesterDates.get(0).getRegStartDate());
				inputObj.setSemesterStartDate(semesterDates.get(0).getSemesterStartDate());
				inputObj.setSemesterEndDate(semesterDates.get(0).getSemesterEndDate());
				getSqlMapClientTemplate().insert("admissionIntegration.insertIntoPrestaging", inputObj);
				getSqlMapClientTemplate().update("admissionIntegration.updateImportedStatus", inputObj);
				count++;
			}
			
		}catch(Exception ex){
			System.out.println(ex);
			throw new MyException("E");
		}
		String studentCount = String.valueOf(count);
		return studentCount;
	}

}
