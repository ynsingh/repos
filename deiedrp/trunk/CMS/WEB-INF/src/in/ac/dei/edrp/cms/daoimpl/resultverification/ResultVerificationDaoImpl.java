/**
 * @(#) ResultVerificationDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.resultverification;

import in.ac.dei.edrp.cms.dao.resultverification.ResultVerificationDao;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;
import in.ac.dei.edrp.cms.domain.resultverification.ResultVerificationBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;
import com.sun.org.apache.commons.collections.ListUtils;

/**
 * This file consist of the methods used at the Staging Table Report Generation.
 * 
 * @author Nupur Dixit
 * @date 26 Sep 2012
 * @version 1.0
 */
@Component
@Qualifier("ResultVerificationImpl")
public class ResultVerificationDaoImpl extends SqlMapClientDaoSupport implements ResultVerificationDao {

	@Autowired
	TransactionTemplate transactionTemplate;
	
	public ResultVerificationDaoImpl(){}
	@Autowired
	public ResultVerificationDaoImpl(SqlMapClient sqlMapClient){		
		setSqlMapClient(sqlMapClient);
	}
	public ResultVerificationDaoImpl(SqlMapClient sqlMapClient,TransactionTemplate transactionTemplate){
		this.transactionTemplate=transactionTemplate;
		setSqlMapClient(sqlMapClient);
	}
	/*Create Logger Object for maintain log file.*/	
	private Logger loggerObject = Logger.getLogger(ResultVerificationDaoImpl.class);

	public ResultVerificationBean getRequestNo(ResultVerificationBean resultVerificationBean) {
		ResultVerificationBean requestNoBean = null;
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String yaerLastDigit = year.substring(year.length()-2);
		String requestCode;
		String requestNo;
		System.out.println("year last two digits "+yaerLastDigit);
		resultVerificationBean.setExtra(yaerLastDigit);
		try{
			requestNoBean = (ResultVerificationBean) getSqlMapClientTemplate().queryForObject("ResultVerification.getRequestNo", resultVerificationBean);		
			if(requestNoBean==null || requestNoBean.getRequestNo()==null){
				requestNoBean = new ResultVerificationBean();
				requestCode = String.format("%04d",1);
				requestNo = yaerLastDigit+requestCode;
				System.out.println("final request no for null case"+requestNo);
				requestNoBean.setRequestNo(requestNo);
			}
			else{
				requestNo = String.valueOf(Integer.parseInt(requestNoBean.getRequestNo())+1);
				requestNoBean.setRequestNo(requestNo);
		}
		} catch(Exception e){
			System.out.println("inside get request no error");
			e.printStackTrace();
		}
		return requestNoBean;
	}
	public List<ResultVerificationBean> getRollNo(ResultVerificationBean resultVerificationBean) {
		List<ResultVerificationBean> rollNoList = new ArrayList<ResultVerificationBean>();
		try{
			rollNoList = getSqlMapClientTemplate().queryForList("ResultVerification.getRollNo", resultVerificationBean);
		} catch(Exception e){
			System.out.println("inside get request no error");
			e.printStackTrace();
		}
		return rollNoList;
	}
	
	/**
	 * Method is used to insert value in result verification request header and detail
	 * @param input object of ResultVerificationBean
	 * @return String(success/failure)
	 * @author Nupur Dixit
	 * @date 05-Nov-2012
	 * @version 1.0
	 */
	public String insertResultVerValue(final ResultVerificationBean inputBean ) {
		String s = (String) transactionTemplate
		.execute(new TransactionCallback() {
			public String doInTransaction(TransactionStatus tStatus) {
				/*
				 * here we'll create a point to which the function revert when
				 * an exception is encountered during the process
				 */
				System.out.println("before save point declaration");
				Object savepoint = null;
				String status="false";
				try {
					savepoint = tStatus.createSavepoint();
					ResultVerificationBean systemValueInfoBean = new ResultVerificationBean();
					System.out.println("cretooooooooooooor id "+inputBean.getCreatorId());
					systemValueInfoBean.setModifierId(inputBean.getCreatorId());
					systemValueInfoBean =generateRequestNumber(inputBean.getUniversityId());
					String requestNumber = systemValueInfoBean.getRequestNo();
					inputBean.setRequestNo(requestNumber);
					//query to insert in request header table
					getSqlMapClientTemplate().insert("ResultVerification.insertResultVerHeader", inputBean);
					System.out.println("after header insert");
					//list of roll no list is iterated to input in detail table
					for (String rollNo:inputBean.getRollNoList()) {
						inputBean.setRollNumber(rollNo);
						getSqlMapClientTemplate().insert("ResultVerification.insertResultVerDetail",inputBean);
					}
					System.out.println("after detail insert");
					/*
					 * Query for updating request number value in system_values table
					 * if no error in the processing
					 */
					String newRequestNumber = String.valueOf(Integer.parseInt(systemValueInfoBean.getRequestNo())+1);
					systemValueInfoBean.setRequestNo(newRequestNumber);
					getSqlMapClientTemplate().update("ResultVerification.updateSystemValue",systemValueInfoBean);	
					status = "true";
					return status;
				} catch (Exception ex) {
					loggerObject.info("connection rollback..." + ex);
					tStatus.rollbackToSavepoint(savepoint);
					loggerObject.error(ex.getMessage());
					ex.printStackTrace();
					return status;
				}
			}
		});
		return s;
	}

