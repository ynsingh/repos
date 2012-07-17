/*
 * @(#) ConsolidatedChartService.java
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
package in.ac.dei.edrp.cms.dao.consolidatedchart;

import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ResultStatisticsInfo;
import in.ac.dei.edrp.cms.domain.coursemaster.CourseMasterBean;

import java.util.List;

/**
 * The client interface for Consolidated chart and result statistics.
 * 
 * @version 1.0 8 September 2011
 * @author MOHD AMIR
 */
public interface ConsolidatedChartService {
	/** getting entity list from Database */
	List<ConsolidatedChartBean> getEntityList(CourseMasterBean courseMasterBean);

	/** getting semester and semester sequence number from Database */
	List<ConsolidatedChartBean> getSemesterAndSeqNo(
			ConsolidatedChartBean chartBean);

	/** getting consolidated chart data from Database */
	List<ConsolidatedChartBean> getChartData(ConsolidatedChartBean chartBean);

	/** getting previous semester marks percentage from Database */
	List<ConsolidatedChartBean> getPreviousSemesterPercentage(
			ConsolidatedChartBean chartBean);

	/** getting program branch specialization list for an entity from Database */
	List<ResultStatisticsInfo> getPBSDetails(
			ResultStatisticsInfo resultStatisticsInfo);

	/** getting student count from Database */
	String getStudentCount(ResultStatisticsInfo resultStatisticsInfo);

	/** getting student count by division from Database */
	String getStudentCountByDivision(ResultStatisticsInfo resultStatisticsInfo);
	
	/** getting student count by division from Database */
	List<ResultStatisticsInfo> getEntityLevelList(ResultStatisticsInfo resultStatisticsInfo);

	/** getting student count by gender and category from Database */
	String getStudentCountByCategoryAndGender(ResultStatisticsInfo resultStatisticsInfo);
}