/**
 * @(#) EodControlController.java
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

package in.ac.dei.edrp.cms.controller.eodcontrol;

import in.ac.dei.edrp.cms.dao.eodcontrol.EodControlDAO;
import in.ac.dei.edrp.cms.daoimpl.eodcontrol.EodControlDAOImpl;
import in.ac.dei.edrp.cms.domain.eodcontrol.EodControl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving
 * the activity master information 
 * @author Ankit Jain
 * @date 15 Jul 2011
 * @version 1.0
 */
public class EodControlController extends MultiActionController{

	private EodControlDAO eodControlDao;
	
	/**
     * The setter method of the interface associated
     * with this controller
     * @param activityMaster
     */
	public void setActivityMasterDAO(EodControlDAO eodControlDao) {
		this.eodControlDao = eodControlDao;
	}
	
	/**
     * Method for fetch the activity master Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getEodControlDetails(HttpServletRequest request, HttpServletResponse response)throws Exception {
		EodControl eodControlObject = new EodControl();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		eodControlObject.setUniversityId(universityId);
		List<EodControl> eodControlDetail = eodControlDao.getEodControlDetails(eodControlObject);
		if(eodControlDetail.size()==0){
			return new ModelAndView("activitymaster/InsertionInfo","message", "norecordFound");
		}
		else{
			return new ModelAndView("eodControl/EodControlDetail","eodControlDetail", eodControlDetail);
		}
		
	}
	
	
	/**
     * Method for fetch the activity master Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getStepFrequency(HttpServletRequest request, HttpServletResponse response)throws Exception {
		EodControl eodControlObject = new EodControl();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		eodControlObject.setUniversityId(universityId);		
		List<EodControl> stepFrequencyList = eodControlDao.getStepFrequency(eodControlObject);
		if(stepFrequencyList.size()==0){
			return new ModelAndView("activitymaster/InsertionInfo","message", "norecordFound");
		}
		else{
			return new ModelAndView("eodControl/StepFrequency","stepFrequencyList", stepFrequencyList);
		}
		
	}
	
	/**
     * Method for fetch the activity master Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getMethodsToRun(HttpServletRequest request, HttpServletResponse response)throws Exception {
		EodControl eodControlObject = new EodControl();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		eodControlObject.setUniversityId(universityId);		
		List<EodControl> MethodsList = eodControlDao.getMethodsToRun(eodControlObject);
		if(MethodsList.size()==0){
			return new ModelAndView("activitymaster/InsertionInfo","message", "norecordFound");
		}
		else{
			return new ModelAndView("eodControl/MethodToRun","MethodsList", MethodsList);
		}
		
	}
	
	/**
     * Method for save the activity master Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView saveDetails(HttpServletRequest request,	HttpServletResponse response)throws Exception {
		EodControl eodControlObject = new EodControl();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		eodControlObject.setPhase(request.getParameter("phase"));
		eodControlObject.setDependentPhase(request.getParameter("dependentPhase"));
		eodControlObject.setStep(request.getParameter("step"));
		eodControlObject.setStepFrequencyCode(request.getParameter("stepFrequencyCode"));
		eodControlObject.setPeriodActiveFrom(request.getParameter("periodActiveFrom"));
		eodControlObject.setPeriodActiveTo(request.getParameter("periodActiveTo"));
		eodControlObject.setMethodToRunCode(request.getParameter("methodToRunCode"));
		eodControlObject.setStatus("ACT");
		eodControlObject.setUserId(session.getAttribute("userId").toString());
		
		String temp=request.getParameter("info");
		if(temp.equals("week")){
			eodControlObject.setDay(request.getParameter("buildTime"));
			eodControlObject.setBuildTime(null);
			eodControlObject.setMmdd(null);
		}
		else if(temp.equals("month")){
			eodControlObject.setDay(null);
			eodControlObject.setBuildTime(request.getParameter("buildTime"));
			eodControlObject.setMmdd(null);
		}
		else if (temp.equals("mmdd")){
			eodControlObject.setDay(null);
			eodControlObject.setBuildTime(null);
			eodControlObject.setMmdd(request.getParameter("buildTime"));
		}
		String message=eodControlDao.saveEodControlDetails(eodControlObject);
		System.out.println("contoller "+message);
		return new ModelAndView("activitymaster/SubmitSuccesful","message", message);
	}
	
	/**
     * Method for Update the activity master Details.
     * @param request
     * @param response
     * @return
     */
	public ModelAndView updateEodControl(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		EodControl eodControlObject = new EodControl();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		eodControlObject.setUserId(session.getAttribute("userId").toString());
		eodControlObject.setPhase(request.getParameter("phase"));
		eodControlObject.setDependentPhase(request.getParameter("dependentPhase"));
		eodControlObject.setStep(request.getParameter("step"));
		eodControlObject.setStepFrequencyCode(request.getParameter("stepFrequencyCode"));
		eodControlObject.setPeriodActiveFrom(request.getParameter("periodActiveFrom"));
		eodControlObject.setPeriodActiveTo(request.getParameter("periodActiveTo"));
		eodControlObject.setMethodToRunCode(request.getParameter("methodToRunCode"));		
		eodControlObject.setStatus(request.getParameter("status"));
		eodControlObject.setDay(request.getParameter("buildDay"));
		eodControlObject.setFlag(request.getParameter("flag"));
		eodControlObject.setMmdd(request.getParameter("mmdd"));
		
		String message= eodControlDao.updateEodControl(eodControlObject);
		
		return new ModelAndView("activitymaster/SubmitSuccesful","message", message);
	}
	
