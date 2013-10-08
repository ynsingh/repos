/**
 * @(#) StagingTableReportDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.reportgeneration;

import in.ac.dei.edrp.cms.dao.reportgeneration.StagingTableReportDao;
import in.ac.dei.edrp.cms.domain.reportgeneration.StagingTableReportBean;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * This file consist of the methods used at the Staging Table Report Generation.
 * 
 * @author Nupur Dixit
 * @date 26 Sep 2012
 * @version 1.0
 */
@Component
@Qualifier("StagingTableReportImpl")
public class StagingTableReportDaoImpl extends SqlMapClientDaoSupport implements StagingTableReportDao {

	@Autowired
	TransactionTemplate transactionTemplate;
	
	public StagingTableReportDaoImpl(){}
	@Autowired
	public StagingTableReportDaoImpl(SqlMapClient sqlMapClient){		
		setSqlMapClient(sqlMapClient);
	}
	public StagingTableReportDaoImpl(SqlMapClient sqlMapClient,TransactionTemplate transactionTemplate){
		this.transactionTemplate=transactionTemplate;
		setSqlMapClient(sqlMapClient);
	}
	/*Create Logger Object for maintain log file.*/	
	private Logger loggerObject = Logger.getLogger(StagingTableReportDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<StagingTableReportBean> getCurrentSession(StagingTableReportBean stagingTableReportBean) {
		List<StagingTableReportBean> sessionDates = null;
		try {
			sessionDates = getSqlMapClientTemplate().queryForList("StagingTableReport.getCurrentSession",stagingTableReportBean);
			loggerObject.info("in getCurrentSession");
		} catch (Exception e) {
			loggerObject.error("in getCurrentSession" + e);
		}
		return sessionDates;
	}


	@SuppressWarnings("unchecked")
	public List<StagingTableReportBean> getStudentDetail(StagingTableReportBean stagingTableReportBean) {
		List<StagingTableReportBean> studentDetails = new ArrayList<StagingTableReportBean>();
		try {
			studentDetails = getSqlMapClientTemplate().queryForList("StagingTableReport.getStagingTableDetail",stagingTableReportBean);
			System.out.println("studentDetails size is: "+ studentDetails.size());
			loggerObject.info("in getStudentDetail");
		} catch (Exception e) {			
			System.out.println("in getStudentDetail" + e);
		}		
		return studentDetails;
	}
	
}
