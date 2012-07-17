/**
 * @(#) SystemTableTwoService.java
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
package in.ac.dei.edrp.cms.dao.systemtabletwo;

import java.util.List;
import java.util.StringTokenizer;

import in.ac.dei.edrp.cms.domain.systemtabletwo.SystemTableTwoBean;
import in.ac.dei.edrp.cms.domain.utility.SystemValue;


/**
 * The client interface for SystemTableTwo.
 *
 * @version 1.0 14 FEB 2011
 * @author MOHD AMIR
 */
public interface SystemTableTwoService {
	/** Getting group components Details from database */
	public List<SystemTableTwoBean> getGroupDetails(SystemTableTwoBean systemTableTwoBean);
	
	/** adding component details to database */
	public void setComponentDetails(SystemTableTwoBean systemTableTwoBean);
	
	/** Checking for duplicate component in database */
	public int getDuplicateComponentCount(SystemTableTwoBean systemTableTwoBean);
	
	/** deleting  component details from database */
	public int deleteComponent(SystemTableTwoBean systemTableTwoBean);
	
	/** Updating component details from database */
	public int updateComponent(SystemTableTwoBean systemTableTwoBean);
	/**
	 * The method builds system values table for the university
	 * @param systemValueBean
	 * @param codesInput codes and values for system_values table
	 * @return success once build is completed
	 */
	public String buildSystemValues(
			SystemValue systemValueBean, StringTokenizer codesInput);
}
