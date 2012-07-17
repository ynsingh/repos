/**
 * @(#) courseEvaluationCompnent.java
 * Author :Arush Kumar
 * Date :21/3/2011
 * Version 1.0
 * 
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
package in.ac.dei.edrp.cms.controller.courseEvaluationComponent;

import in.ac.dei.edrp.cms.dao.courseEvaluationComponent.CecDAO;
import in.ac.dei.edrp.cms.domain.courseEvaluationComponent.Cec;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Course Evaluation Component
 * 
 * @version 1.0 7 MAY 2011
 * @author MOHD AMIR
 */
public class CecController extends MultiActionController {

	/** creating object of CecDAO for using its method **/
	private CecDAO cecDAO;

	/**
	 * this is setter method for interface
	 * 
	 * @param cecDAO
	 *            , Object of bean class CecDAO
	 */
	public void setCecDAO(CecDAO cecDAO) {
		this.cecDAO = cecDAO;
	}

	/**
	 * This method is used for getting program Course details
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing program Course details
	 */
	public ModelAndView getProgramCourse(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		Cec cec = new Cec();

		cec.setUniversityId(session.getAttribute("universityId").toString());

		List<Cec> pclist = cecDAO.getProgramCourse(cec);

		return new ModelAndView("courseEvaluationComponent/pclist", "pclist",
				pclist);
	}

	/**
	 * This method is used for getting course evaluation details
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing course evaluation details
	 */
	public ModelAndView getCecList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		Cec cec = new Cec();

		cec.setUniversityId(session.getAttribute("universityId").toString());
		cec.setCoursecode(request.getParameter("coursecode"));
		cec.setProgramid(request.getParameter("programid"));
		cec.setSemestercode(request.getParameter("semestercode"));

		List<Cec> ceclist = cecDAO.getCecList(cec);

		return new ModelAndView("courseEvaluationComponent/ceclist", "ceclist",
				ceclist);
	}

	/**
	 * This method is used for setting course evaluation details
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing insert flag
	 */
	public ModelAndView setCecDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		Cec cec = new Cec();

		cec.setEvaluationid(request.getParameter("evalid"));
		cec.setEvalgroupcode(request.getParameter("idgrp"));
		cec.setRule(request.getParameter("idrule"));
		cec.setDatetodisplay(request.getParameter("dspdateto"));
		cec.setDatefromdisplay(request.getParameter("dspdatefrom"));
		cec.setOrderinmarksheet(request.getParameter("ordmks"));
		cec.setMaximummark(request.getParameter("mxmmrk"));
		cec.setExamdate(request.getParameter("exmdat"));
		cec.setCreatorid(session.getAttribute("userId").toString());
		cec.setProgramid(request.getParameter("programid"));
		cec.setSemestercode(request.getParameter("semester"));
		cec.setCoursecode(request.getParameter("course"));
		cec.setUniversityId(session.getAttribute("universityId").toString());

		boolean bool = false;

		if (request.getParameter("action").equalsIgnoreCase("I")) {
			bool = cecDAO.insertCecDetail(cec);
		} else if (request.getParameter("action").equalsIgnoreCase("U")) {
			bool = cecDAO.updateCecDetails(cec);
		}

		return new ModelAndView("courseEvaluationComponent/result", "result",
				bool);
	}

	/**
	 * This method is used for getting evaluation ID
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing evaluation ID list
	 */
	public ModelAndView getEvaluationIds(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		Cec cec = new Cec();

		cec.setUniversityId(session.getAttribute("universityId").toString());

		List<Cec> evidlist = cecDAO.getEvaluationIds(cec);

		return new ModelAndView("courseEvaluationComponent/evidlist",
				"evidlist", evidlist);
	}

	/**
	 * This method is used for getting duplicate record count
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing duplicate record count
	 */
	public ModelAndView getDuplicateCount(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cec cec = new Cec();

		cec.setEvaluationid(request.getParameter("evalid"));
		cec.setEvalgroupcode(request.getParameter("idgrp"));
		cec.setProgramid(request.getParameter("programid"));
		cec.setCoursecode(request.getParameter("course"));

		List<Cec> countList = cecDAO.getDuplicateCount(cec);

		return new ModelAndView("systemtabletwo/countInfo", "count", countList
				.get(0).getCount());
	}

	public ModelAndView deleteCecDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Cec> cecList = new ArrayList<Cec>();

		StringTokenizer evalIds = new StringTokenizer(
				request.getParameter("evalid"), "|");
		StringTokenizer groupIds = new StringTokenizer(
				request.getParameter("idgrp"), "|");

		while (evalIds.hasMoreTokens()) {
			Cec cec = new Cec();
			cec.setEvaluationid(evalIds.nextToken());
			cec.setEvalgroupcode(groupIds.nextToken());
			cec.setProgramid(request.getParameter("programid"));
			cec.setCoursecode(request.getParameter("course"));

			cecList.add(cec);
		}

		boolean bool = cecDAO.deleteCecDetails(cecList);
		return new ModelAndView("courseEvaluationComponent/result", "result",
				bool);
	}
}