package in.ac.dei.edrp.cms.controller.studentissue;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.studentissue.StudentIssueConnect;
import in.ac.dei.edrp.cms.domain.cgpadivision.CgpaDivisionInfoGetter;
import in.ac.dei.edrp.cms.domain.studentissue.StudentIssueInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class StudentIssueController extends MultiActionController {
	
	/**
	* this is Server side controller class for Managing Issues of students
	* @version 1.0 05 SEP 2011
	* @author ROHIT
	*/
	
	/** creating object of StudentIssue interface */
	private StudentIssueConnect studentIssueConnect;
	
	/** defining setter method for object of StudentIssue interface */
	public void setStudentIssueConnect(StudentIssueConnect studentIssueConnect) {
		
		this.studentIssueConnect = studentIssueConnect;
	}

	/**
	 * Method for fetching roll numbers
	 * @param request
	 * @param response
	 * @return ModelAndView containing list of roll numbers
	 * @throws Exception
	 */
	public ModelAndView getRollNo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		StudentIssueInfoGetter input = new StudentIssueInfoGetter();

		 HttpSession session = request.getSession(true);
		
	    	String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
		 input.setUniversityCode(session.getAttribute("universityId")+"%");
				

		List<StudentIssueInfoGetter> resultDetails = studentIssueConnect
				.getRollNo(input);
	
		return new ModelAndView("StudentIssue/RollList",
				"resultObject", resultDetails);
		}
	
	/**
	 * Method for getting Issue Details of an Student
	 * @param request
	 * @param response
	 * @return ModelAndView containing list of recrods having issue details
	 * @throws Exception
	 */
	public ModelAndView getIssueDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		StudentIssueInfoGetter input = new StudentIssueInfoGetter();

		 HttpSession session = request.getSession(true);
		 String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
		 input.setUniversityCode(session.getAttribute("universityId").toString());
		
		 input.setRollNo(request.getParameter("rollNo"));

		List<StudentIssueInfoGetter> resultDetails = studentIssueConnect
				.getIssueDetails(input);
	
		return new ModelAndView("StudentIssue/IssueDetails",
				"resultObject", resultDetails);
		}

	/**
	 * This method deletes the issue records from student_issue table
 	 * @param request
	 * @param response
	 * @return ModelAndView containing String of success or failure
	 */
