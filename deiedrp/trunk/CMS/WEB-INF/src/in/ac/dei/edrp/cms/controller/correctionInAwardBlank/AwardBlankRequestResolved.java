package in.ac.dei.edrp.cms.controller.correctionInAwardBlank;

import in.ac.dei.edrp.cms.dao.studentmarkssummary.StudentMarksSummaryDao;
import in.ac.dei.edrp.cms.domain.studentmarkssummary.StudentMarksSummaryBean;
import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.correctionInAwardBlank.AwardBlankRequestResolvedDao;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AwardBlankRequestResolved extends MultiActionController {
	
	
	/** creating object of interface*/
	private AwardBlankRequestResolvedDao awardBlankRequestResolvedDao;

	/**
	 * @param awardBlankRequestResolvedDao the awardBlankRequestResolvedDao to set
	 */
	public void setAwardBlankRequestResolvedDao(
			AwardBlankRequestResolvedDao awardBlankRequestResolvedDao) {
		this.awardBlankRequestResolvedDao = awardBlankRequestResolvedDao;
	}




	/**
	 * Method for closing issue of request submit by student for marks correction
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return Model View containing status of request	 
	 */
	public ModelAndView resolvedIssue(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentMarksSummaryBean input = new StudentMarksSummaryBean();
		input.setRollNumber(request.getParameter("rollNumber"));		
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setUserId(session.getAttribute("userName").toString());
		input.setSessionStartDate(session.getAttribute("startDate").toString());
		input.setSessionEndDate(session.getAttribute("endDate").toString());
		input.setSemesterStartDate(request.getParameter("semesterStartDate"));
		input.setSemesterEndDate(request.getParameter("semesterEndDate"));
		input.setEntityId(request.getParameter("entityId"));
		input.setCourseCode(request.getParameter("courseCode"));
		input.setEvaluationId(request.getParameter("evaluationId"));
		input.setMarks(request.getParameter("newMarks"));
		input.setStatus(request.getParameter("teacherRemark"));// teacher remark		
		
		String message=awardBlankRequestResolvedDao.solveRequestedIssue(input);
		return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
	}
	
	
	
	
	/**
	 * Method for getting request data of the student for marks correction
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return Model View containing status of request	 
	 */
	public ModelAndView getStudentRequestDetail(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentMarksSummaryBean input = new StudentMarksSummaryBean();		
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setSessionStartDate(session.getAttribute("startDate").toString());
		input.setSessionEndDate(session.getAttribute("endDate").toString());
		input.setSemesterStartDate(request.getParameter("semesterStartDate"));
		input.setSemesterEndDate(request.getParameter("semesterEndDate"));
		input.setEntityId(request.getParameter("entityId"));
		input.setCourseCode(request.getParameter("courseCode"));
		List<StudentMarksSummaryBean> detail = new ArrayList<StudentMarksSummaryBean>();
	
		detail=awardBlankRequestResolvedDao.getStudentRequestData(input);
		return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject",detail);
		}
		
		
			
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * @author ashish mohan
	 * @used for award Blank Collation
	 */
	
	////////////////////////////////////////////////////////////////////////////////////
	
	public ModelAndView getCourseList(HttpServletRequest request,
	        HttpServletResponse response) throws Exception { 
	        
	    	HttpSession session = request.getSession(true);
			String universityId =(String) session.getAttribute("universityId");
			if(universityId == null){
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}
	    	
			AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
 
	        inputObj.setCreatorId(session.getAttribute("userId").toString());
	        inputObj.setUniversityId(session.getAttribute("userId").toString().substring(1, 5));
	        inputObj.setDisplayType(request.getParameter("displayType"));

	        List<AwardSheetInfoGetter> courseList =  awardBlankRequestResolvedDao.getCourseList(inputObj);
	        return new ModelAndView("awardsheet/CourseList", "result", courseList);
	    }
	
	
		/**
     * Method for insert/update student marks
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView saveStudentMarks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
        HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		inputObj.setEntityId(request.getParameter("entityId"));
		inputObj.setUniversityId(session.getAttribute("universityId").toString());
        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setStartDate(request.getParameter("startDate"));
        inputObj.setEndDate(request.getParameter("endDate"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setCreatorId(session.getAttribute("userName").toString());
        
        StringTokenizer data = new StringTokenizer(request.getParameter("data"), ";");
        String statusValue = null;
        try {
        	statusValue = awardBlankRequestResolvedDao.saveStudentMarks(inputObj, data);
        } catch (MyException e) {
            return new ModelAndView("RegistrationForm/RegisterStudent","result", e.getMessage());
        }

        return new ModelAndView("RegistrationForm/RegisterStudent", "result", statusValue);
    }
    
    
    /**
     *  Method to get list of marks of students for given course
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getStudentMarks(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
        
        HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
        
		inputObj.setEntityId(request.getParameter("entityId"));
		inputObj.setUniversityId(session.getAttribute("universityId").toString());
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setBranchcode(request.getParameter("branchCode"));
        inputObj.setSpecializationCode(request.getParameter("specCode"));
        inputObj.setSystemCode(request.getParameter("semesterCode"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setStartdate(request.getParameter("startDate"));
        inputObj.setEndDate(request.getParameter("endDate"));
        inputObj.setSystemValue(request.getParameter("buttonPressed"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
             
        List<AwardSheetInfoGetter> marksList = awardBlankRequestResolvedDao.getStudentMarks(inputObj);

        return new ModelAndView("awardsheet/MarksList", "result", marksList);
    }



}
