/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import com.myapp.struts.hbm.AdminRegistrationDAO;
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
public class Admin_AccountAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private int registration_id ;

    List rst;

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
       // registration_id=(Integer.parseInt(request.getParameter("id")));
      //  System.out.println("Registration Id="+registration_id);
        try{
        AdminRegistrationDAO admindao = new AdminRegistrationDAO();
        //registration_id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        String instituteId = (String)session.getAttribute("institute_id");
System.out.println("instituteId");
        rst = (List)admindao.getAdminInstituteDetailsById(instituteId);

        session.setAttribute("resultset", rst);
        return mapping.findForward("success");
        }
        catch(Exception e)
        {
        e.getMessage();
        }
           return mapping.findForward("success");
    }
}