	/**
	 * This method is used to generate unique request number 
	 * @return ResultVerificationBean
	 * @author Nupur Dixit
	 * @date 05-Nov-2012
	 * @version 1.0
	 */
	public ResultVerificationBean generateRequestNumber(String universityCode) {
		ResultVerificationBean systemValueInfoBean = new ResultVerificationBean();
		ResultVerificationBean fetchedSystemValueInfoBean = new ResultVerificationBean();
		//currently it is hard coded, in future session variable will replace this value
		systemValueInfoBean.setUniversityId(universityCode);
		//in system_values table, code is RSVNUM for result verification number
		systemValueInfoBean.setRequestCode("RSVNUM");
		String requestNumber="";
		try{
			/*
			 * Query for getting the value from system_values for the above binded code
			 */
			fetchedSystemValueInfoBean = (ResultVerificationBean) getSqlMapClientTemplate().queryForObject("ResultVerification.getSystemValueDetail",systemValueInfoBean);
			/*
			 * This part of code is used to reinitialize the Request number if year is incremented
			 */
			int actualYear = Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2));
			int sysYear = Integer.parseInt(fetchedSystemValueInfoBean.getRequestNo().substring(0,2));
			if(sysYear<actualYear){
				requestNumber = String.valueOf(actualYear)+"0001";
			}
			else{
				requestNumber = fetchedSystemValueInfoBean.getRequestNo();
			}
			systemValueInfoBean.setRequestNo(requestNumber);
		}catch (Exception e) {
			loggerObject.error("Exception in generateRequestNumber" + e);
			e.printStackTrace();
		}
		return systemValueInfoBean;
	}
	
	public List<ResultVerificationBean> getRequestDetail(ResultVerificationBean resultVerificationBean) {
		List<ResultVerificationBean> requestList = new ArrayList<ResultVerificationBean>();
		try{
			requestList = getSqlMapClientTemplate().queryForList("ResultVerification.getRequestDetail", resultVerificationBean);
		} catch(Exception e){
			System.out.println("inside get request no error");
			e.printStackTrace();
		}
		return requestList;
	}
	public List<ResultVerificationBean> getRequestHeader(ResultVerificationBean resultVerificationBean) {
		List<ResultVerificationBean> requestList = new ArrayList<ResultVerificationBean>();
		try{
			requestList = getSqlMapClientTemplate().queryForList("ResultVerification.getRequestHeader", resultVerificationBean);
		} catch(Exception e){
			System.out.println("inside get request no error");
			e.printStackTrace();
		}
		return requestList;
	}
	
	
	/**
	 * Method is used to delete records in result verification request header and detail
	 * @param input List object of ResultVerificationBean
	 * @return String(success/failure)
	 * @author Nupur Dixit
	 * @date 16-Nov-2012
	 * @version 1.0
	 */
	public String deleteResultVerRecord(final List<ResultVerificationBean> resultVerificationBeanList) {
		String s = (String) transactionTemplate.execute(new TransactionCallback() {
			public String doInTransaction(TransactionStatus tStatus) {
				/*
				 * here we'll create a point to which the function revert when
				 * an exception is encountered during the process
				 */
				System.out.println("before save point declaration");
				Object savepoint = null;
				String status="false";
				try {
					savepoint = tStatus.createSavepoint();
					for(ResultVerificationBean beanObject:resultVerificationBeanList){
						//query to delete  request header table record
						getSqlMapClientTemplate().delete("ResultVerification.deleteResultVerHeader", beanObject);
						System.out.println("after header delete");
						//query to delete  request detail table record
						getSqlMapClientTemplate().delete("ResultVerification.deleteResultVerDetailAll", beanObject);
						System.out.println("after detail delete");
					}
					System.out.println("after header/detail delete");						
					status = "true";
					return status;
				} catch (Exception ex) {
					loggerObject.info("connection rollback..." + ex);
					tStatus.rollbackToSavepoint(savepoint);
					loggerObject.error(ex.getMessage());
					ex.printStackTrace();
					return status;
				}
			}
		});
		return s;
	}
	/**
	 * Method is used to update value in result verification request header and detail
	 * @param input object of ResultVerificationBean
	 * @return String(success/failure)
	 * @author Nupur Dixit
	 * @date 09-Nov-2012
	 * @version 1.0
	 */
	public String updateResultVerValue(final ResultVerificationBean inputBean ) {
		String s = (String) transactionTemplate
		.execute(new TransactionCallback() {
			public String doInTransaction(TransactionStatus tStatus) {
				/*
				 * here we'll create a point to which the function revert when
				 * an exception is encountered during the process
				 */
				System.out.println("before save point declaration");
				Object savepoint = null;
				String status="false";
				try {
					savepoint = tStatus.createSavepoint();					
					System.out.println("cretooooooooooooor id "+inputBean.getCreatorId());		
					List<String> diff = ListUtils.subtract(Arrays.asList(inputBean.getRollNoList()), Arrays.asList(inputBean.getOlderRollNoList()));
					System.out.println("diff size :"+diff.size());
					//query to update in request header table
					getSqlMapClientTemplate().update("ResultVerification.updateResultVerHeader", inputBean);
					System.out.println("after header update");
//					List<String> olderWOCommon = new LinkedList<String>(); 
					List<String> olderWOCommon = new ArrayList<String>(inputBean.getOlderRollNoList());
					System.out.println("aftere new amendments ");
					olderWOCommon.removeAll(inputBean.getRollNoList());
					for(String oldRNo:olderWOCommon){
						System.out.println("old roll no :"+oldRNo);
					}
					List<String> newerWOCommon = new ArrayList<String>(inputBean.getRollNoList());
					newerWOCommon.removeAll(inputBean.getOlderRollNoList());
					for(String newRNo:newerWOCommon){
						System.out.println("new roll no :"+newRNo);
					}
					List<String> diff1 = diff;
					java.util.Iterator<String> iOld = olderWOCommon.iterator();
					java.util.Iterator<String> iNew = newerWOCommon.iterator();
					if(diff.size()>0){					
						do{
							if(olderWOCommon.size()==0){
								if(newerWOCommon.size()>0){
									while(iNew.hasNext()){
										inputBean.setRollNumber(iNew.next());
										getSqlMapClientTemplate().insert("ResultVerification.insertResultVerDetail",inputBean);
										iNew.remove();
										System.out.println("new roll no after add size "+newerWOCommon.size());
									}
									/*for (String rollNo:newerWOCommon) {
										inputBean.setRollNumber(rollNo);
										getSqlMapClientTemplate().insert("ResultVerification.insertResultVerDetail",inputBean);
										newerWOCommon.remove(rollNo);
										System.out.println("new roll no after add size "+newerWOCommon.size());
									}*/
									//all new elemnt insert query
								}
							}
							else{
								if(newerWOCommon.size()==0){
									while(iOld.hasNext()){
										inputBean.setRollNumber(iOld.next());
										getSqlMapClientTemplate().delete("ResultVerification.deleteResultVerDetail",inputBean);
										iOld.remove();
										System.out.println("new roll no after delete size "+newerWOCommon.size());
									}
									/*for (String rollNo:olderWOCommon) {
										inputBean.setRollNumber(rollNo);
										getSqlMapClientTemplate().delete("ResultVerification.deleteResultVerDetail",inputBean);
										olderWOCommon.remove(rollNo);
									}*/
									//all old to delete
								}
								else{
						outer:		while(iOld.hasNext()){
										while(iNew.hasNext()){
											//run update query;
											inputBean.setRollNumber(iNew.next());
											inputBean.setExtra(iOld.next());
											getSqlMapClientTemplate().update("ResultVerification.updateResultVerDetail",inputBean);
											iNew.remove();
											iOld.remove();
											continue outer;
//											System.out.println("new roll no after delete size "+newerWOCommon.size());
										}
									}
					/*outer:			for (String rollNo:olderWOCommon) { 
										for (String newrollNo:newerWOCommon) {
											//run update query;
											inputBean.setRollNumber(newrollNo);
											inputBean.setExtra(rollNo);
											getSqlMapClientTemplate().update("ResultVerification.updateResultVerDetail",inputBean);
											newerWOCommon.remove(newrollNo);
											olderWOCommon.remove(rollNo);
											continue outer;
										}
									}*/
									//update;
								}
							}
							System.out.println("after do content");
							diff1 = ListUtils.subtract(olderWOCommon,newerWOCommon);
							System.out.println("diff1 is "+diff1.size());
						}while(diff1.size()>0);
						System.out.println(" changes in the roll no so update in detail is required");
					}												
					System.out.println("after detail update");						
					status = "true";
					return status;
				} catch (Exception ex) {
					loggerObject.info("connection rollback..." + ex);
					tStatus.rollbackToSavepoint(savepoint);
					loggerObject.error(ex.getMessage());
					ex.printStackTrace();
					return status;
				}
			}
		});
		return s;
	}
	
	public List<ResultVerificationBean> getRequestType(ResultVerificationBean resultVerificationBean) {
		List<ResultVerificationBean> requestTypeList = new ArrayList<ResultVerificationBean>();
		try{
			requestTypeList = getSqlMapClientTemplate().queryForList("ResultVerification.getRequestType", resultVerificationBean);
		} catch(Exception e){
			System.out.println("inside get request type error");
			e.printStackTrace();
		}
		return requestTypeList;
	}
	
	/**
	 * The method gets the details of the students
	 * and prints verification report
	 * @param infoGetter obejct of the reference bean
	 * @return list 
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getStudents4Verification(DegreeListInfoGetter infoGetter) {		
		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();		
		try {
			resultList = getSqlMapClient().queryForList("degreeList.getStudents4Verification",infoGetter);
		} catch (Exception e) {
			loggerObject.error("Exception in getStudents4Verification"+e);
		}				
		return resultList;
	}
}
