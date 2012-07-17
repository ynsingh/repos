/**
 * @(#) StudentUploadDaoImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.manualprocess;

import in.ac.dei.edrp.cms.dao.manualprocess.StudentUploadDao;
import in.ac.dei.edrp.cms.domain.manualprocess.StudentUploadBean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * The server side implementation of the result file upload.
 * 
 * @version 1.0 15 Oct 2011
 * @author Nupur Dixit
 */
public class StudentUploadFirstSemDaoImpl extends SqlMapClientDaoSupport implements StudentUploadDao{

	/** Creating object of TransactionTemplate for transaction Management */
	TransactionTemplate transactionTemplate = null;
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(StudentUploadDaoImpl.class);

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * This method validate student_records in all the tables
	 * @param list of objects of StudentUploadBean
	 * @return String containing info(true/false) whether validation is successful or not
	 */
	public String validateRecords(final List<StudentUploadBean> studentRecords) {
		String resultValidation = "true";
		try {
			List<String> enrollmentResultFile = new ArrayList<String>();
			List<String> enrollmentDatabaseList = new ArrayList<String>();
			List<StudentUploadBean> enrollmentDatabase = getSqlMapClientTemplate().queryForList("studentUpload.selectEnrollment");
			for(int i=0;i<enrollmentDatabase.size();i++){
				enrollmentDatabaseList.add(enrollmentDatabase.get(i).getEnrollmentNumber());
			}					
			for(int i=0;i<studentRecords.size();i++){
				enrollmentResultFile.add(studentRecords.get(i).getEnrollmentNumber());
			}

			System.out.println("result file arraylist "+enrollmentResultFile);
			System.out.println("database arraylist "+enrollmentDatabaseList);

			for(String enrollResultFile :enrollmentResultFile){
				if(enrollmentDatabaseList.contains(enrollResultFile)){}
				else{					
					studentRecords.get(enrollmentResultFile.indexOf(enrollResultFile)).getErrorLogBean().setErrorDescription("Entry for this roll number is not in student_master");
					getSqlMapClientTemplate().insert("studentUpload.insertIntoErrorLog", studentRecords.get(enrollmentResultFile.indexOf(enrollResultFile)).getErrorLogBean());
					logObj.info("*************after inserting in error log for enrollement no : "+studentRecords.get(enrollmentResultFile.indexOf(enrollResultFile)).getErrorLogBean().getRollNumber());					
					resultValidation = "false";
					System.out.println("staus in insert error log"+resultValidation);
				}
			}																																
		} catch (Exception ex) {
			System.out.println("in exception of error log entry ");
			logObj.error("There is exception in error log entry : "+ex);
			resultValidation = "false";					
		}
		return resultValidation;
	}

	
	/**
	 * This method add student Detail into student master table
	 * @param list of objects of StudentUploadBean
	 * @return String containing info(true/false) whether a record is inserted or not
	 */

