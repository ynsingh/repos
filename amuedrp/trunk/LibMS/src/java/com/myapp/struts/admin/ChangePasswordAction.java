/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import com.myapp.struts.utility.Email;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.utility.PasswordEncruptionUtility;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author System Administrator
 */
public class ChangePasswordAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    
    private String user_name;
    private String staff_id;
    private String password;
    private String library_id;
    private String login_id;
    private boolean result;
    int i;
    Email obj;
    private final ExecutorService executor=Executors.newFixedThreadPool(1);
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            CreateAccountActionForm caaction=(CreateAccountActionForm)form;


            login_id=caaction.getLogin_id();
            user_name=caaction.getUser_name();
            password=caaction.getPassword();
            staff_id=caaction.getStaff_id();
            HttpSession session=request.getSession();
            library_id=(String)session.getAttribute("library_id");

      

     
       
        StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);
        Login  log=LoginDAO.searchRole(staff_id, library_id);
 String path = servlet.getServletContext().getRealPath("/");
        obj=new Email(path,staffobj.getEmailId(),password,"Password Changed Successfully from LibMS Account","Your Password for LibMS Account is changed Successfully.\nYour New Password for libMS Account is:\nUser Id :"+login_id+"\nNew Password :"+password+"\n","Dear "+staffobj.getFirstName()+" "+staffobj.getLastName()+",\n","Thanks,\nWebAdmin\nLibMS");
        System.out.println((String)session.getAttribute("webmail")+" "+(String)session.getAttribute("webpass")+" "+obj);
        executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });


        password=PasswordEncruptionUtility.password_encrupt(password);
        log.setPassword(password);
        result=LoginDAO.update1(log);
        System.out.println(login_id+"................"+password);





       if(result==true)
       {

            request.setAttribute("login_id", login_id);
            request.setAttribute("user_name", user_name);
            request.setAttribute("library_id", library_id);
            request.setAttribute("staff_id", staff_id);
            request.setAttribute("staff_name", user_name);
            if(log.getRole().contains("admin")||log.getRole().contains("Admin"))
            return mapping.findForward("success");
            else
                return mapping.findForward("success1");
        }

            return mapping.findForward("failure");
    }
}
