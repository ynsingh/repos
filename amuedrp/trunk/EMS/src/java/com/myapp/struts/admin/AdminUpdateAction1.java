/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import  com.myapp.struts.*;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author System Administrator
 */
public class AdminUpdateAction1 extends org.apache.struts.action.Action {
    
   private int registration_id ;
private String institute_name ;
private String abbreviated_name ;
private String institute_address ;
private String city ;
private String state ;
private String country ;
private String pin ;
private String land_line_no ;
private String mobile_no ;
private String institute_domain ;
private String institute_website ;
private String admin_fname ;
private String admin_lname ;
private String admin_designation ;
private String type_of_institute;
private String admin_email;
private String admin_password;
private String library_name;
private String courtesy;
private String gender;
private String sql;
private String status;
private String institute_id;
private String staff_id;
private String user_id;
private String working_status;

int i=0;
    /* forward name="success" path="" */


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
            throws Exception {
        AdminViewActionForm adminRegistrationActionForm=(AdminViewActionForm)form;
        registration_id=adminRegistrationActionForm.getRegistration_request_id();
        institute_name=adminRegistrationActionForm.getInstitute_name();
        abbreviated_name=adminRegistrationActionForm.getAbbreviated_name();
        institute_address=adminRegistrationActionForm.getInstitute_address();
        pin=adminRegistrationActionForm.getPin();
        city=adminRegistrationActionForm.getCity();
        state=adminRegistrationActionForm.getState();
        country=adminRegistrationActionForm.getCountry();
        land_line_no=adminRegistrationActionForm.getLand_line_no();
        mobile_no=adminRegistrationActionForm.getMobile_no();
        type_of_institute=adminRegistrationActionForm.getType_of_institute();
        institute_domain=adminRegistrationActionForm.getInstitute_domain();
        institute_website=adminRegistrationActionForm.getInstitute_website();
        admin_fname=adminRegistrationActionForm.getAdmin_fname();
        admin_lname=adminRegistrationActionForm.getAdmin_lname();
        admin_designation=adminRegistrationActionForm.getAdmin_designation();
        admin_email=adminRegistrationActionForm.getAdmin_email();
        admin_password=adminRegistrationActionForm.getAdmin_password();
        library_name=adminRegistrationActionForm.getLibrary_name();
        courtesy=adminRegistrationActionForm.getCourtesy();
        gender=adminRegistrationActionForm.getGender();
        institute_id=adminRegistrationActionForm.getInstitute_id();
        user_id=adminRegistrationActionForm.getUser_id();

        System.out.println("user_id="+user_id);
      
        HttpSession session=request.getSession();
        InstituteDAO institutedao = new InstituteDAO();
        AdminRegistrationDAO admindao = new AdminRegistrationDAO();
        LoginDAO logindao = new LoginDAO();
        StaffDetailDAO staffdetaildao = new StaffDetailDAO();
        try
        {
                Institute rs2 = institutedao.getInstituteDetails(institute_id);
                // Iterator it = rs2.iterator();


           /*     if(rs2!=null)
                {
                System.out.println("This Institute ID already exists");

                //   System.out.println(query_id);
                request.setAttribute("reg", "Institute Already Registered With Institute ID:"+institute_id);
                return mapping.findForward("failure");
                }*/

            
            List rs= admindao.getAdminDeatilsByUserId(user_id);
        
            staff_id = "admin."+institute_id;

            List rs1=staffdetaildao.getStaffDetails(staff_id, institute_id);
            Iterator it1 = rs.iterator();
            Iterator it2 = rs1.iterator();

   /*    if(it2.hasNext() || it1.hasNext() )
       {
          //  System.out.println("Duplicate Email ID");
            request.setAttribute("reg1", "Admin."+institute_id+" already exists");
            return mapping.findForward("failure");
       }
*/

            List rs3=admindao.getAdminDeatilsById(registration_id);
            Iterator it3 = rs3.iterator();
            int count = 0;
            if(it3.hasNext())
            {
            AdminRegistration adminReg = (AdminRegistration) rs3.get(count++);
            status=adminReg.getStatus();
            working_status = adminReg.getWorkingStatus();
            //staff_id = adminReg.getStaffId();
            }
        //if admin is registered
      
            //update admin
          AdminRegistration adminReg = new AdminRegistration();

          adminReg.setRegistrationId(registration_id);
          adminReg.setInstituteName(institute_name);
          adminReg.setAbbreviatedName(abbreviated_name);
          adminReg.setInstituteAddress(institute_address);
          adminReg.setCity(city);
          adminReg.setState(state);
          adminReg.setCountry(country);
          adminReg.setPin(pin);
          adminReg.setLandLineNo(land_line_no);
          adminReg.setMobileNo(mobile_no);
          adminReg.setDomain(institute_domain);
          adminReg.setTypeOfInstitute(type_of_institute);
          adminReg.setWebsite(institute_website);
          adminReg.setAdminFname(admin_fname);
          adminReg.setAdminLname(admin_lname);
          adminReg.setAdminDesignation(admin_designation);
          adminReg.setAdminEmail(admin_email);
          adminReg.setAdminPassword(admin_password);
          adminReg.setCourtesy(courtesy);
          adminReg.setGender(gender);
          adminReg.setStatus(status);
          adminReg.setWorkingStatus(working_status);
          adminReg.setUserId(user_id);
          
            admindao.update(adminReg);
if(status.equals("Registered"))
       {

                      
                
                   /* ResultSet rs4=MyQueryResult.getMyExecuteQuery("select staff_id from admin_registration where registration_id="+registration_id) ;
                    if(rs4.next())
                        staff_id=rs4.getString("staff_id");
            ///update staff
*/
            
            StaffDetailId staffdetail1 = new StaffDetailId(staff_id, institute_id);
            StaffDetail staffdetail = new StaffDetail();
            staffdetail.setTitle(courtesy);
            staffdetail.setFirstName(admin_fname);
            staffdetail.setLastName(admin_lname);
            staffdetail.setContactNo(land_line_no);
            staffdetail.setMobileNo(mobile_no);
            staffdetail.setEmailId(admin_email);
            staffdetail.setGender(gender);
            staffdetail.setCity1(city);
            staffdetail.setState1(state);
            staffdetail.setCountry1(country);
            staffdetail.setZip1(pin);
            staffdetail.setId(staffdetail1);

           // staffdetaildao.update(staffdetail);


                    Login login = new Login();
                    login.setUserId(user_id);
                    login.setUserName(admin_fname + " "+admin_lname);
                    login.setPassword(admin_password);
                    login.setStaffDetail(staffdetail);
                    logindao.update(login);
                     
                    String msg="Record Updated Successfully for Institute :"+institute_name;
                    request.setAttribute("msg", msg);
                     request.setAttribute("registration_id",registration_id);
                        return mapping.findForward("success");
                
                
     }
    
      String msg="Record Updated Successfully for Institute :"+institute_name;
                    request.setAttribute("msg", msg);
                     request.setAttribute("registration_id",registration_id);
                        return mapping.findForward("success");

                 //System.out.println(msg);
                

        }
        catch(Exception e)
        {
        e.printStackTrace();
         String msg="Failure due to some Internal Errors";
        request.setAttribute("msg", msg);
       request.setAttribute("registration_id",registration_id);
         return mapping.findForward("failure");
        }



         




       

    }
}
