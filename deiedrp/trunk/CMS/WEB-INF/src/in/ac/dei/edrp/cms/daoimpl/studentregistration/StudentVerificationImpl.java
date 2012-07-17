/*
 * @(#) StudentVerificationImpl.java
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

import in.ac.dei.edrp.cms.dao.studentregistration.StudentVerificationDao;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.studentregistration.CourseInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;


import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is implementation of StudentVerificationRPC DAO interface
 * This class is being used for Student verification checklist
 * @author Manpreet Kaur
 * @version 1.0
 * @date 20-01-2011
 */
public class StudentVerificationImpl extends SqlMapClientDaoSupport
    implements StudentVerificationDao {
    private static Logger logObj = Logger.getLogger(StudentVerificationImpl.class);

    /**
     * Method for fetching list of students with their corresponding details based on
     * entity, program, branch, specialization,semester, semester start date, semester end date basis
     * @param programObject
     * @return object containing students detail list
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<StudentInfoGetter> getStudentBasicDetails(
        StudentInfoGetter programObject) throws Exception {
    
        try {
            List<?> resultList = getSqlMapClientTemplate()
                                     .queryForList("studentVerification.getStudentVerificationBasicDetails",
                    programObject);
           
            
            System.out.println("size if  result list "+resultList.size());
//            StudentInfoGetter[] resultObj = resultList.toArray(new StudentInfoGetter[resultList.size()]);

//            if (resultObj.length == 0)
 //         if(resultList.size()==0) {
  //              throw new Exception("No student found");
   //         } else {
                return (List<StudentInfoGetter>) resultList;
     //       }
        } catch (Exception e) {
            logObj.error("Exception in getStudentBasicDetails : " + e);

            throw e;
        }

        //        return null;
    }

    /**
     * Method for fetching component list with their verification status for a particular student
     * and for inserting verification components that were laterly added to system.
     * @param studentObject
     * @return object containing component list
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<StudentInfoGetter> getComponentVerificationDetails(
        StudentInfoGetter studentObject) throws Exception {
        try {
            List<StudentInfoGetter> resultList = null;
            List<?> intersectedList = null;
          
            if (studentObject.getAdmissionMode().equalsIgnoreCase("NEW")) {
                intersectedList = getSqlMapClientTemplate()
                                      .queryForList("studentVerification.getIntersectedComponentListForNew",
                        studentObject);
            } else if (studentObject.getAdmissionMode().equalsIgnoreCase("SWT")) {
                intersectedList = getSqlMapClientTemplate()
                                      .queryForList("studentVerification.getIntersectedComponentListForSwt",
                        studentObject);
            } else { }       

            StudentInfoGetter[] componentList = intersectedList.toArray(new StudentInfoGetter[intersectedList.size()]);

            /*
             * inserting these components into student_verification_status
             */
            
            
            
            if(studentObject.getAdmissionMode().equalsIgnoreCase("SWT"))
            {
            	studentObject.setInsertNumber(studentObject.getRollNumber());
            }
            else if(studentObject.getAdmissionMode().equalsIgnoreCase("NEW"))
            {
            	studentObject.setInsertNumber(studentObject.getRegistrationNumber());
            }
            
            for (int i = 0; i < componentList.length; i++) {
                studentObject.setComponentCode(componentList[i].getComponentCode());
                getSqlMapClientTemplate()
                    .insert("studentVerification.insertIntoVerificationTable",studentObject);
            }
            
            /*
             * populating all components from student_verification_status
             */
            resultList = getSqlMapClientTemplate()
                             .queryForList("studentVerification.getCompleteComponentList",
                    studentObject);

            //            componentList1 = resultList.toArray(new StudentInfoGetter[resultList.size()]);
            return resultList;
        } catch (Exception e) {
            logObj.error("Exception in getComponentVerificationDetails " +
                " : " + e);

            throw e;
        }
    }

    /**
     * Method for updating component verification status for student and
     * for setting verified status as "verified" if all components for student
     * are verified else "unverified".
     * @param studentObject
     * @throws Exception
     */
    public void saveComponentStatus(StudentInfoGetter[] studentObject)
        throws Exception {
    	System.out.println("in status updation "+studentObject.length);
        try {
            for (int loop = 0; loop < studentObject.length; loop++) {
                getSqlMapClientTemplate()
                    .update("studentVerification.updateStudentVerificationStatus",
                    studentObject[loop]);
                System.out.println(studentObject[loop].getVerified()+studentObject[loop].getNotes()+studentObject[loop].getRegistrationNumber()+" "+studentObject[loop].getComponentCode());
            }

            if (studentObject.length > 0) {
                List<?> resultList = getSqlMapClientTemplate()
                                         .queryForList("studentVerification.check verification",
                        studentObject[0]);

                if (resultList.size() == 0) {
                    if (studentObject[0].getAdmissionMode()
                                            .equalsIgnoreCase("NEW")) {
                        getSqlMapClientTemplate()
                            .update("studentVerification.setVerifiedNew", studentObject[0]);
                    } else if (studentObject[0].getAdmissionMode()
                                                   .equalsIgnoreCase("SWT")) {
                        getSqlMapClientTemplate()
                            .update("studentVerification.setVerifiedSwt", studentObject[0]);
                    }
                } else {
                    if (studentObject[0].getAdmissionMode()
                                            .equalsIgnoreCase("NEW")) {
                        getSqlMapClientTemplate()
                            .update("studentVerification.setUnVerifiedNew", studentObject[0]);
                    } else if (studentObject[0].getAdmissionMode()
                                                   .equalsIgnoreCase("SWT")) {
                        getSqlMapClientTemplate()
                            .update("studentVerification.setUnVerifiedSwt", studentObject[0]);
                    }
                }
            }
        } catch (Exception e) {
            logObj.error("Exception in saveComponentStatus " + " : " + e);

            throw e;
        }
    }

    /**
     * Method for fetching full details of student
     * @param inputObj containing student's key information
     * @return Object containing student's full details
     * @throws Exception
     */
    public StudentInfoGetter getStudentFullDetails(StudentInfoGetter inputObj)
        throws Exception {
        List<?> resultList1 = null;

        try {
        	System.out.println("Dmission mode "+inputObj.getAdmissionMode());
            if (inputObj.getAdmissionMode().equalsIgnoreCase("NEW")) {
                resultList1 = getSqlMapClientTemplate()
                                  .queryForList("studentVerification.getFullDetailsForNew",
                        inputObj);
            } else if (inputObj.getAdmissionMode().equalsIgnoreCase("SWT")) {
                resultList1 = getSqlMapClientTemplate()
                                  .queryForList("studentVerification.getFullDetailsForSwt",
                        inputObj);
            }

            StudentInfoGetter[] componentList = resultList1.toArray(new StudentInfoGetter[resultList1.size()]);

            return componentList[0];
        } catch (Exception e) {
            logObj.error("Exception in getStudentFullDetails " + " : " + e);
            throw e;
        }
    }

    /**
     * Method for getting list of courses opted by student
     * @param inputObj
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CourseInfoGetter> getCourseList(StudentInfoGetter inputObj)
        throws Exception {
        List<CourseInfoGetter> resultList = null;

        try {
            if (inputObj.getAdmissionMode().equalsIgnoreCase("NEW")) {
                resultList = getSqlMapClientTemplate()
                                 .queryForList("studentVerification.getCourseListForNew",
                        inputObj);
            } else if (inputObj.getAdmissionMode().equalsIgnoreCase("SWT")) {
                resultList = getSqlMapClientTemplate()
                                 .queryForList("studentVerification.getCourseListForSwt",
                        inputObj);
            }

            //            CourseInfoGetter[] componentList = resultList.toArray(new CourseInfoGetter[resultList.size()]);
            System.out.println("Before returning the list value"+ resultList.size());
            return resultList;
        } catch (Exception e) {
            logObj.error("Exception in getStudentFullDetails " + " : " + e);
            throw e;
        }
    }

   
    /**
     * Method of getting the personal details of enrolled students
     * @param enrollmentInfo containing the registration number
     * @return Object containing enrollment information of the student
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getEnrollmentPersonalDetails(
			EnrollmentInfo enrollmentInfo) {
		List<EnrollmentInfo> personalDetails = getSqlMapClientTemplate().queryForList("studentVerification.getEnrollmetPersonalDetails",enrollmentInfo);		
		return personalDetails;
	}
	/**
	 * Method of getting the academic details of enrolled student
	 * @param enrollmentInfo having the registration number
	 *  @return Object containing enrollment academic details of the student
	 */
	
			
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getEnrollmentAcademicDetails(
			EnrollmentInfo enrollmentInfo) {	
		List<EnrollmentInfo> academeicDetails = new ArrayList<EnrollmentInfo>();
		try{
			academeicDetails= getSqlMapClientTemplate().queryForList("studentVerification.getEnrollmetAcademicDetails",enrollmentInfo);
		}catch(Exception e)
		{
			logObj.error("Exception in getEnrollmentAcademicDetails "+e.getMessage());
		}
		return academeicDetails;
	}
	/**
	 * Method for rejecting the students
	 * @param enrollmentInfo having the registration number and enrollment number 
	 *  @return Object containing the updated row numbers.
	 */
	public int updateStudentStatus(EnrollmentInfo enrollmentInfo) throws SQLException {				
		int updateConfirm=0;
		if(enrollmentInfo.getAdmissionMode().equalsIgnoreCase("NEW")){
			updateConfirm = getSqlMapClient().update("studentVerification.updateStudentStatusForNew",enrollmentInfo);	
		}
		else if (enrollmentInfo.getAdmissionMode().equalsIgnoreCase("SWT")){
			updateConfirm = getSqlMapClient().update("studentVerification.updateStudentStatusForSwt",enrollmentInfo);
		}
			
		return updateConfirm;
	}

	public int updateRejectStatus(EnrollmentInfo enrollmentInfo) {
		
		int cancelRejectConfirm=0;
		
            	try{
            		if(enrollmentInfo.getAdmissionMode().equalsIgnoreCase("NEW")){
            			
            			cancelRejectConfirm = getSqlMapClient().update("studentVerification.updateStudentStatusForRejectedforNew",enrollmentInfo);	
            		}
            		else if (enrollmentInfo.getAdmissionMode().equalsIgnoreCase("SWT")){
            			cancelRejectConfirm = getSqlMapClient().update("studentVerification.updateStudentStatusForRejectedforSwt",enrollmentInfo);	
            		}
            		
            		return cancelRejectConfirm;
            	}catch (Exception e) {
            		logObj.error("Exception in Cancel Rejection "+e.getMessage());
            		return cancelRejectConfirm;
				}
			
	}
    
	/**
     * Method of getting the personal details of enrolled students for SWT case
     * @param enrollmentInfo containing the enrollment Number
     * @return Object containing enrollment information of the student
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getEnrollmentPersonalDetailsSWT(
			EnrollmentInfo enrollmentInfo) {
		
		List<EnrollmentInfo> personalDetailsSWT = getSqlMapClientTemplate().queryForList("studentVerification.getEnrollmetPersonalDetailsSWT",enrollmentInfo);
		System.out.println("details"+personalDetailsSWT);
		return personalDetailsSWT;
	}
    
	/**
	 * Method of getting the academic details of enrolled student for SWT case
	 * @param enrollmentInfo having the enrollment number
	 *  @return Object containing enrollment academic details of the student
	 */
	
			
	@SuppressWarnings("unchecked")
	public List<EnrollmentInfo> getEnrollmentAcademicDetailsSWT(
			EnrollmentInfo enrollmentInfo) {	
		List<EnrollmentInfo> academeicDetailsSWT = new ArrayList<EnrollmentInfo>();
		
		try{
			academeicDetailsSWT= getSqlMapClientTemplate().queryForList("studentVerification.getEnrollmetAcademicDetailsSWT",enrollmentInfo);
		}catch(Exception e)
		{
			logObj.error("Exception in getEnrollmentAcademicDetails "+e.getMessage());
		}
		return academeicDetailsSWT;
	}

    
}
