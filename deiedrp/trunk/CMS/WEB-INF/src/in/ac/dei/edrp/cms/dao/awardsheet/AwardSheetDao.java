/*
 * @(#) AwardSheetDao.java
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
package in.ac.dei.edrp.cms.dao.awardsheet;

import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.entity.EntityInfoGetter;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramTermDetailsInfoGetter;

import java.util.List;
import java.util.StringTokenizer;


/**
 * This interface contains method declarations for Award blank sheet functionality
 * @author Manpreet Kaur
 * @date 20-03-2011
 * @version 1.0
 */
public interface AwardSheetDao {
	
    /**
     * Method for getting course list of given program, branch, specialization, semester
     * @param inputObj
     * @return List of courses
     */
    public List<AwardSheetInfoGetter> getCourseList(AwardSheetInfoGetter inputObj);
    /**
     * Method for getting evaluation components
     * @param inputObj
     * @return List
     */
    public List<AwardSheetInfoGetter> getEvaluationComponents(
        AwardSheetInfoGetter inputObj);
    /**
     * Method for getting list of students whose marks are to be entered for given course
     * @param inputObj
     * @return List
     */
    public List<AwardSheetInfoGetter> getStudentList(
        ProgramMasterInfoGetter inputObj);
    /**
     * Method for getting marks of students
     * @param inputObj
     * @return List
     */
    public List<AwardSheetInfoGetter> getStudentMarks(
        ProgramMasterInfoGetter inputObj);
    /**
     * Method for getting rules for groups
     * @param inputObj
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getRule(
        ProgramMasterInfoGetter inputObj);
    /**
     * Method for checking status of existing entry regarded given course
     * @param inputObj
     * @return String
     */
    public String checkStatus(ProgramMasterInfoGetter inputObj);
    /**
     * Method for getting employee code
     * @param inputObj
     * @return List
     */
    public List<AwardSheetInfoGetter> getEmployeeCode(String userId);
    /**
     * Method for getting Program Course Key
     * @param inputObj
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getProgramCourseKey(
        ProgramMasterInfoGetter inputObj);
    /**
     * Method for getting List of pending request for given employee
     * @param inputObj
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getPendingList(
        ProgramMasterInfoGetter inputObj);
    /**
     * Method for getting List of approved request for given employee
     * @param inputObj
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getApprovedList(
        ProgramMasterInfoGetter inputObj);
    /**
     * Method for insert/update student marks
     * @param input
     * @return String
     */
    public String saveStudentMarks(AwardSheetInfoGetter input,
        StringTokenizer data);
    /**
     * Method for submitting award sheet for approval
     * @param inputObj
     * @return String
     */
    public String submitForApproval(AwardSheetInfoGetter inputObj,
        String function);
    /**
     * Method for withdrawing award blank sheet previously submitted
     * @param inputObj
     * @return
     */
    public String withdrawRequest(AwardSheetInfoGetter inputObj);
    /**
     * Method for approving award blank sheet
     * @param inputObj
     * @return
     */
    public String approveRequest(AwardSheetInfoGetter inputObj);
    /**
     * Method for rejecting award blank sheet
     * @param inputObj
     * @return
     */
    public String rejectRequest(AwardSheetInfoGetter inputObj);
    
    /**
     * Method for getting grades
     * @return String
     */
    public List<AwardSheetInfoGetter>  getGrade(AwardSheetInfoGetter awardSheetInfoGetter);
    
    /**
     * Method for getting List of rejected request for given employee
     * @param inputObj
     * @return List
     */
    public List<AwardSheetInfoGetter> getRejectedList(
    		AwardSheetInfoGetter inputObj);
    
    /**
     * Method for getting List of rejected request for given employee
     * @param inputObj
     * @return List
     */
    public AwardSheetInfoGetter getStarterEmployee(AwardSheetInfoGetter inputObj);
    
    /**
     * Method to check next approval exist.
     * @param inputObj
     * @return List
     */
    public String isNextApprovalExist(AwardSheetInfoGetter inputObj);
    
    /**
     * Method to check External AwardBlank Allowed.
     * @param inputObj
     * @return List
     */
    public String IsExternalAwardAllowed(AwardSheetInfoGetter inputObj);
    
    public List<AwardSheetInfoGetter> getApprovalOrder(AwardSheetInfoGetter inputObj);
	
	 /** Method to Upload Excel Template
     * @param String filePath of excel file
     * @param String data to be set into student_marks table
     * @param inputObj object of AwardSheetInfoGetter
	 * @param oldmarks 
     * @param String Column
     * @param String display ie.(Internal/External/Remadial)
     * @param String Path1
     * @param String fileName
     * @return String message
    */
   public String uploadTemplate(String filePath,String data,AwardSheetInfoGetter inputObj,String Clist,String display,String path1,String fileName, List<AwardSheetInfoGetter> oldmarks);
   
   /**
    * Method to get Approval Status
    * @param inputObj
    * @return List<AwardSheetInfoGetter>
    */
   public List<AwardSheetInfoGetter> getAprStatus(AwardSheetInfoGetter inputObj);
public List<AwardSheetInfoGetter> getgradelimit(AwardSheetInfoGetter inputObj);
public List<AwardSheetInfoGetter> getcourseAprStatus(
		AwardSheetInfoGetter inputObj);
}
