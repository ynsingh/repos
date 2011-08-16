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

        CourseUpdateViewActionForm duvaf=(CourseUpdateViewActionForm)form;
        course_id=duvaf.getCourse_id();
        course_name=duvaf.getCourse_name();
        button=duvaf.getButton();
        
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
            //request.setAttribute("msg1", "Course Name : "+course_name+" already exists");
             request.setAttribute("msg1", resource.getString("systemsetup.manage_course.coursename")+course_name+resource.getString("systemsetup.manage_notice.alreadyexists"));
            return mapping.findForward("success");
         }
            }
            course.setCourseName(course_name);
             result=CourseDAO.update1(course);
             if(result==true)
             {

              //  request.setAttribute("msg", "Record Update Successfully");
              request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recupdatesucc"));
              return mapping.findForward("success");
             }
             else
             {

              //request.setAttribute("msg", "Record Not Update");
              request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recupdatenotsecc"));
              return mapping.findForward("success");
             }

        }
        if(button.equals("Delete"))
        {


              List<CirMemberAccount> cir=   (List<CirMemberAccount>)FacultyDAO.searchAccountCourse(library_id,course_id);
           if(!cir.isEmpty()){

           //  request.setAttribute("msg1", "Account Created With This Course,Cannot Deleted");
            request.setAttribute("msg1", resource.getString("systemsetup.courseupdateviewAction.acccreatewith"));
              return mapping.findForward("success");
           }





            result=CourseDAO.Delete(course);
             if(result==true)
             {

             //request.setAttribute("msg", "Record Deleted Successfully");
              request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recdelsucc"));
              return mapping.findForward("success");
             }
             else
             {

              //  request.setAttribute("msg", "Record Not Deleted");
              request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.memnotdelsucc"));
              return mapping.findForward("success");
             }


        }



        return mapping.findForward(SUCCESS);
    }
}
