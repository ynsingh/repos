package in.ac.dei.edrp.cms.controller.report;

import in.ac.dei.edrp.cms.dao.report.printMechanismDao;

import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;
import in.ac.dei.edrp.cms.domain.report.printMechanismInfoGetter;




import java.util.ArrayList;
import java.util.StringTokenizer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class printMechanismController extends MultiActionController {
	private printMechanismDao printMechanismDao;

	/**
	 * @param printMechanismDao the printMechanismDao to set
	 */
	public void setPrintMechanismDao(printMechanismDao printMechanismDao) {
		this.printMechanismDao = printMechanismDao;
	}


	public ModelAndView getReportsSession(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		printMechanismInfoGetter input = new printMechanismInfoGetter();
		input.setUserId(session.getAttribute("userId").toString());
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setApplication(session.getAttribute("application").toString());
		input.setSessionStartDate(request.getParameter("sessionStartDate"));
		input.setSessionEndDate(request.getParameter("sessionEndDate"));
		List<printMechanismInfoGetter> resultObject = printMechanismDao.getReportsDetails(input);
		return new ModelAndView("reportgeneration/ashu", "resultObject",resultObject);
	}

	/**
	 * The method retrieves the list of entities which the students are persuing
	 * there programs in a university
	 * 
	 * @param request
	 * @param response
	 * @return model & view object
	 * @throws Exception
	 */
	public ModelAndView getEntities(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		printMechanismInfoGetter input = new printMechanismInfoGetter();
		input.setUniversityCode(userId.substring(1, 5) + "%");
		List<printMechanismInfoGetter> resultObject = printMechanismDao.getEntities(input);
		return new ModelAndView("reportgeneration/printMechanism","entityList", resultObject);
	}
	
	public ModelAndView getCombinations4Entity(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		printMechanismInfoGetter input = new printMechanismInfoGetter();
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setPassedFromSession(request.getParameter("fromSession"));
		input.setPassedToSession(request.getParameter("toSession"));
		input.setEntityId(request.getParameter("entityId"));
		input.setReportType(request.getParameter("reportType"));
		input.setReportCode(request.getParameter("reportCode"));
		List<printMechanismInfoGetter> resultObject = printMechanismDao.getCombinations4Entity(input);
		return new ModelAndView("reportgeneration/EntityCombinations","resultObject", resultObject);
	}
	
	public ModelAndView getGeneralReports(HttpServletRequest request,HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession(true);
		printMechanismInfoGetter input = new printMechanismInfoGetter();
		System.out.println("general reports");
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setUserId(session.getAttribute("userId").toString());
		List<printMechanismInfoGetter>reportList=new ArrayList<printMechanismInfoGetter>();
		reportList=printMechanismDao.getGeneralReportList(input);
		return new ModelAndView("reportgeneration/ashu", "resultObject",reportList);
	}
	
	/**
	 * Method for getting the details of the Report
	 * @param request
	 * @param response
	 * @return  Model And View reportDetail
	 * @throws Exception
	 */	
	public ModelAndView getReportDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession(true);
	    String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
	    {
	    return new ModelAndView("general/SessionInactive","sessionInactive",true);
	    }
	printMechanismInfoGetter input=new printMechanismInfoGetter();
	input.setUniversityCode(session.getAttribute("universityId").toString());
	input.setUserId(session.getAttribute("userId").toString());
	input.setUserRoleId(request.getParameter("userRoleId"));
	List<printMechanismInfoGetter>reportDetail=new ArrayList<printMechanismInfoGetter>();
	reportDetail=printMechanismDao.getReportDetailList(input);
	return new ModelAndView("reportgeneration/ashu","resultObject",reportDetail);
	}
	   /**
     * Method for Inserting,Updating,Deleting Report Authority Detail
     * @param request
     * @param response
     * @return Model And View insert operation result
     * @throws Exception
     */

	public ModelAndView setReportAuthority(HttpServletRequest request,
			HttpServletResponse response){
		HttpSession session = request.getSession(true);
	    String userId = (String) session.getAttribute("userId");
	    String universityId = (String) session.getAttribute("universityId");
		
		if(userId == null)
	    {
	    return new ModelAndView("general/SessionInactive","sessionInactive",true);
	    }
		printMechanismInfoGetter input=new printMechanismInfoGetter();
		String setReportDetail=null;
		
		System.out.println(request.getParameter("reportCode")+"code"+request.getParameter("genAuthority")+"gen"+request.getParameter("dwnAuthority")+"dwn");
        StringTokenizer reportCodeToken=new StringTokenizer(request.getParameter("reportCode"),"|");
        StringTokenizer generateAuthToken=new StringTokenizer(request.getParameter("genAuthority"),"|");
        StringTokenizer downAuthToken=new StringTokenizer(request.getParameter("dwnAuthority"),"|");
        StringTokenizer deleteReportCodeToken=new StringTokenizer(request.getParameter("deleteReportCode"),"|");
        while(reportCodeToken.hasMoreTokens()){
        	input.setPrintStatus("Insert");
        	input.setReportCode(reportCodeToken.nextToken());
        	input.setGenerateAuthority(generateAuthToken.nextToken());
        	input.setDownloadAuthority(downAuthToken.nextToken());
    		input.setUniversityCode(session.getAttribute("universityId").toString());
    		input.setUserId(session.getAttribute("userId").toString());
        	input.setUserRoleId(request.getParameter("userRoleId"));
        	setReportDetail=printMechanismDao.setReportAuthority(input);
        	
        }
        while (deleteReportCodeToken.hasMoreTokens()) {
        	input.setPrintStatus("delete");
        	input.setDeleteReportCode(deleteReportCodeToken.nextToken());
    		input.setUniversityCode(session.getAttribute("universityId").toString());
    		input.setUserId(session.getAttribute("userId").toString());
        	input.setUserRoleId(request.getParameter("userRoleId"));
        	setReportDetail=printMechanismDao.setReportAuthority(input);
			
		}
		
	    return new ModelAndView("preProcessChecks/preProcessResultlist",
	            "resultObject", setReportDetail);
	}

	/**
	 * Method for getting the details of the Where Entity Not In Use Report
	 * @param request
	 * @param response
	 * @return  Model And View reportDetail
	 * @throws Exception
	 */
	public ModelAndView getReportDetailsWithoutEntity(HttpServletRequest request,HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession(true);
		printMechanismInfoGetter input = new printMechanismInfoGetter();
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setPassedFromSession(request.getParameter("fromSession"));
		input.setPassedToSession(request.getParameter("toSession"));
		input.setEntityId(request.getParameter("entityId"));
		input.setReportType(request.getParameter("reportType"));
		input.setReportCode(request.getParameter("reportCode"));
		List<printMechanismInfoGetter> resultObject = printMechanismDao.getCombinations4Entity(input);
		return new ModelAndView("reportgeneration/EntityCombinations","resultObject", resultObject);
	}
	
	
