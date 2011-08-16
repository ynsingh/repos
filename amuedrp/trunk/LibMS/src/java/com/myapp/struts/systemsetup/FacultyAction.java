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
import java.util.Locale;
import java.util.ResourceBundle;

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
        FacultyActionForm faf=(FacultyActionForm)form;
        button=faf.getButton();
        faculty_id=faf.getFaculty_id();
       
        library_id=(String)session.getAttribute("library_id");

        if(button.equals("Add"))
        {
         Faculty sublibobj=FacultyDAO.getFacultyName(library_id, faculty_id);
         if(sublibobj!=null)
         {
            //request.setAttribute("msg1", "Faculty Id : "+faculty_id+" already exists");
             request.setAttribute("msg1", resource.getString("systemsetup.add_faculty.facultyid")+faculty_id+resource.getString("systemsetup.manage_notice.alreadyexists"));
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

                           // request.setAttribute("msg1", "Faculty Id: "+faculty_id+" doesn't exists");
                            request.setAttribute("msg1", resource.getString("systemsetup.add_faculty.facultyid")+faculty_id+resource.getString("systemsetup.manage_notice.doesnotexist"));

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
