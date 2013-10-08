/**
 * @(#) EnrollmentServiceImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.enrollment;

import in.ac.dei.edrp.cms.dao.enrollment.EnrollmentService;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * this is Server side Implementation class for Enrollment
 * 
 * @version 1.0 11 MAR 2011
 * @author MOHD AMIR
 */
public class EnrollmentServiceImpl extends SqlMapClientDaoSupport implements
		EnrollmentService {

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
	 * This method retrieves Academic Details from database
	 * 
	 * @param regNo
	 *            , registration number
	 * @return academicInfo containing Academic Details
	 */
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getAcademicDetails(String registrationNo)
			throws Exception {
		List<EnrollmentInfo> academicInfo = getSqlMapClientTemplate()
				.queryForList("enrollment.getAcademicInfo", registrationNo);

		return academicInfo;
	}

	/**
	 * This method retrieves Contact Details from database
	 * 
	 * @param userId
	 *            , id of user
	 * @return contactInfo containing Contact Details
	 */
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getContactDetails(String userId)
			throws Exception {
		List<EnrollmentInfo> contactInfo = getSqlMapClientTemplate()
				.queryForList("enrollment.getContactInfo", userId);
		return contactInfo;
	}

	/**
	 * This method retrieves Personal Details from database
	 * 
	 * @param userId
	 *            , id of user
	 * @return personalInfo containing Personal Details
	 */
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getPersonalDetails(EnrollmentInfo enrollmentInfo)
			throws Exception {
		List<EnrollmentInfo> personalInfo = getSqlMapClientTemplate()
				.queryForList("enrollment.getPersonalInfo", enrollmentInfo);
		System.out.println(personalInfo.size());
		return personalInfo;
	}

	/**
	 * This method insert the course group details into database
	 * 
	 * @param personalInfo
	 *            , object of bean class EnrollmentInfo containing personal
	 *            details
	 * @param addressList
	 *            , List of type EnrollmentInfo containing address details
	 * @param academicList
	 *            , List of type EnrollmentInfo containing academic details
	 * 
	 * @return value of type boolean show wheater the record are inserted or not
	 */
	public String updateStudentDetails(final EnrollmentInfo personalInfo,
			final List<EnrollmentInfo> addressList,
			final List<EnrollmentInfo> academicList) throws Exception {
		return (String) transactionTemplate.execute(new TransactionCallback() {

			public String doInTransaction(TransactionStatus tStatus) {
				/*
				 * to create a point to which the function revert when an
				 * exception is encountered during the process
				 */
				Object savepoint = null;
				try {
					savepoint = tStatus.createSavepoint();
					getSqlMapClientTemplate().update(
							"enrollment.updatePersonalInfo", personalInfo);

					getSqlMapClientTemplate().delete(
							"enrollment.deleteAcademicInfo", personalInfo);

					for (int i = 0; i < addressList.size(); i++) {
						EnrollmentInfo addressInfo = addressList.get(i);
						int ru = getSqlMapClientTemplate().update(
								"enrollment.updateContactInfo", addressInfo);
						if (ru == 0) {
							getSqlMapClientTemplate().insert(
									"enrollment.setContactInfo", addressInfo);
						}
					}
					for (int j = 0; j < academicList.size(); j++) {
						EnrollmentInfo academicInfo = academicList.get(j);
						getSqlMapClientTemplate().insert(
								"enrollment.setAcademicInfo", academicInfo);
					}
					return "true";
				} catch (Exception e) {
					tStatus.rollbackToSavepoint(savepoint);
					logObj.error(e.getMessage());
					return e.getMessage();
				}
			}

		});
	}

	public Boolean uploadStudentPhotos(String filePath, String outputFolder)
			throws Exception {
		int upCount = 0;
		final int BUFFER = 2048;
		BufferedOutputStream dest = null;
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
				new FileInputStream(new File(filePath))));
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			if (!entry.isDirectory()) {
				String fileName = entry.getName().substring(
						entry.getName().lastIndexOf("/") + 1);
				String registrationNo = fileName.substring(0,
						fileName.lastIndexOf('.'));
				System.out.println(registrationNo);
				EnrollmentInfo input = new EnrollmentInfo();
				input.setPhotoPath("StudentPhotos/" + fileName);
				input.setRegistrationNo(registrationNo);
				if (getSqlMapClientTemplate().update(
						"enrollment.updatePhotoPath", input) > 0) {
					upCount++;
					new File(outputFolder + "StudentPhotos/").mkdirs();
					int count;
					byte data[] = new byte[BUFFER];
					// write the files to the disk
					FileOutputStream fos = new FileOutputStream(outputFolder
							+ "StudentPhotos/" + fileName);
					dest = new BufferedOutputStream(fos, BUFFER);
					while ((count = zis.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
				}
			}
		}
		zis.close();
		new File(filePath).delete();
		if (upCount > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**Method to get student final list from database
	 * @author Devendra Singhal
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@return List<EnrollmentInfo>
	*/
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getStudentFinalList(
			EnrollmentInfo enrollmentInfo) throws Exception {
		List<EnrollmentInfo>list=null;
		try{
			if(enrollmentInfo.getAddress()==null){
			list=getSqlMapClientTemplate().queryForList("enrollment.getStudentFinalList",enrollmentInfo);	
			}
			else
			// Line used to Fire Query that return list of Modified Marks Of Component
			list=getSqlMapClientTemplate().queryForList("enrollment.getListOfComponent",enrollmentInfo);
		}
		catch(Exception e){
			logObj.error("Exception inEnrollmentSErviceImpl inside getStudentFinalList methid : "+e);
		}
		return list;
	}
	
	/**Method to get list  of those student whose sounds match to other students from database
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@return List<EnrollmentInfo>
	*/
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getStudentChackedList(
			EnrollmentInfo enrollmentInfo) throws Exception {
		List<EnrollmentInfo>list=null;
		try{
			list=getSqlMapClientTemplate().queryForList("enrollment.getStudentListMatchSound",enrollmentInfo);
		}
		catch(Exception e){
			logObj.error("Exception inEnrollmentSErviceImpl inside getStudentChackedList methid : "+e);
		}
		return list;
	}


	/**Method to Delete roll number/enrollment number format
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@param String Format
	 *@return String containing success/fail
	 *@author Devendra Singhal
	*/
	public String deleteFormat(EnrollmentInfo enrollmentInfo,String format){
		String msg="";
		try{ 
			StringTokenizer stkn=new StringTokenizer(format, ",");
			while(stkn.hasMoreTokens()){
				enrollmentInfo.setFormat(stkn.nextToken());
				getSqlMapClientTemplate().delete("enrollment.deleteFormat", enrollmentInfo);
			}			
			 msg="success";
		}
		catch(Exception e){
			msg="error";
			logObj.error("Exception in EnrollmentSErviceImpl during deleteFormat : "+e);
		}
		return msg;
	}

	/**Method to get list  roll number/enrollment number format
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@return List<EnrollmentInfo>
	 *@author Devendra Singhal
	*/
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getFormat(EnrollmentInfo enrollmentInfo){
		List<EnrollmentInfo> list=null;
		try{
			list=getSqlMapClientTemplate().queryForList("enrollment.getFormat",enrollmentInfo);
		}
		catch(Exception e){
			logObj.error("Exception in EnrollmentSErviceImpl during getFormat : "+e);
		}
		return list;
	}

	/**Method to Insert roll number/enrollment number format
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@return String containing success/fail
	 *@author Devendra Singhal
	*/
	public String insertFormat(EnrollmentInfo enrollmentInfo){
		String msg="";
		try{
			getSqlMapClientTemplate().insert("enrollment.insertFormat", enrollmentInfo);
			msg="success";
		}
		catch(Exception e){
			msg="error";
			logObj.error("Exception in EnrollmentSErviceImpl during insertFormat : "+e);			
		}
		return msg;
	}
}