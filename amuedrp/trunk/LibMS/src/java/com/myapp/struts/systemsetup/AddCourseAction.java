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
public class AddCourseAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String course_id;
    String course_name;
    String dept_name;
    String library_id;
    String dept_id;
    String faculty_id;
   
    List control_list;
    int max_id;
    boolean result;
    Courses c=new Courses();
    CoursesId cid=new CoursesId();
    Department d=new Department();
    DepartmentId did=new DepartmentId();
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
        AddCourseActionForm acaf=(AddCourseActionForm)form;
        course_id=acaf.getCourse_id();
        course_name=acaf.getCourse_name();
        faculty_id=acaf.getFaculty_id();
        dept_id=acaf.getDept_id();
        
       
        library_id=(String)session.getAttribute("library_id");

        
       
      Courses course = CourseDAO.searchCourseByName(library_id, faculty_id, dept_id, course_name);
        System.out.println("courses"+course);
         if(course!=null)
         {
            //request.setAttribute("msg1", "Course Name : "+course_name+" already exists");
             request.setAttribute("msg1", resource.getString("systemsetup.manage_course.coursename")+course_name+resource.getString("systemsetup.manage_notice.alreadyexists"));
            return mapping.findForward("success");
         }
         else
         {
        
        
        cid.setCourseId(course_id);
        cid.setLibraryId(library_id);
        cid.setDeptId(dept_id);
        cid.setFacultyId(faculty_id);

        did.setDeptId(dept_id);
        did.setFacultyId(faculty_id);
        did.setLibraryId(library_id);
        
        d.setId(did);
        c.setId(cid);
        c.setDepartment(d);
     
        c.setCourseName(course_name);

    
    

        result=CourseDAO.insert(c);
        if(result==true)
        {

            // request.setAttribute("msg", "Record Inserted Successfully");
            request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recinsesucc"));
            return mapping.findForward("success");

        }
        else
        {

            // request.setAttribute("msg", "Record Not Inserted");
            request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recnotinsesucc"));
            return mapping.findForward("success");
        }

         }
        
    }
}
