/**
 * @(#) StartActivityDao.java
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
package in.ac.dei.edrp.cms.dao.activitymaster;


import java.util.List;

import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;

public interface StartActivityDao {

	// Method returns list of process
	public List<StartActivityBean> getProcessList(
			StartActivityBean startActivityBean);

	// Method returns data for process grid
	public List<StartActivityBean> getProcessGridList(
			StartActivityBean startActivityBean);

	// Method returns data for activity grid
	public List<StartActivityBean> getProcessActivityGridList(
			StartActivityBean startActivityBean);

	// Method returns list of program course key
	public List<StartActivityBean> getProgramCourseKey(
			StartActivityBean startActivityKey);

	// Method starts activity code
	public CountProcessRecorList startActivity(
			StartActivityBean startActivityBean, String filePath, String universityName);

	// get Session start and end date according to status ACT
	public List<StartActivityBean> getSessionDate(String universityId);

	// get Semester start date and end date according to session ACT
	public List<StartActivityBean> getSemesterDate(
			StartActivityBean startActivityKey);
}
