/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;
import com.myapp.struts.hbm.InstituteDAO;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.LoginDAO;
import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.hbm.VoterRegistrationId;
import com.myapp.struts.utility.RandomPassword;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.utility.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author akhtar
 */
public class VoterViewAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
   private VoterRegistration ob=new VoterRegistration();

private VoterRegistrationId elid=new VoterRegistrationId();
 private LoginDAO logindao = new LoginDAO();
     private Login login =new Login();
      private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email obj;
    private String admin_password;
    private String admin_password1;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
                 {
        HttpSession session=request.getSession();
        VoterRegActionForm employeeform=(VoterRegActionForm)form;
         String button=employeeform.getButton();

        String  btn=request.getParameter("btn");
         System.out.println("BBBBBBBBBBBB"+button+button);
         
         
          String id=employeeform.getEnrollment();
          String eid=employeeform.getInstitute_id();
         // String institute_id=(String)session.getAttribute("institute_id");
          System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  "+id);
        VoterRegistration l=VoterRegistrationDAO.searchVoterRegistration(eid,id);

          if(button.equals("Add"))
        {
            if(l!=null){

            request.setAttribute("msg1", "enrollment: "+id+" already exists");
            return mapping.findForward("duplicate");
                       }
            else{
                 session.setAttribute("enrollment",id);
                 session.setAttribute("button", button);
                employeeform.setEnrollment(id);
                request.setAttribute("button", button);
         System.out.println("QQQQQQQQQQQQQQQQQQQ"+button);

        InstituteDAO insti= new InstituteDAO();
        String status="OK";
        List Institute = insti.getInstituteNameByStatus(status);
        System.out.println( "InstituteList"+""+Institute.size());
        session.setAttribute("Institute",Institute);
            return mapping.findForward("add");
                }
        }


if(btn.equalsIgnoreCase("Update")){
String rid=request.getParameter("id");
String reid=(String)session.getAttribute("institute_id");
VoterRegistration l1=VoterRegistrationDAO.searchVoterRegistration(reid,rid);
//
//
//
session.setAttribute("voter", l1);
           if(l1!=null){
          employeeform.setB_date(l1.getBirthdate());
             employeeform.setC_add(l1.getCAddress());
             employeeform.setCity(l1.getCity());
              employeeform.setCity1(l1.getCity1());
               employeeform.setCountry(l1.getCountry());
               employeeform.setCountry1(l1.getCountry1());
            employeeform.setCourse(l1.getCourse());
                 employeeform.setDuration(l1.getCourseDuration());
                employeeform.setSession(l1.getCurrentSession());
//
//
           employeeform.setDepartment(l1.getDepartment());
            employeeform.setEmail(l1.getEmail());
//              //employeeform.setFilename(l);
               employeeform.setF_name(l1.getFName());
              employeeform.setGender(l1.getGender());
//
               //employeeform.setUploadedFile(l.getImage());
           employeeform.setJ_date(l1.getJoiningDate());
                 employeeform.setM_name(l1.getMName());
                employeeform.setM_number(l1.getMobileNumber());
//
                employeeform.setP_add(l1.getPAddress());
            employeeform.setState(l1.getState());
                 employeeform.setState1(l1.getState1());
//               //  employeeform.setUploadedFile(l.);
//
                employeeform.setV_name(l1.getVoterName());
          employeeform.setYear(l1.getYear());
               employeeform.setZipcode(l1.getZipCode());
               employeeform.setZipcode1(l1.getZipCode1());
//                 //employeeform.setStatus(l.getStatus());
//
            employeeform.setEnrollment(l1.getId().getEnrollment());
          employeeform.setInstitute_id(l1.getId().getInstituteId());
           }
          //session.setAttribute("button", btn);
           return mapping.findForward("add");
//
//
}




         System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDD"+"gh"+button+"   "+employeeform.getEnrollment());
         
        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+l+eid);
       
       if(button.equals("Update"))
        {
           System.out.println("View Page");
//
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
//
//
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
        }

        if(button.equals("View"))
        {
System.out.println("View Page");

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

            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }
            else{
            request.setAttribute("msg1", "enrollment doesn't exists");
            return mapping.findForward("duplicate");
                }
        }
           if(button.equals("Delete"))
        {
               System.out.println("View Page");

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
             // employeeform.setFilename;
               employeeform.setF_name(l.getFName());
                employeeform.setGender(l.getGender());
            //    employeeform.setImage(l.getImage());
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

            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }
            else{
            request.setAttribute("msg1", "enrollment  doesn't exists");
            return mapping.findForward("duplicate");
                }
        }





           return mapping.findForward("failure");
    }
}
