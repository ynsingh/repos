/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for Calling Ajax From Faculty table.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.systemsetupDAO.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Iqubal ahmad
 */
public class FacultyAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id;
    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for Calling Ajax From Faculty table.
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         FacultyDAO facultyDAO = new FacultyDAO();
        
        String faculty_id = request.getParameter("getFaculty_Id");
        library_id=request.getParameter("getLibrary_Id");
        
        HttpSession session=request.getSession();

        if(library_id==null)
        library_id=(String)session.getAttribute("library_id");
      
         
       
        String depts = facultyDAO.getDeptByFacultyID(library_id, faculty_id);
        if (!depts.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(depts);

        }
        return null;
    }
}
