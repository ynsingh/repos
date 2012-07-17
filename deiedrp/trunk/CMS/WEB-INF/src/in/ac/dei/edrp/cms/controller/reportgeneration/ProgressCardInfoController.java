package in.ac.dei.edrp.cms.controller.reportgeneration;

import in.ac.dei.edrp.cms.dao.reportgeneration.ProgressCardInfoDao;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ProgressCardInfoController extends MultiActionController {
	
	private ProgressCardInfoDao progressCardInfoDao;

	public void setProgressCardInfoDao(ProgressCardInfoDao progressCardInfoDao) {
		this.progressCardInfoDao = progressCardInfoDao;
	}
	
	public ModelAndView returnProgramInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		ProgressCardInfo progressCardInfo = new ProgressCardInfo();
		String entityId = request.getParameter("entityId");
		progressCardInfo.setEntityId(entityId);
		
		List<ProgressCardInfo> programList = progressCardInfoDao.programList(progressCardInfo);
			
		return new ModelAndView("reportgeneration/programList","programList",programList);		
	}
	
	public ModelAndView returnEntityInfo(HttpServletRequest request,HttpServletResponse response)
	{
		ProgressCardInfo progressCardInfo = new ProgressCardInfo();
		HttpSession session = request.getSession(true);
		String universityCode = (String) session.getAttribute("universityId");
		if (universityCode == null) {
			return new ModelAndView("general/SessionInactive",	"sessionInactive", true);
		}
	
		progressCardInfo.setUniversityCode(universityCode);
		List<ProgressCardInfo> entityList = progressCardInfoDao.entityList(progressCardInfo);
		return new ModelAndView("reportgeneration/entityList","entityList",entityList);
	}

	public ModelAndView returnSemesterList(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		ProgressCardInfo progressCardInfo = new ProgressCardInfo();		
		String programId = request.getParameter("programId");
		System.out.println(programId);
		progressCardInfo.setProgramId(programId);
		progressCardInfo.setUniversityCode(universityId);
		List<ProgressCardInfo> semesterList = progressCardInfoDao.semesterList(progressCardInfo);
		return new ModelAndView("reportgeneration/semesterList","semesterList",semesterList);
	}
	public ModelAndView returnSemesterDate(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		ProgressCardInfo progressCardInfo = new ProgressCardInfo();
		String programId = request.getParameter("programId");
		String semesterId = request.getParameter("semesterId");
		String entityId = request.getParameter("entityId");
		String sessionStartDate = request.getParameter("sessionStartDate");
		String sessionEndDate = request.getParameter("sessionEndDate");
		System.out.println(programId + " "+semesterId +" "+entityId + " "+sessionEndDate +" "+sessionStartDate);
		progressCardInfo.setProgramId(programId);
		progressCardInfo.setEntityId(entityId);
		progressCardInfo.setSessionEndDate(sessionEndDate);
		progressCardInfo.setSessionStartDate(sessionStartDate);
		progressCardInfo.setSemesterId(semesterId);
		progressCardInfo.setUniversityCode(universityId);
		progressCardInfo.setBranchId(request.getParameter("branchId"));
		progressCardInfo.setSpecializationId(request.getParameter("specializationId"));
		ProgressCardInfo semesterDate = progressCardInfoDao.semesterDate(progressCardInfo);
		return new ModelAndView("reportgeneration/semesterDate","semesterDate",semesterDate);
	}
}
