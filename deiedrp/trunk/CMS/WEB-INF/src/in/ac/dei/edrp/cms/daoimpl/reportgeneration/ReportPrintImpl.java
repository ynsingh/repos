/**
 * @(#) SystemTableTwoController.java
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

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import in.ac.dei.edrp.cms.dao.reportgeneration.ReportPrintDao;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;
/**
 * This class is for implementing the method for inserting data in printing log
 * @author Ashutosh Pachauri
 *
 */
public class ReportPrintImpl extends SqlMapClientDaoSupport implements ReportPrintDao{
	/**
	 * Method for inserting the record in report_print_log
	 */
	public void progressCardPrint(ProgressCardInfo progressCardInfo) {
		
		ProgressCardInfo programCourseKeyObject = new ProgressCardInfo();
		ProgressCardInfo verifyInsert = new ProgressCardInfo();
		programCourseKeyObject =  (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.getProgramCourseKeyForStudent",progressCardInfo);
		progressCardInfo.setProgramCourseKey(programCourseKeyObject.getProgramCourseKey());		
		if(programCourseKeyObject.getProgramCourseKey() != null)
		{
			verifyInsert = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.verifyInsertInReportPrintLog",progressCardInfo);
			System.out.println("ashu "+verifyInsert.getProgramCourseKey());
			if(verifyInsert.getProgramCourseKey().length()==0)
			{									
			getSqlMapClientTemplate().insert("progressCard.insertInReportPrintLog",progressCardInfo);
			}		
		}		
	}
	
}
