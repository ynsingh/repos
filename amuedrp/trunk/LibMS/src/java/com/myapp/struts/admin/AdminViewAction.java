/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;


import com.myapp.struts.AdminDAO.AdminRegistrationDAO;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Dushyant
 */
public class AdminViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private int registration_id ;
    Connection con;
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
        registration_id = Integer.parseInt(request.getParameter("id"));
        rst = (List)admindao.getAdminInstituteDetailsById(registration_id);
        System.out.println("AdminvIEW"+rst+registration_id);
        request.setAttribute("resultset", rst);
        return mapping.findForward("success");
        }
        catch(Exception e)
        {
        e.getMessage();
        }
           return mapping.findForward("success");
    }
}
