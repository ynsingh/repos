/**
 * @(#) AddDropCourseImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.adddropcourse;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.cms.dao.adddropcourse.AddDropCourseService;
import in.ac.dei.edrp.cms.domain.adddropcourse.AddDropCourseBean;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * this is Server side Implementation class for add drop course
 * 
 * @version 1.0 12 July 2011
 * @author MOHD AMIR
 * @version 2.0 12 Feb 2012
 * @author ASHISH MOHAN
 */
public class AddDropCourseImpl extends SqlMapClientDaoSupport implements
		AddDropCourseService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(AddDropCourseImpl.class);

	/** Creating object of TransactionTemplate for transaction Management */
	private TransactionTemplate transactionTemplate;

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * This method retrieves Student Course List from database
	 * 
	 * @param regNumber
	 *            , registration number
	 * @return addDropCourseBeans containing Student Course List
	 */
	@SuppressWarnings("unchecked")
	public List<AddDropCourseBean> getStudentCourseList(AddDropCourseBean input) {
		List<AddDropCourseBean> addDropCourseBeans = new ArrayList<AddDropCourseBean>();
		try {
				if(input.getStudentId().equalsIgnoreCase("MST")){
				addDropCourseBeans = getSqlMapClientTemplate().queryForList(
						"addDropCourse.getStudentCourseListMain", input);
				}
				else{
				addDropCourseBeans = getSqlMapClientTemplate().queryForList(
					"addDropCourse.getStudentCourseList", input);
				}
		} 
		catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return addDropCourseBeans;
	}

	/**
	 * This method retrieves program Details from database
	 * 
	 * @param regNumber
	 *            , registration number
	 * @return addDropCourseBeans containing program Details
	 */
	@SuppressWarnings("unchecked")
	public List<AddDropCourseBean> getProgramDetails(String regNumber) {

		List<AddDropCourseBean> addDropCourseBeans = new ArrayList<AddDropCourseBean>();
		try {
			addDropCourseBeans = getSqlMapClientTemplate().queryForList(
					"addDropCourse.getProgramDetails", regNumber);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return addDropCourseBeans;
	}

	/**
	 * This method retrieves available courses from database
	 * 
	 * @param addDropCourseBean
	 *            , Object of type AddDropCourseBean
	 * @return addDropCourseBeans containing available courses
	 */
	@SuppressWarnings("unchecked")
	public List<AddDropCourseBean> getCourseAvailable(
			AddDropCourseBean addDropCourseBean) {
		List<AddDropCourseBean> addDropCourseBeans = new ArrayList<AddDropCourseBean>();
		try {
				if(addDropCourseBean.getStudentId().equalsIgnoreCase("MST")){
				addDropCourseBeans = getSqlMapClientTemplate().queryForList(
									"addDropCourse.getCourseAvailableMain", addDropCourseBean);
				}
				else{
				addDropCourseBeans = getSqlMapClientTemplate().queryForList(
					"addDropCourse.getCourseAvailable", addDropCourseBean);
				}
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return addDropCourseBeans;
	}

	/**
	 * This method add and drop course to and from database
	 * 
	 * @param addCourseBeans
	 *            list of type AddDropCourseBean
	 * @param dropCourseBean
	 *            object of AddDropCourseBean
	 * @return result true/False
	 */
	public String addDropStudentCourse(
		final List<AddDropCourseBean> addCourseBeans,
		final AddDropCourseBean dropCourseBean) {
		return (String) transactionTemplate.execute(new TransactionCallback() {
			public String doInTransaction(TransactionStatus ts) {
				String result = "";
				int updateCount=0;
				Boolean updateFlag=true;
				Object savepoint = new Object();
				try {
					savepoint = ts.createSavepoint();
					if(dropCourseBean.getStudentId().equalsIgnoreCase("MST")){
						updateCount=getSqlMapClientTemplate().update(
								"addDropCourse.updateCourse", dropCourseBean);
						
						getSqlMapClientTemplate().update("addDropCourse.updateSRSH", dropCourseBean);
							
						for (int i = 0; i < addCourseBeans.size(); i++) {
							if(updateCount==0){
								updateFlag=false;
								break;
							}
							
							getSqlMapClientTemplate().insert(
									"addDropCourse.insertCourseMain",addCourseBeans.get(i));
						}
						
						getSqlMapClientTemplate().delete(
								"addDropCourse.deleteStuMarks", dropCourseBean);
						
						getSqlMapClientTemplate().delete(
								"addDropCourse.deleteStuMarksSum", dropCourseBean);
					}
					else{
						getSqlMapClientTemplate().delete(
							"addDropCourse.deleteCourse", dropCourseBean);

						for (int i = 0; i < addCourseBeans.size(); i++) {
							getSqlMapClientTemplate().insert(
								"addDropCourse.insertCourse",
								addCourseBeans.get(i));
						}
					}
					
					result = "true";
					
				} catch (Exception e) {
					ts.rollbackToSavepoint(savepoint);
					logObj.error(e.getMessage());
					result = "false"+e;
				}
				if(updateFlag==false){
					result="empty";
				}
				
			return result;
		}
	});
	}

	/**
	 * This method retrieves total credits from database
	 * 
	 * @param regNumber
	 *            , registration number
	 * @return total credits
	 */
	@SuppressWarnings("unchecked")
	public String getTotalCredits(String regNumber) {
		List<AddDropCourseBean> addDropCourseBeans = new ArrayList<AddDropCourseBean>();
		try {
			addDropCourseBeans = getSqlMapClientTemplate().queryForList(
					"addDropCourse.getTotalCredits", regNumber);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return addDropCourseBeans.get(0).getCredits();
	}
}