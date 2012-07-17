/**
 * @(#) ManualProcess.java
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
package in.ac.dei.edrp.cms.dao.manualprocess;

import in.ac.dei.edrp.cms.domain.manualprocess.ManualProcessBean;

import java.util.List;

/**
 * @author Devendra Singhal
 * @date Sept/13/2011
 * @version 1.0
 *
 */
public interface ManualProcess {
	/** Getting OwnerEntity and EntityId from database
	 * @param Object of ManualProcessBean
	 * @return List<ManualProcessBean>
	**/
	List<ManualProcessBean> getOwnerEntity(ManualProcessBean manualProcessInput);

	/** Getting Program Name ,Program Id and Program Code from database
	 * @param Object of ManualProcessBean
	 * @return List<ManualProcessBean>
	**/
	List<ManualProcessBean> getProgram(ManualProcessBean manualProcessInput);

	/** Getting Branch Name and Branch Id from database
	 * @param Object of ManualProcessBean
	 * @return List<ManualProcessBean>
	**/
	List<ManualProcessBean> getBranch(ManualProcessBean manualProcessInput);

	/** Getting Specialization Name and Specialization Id from database
	 * @param Object of ManualProcessBean
	 * @return List<ManualProcessBean>
	**/
	List<ManualProcessBean> getSpecialization(ManualProcessBean manualProcessInput);

	/** Getting Semester Name and Semester Id from database
	 * @param Object of ManualProcessBean
	 * @return List<ManualProcessBean>
	**/
	List<ManualProcessBean> getSemester(ManualProcessBean manualProcessInput);

	/** Getting Session StartDate from database
	 * @param Object of ManualProcessBean
	 * @return List<ManualProcessBean>
	**/
	List<ManualProcessBean> getSessionStartDate(ManualProcessBean manualProcessInput);
}
