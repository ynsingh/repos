/*
 * @(#) AdvanceParentProgramImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.advanceparentprogram;

import in.ac.dei.edrp.cms.dao.advanceparentprogram.AdvanceParentProgramConnect;
import in.ac.dei.edrp.cms.domain.advanceparentprogram.AdvanceParentProgramInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;
import java.util.StringTokenizer;


/**
 *This file consist of the methods used for setting up
 * the advance parent program setup.
 * @author Ashish
 * @date 17 Feb 2011
 * @version 1.0
 */
public class AdvanceParentProgramImpl extends SqlMapClientDaoSupport
    implements AdvanceParentProgramConnect {
    private Logger loggerObject = Logger.getLogger(AdvanceParentProgramImpl.class);

    /**
     * This method retrieves the list of programs
     * associated with the university
     * @param userId user id of the user accessing the application
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<AdvanceParentProgramInfoGetter> getProgramList(String userId) {
        List programList;

        String universityCode = userId.substring(1, 5);

        try {
            programList = getSqlMapClientTemplate()
                              .queryForList("advanceparentprogram.getprogramlist",
                    universityCode);

            return programList;
        } catch (Exception e) {
            loggerObject.error(" in getProgramList" + e);
        }

        return null;
    }

    /**
     * This method retrieves the list of courses
     * associated with the program
     * @param input object of AdvanceParentProgramInfoGetter bean class
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<AdvanceParentProgramInfoGetter> getCoursesList(
        AdvanceParentProgramInfoGetter input) {
        List courseList;

        String universityCode = input.getUserId().substring(1, 5);

        try {
            input.setUniversityCode(universityCode);

            courseList = getSqlMapClientTemplate()
                             .queryForList("advanceparentprogram.getcourseslist",
                    input);

            return courseList;
        } catch (Exception e) {
            loggerObject.error(" in getCoursesList" + e);
        }

        return null;
    }

    /**
     * This method inserts the selected combination for the program
     * @param input object of AdvanceParentProgramInfoGetter bean class
     * @return String
     */
    public String insertAdvanceProgramRecord(
        AdvanceParentProgramInfoGetter input) {
        try {
            getSqlMapClientTemplate()
                .insert("advanceparentprogram.insertadvanceprogramreocrd", input);

            return "success";
        } catch (Exception e) {
            loggerObject.error(" in insertAdvanceProgramRecord" + e);
        }

        return null;
    }

    /**
     * This method retrieves the list of records already set
     * for the program
     * @param input object of AdvanceParentProgramInfoGetter bean class
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<AdvanceParentProgramInfoGetter> getAdvanceProgramRecords(
        AdvanceParentProgramInfoGetter input) {
        List recordList;

        try {
            recordList = getSqlMapClientTemplate()
                             .queryForList("advanceparentprogram.getrecordslist",
                    input);

            return recordList;
        } catch (Exception e) {
            loggerObject.error(" in getAdvanceProgramRecords" + e);
        }

        return null;
    }

    /**
     * This method deletes the selected records for the program
     * @param userId user id of the user accessing the application
     * @param programId program id of the advance program
     * @param advanceProgramId program id of the advance program
     * @param items list of items selected for deletion
     * @return String
     */
    public String deleteAdvanceProgramRecord(String userId, String programId,
        String advanceProgramId, StringTokenizer items) {
        AdvanceParentProgramInfoGetter beanObject;

        try {
            while (items.hasMoreTokens()) {
                beanObject = new AdvanceParentProgramInfoGetter(programId,
                        advanceProgramId, items.nextToken());

                getSqlMapClientTemplate()
                    .delete("advanceparentprogram.deleteadvanceprogramreocrd",
                    beanObject);
            }

            return "success";
        } catch (Exception e) {
            loggerObject.error(" in deleteAdvanceProgramRecord" + e);
        }

        return null;
    }
}
