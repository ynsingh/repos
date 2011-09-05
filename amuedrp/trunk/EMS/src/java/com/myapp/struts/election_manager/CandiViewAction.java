/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Candidate.CandidateRegActionForm;
import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.CandidateRegistration;
import com.myapp.struts.hbm.CandidateRegistrationId;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.Position1;
import com.myapp.struts.hbm.PositionDAO;
import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.hbm.VoterRegistrationId;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Edrp-04
 */
public class CandiViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     private VoterRegistration ob=new VoterRegistration();
     private CandidateRegistration ob1= new CandidateRegistration();
     private CandidateRegistrationId canid= new CandidateRegistrationId();
     PositionDAO p1=new PositionDAO();
     ElectionDAO ED= new ElectionDAO();

private VoterRegistrationId elid=new VoterRegistrationId();
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        CandidateRegActionForm employeeform=(CandidateRegActionForm)form;
         String button="add";
         String stat = request.getParameter("status");
           HttpSession session=request.getSession();
          String  id="id";
          id=request.getParameter(id);
          String position = request.getParameter("pos");
           String eid=(String)session.getAttribute("institute_id");
           
          System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  "+id+eid);
        VoterRegistration r=CandidateRegistrationDAO.searchVoterRegistration(eid,id);
        CandidateRegistration c=CandidateRegistrationDAO.searchCandidateRegistration(eid,id,position);
Candidate1 candi=new Candidate1();

        
        //Position1 p= p1.searchPosition(Integer.parseInt(c.getPosition()));


List<Candidate1> c1=p1.ElectionId(Integer.parseInt(c.getPosition()), eid)   ;



 String electionid=c.getId().getElectionId();

Election e=ED.Electionname(eid,electionid);
String Elec=e.getElectionName();

System.out.println("ElectionId    "+electionid+""+"electionname"+Elec);

        Position1 p=p1.searchPosition1(Integer.parseInt(c.getPosition()),electionid, eid);


        

        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+c);
        if(button.equals("add"))
        {
            /* if(l!=null){

            request.setAttribute("msg1", "your request for candidate registration for Enrollment: "+id+" has been already sent");
            return mapping.findForward("duplicate");
                       }
else{
                employeeform.setEnrollment(id);
                employeeform.setInstitute_id(eid);
            request.setAttribute("button", button);
            return mapping.findForward("add");
                }
        }*/
System.out.println("View Page");
          
            session.setAttribute("voter", r);
            

            if(r!=null){
              
            
                employeeform.setB_date(r.getBirthdate());
             employeeform.setC_add(r.getCAddress());
              employeeform.setCity(r.getCity());
               employeeform.setCity1(r.getCity1());
                employeeform.setCountry(r.getCountry());
                employeeform.setCountry1(r.getCountry1());
            employeeform.setCourse(r.getCourse());
                 employeeform.setDuration(r.getCourseDuration());
                 employeeform.setSession(r.getCurrentSession());


            employeeform.setDepartment(r.getDepartment());
             employeeform.setEmail(r.getEmail());

               employeeform.setF_name(r.getFName());
                employeeform.setGender(r.getGender());

            employeeform.setJ_date(r.getJoiningDate());
                 employeeform.setM_name(r.getMName());
                 employeeform.setM_number(r.getMobileNumber());

                employeeform.setP_add(r.getPAddress());
            employeeform.setState(r.getState());
                 employeeform.setState1(r.getState1());


                employeeform.setV_name(r.getVoterName());
           employeeform.setYear(r.getYear());
                 employeeform.setZipcode(r.getZipCode());
                 employeeform.setZipcode1(r.getZipCode1());

             employeeform.setEnrollment(r.getId().getEnrollment());
            employeeform.setInstitute_id(r.getId().getInstituteId());

//            employeeform.setEnrolled_in(c.getEnrolledIn());
//            employeeform.setP_marks(c.getPMarks());
//            employeeform.setP_attendence(c.getPAttendence());
//
//            employeeform.setBacklog(c.getBacklog());
//            employeeform.setCriminal(c.getCriminal());
//            employeeform.setIndisc(c.getIndisc());
            employeeform.setPosition(p.getPositionName());
            employeeform.setElections(Elec);
            request.setAttribute("button", button);
           if(stat.equalsIgnoreCase("NR"))
               return mapping.findForward("accept");
           else
             return mapping.findForward(SUCCESS);
                       }
            else{
            request.setAttribute("msg1", "plz register yourself as a voter first ");
            return mapping.findForward("duplicate");
                }

        }
   return mapping.findForward("failure");
    }
}
