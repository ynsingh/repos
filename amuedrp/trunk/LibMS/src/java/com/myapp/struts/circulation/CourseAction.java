/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Calling Ajax From Course table.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.systemsetupDAO.*;
import javax.servlet.http.*;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Iqubal Ahmad
 */
public class CourseAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
   
    String library_id;
    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Calling Ajax From Course table.
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         FacultyDAO facultyDAO = new FacultyDAO();
          String dept_id = request.getParameter("getDept_Id");
          
         
        String faculty_id = request.getParameter("getFacultyID");
        
     library_id = request.getParameter("getLibraryId");

        HttpSession session=request.getSession();

        if(library_id==null)
           library_id=(String)session.getAttribute("library_id");
       
       System.out.println(library_id+faculty_id+dept_id)   ;
      
        String courses = facultyDAO.getCourseByDeptID(library_id, faculty_id, dept_id);

        if (!courses.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(courses);

        }
        return null;
    }
}
