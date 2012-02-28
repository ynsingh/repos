/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import  com.myapp.struts.*;
import com.myapp.struts.hbm.*;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpSession;
import com.myapp.struts.utility.RandomPassword;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.*;
import com.myapp.struts.utility.Email;
/**
 *
 * @author Dushyant
 */
public class ResetPasswordAction extends org.apache.struts.action.Action {
    String user_id1;
    String user_id2;
    String password1;
    String password4;
    ResultSet rst1,rst2;
    String role;
    String staff_id;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    
      private final ExecutorService executor=Executors.newFixedThreadPool(1);
  Email obj1;
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
try
{
        SuperAdminActionForm admin=(SuperAdminActionForm)form;
        Login login= new Login();
        user_id1=admin.getUser_id1().trim();

        HttpSession session=request.getSession();
        String institute_id=(String)session.getAttribute("institute_id");
String role=(String)session.getAttribute("login_role");

if(role.equalsIgnoreCase("Superadmin"))
{

    LoginDAO logindao= new LoginDAO();
    System.out.println("user_id= "+user_id1);
    Login rs = (Login)logindao.getUserId(user_id1);
    if(rs!=null)
        {
    
    /*Password Generate and Reset It*/
    String    password= admin.getPassword1();

        String password1 = PasswordEncruptionUtility.password_encrupt(password);
        
        if(rs.getRole().equalsIgnoreCase("Voter"))
        {

        rs.setPassword(password1);
        logindao.update(rs);

        InstituteDAO obj=new InstituteDAO();
        VoterRegistration voter=obj.getVoterDetails(rs.getStaffDetail().getId().getStaffId(),rs.getStaffDetail().getId().getInstituteId());


        String path = servlet.getServletContext().getRealPath("/");
       

            obj1=new Email(path,voter.getEmail(),password,"Renewal of Password From EMS","Your Password Successfully update with User Id "+rs.getUserId()+" Your Password for EMS Login is "+password);
         executor.submit(new Runnable() {

                public void run() {
                    obj1.send();
                }
            });
      request.setAttribute("msg","Password Succesfully Updated & Mail Sent Successfully");
      return mapping.findForward("success");
        }
        else if(rs.getRole().equalsIgnoreCase("Election Manager")||rs.getRole().equalsIgnoreCase("Election Manager,voter")||rs.getRole().equalsIgnoreCase("Insti-admin") || rs.getRole().equalsIgnoreCase("Insti-admin,voter"))
        {

             rs.setPassword(password1);
        logindao.update(rs);

               StaffDetailDAO obj=new StaffDetailDAO();
        StaffDetail staff=obj.getStaffDetails1(rs.getStaffDetail().getId().getStaffId(),rs.getStaffDetail().getId().getInstituteId());

 
        String path = servlet.getServletContext().getRealPath("/");
            obj1=new Email(path,staff.getEmailId(),password,"Renewal of Password From EMS","Your Password Successfully update with User Id "+rs.getUserId()+" Your Password for EMS Login is "+password);
         executor.submit(new Runnable() {

                public void run() {
                    obj1.send();
                }
            });

      request.setAttribute("msg","Password Succesfully Updated & Mail Sent Successfully");
      return mapping.findForward("success");

        }
        else{
            
         request.setAttribute("msg1","As a SuperAdmin You Need to Reset Your Password from Manage SuperAdmin Section Only");
      return mapping.findForward("success");
        }
}else{
 request.setAttribute("msg1","User Id Not Found");
      return mapping.findForward("success");

}
   }
   else if (role.equalsIgnoreCase("Election Manager") || role.equalsIgnoreCase("Election Manager,voter"))
   {

   LoginDAO logindao= new LoginDAO();

System.out.println("user_id= "+user_id1);

Login rs = (Login)logindao.getUserDetails(user_id1,institute_id);
if(rs!=null){
if(rs.getRole().equalsIgnoreCase("voter"))
{
    String    password= admin.getPassword1();

        String password1 = PasswordEncruptionUtility.password_encrupt(password);

        if(rs.getRole().equalsIgnoreCase("voter"))
        {

        rs.setPassword(password1);
        logindao.update(rs);

        InstituteDAO obj=new InstituteDAO();
        VoterRegistration voter=obj.getVoterDetails(rs.getStaffDetail().getId().getStaffId(),institute_id);


        String path = servlet.getServletContext().getRealPath("/");


            obj1=new Email(path,voter.getEmail(),password,"Renewal of Password From EMS","Your Password Successfully update with User Id "+rs.getUserId()+" Your Password for EMS Login is "+password);
         executor.submit(new Runnable() {

                public void run() {
                    obj1.send();
                }
            });

 request.setAttribute("msg","Password Succesfully Updated & Mail Sent Successfully");
      return mapping.findForward("success");
}
   }
else{

  request.setAttribute("msg1","As a ElectionManager You Can Reset Voter Password Only");
  return mapping.findForward("success");
}
}
else{
  request.setAttribute("msg1","User ID Not Found");
  return mapping.findForward("success");
}
   }
   else if(role.equalsIgnoreCase("Insti-Admin") ||role.equalsIgnoreCase("Insti-Admin,voter")  )
   {
    LoginDAO logindao= new LoginDAO();

System.out.println("user_id= "+user_id1);

Login rs = (Login)logindao.getUserDetails(user_id1,institute_id);
if(rs!=null)
{
if(rs.getRole().equalsIgnoreCase("Election Manager")|| rs.getRole().equalsIgnoreCase("Election Manager,voter"))
{
    String    password= admin.getPassword1();

        String password1 = PasswordEncruptionUtility.password_encrupt(password);

        if(rs.getRole().equalsIgnoreCase("Election Manager")|| rs.getRole().equalsIgnoreCase("Election Manager,voter"))
        {

           rs.setPassword(password1);
        logindao.update(rs);

               StaffDetailDAO obj=new StaffDetailDAO();
        StaffDetail staff=obj.getStaffDetails1(rs.getStaffDetail().getId().getStaffId(),rs.getStaffDetail().getId().getInstituteId());


        String path = servlet.getServletContext().getRealPath("/");
            obj1=new Email(path,staff.getEmailId(),password,"Renewal of Password From EMS","Your Password Successfully update with User Id "+rs.getUserId()+" Your Password for EMS Login is "+password);
         executor.submit(new Runnable() {

                public void run() {
                    obj1.send();
                }
            });

 request.setAttribute("msg","Password Succesfully Updated & Mail Sent Successfully");
      return mapping.findForward("success");
}
   }
else{
  request.setAttribute("msg1","As a Institute Admin You Can Reset Election Manager Password Only");
  return mapping.findForward("success");
}
}
else{

  request.setAttribute("msg1","User ID Not Found");
  return mapping.findForward("success");

}
   }
     }catch(Exception e)
     {
         e.printStackTrace();
       request.setAttribute("msg1","User Id Not Found");
      return mapping.findForward("success");

     }
return null;
     
          }
}
