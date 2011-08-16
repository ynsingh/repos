/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.CirculationDAO.*;
import com.myapp.struts.hbm.Courses;
import com.myapp.struts.hbm.Department;
import com.myapp.struts.hbm.Faculty;
import com.myapp.struts.hbm.SubLibrary;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edrp01
 */
public class CirViewAllLoadAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
              HttpSession session=request.getSession();
            String library_id =(String)session.getAttribute("library_id");
           


           List faculty   = (List<Faculty>)CirculationDAO.LoadFaculty(library_id);

        
         
         
                 session.setAttribute("faculty",faculty );
         

        
         List dept   = (List<Department>)CirculationDAO.LoadDepartment(library_id);
                 session.setAttribute("dept",dept);
      
      List  courses =(List<Courses>)CirculationDAO.LoadCourses(library_id);
      
            session.setAttribute("courses",courses);

      
      
       List  sublibrary = (List<SubLibrary>)CirculationDAO.LoadSublibrary(library_id);
           session.setAttribute("sublibrary", sublibrary);
     


        return mapping.findForward(SUCCESS);
    }
}
