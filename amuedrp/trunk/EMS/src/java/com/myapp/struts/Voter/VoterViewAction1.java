/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;
import com.myapp.struts.hbm.InstituteDAO;
import com.myapp.struts.hbm.VoterRegistrationId;
import com.myapp.struts.hbm.VoterRegistration;
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
public class VoterViewAction1 extends org.apache.struts.action.Action {
    
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
         HttpSession session=request.getSession();
        VoterRegActionForm employeeform=(VoterRegActionForm)form;
         String button="View";
         System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDD"+"gh"+button);



     


        String id="id";
         id=request.getParameter(id);
          String institute_id=(String)session.getAttribute("institute_id");
          System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  "+id);
        VoterRegistration l=VoterRegistrationDAO.searchVoterRegistration(institute_id,id);
        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+l);
   /*     if(button.equals("Add"))
        {
            if(l!=null){

            request.setAttribute("msg1", "enrollment: "+id+" already exists");
            return mapping.findForward("duplicate");
                       }
            else{
                employeeform.setEnrollment(id);
          //  request.setAttribute("button", button);
            return mapping.findForward("add");
                }
        }

        if(button.equals("Update"))
        {
            System.out.println("View Page");
            HttpSession session=request.getSession();
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
              //employeeform.setFilename(l);
               employeeform.setF_name(l.getFName());
                employeeform.setGender(l.getGender());

               //employeeform.setUploadedFile(l.getImage());
            employeeform.setJ_date(l.getJoiningDate());
                 employeeform.setM_name(l.getMName());
                 employeeform.setM_number(l.getMobileNumber());
                 
                employeeform.setP_add(l.getPAddress());
            employeeform.setState(l.getState());
                 employeeform.setState1(l.getState1());
               //  employeeform.setUploadedFile(l.);
                 
                employeeform.setV_name(l.getVoterName());
            employeeform.setYear(l.getYear());
                 employeeform.setZipcode(l.getZipCode());
                 employeeform.setZipcode1(l.getZipCode1());
                 
             employeeform.setEnrollment(l.getId().getEnrollment());
            employeeform.setInstitute_id(l.getId().getInstituteId());

            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }
            else{
            request.setAttribute("msg1", "enrollment  doesn't exists");
            return mapping.findForward("duplicate");
                }
        }*/

       if(button.equals("View"))
        {
System.out.println("View Page");
           
            session.setAttribute("voter", l);

          if(l!=null){


        InstituteDAO insti= new InstituteDAO();
        String status="OK";
        List Institute = insti.getInstituteNameByStatus(status);
        System.out.println( "instituteList"+""+Institute.size());
session.setAttribute("instituteList",Institute);
session.setAttribute("Institute",Institute);
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
            System.out.println("emailId="+l.getEmail());
            employeeform.setEmail(l.getEmail());
             // employeeform.setFilename;
               employeeform.setF_name(l.getFName());
                employeeform.setGender(l.getGender());
            //   employeeform.setImage(l.getImage());
            employeeform.setJ_date(l.getJoiningDate());
                 employeeform.setM_name(l.getMName());
                 employeeform.setM_number(l.getMobileNumber());

                employeeform.setP_add(l.getPAddress());
            employeeform.setState(l.getState());
                 employeeform.setState1(l.getState1());
               //employeeform.setUploadedFile(l.getImage());

                employeeform.setV_name(l.getVoterName());
           employeeform.setYear(l.getYear());
                 employeeform.setZipcode(l.getZipCode());
                 employeeform.setZipcode1(l.getZipCode1());
                 

             employeeform.setEnrollment(l.getId().getEnrollment());
            employeeform.setInstitute_id(l.getId().getInstituteId());

            if(request.getParameter("status")!=null && request.getParameter("status").equalsIgnoreCase("AB"))
            {button = "AB";}
            if(request.getParameter("status")!=null && request.getParameter("status").equalsIgnoreCase("B"))
            {button = "Block1";}


            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }
           




          return mapping.findForward("add");
       }
        return mapping.findForward("duplicate");
    }

}

