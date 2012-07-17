package in.ac.dei.edrp.cms.controller.studentregistration;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.cms.dao.studentregistration.TempCoursePdfDao;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class TempCoursePdfController extends MultiActionController{
	
	private TempCoursePdfDao tempCoursePdfDao;
	

	public void setTempCoursePdfDao(TempCoursePdfDao tempCoursePdfDao) {
		this.tempCoursePdfDao = tempCoursePdfDao;
	}


	public ModelAndView directToExtendedPdf(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		String userId = (String)session.getAttribute("userId");
		String entityId = request.getParameter("entityId");
		String programId = request.getParameter("programId");
		String branchId =  request.getParameter("branchId");
		String specializationId = request.getParameter("specializationId");
		String semesterStartDate = request.getParameter("semesterStartDate");
		String semesterEndDate = request.getParameter("semesterEndDate");
//		String semesterId = request.getParameter("semesterId");
		String semesterId = request.getParameter("semesterCode");
		String sequenceNumber = request.getParameter("semesterSequence");
		StudentInfoGetter studentInfoGetter = new StudentInfoGetter();
		studentInfoGetter.setUniversityId(universityId);
		studentInfoGetter.setUserId(userId);
		studentInfoGetter.setEntityId(entityId);
		studentInfoGetter.setProgramId(programId);
		studentInfoGetter.setBranchId(branchId);
		studentInfoGetter.setSemesterCode(semesterId);
		studentInfoGetter.setSemesterStartDate(semesterStartDate);
		studentInfoGetter.setSemesterEndDate(semesterEndDate);
		studentInfoGetter.setSpecializationId(specializationId);
		studentInfoGetter.setSequenceNumber(sequenceNumber);
	
		List<StudentInfoGetter> checkList = new ArrayList<StudentInfoGetter>();	
		try{
		checkList = tempCoursePdfDao.getPersonalInfoForExtendedList(studentInfoGetter);
		System.out.println("check list size :"+checkList.size());		
		}catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", "False-Some error in getting records from the database");
		}
		int count=checkList.size();
		if(count==0){
			String message = "true-No Student with Registration Information";
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
//			return new ModelAndView("systemtabletwo/countInfo", "count", count);
		}
		else
			try{
				return new ModelAndView("studentCheckList","checkList",checkList);	
			}catch(Exception e){
				System.out.println("inside the exception "+e);
				return new ModelAndView("activitymaster/SubmitSuccesful", "message", "true");
			}						
	}
	
	public ModelAndView directPdf(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		String userId = (String)session.getAttribute("userId");
		String entityId = request.getParameter("entityId");
		String programId = request.getParameter("programId");
		String branchId =  request.getParameter("branchId");
		String specializationId = request.getParameter("specializationId");
		String semesterStartDate = request.getParameter("semesterStartDate");
		String semesterEndDate = request.getParameter("semesterEndDate");
//		String semesterId = request.getParameter("semesterId");
		String semesterId = request.getParameter("semesterCode");
		String sequenceNumber = request.getParameter("semesterSequence");
		StudentInfoGetter studentInfoGetter = new StudentInfoGetter();
		studentInfoGetter.setUniversityId(universityId);
		studentInfoGetter.setUserId(userId);
		studentInfoGetter.setEntityId(entityId);
		studentInfoGetter.setProgramId(programId);
		studentInfoGetter.setBranchId(branchId);
		studentInfoGetter.setSemesterCode(semesterId);
		studentInfoGetter.setSemesterStartDate(semesterStartDate);
		studentInfoGetter.setSemesterEndDate(semesterEndDate);
		studentInfoGetter.setSpecializationId(specializationId);
		studentInfoGetter.setSequenceNumber(sequenceNumber);
	
		List<StudentInfoGetter> checkList = new ArrayList<StudentInfoGetter>();		
		
		checkList = tempCoursePdfDao.getPersonalInfoForMainList(studentInfoGetter);
		int count=checkList.size();
		if(count==0){
			String message = "true-No Student with Registration Information";
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
//			return new ModelAndView("systemtabletwo/countInfo", "count", count);
		}
		else
			try{
				return new ModelAndView("finalStudentCheckList","checkList",checkList);	
			}catch(Exception e){
				System.out.println("inside the exception "+e);
				return new ModelAndView("activitymaster/SubmitSuccesful", "message", "true");
			}								
		}
	}