	public String insertIntoStudentMaster(final List<StudentUploadBean> studentRecordList) {
		String status="false";
		String studentFirstName = null,studentLastName = null,studentId;
		String lastId = null;
		StudentUploadBean codeValue = (StudentUploadBean)getSqlMapClientTemplate().queryForObject("studentUpload.selectStudentID",studentRecordList.get(0).getUniversityId());            	
		int a = (Integer.parseInt(codeValue.getValue()));            
		String studentIdCode = String.format("%07d",a);  
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String yaerLastDigit = year.substring(year.length()-2);
		System.out.println("year"+year.substring(year.length()-2));
		studentId = "S"+studentRecordList.get(0).getEntityId()+yaerLastDigit+studentIdCode;
		System.out.println("actual student id"+studentId);

		for(StudentUploadBean upload:studentRecordList){
			studentFirstName="";
			studentLastName = "";
			String arrParentName[] = upload.getStudentName().split("\\(");
			System.out.println("length parent separated "+arrParentName.length);
			String arrFirstName[] = arrParentName[0].split(" ");
			if(arrFirstName.length==1){
				studentFirstName = arrFirstName[0];					
			}
			else if(arrFirstName.length==2){
				studentFirstName = arrFirstName[0];
				studentLastName = 	arrFirstName[1];
			}
			else if(arrFirstName.length>=3 ){
				studentFirstName = arrFirstName[0];
				studentLastName = "";
				for(int i=1;i<arrFirstName.length;i++){
					studentLastName = studentLastName.concat(arrFirstName[i]+" ");
				}
			}		
			upload.setStudentFirstName(studentFirstName);
			upload.setStudentLastName(studentLastName);
			System.out.println("attempt number "+upload.getAttemptNumber());
			if(Integer.parseInt(upload.getAttemptNumber())!=1){
				upload.setYearOfRegistration("20".concat(upload.getRollNumber().substring(0,2)).concat("-").
						concat(String.format("%02d",(Integer.parseInt(upload.getRollNumber().substring(0,2))+1))));
				System.out.println("registration date with roll no "+upload.getYearOfRegistration());
			}
			upload.setStudentId(studentId);
			lastId = String.format("%07d", Integer.parseInt(studentId.substring(studentId.length()-7))+1);
								
			List<StudentUploadBean> stuList = (List<StudentUploadBean>)getSqlMapClientTemplate().queryForList("studentUpload.selectStudentMaster", upload);
			System.out.println("list size :"+stuList.size());
			if(stuList.size()==0){				
				getSqlMapClientTemplate().insert("studentUpload.insertstudentmaster",upload);
				System.out.println("insert in student master for "+upload.getEnrollmentNumber());
			}
			else{				
				System.out.println("skipping insert in student master for enroll.no. "+upload.getEnrollmentNumber());
			}
			
			List<StudentUploadBean> stuList1 = (List<StudentUploadBean>)getSqlMapClientTemplate().queryForList("studentUpload.selectEPD", upload);
			System.out.println("list size :"+stuList1.size());
			if(stuList1.size()==0){				
				getSqlMapClientTemplate().insert("studentUpload.insertEnrollmentDetail",upload);
				System.out.println("insert in enrollment personal details for "+upload.getEnrollmentNumber());
			}
			else{				
				System.out.println("skipping insert in enrollment personal details for enroll.no. "+upload.getEnrollmentNumber());
			}											
			System.out.println("last id "+lastId);			
//			getSqlMapClientTemplate().insert("studentUpload.insertEnrollmentDetail",upload);
			studentId = "S"+upload.getEntityId()+yaerLastDigit+lastId;			
		}
		System.out.println("after detail insert last student id "+lastId);
		studentRecordList.get(0).setValue(String.valueOf(Integer.valueOf(lastId)));
		getSqlMapClientTemplate().update("studentUpload.updateStudentId", studentRecordList.get(0));
		status = "true";
		return status;
//		return "false";
	}

	/*public String insertIntoErrorLog(final StudentUploadBean upload,final Exception ex, final String path){
	
		String ss = (String)transactionTemplate.execute(new TransactionCallback() {
			public String doInTransaction(TransactionStatus tStatus1) {
				System.out.println("before save point declaration");
				Object savepoint1 = null;
				String newStatus = "false";
				try {
					savepoint1 = tStatus1.createSavepoint();
					//*******************************************
					String arr[] = ex.getCause().toString().split(":");
					System.out.println("inside inner exception in SRSH sql ::::::"+arr[3]+" : "+arr[4]);					
					upload.getErrorLogBean().setErrorDescription(arr[3]+" : "+arr[4]);
					String answer = (String)getSqlMapClientTemplate().insert("studentUpload.insertIntoErrorLog", upload.getErrorLogBean());
					logObj.info("*************after inserting in error log for enrollement no : "+upload.getErrorLogBean().getRollNumber());
					System.out.println("****sysout with answer*****"+answer+"****after inserting in error log for enrollement no : "+upload.getErrorLogBean().getRollNumber());					
					newStatus = "true";
					System.out.println("staus in insert error log"+newStatus);
					//*********************************************																												
				} catch (Exception ex) {
					System.out.println("in exception of error log entry ");
					logObj.error("There is exception in error log entry : "+ex);
					newStatus = "false";
					tStatus1.rollbackToSavepoint(savepoint1);					
				}
				return newStatus;
			}
		});
		return ss;
	}*/
	
