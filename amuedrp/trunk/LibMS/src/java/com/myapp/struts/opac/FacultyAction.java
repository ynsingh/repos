/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class FacultyAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id;
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
        
         FacultyDAO facultyDAO = new FacultyDAO();
        
        String faculty_id = request.getParameter("getFaculty_Id");
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
System.out.println(faculty_id+library_id);
        String depts = facultyDAO.getDeptByFacultyID(library_id, faculty_id);
        if (!depts.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(depts);

        }
        return null;
    }
}
