/**
 * @(#) ConsolidatedMarksheetImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.consolidatedchart;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.cms.dao.consolidatedchart.ConsolidatedMarksheetService;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedMarkSheetInfo;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedMarkSheetInfo;
import in.ac.dei.edrp.cms.domain.coursegroup.CourseGroupBean;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * The server side implementation of the RPC service for Consolidated Marksheet Generation.
 * @version 1.0 07 SEP 2011
 * @author ROHIT SAXENA
 */
public class ConsolidatedMarksheetImpl extends SqlMapClientDaoSupport implements ConsolidatedMarksheetService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(ConsolidatedChartImpl.class);

/**
* This method is used for fetching the student Details
* @param input Object of the referenced bean class
* @return List
*/
@SuppressWarnings("unchecked")
public List<ConsolidatedMarkSheetInfo> getStudentDetails(
		ConsolidatedMarkSheetInfo consolidatedMarkSheetInfo) {
	List<ConsolidatedMarkSheetInfo> dataList = new ArrayList<ConsolidatedMarkSheetInfo>();
	try {
		dataList = getSqlMapClientTemplate().queryForList(
				"consolidatedmarksheet.getStudentDetails",
				consolidatedMarkSheetInfo);
	} catch (Exception e) {
		logObj.error("In function getStudentDetails"+e.getMessage());
	}
	return dataList;
}

/**
* This method is used for fetching the student marks
* @param input Object of the referenced bean class
* @return List
*/
@SuppressWarnings("unchecked")
public List<ConsolidatedMarkSheetInfo> getMarksDetails(
		ConsolidatedMarkSheetInfo consolidatedMarkSheetInfo) {
	List<ConsolidatedMarkSheetInfo> dataList = new ArrayList<ConsolidatedMarkSheetInfo>();
	try {
		dataList = getSqlMapClientTemplate().queryForList(
				"consolidatedmarksheet.getCourseMarksDetails",
				consolidatedMarkSheetInfo);
	} catch (Exception e) {
		logObj.error("In function getMarksDetails"+e.getMessage());
	}
	return dataList;
}

/**
* This method is used for fetching the branch list
* @param input Object of the referenced bean class
* @return List
*/
@SuppressWarnings("unchecked")
public List<ConsolidatedMarkSheetInfo> getBranchList(
		ConsolidatedMarkSheetInfo input) {
	List<ConsolidatedMarkSheetInfo> dataList = new ArrayList<ConsolidatedMarkSheetInfo>();
	try{
		dataList = getSqlMapClientTemplate().queryForList(
				"consolidatedmarksheet.getBranch", input);
	}
	catch (Exception e) {
		logObj.error("In function getBranchList"+e.getMessage());
	}
	return dataList;
}

/**
* This method is used for fetching the Entity list
* @param input Object of the referenced bean class
* @return List
*/
@SuppressWarnings("unchecked")
public List<ConsolidatedMarkSheetInfo> getEntitiesList(
		ConsolidatedMarkSheetInfo input) {
	
	List<ConsolidatedMarkSheetInfo> dataList = new ArrayList<ConsolidatedMarkSheetInfo>();
	try{
		dataList = getSqlMapClientTemplate().queryForList(
				"consolidatedmarksheet.getEntity", input);

	}
	catch (Exception e) {
		logObj.error("In function getEntitiesList"+e.getMessage());
	}
	return dataList;
}

/**
* This method is used for fetching the program list
* @param input Object of the referenced bean class
* @return List
*/
@SuppressWarnings("unchecked")
public List<ConsolidatedMarkSheetInfo> getProgramList(
		ConsolidatedMarkSheetInfo input) {
	List<ConsolidatedMarkSheetInfo> dataList = new ArrayList<ConsolidatedMarkSheetInfo>();
	try{
		dataList = getSqlMapClientTemplate().queryForList(
				"consolidatedmarksheet.getProgram", input);

	}
	catch (Exception e) {
		logObj.error("In function getProgramList"+e.getMessage());
	}
	return dataList;
}

/**
* This method is used for fetching the specialization list
* @param input Object of the referenced bean class
* @return List
*/
@SuppressWarnings("unchecked")
public List<ConsolidatedMarkSheetInfo> getSpecializationList(
		ConsolidatedMarkSheetInfo input) {
	List<ConsolidatedMarkSheetInfo> dataList = new ArrayList<ConsolidatedMarkSheetInfo>();
	try{
		
		dataList = getSqlMapClientTemplate().queryForList(
				"consolidatedmarksheet.getSpecialization", input);

	}
	catch (Exception e) {
		logObj.error("In function getSpecializationList"+e.getMessage());
	}
	return dataList;
}

/**
* This method is used for fetching the student marks
* @param input Object of the referenced bean class
* @return List
*/
@SuppressWarnings("unchecked")
public ConsolidatedMarkSheetInfo getAddressInfo(ConsolidatedMarkSheetInfo consolidatedMarkSheetInfo) {
	ConsolidatedMarkSheetInfo dataList = new ConsolidatedMarkSheetInfo();
	try {
		dataList = (ConsolidatedMarkSheetInfo) getSqlMapClientTemplate().queryForObject("consolidatedmarksheet.getAddressEntity",consolidatedMarkSheetInfo);
	} catch (Exception e) {
		logObj.error("In function getAddressInfo"+e.getMessage());
	}
	return dataList;
}

}
