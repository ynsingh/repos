/**
 * @(#) BuildNextSessionController.java
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
package in.ac.dei.edrp.cms.controller.buildnextsession;

import in.ac.dei.edrp.cms.dao.buildnextsession.BuildNextSessionDao;
import in.ac.dei.edrp.cms.domain.buildnextsession.BuildNextSession;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving
 * the Build Next Session information 
 * @author Devendra Singhal
 * @date Nov 17 2011
 * @version 1.0
 */
public class BuildNextSessionController extends MultiActionController {
	private BuildNextSessionDao buildNextSessionDao;
	
	/**
     * The setter method of the interface associated
     * with this controller
     * @param buildActivityMaster
     */
	
	public void setBuildSessionDao(BuildNextSessionDao buildNextSessionDao) {
		this.buildNextSessionDao = buildNextSessionDao;
	}
	/**
     * Method to get the university session start date ,end date from database
     * @param request
     * @param response
     * @throws Exception
     * @return Model and View containing university session detail
     */
	public ModelAndView getCurrentSession(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		
		BuildNextSession buildNextSessionObj = new BuildNextSession();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		buildNextSessionObj.setUniversityId(universityId);
		List<BuildNextSession> sessionDetails = buildNextSessionDao.getCurrentSessionDetails(buildNextSessionObj);
		return new ModelAndView("buildnextsession/SessionDetails","sessionDetails", sessionDetails);
	}
	
	/**
     * Method to build the next session
     * @param request
     * @param response
     * @throws Exception
     * @return Model and View containing message
     */
	public ModelAndView buildNextSession(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		
		BuildNextSession buildNextSessionObj = new BuildNextSession();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		buildNextSessionObj.setUniversityId(universityId);
		buildNextSessionObj.setCreatorId((String)session.getAttribute("userId"));
		System.out.println("inside build next session controller");
		String buildMsg = buildNextSessionDao.buildNextSession(buildNextSessionObj);
		System.out.println("inside build next session controller message is "+buildMsg);
		return new ModelAndView("buildactivitymaster/Result","message", buildMsg);
	}
	
}
