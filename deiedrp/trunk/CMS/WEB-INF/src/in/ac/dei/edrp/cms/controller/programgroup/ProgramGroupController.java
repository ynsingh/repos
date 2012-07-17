/**
 * @(#) ProgramGroupController.java
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

package in.ac.dei.edrp.cms.controller.programgroup;
import in.ac.dei.edrp.cms.dao.programgroup.ProgramGroupDAO;
import in.ac.dei.edrp.cms.domain.programgroup.ProgramGroup;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving
 * the ProgramGroup Controller information 
 * @author Ankit Jain
 * @date 26 April 2011
 * @version 1.0
 */
public class ProgramGroupController extends MultiActionController{

	private ProgramGroupDAO programGroupDAO;
	
	/**
	 * the setter method of the interface associated
     * with this controller
	 * @param programGroupDAO the programGroupDAO to set
	 */
	public void setProgramGroupDAO(ProgramGroupDAO programGroupDAO) {
		this.programGroupDAO = programGroupDAO;
	}
	
	
	/**
     * Method to get the Program Group Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getProgramGroupDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ProgramGroup programGroup=new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setSpecializationId(request.getParameter("specializationId"));
		programGroup.setSemesterCode(request.getParameter("semesterCode"));
		programGroup.setUniversityId(universityId);
		
		List<ProgramGroup> programGroupDetail = programGroupDAO.getProgramGroupDetails(programGroup);
		return new ModelAndView("programgroup/ProgramGroupDetail","programGroupDetail", programGroupDetail);
	}
	
	/**
     * Method to get the Program List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView programList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setUniversityId(universityId+"%");
		List<ProgramGroup> programNameList = programGroupDAO.getProgramList(programGroup);
		return new ModelAndView("associatecoursewithinstructor/ProgramList","programNameList", programNameList);
	}
	
	/**
     * Method to get the Branch List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView branchList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setUniversityId(universityId);
		List<ProgramGroup> branchList = programGroupDAO.getBranchList(programGroup);
		return new ModelAndView("associatecoursewithinstructor/branchList","branchList", branchList);
	}
	
	/**
     * Method to get the Specialization List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView specializationList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setUniversityId(universityId);
		List<ProgramGroup> specializationList = programGroupDAO.getSpecializationList(programGroup);
		return new ModelAndView("associatecoursewithinstructor/SpecializationList","specializationList", specializationList);
	}
	
	/**
     * Method to get the Semester List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView semesterList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setUniversityId(universityId);
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setSpecializationId(request.getParameter("specializationId"));
		List<ProgramGroup> semesterList = programGroupDAO.getSemesterList(programGroup);
		return new ModelAndView("associatecoursewithinstructor/SemesterList","semesterList", semesterList);
	}
	
	/**
     * Method to get the group List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView groupList(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setUniversityId(universityId);
		List<ProgramGroup> groupList = programGroupDAO.getGroup(programGroup);
		return new ModelAndView("programgroup/GroupList","groupList", groupList);
	}
	
	/**
     * Method to get the subgroup List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView subGroupList(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setUniversityId(universityId);
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setSpecializationId(request.getParameter("specializationId"));
		programGroup.setSemesterCode(request.getParameter("semesterCode"));
		programGroup.setGroupCode(request.getParameter("groupCode"));
		List<ProgramGroup> subGroupList = programGroupDAO.getSubGroup(programGroup);
		return new ModelAndView("programgroup/SubgroupList","subgroupList", subGroupList);
	}
	
	/**
     * this method will save the details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView saveDetail(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setSpecializationId(request.getParameter("specializationId"));
		programGroup.setSemesterCode(request.getParameter("semesterCode"));
		programGroup.setGroupCode(request.getParameter("groupCode"));
		programGroup.setGroupOrder(request.getParameter("groupOrder"));
		programGroup.setMinimumSelection(request.getParameter("minimumSelection"));
		programGroup.setMaximumSelection(request.getParameter("maximumSelection"));
		programGroup.setConditionalGroup(request.getParameter("conditionalGroup"));
		programGroup.setCreatorId(session.getAttribute("userId").toString());
		String subGroupTokens = request.getParameter("selectedSubGroupCode");
				
		String message = programGroupDAO.saveDetails(programGroup, subGroupTokens);
		return new ModelAndView("programgroup/Result","message", message);
	}	
	
	/**
     * this method will delete the details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView deleteDetail(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		String selectedRecordsTokens = request.getParameter("selectedRecords");
		String message = programGroupDAO.deleteDetails(programGroup, selectedRecordsTokens);
		return new ModelAndView("programgroup/Result","message", message);
	}
	
	/**
     * this method will fetch the linked group list
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView linkedGroupList(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setUniversityId(universityId);
		List<ProgramGroup> linkedGroup = programGroupDAO.linkedGroupDetails(programGroup);
		return new ModelAndView("programgroup/LinkedGroup","linkedGroup", linkedGroup);
	}
	
	/**
     * this method will save the linkedGroup.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView saveLinkedGroup(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		String selectedRecordsTokens = request.getParameter("selectedLinkedGroup");
//		String selectedSubGroupTokens= request.getParameter("selectedSubGroup");
		programGroup.setLinkedMaximumSelection(request.getParameter("linkedMaximum"));
		programGroup.setLinkedMinimumSelection(request.getParameter("linkedMinimum"));
//		String message = programGroupDAO.saveLinkedGroup(programGroup, selectedRecordsTokens, selectedSubGroupTokens);
		String message = programGroupDAO.saveLinkedGroup(programGroup, selectedRecordsTokens);
		return new ModelAndView("programgroup/Result","message", message);
	}
	
	/**
     * this method will update the details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView updateDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setProgramCourseKey(request.getParameter("programCourseKey"));
		programGroup.setGroupCode(request.getParameter("groupCode"));
		programGroup.setGroupOrder(request.getParameter("groupOrder"));
		programGroup.setMinimumSelection(request.getParameter("minimumSelection"));
		programGroup.setMaximumSelection(request.getParameter("maximumSelection"));
		programGroup.setConditionalGroup(request.getParameter("conditionalGroup"));
//		programGroup.setSubgroupCode(request.getParameter("subgroupCode"));
		programGroup.setModifierId(session.getAttribute("userId").toString());
		String message = programGroupDAO.updateDetails(programGroup);
		return new ModelAndView("programgroup/Result","message", message);
	}
	
	/**
     * this method will fetch existing group list.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView existingGroupList(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setProgramCourseKey(request.getParameter("programCourseKey"));
		programGroup.setUniversityId(universityId);
		List<ProgramGroup> groupList = programGroupDAO.getExistingGroup(programGroup);
		return new ModelAndView("programgroup/GroupList","groupList", groupList);
	}
	
	/**
     * this method will save group rule details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView saveProgramGroupRule(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		ProgramGroup programGroup= new ProgramGroup();
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setSpecializationId(request.getParameter("specializationId"));
		programGroup.setSemesterCode(request.getParameter("semesterCode"));
		programGroup.setGroupCode(request.getParameter("groupCode"));
		programGroup.setSubgroupCode(request.getParameter("subgroupCode"));
		programGroup.setGroupRule(request.getParameter("groupRule"));
		programGroup.setCreatorId(session.getAttribute("userId").toString());
		String message = programGroupDAO.saveProgramGroupRule(programGroup);
		return new ModelAndView("programgroup/Result","message", message);
	}
	
	/**
     * this method will fetch the group rule details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView groupRuleDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setUniversityId(universityId);
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setSpecializationId(request.getParameter("specializationId"));
		programGroup.setSemesterCode(request.getParameter("semesterCode"));
		List<ProgramGroup> groupRuleDetails = programGroupDAO.groupRuleDetails(programGroup);
		return new ModelAndView("programgroup/GroupRuleDetails","groupRuleDetails", groupRuleDetails);
	}
	
	/**
     * this method will delete group rule details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView deleteGroupRuleDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		String selectedRecordsTokens = request.getParameter("selectedRecords");
		String message = programGroupDAO.deleteGroupRuleDetails(programGroup, selectedRecordsTokens);
		return new ModelAndView("programgroup/Result","message", message);
	}
	
	/**
     * this method will update the group rule details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView updateProgramRuleDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setProgramCourseKey(request.getParameter("programCourseKey"));
		programGroup.setGroupCode(request.getParameter("groupCode"));
		programGroup.setSubgroupCode(request.getParameter("subgroupCode"));
		programGroup.setGroupRule(request.getParameter("groupRule"));
		programGroup.setModifierId(session.getAttribute("userId").toString());
		String message = programGroupDAO.updateProgramRuleDetails(programGroup);
		return new ModelAndView("programgroup/Result","message", message);
	}
	
	/**
     * this method will get the group rule.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getRule(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		ProgramGroup programGroup = new ProgramGroup();
		
		programGroup.setUniversityId(universityId);
		List<ProgramGroup> groupList = programGroupDAO.getRules(programGroup);
		return new ModelAndView("programgroup/GroupList","groupList", groupList);
	}
	
	/**
     * Method to get the subgroup List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView GetFirstGroup(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setUniversityId(universityId);
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setSpecializationId(request.getParameter("specializationId"));
		programGroup.setSemesterCode(request.getParameter("semesterCode"));
		
		List<ProgramGroup> subGroupList = programGroupDAO.getFirstGroup(programGroup);
		return new ModelAndView("programgroup/SubgroupList","subgroupList", subGroupList);
	}
	
	/**
     * Method to get the Group Order List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView groupOrder(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setSpecializationId(request.getParameter("specializationId"));
		programGroup.setSemesterCode(request.getParameter("semesterCode"));
		programGroup.setGroupCode(request.getParameter("groupCode"));
		String groupOrder = programGroupDAO.getGroupOrder(programGroup);
		return new ModelAndView("programgroup/Result","message", groupOrder);
	}
	
	public ModelAndView unlinkLinkedGroup(HttpServletRequest request,
		HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		String selectedRecordsTokens = request.getParameter("selectedRecords");
		String message = programGroupDAO.unLinkGroup(programGroup, selectedRecordsTokens);
		return new ModelAndView("programgroup/Result","message", message);
	}
	
	/**
     * Method to get the max conditional Group Order List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView conditionalGroupOrder(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProgramGroup programGroup= new ProgramGroup();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		programGroup.setProgramId(request.getParameter("programId"));
		programGroup.setBranchId(request.getParameter("branchId"));
		programGroup.setSpecializationId(request.getParameter("specializationId"));
		programGroup.setSemesterCode(request.getParameter("semesterCode"));
		programGroup.setConditionalGroup(request.getParameter("conditionalGroup"));
		
		String groupOrder = programGroupDAO.getMaxConditionalGroupOrder(programGroup);
		return new ModelAndView("programgroup/Result","message", groupOrder);
	}
}