	public String insertIntoErrorLog(StudentUploadBean upload, Exception ex, String path){
				String newStatus = "false";
				try {
					String arr[] = ex.getCause().toString().split(":");
					System.out.println("inside inner exception in SRSH sql ::::::"+arr[3]+" : "+arr[4]);					
					upload.getErrorLogBean().setErrorDescription(arr[3]+" : "+arr[4]);
					// Create file 
					  FileWriter fstream = new FileWriter(path+"result_file_error_log.txt");
					  BufferedWriter out = new BufferedWriter(fstream);
					  out.write("Roll Number :"+upload.getErrorLogBean().getRollNumber());					  
					  out.newLine();
					  out.write("Error       :"+upload.getErrorLogBean().getErrorDescription());
					  //Close the output stream
					  out.close();					
					String answer = (String)getSqlMapClientTemplate().insert("studentUpload.insertIntoErrorLog", upload.getErrorLogBean());
					logObj.info("*************after inserting in error log for enrollement no : "+upload.getErrorLogBean().getRollNumber());
					System.out.println("****sysout with answer*****"+answer+"****after inserting in error log for enrollement no : "+upload.getErrorLogBean().getRollNumber());					
					newStatus = "There is some error, kindly check result_file_error_log.txt file.";					
					//*********************************************																												
				} catch (Exception ex1) {
					System.out.println("in exception of error log entry ");
					logObj.error("There is exception in error log entry : "+ex1);
					newStatus = "There is some error, kindly check result_file_error_log.txt file.";						
				}
				return newStatus;
	}
	/**
	 * This method add student Detail into student registration semester header(SRSH) table
	 * @param list of objects of StudentUploadBean
	 * @return String containing info(true/false) whether a record is inserted or not
	 */
	public String insertIntoSRSH(final List<StudentUploadBean> studentRecordList, final String path) {
		double theoryCredit;
		double practicalCredit;
		double totalCredits;
		String status="false";
		for(StudentUploadBean upload:studentRecordList){
			theoryCredit = 0;
			practicalCredit = 0;
			totalCredits = 0;
			for(StudentUploadBean uploadCourse : upload.getCourseList()){				
				upload.setCourseCode(uploadCourse.getCourseCode().trim());
				if(!(upload.getCourseCode().length()==0)){
					System.out.println("course code "+upload.getCourseCode()+"sem start date " +upload.getSemesterStartDate()+ " sem end date "+upload.getSemesterEndDate());
					StudentUploadBean newBean = new StudentUploadBean();
					newBean = (StudentUploadBean)getSqlMapClientTemplate().queryForObject("studentUpload.getCourseCredits", upload);
					System.out.println("\n"+newBean.getCourseClassification());
					if(newBean.getCourseClassification().equalsIgnoreCase("t")){
						theoryCredit=theoryCredit+Double.valueOf(newBean.getCredits());						
					}
					else if(newBean.getCourseClassification().equalsIgnoreCase("p")){
						practicalCredit = practicalCredit + Double.valueOf(newBean.getCredits());				
					}
					else{
						logObj.error("Course classfication in course master is neither T nor P for course : "+upload.getCourseCode());
						status="Course classfication in course master is neither T nor P for course : "+upload.getCourseCode();
						return status;
					}
				}
			}
			totalCredits = theoryCredit +  practicalCredit;
//			System.out.println("total credits "+totalCredits);
//			System.out.println("theory Credit "+theoryCredit);
//			System.out.println("practical Credit "+practicalCredit);
			upload.setRegisteredTheoryCreditExcludingAudit(String.valueOf(theoryCredit));
			upload.setRegisteredPracticalCreditExcludingAudit(String.valueOf(practicalCredit));
			upload.setRegisteredCredit(String.valueOf(String.valueOf(totalCredits)));
			upload.setRegistrationCreditExcludingAudit(String.valueOf(String.valueOf(totalCredits)));
			upload.setStatusSRSH("REG");
			try{
				getSqlMapClientTemplate().insert("studentUpload.insertIntoSRSH",upload);
				System.out.println("after detail insert");
				status="true";
			}catch(Exception ex) {
				logObj.info("Exception in SRSH Insert for enrollment number : "+upload.getErrorLogBean().getRollNumber());
				ex.printStackTrace();
				String newStatus = insertIntoErrorLog(upload,ex,path);	
				System.out.println("returning to srsh newStatus "+newStatus);
				status = "false";
				return status;
			}			
		}
		System.out.println("status after try/catch "+status);
		return status;
	}

	/**
	 * This method add student Detail into student aggregate table
	 * @param list of objects of StudentUploadBean
	 * @return String containing info(true/false) whether a record is inserted or not
	 */
	public String insertIntoStudentAggregate(final List<StudentUploadBean> studentRecordList, final String path) {
		String status="false";
		for(StudentUploadBean upload:studentRecordList){
			try{
			getSqlMapClientTemplate().insert("studentUpload.insertIntoStudentAggregate",upload);
//			System.out.println("after student aggregate insert");
			status = "true";
			}catch(Exception ex) {
				logObj.info("Exception in student_aggregate Insert for enrollment number : "+upload.getErrorLogBean().getRollNumber());
				ex.printStackTrace();
				insertIntoErrorLog(upload,ex, path);				
				status = "false";
				return status;
			}	
		}						
		return status;
	}

