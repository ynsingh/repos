/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import com.myapp.struts.AdminDAO.AdminRegistrationDAO;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Shadab
 */
public class superadminModuleAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List rst;
        HttpSession session = request.getSession();
         AdminRegistrationDAO admindao = new AdminRegistrationDAO();
                    session.removeAttribute("resultset");
                    rst = admindao.getAdminDetailsByStatus("NotRegistered");
                    
                    session.setAttribute("resultset", rst);
                      int count = admindao.getAdminRequestCount("NotRegistered");

                
//                 get List of Rejected Library
                 rst = admindao.getAdminDetailsByStatus("rejected");
                      session.setAttribute("rejected", rst);




                    session.setAttribute("count", count);

                    rst = admindao.getAdminDetailsByStatus("Registered");

                    session.setAttribute("resultset1", rst);

                    count = admindao.getAdminRequestCount("Registered");
                    session.setAttribute("count1", count);


                   
                    rst = admindao.getAdminDetails();
                    session.setAttribute("resultset2", rst);

                    count = admindao.getAdminRequestCount();
                    session.setAttribute("count2", count);


                    //java mailing code to send it on Admin registration page
        return mapping.findForward(SUCCESS);
    }
}
