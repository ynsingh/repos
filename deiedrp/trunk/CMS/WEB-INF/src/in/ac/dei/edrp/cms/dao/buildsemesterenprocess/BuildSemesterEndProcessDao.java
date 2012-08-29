/**
 * @(#) BuildSemesterEndProcessDao.java
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
package in.ac.dei.edrp.cms.dao.buildsemesterenprocess;

import in.ac.dei.edrp.cms.domain.buildsemesterendprocess.BuildSemesterEndProcess;

import java.util.List;

/**
 * This interface consist the list of methods used in BuildSemesterEndProcessImpl file.
 * @author Devendra Singhal
 * @date Dec 02 2011
 * @version 1.0
 */
public interface BuildSemesterEndProcessDao {

	/**
     * Method to Clear Temporary Tables
     * @param object of BuildSemesterEndProcess
     * @return String message
     */
	String clearTempTables(BuildSemesterEndProcess buildSemesterEndProcess);
	
	/**
     * Method to Build REG in semester_processing_control table for next semester
     * @param object of BuildSemesterEndProcess
     * @return String message
     */
	String readyForRegistration(BuildSemesterEndProcess buildSemesterEndProcess);
	
	/**
     * Method to Build SEP in semester_processing_control table for current semester
     * @param object of BuildSemesterEndProcess
     * @return String message
     */
	String readyForSemesterEnd(BuildSemesterEndProcess buildSemesterEndProcess);
	
}