	/**
	 * This method compute sgpa and cgpa
	 * @param object of StudentUploadBean
	 * @return HashMap containing SGPA/CGPA info
	 */	
	@SuppressWarnings("unused")

	private Map<String, Double> computeSgpaCgpa(StudentUploadBean upload) {
		Map<String,Double> a = new HashMap<String,Double>();
		a.put("pointSecuredSgpa", 0.0);
		a.put("pointSecuredCgpa", 0.0);
		a.put("earnedCreditsCgpa", 0.0);
		Double finalGradePoint = new Double(upload.getCummulativeGrades());
		Double earnedCredit = new Double(upload.getEarnedCredits());
		Double courseCredit = new Double(upload.getCredits());
		String courseClassification = upload.getCourseClassification();
//		System.out.println("course classification "+courseClassification);
		if(upload.getCourseCategory().equalsIgnoreCase("reg")){
			a.put("pointSecuredSgpa", finalGradePoint*courseCredit);
			a.put("pointSecuredCgpa", finalGradePoint*earnedCredit);
			a.put("earnedCreditsCgpa", earnedCredit);
		}
//		System.out.println(a.get("pointSecuredSgpa")+" : "+a.get("pointSecuredCgpa")+" : "+a.get("earnedCreditsCgpa"));
		return a;
	}
	
	/**
	 * This method compute theory credit
	 * @param object of StudentUploadBean
	 * @return HashMap containing theory credit info
	 */	
	@SuppressWarnings("unused")
	private Map<String, Double> computeTheoryCredit(StudentUploadBean upload) {
		Map<String,Double> a = new HashMap<String,Double>();
		a.put("theoryCredits", 0.0);
		a.put("practicalCredits", 0.0);
		a.put("totalCredits", 0.0);
		Double earnedCredit = new Double(upload.getEarnedCredits());
		String courseClassification = upload.getCourseClassification();
		System.out.println("course classification "+courseClassification);
		if(courseClassification.equalsIgnoreCase("t")){
			a.put("theoryCredits", earnedCredit);
			a.put("practicalCredits", 0.0);
			a.put("totalCredits", earnedCredit);
		}
		else if(courseClassification.equalsIgnoreCase("p")){
			a.put("theoryCredits", 0.0);
			a.put("practicalCredits", earnedCredit);
			a.put("totalCredits", earnedCredit);
		}
		System.out.println(a.get("theoryCredits")+" : "+a.get("practicalCredits")+" : "+a.get("totalCredits"));
		return a;
	}
	
	/**
	 * This method compute earned credit
	 * @param object of StudentUploadBean
	 * @return String containing earned credit
	 */	
	@SuppressWarnings("unused")
	private String computeEarnedCredits(StudentUploadBean upload) {
		String courseCategory = upload.getCourseCategory();
		String credits = upload.getCredits();
		String earnedCredits = "";
		String internalgrades = upload.getInternalGrades();
		String  d = upload.getCummulativeGrades();
		System.out.println("final grade in string "+d);
		Double finalGradePoint = new Double(d);
		System.out.println("final grade point "+finalGradePoint);
		System.out.println("course category "+courseCategory);
		if(courseCategory.equalsIgnoreCase("reg")){
			if(finalGradePoint>=3){
				earnedCredits = credits;
			}
			else{
				earnedCredits = "0.00";
			}
		}
		else{
			if(internalgrades.equalsIgnoreCase("np")|| internalgrades.equalsIgnoreCase("s")){
				earnedCredits = credits;
			}
			else{
				earnedCredits = "0.00";
			}
		}
		System.out.println("earned credits "+earnedCredits);
		return earnedCredits;
	}

