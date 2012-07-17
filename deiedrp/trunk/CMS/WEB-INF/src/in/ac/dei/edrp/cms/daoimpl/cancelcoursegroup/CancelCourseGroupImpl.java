/**
 * @(#) CancelCourseGroupImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.cancelcoursegroup;

import in.ac.dei.edrp.cms.dao.cancelcoursegroup.CancelCourseGroupService;
import in.ac.dei.edrp.cms.domain.adddropcourse.AddDropCourseBean;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * This file consist of the methods used at the
 * Cancel Course Group process.
 * @author Devendra Singhal
 * @date 29 March 2012
 * @version 1.0
 */
public class CancelCourseGroupImpl extends SqlMapClientDaoSupport implements CancelCourseGroupService{
	
	/**Create instance of Logger to maintain log file*/
	private Logger loggerObject = Logger.getLogger(CancelCourseGroupImpl.class);
	/**Create instance TransactionTemplate to maintain Transactions */
	TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	/**
     * Method to get the student course group for Cancellation.
     * @param object of AddDropCourseBean
     * @return List of AddDropCourseBean
     */
	@SuppressWarnings("unchecked")
	public List<AddDropCourseBean> getStudentCourseGroupCancel(
			AddDropCourseBean addDropCourseBean) {
		List<AddDropCourseBean>semesterDateList=null;
		List<AddDropCourseBean>cancelGroupList=null;	
		try{	
			semesterDateList=getSqlMapClientTemplate().queryForList("cancelCourseGroup.getSemesterDates", addDropCourseBean);			
			if(semesterDateList.size()!=0){
				addDropCourseBean.setProgramName(semesterDateList.get(0).getProgramName());
				addDropCourseBean.setSemesterStartDate(semesterDateList.get(0).getSemesterStartDate());
				addDropCourseBean.setSemesterEndDate(semesterDateList.get(0).getSemesterEndDate());
				cancelGroupList=getSqlMapClientTemplate().queryForList("cancelCourseGroup.getStudentCourseGroupForCancel", addDropCourseBean);									
			}			
		}
		catch (Exception e) {
			loggerObject.error("Exception in CancelCourseGroupImpl : in getStudentCourseGroupCancel" + e);			
		}
		return cancelGroupList;
	}

	/**
     * Method to get the student course code detail for Cancellation.
     * @param object of AddDropCourseBean
     * @return List of AddDropCourseBean
     */
	@SuppressWarnings("unchecked")
	public List<AddDropCourseBean> getStudentCourseCodeCancel(
			AddDropCourseBean addDropCourseBean) {
		List<AddDropCourseBean>cancelCodeList=null;	
		try{				
			cancelCodeList=getSqlMapClientTemplate().queryForList("cancelCourseGroup.getStudentCourseCodeForCancel", addDropCourseBean);									
						
		}
		catch (Exception e) {
			loggerObject.error("Exception in CancelCourseGroupImpl : in getStudentCourseCodeCancel" + e);			
		}
		return cancelCodeList;
	}

	/**
     * Method to get the student course group to insert.
     * @param object of AddDropCourseBean
     * @return List of AddDropCourseBean
     */
	@SuppressWarnings("unchecked")
	public List<AddDropCourseBean> getStudentCourseGroupAdd(
			AddDropCourseBean addDropCourseBean) {
		List<AddDropCourseBean>addCodeList=null;			
		try{				
			addCodeList=getSqlMapClientTemplate().queryForList("cancelCourseGroup.getStudentCourseGroupForAdd", addDropCourseBean);									
						
		}
		catch (Exception e) {
			loggerObject.error("Exception in CancelCourseGroupImpl : in getStudentCourseGroupAdd" + e);			
		}
		return addCodeList;
	}
	
