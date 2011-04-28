/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import java.sql.*;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.utility.PasswordEncruptionUtility;

/**
 *
 * @author System Administrator
 */
public class UserPasswordAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    
    private String user_name;
    private String staff_id;
    private String password;
    private String library_id;
    private String login_id;
    private boolean result;
    int i;
    Connection con;
   
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

      

     
        password=PasswordEncruptionUtility.password_encrupt(password);

        Login  log=LoginDAO.searchRole(staff_id, library_id);
        log.setPassword(password);
        result=LoginDAO.update1(log);


        AdminRegistration admin=AdminRegistrationDAO.searchInstituteAdmin(staff_id, library_id);
        admin.setAdminPassword(password);
        result=AdminRegistrationDAO.update1(admin);



       if(result==true)
       {

            request.setAttribute("login_id", login_id);
            request.setAttribute("user_name", user_name);
            request.setAttribute("library_id", library_id);
            request.setAttribute("staff_id", staff_id);
            request.setAttribute("staff_name", user_name);
            return mapping.findForward("success");
        }

            return mapping.findForward("failure");
    }
}
