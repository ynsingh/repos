/**
 * @(#) RegistrationActivityController.java
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
package in.ac.dei.edrp.cms.controller.registration.prestagingcontroller;


import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.dao.registration.prestagingdao.ReadyPreStagingDAO;
import in.ac.dei.edrp.cms.daoimpl.activitymaster.StartActivityDaoImpl;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ActivityMasterBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RegistrationActivityController extends MultiActionController {

	private ReadyPreStagingDAO readyPreStagingDAO;

	public static final Logger logger = Logger
			.getLogger(RegistrationActivityController.class);

	public void setReadyPreStagingDAO(ReadyPreStagingDAO readyPreStagingDAO) {
		System.out.println("Coming here");
		this.readyPreStagingDAO = readyPreStagingDAO;
	}

	public ModelAndView getProgramActivity(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Coming inside getActivity in RegistrationActivity");
		HttpSession session = request.getSession(true);

		String universityId = (String) session.getAttribute("universityId");
		String userId = (String) session.getAttribute("userId");
		// System.out.println("Coming in process");
		ActivityMasterBean activityMasterBean = new ActivityMasterBean();
		activityMasterBean.setUniversityId(universityId);

		// Since code for process master is attached, so process id will be in
		// list
		System.out.println("Coming here before list");
		List<ActivityMasterBean> activityList = readyPreStagingDAO
				.getAllActivities(activityMasterBean);
		System.out.println("Coming here after activityList");
		// for(StartActivityBean sta:processList){
		// System.out.println("Coming here"+sta.getComponentDescription());
		// }
		System.out.println("Coming inside 123 activity" + activityList);
		return new ModelAndView("prestaging/ProgramActivity", "activityList",
				activityList);
	}

	public ModelAndView getSemesterDate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);

		String universityId = (String) session.getAttribute("universityId");
		String userId = (String) session.getAttribute("userId");
		System.out.println("Coming in getSemesterDate");
		ProgressCardInfo progressCardInfo = new ProgressCardInfo();
		progressCardInfo.setUniversityCode(universityId);
		progressCardInfo.setProgramId((String) request
				.getParameter("programId"));
		progressCardInfo.setSemesterId((String) request
				.getParameter("semesterCode"));
		progressCardInfo.setEntityId((String) request
				.getParameter("entityId"));

		// progressCardInfo.setSessionStartDate("2011-07-01");
		// progressCardInfo.setSessionEndDate("2012-06-30");

		// Since code for process master is attached, so process id will be in
		// list
		ProgressCardInfo semesterDate = readyPreStagingDAO
				.getSemesterDates(progressCardInfo);
		// for(StartActivityBean sta:processList){
		// System.out.println("Coming here"+sta.getComponentDescription());
		// }
		System.out.println("Coming inside semester dates" + semesterDate);
		return new ModelAndView("reportgeneration/semesterDate",
				"semesterDate", semesterDate);
	}

	public ModelAndView startActivity(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);

		String universityId = (String) session.getAttribute("universityId");
		String userId = (String) session.getAttribute("userId");
		System.out.println("Coming in process");
		String entityId = request.getParameter("entityId");
		String programId = request.getParameter("programId");
		String semesterCode = request.getParameter("semesterCode");
		String semesterStartDate = request.getParameter("semesterStartDate");
		String semesterEndDate = request.getParameter("semesterEndDate");
		String activityId = request.getParameter("activityId");

		System.out.println("Coming inside start activity" + entityId
				+ programId + semesterCode + semesterStartDate
				+ semesterEndDate + activityId);
		ActivityMasterBean activityMasterBean = new ActivityMasterBean();
		activityMasterBean.setUniversityId(universityId);
		activityMasterBean.setEntityId(entityId);
		activityMasterBean.setProgramId(programId);
		activityMasterBean.setSemesterCode(semesterCode);
		activityMasterBean.setSemesterStartDate(semesterStartDate);
		activityMasterBean.setSemesterEndDate(semesterEndDate);
		activityMasterBean.setUserId(userId);
		activityMasterBean.setActivityId(activityId);
		activityMasterBean.setBranchId("ALL");//it is required because batch_process_logs table's branch_id field is required field.
		activityMasterBean.setSpecializationId("ALL");//it is required because batch_process_logs table's specialization_code field is required field.
		CountProcessRecorList countList = new CountProcessRecorList(0, 0, 0, 0,
				false,new ArrayList<List<UnProcessedStduent>>());

		ActivityMasterBean bean = new ActivityMasterBean();

		try {

			if (activityId
					.equalsIgnoreCase(CRConstant.PRESTAGING_READY_PROCESS)) {
				countList = readyPreStagingDAO
						.makePreStageReady(activityMasterBean);
			}

			if (activityId
					.equalsIgnoreCase(CRConstant.STAGING_TRANSFER_PROCESS)) {

				bean = readyPreStagingDAO.getstatusforCTT(activityMasterBean);

				if ((bean == null) || (!bean.getStatus().equalsIgnoreCase("P"))) {

					bean.setProcessName("cannotrun");

				} else {

					countList = readyPreStagingDAO
							.transferRDYinST(activityMasterBean);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if((activityId
					.equalsIgnoreCase(CRConstant.STAGING_TRANSFER_PROCESS))){
				if ((bean == null) || (!bean.getStatus().equalsIgnoreCase("P"))) {

					return new ModelAndView(
							"preProcessChecks/preProcessResultlist",
							"resultObject", "failure");
				} 	
			}
				countList = new CountProcessRecorList(countList
						.getTotalRecords(), countList.getCorrectProcessed(),
						countList.getRejectedProcessed(), countList
								.getInError(), countList.isActivityCompleted(),
						countList.getStudentList());

//			}
		}
		return new ModelAndView("activitymaster/CountProcessedRecord",
				"countList", countList);
	}

}
