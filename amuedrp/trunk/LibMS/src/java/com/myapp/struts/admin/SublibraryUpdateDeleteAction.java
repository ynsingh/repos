/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AdminDAO.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.myapp.struts.hbm.HibernateUtil;
/**
 *
 * @author edrp02
 */
public class SublibraryUpdateDeleteAction extends org.apache.struts.action.Action {
    
    
    String library_id;
    private boolean result;
    String  button;
    String faculty;
    String sublib_name;
    String sublibrary_id;
    
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
          SublibraryUpdateDeleteActionForm subobj=(SublibraryUpdateDeleteActionForm)form;
          button=subobj.getButton();
          HttpSession session=request.getSession();
          library_id=(String)session.getAttribute("library_id");
         sublibrary_id=subobj.getSublibrary_id();
         System.out.println(button+".......................");
          if(button.equals("Update"))
          {
           
            SubLibrary sublib=SubLibraryDAO.getLibName(library_id, sublibrary_id);
             if(sublib!=null)
             {
                 sublib.setDeptAddress(subobj.getDepartment_address());

                 if(subobj.getFaculty().equals("Select"))
                     sublib.setFacultyName(subobj.getFaculty1());
                 else
                     sublib.setFacultyName(subobj.getFaculty());



                 if(subobj.getSublib_name().equals("Select"))
                     sublib.setSublibName(subobj.getSublib_name1());
                 else
                     sublib.setSublibName(subobj.getSublib_name());
             result=SubLibraryDAO.update(sublib);
             if(result==true)
             {
              String msg="Record Successfully updated";
              request.setAttribute("msg",msg);
              return mapping.findForward("success");
             }
             
            }
          }
          if(button.equals("Delete"))
          {
           
             result=SubLibraryDAO.Delete(library_id, sublibrary_id);
             if(result==true)
             {
              String msg="Record Successfully Deleted";
              request.setAttribute("msg",msg);
              return mapping.findForward("success");
             }
          
          }
        
        return null;
    }
}
