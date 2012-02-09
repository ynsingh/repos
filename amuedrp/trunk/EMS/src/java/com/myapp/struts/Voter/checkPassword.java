/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.hbm.AdminRegistration;
import com.myapp.struts.hbm.AdminRegistrationDAO;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.SetVoter;
import com.myapp.struts.instituteAdmin;
import com.myapp.struts.utility.PasswordEncruptionUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Edrp-04
 */
public class checkPassword extends org.apache.struts.action.Action {



    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer emp_ids = new StringBuffer();


HttpSession session=request.getSession();
session.removeAttribute("election_id");
        String election = request.getParameter("election");

        String passw = request.getParameter("pass");
        String staff_id=(String)session.getAttribute("staff_id");
        String inst_id=(String)session.getAttribute("institute_id");
System.out.println(election+"  "+staff_id+"  "+inst_id+" "+passw);

Login o=(Login)LoginDAO.searchRole(staff_id, inst_id);
if(o!=null){
if(o.getRole().equalsIgnoreCase("insti-admin,voter"))
{
    AdminRegistrationDAO admin=new AdminRegistrationDAO();
    AdminRegistration t=(AdminRegistration)admin.getAdminDeatilsByUserId1(o.getUserId());
    staff_id=t.getEnrollment();
           emp_ids.append("<messages>");
        emp_ids.append("<message>");
        if(passw!=null)
        {
        String passd = PasswordEncruptionUtility.password_encrupt(passw);

        SetVoter obj=ElectionDAO.searchVoter(election,inst_id,staff_id);

if(obj!=null)
{
        if(passd.equals(obj.getPassword()))
        {
            emp_ids.append("pass");
        }
        else
        {
            emp_ids.append("Password Mismatch!");
        }

 }
        emp_ids.append("</message>");
        emp_ids.append("</messages>");
        }
//System.out.println(emp_ids.length()+"............"+obj.getPassword()+"  "+passd);



    
}
else if(o.getRole().equalsIgnoreCase("insti-admin")){
 emp_ids.append("<messages1>");
        emp_ids.append("<message1>");
        emp_ids.append("Sorry You are not a Valid Voter");
         emp_ids.append("</message1>");
        emp_ids.append("</messages1>");

        response.setContentType("application/xml");
        response.getWriter().write(emp_ids.toString());
       
}
else{
       emp_ids.append("<messages>");
        emp_ids.append("<message>");
        if(passw!=null)
        {
        String passd = PasswordEncruptionUtility.password_encrupt(passw);

        SetVoter obj=ElectionDAO.searchVoter(election,inst_id,staff_id);

if(obj!=null)
{
        if(passd.equals(obj.getPassword()))
        {
            emp_ids.append("pass");
        }
        else
        {
            emp_ids.append("Password Mismatch!");
        }

 }else{
   emp_ids.append("You are not a Valid Voter");
 }
        emp_ids.append("</message>");
        emp_ids.append("</messages>");
        }
//System.out.println(emp_ids.length()+"............"+obj.getPassword()+"  "+passd);

       


    }
 

         
    }
System.out.println(emp_ids.toString());
if(emp_ids!=null)
        {response.setContentType("application/xml");
        response.getWriter().write(emp_ids.toString());
        }
return null;
    }
}
