/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import java.util.List;
/**
 *
 * @author Dushyant
 */
public class ViewAllStaffAccountAction extends org.apache.struts.action.Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
  HttpSession session=request.getSession();
        String login_role=(String)session.getAttribute("login_role");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");
        String library_id=(String)session.getAttribute("library_id");
           List<AccountSubLibrary>  loginobj;
System.out.println(login_role+library_id);
        if(login_role.equalsIgnoreCase("insti-admin")==true)
        {
            loginobj=LoginDAO.searchAllStaffListAccount(library_id);
        }
        else
        {
              loginobj=LoginDAO.searchAllStaffListAccount(library_id,sublibrary_id);
        }
System.out.println(loginobj.size());
        if(!loginobj.isEmpty())
             {
            request.setAttribute("viewallaccount",loginobj);

            return mapping.findForward("success");
        }
        else
        {
            session.setAttribute("msg", "No Record Found");
            return mapping.findForward("fail");}




        }
}




        
     