	/**
	 * This method add student Detail into student mark summary table
	 * @param list of objects of StudentUploadBean
	 * @return String containing info(true/false) whether a record is inserted or not
	 */
	public String insertIntoStudentMarksSummary(List<StudentUploadBean> studentRecordList, String path) {
		String status = "false";
		String internalGrade="";
		for(StudentUploadBean upload:studentRecordList){
			try{
				System.out.println("program code "+upload.getProgramCode());
				System.out.println("program course key "+upload.getProgramCourseKey()+" roll number "+upload.getRollNumber());
				for(StudentUploadBean uploadCourse : upload.getCourseList()){
					upload.setCourseCode(uploadCourse.getCourseCode().trim());
					if(!(upload.getCourseCode().length()==0)){
						System.out.println("course code "+upload.getCourseCode()+"sem start date " +upload.getSemesterStartDate()+ " sem end date "+upload.getSemesterEndDate());
						
						upload.setInternalMarks(uploadCourse.getInternalMarks());
						upload.setExternalMarks(uploadCourse.getExternalMarks());
						upload.setCummulativeMarks(uploadCourse.getCummulativeMarks());
						upload.setInternalGrades(uploadCourse.getInternalGrades());
						upload.setExternalGrades(uploadCourse.getExternalGrades());
						System.out.println("external grades "+uploadCourse.getExternalGrades());
						upload.setCummulativeGrades(uploadCourse.getCummulativeGrades());
						if(upload.getInternalGrades()==null && upload.getExternalGrades()==null){
//							if(new Double(upload.getCummulativeGrades())==9.99){
//								upload.setCummulativeGrades("10.0");
//							}
							internalGrade=computeInternalGrade(upload);
							if(internalGrade.length()!=0){
								upload.setInternalGrades(internalGrade);
							}
						}
//						String earnedCredits = computeEarnedCredits(upload);
//						System.out.println("earned credit is "+earnedCredits);
//						upload.setEarnedCredits(earnedCredits);
						getSqlMapClientTemplate().insert("studentUpload.insertIntoSMS", upload);
					}
				}	
			}catch(Exception ex) {
				logObj.info("Exception in student_marks_summary Insert for enrollment number : "+upload.getErrorLogBean().getRollNumber()+" "+ex.getCause());
				ex.printStackTrace();
				insertIntoErrorLog(upload,ex, path);				
				status = "false";
				return status;
			}					
		}
		status = "true";
		return status;
	}

	
	private String computeInternalGrade(StudentUploadBean upload) {
		StudentUploadBean studentUploadBean = new StudentUploadBean();
		studentUploadBean = (StudentUploadBean)getSqlMapClientTemplate().queryForObject("studentUpload.getGrades", upload);
		return studentUploadBean.getInternalGrades();
	}

	/**
	 * This method add student Detail into student program table
	 * @param list of objects of StudentUploadBean
	 * @return String containing info(true/false) whether a record is inserted or not
	 */
	public String insertIntoStudentProgram(List<StudentUploadBean> studentRecordList, String path) {
		String status = "false";
//		try{
		for(StudentUploadBean upload:studentRecordList){
			try{
			System.out.println(upload.getProgramCode());
			System.out.println("semester sequesnce "+upload.getSemesterCode());
			System.out.println("branch id "+upload.getBranchId());
			System.out.println("status_student_program "+upload.getStatusStudentProgram());
			upload.setPassedFromSession(null);
			upload.setPassedToSession(null);
			if(upload.getStatusStudentProgram().equalsIgnoreCase("pas")){
				upload.setPassedFromSession(upload.getSessionStartDate());
				upload.setPassedToSession(upload.getSessionEndDate());
			}
			System.out.println("attempt number "+upload.getAttemptNumber());
			List<StudentUploadBean> stuList = (List<StudentUploadBean>)getSqlMapClientTemplate().queryForList("studentUpload.selectStudentProgram", upload);
			System.out.println("list size :"+stuList.size());
			if(stuList.size()==0){
				upload.setCGPA("0.0");
				getSqlMapClientTemplate().insert("studentUpload.insertIntoStudentProgram", upload);
				System.out.println("insert for "+upload.getEnrollmentNumber());
			}
			else{
				getSqlMapClientTemplate().update("studentUpload.updateStudentProgram", upload);
				System.out.println("update for "+upload.getEnrollmentNumber());
			}
			}catch(Exception ex) {
				logObj.info("Exception in student_program Insert for enrollment number : "+upload.getErrorLogBean().getRollNumber());
				ex.printStackTrace();
				String newStatus = insertIntoErrorLog(upload,ex,path);	
				System.out.println("returning to student program newStatus "+newStatus);			
				status = "false";
				return status;
			}
		}//end for
		status = "true";
//		}catch(Exception ex) {
//			logObj.error("error in insert student program in impl", ex.getCause());
//			System.out.println("inside outer exception in student program sql");
//			status = "false";
//			return status;
//		}
		return status;
	}

