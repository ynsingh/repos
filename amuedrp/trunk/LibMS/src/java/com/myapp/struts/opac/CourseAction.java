/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class CourseAction extends org.apache.struts.action.Action {
    
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
          String dept_id = request.getParameter("getDept_Id");
         
        String faculty_id = request.getParameter("getFacultyID");

        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
    
        //String query="select dept_id from department where library_id='"+library_id+"' and faculty_id='"+faculty_id+"' and dept_name='"+dept_name+"'";
       // ResultSet rs=MyQueryResult.getMyExecuteQuery(query);
       // if(rs.next())
      //  {
        //    dept_id=rs.getString(1);
       // }
        System.out.println(dept_id+" "+faculty_id);
        String courses = facultyDAO.getCourseByDeptID(library_id, faculty_id, dept_id);
        if (!courses.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(courses);

        }
        return null;
    }
}
