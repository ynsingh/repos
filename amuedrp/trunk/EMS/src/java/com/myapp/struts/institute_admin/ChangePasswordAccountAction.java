/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import com.myapp.struts.hbm.AdminRegistrationDAO;
import com.myapp.struts.hbm.Login;
import java.util.List;
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
public class ChangePasswordAccountAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private int registration_id ;

    Login rst;

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception

     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        try{
        AdminRegistrationDAO admindao = new AdminRegistrationDAO();
        //registration_id = Integer.parseInt(request.getParameter("id"));
        
        String instituteId = (String)session.getAttribute("institute_id");
        String user_id = (String)session.getAttribute("user_id");
         System.out.println("instituteId"+user_id+instituteId);
        rst = (Login)admindao.getLoginUser(user_id);
System.out.println("...................."+rst.getUserId());
        session.setAttribute("resultset1", rst);
      
        }
        catch(Exception e)
        {
        e.getMessage();
        }


        if(((String)session.getAttribute("login_role")).equalsIgnoreCase("Election Manager"))
           return mapping.findForward("success");
        else if(((String)session.getAttribute("login_role")).equalsIgnoreCase("candidate"))
            return mapping.findForward("success2");
        else  if(((String)session.getAttribute("login_role")).equalsIgnoreCase("voter"))
            return mapping.findForward("success1");


        
return null;

    }
}
