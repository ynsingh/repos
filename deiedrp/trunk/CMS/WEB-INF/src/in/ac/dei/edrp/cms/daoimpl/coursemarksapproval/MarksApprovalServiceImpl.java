/**
 * @(#) MarksApprovalServiceImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.coursemarksapproval;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import in.ac.dei.edrp.cms.dao.coursemarksapproval.MarksApprovalService;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.coursemarksapproval.MarksApprovalInfo;
import in.ac.dei.edrp.cms.domain.programcoursetypesummary.ProgramCourseTypeSummaryInfoGetter;

/**
 * this is Server side Implementation class for Marks Approval
 * 
 * @version 1.0 16 MAR 2011
 * @author MOHD AMIR
 */
public class MarksApprovalServiceImpl extends SqlMapClientDaoSupport implements
		MarksApprovalService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(UniversityReservationServiceImpl.class);

	/**
	 * This method retrieves Approval Details from database
	 *
	 * @param marksApprovalInfo, object of bean class MarksApprovalInfo
	 * @return approvalDetails containing Approval Details
	 */
	@SuppressWarnings("unchecked")
	public List<MarksApprovalInfo> getApprovalDetails(
			MarksApprovalInfo marksApprovalInfo) {
		List<MarksApprovalInfo> approvalDetails = new ArrayList<MarksApprovalInfo>();
		try {			
			approvalDetails = getSqlMapClientTemplate().queryForList(
					"marksApproval.getCourseMarksApprovalDetails", marksApprovalInfo);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return approvalDetails;
	}

	/**
	 * This method retrieves Course Details from database
	 *
	 * @param marksApprovalInfo, object of bean class MarksApprovalInfo
	 * @return courseList containing Course Details
	 */
	@SuppressWarnings("unchecked")
	public List<MarksApprovalInfo> getCourseList(
			MarksApprovalInfo marksApprovalInfo) {
		List<MarksApprovalInfo> courseList = new ArrayList<MarksApprovalInfo>();
		try {
			courseList = getSqlMapClientTemplate().queryForList(
					"marksApproval.getCourseList", marksApprovalInfo);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return courseList;
	}

	/**
	 * This method retrieves Employee Details from database
	 *
	 * @param marksApprovalInfo, object of bean class MarksApprovalInfo
	 * @return employeeList containing Employee Details
	 */
	@SuppressWarnings("unchecked")
	public List<MarksApprovalInfo> getEmployeeList(
			MarksApprovalInfo marksApprovalInfo) {
		List<MarksApprovalInfo> employeeList = new ArrayList<MarksApprovalInfo>();
		try {
			employeeList = getSqlMapClientTemplate().queryForList(
					"marksApproval.getEmployeeList", marksApprovalInfo);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return employeeList;
	}

	/**
	 * This method insert the marks approval details into database
	 *
	 * @param marksApprovalInfo, object of bean class MarksApprovalInfo
	 * @return value of type boolean show whether the record are inserted or not
	 */
	@SuppressWarnings("unchecked")
	public String setApprovalDetails(MarksApprovalInfo marksApprovalInfo,String data) {
		try {
			StringTokenizer token=new StringTokenizer(data, "|");
			List<MarksApprovalInfo> countList = new ArrayList<MarksApprovalInfo>();
			int total=token.countTokens();
			int insertedRecord=0;
			String emp="";
			while(token.hasMoreTokens()){
				String record=token.nextToken();
				StringTokenizer recordToken=new StringTokenizer(record, ",");			
				while(recordToken.hasMoreTokens()){
					String empCod=recordToken.nextToken();
					marksApprovalInfo.setEmployeeCode(empCod);
					String entityCD=recordToken.nextToken();
					marksApprovalInfo.setEntityCode(entityCD);				
					countList = getSqlMapClientTemplate().queryForList("marksApproval.getDuplicateRecordCount", marksApprovalInfo);
					int count= countList.get(0).getCount();
					if(count==0){
							if(marksApprovalInfo.getSequenceNo()==null){
								Object obj=getSqlMapClientTemplate().queryForObject("marksApproval.getSequenceNo", marksApprovalInfo);								
								if((String)obj==null || ((String)obj).equals(null)){
									marksApprovalInfo.setSequenceNo("1");
								}
								else{
									int seq=Integer.parseInt((String) obj);
									marksApprovalInfo.setSequenceNo(String.valueOf(seq+1));
								}
								
							}							
							getSqlMapClientTemplate().insert("marksApproval.setMarksApprovalDetails", marksApprovalInfo);
							insertedRecord++;						
					}
					else{
						//Write Duplicate Record into log file
						logObj.error("Duplicate record : programCourseKey "+marksApprovalInfo.getProgramCourseKey()+" courseCode : "+marksApprovalInfo.getCourseCode()
									 +" entityCode : "+marksApprovalInfo.getEntityCode()+" employeeCode : "+marksApprovalInfo.getEmployeeCode()+" approvalOrder : "+marksApprovalInfo.getApprovalOrder());
						emp=emp+marksApprovalInfo.getEmployeeCode()+",";
					}
				}
			}
			return "success|"+total+"|"+insertedRecord+"|"+(total-insertedRecord)+"|"+emp;
		} catch (Exception e) {
			logObj.error("Error in method setApprovalDetails inside MarksApprovalServiceImpl : "+e.getMessage());
			return "error"+e.getMessage();
		}
	}

	/**
	 * This method get duplicate record count from database 
	 *
	 * @param registrationNo, registration Number
	 * @return count,Number of record found
	 */
	 @SuppressWarnings("unchecked")
	public int getDuplicateCount(
			MarksApprovalInfo marksApprovalInfo) {
		List<MarksApprovalInfo> countList = new ArrayList<MarksApprovalInfo>();
		int count=1;
		try {
			countList = getSqlMapClientTemplate().queryForList("marksApproval.getDuplicateRecordCount", marksApprovalInfo);
			count= countList.get(0).getCount();
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return count;
	}

	/**
	 * This method delete Approval Details from database
	 *
	 * @param marksApprovalInfo, object of bean class MarksApprovalInfo
	 * @return count,no of record deleted
	 */
	public int deleteApprovalDetails(MarksApprovalInfo marksApprovalInfo) {
		int count=0;
		try {
			if(marksApprovalInfo.getSequenceNo()==null){
				count = getSqlMapClientTemplate().delete("marksApproval.deleteApprovalDetailsForSeqNull", marksApprovalInfo);
			}
			else{
				count = getSqlMapClientTemplate().delete("marksApproval.deleteApprovalDetails", marksApprovalInfo);
			}
			
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return count;
	}

	/**
	 * This method update Approval Details into database
	 *
	 * @param marksApprovalInfo, object of bean class MarksApprovalInfo
	 * @return count,no of record updated
	 */
	public int updateApprovalDetails(MarksApprovalInfo marksApprovalInfo) {
		int count=0;
		try {
			count = getSqlMapClientTemplate().update("marksApproval.updateApprovalDetails", marksApprovalInfo);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return count;
	}
	
	
	/**
	 * This method get program details from database
	 *@author Devendra Singhal
	 * @param ProgramCourseTypeSummaryInfoGetter, object of bean class ProgramCourseTypeSummaryInfoGetter
	 * @return List<ProgramCourseTypeSummaryInfoGetter> program details
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramCourseTypeSummaryInfoGetter> getProgramDetails(ProgramCourseTypeSummaryInfoGetter programCourseTypeSummaryInfoGetter) {
		List<ProgramCourseTypeSummaryInfoGetter>list=null;	
		try {
			list = getSqlMapClientTemplate().queryForList("marksApproval.getProgramDetails", programCourseTypeSummaryInfoGetter);
		} catch (Exception e) {
			logObj.error("Error inside getProgramDetails method in MarksApprovalServiceImpl class : "+e.getMessage());
		}
		return list;
	}
	
	/**
	 * This method get program entity from database
	 *@author Devendra Singhal
	 * @param ProgramCourseTypeSummaryInfoGetter, object of bean class ProgramCourseTypeSummaryInfoGetter
	 * @return List<ProgramCourseTypeSummaryInfoGetter> program details
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramCourseTypeSummaryInfoGetter> getEntity(ProgramCourseTypeSummaryInfoGetter programCourseTypeSummaryInfoGetter) {
		List<ProgramCourseTypeSummaryInfoGetter>list=null;
		programCourseTypeSummaryInfoGetter.setComponentId(programCourseTypeSummaryInfoGetter.getUniversityId()+"%");
		try {
			list = getSqlMapClientTemplate().queryForList("marksApproval.getEntity", programCourseTypeSummaryInfoGetter);
		} catch (Exception e) {
			logObj.error("Error inside getProgramDetails method in MarksApprovalServiceImpl class : "+e.getMessage());
		}
		return list;
	}

	/** Getting message that is a employee attach to a particular course
	 * @author Devendra Singhal
	 * @param Object of marksApprovalInfo been
	 * @return String count
	 **/
	public String getCourseEmployee(MarksApprovalInfo marksApprovalInfo) {
		String count="";
		try {
			Object obj = getSqlMapClientTemplate().queryForObject("marksApproval.getCourseEmployee", marksApprovalInfo);
			count=String.valueOf(obj);
		} catch (Exception e) {
			count="error";
			logObj.error("Error inside getProgramDetails method in MarksApprovalServiceImpl class : "+e.getMessage());
		}
		return count;
	}

	/** Method to check sequence no already exists for a particular course
	 * @author Devendra Singhal
	 * @param  Object of marksApprovalInfo been
	 * @return String count
	 */
	public String checkSequence(MarksApprovalInfo marksApprovalInfo) {
		String message="";
		try {			
			message = (String) getSqlMapClientTemplate().queryForObject("marksApproval.getSequenceToCheck", marksApprovalInfo);					
		} catch (Exception e) {
			message="error";
			logObj.error("Error inside getProgramDetails method in MarksApprovalServiceImpl class : "+e.getMessage());
		}
		return message;
	}
	
	/** Method to Get Display Type From database
	 * @author Devendra Singhal
	 * @param  Object of marksApprovalInfo been
	 * @return List<MarksApprovalInfo>
	 */
	@SuppressWarnings("unchecked")
	public List<AwardSheetInfoGetter> getDisplayType(
			MarksApprovalInfo marksApprovalInfo) {
		List<AwardSheetInfoGetter> dispList=null;
		try {			
			dispList = getSqlMapClientTemplate().queryForList("marksApproval.getDisplayType", marksApprovalInfo);			
		} catch (Exception e) {			
			logObj.error("Error inside getProgramDetails method in MarksApprovalServiceImpl class : "+e.getMessage());
		}
		return dispList;
	}
}