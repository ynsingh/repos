/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.Courses;
import com.myapp.struts.systemsetupDAO.CourseDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author EdRP-05
 */
public class ViewAllCourseAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CourseDAO coursedao=new CourseDAO();
        HttpSession session=request.getSession();
        String     library_id=(String)session.getAttribute("library_id");
         String     sub_lib=(String)session.getAttribute("sublibrary_id");
             List<Courses> list=(List<Courses>)coursedao.getCourse(library_id);
             session.setAttribute("list", list);
        return mapping.findForward(SUCCESS);
    }
}
