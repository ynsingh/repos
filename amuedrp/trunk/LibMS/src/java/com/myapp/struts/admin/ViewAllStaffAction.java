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
public class ViewAllStaffAction extends org.apache.struts.action.Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        String login_role=(String)session.getAttribute("login_role");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");
        String library_id=(String)session.getAttribute("library_id");
            List<staffsubLib>  staffobj;

System.out.println(library_id+sublibrary_id);
        if(login_role.equalsIgnoreCase("insti-admin")==true)
        {
            staffobj=StaffDetailDAO.searchAllStaff(library_id);
        }
        else
        {
              staffobj=StaffDetailDAO.searchAllStaff(library_id,sublibrary_id);
              
        }
System.out.println(staffobj.size());
        if(!staffobj.isEmpty())
             {
            session.setAttribute("viewallstaff",staffobj);

            return mapping.findForward("success");
        }
        else
        {
            session.setAttribute("msg", "No Record Found");
            return mapping.findForward("fail");}



        }
}




        
     






