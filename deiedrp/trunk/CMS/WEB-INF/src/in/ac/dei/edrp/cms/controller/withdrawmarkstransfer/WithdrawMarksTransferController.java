package in.ac.dei.edrp.cms.controller.withdrawmarkstransfer;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.withdrawmarkstransfer.WithdrawMarksTransferDao;
import in.ac.dei.edrp.cms.domain.withdrawmarkstransfer.WithdrawMarksTransferGetter;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class WithdrawMarksTransferController extends MultiActionController  {
	WithdrawMarksTransferDao withdrawMarksTransferDao;
	
	private static Logger logObj = Logger.getLogger(WithdrawMarksTransferController.class);
	
	public void setWithdrawMarksTransferDao(WithdrawMarksTransferDao withdrawMarksTransferDao) {
        this.withdrawMarksTransferDao = withdrawMarksTransferDao;
    }
	
	public ModelAndView getStudentDetails(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
			HttpSession session = request.getSession(true);
			WithdrawMarksTransferGetter input = new WithdrawMarksTransferGetter();
			input.setUniversityId((String)session.getAttribute("universityId"));
			input.setRollNumber(request.getParameter("rollNumber"));
        	List<WithdrawMarksTransferGetter> list = withdrawMarksTransferDao.getDetails(input);
        	return new ModelAndView("withdrawMarksTransfer/StudentDetails", "result", list);
    }
	
	public ModelAndView getRegisteringSession(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
			WithdrawMarksTransferGetter input = new WithdrawMarksTransferGetter();
			input.setEntityId(request.getParameter("entityId"));
			input.setProgramCourseKey(request.getParameter("programCourseKey"));
        	List<WithdrawMarksTransferGetter> list = withdrawMarksTransferDao.getSession(input);
        	return new ModelAndView("withdrawMarksTransfer/Dates", "result", list);
    }
	
	public ModelAndView getRegDates(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
		
		    HttpSession session = request.getSession(true);
			WithdrawMarksTransferGetter input = new WithdrawMarksTransferGetter();
			input.setUniversityId((String)session.getAttribute("universityId"));
			input.setEntityId(request.getParameter("entityId"));
			input.setProgramId(request.getParameter("programId"));
			input.setBranchId(request.getParameter("branchId"));
			input.setSpecializationId(request.getParameter("specializationId"));
			input.setSemesterCode(request.getParameter("semesterCode"));
			System.out.println("Inside date Control");
        	List<WithdrawMarksTransferGetter> list = withdrawMarksTransferDao.getRegistrationDates(input);
        	return new ModelAndView("withdrawMarksTransfer/Dates", "result", list);
    }
	
	public ModelAndView enableRegistration(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
		
			HttpSession session = request.getSession(true);
			WithdrawMarksTransferGetter input = new WithdrawMarksTransferGetter();
			input.setEntityId(request.getParameter("entityId"));
			input.setProgramId(request.getParameter("programId"));
			input.setBranchId(request.getParameter("branchId"));
			input.setSpecializationId(request.getParameter("specializationId"));
			input.setSemesterCode(request.getParameter("semesterCode"));
			input.setSessionStartDate(request.getParameter("sessionStartDate"));
			input.setSessionEndDate(request.getParameter("sessionEndDate"));
			input.setRollNumber(request.getParameter("rollNumber"));
			input.setProgramCourseKey(request.getParameter("programCourseKey"));
			input.setSemesterStartDate(request.getParameter("semesterStartDate"));
			input.setSemesterEndDate(request.getParameter("semesterEndDate"));
			input.setAttemptNumber(request.getParameter("attemptNo"));
			input.setCreatorId((String)session.getAttribute("userId"));
			System.out.println(input.getEntityId()
								+ " " + input.getProgramId()
								+ " " +	input.getBranchId()
								+ " " +	input.getSpecializationId()
								+ " " +	input.getSemesterCode()
								+ " " +	input.getSessionStartDate()
								+ " " +	input.getSessionEndDate()
								+ " " +	input.getRollNumber()
								+ " " +	input.getProgramCourseKey()
								+ " " +	input.getSemesterStartDate()
								+ " " +	input.getSemesterEndDate()
								+ " " +	input.getAttemptNumber()
								+ " " +	input.getCreatorId()
								);
        	String status = "";
        	try{
        		status = withdrawMarksTransferDao.enableRegistration(input);
        	}catch(Exception ex){
        		return new ModelAndView("RegistrationForm/RegisterStudent","result", ex.getMessage());
        	}
        	return new ModelAndView("RegistrationForm/RegisterStudent","result", status);
    }
}