//	public ModelAndView processReportRequest(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//
//		HttpSession session = request.getSession(true);
//
//		printMechanismInfoGetter input = new printMechanismInfoGetter();
//
//		input
//				.setUniversityCode(session.getAttribute("universityId")
//						.toString());
//		input.setPassedFromSession(request.getParameter("fromSession"));
//		input.setPassedToSession(request.getParameter("toSession"));
//		input.setEntityId(request.getParameter("entityId"));
//		input.setReportCode(request.getParameter("reportCode"));
//		input.setReportActivity(request.getParameter("buttonLabel"));
//
//		/*
//		 * if report is to be generated program course key wise
//		 */
//		if (request.getParameter("reportType").equalsIgnoreCase("RCL")) {
//
//			input.setEntityName(request.getParameter("entityName"));
//			input.setProgramName(request.getParameter("programName"));
//			input.setProgramId(request.getParameter("programId"));
//			input.setBranchId(request.getParameter("branchId"));
//			input.setBranchName(request.getParameter("branchName"));
//			input.setSpecializationId(request.getParameter("specializationId"));
//			input.setSpecializationName(request
//					.getParameter("specializationName"));
//			input.setSemesterCode(request.getParameter("semesterCode"));
//			input.setSemesterName(request.getParameter("semesterName"));
//			input.setSemesterStartDate(request
//					.getParameter("semesterStartDate"));
//			input.setSemesterEndDate(request.getParameter("semesterEndDate"));
//
//		}
//
//		if (input.getReportActivity().equalsIgnoreCase("Generate")
//				&& (input.getReportCode().equalsIgnoreCase("6"))) {
//			
//			
//
//		}
//
//		return null;
//
//	}
}