	/**
     * Method to get the student course code to insert.
     * @param object of AddDropCourseBean
     * @return List of AddDropCourseBean
     */
	@SuppressWarnings("unchecked")
	public List<AddDropCourseBean> getStudentCourseCodeAdd(
			AddDropCourseBean addDropCourseBean) {
		List<AddDropCourseBean>addCodeList=null;	
		try{				
			addCodeList=getSqlMapClientTemplate().queryForList("cancelCourseGroup.getStudentCourseCodeForAdd", addDropCourseBean);									
						
		}
		catch (Exception e) {
			loggerObject.error("Exception in CancelCourseGroupImpl : in getStudentCourseCodeAdd" + e);			
		}
		return addCodeList;
	}

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
	public String cancelCourse(final AddDropCourseBean addDropCourseBean,
			final String theoryCredits, final String practicalCredits,
			final String totalAddCredit,final String cancelTheoryCredit,final String cancelPracticalCredit,final String cancelTotalCredit,final String totalAddCreditExcludingCredit,final String cancelTotalCreditExcludindCredit,
			final String cancelGroup, final String addGroup,final StringTokenizer addCourseToken, final String flag) {
		String message="";
		message=(String) transactionTemplate.execute(new TransactionCallback() {
			Object savepoint = null;
			String message="";
			@SuppressWarnings("unchecked")
			public Object doInTransaction(TransactionStatus status) {
				savepoint = status.createSavepoint();
				try{
					//Get maximum credits for a particular program
					Object maxCredit=getSqlMapClientTemplate().queryForObject("cancelCourseGroup.getMaxCredit", addDropCourseBean);
					Float maximumCredit=Float.parseFloat((String) maxCredit);					
					//Get Total credits from database for update
					List<AddDropCourseBean>srshList=getSqlMapClientTemplate().queryForList("cancelCourseGroup.getSRSHCreditsForUpdate", addDropCourseBean);					
					//calculate credits to update
					float totalUpdateCredit=(Float.parseFloat(srshList.get(0).getMinimumCredits())-Float.parseFloat(cancelTotalCredit))+Float.parseFloat(totalAddCredit);
					float totalUpdateCreditExcludingAudit=(Float.parseFloat(srshList.get(0).getMaximumCredits())-Float.parseFloat(cancelTotalCreditExcludindCredit))+Float.parseFloat(totalAddCreditExcludingCredit);
					float theoryUpdagteCredit=(Float.parseFloat(srshList.get(0).getTheoryCredits())-Float.parseFloat(cancelTheoryCredit))+Float.parseFloat(theoryCredits);
					float practicalUpdagteCredit=(Float.parseFloat(srshList.get(0).getPracticalCredits())-Float.parseFloat(cancelPracticalCredit))+Float.parseFloat(practicalCredits);
					
					if(totalUpdateCredit<=maximumCredit){
						addDropCourseBean.setCourseGroup(cancelGroup);					
						//Update student_course table set course status 'CAN' For cancel course group
						getSqlMapClientTemplate().update("cancelCourseGroup.updateStudentCourse", addDropCourseBean);
						
						//For Add Course Group
						addDropCourseBean.setCourseGroup(addGroup);	
						
						while(addCourseToken.hasMoreTokens()){
							String code=addCourseToken.nextToken();							
							String splData[]=code.split(",");
							if(splData.length>0){
								addDropCourseBean.setCourseCode(splData[0]);
								addDropCourseBean.setCourseName(splData[1]);
							}					
							//Insert into student_course table  For add course group			
							getSqlMapClientTemplate().insert("cancelCourseGroup.insertStudentCourse", addDropCourseBean);
						}
						
						//For Update SRSH(student_registration_semester_header) table
						if(flag.equals("GREATER")){
							addDropCourseBean.setTheoryCredits(String.valueOf(theoryUpdagteCredit));
							addDropCourseBean.setPracticalCredits(String.valueOf(practicalUpdagteCredit));
							addDropCourseBean.setMaximumCredits(String.valueOf(totalUpdateCreditExcludingAudit));
							addDropCourseBean.setMinimumCredits(String.valueOf(totalUpdateCredit));
							//Update SRSH 							
							getSqlMapClientTemplate().update("cancelCourseGroup.updateSRSH", addDropCourseBean);
						}
						message="success";	
					}
					else{
						message="greaterThanMaxCredit-"+totalUpdateCredit+"-"+maximumCredit;
					}
				}
				catch (Exception e) {
					message="sqlError";					
					loggerObject.error("Exception in CancelCourseGroupImpl : in cancelCourse " + e);	
					status.rollbackToSavepoint(savepoint);	
				}
				return message;
			}
		});
		
	
		return message;
	}


}
