/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;



import com.myapp.struts.AdminDAO.AdminRegistrationDAO;
import  com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.List;

/**
 *
 * @author System Administrator
 */
public class AdminUpdateAction extends org.apache.struts.action.Action {
    
   private int registration_id ;

    /* forward name="success" path="" */


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
     


         registration_id=(Integer.parseInt(request.getParameter("id")));
      //  System.out.println("Registration Id="+registration_id);
        try{
        AdminRegistrationDAO admindao = new AdminRegistrationDAO();
        
      List  rst= (List)admindao.getAdminInstituteDetailsById(registration_id);
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
