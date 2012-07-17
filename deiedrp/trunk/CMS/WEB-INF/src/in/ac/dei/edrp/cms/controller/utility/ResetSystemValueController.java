/**
 * @(#) ResetSystemValueController.java
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
package in.ac.dei.edrp.cms.controller.utility;


import in.ac.dei.edrp.cms.domain.utility.SystemValue;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


import in.ac.dei.edrp.cms.dao.utility.ResetSystemValueService;

/**
 * This controller is designed for setting & retrieving
 * the system value information 
 * @author Devendra Singhal
 * @date Nov 22 2011
 * @version 1.0
 */
public class ResetSystemValueController extends MultiActionController {
	/**
     * Create Variable systemValueService of interface ResetSystemValueService
     **/
	private ResetSystemValueService systemValueService;
	
	/**
     * The setter method of the interface associated
     * with this controller
     * @param ResetSystemValueService
     */
	public void setSystemValueService(ResetSystemValueService systemValueService) {
		this.systemValueService = systemValueService;
	}
	
	/**
     * Method to Reset the System Values.  
     * @param request
     * @param response
     * @return ModelAndView contain a String message
     */
	public ModelAndView systemValue(HttpServletRequest request,
			HttpServletResponse response) {
		
		/**create String Variable*/
		String message="";
		/**create systemValue object*/
		SystemValue systemValue = new SystemValue();
		/**create session object and set it to current session*/
		HttpSession session = request.getSession(true);
		
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		systemValue.setUniveristyCode(session.getAttribute("universityId").toString());
		systemValue.setCreatorId(session.getAttribute("userId").toString());
		String process=request.getParameter("process");
        if(process.equals("reset")){
        	 message = systemValueService.resetSystemValues(systemValue);
        }
        else if(process.equals("clearTemp")){
        	 message=systemValueService.clearTempTables(systemValue);
        }
        return new ModelAndView("buildactivitymaster/Result","message", message);
	
	}
}
