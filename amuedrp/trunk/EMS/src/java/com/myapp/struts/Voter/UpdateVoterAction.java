/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.utility.UserLog;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author edrp01
 */
public class UpdateVoterAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

         HttpSession session=request.getSession();
        VoterRegActionForm employeeform=(VoterRegActionForm)form;
       //  String button=employeeform.getButton();
        String button="Update";
  //VoterRegistrationDAO obj= new VoterRegistrationDAO();
        String  btn=request.getParameter("btn");
        
        if(btn.equalsIgnoreCase("Update")){
String rid=request.getParameter("id");
String reid=(String)session.getAttribute("institute_id");
VoterRegistration l1=VoterRegistrationDAO.searchVoterRegistration(reid,rid);
 FormFile v=(FormFile)session.getAttribute("filename");
           byte[] iii=null;
           if(v!=null)iii=v.getFileData();
//
//
//
           session.setAttribute("voter", l1);
           if(l1!=null){
              employeeform.setAlternateemail(l1.getAlternateMail());
            employeeform.setB_date(l1.getBirthdate());
             employeeform.setC_add(l1.getCAddress());
             employeeform.setCity(l1.getCity());
              employeeform.setCity1(l1.getCity1());
               employeeform.setCountry(l1.getCountry());
               employeeform.setCountry1(l1.getCountry1());
               employeeform.setCourse(l1.getCourse());
                 employeeform.setDuration(l1.getCourseDuration());
                employeeform.setSession(l1.getCurrentSession());

             employeeform.setDepartment(l1.getDepartment());
            employeeform.setEmail(l1.getEmail());

             employeeform.setF_name(l1.getFName());
              employeeform.setGender(l1.getGender());
              employeeform.setJ_date(l1.getJoiningDate());
                 employeeform.setM_name(l1.getMName());
                employeeform.setM_number(l1.getMobileNumber());
                 employeeform.setP_add(l1.getPAddress());
            employeeform.setState(l1.getState());
                 employeeform.setState1(l1.getState1());
                  employeeform.setV_name(l1.getVoterName());
          employeeform.setYear(l1.getYear());
               employeeform.setZipcode(l1.getZipCode());
               employeeform.setZipcode1(l1.getZipCode1());
                employeeform.setEnrollment(l1.getId().getEnrollment());
          employeeform.setInstitute_id(l1.getId().getInstituteId());


         // session.setAttribute("button", btn);
                     
          
          //return mapping.findForward("add");
           }
          session.setAttribute("button", btn);

         
//
//
}

      return mapping.findForward("add");

    }


}
