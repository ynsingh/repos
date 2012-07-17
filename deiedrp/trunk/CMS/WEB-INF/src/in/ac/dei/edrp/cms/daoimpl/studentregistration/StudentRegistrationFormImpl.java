/*
 * @(#) StudentRegistrationFormImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.studentregistration;


import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.studentregistration.StudentRegistrationFormDao;
import in.ac.dei.edrp.cms.domain.studentregistration.CourseInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.dao.DataAccessException;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *Server-side implementation of RPC service <code> StudentRegistrationFormRPC </code>
 * @author Manpreet Kaur
 * @date 22-01-2011
 * @version 1.0
 */
public class StudentRegistrationFormImpl extends SqlMapClientDaoSupport
    implements StudentRegistrationFormDao {
    private static Logger logObj = Logger.getLogger(StudentRegistrationFormImpl.class);
    TransactionTemplate transactionTemplate = null;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
    * Method to populate student's details to show on registration form
    * @param registrationRollNumber registration number of the applicant or roll number of existing student
    * @return student's details
    */
    @SuppressWarnings("unchecked")
    public List<StudentInfoGetter> getStudentDetails(
        String registrationRollNumber) throws Exception {
        StudentInfoGetter[] stdResultObject1 = null;

        try {
            List<StudentInfoGetter> resultList = null;
            List<?> resultList1;
            StudentInfoGetter stdObject = new StudentInfoGetter();
            stdObject.setRollNumber(registrationRollNumber);

            resultList1 = getSqlMapClientTemplate()
                              .queryForList("studentRegistration.checkStudentRecord",
                    stdObject);
            stdResultObject1 = resultList1.toArray(new StudentInfoGetter[resultList1.size()]);

            
            if (stdResultObject1.length > 0) {
                if (stdResultObject1[0].getAdmissionMode()
                                           .equalsIgnoreCase("SWT")) {
                    resultList = getSqlMapClientTemplate()
                                     .queryForList("studentRegistration.getStudentDetailsForSwt",
                            stdObject);
                } else if (stdResultObject1[0].getAdmissionMode()
                                                  .equalsIgnoreCase("NOR")) {
                	System.out.println("coming in normal");
                	
                    resultList = getSqlMapClientTemplate()
                                     .queryForList("studentRegistration.getStudentDetailsForNor",
                            stdObject);
                    System.out.println("details length "+resultList.size());
                    
                } else if (stdResultObject1[0].getAdmissionMode()
                                                  .equalsIgnoreCase("NEW")) {
                    resultList = getSqlMapClientTemplate()
                                     .queryForList("studentRegistration.getStudentDetailsForNew",
                            stdObject);
                } else {
                    throw new MyException("Invalid registration mode");
                }
                if(resultList.size()>0){
                    return resultList;
                }
                else{   
                	throw new MyException("Student details missing");
                }
            } else {
                throw new MyException("Registration not allowed");
            }
        } catch (MyException e) {
//        	System.out.println(e.getMessage());
            throw new MyException(e.getMessage());
        }catch (Exception e) {
            logObj.error("Exception in getStudentDetails" + " : " + e);
            throw e;
        }
        
    }

    /**
     * Method to get core courses for specific program,branch,semester basis
     * @param inputObj containing program, branch, semester, specialization
     * @return list of core/compulsary courses
     */
    @SuppressWarnings("unchecked")
    public List<CourseInfoGetter> getStudentCoreSubject(
        StudentInfoGetter inputObj,StringTokenizer courseGroup) throws Exception {
        try {
        	List<CourseInfoGetter> coreSubjectResult=new ArrayList<CourseInfoGetter>();
        	
        	while(courseGroup.hasMoreTokens()){
        		String token=courseGroup.nextToken();
       		
        		inputObj.setComponentCode(token);
            List<CourseInfoGetter> coreSubjectList = getSqlMapClientTemplate()
                                                         .queryForList("studentRegistration.getCoreGroupSubjects",inputObj);
        	for(int i=0;i<coreSubjectList.size();i++){
        		coreSubjectResult.add(coreSubjectList.get(i));
        	}
        	}
        	System.out.println("rohit impl size"+coreSubjectResult);
            return coreSubjectResult;
        } catch (Exception e) {
            logObj.error("Exception in getStudentCoreSubject" + " : " + e);
            throw e;
        }
    }

    /**
     * Method to get registration deadlines for specific program,branch,specialization semester basis
     * @param inputObj containing program, branch, semester, specialization
     * @return registration deadlines
     */
    @SuppressWarnings("unchecked")
    public List<StudentInfoGetter> getRegistrationDeadlines(
        StudentInfoGetter inputObj) throws Exception {
        try {
            List<StudentInfoGetter> deadlines;
            deadlines= getSqlMapClientTemplate()
                                                    .queryForList("studentRegistration.getRegistrationDeadlines",
                    inputObj);
            StudentInfoGetter[] resultObj = deadlines.toArray(new StudentInfoGetter[deadlines.size()]);

            if (resultObj.length == 0) {
                throw new MyException("Registration Deadlines not specified yet");
            } else {
                if (resultObj[0].getRegistrationStartDate()
                                    .before(new java.util.Date()) &&
                        resultObj[0].getRegistrationEndDate()
                                        .after(new java.util.Date())) {
                    resultObj[0].setVerified("Yes");
                } else {
                    resultObj[0].setVerified("No");
                }

                return deadlines;
            }
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        catch (Exception e) {
            logObj.error("Exception in getStudentCoreSubject " + " : " + e);

            throw e;
        }
    }

    /**
     * Method to get Elective courses for specific program,branch,semester basis
     * @param inputObj containing program, branch, semester
     * @return list of elective courses with respective groups and minimum and maximum selections
     */
    @SuppressWarnings("unchecked")
    public List<CourseInfoGetter> getElectiveSubject(StudentInfoGetter inputObj)
        throws Exception {
        try {
            List<CourseInfoGetter> electiveSubjects = getSqlMapClientTemplate()
                                                          .queryForList("studentRegistration.getElectiveSubjects",
                    inputObj);

            return electiveSubjects;
        } catch (Exception e) {
            logObj.error("Exception in getElectiveSubject" + " : " + e);
            throw e;
        }
    }

    /**
     * Method to get minimum required number of courses of each type for specific program,branch,semester basis
     * @param inputObj containing program, branch, semester
     * @return list of courses and their respective minimum selections required
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CourseInfoGetter> getCourseTypeSummary(
        StudentInfoGetter inputObj) throws Exception {
        try {
            List<CourseInfoGetter> courseSummary = getSqlMapClientTemplate()
                                                       .queryForList("studentRegistration.getCourseTypeSummary",
                    inputObj);

            return courseSummary;
        } catch (Exception e) {
            logObj.error("Exception in getCourseTypeSummary" + " : " + e);
            throw e;
        }
    }

    /**
     * Method for inserting student details into database
     * @param progDetails contains academic details of student
     * @param courseDetails contains list of courses selected by student
     * @return
     * @throws Exception
     */
    public String registerStudent(final StudentInfoGetter progDetails,
        final CourseInfoGetter[] courseDetails) throws DataAccessException {
        return (String) transactionTemplate.execute(new TransactionCallback() {
                public Object doInTransaction(TransactionStatus arg0) {
                    try {
                        String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                                                                                             .substring(0,
                                19));

                        progDetails.setInsertTime(date);
                        progDetails.setCreatorId(progDetails.getStudentId());
                      //  progDetails.setModificationTime(null);
                        progDetails.setModifierId(progDetails.getStudentId());

                        /*
                         * implementing batch process 
                         */
                        getSqlMapClientTemplate()
                            .insert("studentRegistration.insertTempStudentMaster",
                            progDetails);
                        
                        StudentInfoGetter addressObject= (StudentInfoGetter) getSqlMapClientTemplate().queryForObject("studentRegistration.getStudentAddress",
                        progDetails);
                        
                        String ss=addressObject.getEnrollmentNumber();
                       System.out.println(ss);
                        if((ss==null)||(ss=="")){
                        	System.out.println("rohit checkkkkkkkkkkkkkkkkkkkkkkkkk");
                        	addressObject.setEnrollmentNumber("");	
                        }else{
                        	addressObject.setEnrollmentNumber(ss);
                        }
                        addressObject.setModifierId(progDetails.getStudentId());
                        getSqlMapClientTemplate()
                        .update("studentRegistration.updateStudentAddress",
                        		addressObject);
                        
                        
                        getSqlMapClientTemplate()
                            .insert("studentRegistration.insertTempStudentProgram",
                            progDetails);

                        for (int i = 0; i < courseDetails.length; i++) {
                            progDetails.setCourseCode(courseDetails[i].getCourseCode());
                            progDetails.setCourseGroupCode(courseDetails[i].getCourseGroupCode());
                            progDetails.setCourseName(courseDetails[i].getCourseName());		// Course Name Added By Dheeraj to be inserted in temp_student_courses
                            progDetails.setStatus("REG");
                            getSqlMapClientTemplate()
                                .insert("studentRegistration.insertTempStudentCourse",
                                progDetails);
                        }

                        if (progDetails.getAdmissionMode()
                                           .equalsIgnoreCase("NEW")) {
                            StudentInfoGetter[] componentList = getVerificationComponentCodes(progDetails,
                                    "New");

                            for (int loop = 0; loop < componentList.length;
                                    loop++) {
                                progDetails.setComponentCode(componentList[loop].getComponentCode());
                                getSqlMapClientTemplate()
                                    .insert("studentRegistration.insertIntoVerificationTable",
                                    progDetails);
                            }
                        } else if (progDetails.getAdmissionMode()
                                                  .equalsIgnoreCase("SWT")) {
                            StudentInfoGetter[] componentList = getVerificationComponentCodes(progDetails,
                                    "Swt");
                            progDetails.setRegistrationNumber(progDetails.getRollNumber());

                            for (int loop = 0; loop < componentList.length;
                                    loop++) {
                                progDetails.setComponentCode(componentList[loop].getComponentCode());
                                getSqlMapClientTemplate()
                                    .insert("studentRegistration.insertIntoVerificationTable",
                                    progDetails);
                            }
                        }

//                        System.out.println("reg_roll_number=" +
//                            progDetails.getRegRollNumber() + "\n program_id=" +
//                            progDetails.getProgramId() + "\n entity_id=" +
//                            progDetails.getEntityId() + "\n branch=" +
//                            progDetails.getBranchCode() + "\n specialization=" +
//                            progDetails.getNewSpecialization() +
//                            "\n semester_code" + progDetails.getSemesterCode());

                        getSqlMapClientTemplate()
                            .update("studentRegistration.updateRegistrationProcessingFlag",
                            progDetails);

                        getSqlMapClientTemplate().getDataSource().getConnection()
                            .commit();
                    } catch (Exception e) {
                        logObj.error(e);
                        logObj.debug(e);

                    throw new MyException("Registration Failed");
                    }

                    return null;
                }
            });
    }

    /**
     * Method to get list of verification components that has to be verified
     * before final transfer of data into master tables
     * @param progDetails
     * @return
     */
    public StudentInfoGetter[] getVerificationComponentCodes(
        StudentInfoGetter progDetails, String mode) {
        try {
            StudentInfoGetter[] resultObj = null;
            List<?> resultList1 = null;

            if (mode.equalsIgnoreCase("New")) {
                resultList1 = getSqlMapClientTemplate()
                                  .queryForList("studentRegistration.getVerificationComponents",
                        progDetails);
            } else if (mode.equalsIgnoreCase("Swt")) {
                resultList1 = getSqlMapClientTemplate()
                                  .queryForList("studentRegistration.getVerificationComponentsSWT",
                        progDetails);
            }

            resultObj = resultList1.toArray(new StudentInfoGetter[resultList1.size()]);

            return resultObj;
        } catch (Exception e) {
            logObj.error(e);
        }

        return null;
    }

    /**
     * Method to get minimum and maximum required credits for specific program,semester basis
     * @param inputObj containing program, semester
     * @return list of courses and their respective minimum credits required
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<StudentInfoGetter> getCreditDetailsFromTermDetails(
        StudentInfoGetter inputObj) throws Exception {
        try {
            List<StudentInfoGetter> resultList1 = getSqlMapClientTemplate()
                                                      .queryForList("studentRegistration.getCreditDetailsFromTermDetails",
                    inputObj);

            return resultList1;
        } catch (Exception e) {
            logObj.error("Exception in getCreditDetailsFromTermDetails" +
                " : " + e);
            throw e;
        }
    }
    
    
    
 /**
  * Method to get core program group list
  * @param inputObj
  * @return List
  */
	@SuppressWarnings({ "unchecked" })
	public List<CourseInfoGetter> getProgramGroup(StudentInfoGetter inputObj) throws Exception{
    	 try {
             List<CourseInfoGetter> resultList = getSqlMapClientTemplate()
                                                       .queryForList("studentRegistration.getCompulsaryProgramGroup",
                     inputObj);

             return resultList;
         } catch (Exception e) {
             logObj.error("Exception in getProgramGroup" +
                 " : " + e);
             throw e;
         }
		    	
    }
    
	 /**
	  * Method to get core program group rules
	  * @param inputObj
	  * @return List
	  */
	@SuppressWarnings({ "unchecked" })
	public List<CourseInfoGetter> getProgramGroupRule(StudentInfoGetter inputObj) throws Exception{
    	 try {
             List<CourseInfoGetter> resultList = getSqlMapClientTemplate()
                                                       .queryForList("studentRegistration.getProgramRule",
                     inputObj);

             return resultList;
         } catch (Exception e) {
             logObj.error("Exception in getProgramGroupRule" +
                 " : " + e);
             throw e;
         }
		    	
    }
    
    
    
	 /**
	  * Method to get student's previously opted course groups
	  * @param inputObj
	  * @return List
	  */
		@SuppressWarnings({ "unchecked" })
		public List<CourseInfoGetter> getOldCourseGroup(StudentInfoGetter inputObj) throws Exception{
	    	 try {
	             List<CourseInfoGetter> resultList = getSqlMapClientTemplate()
	                                                       .queryForList("studentRegistration.getStudentOptedGroup",
	                     inputObj);

	             return resultList;
	         } catch (Exception e) {
	             logObj.error("Exception in getOldCourseGroup" +
	                 " : " + e);
	             throw e;
	         }
			    	
	    }
    
    
    
    
    
    
    
    
    
    
}