	/**
	 * This method add student Detail into student course table
	 * @param list of objects of StudentUploadBean
	 * @return String containing info(true/false) whether a record is inserted or not
	 */
	public String insertIntoStudentCourse( List<StudentUploadBean> studentRecordList,  String path) {
		String status="false";
		for(StudentUploadBean upload:studentRecordList){
			try{
				for(StudentUploadBean uploadCourse : upload.getCourseList()){
					upload.setCourseCode(uploadCourse.getCourseCode().trim());
					upload.setOriginalCourseCode(uploadCourse.getOriginalCourseCode());
					upload.setCourseStatus(uploadCourse.getCourseStatus());
					upload.setStudentStatus(uploadCourse.getStudentStatus());
					
//					upload.setCourseGroup(uploadCourse.getCourseGroup());
					upload.setStudentStatus("REG");
					System.out.println("course code "+upload.getCourseCode());
					System.out.println("semester code "+upload.getSemesterCode());
					System.out.println("program course key "+upload.getProgramCourseKey()+"roll number :"+upload.getRollNumber());
					if(upload.getCourseCode().length()!=0){
						String courseGroup = computeCourseGroup(upload);
						if(courseGroup.length()==0){
							logObj.error("No course Group available for the course code "+upload.getCourseCode()+" in program_course_detail table");
							throw new Exception();
						}
						upload.setCourseGroup(courseGroup);						
						System.out.println("inside if "+upload.getCourseCode().length());
						getSqlMapClientTemplate().insert("studentUpload.insertIntoStudentCourse", upload);
					}else{
						System.out.println("inside else "+upload.getCourseCode());
					}
				}
			}catch(Exception ex) {
				logObj.info("Exception in student_course Insert for enrollment number : "+upload.getErrorLogBean().getRollNumber());
				ex.printStackTrace();
				insertIntoErrorLog(upload,ex,path);				
				status = "false";
				return status;
			}		
		}
		System.out.println("after detail insert");
		status = "true";
		return status;
	}

	private String computeCourseGroup(StudentUploadBean upload) throws Exception {
		List<StudentUploadBean> studentUploadBean = new ArrayList<StudentUploadBean>();
		String courseGroup = "";
		studentUploadBean = getSqlMapClientTemplate().queryForList("studentUpload.selectCourseGroup", upload);
		if(studentUploadBean!=null){
			if(studentUploadBean.size()>1){
				logObj.error("more than one record for course group is returned");
				throw new Exception();
			}
			courseGroup = studentUploadBean.get(0).getCourseGroup();
		}
		return courseGroup;
	}

	/**
	 * This method get program course key
	 * @param object of StudentUploadBean
	 * @return object of StudentUploadBean containing program course key
	 */
	public StudentUploadBean getPrgCourseKey(StudentUploadBean sub) {
		StudentUploadBean studentUploadBean = new StudentUploadBean();
		try{
			studentUploadBean = (StudentUploadBean) getSqlMapClient().queryForObject("studentUpload.getProgramCourseKey",sub);
			sub.setProgramCourseKey(studentUploadBean.getProgramCourseKey());
		}catch (Exception e) {
			logObj.info("\n program course key not found for the given combination of program, branch and specialization ");
			System.out.println(e.getMessage());
		}
		return sub;
	}

	/**
	 * This method get semester start date and end date
	 * @param object of StudentUploadBean
	 * @return object of StudentUploadBean containing semester start date and end date
	 */
	public StudentUploadBean getSemesterDates(StudentUploadBean sub) {
		StudentUploadBean studentUploadBean = new StudentUploadBean();
		try{
			studentUploadBean = (StudentUploadBean) getSqlMapClient().queryForObject("studentUpload.getSemesterDates",sub);
			sub.setSemesterStartDate(studentUploadBean.getSemesterStartDate());
			sub.setSemesterEndDate(studentUploadBean.getSemesterEndDate());
		}catch (Exception e) {
			logObj.info("\n Semester start date and end date is not specified in program_registration table for the given session ");
			System.out.println("error in get semester dates"+e.getMessage());
		}
		return sub;
	}	

	/**
	 * This method get semester code for final semester
	 * @param object of StudentUploadBean
	 * @return object of StudentUploadBean containing semester code for final semester
	 */
	public StudentUploadBean getFinalSemester(StudentUploadBean sub) {
		StudentUploadBean studentUploadBean = new StudentUploadBean();
		try{
			studentUploadBean = (StudentUploadBean) getSqlMapClient().queryForObject("studentUpload.getFinalSemester",sub);
			sub.setFinalSemesterCode(studentUploadBean.getFinalSemesterCode());			
		}catch (Exception e) {
			logObj.info("\n For the specified program, final semester is not given in program_term_detail table");
			System.out.println("error in getFinalSemester"+e.getMessage());
		}
		return sub;
	}	

