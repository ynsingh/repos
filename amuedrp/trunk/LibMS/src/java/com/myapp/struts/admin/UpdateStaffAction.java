/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;


/**
 *
 * @author System Administrator
 */
public class UpdateStaffAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String library_id;
    private String staff_id;
    private String first_name;
    private String last_name;
    private String contact_no;
    private String mobile_no;
    private String email_id;
    private String do_joining;
    private String do_releaving;
    private String date_of_birth;
    private String address1;
    private String city1;
    private String state1;
    private String country1;
    private String zip1;
    private String address2;
    private String city2;
    private String state2;
    private String country2;
    private String zip2;
    private String father_name;
    private String gender;
    private String courtesy;
    private String sublibrary_id;
    private String button;
    private boolean result;
         private byte[] imagefile;
         private String role;
LoginDAO logindao;
    int i=0;
    Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
       logindao=new LoginDAO();
   
        SubLibraryDAO sublibdao=new SubLibraryDAO();
        PrivilegeDAO privdao=new PrivilegeDAO();
        AcqPrivilegeDAO acqprivdao=new AcqPrivilegeDAO();
        SerPrivilegeDAO serprivdao=new SerPrivilegeDAO();
        CirPrivilegeDAO cirprivdao=new CirPrivilegeDAO();
        CatPrivilegeDAO catprivdao=new CatPrivilegeDAO();
        StaffDetailDAO staffdao=new StaffDetailDAO();
        CreatePrivilege cirprivobj=new CreatePrivilege();
        AdminRegistrationDAO admindao=new AdminRegistrationDAO();


        HttpSession session=request.getSession();

       try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        library_id=(String)session.getAttribute("library_id");

       

        StaffDetailActionForm staff=(StaffDetailActionForm)form;

      
    sublibrary_id= staff.getSublibrary_id();


        staff_id=staff.getEmployee_id();

        first_name=staff.getFirst_name();
        last_name=staff.getLast_name();
        contact_no=staff.getContact_no();
        mobile_no=staff.getMobile_no();
        email_id=staff.getEmail_id();
        do_joining=staff.getDo_joining();
        date_of_birth=staff.getDate_of_birth();
        do_releaving=staff.getDo_releaving();
        address1=staff.getAddress1();
        city1=staff.getCity1();
        state1=staff.getState1();
        country1=staff.getCountry1();
        zip1=staff.getZip1();
        address2=staff.getAddress2();
        city2=staff.getCity2();
        state2=staff.getState2();
        country2=staff.getCountry2();
        zip2=staff.getZip2();
        father_name=staff.getFather_name();
        gender=staff.getGender();
        if(staff.getCourtesy()!=null)
        courtesy=staff.getCourtesy();
        button=staff.getButton();
       
      System.out.println("Button Vaule="+button);
       String mainlib=(String)session.getAttribute("mainsublibrary");

         if(button.equals("Update"))
         {

        String  id1=(String)session.getAttribute("staff_id");



        String id="admin."+library_id;   //cannot update Institute admin ID if u are not insti-admin


        if(staff_id.equals(id) && staff_id.equals(id1)==false)
         {
              StaffDetail staffobj=staffdao.searchStaffId(staff_id,library_id);

                     if(staffobj!=null)
                     {
                    first_name=staffobj.getFirstName()+" "+staffobj.getLastName();

                     }
               request.setAttribute("user_id", email_id);
              request.setAttribute("staff_name",first_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);

               // request.setAttribute("msg", "You Cannot Update Institute Admin");
                 request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error1"));
                  return mapping.findForward("success");
         }

      
             id=(String)session.getAttribute("staff_id");
         String  login_role=(String)session.getAttribute("login_role");    //cannot update  same role person if it is not u

            Login logobj=logindao.searchRole(staff_id,library_id);

         
  if(logobj!=null)
            {
                if(logobj.getRole().equalsIgnoreCase(login_role) && logobj.getId().getStaffId().equalsIgnoreCase(id)==false)
                {

                request.setAttribute("user_id", email_id);

                  request.setAttribute("staff_name",first_name+" "+last_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                
                //request.setAttribute("msg", "You Cannot Update Same role Person Name :");
                request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error2"));
                  return mapping.findForward("success");
                }
            }



System.out.println(sublibrary_id+"/////////////////////////////////"+staff_id);





       /* Use to Update Staff Entry related to Library Table & SubLibrary Table */
            StaffDetail staffobj=staffdao.searchStaffId(staff_id,library_id);




            if(courtesy.equals("Select")==false)
            staffobj.setTitle(courtesy);
            staffobj.setSublibraryId(sublibrary_id);

            staffobj.setFirstName(first_name);
            staffobj.setLastName(last_name);
            staffobj.setEmailId(email_id);
            if(do_joining.equals("")==false)
            staffobj.setDateJoining(do_joining);
            else
            staffobj.setDateJoining(null);
            if(do_releaving.equals("")==false)
            staffobj.setDateReleaving(do_releaving);
            else
            staffobj.setDateReleaving(null);
            if(date_of_birth.equals("")==false)
            staffobj.setDateOfBirth(date_of_birth);
            else
            staffobj.setDateOfBirth(null);
            staffobj.setGender(gender);
            staffobj.setMobileNo(mobile_no);
            staffobj.setContactNo(contact_no);
            staffobj.setCity1(city1);
            staffobj.setState1(state1);
            staffobj.setZip1(zip1);
            staffobj.setCountry1(country1);
            staffobj.setFatherName(father_name);
            staffobj.setAddress1(address1);
            staffobj.setAddress2(address2);
            staffobj.setCity2(city2);
            staffobj.setState2(state2);
            staffobj.setCountry2(country2);
            staffobj.setZip2(zip2);
            // if (form1!=null)
                 staffobj.setStaffImage(null);





             result=staffdao.update1(staffobj);
                if(result==false)
                {
                     request.setAttribute("staff_id",staff_id );
                  request.setAttribute("staff_name",first_name+" "+last_name );
                   // request.setAttribute("msg", "Sorry Record Updation UnSuceessfully For ");
                   request.setAttribute("msg",resource.getString("admin.UpdateStaffAction.error3"));
                      return mapping.findForward("success");

                }

      /* Use to Update Login Entry related to Library Table & SubLibrary Table and Staff Table */
            logobj=logindao.searchStaffId(staff_id,library_id);
            
                
                if(logobj!=null)
                {
                logobj.setUserName(first_name+" "+last_name);
                logobj.setSublibraryId(sublibrary_id);

               if(sublibrary_id.equalsIgnoreCase(mainlib))
               {
                if(logobj.getRole().equalsIgnoreCase("dept-admin"))
                    logobj.setRole("staff");
                if(logobj.getRole().equalsIgnoreCase("dept-staff"))
                    logobj.setRole("staff");


               }else{
                 if(logobj.getRole().equalsIgnoreCase("admin"))
                     logobj.setRole("dept-staff");
                if(logobj.getRole().equalsIgnoreCase("staff"))
                    logobj.setRole("dept-staff");



               }






                    result=logindao.update1(logobj);
                if(result==false)
                {
                    request.setAttribute("staff_id",staff_id );
                  request.setAttribute("staff_name",first_name+" "+last_name );
                    //request.setAttribute("msg", "Sorry Record Updation UnSuceessfully For ");
                    request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error3"));

                    return mapping.findForward("success");

                }
                }

 /* Use to Update Privilege Entry related to Library Table & SubLibrary Table and Staff Table */
            
            Login log=logindao.searchRole(staff_id, library_id);
            if(log!=null)
            {  role=log.getRole();

                   result =privdao.DeleteStaff(staff_id,library_id);
                   result=acqprivdao.DeleteStaff(staff_id, library_id);
                   result=catprivdao.DeleteStaff(staff_id, library_id);
                   result=cirprivdao.DeleteStaff(staff_id, library_id);
                   result=serprivdao.DeleteStaff(staff_id, library_id);

                        if(result==true)
                        {

                            if(role.equals("staff"))
                                    result=cirprivobj.assignStaffPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equals("admin"))
                                    result=cirprivobj.assignAdminPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equals("dept-admin"))
                                   result=cirprivobj.assignDepartmentalAdminPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equals("dept-staff"))
                                   result=cirprivobj.assignDepartmentalStaffPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equalsIgnoreCase("insti-admin"))
                                   result=cirprivobj.assignAdminPrivilege(staff_id, library_id,sublibrary_id);
                        }

            }
      
                //admin table updated if staff is admin.library_id
            //    if(staff_id.equals("admin."+library_id))
              //  {
       /* Use to Update AdminRegistration Table */

            AdminRegistration adminobj=admindao.searchInstituteAdmin(staff_id,library_id);
