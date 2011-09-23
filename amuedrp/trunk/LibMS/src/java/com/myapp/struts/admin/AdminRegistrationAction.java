/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import com.myapp.struts.MyConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.*;
import com.myapp.struts.utility.Email;






/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Admin Registration Activity
 */
public class AdminRegistrationAction extends org.apache.struts.action.Action {


 private final ExecutorService executor=Executors.newFixedThreadPool(1);
  Email obj;


private String login_id;
boolean result;
AdminRegistration adminobj;
 Connection con1;

 Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";




    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {



             UserLog.setPath(request.getContextPath());

 HttpSession session1 =request.getSession();

   try{

        locale1=(String)session1.getAttribute("locale");
    if(session1.getAttribute("locale")!=null)
    {
        locale1 = (String)session1.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);



        AdminRegistrationActionForm adminRegistrationActionForm=(AdminRegistrationActionForm)form;
        
        login_id=adminRegistrationActionForm.getLogin_id();
      
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

           // request.setAttribute("msg", "User ID is Duplicate");
             request.setAttribute("msg",resource.getString("admin.adminregistrationaction.error1"));
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
            
                adminobj.setLibraryName(adminRegistrationActionForm.getLibrary_name());
                adminobj.setCourtesy(adminRegistrationActionForm.getCourtesy());
                adminobj.setRegistrationId(AdminRegistrationDAO.maxRegistrationID());
                adminobj.setGender(adminRegistrationActionForm.getGender());
                adminobj.setLoginId(adminRegistrationActionForm.getLogin_id());
                adminobj.setWorkingStatus("OK");
                adminobj.setStatus("NotRegistered");
         
System.out.println(adminobj);
                 result=AdminRegistrationDAO.insert1(adminobj);
                if(result==false)
                {
                    String msg="Request for registration failure due to some error";

                    //request.setAttribute("msg1", msg);
                    request.setAttribute("msg1",resource.getString("admin.adminregistrationaction.error2"));
                    return mapping.findForward("failure");
                }



//       String    path = servlet.getServletContext().getRealPath("/");
//path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
//System.out.println(path+"................");
//             obj=new Email(path,"amuedrp@gmail.com","","New Request of Library Registration from "+adminRegistrationActionForm.getLibrary_name(),"You Have a Fresh Request for Library Registration Received from \n Library Name :"+adminRegistrationActionForm.getLibrary_name()+" with"+"\n"+"User ID :"+adminRegistrationActionForm.getLogin_id()+"\n Please Check It.","Dear WebAdmin, ","Thanks");
//            executor.submit(new Runnable() {
//
//                public void run() {
//                    obj.send();
//                }
//            });



                String msg="Request for Library Registration accepted successfully" ;
  
 String path = servlet.getServletContext().getRealPath("/");
 
             obj=new Email((String)session.getAttribute("webmail"),(String)session.getAttribute("webpass"),path,adminRegistrationActionForm.getAdmin_email(),"",msg,"Your request for Library registration has been accepted Successfully.\nShortly you will get another mail regrading approval of your request.\n","\n\nDear "+adminRegistrationActionForm.getAdmin_fname()+" "+adminRegistrationActionForm.getAdmin_lname()+",\n","Thanks\nWebAdmin\nLibMS");
            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });
              
                //request.setAttribute("registration_msg", msg);
                 request.setAttribute("registration_msg",resource.getString("admin.adminregistrationaction.error3"));
                return mapping.findForward("success");
           }

        }
        catch(Exception e)
        {
      System.out.println(e);
            String msg="Request for registration failure due to some error";
            //request.setAttribute("msg1", msg);
            request.setAttribute("msg1", resource.getString("admin.adminregistrationaction.error2"));

            session.invalidate();
            System.out.println(e.toString());
            return mapping.findForward("failure");
        }

    }
}
