/**
 * @(#) CancelCourseGroupService.java
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
package in.ac.dei.edrp.cms.dao.cancelcoursegroup;

import in.ac.dei.edrp.cms.domain.adddropcourse.AddDropCourseBean;

import java.util.List;
import java.util.StringTokenizer;

/**
 * This interface consist the list of methods used
 * in CancelCourseGroupImpl file.
 * @author Devendra Singhal
 * @date 29 March 2012
 * @version 1.0
 */
public interface CancelCourseGroupService {

	/**
     * Method to get the student course group for Cancellation.
     * @param object of AddDropCourseBean
     * @return List of AddDropCourseBean
     */
	List<AddDropCourseBean> getStudentCourseGroupCancel(AddDropCourseBean addDropCourseBean);
	
	/**
     * Method to get the student course code detail for Cancellation.
     * @param object of AddDropCourseBean
     * @return List of AddDropCourseBean
     */
	List<AddDropCourseBean> getStudentCourseCodeCancel(AddDropCourseBean addDropCourseBean);
	
	/**
     * Method to get the student course group to insert.
     * @param object of AddDropCourseBean
     * @return List of AddDropCourseBean
     */
	List<AddDropCourseBean> getStudentCourseGroupAdd(AddDropCourseBean addDropCourseBean);
	
	/**
     * Method to get the student course code detail for Insert.
     * @param object of AddDropCourseBean
     * @return List of AddDropCourseBean
     */
	List<AddDropCourseBean> getStudentCourseCodeAdd(AddDropCourseBean addDropCourseBean);
	
	/**
     * Method to cancel student course group and add course group
     * @param object of AddDropCourseBean
	 * @param theoryCredits 
	 * @param practicalCredits 
	 * @param totalAddCredit
	 * @param cancelTheoryCredit 
	 * @param cancelPracticalCredit 
	 * @param cancelTotalCredit 
	 * @param totalAddCreditExcludingAudit
	 * @param cancelTotalCreditExcludingAudit    
	 * @param cancelGroup 
	 * @param addGroup 
	 * @param StringTokenizer courseToken
	 * @param flag 
     * @return String message
     */
	String cancelCourse(AddDropCourseBean addDropCourseBean, String theoryCredits, String practicalCredits, String totalAddCredit,String cancelTheoryCredit
			,String cancelPracticalCredit,String cancelTotalCredit,String totalAddCreditExcludingAudit,String cancelTotalCreditExcludingAudit, String cancelGroup, String addGroup, StringTokenizer courseToken, String flag);
}
