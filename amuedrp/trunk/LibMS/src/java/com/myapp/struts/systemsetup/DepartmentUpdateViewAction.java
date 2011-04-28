/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.systemsetupDAO.DeptDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.CourseDAO;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import java.util.List;

/**
 *
 * @author edrp02
 */
public class DepartmentUpdateViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String dept_id;
    String dept_name;
    String faculty_id;
    String button;
    String library_id;
    boolean result;

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DepartmentUpdateViewActionForm duvaf=(DepartmentUpdateViewActionForm)form;
        dept_id=duvaf.getDept_id();
        dept_name=duvaf.getDept_name();
        faculty_id=duvaf.getFaculty_name();
        button=duvaf.getButton();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
      Department dept=(Department)DeptDAO.getDeptByFaculty(library_id, faculty_id, dept_id);
        if(button.equals("Update"))
        {   
        
             dept.setDeptName(dept_name);

             result=DeptDAO.update(dept);
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

             List<Courses> course=(List<Courses>)CourseDAO.getCourse(library_id, faculty_id,dept_id);
               if(!course.isEmpty())
               {
                request.setAttribute("msg1", "Courses Already there This Dept,Cannot Deleted");
              return mapping.findForward("success");
               }


              List<CirMemberAccount> cir=   (List<CirMemberAccount>)FacultyDAO.searchAccount(library_id,faculty_id,dept_id);
           if(!cir.isEmpty()){
            request.setAttribute("msg1", "Account Created With This Dept,Cannot Deleted");
              return mapping.findForward("success");
           }

            result=DeptDAO.Delete(dept);
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
