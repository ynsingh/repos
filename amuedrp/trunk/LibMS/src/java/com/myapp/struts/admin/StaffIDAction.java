/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.*;
/**
 *
 * @author Dushyant
 */
public class StaffIDAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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
         EmailDAO emailDAO = new EmailDAO();
        String searchText = request.getParameter("getEmail_Id");
        String library_id = (String)session.getAttribute("library_id");
        String emails = emailDAO.getStaffID(searchText,library_id);
        if (!emails.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(emails);

        }
        return null;
    }
}
