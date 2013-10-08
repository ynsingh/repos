package in.ac.dei.edrp.cms.controller.revertResultProcess;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import in.ac.dei.edrp.cms.dao.revertResultProcess.RevertResultProcessDao;
import in.ac.dei.edrp.cms.domain.revertResultProcess.RevertResultProcessBean;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class RevertResultProcessController extends MultiActionController{

	RevertResultProcessDao revertResultProcessDao;
	
	private static Logger logObj = Logger.getLogger(RevertResultProcessController.class);
	
	public void setRevertResultProcessDao(RevertResultProcessDao revertResultProcessDao) {
        this.revertResultProcessDao = revertResultProcessDao;
    }
	
	public ModelAndView getEntities(HttpServletRequest request, 
			HttpServletResponse response) throws Exception { 
            
		RevertResultProcessBean input = new RevertResultProcessBean();
		HttpSession session = request.getSession(true);
		input.setUniversityId(session.getAttribute("universityId").toString());
		List<RevertResultProcessBean> entityList = revertResultProcessDao.getEntity(input);
		return new ModelAndView("revertResultProcess/Details", "result", entityList);	
    }
	
	public ModelAndView getPrograms(HttpServletRequest request, 
			HttpServletResponse response) throws Exception { 
            
		RevertResultProcessBean input = new RevertResultProcessBean();
		input.setEntityId(request.getParameter("entityId"));
		List<RevertResultProcessBean> programList = revertResultProcessDao.getProgram(input);
		return new ModelAndView("revertResultProcess/Details", "result", programList);	
    }
	
	public ModelAndView getBranches(HttpServletRequest request, 
			HttpServletResponse response) throws Exception { 
            
		RevertResultProcessBean input = new RevertResultProcessBean();
		HttpSession session = request.getSession(true);
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setEntityId(request.getParameter("entityId"));
		input.setProgramId(request.getParameter("programId"));
		List<RevertResultProcessBean> branchList = revertResultProcessDao.getBranch(input);
		return new ModelAndView("revertResultProcess/Details", "result", branchList);	
    }
	
	public ModelAndView getSpecializations(HttpServletRequest request, 
			HttpServletResponse response) throws Exception { 
            
		RevertResultProcessBean input = new RevertResultProcessBean();
		HttpSession session = request.getSession(true);
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setEntityId(request.getParameter("entityId"));
		input.setProgramId(request.getParameter("programId"));
		List<RevertResultProcessBean> specializationList = revertResultProcessDao.getSpecialization(input);
		return new ModelAndView("revertResultProcess/Details", "result", specializationList);	
    }
	
	public ModelAndView getSemesters(HttpServletRequest request, 
			HttpServletResponse response) throws Exception { 
            
		RevertResultProcessBean input = new RevertResultProcessBean();
		HttpSession session = request.getSession(true);
		input.setUniversityId(session.getAttribute("universityId").toString());
		List<RevertResultProcessBean> semesterList = revertResultProcessDao.getSemester(input);
		return new ModelAndView("revertResultProcess/Details", "result", semesterList);	
    }
	
	public ModelAndView revertProcess(HttpServletRequest request, 
			HttpServletResponse response) throws Exception { 
            
		RevertResultProcessBean input = new RevertResultProcessBean();
		HttpSession session = request.getSession(true);
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setSessionStartDate(session.getAttribute("startDate").toString());
		input.setSessionEndDate(session.getAttribute("endDate").toString());
		input.setEntityId(request.getParameter("entityId"));
		input.setProgramId(request.getParameter("programId"));
		input.setBranchId(request.getParameter("branchId"));
		input.setSpecializationId(request.getParameter("specializationId"));
		input.setSemesterId(request.getParameter("semesterId"));
		
		String message = revertResultProcessDao.revertProcess(input);
		return new ModelAndView("general/MyException", "exception", message);	
    }
}