	/**
     * Method for delete the activity master Details.
     * @param request
     * @param response
     * @return
     */
	public ModelAndView deleteEodControlDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		EodControl eodControlObject = new EodControl();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		String eodControlDataTokens = request.getParameter("recordArrayColl");
		String message = eodControlDao.deleteEodControlDetails(eodControlObject, eodControlDataTokens);
		return new ModelAndView("activitymaster/InsertionInfo","message", message);
	}
	
	
	/**
     * Method for delete the activity master Details.
     * @param request
     * @param response
     * @return
     */
	public ModelAndView ChangeEodControlStatus(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		String message;
		EodControl eodControlObject = new EodControl();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		String eodControlDataTokens = request.getParameter("recordArrayColl");
		message =eodControlDao.changeEodControlStatus(eodControlObject, eodControlDataTokens);
		
		return new ModelAndView("activitymaster/InsertionInfo","message", message);
	}
	
	/**
     * this method will called to build the EOD Master 
     * @param request
     * @param response1
     * @return
     */
	public ModelAndView buildEodMaster(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
//		if(universityId == null){
//			return new ModelAndView("general/SessionInactive","sessionInactive",true);
//		}
		
		EodControl eodMasterObject = new EodControl();
		System.out.println("  In Controller: "+request.getParameter("eodDate"));
		eodMasterObject.setEodDate(request.getParameter("eodDate"));
		eodMasterObject.setRemark(request.getParameter("methodRemark"));
		eodMasterObject.setUserId(session.getAttribute("userId").toString());
		
//		String eodDate=request.getParameter("eodDate");
		
//		Date eodDate1= new Date(eodDate);
		
//		Calendar cal = Calendar.getInstance();
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
//		System.out.println("XXXXXXXX "+cal.getTime());
//		System.out.println("Today's date is "+dateFormat.format(dateFormat.parse(eodDate)));
//	    cal.add(dateFormat.parse(eodDate).getDate(), -1);
//		System.out.println("Yesterday's date was "+dateFormat.format(cal.getTime()));  
//		eodMasterObject.setLastEodDate();
		
		String message=eodControlDao.buildEODMaster(eodMasterObject);
		System.out.println("Message : "+ message);
		return new ModelAndView("activitymaster/InsertionInfo","message", message);
	}
}
