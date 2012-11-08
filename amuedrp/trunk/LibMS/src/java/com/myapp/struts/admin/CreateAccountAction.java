/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.AdminDAO.*;
import com.myapp.struts.hbm.*;
//import javax.mail.*;
//import javax.mail.internet.*;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.*;
import com.myapp.struts.utility.Email;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 *
 * @author Dushyant
 */
public class CreateAccountAction extends org.apache.struts.action.Action {
    
   
  private final ExecutorService executor=Executors.newFixedThreadPool(1);
  Email obj;
    private String user_name;
    private String staff_id;
    private String password;
    private String library_id;
    private String login_id;
    private String sublibrary_id;
    private String role;
    private boolean result;
    LoginDAO logindao;
    StaffDetailDAO staffdao=new StaffDetailDAO();
    int i;
    private String password1;
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
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

      CreateAccountActionForm accountobj=(CreateAccountActionForm)form;
      login_id=accountobj.getLogin_id();
     
      user_name=accountobj.getUser_name();
      password=accountobj.getPassword();
      staff_id=accountobj.getStaff_id();
      role=accountobj.getRole();
      sublibrary_id=accountobj.getSublibrary_id();

      logindao=new LoginDAO();
    
      library_id=(String)session.getAttribute("library_id");
     
        CreatePrivilege cirpriv=new CreatePrivilege();
     

      request.setAttribute("user_id", login_id);
      request.setAttribute("user_name", user_name);
      request.setAttribute("staff_id", staff_id);
      request.setAttribute("role", role);
    
      request.setAttribute("library_id",library_id);

                        Login log=logindao.searchLoginID(login_id);
                        if(log!=null)
                        {
                            //String msg="Duplicate Login Id ";
                         String msg=resource.getString("admin.createaccountaction.duplicate");
                            request.setAttribute("msg1", msg);
                             return mapping.findForward("duplicate");

                        }

 



  /* Use to Update Staff Entry related to Library Table & SubLibrary Table if sublibrary is changed */
            StaffDetail staffobj=staffdao.searchStaffId(staff_id,library_id);


                    staffobj.setSublibraryId(sublibrary_id);

             result=staffdao.update1(staffobj);
                if(result==false)
                {
                   // String msg="Request for registration failure due to some error";
                    String msg=resource.getString("admin.StaffDetailAction.error");
                               request.setAttribute("msg1", msg);
                               return mapping.findForward("error");

                }


              staffobj=staffdao.searchStaffId(staff_id,library_id);
              LoginId loginIdobj=new LoginId(staff_id, library_id);
              Login logobj=new Login(loginIdobj,staffobj,login_id) ;

              /*Password Generate and Reset It*/
                 password= RandomPassword.getRandomString(10);
                 System.out.println(password+"................"+staffobj);


              password1=PasswordEncruptionUtility.password_encrupt(password);

                logobj.setPassword( password1);
                logobj.setRole(role);
                logobj.setSublibraryId(sublibrary_id);
                logobj.setUserName(user_name);
                logobj.setQuestion("@");
                  result=logindao.insert1(logobj);
                if(result==false)
                {



                    //String msg="Request for registration failure due to some error";
                      String msg=resource.getString("admin.StaffDetailAction.error");
                    request.setAttribute("msg1", msg);
                    return mapping.findForward("error");

                }
                else{


 
             obj=new Email(staffobj.getEmailId(),password,"Your Account on LibMS Created Successfully","Dear "+staffobj.getFirstName()+" "+staffobj.getLastName()+",\nYour Account of LibMS has been Created Successfully for LibMS.\nUser Id="+login_id+"\nPassword:"+password+"\nUser Role:"+role+"Thanks,"+session.getAttribute("username").toString()+"\nInstitute Admin");

            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });




                           if(role.equals("staff"))
                                    result=cirpriv.assignStaffPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equals("admin"))
                                    result=cirpriv.assignAdminPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equals("dept-admin"))
                                   result=cirpriv.assignDepartmentalAdminPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equals("dept-staff"))
                                   result=cirpriv.assignDepartmentalStaffPrivilege(staff_id, library_id,sublibrary_id);


                           if(result==false)
                           {
                               //String msg="Request for registration failure due to some error";
                                 String msg=resource.getString("admin.StaffDetailAction.error");
                               request.setAttribute("msg1", msg);
                               return mapping.findForward("error");
                          }
                          

                           return mapping.findForward("success");
                }

       
       

         

    }
    }
    

