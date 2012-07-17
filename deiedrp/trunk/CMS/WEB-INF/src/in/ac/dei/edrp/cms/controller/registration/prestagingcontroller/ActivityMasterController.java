
/**
 * @(#) ActivityMasterController.java
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

import in.ac.dei.edrp.cms.dao.registration.prestagingdao.ReadyPreStagingDAO;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ActivityMasterBean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@SuppressWarnings("deprecation")
public class ActivityMasterController extends SimpleFormController {

	private ReadyPreStagingDAO activityMasterService;

	public ActivityMasterController() {
		setCommandClass(ActivityMasterBean.class);
		setCommandName("activityMasterBean");
	}

	public void setActivityMasterService(
			ReadyPreStagingDAO activityMasterService) {
		this.activityMasterService = activityMasterService;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		// System.out.println("Coming here in Activity Master Controller");
		Map referenceData = new HashMap();
		// referenceData.put("processList",
		// activityMasterService.getAllProcess());
		// referenceData.put("activityList",
		// activityMasterService.getAllActivities("REG"));
		return referenceData;
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		System.out.println("inside model and view");
		ActivityMasterBean activityMasterBean = (ActivityMasterBean) command;
		System.out.println("inside model and view"
				+ activityMasterBean.getEntityId());
		if (activityMasterBean.getEntityId().equalsIgnoreCase("00010001")) {
			System.out.println("Running prestaging");
			activityMasterService.makePreStageReady(activityMasterBean);
		}
		return new ModelAndView("activityMaster", "activityMasterBean",
				activityMasterBean);
	}

}
