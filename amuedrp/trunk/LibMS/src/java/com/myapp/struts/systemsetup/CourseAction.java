/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;


import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class CourseAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String course_id;
    String button;
    String library_id;
    String faculty_id;
    String dept_id;
    List dept;
    int faculty_rec_id=4;
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


         HttpSession session=request.getSession();
          try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        CourseActionForm caf=(CourseActionForm)form;
        faculty_id=caf.getFaculty_id();
        dept_id=caf.getDept_id();
        course_id=caf.getCourse_id();
        button=caf.getButton();
       
        System.out.println(faculty_id+" "+dept_id+" "+course_id);
        library_id=(String)session.getAttribute("library_id");


        
       
         if(button.equals("Add"))
        {
          Courses course=CourseDAO.searchCourseName(library_id,faculty_id,dept_id,course_id);
          
         if(course!=null)
         {
            // request.setAttribute("msg1", "Course Id : "+course_id+" already exists");
             request.setAttribute("msg1", resource.getString("systemsetup.manage_course.courseid")+course_id+resource.getString("systemsetup.manage_notice.alreadyexists"));
            return mapping.findForward("duplicate");
         }
         else
         {
              
             dept= DeptDAO.getDeptRecord(library_id,faculty_id);
             session.setAttribute("dept",dept);
              request.setAttribute("faculty_id",faculty_id);
           request.setAttribute("dept_id", dept_id);
             request.setAttribute("course_id",course_id);
             return mapping.findForward("success");


         }
        }


Courses course;
        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {



                         course=CourseDAO.searchCourseName(library_id,faculty_id,dept_id, course_id);
                        if(course==null)
                        {

                        //   request.setAttribute("msg1", "Course Id: "+course_id+" doesn't exists");
                        request.setAttribute("msg1", resource.getString("systemsetup.manage_course.courseid")+course_id+resource.getString("systemsetup.manage_notice.doesnotexist"));

                        return mapping.findForward("duplicate");
                        }






         if(course!=null)
         {

 dept= DeptDAO.getDeptRecord(library_id,faculty_id);
            session.setAttribute("dept",dept);
          List<Faculty>   faculty= FacultyDAO.searchFaculty(library_id);
             session.setAttribute("faculty",faculty);

          request.setAttribute("button",button);
           request.setAttribute("sublib",course);
           request.setAttribute("faculty_id",faculty_id);
           request.setAttribute("dept_id", dept_id);

             return mapping.findForward("update/view/delete");
         }



    }


        return mapping.findForward(SUCCESS);
    }
}
