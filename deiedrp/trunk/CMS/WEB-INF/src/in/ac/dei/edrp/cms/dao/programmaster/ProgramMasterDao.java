/*
 * @(#) ProgramMasterDao.java
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
package in.ac.dei.edrp.cms.dao.programmaster;

import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;

import java.util.List;


/**
 * Interface for program master functionality
 * @author Manpreet Kaur
 * @date 24-02-2011
 * @version 1.0
 */
public interface ProgramMasterDao {
    /**
    * Method to get list of program modes available in university
    * @param userId user id of the user logged in
    * @return List<ProgramInfoGetter> list of program modes in a university
    */
    List<ProgramMasterInfoGetter> methodUniversityProgramMode(String userId)
        throws Exception;

    /**
     * Method to get list of program types available in university
     * @param userId user id of the user logged in
     * @return List<ProgramInfoGetter> list of program types in a university
     */
    List<ProgramMasterInfoGetter> methodUniversityProgramType(String userId)
        throws Exception;

    /**
     * Method for getting list of programs added under university
     * @param userId user id of the user logged in
     * @return List<ProgramInfoGetter> list of programs in a university
     */
    List<ProgramMasterInfoGetter> methodprogList(String userId);

    /**
     * Method for getting list of branches that can be associated for programs under university
     * @param userId user id of the user logged in
     * @return List<ProgramMasterInfoGetter> list of branches
     */
    List<ProgramMasterInfoGetter> methodBranchList(String userId)
        throws Exception;

    /**
     * Method for getting list of specializations that can be associated for programs under university
     * @param userId user id of the user logged in
     * @return List<ProgramMasterInfoGetter> list of specializations
     */
    List<ProgramMasterInfoGetter> methodSpecList(String userId)
        throws Exception;

    /**
     * Method for getting list of semesters
     * @param userId user id of the user logged in
     * @return List<ProgramMasterInfoGetter> list of specializations
     */
    List<ProgramMasterInfoGetter> methodSemesterList(String userId)
        throws Exception;

    /**
     * Method for adding new program under university
     * @param userId
     * @param progInfo
     * @return String
     */
    String methodAddProgDetails(String userId, ProgramMasterInfoGetter progInfo)
        throws Exception;

    /**
     * Method for getting program details for manage screen
     * @param userId
     * @param object
     * @param criteria
     * @return List of ProgramMasterInfoGetter type
     */
    List<ProgramMasterInfoGetter> methodProgMasterDetailsForManage(
        String userId, ProgramMasterInfoGetter object, String criteria)
        throws Exception;

    /**
     * Method for getting duration details for given program
     * @param progInfo
     * @return List of ProgramMasterInfoGetter type
     */
    List<ProgramMasterInfoGetter> methodProgDurationDetailsForManage(
        ProgramMasterInfoGetter object) throws Exception;

    /**
     * Method for getting program's branch and specialization details for editing purpose
     * @param object
     * @param criteria
     * @return List of ProgramMasterInfoGetter type
     */
    List<ProgramMasterInfoGetter> methodProgBranchSpecDetailsForManage(
        ProgramMasterInfoGetter object, String criteria)
        throws Exception;

    /**
     * Method for updating program details
     * @param userId
     * @param object
     */
    void methodUpdateProgBasicDetails(String userId,
        ProgramMasterInfoGetter object) throws Exception;

    /**
     * Method for deleting program details
     * @param programId
     * @return String error string
     */
    String methodDeleteProg(String programId) throws Exception;

    /**
     * Method for deleting program duration detail
     * @param object
     */
    void methodProgDurationDelete(ProgramMasterInfoGetter object)
        throws Exception;

    /**
     * Method for deleting a branch related to given program
     * @param object
     * @return String
     */
    String methodBranchDelete(ProgramMasterInfoGetter object)
        throws Exception;

    /**
     * Method to update program duration details
     * @param object
     */
    void methodUpdateProgDurationDetails(ProgramMasterInfoGetter object)
        throws Exception;

    /**
     * Method for add new start date for program
     *  @param object
     */
    void methodAddStartDate(ProgramMasterInfoGetter object)
        throws Exception;

    /**
     * Method for adding new branch and specialization under program
     *  @param object
     *  @return String
     */
    String methodAddAnotherBranch(ProgramMasterInfoGetter progInfo)
        throws Exception;

	List<ProgramMasterInfoGetter> getPrograDomainList(
			ProgramMasterInfoGetter input);
}
