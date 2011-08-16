/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import  com.myapp.struts.*;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AdminDAO.*;
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
private boolean result;

String msg;
int i=0;
    /* forward name="success" path="" */
Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";


   
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
        library_name=adminRegistrationActionForm.getLibrary_name();
        
        institute_website=adminRegistrationActionForm.getInstitute_website();
        admin_fname=adminRegistrationActionForm.getAdmin_fname();
        admin_lname=adminRegistrationActionForm.getAdmin_lname();
        admin_designation=adminRegistrationActionForm.getAdmin_designation();
        admin_email=adminRegistrationActionForm.getAdmin_email();
        admin_password=adminRegistrationActionForm.getAdmin_password();
        library_name=adminRegistrationActionForm.getLibrary_name();
        courtesy=adminRegistrationActionForm.getCourtesy();
        gender=adminRegistrationActionForm.getGender();
        institute_id=adminRegistrationActionForm.getLibrary_id();
        user_id=adminRegistrationActionForm.getLogin_id();


        System.out.println("user_id="+user_id);
      
        HttpSession session=request.getSession();
        LibraryDAO institutedao = new LibraryDAO();
        AdminRegistrationDAO admindao = new AdminRegistrationDAO();
        LoginDAO logindao = new LoginDAO();
        StaffDetailDAO staffdetaildao = new StaffDetailDAO();

        try{
              locale1=(String)session.getAttribute("locale");

              if(session.getAttribute("locale")!=null)
              {
                locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
              }
              else locale1="en";
            }catch(Exception e){locale1="en";}
             locale = new Locale(locale1);
             if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
             else{ rtl="RTL";page=false;align="right";}
              ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);


         AdminRegistration adminobj = AdminRegistrationDAO.searchInstituteAdmin(user_id);
         if(adminobj.getStatus().equals("NotRegistered"))
         {

          adminobj.setInstituteName(institute_name);
          adminobj.setAbbreviatedName(abbreviated_name);
          adminobj.setInstituteAddress(institute_address);
          adminobj.setCity(city);
          adminobj.setState(state);
          adminobj.setCountry(country);
          adminobj.setPin(pin);
          adminobj.setLandLineNo(land_line_no);
          adminobj.setMobileNo(mobile_no);

          adminobj.setTypeOfInstitute(type_of_institute);
          adminobj.setWebsite(institute_website);
          adminobj.setAdminFname(admin_fname);
          adminobj.setAdminLname(admin_lname);
          adminobj.setLibraryName(library_name);
          adminobj.setAdminDesignation(admin_designation);
          adminobj.setAdminEmail(admin_email);

          adminobj.setCourtesy(courtesy);
          adminobj.setGender(gender);
          adminobj.setStatus("NotRegistered");




          result= AdminRegistrationDAO.update1(adminobj);
if(result==true){
         System.out.println("Here");
         System.out.println("@@@@@@@@@"+locale);
      if(locale1.equals("en")||locale1.equals("ar")||locale1.equals("ur")) {
          System.out.println("###############################33");
        String msg=resource.getString("admin.updateadmin.msg")+" "+institute_name;
        request.setAttribute("msg", msg);}
      else
      {  System.out.println("###############################33#############");
         String msg=institute_name+" "+resource.getString("admin.updateadmin.msg");
        request.setAttribute("msg", msg);}
     
      request.setAttribute("registration_id",registration_id);
      return mapping.findForward("success");
}
         }





        try
        {
               Library rs2 = institutedao.getInstituteDetails(institute_id);

              rs2.setLibraryName(library_name);
              LibraryDAO libdao=new LibraryDAO();

              libdao.update(rs2);


            

            
            List rs= admindao.getAdminDeatilsByUserId(user_id);
        
            staff_id = "admin."+institute_id;

            List rs1=staffdetaildao.getStaffDetails(staff_id, institute_id);
            Iterator it1 = rs.iterator();
            Iterator it2 = rs1.iterator();

  
            List rs3=admindao.getAdminDeatilsById(registration_id);
            Iterator it3 = rs3.iterator();
            int count = 0;
            if(it3.hasNext())
            {
            AdminRegistration adminReg = (AdminRegistration) rs3.get(count++);
            status=adminReg.getStatus();
            working_status = adminReg.getWorkingStatus();
          
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
          adminReg.setStaffId(staff_id);
          
          adminReg.setTypeOfInstitute(type_of_institute);
          adminReg.setWebsite(institute_website);
          adminReg.setAdminFname(admin_fname);
          adminReg.setAdminLname(admin_lname);
          adminReg.setLibraryName(library_name);
          adminReg.setAdminDesignation(admin_designation);
          adminReg.setAdminEmail(admin_email);
          adminReg.setAdminPassword(admin_password);
          adminReg.setCourtesy(courtesy);
          adminReg.setGender(gender);
          adminReg.setStatus(status);
          adminReg.setWorkingStatus(working_status);
          adminReg.setLoginId(user_id);
          adminReg.setLibraryId(institute_id);
          
            admindao.update(adminReg);
            System.out.println("here");
if(status.equalsIgnoreCase("Registered"))
       {

                      
                          
            StaffDetail staffdetail =StaffDetailDAO.searchStaffId(staff_id, institute_id);
            
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
            

            result=StaffDetailDAO.update1(staffdetail);

         System.out.println(user_id+"............");


                    Login login =LoginDAO.searchLoginID(user_id);
                    
                    login.setUserName(admin_fname + " "+admin_lname);
                    login.setPassword(admin_password);
                    
                    result=LoginDAO.update1(login);
                    
                     if(result==true)
                     {  System.out.println("Here");
                        System.out.println("@@@@@@@@@"+locale);
                         if(locale1.equals("en")||locale1.equals("ar")||locale1.equals("ur")) {
                            msg=resource.getString("admin.updateadmin.msg")+" "+institute_name;
                           }
                         else
                         {
                            msg=institute_name+" "+resource.getString("admin.updateadmin.msg");
                           }
                         request.setAttribute("msg", msg);
                         request.setAttribute("registration_id",registration_id);
                         return mapping.findForward("success");
                     }
                
     }

      

                 //System.out.println(msg);
                

        }
        catch(Exception e)
        {
        e.printStackTrace();
         String msg=resource.getString("admin.updateadmin.msg1");
        request.setAttribute("msg", msg);
       request.setAttribute("registration_id",registration_id);
         return mapping.findForward("failure");
        }



         
return null;



       

    }
}
