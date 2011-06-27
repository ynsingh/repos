/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;


import com.myapp.struts.hbm.AdminRegistration;
import com.myapp.struts.AdminDAO.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.List;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Dushyant
 */
public class BlockLibraryAction extends org.apache.struts.action.Action {
    
int i;
private String working_status;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {

        AdminRegistration adminreg= new AdminRegistration();
        working_status=adminreg.getWorkingStatus();
String t=request.getParameter("id");
System.out.println(t);
        i=Integer.parseInt(t);
        HttpSession session=request.getSession();


        AdminRegistrationDAO admindao = new AdminRegistrationDAO();
        List rst= admindao.getAdminInstituteDetailsById(i);

        if(rst!=null)
        {
            session.setAttribute("blocked_resultset", rst);
            System.out.println("here");
            //request.setAttribute("msg", "Status changed successfully: "+working_status);
            return mapping.findForward("success");
        }
        else
        {
        request.setAttribute("msg", "Some Error Encountered");
        return mapping.findForward("success");
        }
        
    }
}
