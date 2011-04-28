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
import com.myapp.struts.hbm.Faculty;
import com.myapp.struts.systemsetupDAO.FacultyDAO;

/**
 *
 * @author edrp02
 */
public class FacultyAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String faculty_id;
    String button;
    String library_id;
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FacultyActionForm faf=(FacultyActionForm)form;
        button=faf.getButton();
        faculty_id=faf.getFaculty_id();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");

        if(button.equals("Register"))
        {
         Faculty sublibobj=FacultyDAO.getFacultyName(library_id, faculty_id);
         if(sublibobj!=null)
         {
            request.setAttribute("msg1", "Faculty Id : "+faculty_id+" already exists");
            return mapping.findForward("duplicate");
         }
         else
         {

             request.setAttribute("new_faculty_id",faculty_id);
             return mapping.findForward("success");


         }
        }


Faculty sublibobj;
        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {



                         sublibobj=FacultyDAO.getFacultyName(library_id, faculty_id);
                        if(sublibobj==null)
                        {

                        request.setAttribute("msg1", "Faculty Id: "+faculty_id+" doesn't exists");

                        return mapping.findForward("duplicate");
                        }






         if(sublibobj!=null)
         {



          request.setAttribute("button",button);
           request.setAttribute("sublib", sublibobj);
             return mapping.findForward("update/view/delete");
         }



    }
        
        return mapping.findForward(SUCCESS);
    }
}
