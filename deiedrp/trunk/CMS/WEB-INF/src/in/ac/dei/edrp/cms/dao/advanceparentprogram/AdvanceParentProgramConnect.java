/*
 * @(#) AdvanceParentProgramConnect.java
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
package in.ac.dei.edrp.cms.dao.advanceparentprogram;

import in.ac.dei.edrp.cms.domain.advanceparentprogram.AdvanceParentProgramInfoGetter;

import java.util.List;
import java.util.StringTokenizer;


/**
 * This interface consist the list of methods used
 * in AdvanceParentProgramImpl file.
 * @author Ashish
 * @date 17 Jan 2011
 * @version 1.0
 */
public interface AdvanceParentProgramConnect {
    /**
     * This method retrieves the list of programs
     * associated with the university
     * @param userId user id of the user accessing the application
     * @return List
     */
    List<AdvanceParentProgramInfoGetter> getProgramList(String userId);

    /**
     * This method retrieves the list of courses
     * associated with the program
     * @param input object of AdvanceParentProgramInfoGetter bean class
     * @return List
     */
    List<AdvanceParentProgramInfoGetter> getCoursesList(
        AdvanceParentProgramInfoGetter input);

    /**
     * This method inserts the selected combination for the program
     * @param input object of AdvanceParentProgramInfoGetter bean class
     * @return String
     */
    String insertAdvanceProgramRecord(AdvanceParentProgramInfoGetter input);

    /**
     * This method retrieves the list of records already set
     * for the program
     * @param input object of AdvanceParentProgramInfoGetter bean class
     * @return List
     */
    List<AdvanceParentProgramInfoGetter> getAdvanceProgramRecords(
        AdvanceParentProgramInfoGetter input);

    /**
     * This method deletes the selected records for the program
     * @param userId user id of the user accessing the application
     * @param programId program id of the advance program
     * @param advanceProgramId program id of the advance program
     * @param items list of items selected for deletion
     * @return String
     */
    String deleteAdvanceProgramRecord(String userId, String programId,
        String advanceProgramId, StringTokenizer items);
}