	/**
	 * This method get division from the CGPA
	 * @param object of StudentUploadBean
	 * @return object of StudentUploadBean containing division
	 */
	public StudentUploadBean getDivisionFromCGPA(StudentUploadBean sub) {
		StudentUploadBean studentUploadBean = new StudentUploadBean();
		try{
			studentUploadBean = (StudentUploadBean) getSqlMapClient().queryForObject("studentUpload.getDivisionFromCGPA",sub);
			sub.setDivision(studentUploadBean.getDivision());
		}catch (Exception e) {
			logObj.error("\n division for the CGPA :"+sub.getCGPA()+" is not in CGPA_division table ");
			System.out.println("error in cgpa division"+e.getMessage());
			sub.setDivision(null);
		}
		return sub;
	}

	/**
	 * This method is called from the controller for the final semester records entry
	 * @param list of objects of StudentUploadBean containing course details of a particular student
	 * @param list of objects of StudentUploadBean containing student records
	 * @return String containing info whether the records are inserted successfully
	 */
	public String finalSemesterEntry(List<StudentUploadBean> studentCourseList, final List<StudentUploadBean> studentRecords,final String path) {
		//		##update student_program
		//	 	##SRSH
		//	   	##student_courses
		//	   	##student_marks_summary
		//	   	##student_aggregate
		String s = (String) transactionTemplate.execute(new TransactionCallback() {
			public String doInTransaction(TransactionStatus tStatus) {
				System.out.println("before save point declaration in final semester entry");
				Object savepoint = null;
				String status="false";
				try {
					savepoint = tStatus.createSavepoint();
					for(StudentUploadBean upload:studentRecords){
						System.out.println("semester sequence "+upload.getSemesterCode().substring(2));
						int seq = (Integer.parseInt(upload.getSemesterCode().substring(2)));
						/**
						 * if semester is even then check for the odd sem remedial index value
						 */
						if(seq%2==0){
							String previousSem = "SM".concat((seq-1)+"");
							System.out.println("previous semester "+previousSem);
							StudentUploadBean uploadRem = new StudentUploadBean();
							uploadRem.setSemesterCode(previousSem);
							chkRemCases(upload,uploadRem);
						}
					}
					status = insertIntoStudentProgram(studentRecords,path);
					if(status.equalsIgnoreCase("true")){
						status = insertIntoSRSH(studentRecords, path);
						if(status.equalsIgnoreCase("true")){
							status = insertIntoStudentCourse(studentRecords, path);
							if(status.equalsIgnoreCase("true")){
								status = insertIntoStudentMarksSummary(studentRecords, path);
								if(status.equalsIgnoreCase("true")){
									status = insertIntoStudentAggregate(studentRecords, path);
									return status;
								}
							}
						}
					}
					if(!status.equalsIgnoreCase("true")){
						tStatus.rollbackToSavepoint(savepoint);
					}
					return status;
				} catch (Exception ex) {
					System.out.println("inside inner exception in final semester entry sql"+ex);
					logObj.error("Kindly check the exception in final semester entry :"+ex.getCause());
					status = "false";					
					tStatus.rollbackToSavepoint(savepoint);
					return status;
				}
			}
		});
		return s;			
	}

	/**
	 * This method is called from the controller for the first semester records entry
	 * @param list of objects of StudentUploadBean containing course details of a particular student
	 * @param list of objects of StudentUploadBean containing student records
	 * @return String containing info whether the records are inserted successfully
	 */
	public String firstSemesterEntry(final List<StudentUploadBean> studentCourseList, final List<StudentUploadBean> studentRecords, final String path) {
		//		## entry in student_master
		//   	##entry in enrollment_personal_detail
		//		## entry in student_program
		//		##SRSH
		//		##student_courses
		//		##student_marks_summary
		//		##student_aggregate
		String s = (String) transactionTemplate.execute(new TransactionCallback() {
			public String doInTransaction(TransactionStatus tStatus) {
				System.out.println("inside new impl for first sem");
				System.out.println("before save point declaration in first semester entry");
				Object savepoint = null;
				String status="false";
				try {
					savepoint = tStatus.createSavepoint();
					status = insertIntoStudentMaster(studentRecords);
					if(status.equalsIgnoreCase("true")){
						status = insertIntoStudentProgram(studentRecords,path);
						if(status.equalsIgnoreCase("true")){
							status = insertIntoSRSH(studentRecords,path);
							if(status.equalsIgnoreCase("true")){
								status = insertIntoStudentCourse(studentRecords,path);
								if(status.equalsIgnoreCase("true")){
									status = insertIntoStudentMarksSummary(studentRecords,path);
//									if(status.equalsIgnoreCase("true")){
//										status = insertIntoStudentAggregate(studentRecords,path);
										//return status;
//									}
								}
							}
						}
					}
					if(!status.equalsIgnoreCase("true")){
						tStatus.rollbackToSavepoint(savepoint);
					}
					return status;
				} catch (Exception ex) {
					System.out.println("inside inner exception in first semester entry sql"+ex);
					logObj.error("Exception in first semester entry", ex.getCause());
					status = "false";
					tStatus.rollbackToSavepoint(savepoint);
					return status;
				}
			}
		});
		return s;	
	}