System.out.println("Admin..........."+adminobj);
if(adminobj!=null)
{
            if(city1!=null)
            adminobj.setCity(city1);
            if(state1!=null)
            adminobj.setState(state1);
            if(country1!=null)
            adminobj.setCountry(country1);
            if(zip1!=null)
            adminobj.setPin(zip1);
            if(contact_no!=null)
            adminobj.setLandLineNo(contact_no);
            if(mobile_no!=null)
            adminobj.setMobileNo(mobile_no);
            if(first_name!=null)
            adminobj.setAdminFname(first_name);
            if(last_name!=null)
            adminobj.setAdminLname(last_name);
            if(email_id!=null)
            adminobj.setAdminEmail(email_id);
            if(courtesy!=null)
            adminobj.setCourtesy(courtesy);
            if(gender!=null)
            adminobj.setGender(gender);

            

             result= admindao.update1(adminobj);

            if(result==false)
                {
                    request.setAttribute("staff_id",staff_id );
                  request.setAttribute("staff_name",first_name+" "+last_name );
                  //  request.setAttribute("msg", "Sorry Record Updation UnSuceessfully For ");
                    request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error3"));

                 return mapping.findForward("success");
                }else{
                request.setAttribute("staff_id",staff_id );
                  request.setAttribute("staff_name",first_name+" "+last_name );
                 //   request.setAttribute("msg", "Record Suceessfully Updated For ");
                  request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error10"));

                 return mapping.findForward("success");


                }

}
            
      
         
                request.setAttribute("staff_id",staff_id );
                  request.setAttribute("staff_name",first_name+" "+last_name );
                 //   request.setAttribute("msg", "Record Suceessfully Updated For ");
                  request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error10"));

                 return mapping.findForward("success");



         }




         if(button.equals("Confirm"))
         {
        
    String id="admin."+library_id;   //get Institute admin ID



                if(staff_id.equals(id))
                {
                      request.setAttribute("user_id", email_id);
                      request.setAttribute("staff_name",first_name+" "+last_name );
                      request.setAttribute("library_id", library_id);
                      request.setAttribute("staff_id", staff_id);
                     // request.setAttribute("msg", "You Cannot Delete Institute Admin ");
                       request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error4"));
                      return mapping.findForward("success");
                  }

        id=(String)session.getAttribute("staff_id");  //cannot delete own account
            if(staff_id.equals(id))
            {


                     request.setAttribute("staff_name",first_name+" "+last_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                //request.setAttribute("msg", "You Cannot Delete Your Own Account for Staff Name ");
                request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error5"));
                  return mapping.findForward("success");

            }
      String  login_role=(String)session.getAttribute("login_role");    //cannot create co-admin

      Login  logobj=logindao.searchRole(staff_id,library_id);
         

            if(logobj!=null)
            {
                if(logobj.getRole().equalsIgnoreCase(login_role))
                {

               request.setAttribute("staff_name",first_name+" "+last_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);

               // request.setAttribute("msg", "You Cannot Delete Same Role Staff Name :");
                 request.setAttribute("msg",resource.getString("admin.UpdateStaffAction.error6") );
                  return mapping.findForward("success");
                }
            }

System.out.println(sublibrary_id);
           
                  result=logindao.DeleteLogin(staff_id,library_id,sublibrary_id);


           
             if(result==true)
             {
                    

                result=staffdao.DeleteStaff(staff_id,library_id,sublibrary_id);
                 if(result==true)
                    {
                    request.setAttribute("staff_id",staff_id);
                    request.setAttribute("staff_name",first_name+" "+last_name );
                  //  request.setAttribute("msg", "Staff & Account Details Are Deleted Successfully for ");
                      request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error7"));
                    return mapping.findForward("success");
                    }
                    else
                    {
                    request.setAttribute("staff_id",staff_id );
                   request.setAttribute("staff_name",first_name+" "+last_name );
                    //request.setAttribute("msg", "Staff Record are not Sucessfully Deleted for" ));
                    request.setAttribute("msg", resource.getString("admin.UpdateStaffAction.error8"));
                    return mapping.findForward("success");
                    }
              }
              else
              {
               request.setAttribute("staff_id",staff_id );
               request.setAttribute("staff_name",first_name+" "+last_name );
             //  request.setAttribute("msg", "Sorry Record Deletion UnSuceessfully For ");
                 request.setAttribute("msg",  resource.getString("admin.UpdateStaffAction.error9"));
              return mapping.findForward("success");
              }
           
       //////////////////////////////////////////////////
                
          }
        return null;
    }
}
        

      