//	public ModelAndView deleteIssues(HttpServletRequest request,
//		        HttpServletResponse response) throws Exception {
//		    	StudentIssueInfoGetter input=new StudentIssueInfoGetter();
//		    	
//		        input.setRollNo(request.getParameter("rollNo"));
//		       
//		        StringTokenizer items = new StringTokenizer(request.getParameter(
//		                    "issueId"), ",");
//		    
//		        String resultDeleteDetails = studentIssueConnect.deleteIssueRecords(input,
//		                items);
//
//		        return new ModelAndView("preProcessChecks/preProcessResultlist",
//		            "resultObject", resultDeleteDetails);
//		    }
	
	/**
	 * Method for fetching all penality codes
	 * @param request
	 * @param response
	 * @return ModelAndView containing List of Penality codes
	 * @throws Exception
	 */
	 public ModelAndView getPenalityCodes(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
		     HttpSession session = request.getSession(true);
		     String userId = (String) session.getAttribute("userId");
		    	if(userId == null)
		        {
		        return new ModelAndView("general/SessionInactive","sessionInactive",true);
		        } 
			 input.setUserId(session.getAttribute("userId").toString());
				        
	        List<StudentIssueInfoGetter> resultGetPenalityCodes = studentIssueConnect.getPenalityCodes(input.getUserId());

	        return new ModelAndView("StudentIssue/PenalityDetails",
	            "resultObject", resultGetPenalityCodes);
		    }
	 
	 /**
	  * Method for fetching course codes on which penality can be applied
	  * @param request
	  * @param response
	  * @return ModelAndView containing List of course codes
	  * @throws Exception
	  */
	 public ModelAndView getPenalityCourseCodes(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		
		 HttpSession session = request.getSession(true);
		 String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
		     input.setRollNo(request.getParameter("rollNo"));
		     input.setProgramCourseKey(request.getParameter("programCourseKey"));
		     input.setFlag(request.getParameter("flag"));
		     input.setProgramId(request.getParameter("programId"));
		     input.setBranchId(request.getParameter("branchId"));
		     input.setSpecializationId(request.getParameter("specializationId"));
		     input.setSemesterId(request.getParameter("semesterId"));
	        List<StudentIssueInfoGetter> resultGetPenalityCourseCodes = studentIssueConnect.resultGetPenalityCourseCodes(input);

	        return new ModelAndView("StudentIssue/PenalityCourseDetails",
	            "resultObject", resultGetPenalityCourseCodes);
		    }
	
	 /**
	  * Method for closing an issue
	  * @param request
	  * @param response
	  * @return ModelAndView containing String of sucess or failure
	  * @throws Exception
	  */
	 public ModelAndView updateIssues(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
		     HttpSession session = request.getSession(true);
		     String userId = (String) session.getAttribute("userId");
		    	if(userId == null)
		        {
		        return new ModelAndView("general/SessionInactive","sessionInactive",true);
		        } 
		     input.setUniversityCode(session.getAttribute("universityId").toString());
		     input.setUserId(session.getAttribute("userId").toString());
		     input.setEntityId(request.getParameter("entityId"));
		     input.setProgramId(request.getParameter("programId"));	  
		     input.setBranchId(request.getParameter("branchId"));   
		     input.setSpecializationId(request.getParameter("specializationId"));  
		     input.setProgramCourseKey(request.getParameter("programCourseKey"));
		     input.setSemesterId(request.getParameter("semesterId"));   
		     input.setSemesterStartDate(request.getParameter("semesterStartDate"));   
		     input.setSemesterEndDate(request.getParameter("semesterEndDate"));	
		     input.setIssueCode(request.getParameter("issueCode"));    	
		     input.setRollNo(request.getParameter("rollNo"));
		     input.setIssueId(request.getParameter("issueId"));
		     input.setPenalityId(request.getParameter("penalityId"));
		     input.setPenalityCourse1(request.getParameter("penalityCourse1"));
		     input.setPenalityCourse2(request.getParameter("penalityCourse2"));
		     input.setPenalityCourse3(request.getParameter("penalityCourse3"));
		     input.setPenalityCourse4(request.getParameter("penalityCourse4"));
		     input.setRemarks(request.getParameter("remarks"));
		    
	        
		     String updateResult = studentIssueConnect.updateIssue(input);

	        return new ModelAndView("preProcessChecks/preProcessResultlist",
	            "resultObject",updateResult);
		    }
	 
	 /**
	  * Method for fetching entity
	  * @param request
	  * @param response
	  * @return ModelAndView containing List of entity
	  * @throws Exception
	  */
	 public ModelAndView getEntity(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
	
		 HttpSession session = request.getSession(true);
		 String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
		     input.setRollNo(request.getParameter("rollNo"));
		     StudentIssueInfoGetter entity = (StudentIssueInfoGetter) studentIssueConnect.getEntity(input);
		     
	        return new ModelAndView("StudentIssue/EntityDetails",
	            "resultObject",entity);
		    }
	 
	 /**
	  * Method for fetching list of programs
	  * @param request
	  * @param response
	  * @return ModelAndView containing list of programs
	  * @throws Exception
	  */
	 public ModelAndView getPrograms(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		
		 HttpSession session = request.getSession(true);
		 String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
		     input.setRollNo(request.getParameter("rollNo"));
		     		     
	        List<StudentIssueInfoGetter> resultGetPrograms = studentIssueConnect.getPrograms(input);

	        return new ModelAndView("StudentIssue/ProgramList",
	            "resultObject", resultGetPrograms);
		    }
	 
	 /**
	  * Method for fetching branchs
	  * @param request
	  * @param response
	  * @return ModelAndView containing list of branchs
	  * @throws Exception
	  */
	 public ModelAndView getBranch(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
	
		 HttpSession session = request.getSession(true);
		 String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
		     input.setRollNo(request.getParameter("rollNo"));
		     input.setProgramId(request.getParameter("programId"));
		     		     
	        List<StudentIssueInfoGetter> resultGetBranchs = studentIssueConnect.getBranch(input);

	        return new ModelAndView("StudentIssue/BranchList",
	            "resultObject", resultGetBranchs);
		    }
	 
	 /**
	  * Method for fetching specializations
	  * @param request
	  * @param response
	  * @return ModelAndView containing list of specializations
	  * @throws Exception
	  */
	 public ModelAndView getSpecialization(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
	
		 HttpSession session = request.getSession(true);
		 String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
		     input.setRollNo(request.getParameter("rollNo"));
		     input.setProgramId(request.getParameter("programId"));
		     input.setBranchId(request.getParameter("branchId"));
		     		     
	        List<StudentIssueInfoGetter> resultGetSpecialization = studentIssueConnect.getSpecialization(input);

	        return new ModelAndView("StudentIssue/SpecializationList",
	            "resultObject", resultGetSpecialization);
		    }
	 
	 /**
	  * Method for fetching semesters
	  * @param request
	  * @param response
	  * @return ModelAndView containing list of semesters
	  * @throws Exception
	  */
	 public ModelAndView getSemester(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
	
		 HttpSession session = request.getSession(true);
		 String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
		     input.setRollNo(request.getParameter("rollNo"));
		     input.setProgramId(request.getParameter("programId"));
		     input.setBranchId(request.getParameter("branchId"));
		     input.setSpecializationId(request.getParameter("specializationId"));
		     		     
	        List<StudentIssueInfoGetter> resultGetSemesters = studentIssueConnect.getSemester(input);

	        return new ModelAndView("StudentIssue/SemesterList",
	            "resultObject", resultGetSemesters);
		    }
	 
	 /**
	  * Method for fetching semster dates
	  * @param request
	  * @param response
	  * @return ModelAndView containing list of dates
	  * @throws Exception
	  */
	 public ModelAndView getSemesterDates(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		
		 HttpSession session = request.getSession(true);
		 String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
		     input.setRollNo(request.getParameter("rollNo"));
		     input.setProgramId(request.getParameter("programId"));
		     input.setBranchId(request.getParameter("branchId"));
		     input.setSpecializationId(request.getParameter("specializationId"));
		     input.setSemesterId(request.getParameter("semesterId"));
		     
		     StudentIssueInfoGetter semesterDates = (StudentIssueInfoGetter) studentIssueConnect.getSemesterDates(input);
		     
		      return new ModelAndView("StudentIssue/DateList",
	            "resultObject",semesterDates);
		    }
	 
	    /**
	    * Method for getting the all Issue codes
	    * @param request
	    * @param response
	    * @return ModelAndView containing list of Issue codes
	    * @throws Exception 
	    */
	    public ModelAndView getIssues(HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
	    	
	        StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
	        HttpSession session = request.getSession(true);
	        String userId = (String) session.getAttribute("userId");
	    	if(userId == null)
	        {
	        return new ModelAndView("general/SessionInactive","sessionInactive",true);
	        } 
	    	
			input.setUniversityId(session.getAttribute("universityId").toString());
			
			input.setGroupCode("ISUCOD");
				     
	        List<StudentIssueInfoGetter> resultGetIssueList = studentIssueConnect.getIssues(input);
	       
	        return new ModelAndView("StudentIssue/IssueList",
	            "resultObject", resultGetIssueList);
	    }
	    
	    /**
	     * Method for opening and closing an isse based on conditions
	     * @param request
	     * @param response
	     * @return ModelAndView containing String of sucess or failure
	     * @throws Exception
	     */
	    public ModelAndView insertIssueDetails(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		
		     StudentIssueInfoGetter input = new StudentIssueInfoGetter();
	        
		     HttpSession session = request.getSession(true);
		     String userId = (String) session.getAttribute("userId");
		    	if(userId == null)
		        {
		        return new ModelAndView("general/SessionInactive","sessionInactive",true);
		        } 
		    	
		     input.setUniversityCode(session.getAttribute("universityId").toString());
		     input.setUserId(session.getAttribute("userId").toString());
		     input.setUnivSessionStartDate(session.getAttribute("startDate").toString());
		     input.setEntityId(request.getParameter("entityId"));
		     input.setRollNo(request.getParameter("rollNo"));
		     input.setProgramId(request.getParameter("programId"));
		     input.setBranchId(request.getParameter("branchId"));
		     input.setSpecializationId(request.getParameter("specializationId"));
		     input.setSemesterId(request.getParameter("semesterId"));
		     input.setSemesterStartDate(request.getParameter("semesterStartDate"));
		     input.setSemesterEndDate(request.getParameter("semesterEndDate"));
		     input.setIssue(request.getParameter("issue"));
		     input.setPenalityCode(request.getParameter("penalityId"));
		     input.setPenalityCourse1(request.getParameter("penalityCourse1"));
		     input.setPenalityCourse2(request.getParameter("penalityCourse2"));
		     input.setPenalityCourse3(request.getParameter("penalityCourse3"));
		     input.setPenalityCourse4(request.getParameter("penalityCourse4"));
		     input.setRemarks(request.getParameter("remarks"));
		     
	        String insertResult = studentIssueConnect.insertIssue(input);

	        return new ModelAndView("preProcessChecks/preProcessResultlist",
	            "resultObject",insertResult);
		    }
}
