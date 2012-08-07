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
public class UpdateVoterAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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
         HttpSession session=request.getSession();
        VoterRegActionForm employeeform=(VoterRegActionForm)form;
        String  btn=employeeform.getButton();
         if(btn.equalsIgnoreCase("Update")){
//String rid=request.getParameter("id");
String rid=employeeform.getEnrollment();
String reid=(String)session.getAttribute("institute_id");
VoterRegistration l1=VoterRegistrationDAO.searchVoterRegistration(reid,rid);
 FormFile v=(FormFile)session.getAttribute("filename");
           byte[] iii=null;
           if(v!=null)iii=v.getFileData();
           session.setAttribute("voter", l1);
         l1.setBirthdate(employeeform.getB_date());
          l1.setAlternateMail(employeeform.getAlternateemail());
             l1.setCAddress(employeeform.getC_add());
             l1.setCity(employeeform.getCity());
              l1.setCity1(employeeform.getCity1());
               l1.setCountry(employeeform.getCountry());
               l1.setCountry1(employeeform.getCountry1());
               l1.setCourse(employeeform.getCourse());
                 l1.setCourseDuration(employeeform.getDuration());
                l1.setCurrentSession(employeeform.getSession());
//
//
           l1.setDepartment(employeeform.getDepartment());
          //  l1.setEmail(employeeform.getEmail());
//             employeeform.setFilename(l);
               l1.setFName(employeeform.getF_name());
              l1.setGender(employeeform.getGender());
//
             //  employeeform.setUploadedFile(l1.getImage());
           l1.setJoiningDate(employeeform.getJ_date());
                 l1.setMName(employeeform.getM_name());
                l1.setMobileNumber(employeeform.getM_number());
//
                l1.setPAddress(employeeform.getP_add());
            l1.setState(employeeform.getState());
                l1.setState1(employeeform.getState1());
//               //  employeeform.setUploadedFile(l.);
//
                l1.setVoterName(employeeform.getV_name());
                l1.setYear(employeeform.getYear());
               l1.setZipCode(employeeform.getZipcode());
               l1.setZipCode1(employeeform.getZipcode1());
//                 //employeeform.setStatus(l.getStatus());
//
           // l1.setEnrollment(l1.getId().getEnrollment());
         // employeeform.setInstitute_id(l1.getId().getInstituteId());
           if (iii!=null)
               {
                  String ext=UserLog.returnextension(v.getFileName());
                  UserLog.writeImage1(rid+reid+"."+ext, iii);
                l1.setImage(rid+reid+"."+ext);
                  //employeeform.setFilename(rid+reid+"."+ext);
               }
               VoterRegistrationDAO.update(l1);
            session.setAttribute("button", btn);
            request.setAttribute("msg","Record Updated Successfully");
         }

        
        return mapping.findForward("add");
    }
}
