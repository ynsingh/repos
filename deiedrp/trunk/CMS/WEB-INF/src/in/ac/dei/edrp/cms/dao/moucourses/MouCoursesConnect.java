/*
 * @(#) MouCoursesConnect.java
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
package in.ac.dei.edrp.cms.dao.moucourses;

import in.ac.dei.edrp.cms.domain.moucourses.MouCoursesInfoGetter;

import java.util.List;
import java.util.StringTokenizer;

/**
 * This interface consist the list of methods used
 * in MouCoursesImpl file.
 * @author Ashish
 * @date 10 Mar 2011
 * @version 1.0
 */
public interface MouCoursesConnect {
    /**
     * Method for getting the list of Universities(MOU)
     * for the concerned university
     * @param input object of the referenced bean
     * @return List
     */
    List<MouCoursesInfoGetter> getUniversitiesList(MouCoursesInfoGetter input);

    /**
     * Method for getting the list of courses which can be offered
     * as an MOU course(offered by the selected university(MOU)) in our university
     * @param input object of the referenced bean
     * @return List
     */
    List<MouCoursesInfoGetter> getMOUCoursesList(MouCoursesInfoGetter input);

    /**
     * Method for setting up a record
     * for university-mou university-mou course combination
     * for the activity(insert/update) performed
     * @param input object of the referenced bean
     * @return String
     */
    String setMouCoursesDetails(MouCoursesInfoGetter input);

    /**
     * Method for getting the list of records
     * already defined for the university-mou_university combination
     * @param input object of the referenced bean
     * @return List
     */
    List<MouCoursesInfoGetter> getSetRecordsList(MouCoursesInfoGetter input);

    /**
     * Method for deleting the records already defined
     * for the concerned university-mou_university combination
     * @param input object of the referenced bean
     * @param items items(course codes) to be deleted w.r.t university-mou_university combination
     * @return String
     */
    String deleteMOURecords(MouCoursesInfoGetter input, StringTokenizer items);
}
