/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.CirMemberAccount;
import com.myapp.struts.hbm.Courses;
import com.myapp.struts.systemsetupDAO.CourseDAO;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author System Administrator
 */
public class CourseUpdateViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
   private static final String SUCCESS = "success";
    String course_id;
    String course_name;
    String button;
    String library_id;
    boolean result;

  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CourseUpdateViewActionForm duvaf=(CourseUpdateViewActionForm)form;
        course_id=duvaf.getCourse_id();
        course_name=duvaf.getCourse_name();
        button=duvaf.getButton();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        Courses course=(Courses)session.getAttribute("course");
        String faculty_id = (String)course.getId().getFacultyId();
        String dept_id = (String)course.getId().getDeptId();

        if(button.equals("Update"))
        {
            System.out.println("course_name="+course_name);
            // dept.setDeptId(dept_id);
            if(!course.getCourseName().equals(course_name))
            {
            Courses course1 = CourseDAO.searchCourseByName(library_id, faculty_id, dept_id, course_name);
        
         if(course1!=null)
         {
            request.setAttribute("msg1", "Course Name : "+course_name+" already exists");
            return mapping.findForward("success");
         }
            }
            course.setCourseName(course_name);
             result=CourseDAO.update1(course);
             if(result==true)
             {
              request.setAttribute("msg", "Record Update Successfully");
              return mapping.findForward("success");
             }
             else
             {
              request.setAttribute("msg", "Record Not Update");
              return mapping.findForward("success");
             }

        }
        if(button.equals("Delete"))
        {


              List<CirMemberAccount> cir=   (List<CirMemberAccount>)FacultyDAO.searchAccountCourse(library_id,course_id);
           if(!cir.isEmpty()){
            request.setAttribute("msg1", "Account Created With This Course,Cannot Deleted");
              return mapping.findForward("success");
           }





            result=CourseDAO.Delete(course);
             if(result==true)
             {
              request.setAttribute("msg", "Record Deleted Successfully");
              return mapping.findForward("success");
             }
             else
             {
              request.setAttribute("msg", "Record Not Deleted");
              return mapping.findForward("success");
             }


        }



        return mapping.findForward(SUCCESS);
    }
}
