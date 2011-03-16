/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;


import  com.myapp.struts.*;
import com.myapp.struts.hbm.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

/**
 *
 * @author Dushyant
 */
public class AdminViewAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    
    private  String institute_id;
    private int registration_request_id;
    private String staff_id;

    
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        AdminViewActionForm admin=(AdminViewActionForm)form;
        registration_request_id=admin.getRegistration_request_id();
        institute_id=admin.getInstitute_id();
        System.out.println("Registration_Id="+ registration_request_id +" Institute ID="+institute_id);
        staff_id="admin."+institute_id;

        //Institute Table Data Access Objects and POJO's
            InstituteDAO institutedao = new InstituteDAO();
            Institute institute = new Institute();
            //Staff Detail Table Data Access Objects and POJO's
            StaffDetailDAO staffDetaildao = new StaffDetailDAO();
            StaffDetail staffDetail = new StaffDetail();
            StaffDetailId staffDetailId = new StaffDetailId();
            //Login Table Data Access Objects and POJO's
            LoginDAO logindao = new LoginDAO();
            Login login = new Login();
            //Admin Registration Table Data Access Objects and POJO's
            AdminRegistrationDAO admindao = new AdminRegistrationDAO();
            AdminRegistration adminReg = new AdminRegistration();

if (institute_id!=null)
{
          //  System.out.println("..................."+institute_name);
      // System.out.println("user_id="+institute_id+library_name);
  try
   {
        System.out.println("Institute Id="+institute_id);
        boolean b=DuplicateEntry.checkDuplicateEntry("institute","institute_id",institute_id);
        boolean b1=DuplicateEntry.checkDuplicateEntry("login","staff_id",staff_id,institute_id);
        if(b==false)
        {
            
         //request.setAttribute("query_id",query_id);
      //   System.out.println(query_id);
             request.setAttribute("msg", "Institute Already Registered with institute_id: "+institute_id);
             return mapping.findForward("failure");
        }
        if(b1==false)
        {
            request.setAttribute("msg", "Admin Already Registered with Staff_ID: "+staff_id);
           
              return mapping.findForward("failure");
        }
       //insert into library
           List adminList = (List)admindao.getAdminDeatilsById(registration_request_id);
            if (adminList.isEmpty())
            {
                request.setAttribute("ErrorMsg", "DataBase Disconnected! Please Contact Web Admin");
                return mapping.findForward("error");
            }
            adminReg = (AdminRegistration)adminList.get(0);

            institute.setInstituteId(institute_id);
            institute.setRegistrationId(registration_request_id);
            institute.setInstituteName(adminReg.getInstituteName());
            institute.setWorkingStatus("OK");

            //institutedao.insert(institute);

            staffDetailId.setStaffId(staff_id);
            staffDetailId.setInstituteId(institute_id);

            staffDetail.setId(staffDetailId);
            staffDetail.setInstitute(institute);
            staffDetail.setEmailId(adminReg.getAdminEmail());
            staffDetail.setFirstName(adminReg.getAdminFname());
            staffDetail.setLastName(adminReg.getAdminLname());
            String user_id = adminReg.getUserId();
            System.out.println("user_id = "+user_id);
            login.setUserId(user_id);
            login.setUserName(adminReg.getAdminFname()+" "+ adminReg.getAdminLname());
            login.setPassword(adminReg.getAdminPassword());
            login.setStaffDetail(staffDetail);

            institutedao.insert(institute);
            staffDetaildao.insert(staffDetail);
            logindao.insert(login,(String)admin.getUser_id());
            
            adminReg.setStatus("Registered");
            admindao.update(adminReg);
            request.setAttribute("accept_msg1", institute_id);
            request.setAttribute("accept_msg3",adminReg.getInstituteName() );
            request.setAttribute("msg", "Request Accepted with institute Id "+institute_id);
            
        
        
return mapping.findForward("success");
    }
        catch(Exception e2)
        {
       System.out.println(e2);
       
        }
  return mapping.findForward("success");
}
        return mapping.findForward("failure");
    }
}
