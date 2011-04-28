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
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.DeptDAO;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import java.util.List;
/**
 *
 * @author edrp02
 */
public class FacultyUpdateViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String faculty_id;
    String faculty_name;
    String button;
    String library_id;
    boolean result;
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
        FacultyUpdateViewActionForm fuvaf=(FacultyUpdateViewActionForm)form;
        faculty_id=fuvaf.getFaculty_id();
        faculty_name=fuvaf.getFaculty_name();
        button=fuvaf.getButton();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
       // Faculty fac=(Faculty)session.getAttribute("faculty");
       
        Faculty faculty=FacultyDAO.searchFacultyName(library_id, faculty_id);
        if(button.equals("Update"))
        {
             faculty.getId().setFacultyId(faculty_id);
             faculty.setFacultyName(faculty_name);
             result=FacultyDAO.update(faculty,library_id);
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

               List<Department> dept=(List<Department>)DeptDAO.getDept(library_id, faculty_id);
               if(!dept.isEmpty())
               {
                request.setAttribute("msg1", "Dept Already there This faculty,Cannot Deleted");
              return mapping.findForward("success");
               }





            List<CirMemberAccount> cir=   (List<CirMemberAccount>)FacultyDAO.searchAccount(library_id,faculty_id);
           if(!cir.isEmpty()){
            request.setAttribute("msg1", "Account Created With This faculty,Cannot Deleted");
              return mapping.findForward("success");
           }

            result=FacultyDAO.Delete(faculty);
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


        return mapping.findForward("suc");
    }
}
