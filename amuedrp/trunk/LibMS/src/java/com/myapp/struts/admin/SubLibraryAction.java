/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import com.myapp.struts.systemsetupDAO.DeptDAO;
import com.myapp.struts.systemsetupDAO.FacultyDAO;

import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class SubLibraryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private String sublibrary_id;
    private String button;
    private String library_id;
    
    
    String sql;
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        FacultyDAO facdao=new FacultyDAO();
        DeptDAO deptdao=new DeptDAO();
        SubLibraryDAO sublibdao=new SubLibraryDAO();
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
        AddSubLibraryActionForm sublibActionForm =(AddSubLibraryActionForm)form;
        sublibrary_id=sublibActionForm.getSublibrary_id();
        button=sublibActionForm.getButton();
       
       
        library_id=(String)session.getAttribute("library_id");
            List   list2=(List)facdao.searchFaculty(library_id);
            session.setAttribute("list2", list2);
            List   list3=(List)deptdao.searchDept(library_id);
            session.setAttribute("list3", list3);
       
        if(button.equals("Add"))
        {
         
         SubLibrary sublibobj=(SubLibrary)sublibdao.getLibName(library_id, sublibrary_id);
            
         if(sublibobj!=null)
         {
            request.setAttribute("msg1", resource.getString("systemsetup.add_sublibrary.sublibraryid")+sublibrary_id+resource.getString("systemsetup.manage_notice.alreadyexists"));
            return mapping.findForward("duplicate");
         }
         else
         {
            
               request.setAttribute("new_sublib_id",sublibrary_id);
             return mapping.findForward("success");
             
          
         }
        }
















SubLibrary sublibobj;
        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {

            

                         sublibobj=(SubLibrary)sublibdao.getLibName(library_id, sublibrary_id);
                        if(sublibobj==null)
                        {

                        request.setAttribute("msg1", resource.getString("systemsetup.add_sublibrary.sublibraryid")+sublibrary_id+resource.getString("systemsetup.manage_notice.doesnotexist"));

                        return mapping.findForward("duplicate");
                        }
             



            

         if(sublibobj!=null)
         {
           
           request.setAttribute("button",button);
         
           request.setAttribute("sublib", sublibobj);

         Department ds=deptdao.getDeptName(library_id,sublibobj.getSublibName());
        if(ds!=null)
            request.setAttribute("sublib_name",sublibobj.getSublibName());
      
        
             return mapping.findForward("update/view/delete");
         }
        
 
       
    }
 return null;
}
}
