/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;

import  com.myapp.struts.MyConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;
import com.myapp.struts.utility.*;
import java.sql.Connection;







/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Admin Registration Activity
 */
public class AdminRegistrationAction extends org.apache.struts.action.Action {




private String admin_password;
private String login_id;
boolean result;
AdminRegistration adminobj;
 private byte[] imagefile;
Connection con1;





    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {



             UserLog.setPath(request.getContextPath());

 HttpSession session1 =request.getSession();

 //ImageUploadActionForm form1 = (ImageUploadActionForm)session1.getAttribute("ImageUploadActionForm");

        AdminRegistrationActionForm adminRegistrationActionForm=(AdminRegistrationActionForm)form;
        
        login_id=adminRegistrationActionForm.getLogin_id();
       // admin_password=adminRegistrationActionForm.getAdmin_password();
      // System.out.println(admin_password);
        //admin_password=PasswordEncruptionUtility.password_encrupt(admin_password);
        HttpSession session=request.getSession();


        try{
       
            
        con1= MyConnection.getMyConnection();

            if(con1==null)
             {
                request.setAttribute("msg1","Database Connectivity is Closed");
                return mapping.findForward("failure");
             }






             adminobj=new AdminRegistration();



            
            AdminRegistration tempobj=(AdminRegistration)AdminRegistrationDAO.searchLoginID(login_id);
            Login loginobj=(Login)LoginDAO.searchLoginID(login_id);
           

   

          if(tempobj!=null || loginobj!=null)
            {

            request.setAttribute("msg", "User ID is Duplicate");
                return mapping.findForward("duplicate");
           }

           else
            {


                adminobj.setInstituteName(adminRegistrationActionForm.getInstitute_name());
                adminobj.setAbbreviatedName(adminRegistrationActionForm.getAbbreviated_name());
                adminobj.setInstituteAddress(adminRegistrationActionForm.getInstitute_address());
                adminobj.setPin(adminRegistrationActionForm.getPin());
                adminobj.setCity(adminRegistrationActionForm.getCity());
                adminobj.setState(adminRegistrationActionForm.getState());
                adminobj.setCountry(adminRegistrationActionForm.getCountry());
                adminobj.setLandLineNo(adminRegistrationActionForm.getLand_line_no());
                adminobj.setMobileNo(adminRegistrationActionForm.getMobile_no());
                adminobj.setTypeOfInstitute(adminRegistrationActionForm.getType_of_institute());
                
                adminobj.setWebsite(adminRegistrationActionForm.getInstitute_website());
                adminobj.setAdminFname(adminRegistrationActionForm.getAdmin_fname());
                adminobj.setAdminLname(adminRegistrationActionForm.getAdmin_lname());
                adminobj.setAdminDesignation(adminRegistrationActionForm.getAdmin_designation());
                adminobj.setAdminEmail(adminRegistrationActionForm.getAdmin_email());
             //   adminobj.setAdminPassword(admin_password);
                adminobj.setLibraryName(adminRegistrationActionForm.getLibrary_name());
                adminobj.setCourtesy(adminRegistrationActionForm.getCourtesy());
                adminobj.setRegistrationId(AdminRegistrationDAO.maxRegistrationID());
                adminobj.setGender(adminRegistrationActionForm.getGender());
                adminobj.setLoginId(adminRegistrationActionForm.getLogin_id());
                adminobj.setWorkingStatus("OK");
                adminobj.setStatus("NotRegistered");
           //  if (form1!=null)
           //      adminobj.setInstiLogo(form1.getImg().getFileData());


                 result=AdminRegistrationDAO.insert1(adminobj);
                if(result==false)
                {
                    String msg="Request for registration failure due to some error";
                    request.setAttribute("msg1", msg);
                    return mapping.findForward("failure");
                }

                String msg="Request for Library Registration accepted successfully" ;

              
                request.setAttribute("registration_msg", msg);
                return mapping.findForward("success");
           }

        }
        catch(Exception e)
        {
      
            String msg="Request for registration failure due to some error";
            request.setAttribute("msg1", msg);
            session.invalidate();
            System.out.println(e.toString());
            return mapping.findForward("failure");
        }

    }
}
