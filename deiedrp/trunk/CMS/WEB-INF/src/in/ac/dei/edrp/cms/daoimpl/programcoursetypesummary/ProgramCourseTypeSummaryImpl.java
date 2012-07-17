/*
 * @(#) ProgramCourseTypeSummaryImpl.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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
package in.ac.dei.edrp.cms.daoimpl.programcoursetypesummary;

import in.ac.dei.edrp.cms.dao.programcoursetypesummary.ProgramCourseTypeSummaryConnect;
import in.ac.dei.edrp.cms.domain.programcoursetypesummary.ProgramCourseTypeSummaryInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentNumbersInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;
import java.util.StringTokenizer;

/**
 * This file consist of the methods used for setting up
 * the program course type summary.
 * @author Ashish
 * @date 18 Feb 2011
 * @version 1.0
 */
public class ProgramCourseTypeSummaryImpl extends SqlMapClientDaoSupport
    implements ProgramCourseTypeSummaryConnect {
    private Logger loggerObject = Logger.getLogger(ProgramCourseTypeSummaryImpl.class);

    /**
    * method gets the list of program for the entity which the user belongs
    * @param userId user id of the user who is accessing the application
    * @return list
    */
    @SuppressWarnings("unchecked")
    public List<ProgramCourseTypeSummaryInfoGetter> getProgramCourseTypeDetails(
        String userId) {
        try {
            List programCourseList;
            String universityId = userId.substring(1,5)+"%";
            
            
            programCourseList = getSqlMapClientTemplate()
                                    .queryForList("programcoursetypesummary.getprogramcoursetypedetails",
                    universityId);

            return programCourseList;
        } catch (Exception e) {
            loggerObject.error("exception" + e);
        }

        return null;
    }

    /**
     * method gets the list of branches for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List<ProgramCourseTypeSummaryInfoGetter> getProgramBranchDetails(
        ProgramCourseTypeSummaryInfoGetter input) {
        try {
            List programBranchList;

            programBranchList = getSqlMapClientTemplate()
                                    .queryForList("programcoursetypesummary.getprogrambranchdetails",
                    input.getProgramId());

            return programBranchList;
        } catch (Exception e) {
            loggerObject.error("exception in getProgramBranchDetails" + e);
        }

        return null;
    }

    /**
     * method gets the list of specializations for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @param branchId branch id of the concerned program
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List<ProgramCourseTypeSummaryInfoGetter> getProgramSpecializationDetails(
        ProgramCourseTypeSummaryInfoGetter input) {
        List programSpecializationList;

        try {
            programSpecializationList = getSqlMapClientTemplate()
                                            .queryForList("programcoursetypesummary.getprogramspecializationdetails",
                    input);

            return programSpecializationList;
        } catch (Exception e) {
            loggerObject.error("exception in getProgramSpecializationDetails" +
                e);
        }

        return null;
    }

    /**
     * method gets the list of semesters for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @param branchId branch id of the concerned program
     * @param specializationId specialization id of the concerned program
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List<ProgramCourseTypeSummaryInfoGetter> getProgramSemesterDetails(
        ProgramCourseTypeSummaryInfoGetter input) {
        List programSemesteList;

        try {
            programSemesteList = getSqlMapClientTemplate()
                                     .queryForList("programcoursetypesummary.getprogramsemesterdetails",
                    input);

            return programSemesteList;
        } catch (Exception e) {
            loggerObject.error("exception in getProgramSemesterDetails" + e);
        }

        return null;
    }

    /**
     * method to get the type of courses associated with the program combination
     * which is not set for the combination yet.
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @param branchId branch id of the concerned program
     * @param specializationId specialization id of the concerned program
     * @param semesterCode semester code of the concerned program
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List<ProgramCourseTypeSummaryInfoGetter> getProgramTypeDetails(
        ProgramCourseTypeSummaryInfoGetter input) {
        List programDetailsList;

        try {
            programDetailsList = getSqlMapClientTemplate()
                                     .queryForList("programcoursetypesummary.getprogramcoursetypesdetails",
                    input);

            return programDetailsList;
        } catch (Exception e) {
            loggerObject.error("exception in getProgramTypeDetails" + e);
        }

        return null;
    }

    /**
     * method to insert/update course types for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @param branchId branch id of the concerned program
     * @param specializationId specialization id of the concerned program
     * @param semesterCode semester code of the concerned program
     * @param courseTypeCode course type code of the concerned courses for the program
     * @param minCredits minimum credits for the concerned courses for the program
     * @param maxCredits maximum credits for the concerned courses for the program
     * @param activityFlag activity of the process(eg. insert,update)
     * @return String
     */
    public String setProgramCoursetypeSummary(
        ProgramCourseTypeSummaryInfoGetter input) {
        StudentNumbersInfoGetter programCourseKeyObject;

        try {
            programCourseKeyObject = (StudentNumbersInfoGetter) getSqlMapClientTemplate()
                                                                    .queryForObject("studentenrollment.getprogramcoursekey",
                    input);

            input.setProgramCourseKey(programCourseKeyObject.getProgramCourseKey());

            // condition for inserting record 
            if (input.getActivityFlag().equalsIgnoreCase("insert")) {
                getSqlMapClientTemplate()
                    .insert("programcoursetypesummary.setprogramcoursetypesummary",
                    input);
            }
            // condition for updating record
            else if (input.getActivityFlag().equalsIgnoreCase("update")) {
                getSqlMapClientTemplate()
                    .update("programcoursetypesummary.updateprogramcoursetypesummary",
                    input);
            }

            return "success";
        } catch (Exception e) {
            loggerObject.error("exception in setProgramCoursetypeSummary" + e);
        }

        return null;
    }

    /**
     * method retrieves the list of records already set for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @param branchId branch id of the concerned program
     * @param specializationId specialization id of the concerned program
     * @param semesterCode semester code of the concerned program
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List<ProgramCourseTypeSummaryInfoGetter> getProgramCoursetypeSummary(
        ProgramCourseTypeSummaryInfoGetter input) {
        StudentNumbersInfoGetter programCourseKeyObject;

        List getRecordsList;

        try {
            programCourseKeyObject = (StudentNumbersInfoGetter) getSqlMapClientTemplate()
                                                                    .queryForObject("studentenrollment.getprogramcoursekey",
                    input);

            input.setProgramCourseKey(programCourseKeyObject.getProgramCourseKey());

            getRecordsList = getSqlMapClientTemplate()
                                 .queryForList("programcoursetypesummary.getrecordslist",
                    input);

            return getRecordsList;
        } catch (Exception e) {
            loggerObject.error("exception in getProgramCoursetypeSummary" + e);
        }

        return null;
    }

    /**
     * method to delete the selected course types for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @param branchId branch id of the concerned program
     * @param specializationId specialization id of the concerned program
     * @param semesterCode semester code of the concerned program
     * @param items
     * @return String
     */
    public String deleteProgramCoursetypeSummaryRecords(
        ProgramCourseTypeSummaryInfoGetter input, StringTokenizer courseTypeCode) {
        try {
            while (courseTypeCode.hasMoreTokens()) {
                input.setCourseTypeCode(courseTypeCode.nextToken());

                getSqlMapClientTemplate()
                    .delete("programcoursetypesummary.deleterecordslist", input);
            }

            return "success";
        } catch (Exception e) {
            loggerObject.error(
                "setroleauthorities in deleteProgramCoursetypeSummaryRecords" +
                e);
        }

        return null;
    }
}
