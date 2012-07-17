/*
 * @(#) ExternalExaminarDetailImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.externalexaminercourse;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import in.ac.dei.edrp.cms.dao.externalexaminercourse.ExternalExaminarDetailDao;
import in.ac.dei.edrp.cms.domain.employee.EmployeeMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.externalexaminercourse.ExternalExaminerCourseInfoGetter;

/**
 * This file consist of the methods used for setting up the External Examinar detail
 * setup.
 * @author Mandeep Sodhi
 * @date 29 Dec 2011
 * @version 1.0
 */

public class ExternalExaminarDetailImpl extends SqlMapClientDaoSupport implements ExternalExaminarDetailDao {
	/** Creating object of TransactionTemplate for transaction Management */
	TransactionTemplate transactionTemplate = null;

/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(ExternalExaminarDetailImpl.class);
	
/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	/**
	 * This method is used to insert the Examinar Detail
	 * @param input
	 *            details of the employee
	 * @return String
	 * @throws Exception
	 */	
	public String insertExtExaminarDetail(final 
			ExternalExaminerCourseInfoGetter infoBean) {
		
		String detailFlag=(String)transactionTemplate.execute(new TransactionCallback() {
			Object savepoint=null;
			public String doInTransaction(TransactionStatus status) {
				String flag="success";
				try{
					savepoint=status.createSavepoint();
					getSqlMapClientTemplate().insert("externalExaminarDetail.insertDetail",infoBean);
					getSqlMapClientTemplate().insert("externalExaminarDetail.inputpermanentaddressdetails",infoBean);
					getSqlMapClientTemplate().insert("externalExaminarDetail.inputcoresspondenceaddressdetails",infoBean);
				System.out.println("inside impl");
				
			        }
			        catch (DataIntegrityViolationException e) {
			        	status.rollbackToSavepoint(savepoint);
			        	return "duplicate";
					}
			        catch(Exception e){
			        	status.rollbackToSavepoint(savepoint);
			        	logObj.error(e.getMessage());
			        	flag="failure";
			        	return flag;
			    	}
				return flag;
			}
		});

		System.out.println(detailFlag+"ss");
	    
		return detailFlag;
		
	}
	/**
	 * This method is used to get the List of Examinar Id
	 * @param input
	 *            details of the employee
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ExternalExaminerCourseInfoGetter> getExaminarIdDetail(ExternalExaminerCourseInfoGetter input) {
	    List<ExternalExaminerCourseInfoGetter>examinarIdList=null;
	    try{
	    	examinarIdList=new ArrayList<ExternalExaminerCourseInfoGetter>();
	    	examinarIdList=getSqlMapClientTemplate().queryForList("externalExaminarDetail.getExaminarId",input);
	    	System.out.println(examinarIdList.get(0).getExaminarId());
	    }
	    catch(Exception e){
	    	logObj.error(e.getMessage());
	    	
	    }
	    return examinarIdList;
	}
	/**
	 * This method is used to get the Basic Examinar Detail
	 * @param input
	 *            details of the employee
	 * @return String
	 * @throws Exception
	 */
	public List<ExternalExaminerCourseInfoGetter> getEmployeeDetails(
			ExternalExaminerCourseInfoGetter input) {
		List externalDetailsList;
		
		externalDetailsList = getSqlMapClientTemplate().queryForList(
				"externalExaminarDetail.getExaminarDetail", input);

		return externalDetailsList;
	
	}
	/**
	 * This method is used to get the Personal Examinar Detail
	 * @param input
	 *            details of the employee
	 * @return String
	 * @throws Exception
	 */	
	public List<ExternalExaminerCourseInfoGetter> getExternalAddressDetails(
			ExternalExaminerCourseInfoGetter input) {
		List employeeDetailsList;

		employeeDetailsList = getSqlMapClientTemplate().queryForList(
				"externalExaminarDetail.getAddressDetails", input);
//         System.out.println(employeeDetailsList.get(0));
		return employeeDetailsList;
	}
	/**
     * This method will delete the Examinar Record
     * @param examinarList Object of the referenced bean class
     * @return Boolean
     */
	public Boolean deleteExaminarRecord(final
			List<ExternalExaminerCourseInfoGetter> examinarList,final ExternalExaminerCourseInfoGetter input) {
		Boolean detailFlag=(Boolean)transactionTemplate.execute(new TransactionCallback(){
			Object savepoint=null;
			public Boolean doInTransaction(TransactionStatus status) {
				Boolean flag=false;
		System.out.println(examinarList.size()+"size");
		try{	
			savepoint=status.createSavepoint();
			 for(int i=0;i<examinarList.size();i++){
				 input.setExaminarId(examinarList.get(i).getRecordArrayCol());
				 input.setUniversityCode(examinarList.get(i).getUniversityCode());

				getSqlMapClientTemplate().delete("externalExaminarDetail.deleteDetail",input);
				getSqlMapClientTemplate().delete("externalExaminarDetail.deleteAddressDetail",input);
		        flag=true;
			 }
			 
		}
		catch(Exception e){
			status.rollbackToSavepoint(savepoint);
			logObj.error(e.getMessage());
			return flag;
			}
		return flag;
			}
		});
		System.out.println(detailFlag+"delete");
		return detailFlag;
	}
	
	/**
     * This method will update the External Examinar Details.
     * @param infobean Object of the referenced bean class
     * @return string
     */	
	public String updateExtExaminarDetail(
			final ExternalExaminerCourseInfoGetter infoBean) {
		String detailFlag=(String)transactionTemplate.execute(new TransactionCallback() {
			Object savepoint=null;
			public String doInTransaction(TransactionStatus status) {
				String flag="success";
				try{
					savepoint=status.createSavepoint();
					getSqlMapClientTemplate().insert("externalExaminarDetail.updateDetail",infoBean);
					getSqlMapClientTemplate().insert("externalExaminarDetail.updatepermanentaddressdetails",infoBean);
					getSqlMapClientTemplate().insert("externalExaminarDetail.updatecoresspondenceaddressdetails",infoBean);
				System.out.println("inside impl");
				
			        }
				
			        catch (DataIntegrityViolationException e) {
			        	status.rollbackToSavepoint(savepoint);
			        	return "duplicate";
					}
			        catch(Exception e){
			        	status.rollbackToSavepoint(savepoint);
			        	logObj.error(e.getMessage());
			        	flag="failure";
			        	return flag;
			    	}
				return flag;
			}
		});

		System.out.println(detailFlag+"ss");
	    
		return detailFlag;
	}
	


}
