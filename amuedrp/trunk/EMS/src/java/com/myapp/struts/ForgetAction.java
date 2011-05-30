/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;
import com.myapp.struts.admin.SecurityActionForm;
import java.sql.*;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.LoginDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.Email;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import com.myapp.struts.utility.RandomPassword;
/**
 *
 * @author System Administrator
 */
public class ForgetAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    String institute_id;
     private final ExecutorService executor=Executors.newFixedThreadPool(1);
     String staff_id;
    String user_id;
    String question;
    String ans;
    private String password;
    private String password1;
    Email obj;
    private boolean result;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SecurityActionForm login=(SecurityActionForm)form;

            HttpSession session=request.getSession(true);
            institute_id=(String)session.getAttribute("institute_id");

           staff_id=login.getStaff_id();
           user_id=login.getUser_id();
           question=login.getQuestion();
           ans=login.getAns();

            System.out.println(staff_id+""+ans+institute_id);
            Login logobj=LoginDAO.searchAns(staff_id,institute_id,ans);
        

      if(logobj!=null)
      {

            
            request.setAttribute("question",question);
            request.setAttribute("staff_id",staff_id);
            request.setAttribute("ans",ans);
           
            request.setAttribute("msg","The Password is reset and send to your MailID");

            /*Password Generate and Reset It*/
                 password= RandomPassword.getRandomString(10);
                 System.out.println(password);

                Login  log=LoginDAO.searchRole(staff_id,institute_id);
                password1=PasswordEncruptionUtility.password_encrupt(password);
                log.setPassword(password1);
                result=LoginDAO.update1(log);

       if(result==true)
       {
                

                StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, institute_id);

             obj=new Email(staffobj.getEmailId(),password,"Forget Password :Password Reset Successfully from LibMS","User Id="+user_id+" Your Password for LibMS Login is="+password);
            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });





                return mapping.findForward("success");
       }else{
        request.setAttribute("error","UnExpected Error Encountered");
            return mapping.findForward("failure");

       }
      }
      else
      {
          
          
            request.setAttribute("error","Answer not correct");
            return mapping.findForward("failure");
      }
          
    }
}
