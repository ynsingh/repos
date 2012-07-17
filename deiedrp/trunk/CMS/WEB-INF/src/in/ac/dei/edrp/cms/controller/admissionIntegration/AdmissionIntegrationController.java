package in.ac.dei.edrp.cms.controller.admissionIntegration;


import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.admissionIntegration.AdmissionIntegrationDao;
import in.ac.dei.edrp.cms.domain.admissionIntegration.AdmissionIntegrationInfo;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Controller for Admission System Data import 
 * @author Dheeraj Singh
 * @date 14-MAY-2012
 * @version 1.0
 */

public class AdmissionIntegrationController extends MultiActionController {
	
	AdmissionIntegrationDao admissionIntegrationDao;
	
	private static Logger logObj = Logger.getLogger(AdmissionIntegrationController.class);
	
	/**
     * Method to initialize controller
     * @param admissionIntegrationDao object of AdmissionIntegrationDao interface
     */
	public void setAdmissionIntegrationDao(AdmissionIntegrationDao admissionIntegrationDao) {
        this.admissionIntegrationDao = admissionIntegrationDao;
    }
	
	/**
     *  Method to get the list of entities
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
	public ModelAndView getEntities(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
        	AdmissionIntegrationInfo input = new AdmissionIntegrationInfo();
        	List<AdmissionIntegrationInfo> list = admissionIntegrationDao.getEntities(input);
            return new ModelAndView("admissionIntegration/entityProgramList", "entityList", list);
    }
	
	/**
     *  Method to get the list of programs
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
	public ModelAndView getPrograms(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 

        	AdmissionIntegrationInfo input = new AdmissionIntegrationInfo();
        	input.setEntityId(request.getParameter("entityId"));
        	List<AdmissionIntegrationInfo> list = admissionIntegrationDao.getPrograms(input);
            return new ModelAndView("admissionIntegration/entityProgramList", "entityList", list);
    }
	
	/**
     *  Method to get the list of selected students
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
	public ModelAndView getStudentDetails(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
        	AdmissionIntegrationInfo input = new AdmissionIntegrationInfo();
        	input.setEntityId(request.getParameter("entityId"));
        	input.setProgramId(request.getParameter("programId"));
        	List<AdmissionIntegrationInfo> list = admissionIntegrationDao.getStudents(input);
            return new ModelAndView("admissionIntegration/studentDetails", "studentList", list);
    }
	
	/**
     *  Method to get the list of branch and specialization details
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
	public ModelAndView getBranchDetails(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
        	AdmissionIntegrationInfo input = new AdmissionIntegrationInfo();
        	input.setProgramId(request.getParameter("programId"));
        	List<AdmissionIntegrationInfo> list = admissionIntegrationDao.getBranches(input);
            return new ModelAndView("admissionIntegration/entityProgramList", "entityList", list);
    }
	
	/**
     *  Method for inserting data in CMS tables
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
	public ModelAndView submitDetails(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
			HttpSession session = request.getSession(true);
        	AdmissionIntegrationInfo input = new AdmissionIntegrationInfo();
        	input.setUniversityId(session.getAttribute("universityId").toString());
        	input.setCreatorId(session.getAttribute("userId").toString());
        	input.setBranchId(request.getParameter("branchId"));
        	input.setSpecializationId(request.getParameter("specializationId"));
        	StringTokenizer data = new StringTokenizer(request.getParameter("data"), ";");
        	String StatusValue = null;
        	try {
        		StatusValue = admissionIntegrationDao.submitDetails(input, data);
            } catch (MyException ex) {
                return new ModelAndView("RegistrationForm/RegisterStudent","result", ex.getMessage());
            }
            return new ModelAndView("RegistrationForm/RegisterStudent", "result", StatusValue);
    	}
	}
