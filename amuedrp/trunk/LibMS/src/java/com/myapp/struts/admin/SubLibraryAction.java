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
    
    Connection con;
    PreparedStatement stmt;
    String sql;

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AddSubLibraryActionForm sublibActionForm =(AddSubLibraryActionForm)form;
        sublibrary_id=sublibActionForm.getSublibrary_id();
        button=sublibActionForm.getButton();
       
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
            List   list2=(List)FacultyDAO.searchFaculty(library_id);
            session.setAttribute("list2", list2);
            List   list3=(List)DeptDAO.searchDept(library_id);
            session.setAttribute("list3", list3);
       
        if(button.equals("Register"))
        {
         
         SubLibrary sublibobj=(SubLibrary)SubLibraryDAO.getLibName(library_id, sublibrary_id);
            
         if(sublibobj!=null)
         {
            request.setAttribute("msg1", "SubLibrary Id : "+sublibrary_id+" already exists");
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

            

                         sublibobj=(SubLibrary)SubLibraryDAO.getLibName(library_id, sublibrary_id);
                        if(sublibobj==null)
                        {

                        request.setAttribute("msg1", "Sublibrary Id: "+sublibrary_id+" doesn't exists");

                        return mapping.findForward("duplicate");
                        }
             



            

         if(sublibobj!=null)
         {
           
           
          request.setAttribute("button",button);
           request.setAttribute("sublib", sublibobj);

         Department ds= DeptDAO.getDeptName(library_id,sublibobj.getSublibName());
         if(ds!=null){
         request.setAttribute("sublib_name",sublibobj.getSublibName());
         }
         

             return mapping.findForward("update/view/delete");
         }
        

       
    }
 return null;
}
}