	/**
	 * method to check odd semester remedial index records and if exist then update the previous semesters records accordingly
	 * @param object of StudentUploadBean containing student record
	 * @param object of StudentUploadBean containing previous semester's program course key
	 * @return nothing
	 */
	public void chkRemCases(StudentUploadBean upload, StudentUploadBean uploadRem) throws Exception{
		if(upload.getRemedialCourseList().size()!=0){
			if(upload.getResultCode().equalsIgnoreCase("p")){
				uploadRem.setProgramId(upload.getProgramId());
				uploadRem.setBranchId(upload.getBranchId());
				uploadRem.setSpecializationId(upload.getSpecializationId());
				uploadRem = getPrgCourseKey(uploadRem);
				uploadRem.setRollNumber(upload.getRollNumber());
				uploadRem.setStatusSRSH(upload.getStatusSRSH());
				System.out.println("program course key original"+upload.getProgramCourseKey());
				System.out.println("program course key previous case"+uploadRem.getProgramCourseKey());
				System.out.println("roll number"+uploadRem.getRollNumber());
				List<StudentUploadBean> stuList = upload.getRemedialCourseList();
				for(StudentUploadBean remUpload:stuList){
					uploadRem.setCourseCode(remUpload.getCourseCode());
					System.out.println("reme course code "+remUpload.getCourseCode());
					uploadRem.setCummulativeGrades(remUpload.getCummulativeGrades());
					uploadRem.setInternalGrades(null);
					uploadRem.setExternalGrades(null);
					getSqlMapClient().update("studentUpload.updateSMS", uploadRem);
					getSqlMapClient().update("studentUpload.updateStudentCourse", uploadRem);
					getSqlMapClient().update("studentUpload.updateSRSH", uploadRem);
				}//end for						
			}
		}
	}

	/**
	 * This method is called from the controller for the mid semester(neither first nor final semester) records entry
	 * @param list of objects of StudentUploadBean containing course details of a particular student
	 * @param list of objects of StudentUploadBean containing student records
	 * @return String containing info whether the records are inserted successfully
	 */
	public String midSemesterEntry(final List<StudentUploadBean> studentCourseList, final List<StudentUploadBean> studentRecords,final String path) {
		//		##SRSH
		//   	##student_courses
		//   	##student_marks_summary
		//   	##student_aggregate
		String s = (String) transactionTemplate.execute(new TransactionCallback() {
			public String doInTransaction(TransactionStatus tStatus) {
				System.out.println("before save point declaration");
				Object savepoint = null;
				String status="false";
				try {
					savepoint = tStatus.createSavepoint();
					//*******************************************

					for(StudentUploadBean upload:studentRecords){
						System.out.println("semester sequence "+upload.getSemesterCode().substring(2));
						int seq = (Integer.parseInt(upload.getSemesterCode().substring(2)));
						if(seq%2==0){
							String previousSem = "SM".concat((seq-1)+"");
							System.out.println("previous semester "+previousSem);
							StudentUploadBean uploadRem = new StudentUploadBean();
							uploadRem.setSemesterCode(previousSem);
							chkRemCases(upload,uploadRem);
						}
					}

					//*********************************************	
					status = insertIntoStudentProgram(studentRecords,path);
					if(status.equalsIgnoreCase("true")){
						status = insertIntoSRSH(studentRecords,path);
						if(status.equalsIgnoreCase("true")){
							status = insertIntoStudentCourse(studentRecords,path);
							if(status.equalsIgnoreCase("true")){
								status = insertIntoStudentMarksSummary(studentRecords,path);
								if(status.equalsIgnoreCase("true")){
									status = insertIntoStudentAggregate(studentRecords,path);
									return status;
								}
							}
						}
					}
					if(!status.equalsIgnoreCase("true")){
						tStatus.rollbackToSavepoint(savepoint);
					}
					return status;
				} catch (Exception ex) {
					System.out.println("inside inner exception in mid semester entry sql"+ex);
					logObj.error("Exception in mid semester entry", ex.getCause());
					tStatus.rollbackToSavepoint(savepoint);
					status = "false";
					return status;
				}
			}
		});
		return s;
	}
}
