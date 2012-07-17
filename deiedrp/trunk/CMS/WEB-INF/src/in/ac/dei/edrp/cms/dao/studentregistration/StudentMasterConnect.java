/*
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
package in.ac.dei.edrp.cms.dao.studentregistration;

import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ActivityMasterBean;

import java.util.List;


/**
 * This interface consist the list of methods used
 * in StudentMasterImpl file.
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public interface StudentMasterConnect {

	CountProcessRecorList generateEnrollmentNumbers(
			ActivityMasterBean activityMaster);
	
	CountProcessRecorList generateRollNumbers(
			ActivityMasterBean activityMaster);
    /**
    * Method generates enrollment numbers for the students of a programme-branch
    * @param userId user id of the user who executed the process
    * @param entityId entity offering the programme
    * @param programId program_id of the selected programme
    * @param branchId branch_id of the selected branch
    * @param specialization_id specialization_id of the selected Specialization
    * @param semesterEndDate end date of the semester
    * @param semesterStartDate start date of the semester
    * @param semesterCode semester in which the student is about to pursue
    * @return String
    */
//    List<String> generateEnrollmentNumbers(String userId, String entityId,
//        String programId, String branchCode, String specializationId,
//        String semesterCode, String semesterStartDate, String semesterEndDate);

    /**
     * the commented code can be used in future
     */

    //    /**
    //     * Method generates roll numbers for the students of a programme-branch
    //     * @param userid creator id
    //     * @param entity_id entity offering the programme
    //     * @param program_id program_id of the selected programme
    //     * @param branch_id branch_id of the selected branch
    //     * @param specialization_id specialization_id of the selected Specialization
    //     * @return String on failure/successful generation of numbers
    //     * @throws SQLException
    //     */
    //    List<String> generateRollNumbers(String userid, String value,
    //        String value2, String value3, String string) throws Exception;

    /**
     * Method update the batch process tables on successful data transfer into the master tables
     * @param userId user id of the user who executed the process
     * @param entityId entity offering the programme
     * @param programId program_id of the selected programme
     * @param branchId branch_id of the selected branch
     * @param specialization_id specialization_id of the selected Specialization
     * @param semesterCode semester in which the student is about to pursue
     * @return String
     */
//    String setBatchUpdateLog(String userId, String entityId, String programId,
//        String branchCode, String specializationId, String semesterCode);

    /**
     * Method inserts the details of the student whose data was not transferred
     * to the master tables due to some fault in the data
     * @param userId user id of the user who executed the process
     * @param entityId entity id of the entity
     * @param programId programId of the concerned program
     * @param branchId branchId of the concerned branch
     * @param studentDetail error code,enrollment number and process counter value where error occurred
     * @param specializationId specializationId for the concerned program-branch
     * @param semester semester of the concerned program
     * @param reason exception & the details of the student who encountered the error
     * @return String
     */
//    String setStudentErrorLog(String userId, String entityId, String programId,
//        String branchId, String specializationId, String semester,
//        String studentDetail, String reason);

    /**
     * Method to transfer the student's data to the master tables
     * @param userId user id of the user who executed the process
     * @param entityId entity offering the programme
     * @param programId program_id of the selected programme
     * @param branchId branch_id of the selected branch
     * @param specializationId specialization_id of the selected Specialization
     * @param semesterEndDate end date of the semester
     * @param semesterStartDate start date of the semester
     * @param semester semester in which the student is about to pursue
     * @return student details
     */
//    List<String> setStudentMasterTransfer(String userId, String entityId,
//        String programId, String branchId, String specializationId,
//        String semester, String semesterStartDate, String semesterEndDate)
//        throws Exception;
	
	
	
	
	
}
