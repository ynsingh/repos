/*
 * @(#) ProgramCourseTypeSummaryConnect.java
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
package in.ac.dei.edrp.cms.dao.programcoursetypesummary;

import in.ac.dei.edrp.cms.domain.programcoursetypesummary.ProgramCourseTypeSummaryInfoGetter;

import java.util.List;
import java.util.StringTokenizer;


/**
 * This interface consist the list of methods used
 * in ProgramCourseTypeSummaryImpl file.
 * @author Ashish
 * @date 12 Feb 2011
 * @version 1.0
 */
public interface ProgramCourseTypeSummaryConnect {


    /**
     * method gets the list of program for the entity which the user belongs
     * @param userId user id of the user who is accessing the application
     * @return list
     */
    List<ProgramCourseTypeSummaryInfoGetter> getProgramCourseTypeDetails(
        String userId);

    /**
     * method gets the list of branches for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @return list
     */
    List<ProgramCourseTypeSummaryInfoGetter> getProgramBranchDetails(ProgramCourseTypeSummaryInfoGetter input);

    /**
     * method gets the list of specializations for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @param branchId branch id of the concerned program
     * @return list
     */
    List<ProgramCourseTypeSummaryInfoGetter> getProgramSpecializationDetails(ProgramCourseTypeSummaryInfoGetter input);

    /**
     * method gets the list of semesters for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @param branchId branch id of the concerned program
     * @param specializationId specialization id of the concerned program
     * @return list
     */
    List<ProgramCourseTypeSummaryInfoGetter> getProgramSemesterDetails(ProgramCourseTypeSummaryInfoGetter input);

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
    List<ProgramCourseTypeSummaryInfoGetter> getProgramTypeDetails(ProgramCourseTypeSummaryInfoGetter input);

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
    String setProgramCoursetypeSummary(ProgramCourseTypeSummaryInfoGetter input);

    /**
     * method retrieves the list of records already set for the concerned program combination
     * @param userId user id of the user who is accessing the application
     * @param programId program id of the concerned program
     * @param branchId branch id of the concerned program
     * @param specializationId specialization id of the concerned program
     * @param semesterCode semester code of the concerned program
     * @return list
     */
    List<ProgramCourseTypeSummaryInfoGetter> getProgramCoursetypeSummary(ProgramCourseTypeSummaryInfoGetter input);

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
    String deleteProgramCoursetypeSummaryRecords(ProgramCourseTypeSummaryInfoGetter input,StringTokenizer items);
}
