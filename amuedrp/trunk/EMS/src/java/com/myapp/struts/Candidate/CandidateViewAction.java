/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;


import com.myapp.struts.hbm.CandidateRegistration;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
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
 * @author akhtar
 */
public class CandidateViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
   private VoterRegistration ob=new VoterRegistration();

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
            throws Exception
                 {
        CandidateRegActionForm employeeform=(CandidateRegActionForm)form;
         String button=employeeform.getButton();
         System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDD"+"gh"+button+"   "+employeeform.getEnrollment());
          String id=employeeform.getEnrollment();
           String eid=employeeform.getInstitute_id();
          System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  "+id+""+eid);
        VoterRegistration l=CandidateRegistrationDAO.searchVoterRegistration(eid,id);
       // CandidateRegistration c=CandidateRegistrationDAO.searchCandidateRegistration(eid,id,);
        //System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+l+c);
        if(button.equals("Add"))
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
            HttpSession session=request.getSession();
             ElectionDAO electiondao=new ElectionDAO();
            Election election=new Election();
            session.setAttribute("voter", l);

            if(l!=null){
             employeeform.setB_date(l.getBirthdate());
             employeeform.setC_add(l.getCAddress());
              employeeform.setCity(l.getCity());
               employeeform.setCity1(l.getCity1());
                employeeform.setCountry(l.getCountry());
                employeeform.setCountry1(l.getCountry1());
            employeeform.setCourse(l.getCourse());
                 employeeform.setDuration(l.getCourseDuration());
                 employeeform.setSession(l.getCurrentSession());


            employeeform.setDepartment(l.getDepartment());
             employeeform.setEmail(l.getEmail());
          
               employeeform.setF_name(l.getFName());
                employeeform.setGender(l.getGender());
           
            employeeform.setJ_date(l.getJoiningDate());
                 employeeform.setM_name(l.getMName());
                 employeeform.setM_number(l.getMobileNumber());

                employeeform.setP_add(l.getPAddress());
            employeeform.setState(l.getState());
                 employeeform.setState1(l.getState1());
              

                employeeform.setV_name(l.getVoterName());
           employeeform.setYear(l.getYear());
                 employeeform.setZipcode(l.getZipCode());
                 employeeform.setZipcode1(l.getZipCode1());

             employeeform.setEnrollment(l.getId().getEnrollment());
            employeeform.setInstitute_id(l.getId().getInstituteId());


             List elections=electiondao.Elections(l.getId().getInstituteId());
System.out.println(elections.size());
session.setAttribute("elections", elections);

            List position =electiondao.Positions(l.getId().getInstituteId());
            System.out.println(position.size());
            session.setAttribute("position", position);
            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }
            else{
            request.setAttribute("msg1", "plz register yourself as a voter first or you are already registered ");
            return mapping.findForward("duplicate");
                }

        }
   return mapping.findForward("failure");
        }
   }