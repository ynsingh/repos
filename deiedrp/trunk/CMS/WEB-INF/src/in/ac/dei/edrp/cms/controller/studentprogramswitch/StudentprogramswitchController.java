package in.ac.dei.edrp.cms.controller.studentprogramswitch;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.Studentprogramswitch.Studentprogramswitchdao;
import in.ac.dei.edrp.cms.domain.studentprogramswitch.Studentprogramswitch;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class StudentprogramswitchController extends MultiActionController {

	/**
	* this is Server side controller class for Switch of Program/Branch/Specilization of students
	* @version 1.0 03, Dec 2011
	* @author ARUSH
	*/
	
	/** creating object of Student Program Switch interface */
	
	private Studentprogramswitchdao studentswitchdao;
	public void setStudentswitchdao(Studentprogramswitchdao studentswitchdao){
		this.studentswitchdao =studentswitchdao ;
	}
	
	
	
	
	/** defining setter method for object of Student Program Switch interface */
	//public void setStudentprogramswitchdao(Studentprogramswitchdao studentprogramswitchdao) {
	
	
	 public ModelAndView getSwitches(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 		//Studentprogramswitch input = new Studentprogramswitch();

		        HttpSession session = request.getSession(true);
		        
		        System.out.println("inside controller Arush ");

		        String userId = (String) session.getAttribute("userId");
				
				//if(userId == null)
		       // {
		        //return new ModelAndView("general/SessionInactive","sessionInactive",true);
		        //}
				
				//input.setUserId(userId);
		       
		        List<Studentprogramswitch> switches = studentswitchdao.getProgramswitches();
		        
		        System.out.println("Arush "+switches);

		        return new ModelAndView("StudentProgramSwitch/switchstudent",
		            "switchstudents", switches);
		        
		    
		        
		    }
	
	 public ModelAndView getstudentlist(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 		//Studentprogramswitch input = new Studentprogramswitch();

		        HttpSession session = request.getSession(true);
		        
		        System.out.println("inside  controller for getstudentforswitches ");

		        String userId = (String) session.getAttribute("userId");
		        
		        Studentprogramswitch student = new Studentprogramswitch();
		        student.setUniversityId(session.getAttribute("userId").toString().substring(1, 5));
		        student.setFmentityId(request.getParameter("fmentityId"));
		        student.setFmprogramId(request.getParameter("fmprogramId"));
		        
		        student.setFmbranchId(request.getParameter("fmbranchId"));
		        student.setFmspecializationId(request.getParameter("fmspecializationId"));
		        student.setFmsemesterCode(request.getParameter("fmsemesterCode"));
		        
		        student.setToentityId(request.getParameter("toentityId"));
		        student.setToprogramId(request.getParameter("toprogramId"));
		        student.setTobranchId(request.getParameter("tobranchId"));
		        student.setTospecializationId(request.getParameter("tospecializationId"));
		        student.setTosemesterCode(request.getParameter("tosemesterCode"));
		        
		        
		        student.setFmprogramcoursekey(request.getParameter("fmprogramcoursekey"));
		        student.setFmsemesterstartdate(request.getParameter("fmsemstdt"));
		        student.setFmsemesterenddate(request.getParameter("fmsemeddt"));
		        student.setTosemesterstartdate(request.getParameter("tosemstdt"));
		        student.setTosemesterenddate(request.getParameter("tosemeddt"));
		       
		       
		        List<Studentprogramswitch> studentlist = studentswitchdao.getstudentforswitches(student);
		        
		        System.out.println("Arush "+studentlist);

		        return new ModelAndView("StudentProgramSwitch/studentlist",
		            "studentlist", studentlist);
		        
		    
		        
		    }
	
	 public ModelAndView getsemesterdate(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 		//Studentprogramswitch input = new Studentprogramswitch();

		        HttpSession session = request.getSession(true);
		        
		        
		        System.out.println("inside  controller for getsemesterdate ");

		        String userId = (String) session.getAttribute("userId");
				
				        
		        Studentprogramswitch semesterdetail = new Studentprogramswitch();
		        System.out.println("university= "+ session.getAttribute("userId").toString().substring(1, 5));
		        
		        semesterdetail.setUniversityId(session.getAttribute("userId").toString().substring(1, 5));
		        
		        semesterdetail.setFmentityId(request.getParameter("fmentityId"));
		        semesterdetail.setFmprogramId(request.getParameter("fmprogramId"));
		        
		        semesterdetail.setFmbranchId(request.getParameter("fmbranchId"));
		        semesterdetail.setFmspecializationId(request.getParameter("fmspecializationId"));
		        semesterdetail.setFmsemesterCode(request.getParameter("fmsemesterCode"));
		        
		        semesterdetail.setToentityId(request.getParameter("toentityId"));
		        semesterdetail.setToprogramId(request.getParameter("toprogramId"));
		        semesterdetail.setTobranchId(request.getParameter("tobranchId"));
		        semesterdetail.setTospecializationId(request.getParameter("tospecializationId"));
		        semesterdetail.setTosemesterCode(request.getParameter("tosemesterCode"));
		        
		        List<Studentprogramswitch> semesterdatelist = studentswitchdao.getsemesterdate(semesterdetail);
		       
		        
		        System.out.println("Arush "+semesterdatelist);

		        return new ModelAndView("StudentProgramSwitch/semesterdatelist",
		            "semesterdatelist", semesterdatelist);
		        
		    
		        
		    } 
	 
	
	 public ModelAndView switchstudent(HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 		//Studentprogramswitch input = new Studentprogramswitch();

		        HttpSession session = request.getSession(true);
		        
		        
		        System.out.println("inside  controller for getsemesterdate ");

		        String userId = (String) session.getAttribute("userId");
				
				        
		        Studentprogramswitch semesterdetail = new Studentprogramswitch();
		        System.out.println("university= "+ session.getAttribute("userId").toString().substring(1, 5));
		        
		        semesterdetail.setUniversityId(session.getAttribute("userId").toString().substring(1, 5));
		        
		        semesterdetail.setFmentityId(request.getParameter("fmentityId"));
		        semesterdetail.setFmprogramId(request.getParameter("fmprogramId"));
		        
		        semesterdetail.setFmbranchId(request.getParameter("fmbranchId"));
		        semesterdetail.setFmspecializationId(request.getParameter("fmspecializationId"));
		        semesterdetail.setFmsemesterCode(request.getParameter("fmsemesterCode"));
		        
		        semesterdetail.setToentityId(request.getParameter("toentityId"));
		        semesterdetail.setToprogramId(request.getParameter("toprogramId"));
		        
		        semesterdetail.setTobranchId(request.getParameter("tobranchId"));
		        semesterdetail.setTospecializationId(request.getParameter("tospecializationId"));
		        semesterdetail.setTosemesterCode(request.getParameter("tosemesterCode"));
		        
		        
		        
		     
		        semesterdetail.setSwitchstatus(request.getParameter("switchstatus"));
		        
		        semesterdetail.setRollNumber(request.getParameter("rollNo"));
		        
		        semesterdetail.setTosemesterstartdate(request.getParameter("tosemstdt"));
		        semesterdetail.setTosemesterenddate(request.getParameter("tosemeddt"));
		        
		        semesterdetail.setTosemesterCode(request.getParameter("tosemesterCode"));
		        
		        
		        semesterdetail.setPrvswitchstatus(request.getParameter("prvswitchstatus"));
		        
		       
		        		        
		        List<Studentprogramswitch> switchstatus = studentswitchdao.switchstudent(semesterdetail);
		        
		    
		        
		        System.out.println("Switch Student " + switchstatus);

		        return new ModelAndView("StudentProgramSwitch/studentlist",
			            "studentlist", switchstatus);
		        
		    
		        
		    } 

	 
		
	}
	
	

