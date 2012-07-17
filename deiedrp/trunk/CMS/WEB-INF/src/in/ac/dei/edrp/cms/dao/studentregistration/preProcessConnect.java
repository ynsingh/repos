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
 * in preProcessImpl file.
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public interface preProcessConnect {
    /**
    * Method is for marking the students in all respect
    * before generating the enrollment & roll numbers.
    * @param userId modifier id
    * @param entityId entity offering the programme
    * @param programId program_id of the selected programme
    * @param branchId branch_id of the selected branch
    * @param specialization_id specialization_id of the selected Specialization
    * @param semesterEndDate end date of the semester
    * @param semesterStartDate start date of the semester
    * @param semesterCode semester in which the student is about to pursue
    * @return String
    */
    CountProcessRecorList preProcessCheck(
			ActivityMasterBean activityMaster);

    /**
     * Method is for marking the students in all respect
     * before transferring their data into the master tables.
     * @param userId modifier id
     * @param entityId entity offering the programme
     * @param programId program_id of the selected programme
     * @param branchId branch_id of the selected branch
     * @param specialization_id specialization_id of the selected Specialization
     * @param semesterEndDate end date of the semester
     * @param semesterStartDate start date of the semester
     * @param semesterCode semester in which the student is about to pursue
     * @return String
     */
    CountProcessRecorList preCheckforMaster(
			ActivityMasterBean activityMaster);
}
