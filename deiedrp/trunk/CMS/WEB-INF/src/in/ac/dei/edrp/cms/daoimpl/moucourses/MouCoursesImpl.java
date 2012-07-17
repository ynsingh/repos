/*
 * @(#) MouCoursesImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.moucourses;

import in.ac.dei.edrp.cms.dao.moucourses.MouCoursesConnect;
import in.ac.dei.edrp.cms.domain.moucourses.MouCoursesInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;
import java.util.StringTokenizer;


/**
 * This file consist of the methods used for setting up
 * the MOU University Courses setup.
 * @author Ashish
 * @date 10 Mar 2011
 * @version 1.0
 */
public class MouCoursesImpl extends SqlMapClientDaoSupport
    implements MouCoursesConnect {
    private Logger loggerObject = Logger.getLogger(MouCoursesImpl.class);

    /**
     * Method for getting the list of Universities(MOU)
     * for the concerned university
     * @param input object of the referenced bean
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<MouCoursesInfoGetter> getUniversitiesList(
        MouCoursesInfoGetter input) {
        List<MouCoursesInfoGetter> MOUList;

        loggerObject.info("In getUniversitiesList");

        input.setUniversityCode(input.getUserId().substring(1, 5));

        MOUList = getSqlMapClientTemplate()
                      .queryForList("moucourses.getMouUniversity", input);

        return MOUList;
    }

    /**
     * Method for getting the list of courses which can be offered
     * as an MOU course(offered by the selected university(MOU)) in our university
     * @param input object of the referenced bean
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<MouCoursesInfoGetter> getMOUCoursesList(
        MouCoursesInfoGetter input) {
        List<MouCoursesInfoGetter> MOUCoursesList;

        loggerObject.info("In getMOUCoursesList");

        input.setUniversityCode(input.getUserId().substring(1, 5));

        MOUCoursesList = getSqlMapClientTemplate()
                             .queryForList("moucourses.getMouCourses", input);

        return MOUCoursesList;
    }

    /**
     * Method for setting up a record
     * for university-mou university-mou course combination
     * for the activity(insert/update) performed
     * @param input object of the referenced bean
     * @return String
     */
    public String setMouCoursesDetails(MouCoursesInfoGetter input) {
        input.setUniversityCode(input.getUserId().substring(1, 5));

        loggerObject.info("In setMouCoursesDetails");

        if (input.getActivity().equalsIgnoreCase("insert")) {
            getSqlMapClientTemplate().insert("moucourses.setMOUCourse", input);
        } else if (input.getActivity().equalsIgnoreCase("update")) {
            getSqlMapClientTemplate().update("moucourses.updateMOUCourse", input);
        }

        return "success";
    }

    /**
     * Method for getting the list of records
     * already defined for the university-mou_university combination
     * @param input object of the referenced bean
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<MouCoursesInfoGetter> getSetRecordsList(
        MouCoursesInfoGetter input) {
        List<MouCoursesInfoGetter> MOURecordList;

        loggerObject.info("In getSetRecordsList");

        input.setUniversityCode(input.getUserId().substring(1, 5));

        MOURecordList = getSqlMapClientTemplate()
                            .queryForList("moucourses.getMOURecords", input);

        return MOURecordList;
    }

    /**
     * Method for deleting the records already defined
     * for the concerned university-mou_university combination
     * @param input object of the referenced bean
     * @param items items(course codes) to be deleted w.r.t university-mou_university combination
     * @return String
     */
    public String deleteMOURecords(MouCoursesInfoGetter input,
        StringTokenizer items) {
        input.setUniversityCode(input.getUserId().substring(1, 5));

        loggerObject.info("In deleteMOURecords");

        while (items.hasMoreTokens()) {
            input.setCourseCode(items.nextToken());

            getSqlMapClientTemplate()
                .delete("moucourses.deleteMOURecords", input);
        }

        return "success";
    }
}
