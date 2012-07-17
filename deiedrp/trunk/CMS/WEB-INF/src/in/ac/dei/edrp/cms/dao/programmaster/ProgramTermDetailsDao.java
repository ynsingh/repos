/*
 * @(#) ProgramTermDetailsDao.java
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

import in.ac.dei.edrp.cms.domain.programmaster.ProgramTermDetailsInfoGetter;

import java.util.List;


public interface ProgramTermDetailsDao {
    /**
     * Method for inserting details of a term into database
     * @param inputObj
     * @return String
     */
    public String insertTermDetails(ProgramTermDetailsInfoGetter inputObj);

    /**
     * Method for getting list of all possible semesters
     * @param universityId
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getSemesterList(
        String universityId);

    /**
     * Method for getting list of all possible semester groups
     * @param universityId
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getSemesterGroupList(
        String universityId);

    /**
     * Method for getting list of sequences for a program
     * @param universityId
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getProgSequenceList(
        String programId);

    /**
     * Method for getting list of programs for manage
     * @param programId
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getProgListForManage(
        String programId);

    /**
     * Method for getting list of semesters existing in a program
     * @param programId
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getProgramSemList(
        String programId);

    /**
     * Method for getting program term details for managing purpose
     * @param object
     * @return List
     */
    public List<ProgramTermDetailsInfoGetter> getTermDetailForManage(
        ProgramTermDetailsInfoGetter object);

    /**
     * Method for update details of a term into database
     * @param inputObj
     * @return String
     */
    public String updateTermDetails(ProgramTermDetailsInfoGetter inputObj);

    /**
     * Method for delete details of a term into database
     * @param inputObj
     * @return String
     * @throws Exception 
     */
    public String deleteTermDetails(ProgramTermDetailsInfoGetter inputObj) throws Exception;
}
